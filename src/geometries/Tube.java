package geometries;

import primitives.*;

public class Tube implements Geometry{
    Ray axisRay;
    double radius;

    public double getRadius() {
        return radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point3D point) {
        return null;
    }
}
