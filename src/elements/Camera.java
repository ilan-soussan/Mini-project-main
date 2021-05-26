package elements;

import primitives.*;

public class Camera {
    private Point3D point;
    private Vector Vup;
    private Vector Vleft;
    private Vector Vright;

    double Width;
    double Height;
    double Distance;

    public Vector getVleft() {
        return Vleft;
    }

    public Vector getVright() {
        return Vright;
    }

    public Vector getVup() {
        return Vup;
    }

    public Point3D getPoint() {
        return point;
    }

    public Camera(Point3D cameraPoint, Vector cameraVup, Vector cameraVleft) {
        if (!Util.isZero(cameraVup.dotProduct(cameraVleft))) {
            throw new IllegalArgumentException("Vectors not vertical to each other");
        }
        Vright = cameraVup.crossProduct(cameraVleft).normalize();
        Vleft = cameraVleft.normalize();
        Vup = cameraVup.normalize();
        point = cameraPoint;
    }

    public Camera setViewPlaneSize(double width, double height) {
        Width = width;
        Height = height;
        return this;
    }

    public Camera setDistance(double distance) {
        Distance = distance;


        return this;
    }

    public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
        return null;
    }
}
