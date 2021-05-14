package geometries;

import primitives.*;

public class Sphere implements Geometry {

    Point3D center;
    double radius;
    Vector normal;

    public Sphere(Point3D Center,double r)
    {
        center = Center;
        radius = r;
    }
    public Sphere(Point3D Center,Point3D p1)
    {
        center = Center;
        radius = p1.distance(Center);
        Vector n = new Vector(p1.getX()-center.getX(),p1.getY()-center.getY(), p1.getZ()-center.getZ());
        normal = n.normalize();
    }
    public double getRadius() {
        return radius;
    }
    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D p1) {
        Vector n = new Vector(p1.getX()-center.getX(),p1.getY()-center.getY(), p1.getZ()-center.getZ());
        normal = n.normalize();
        return normal;
    }
}
