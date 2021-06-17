package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


/**
 * we make a class that represent sphere and hes a part of geometries
 */
public class Sphere extends Geometry {

    Point3D center;
    double radius;
    Vector normal;
    Box box; //Conservative Bounding Region

    public Sphere(double r,Point3D Center)
    {
        center = Center;
        radius = r;
        //we ccreating bounding box  calculating the min/max point to be the 2 points at the end of a Koter
        box = new Box(new Point3D(Center.getX() + r,Center.getY() + r,Center.getZ() + r)
                ,new Point3D(Center.getX() - r,Center.getY() - r,Center.getZ() - r));
    }
    public Sphere(Point3D Center,Point3D p1)
    {
        center = Center;
        radius = p1.distance(Center);
        Vector n = new Vector(p1.getX()-center.getX(),p1.getY()-center.getY(), p1.getZ()-center.getZ());
        //we ccreating bounding box  calculating the min/max point to be the 2 points at the end of a Koter
        box = new Box(new Point3D(center.getX() + radius,center.getY() + radius,center.getZ() + radius)
                ,new Point3D(center.getX() - radius,center.getY() - radius,center.getZ() - radius));
        normal = n.normalize();
    }
    @Override
    public Box getBox() {
        if(box != null)
            return box;
        return null;
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

    /**
     *
     * @param ray
     * @return list<geoPoint>
     * this function we search and fine all the points on the sphere that interact with ray
     */
    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        //u is a new vector we create that calculate dist between center and ray point
        Vector u = new Vector(new Point3D(center.getX()-ray.getPoint(0).getX(),center.getY()-ray.getPoint(0).getY(),center.getZ()-ray.getPoint(0).getZ()));
        //the first "T" of intersection
        double Tm = ray.getRayDir().dotProduct(u);
        //distance: i calculated by the Nouscha
        double d = Math.sqrt(Math.pow(u.length(),2) - Math.pow(Tm,2));
        //the point in sphere
        if(d >= radius)
            return null;
        double Th = Math.sqrt(Math.pow(radius,2)-Math.pow(d,2));//secound T of intersecion
        double T1 = Tm - Th;
        double T2 = Tm + Th;
        GeoPoint P1 = new GeoPoint(this,ray.getPoint(T1));//intersection
        GeoPoint P2 = new GeoPoint(this,ray.getPoint(T2));//intersection
        List<GeoPoint> list = new LinkedList<>();
        if (T1 > 0) //the intersection is biggere then 0(distance)
            list.add(P1);
        if (T2 > 0)//the intersection is biggere then 0(distance)
            list.add(P2);
        if(list.size() > 0) //if list isnt empty
            return list;
        else
            return  null;
    }
}
