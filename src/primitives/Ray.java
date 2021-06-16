package primitives;

import java.util.List;

import geometries.Geometry;
import geometries.Intersectable.GeoPoint;



/**
 * ray class.
 * Represented by Point 3d and a Vector
 *
 *
 *
 * @author Ilan and didi
 */
public class Ray {

    Point3D p0;  //point
    Vector dir;  //vector

    private static final double DELTA = 0.1;  //delta for calculate a ray Very close to our orignal ray

    /**
     * constructor for ray. gets vector and a point and sets them in the right places.
     * @param vector
     * @param point
     */
    public Ray(Vector vector,Point3D point)
    {
        dir =vector.normalize();
        p0 = point;
    }

    /**
     * A second constructor used to create a ray that is
     * slightly different from our ray for creating shadow ray etc.
     * Calculates  whether to add or subtract the delta to the point
     * and then create the ray with the new point
     *
     * @param head
     * @param direction
     * @param normal
     */
    public Ray(Point3D head, Vector direction, Vector normal)
    {
        Vector delta = normal.scale(normal.dotProduct(direction) > 0 ? DELTA : - DELTA);
        Point3D tempPoint = head.add(delta);
        dir = direction.normalize();
        p0 = tempPoint;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return this.dir.equals(other.dir) && this.p0.equals(other.p0);
    }


    //gets
    public Point3D getRayPoint() {
        return p0;
    }

    public Vector getRayDir() {
        return dir;
    }

    /**
     * return new point that is the rat point * some t
     * @param t
     * @return new point
     */
    public  Point3D getPoint(double t)
    {
        Point3D p = new Point3D(p0.getX()+t*dir.getHead().getX(),p0.getY()+t*dir.getHead().getY(),p0.getZ()+t*dir.getHead().getZ());
        return p;
    }


    /**
     * gets a list of Points and return the closest to our source point.
     *
     * @param list
     * @return closest point
     */
    public Point3D findClosestPoint(List<Point3D> list)
    {
        if (list.isEmpty())
            return null;

        double dist = p0.distance(list.get(0));
        Point3D closestPoint = list.get(0);

        for (Point3D p:list){ //for each
            if(p0.distance(p)<dist) {
                dist = p0.distance(p);
                closestPoint = p;
            }
        }
        return closestPoint;
    }


    /**
     * gets a list of GetPoints and return the closest to our source point.
     * @param list
     * @return closest geoPoint
     */
    public GeoPoint findClosestGeoPoint(List<GeoPoint> list){
        if (list.isEmpty())
            return null;

        double dist = p0.distance(list.get(0).point);
        Geometry geometry = list.get(0).geometry;
        Point3D closestPoint = list.get(0).point;

        for (GeoPoint p:list){//for each
            if(p0.distance(p.point)<dist) {
                dist = p0.distance(p.point);
                geometry = p.geometry;
                closestPoint = p.point;
            }
        }
        return new GeoPoint(geometry,closestPoint);
    }
}
