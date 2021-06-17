package geometries;
import primitives.*;

/**
 * this class is rempresnting a cyinder(didn't use this for the project
 */
public class Cylinder extends Tube {
    double height;

    /**
     * @param Height
     * @param Radius
     * @param ray
     * constractor for cylinder that use tube constractor and add the height
     */
    public Cylinder(double Height, double Radius, Ray ray)
    {
        super(Radius, ray);
        height = Height;
    }

    /**
     * @param Height
     * @param Radius
     * @param Point
     * @param V
     * like the other constractor but using a point and vector and not a ray
     */
    public Cylinder(double Height, double Radius, Point3D Point, Vector V)
    {
        super(Radius, Point,V);
        height = Height;
    }

    /**
     * @param point
     * @return vector normal
     * normalize axisray vec and return it
     */
    @Override
    public Vector getNormal(Point3D point) {
        return axisRay.getRayDir().normalized();
    }
    public double getHeight() {
        return height;
    }
}
