package geometries;

import primitives.*;

import java.util.List;

public class Tube implements Geometry{
    Ray axisRay;
    double radius;

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
    public Tube(double Radius, Ray ray)
    {
        radius = Radius;
        axisRay = ray;
    }
    public Tube(double Radius, Point3D point,Vector V)
    {
        radius = Radius;
        axisRay = new Ray(V,point);
    }
    public double getRadius() {
        return radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point3D point)
    {
        double t = (axisRay.getRayDir().scale(point.distance(axisRay.getRayPoint()))).length();
        Point3D O = axisRay.getRayPoint().add(axisRay.getRayDir().scale(t));
        Vector V = point.subtract(O);
        return V.normalized();
    }
}
