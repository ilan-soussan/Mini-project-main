package renderer;

import elements.LightSource;
import primitives.*;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

import java.util.List;

import static primitives.Util.alignZero;

public class RayTracerBasic extends RayTracerBase {
    public RayTracerBasic(Scene myScene) {
        super(myScene);
    }

    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(lightDirection.getHead(), lightDirection, n); // refactored ray head move
        List<GeoPoint> intersections = scene.geometries.findGeoIntersection(lightRay);
        if (intersections == null) return true;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0 &&
                    gp.geometry.getMaterial().kT == 0)
                return false;
        }
        return true;
    }


/*
    private boolean unshaded(LightSource light, Vector l, Vector n, GeoPoint geopoint) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Vector delta = n.scale(n.dotProduct(lightDirection) > 0 ? DELTA : -DELTA);
        Point3D point = geopoint.point.add(delta);
        Ray lightRay = new Ray( lightDirection,point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersection(lightRay);
        if (intersections == null) return true;
        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero((gp.point.distance(geopoint.point)-lightDistance)) <= 0)
                return false;
        }
        return true;
    }
*/

    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

   /*     @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersection(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint,ray);
    }*/

    private Color calcColor(GeoPoint intersection, Ray ray,int level,double k) {
        Color color = intersection.geometry.getEmission()
        // add calculated light contribution from all light sources)
                .add(calcLocalEffects(intersection, ray));
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getRayDir(), level, k));
    }
    private Color calcColor(GeoPoint geopoint, Ray ray) {

        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }
    private Color calcGlobalEffects(GeoPoint gp, Vector v, int level, double k) {
        Color color = Color.BLACK;
        Vector n = gp.geometry.getNormal(gp.point);
        Material material = gp.geometry.getMaterial();
        double kkr = k * material.kR;
        if (kkr > MIN_CALC_COLOR_K)
            color = calcGlobalEffect(constructReflectedRay(gp.point, v, n), level, material.kR, kkr);
        double kkt = k * material.kT;
        if (kkt > MIN_CALC_COLOR_K)
            color = color.add(
                    calcGlobalEffect(constructRefractedRay(gp.point, v, n), level, material.kT, kkt));
        return color;
    }

    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {

        return new Ray(v ,point);
    }

    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        //   𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ 𝒏
        return new Ray(v.subtract((v.crossProduct(n).crossProduct(n).scale(2))), point);
    }

    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection(ray);
        return (gp == null ? scene.background : calcColor(gp, ray, level-1, kkx)
        ).scale(kx);
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getRayDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = Util.alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = Util.alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                if (unshaded(lightSource,l,n, intersection)) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }

        }
        return color;
    }

    private boolean occluded( LightSource lightSource,GeoPoint intersection) {
        Vector lightDir = lightSource.getL(intersection.point);
        lightDir.scale(-1);

        Ray lightRay= new Ray(lightDir,intersection.point);

        List<GeoPoint> intersectionPoints = intersection.geometry.findGeoIntersection(lightRay);

        return (intersectionPoints == null);

    }
    private GeoPoint findClosestIntersection(Ray ray)
    {
        List<GeoPoint> list = scene.geometries.findGeoIntersection(ray);
        if(list == null)
            return null;
        GeoPoint p = ray.findClosestGeoPoint(list);
        if(p != null)
            return p;
        else
            return null;


    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return lightIntensity.scale(kd* Math.abs(l.dotProduct(n)));
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        return lightIntensity.scale(ks*Math.pow((v.scale(-1).dotProduct(l.subtract(n.scale(2*l.dotProduct(n))))),nShininess));
    }



}
