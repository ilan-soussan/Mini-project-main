package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;


public class Plane implements Geometry {
    Point3D q0;
    Vector normal;
    public Plane(Point3D point,Vector vector)
    {
        q0=point;
        normal = vector;
    }



    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        Vector p1p2 = new Vector(p2.getX()- p1.getX(), p2.getY()- p1.getY(), p2.getZ()- p1.getZ());
        Vector p1p3 = new Vector(p3.getX()- p1.getX(), p3.getY()- p1.getY(), p3.getZ()- p1.getZ());
        normal = p1p2.crossProduct(p1p3);
        q0 = p1;
    }

    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal.normalize();
    }

    @Override
    public Vector getNormal(Point3D point) {
        return normal.normalize();
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {

        double normaldir = normal.dotProduct(ray.getRayDir());
        if (isZero(normaldir) || q0.equals(ray.getRayPoint())) {
            return null;
        }
        Vector QMinusP0 = q0.subtract(ray.getRayPoint());
        double t = Util.alignZero(normal.dotProduct(QMinusP0) / normaldir);
        if (t > 0) {
            List<Point3D> list = new LinkedList<>();
            Point3D p = new Point3D(ray.getRayPoint().getX()+t*ray.getRayDir().getHead().getX(),ray.getRayPoint().getY()+t*ray.getRayDir().getHead().getY(),ray.getRayPoint().getZ()+t*ray.getRayDir().getHead().getZ());
            list.add(p);
            return list;
        }
        return  null;
    }
}
