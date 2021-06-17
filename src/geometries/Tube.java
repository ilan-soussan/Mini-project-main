package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * we didnt used tube in the project
 *
 */

public class Tube extends Geometry {
    Ray axisRay;
    double radius;
    Box box;


    public Tube(double Radius, Ray ray) {
        radius = Radius;
        axisRay = ray;
    }

    public Tube(double Radius, Point3D point, Vector V) {
        radius = Radius;
        axisRay = new Ray(V, point);
    }

    public double getRadius() {
        return radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point3D point) {
        double t = (axisRay.getRayDir().scale(point.distance(axisRay.getRayPoint()))).length();
        Point3D O = axisRay.getRayPoint().add(axisRay.getRayDir().scale(t));
        Vector V = point.subtract(O);
        return V.normalized();
    }

    @Override
    public Box getBox() {
        return null;
    }

    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        double a = 0;
        double b = 0;
        double c = 0;
        if (ray.getRayPoint().equals(axisRay.getRayPoint())) {
            if (ray.getRayDir().equals(axisRay.getRayDir())) return null;
            else {
                if (Util.isZero(ray.getRayDir().dotProduct(axisRay.getRayDir())))
                    return new ArrayList<GeoPoint>(Arrays.asList(new GeoPoint(this, ray.getPoint(radius))));
                else
                    return new ArrayList<GeoPoint>(Arrays.asList(new GeoPoint(this, ray.getPoint(radius / Math.sqrt((ray.getRayDir().subtract(axisRay.getRayDir().scale
                            (ray.getRayDir().dotProduct(axisRay.getRayDir()) / axisRay.getRayDir().lengthSquared()))).lengthSquared())))));
            }
        }
        if (Util.isZero(ray.getRayDir().dotProduct(axisRay.getRayDir()))) {
            a = ray.getRayDir().lengthSquared();
            if (Util.isZero(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(axisRay.getRayDir()))) {
                b = 2 * ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(ray.getRayDir());
                c = ray.getRayPoint().subtract(axisRay.getRayPoint()).lengthSquared() - radius * radius;
            } else {
                b = 2 * ray.getRayDir().dotProduct(ray.getRayPoint().subtract(axisRay.getRayPoint()).subtract(this.axisRay.getRayDir().scale(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(this.axisRay.getRayDir()) / this.axisRay.getRayDir().lengthSquared())));
                c = ray.getRayPoint().subtract(axisRay.getRayPoint()).subtract(this.axisRay.getRayDir().scale(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(this.axisRay.getRayDir()) / this.axisRay.getRayDir().lengthSquared())).lengthSquared() - radius * radius;
            }
        } else if (Util.isZero(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(axisRay.getRayDir()))) {
            try {
                a = ray.getRayDir().subtract(this.axisRay.getRayDir().scale(ray.getRayDir().dotProduct(this.axisRay.getRayDir()) / this.axisRay.getRayDir().lengthSquared())).lengthSquared();

            } catch (IllegalArgumentException e) {
                return null;
            }
            b = 2 * ray.getRayDir().subtract(this.axisRay.getRayDir().scale(ray.getRayDir().dotProduct(this.axisRay.getRayDir()) / this.axisRay.getRayDir().lengthSquared())).dotProduct(ray.getRayPoint().subtract(axisRay.getRayPoint()));
            c = -radius * radius + ray.getRayPoint().subtract(axisRay.getRayPoint()).lengthSquared();
        } else {
            Vector projectedRayVector = this.axisRay.getRayDir().scale(ray.getRayDir().dotProduct(this.axisRay.getRayDir()) / this.axisRay.getRayDir().lengthSquared());
            ;
            Vector pRaySubP0 = pRaySubP0 = ray.getRayPoint().subtract(axisRay.getRayPoint());
            Vector projectedDPVector = this.axisRay.getRayDir().scale(pRaySubP0.dotProduct(this.axisRay.getRayDir()) / this.axisRay.getRayDir().lengthSquared());
            try {
                a = ray.getRayDir().subtract(projectedRayVector).lengthSquared();
            } catch (IllegalArgumentException e) {
                a = 0;
            }
            try {
                b = 2 * ray.getRayDir().subtract(projectedRayVector).dotProduct(pRaySubP0.subtract(projectedDPVector));
            } catch (IllegalArgumentException e) {
                b = 0;
            }
            try {
                c = pRaySubP0.subtract(projectedDPVector).lengthSquared() - this.radius * radius;
            } catch (IllegalArgumentException e) {
                c = -this.radius * radius;
            }

        }
/*	double a=ray.getDir().getHead().getX()*ray.getDir().getHead().getX()+ray.getDir().getHead().getY()*ray.getDir().getHead().getY();
	double b=2*(ray.getDir().getHead().getX()*ray.getP0().getX()+ray.getDir().getHead().getY()*ray.getP0().getY());
	double c=ray.getP0().getY()*ray.getP0().getY()+ray.getP0().getX()*ray.getP0().getX()-this.radius;*/

        if (Util.isZero(a)) return null;
        if (Util.isZero(b * b - 4 * a * c)) {
            if (-b / (2 * a) < 0) return null;
            ArrayList<GeoPoint> result = new ArrayList<GeoPoint>();
            result.add(new GeoPoint(this, ray.getPoint(-b / (2 * a))));
            return result;
        } else if ((b * b - 4 * a * c) < 0) return null;
        else {
            double d = Math.sqrt(b * b - 4 * a * c);
            if ((-b - d) / (2 * a) > 0 && (-b + d) / (2 * a) > 0 && !Util.isZero((-b - d) / (2 * a))) {
                ArrayList<GeoPoint> result = new ArrayList<GeoPoint>();
                result.add(new GeoPoint(this, ray.getPoint((-b - d) / (2 * a))));
                result.add(new GeoPoint(this, ray.getPoint((-b + d) / (2 * a))));
                return result;
            } else if ((-b + d) / (2 * a) > 0 && !Util.isZero((-b + d) / (2 * a))) {
                ArrayList<GeoPoint> result = new ArrayList<GeoPoint>();
                result.add(new GeoPoint(this, ray.getPoint((-b + d) / (2 * a))));
                return result;
            } else return null;
        }
    }

}