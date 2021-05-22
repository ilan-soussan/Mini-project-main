package geometries;
import primitives.*;

import java.util.List;

public class Cyilnder extends Tube {
    double height;

    public Cyilnder (double Height,double Radius,Ray ray)
    {
        super(Radius, ray);
        height = Height;
    }
    public Cyilnder (double Height,double Radius,Point3D Point,Vector V)
    {
        super(Radius, Point,V);
        height = Height;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return axisRay.getRayDir().normalized();
        //return super.getNormal(point);
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }

    public double getHeight() {
        return height;
    }
}
