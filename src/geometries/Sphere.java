package geometries;

import primitives.*;

public class Sphere implements Geometry {
    Point3D center;
    double radius;

    public double getRadius() {
        return radius;
    }

    public Point3D getCenter() {
        return center;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
