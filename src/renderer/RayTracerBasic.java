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

    @Override
    public Color traceRay(Ray ray) {
        List<GeoPoint> intersections = scene.geometries.findGeoIntersection(ray);
        if (intersections == null) return scene.background;
        GeoPoint closestPoint = ray.findClosestGeoPoint(intersections);
        return calcColor(closestPoint);
    }

    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(intersection.geometry.getEmission())
// add calculated light contribution from all light sources)
                .add(calcLocalEffects(intersection, ray));
    }

    private Color calcLocalEffects(GeoPoint intersection, Ray ray) {
        Vector v = ray.getRayDir (); Vector n = intersection.geometry.getNormal();
        double nv = alignZero(n.dotProduct(v); if (nv == 0) return Color.BLACK;
        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l);
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color lightIntensity = lightSource.getIntensity(intersection.point);
                color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                        calcSpecular(ks, l, n, v, nShininess, lightIntensity));
            }
        }
        return color;
    }

    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {
        Vector r = l.subtract()
        ks*Math.max(0,-(v.))
        return null;
    }

    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        return null;
    }



    /*
    private Color calcColor(GeoPoint closestPoint) {

        return closestPoint.geometry.getEmission().add(scene.ambientLight.getIntensity())
    }*/
}
