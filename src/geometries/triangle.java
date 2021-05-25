package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class triangle extends Polygon{

    public  triangle(Point3D p1,Point3D p2,Point3D p3)
    {
        super(p1,p2,p3);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal(point);
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        if(!isIntsersectionsExist(ray))
            return null;
        Plane p = new Plane(vertices.get(0),vertices.get(1),vertices.get(2));//// הנחה שהמצולע יכול להיות רק במישור אחד
        return p.findIntsersections(ray);
    }

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
