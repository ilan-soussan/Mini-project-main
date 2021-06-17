package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

import static primitives.Util.isZero;


/**
 * we make a class that represent plane and hes a part of geometries
 */
public class Plane extends Geometry {
    Point3D q0;
    Vector normal;
    Box box;
    public Plane(Point3D point,Vector vector)
    {
        q0=point;
        Point3D p1 = new Point3D(0,0,0);
        Point3D p2 = new Point3D(vector.getHead().getX(),vector.getHead().getY(),vector.getHead().getZ());
        Plane plane = new Plane(point,p1,p2);
        normal = plane.normal;
        //we make this here cause a plane is all over the dimensins
        box = new Box(new Point3D(Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE),new Point3D(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE));
    }



    public Plane(Point3D p1, Point3D p2, Point3D p3)
    {
        Vector p1p2 = new Vector(p2.getX()- p1.getX(), p2.getY()- p1.getY(), p2.getZ()- p1.getZ());
        Vector p1p3 = new Vector(p3.getX()- p1.getX(), p3.getY()- p1.getY(), p3.getZ()- p1.getZ());
        normal = p1p2.crossProduct(p1p3).normalize();
        q0 = p1;
        //we make this here cause a plane is all over the dimensins
        box = new Box(new Point3D(Double.MIN_VALUE,Double.MIN_VALUE,Double.MIN_VALUE),new Point3D(Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE));
    }

    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return normal.normalize();
    }

/*    @Override
    public List<Point3D> findIntsersections(Ray ray) {

        double normaldir = normal.dotProduct(ray.getRayDir());
        if (isZero(normaldir) || q0.equals(ray.getRayPoint())) {
            return null;
        }
        Vector QMinusP0 = q0.subtract(ray.getRayPoint());
        double t = Util.alignZero(normal.dotProduct(QMinusP0) / normaldir);
        if (t > 0) {
            List<Point3D> list = new LinkedList<>();
            Point3D P = ray.getPoint(t);
            list.add(P);
            return list;
        }
        return  null;
    }*/

    @Override
    public Box getBox() {
        if(box != null)
            return box;
        return null;
    }

    /**
     * @param ray
     * @return list of geo points
     * this function we search and fine all the points on the plane that interact with ray
     */
    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        double normaldir = normal.dotProduct(ray.getRayDir()); //direction of the ray and normal scale
        if (isZero(normaldir) || q0.equals(ray.getRayPoint())) { //we devide by normaldir so cant be 0 if qo and ray point the same line 90 wont work
            return null;
        }
        Vector QMinusP0 = q0.subtract(ray.getRayPoint());
        double t = Util.alignZero(normal.dotProduct(QMinusP0) / normaldir);
        if (t > 0) { //onlt if t>0
            List<GeoPoint> list = new LinkedList<>();
            GeoPoint P = new GeoPoint(this,ray.getPoint(t)); //find and add the point of intersections to the list
            list.add(P);
            return list;
        }
        return  null; //didnt find points
    }

}
