package geometries;
import primitives.*;

import java.util.List;

/**
 *  class that extends Tube
 *  our why to represent Cylinder in our scene
 *
 *   @author Ilan and didi
 *
 */
public class Cylinder extends Tube {
    double height;

    /**
     * constructor for Cylinder class. gets Height,Radius and ray and implant them in the right places.
     * @param Height
     * @param Radius
     * @param ray
     */
    public Cylinder(double Height, double Radius, Ray ray)
    {
        super(Radius, ray);
        height = Height;
    }

    /**
     * constructor for Cylinder class. gets Height,Radius and ray(in shape Vector and a point) and implant them in the right places.
     * @param Height
     * @param Radius
     * @param Point
     * @param V
     */
    public Cylinder(double Height, double Radius, Point3D Point, Vector V)
    {
        super(Radius, Point,V);
        height = Height;
    }

    /**
     * gets the normal of the Cylinder
     * @param point
     * @return Vector normal to the Cylinder
     */
    @Override
    public Vector getNormal(Point3D point) {
        return axisRay.getRayDir().normalized();
        //return super.getNormal(point);
    }

    /**
     * gets the height of the Cylinder
     * @return
     */
    public double getHeight() {
        return height;
    }
}
