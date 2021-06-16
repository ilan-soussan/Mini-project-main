package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;
import geometries.Intersectable.GeoPoint;

/**
 *  abstract RayTracerBase class.
 * Represented by Scene
 * extends by RayTracerBasic. have 3 funcs for his chiled
 * traceRay , findClosestIntersection and a constructor.
 *
 *
 * @author Ilan and didi
 */
public abstract class RayTracerBase
{
    protected Scene scene;
    public RayTracerBase(Scene myScene)
    {
        scene = myScene;
    }
    public abstract Color traceRay(Ray ray);
    public abstract GeoPoint findClosestIntersection(Ray ray);
    }
