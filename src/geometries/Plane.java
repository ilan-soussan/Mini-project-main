package geometries;

import primitives.*;

public class Plane implements Geometry {
    Point3D q0;
    Vector normal;
    public Plane(Point3D point,Vector vector)
    {
        q0=point;
        normal = vector;
    }
    public Plane(Point3D point,Point3D point1,Point3D point2)
    {
        q0=point;

    }

    public Point3D getQ0() {
        return q0;
    }

    public Vector getNormal() {
        return normal;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
