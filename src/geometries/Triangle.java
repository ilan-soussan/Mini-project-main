package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.List;

/**
 * we make a class that represent triangle and hes a part of geometries
 */
public class Triangle extends Polygon{

    public Box box;
    //in this constractor that make a plane of 3 points and also choose the min/max coordinate of the 3 points and put them to make the box
    public Triangle(Point3D p1, Point3D p2, Point3D p3)
    {
        super(p1,p2,p3);
        double PxMax,PyMax,PzMax,PxMin,PyMin,PzMin;
        //which coordinate x the biggest
        if (p1.getX() >= p2.getX() && p1.getX() >= p3.getX())
            PxMax = p1.getX();
        else if (p1.getX() <= p2.getX() && p2.getX() >= p3.getX())
            PxMax = p2.getX();
        else
            PxMax = p3.getX();
        //which coordinate y the biggest
        if (p1.getY() >= p2.getY() && p1.getY() >= p3.getY())
            PyMax = p1.getY();
        else if (p1.getY() <= p2.getY() && p2.getY() >= p3.getY())
            PyMax = p2.getY();
        else
            PyMax = p3.getY();
        //which coordinate z the biggest

        if (p3.getZ() <= p1.getZ() && p1.getZ() >= p2.getZ())
            PzMax = p1.getZ();
        else if (p1.getZ() <= p2.getZ() && p2.getZ() >= p3.getZ())
            PzMax = p2.getZ();
        else
            PzMax = p3.getZ();


        //which coordinate x the smallest

        if (p1.getX() <= p2.getX() && p1.getX() <= p3.getX())
            PxMin = p1.getX();
        else if (p1.getX() >= p2.getX() && p2.getX() <= p3.getX())
            PxMin = p2.getX();
        else
            PxMin = p3.getX();
        //which coordinate x the smallest

        if (p1.getY() <= p2.getY() && p1.getY() <= p3.getY())
            PyMin = p1.getY();
        else if (p1.getY() >= p2.getY() && p2.getY() <= p3.getY())
            PyMin = p2.getY();
        else
            PyMin = p3.getY();
        //which coordinate x the smallest

        if (p3.getZ() >= p1.getZ() && p2.getZ() >= p1.getZ())
            PzMin = p1.getZ();
        else if (p1.getZ() >= p2.getZ() && p2.getZ() <= p3.getZ())
            PzMin = p2.getZ();
        else
            PzMin = p3.getZ();
        box = new Box(new Point3D(PxMax,PyMax,PzMax),new Point3D(PxMin,PyMin,PzMin));


    }
    @Override
    public Box getBox(){if(box != null)
        return box;
        return null;}
    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal(point);
    }

    /**
     *
     * @param ray
     * @return list<geoPoint>
     * this function we search and fine all the points on the sphere that interact with ray
     */
    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray){
        if(!isIntsersectionsExist(ray))//there is an intersection
            return null;
        Plane p = new Plane(vertices.get(0),vertices.get(1),vertices.get(2));//// הנחה שהמצולע יכול להיות רק במישור אחד
        p.material = this.material;
        p.emission = this.emission;
        return p.findGeoIntersection(ray);
    }

    /**
     * does the rayy have intersection with triangle?
     * @param ray
     * @return
     */
    @Override
    public boolean isIntsersectionsExist(Ray ray)
    {
        boolean flag = true;
        Vector V1 = vertices.get(0).subtract(ray.getRayPoint());
        Vector V2 = vertices.get(1).subtract(ray.getRayPoint());
        Vector V3 = vertices.get(2).subtract(ray.getRayPoint());

        Vector N1 = V1.crossProduct(V2).normalize();
        Vector N2 = V2.crossProduct(V3).normalize();
        Vector N3 = V3.crossProduct(V1).normalize();

        Double num1=  N1.dotProduct(ray.getRayDir());
        Double num2=  N2.dotProduct(ray.getRayDir());
        Double num3=  N3.dotProduct(ray.getRayDir());

        if((num1>0 && num2<0) || (num1<0 && num2>0))
            flag=false;
        if((num2>0 && num3<0) || (num2<0 && num3>0))
            flag=false;
        if(Util.isZero(Util.alignZero(num1)))
        flag=false;
        if(Util.isZero(Util.alignZero(num2)))
        flag=false;
        if(Util.isZero(Util.alignZero(num3)))
        flag=false;

        return flag;
    }
}
