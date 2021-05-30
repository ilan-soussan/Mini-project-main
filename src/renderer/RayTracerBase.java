package renderer;

import primitives.Color;
import primitives.Ray;
import scene.Scene;

public abstract class RayTracerBase
{
    protected Scene scene;
    public RayTracerBase(Scene myScene)
    {
        scene = myScene;
    }
    public abstract Color traceRay(Ray ray);
}
