package elements;

import primitives.*;

public class Camera {
    private Point3D point;
    private Vector Vup;
    private Vector Vtowards;
    private Vector Vright;

    double Width;
    double Height;
    double Distance;

    public Vector getVtowards() {
        return Vtowards;
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

    public Camera(Point3D cameraPoint, Vector cameraVtowards, Vector cameraVup) {
        if (!Util.isZero(cameraVup.dotProduct(cameraVtowards))) {
            throw new IllegalArgumentException("Vectors not vertical to each other");
        }
        Vright = cameraVup.crossProduct(cameraVtowards).normalize();
        Vtowards = cameraVtowards.normalize();
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
        Point3D Pc = point.add(Vtowards.scale(Distance));

        double Ry = Height/nY;
        double Rx = Width/nX;

        double Xj = ((j-(nX-1)/2)*Rx)*-1;
        double Yi = ((i-(nY-1)/2)*Ry);
/*
        if(Util.isZero(Rx*(j-(double)(nX-1)/2))&&Util.isZero(-Ry*(i-(double)(nY-1)/2))) return new Ray(Pc.subtract(point),point);
        else if(Util.isZero(Rx*(j-(double)(nX-1)/2))) return new Ray(Pc.add(Vup.scale(-Ry*(i-(double)(nY-1)/2))).subtract(point),point);
        else if(Util.isZero(-Ry*(i-(double)(nY-1)/2))) return new Ray(Pc.add(Vright.scale(Rx*(j-(double)(nX-1)/2))).subtract(point),point);
        else return new Ray(Pc.add(Vright.scale(Rx*(j-(double)(nX-1)/2))).add(Vup.scale(-Ry*(i-(double)(nY-1)/2))).subtract(point),point);
        */


// j-((nX-1)/2))                 //i-((nY-1)/2))
//        Point3D Pij = Pc.add(Vright.scale(Xj).add(Vup.scale(Yi)));
        Point3D Pij = Pc.add(Vright.scale((Xj)).subtract(Vup.scale((Yi))));
        Vector Vij = Pij.subtract(point);


        return new Ray(Vij,point);
    }
}
