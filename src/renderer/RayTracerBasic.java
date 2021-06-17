package renderer;

import java.util.List;
import geometries.Intersectable.GeoPoint;
import primitives.*;
import scene.Scene;
import elements.*;

import static primitives.Util.alignZero;


/**
 *
 *  RayTracerBasic class , extends RayTracerBase
 *  Responsible for all color calculations
 *  Implements the 3 functions it inherits from RayTracerBase
 *  traceRay , findClosestIntersection , RayTracerBase(constructor)
 *   @author Ilan and didi
 */
public class RayTracerBasic extends RayTracerBase {
    private static final double INITIAL_K = 1.0;
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    public RayTracerBasic(Scene aScene) {
        super(aScene);
    }

    /**
     * finding the Closest Intersection to our ray point
     * @param ray
     * @return closest point to ray point
     */
    @Override
    public GeoPoint findClosestIntersection(Ray ray) {
        List<GeoPoint> list = scene.geometries.findGeoIntersection(ray);
        if(list == null)
            return null;
        GeoPoint p = ray.findClosestGeoPoint(list);
        if(p != null)
            return p;
        else
            return null;


    }

    /**
     * main func to calc Color
     * li = ambientLight(le) + LocalEffects(KamLam) + Diffusive(kd*(N*l)*li) + ks*(V*R)^n*li
     * @param geopoint
     * @param ray
     * @return the Color
     */
    private Color calcColor(GeoPoint geopoint, Ray ray) {
        return calcColor(geopoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K)
                .add(scene.ambientLight.getIntensity());
    }

    /**
     * secound func to calc Color
     * li = LocalEffects(KamLam) + Diffusive(kd*(N*l)*li) + ks*(V*R)^n*li
     *
     * @param intersection
     * @param ray
     * @param level for our Recursion
     * @param k Below this value we neglect
     * @return the Color except ambientLight
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, double k) {

        Color color = intersection.geometry.getEmission(); //

        color = color.add(calcLocalEffects(intersection, ray,k)); // add Local Effects

        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray.getRayDir(), level, k)); // add GlobalE ffects
    }


    /**
     * Finds the light path, if empty returns 1
     * If not empty, Calculates the light passing through each time.
     * In case it is less than our minimum value (MIN_CALC_COLOR_K), returns 0.
     * Otherwise it returns what returned from the light
     * @param light
     * @param l
     * @param n
     * @param geopoint
     * @return
     */
    private double transparency(LightSource light, Vector l, Vector n, GeoPoint geopoint)
    {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geopoint.point, lightDirection, n); // refactored ray head move

        List<GeoPoint> intersections = scene.geometries.findGeoIntersection(lightRay);
        if (intersections == null)
            return 1.0;
        double ktr = 1.0;

        double lightDistance = light.getDistance(geopoint.point);
        for (GeoPoint gp : intersections) {
            if (alignZero(gp.point.distance(geopoint.point) - lightDistance) <= 0)
            {
                ktr *= gp.geometry.getMaterial().kT;
                if (ktr < MIN_CALC_COLOR_K) return 0.0;
            }
        }
        return ktr;

    }


    /**
     *
     * @param gp
     * @param v
     * @param level
     * @param k
     * @return Global Effects Color
     */
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

    /**
     *  return new ray with the point +- delta and v
     * @param point
     * @param v
     * @param n
     * @return
     */
    private Ray constructRefractedRay(Point3D point, Vector v, Vector n) {
        return new Ray(point,v,n);
    }

    /**
     * //   𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ 𝒏
     * @param point
     * @param v
     * @param n
     * @return ray  𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ 𝒏
     */
    private Ray constructReflectedRay(Point3D point, Vector v, Vector n) {
        //   𝒓 = 𝒗 − 𝟐 ∙ (𝒗 ∙ 𝒏) ∙ 𝒏
        Vector r = v.subtract(n.scale(2*v.dotProduct(n))).normalize();
        return new Ray(point,r,n);
    }

    /**
     * calcGlobalEffect
     * Each time seeks the point closest to it
     * up to 10 levels (recursion) and returns the global Effect
     * @param ray
     * @param level
     * @param kx
     * @param kkx
     * @return Global Effect Color
     */
    private Color calcGlobalEffect(Ray ray, int level, double kx, double kkx) {
        GeoPoint gp = findClosestIntersection (ray);
        return (gp == null ? scene.background : calcColor(gp, ray, (level- 1), kkx)
        ).scale(kx);
    }

    /**
     * calcLocalEffects
     * calc the Local effects, Diffusive + Specular
     * KamIam
     *
     * @param intersection
     * @param ray
     * @param k
     * @return Color of local effect
     */
    private Color calcLocalEffects(GeoPoint intersection, Ray ray,double k) {
        Vector v = ray.getRayDir ();
        Vector n = intersection.geometry.getNormal(intersection.point);
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0) return Color.BLACK;

        Material material = intersection.geometry.getMaterial();
        int nShininess = material.getShininess();
        double kd = material.getKd(), ks = material.getKs();
        Color color = Color.BLACK;

        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(intersection.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                double ktr = transparency(lightSource, l, n, intersection);
                if (ktr * k > MIN_CALC_COLOR_K) {
                    Color lightIntensity = lightSource.getIntensity(intersection.point).scale(ktr);
                    color = color.add(calcDiffusive(kd, l, n, lightIntensity),
                            calcSpecular(ks, l, n, v, nShininess, lightIntensity));
                }
            }
        }
        return color;
    }

    /**
     * //kd*(n*l)*li
     * @param kd
     * @param l
     * @param n
     * @param lightIntensity
     * @return Diffusive Color
     */
    private Color calcDiffusive(double kd, Vector l, Vector n, Color lightIntensity) {
        //kd*(n*l)*li
        return lightIntensity.scale(kd* Math.abs(l.dotProduct(n)));
    }

    /**         //ks*(V*R)^n*Li
     *
     * @param ks
     * @param l
     * @param n
     * @param v
     * @param nShininess
     * @param lightIntensity
     * @return Specular Color
     */
    private Color calcSpecular(double ks, Vector l, Vector n, Vector v, int nShininess, Color lightIntensity) {

        //ks*(V*R)^n*Li
        return lightIntensity.scale(ks*Math.pow((v.scale(-1).dotProduct(l.subtract(n.scale(2*l.dotProduct(n))))),nShininess));
    }

    /**
     * trace the ray and calc the Color for the pixsel.
     * @param ray
     * @return
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }
}
