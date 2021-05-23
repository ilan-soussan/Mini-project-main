package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Sphere implements Geometry {

    Point3D center;
    double radius;
    Vector normal;


    public Sphere(double r,Point3D Center)
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

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        Vector u = new Vector(new Point3D(center.getX()-ray.getRayDir().getHead().getX(),center.getY()-ray.getRayDir().getHead().getY(),center.getZ()-ray.getRayDir().getHead().getZ()));
        double Tm = ray.getRayDir().dotProduct(u);
        double d = Math.sqrt(Math.pow(u.length(),2) - Math.pow(Tm,2));
        if(d > radius)
            return null;
        double Th = Math.sqrt(Math.pow(radius,2)-Math.pow(d,2));
        double T1 = Tm - Th;
        double T2 = Tm + Th;
        Point3D P1 = ray.getPoint(T1);
        Point3D P2 = ray.getPoint(T2);
        List<Point3D> list = new LinkedList<>();
        if (T1 > 0)
            list.add(P1);
        if (T2 > 0)
            list.add(P2);
        if(list.size() > 0)
            return list;
        else
            return  null;
    }
}
