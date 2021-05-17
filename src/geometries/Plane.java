package geometries;

import primitives.*;
import static java.lang.System.out;


public class Plane implements Geometry {
    Point3D q0;
    Vector normal;
    public Plane(Point3D point,Vector vector)
    {
        q0=point;
        normal = vector;
    }
    public Plane(Point3D p1,Point3D p2,Point3D p3)
    {
        /*try
        {
            double p1p2X = p1.getX()/p2.getX();
            double p1p3X = p1.getX()/p3.getX();
            double p3p2X = p1.getX()/p2.getX();
            double p1p2X = p1.getX()/p2.getX();
            double p1p2X = p1.getX()/p2.getX();

        }*/
        Vector p1p2 = new Vector(p2.getX()- p1.getX(), p2.getY()- p1.getY(), p2.getZ()- p1.getZ());
        Vector p1p3 = new Vector(p3.getX()- p1.getX(), p3.getY()- p1.getY(), p3.getZ()- p1.getZ());
        if(p1.equals(p2)||p2.equals(p3)||p3.equals(p1))
        {
            out.println("same point3D");
        }
        if(p1.samePoiontRasio(p2) || p2.samePoiontRasio(p3) || p3.samePoiontRasio(p1))
        {
            out.println("same point3D");
        }
        normal = p1p2.crossProduct(p1p3);
        normal.normalize();
        q0 = p1;
    }

    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return normal;
    }
}
