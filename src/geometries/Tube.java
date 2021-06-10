package geometries;

import primitives.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Tube extends Geometry{
    Ray axisRay;
    double radius;


    public Tube(double Radius, Ray ray)
    {
        radius = Radius;
        axisRay = ray;
    }
    public Tube(double Radius, Point3D point,Vector V)
    {
        radius = Radius;
        axisRay = new Ray(V,point);
    }
    public double getRadius() {
        return radius;
    }

    public Ray getAxisRay() {
        return axisRay;
    }

    @Override
    public Vector getNormal(Point3D point)
    {
        double t = (axisRay.getRayDir().scale(point.distance(axisRay.getRayPoint()))).length();
        Point3D O = axisRay.getRayPoint().add(axisRay.getRayDir().scale(t));
        Vector V = point.subtract(O);
        return V.normalized();
    }

   /* @Override
    public List<Point3D> findIntsersections(Ray ray) {return  null;}*/

    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        double a=0;
        double b=0;
        double c=0;
        if(ray.getRayPoint().equals(axisRay.getRayPoint()))
        {
            if(ray.getRayDir().equals(axisRay.getRayDir()))return null;
            else
            {
                if(Util.isZero(ray.getRayDir().dotProduct(axisRay.getRayDir())))return new ArrayList<GeoPoint>(Arrays.asList(new GeoPoint(this,ray.getPoint(radius))));
                else return new ArrayList<GeoPoint>(Arrays.asList(new GeoPoint(this,ray.getPoint(radius/Math.sqrt((ray.getRayDir().subtract(axisRay.getRayDir().scale
                        (ray.getRayDir().dotProduct(axisRay.getRayDir())/axisRay.getRayDir().lengthSquared()))).lengthSquared())))));
            }
        }
        if(Util.isZero(ray.getRayDir().dotProduct(axisRay.getRayDir())))
        {
            a=ray.getRayDir().lengthSquared();
            if(Util.isZero(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(axisRay.getRayDir())))
            {
                b=2*ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(ray.getRayDir());
                c=ray.getRayPoint().subtract(axisRay.getRayPoint()).lengthSquared()-radius*radius;
            }
            else
            {
                b=2*ray.getRayDir().dotProduct(ray.getRayPoint().subtract(axisRay.getRayPoint()).subtract(this.axisRay.getRayDir().scale(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(this.axisRay.getRayDir())/this.axisRay.getRayDir().lengthSquared())));
                c=ray.getRayPoint().subtract(axisRay.getRayPoint()).subtract(this.axisRay.getRayDir().scale(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(this.axisRay.getRayDir())/this.axisRay.getRayDir().lengthSquared())).lengthSquared()-radius*radius;
            }
        }
        else if(Util.isZero(ray.getRayPoint().subtract(axisRay.getRayPoint()).dotProduct(axisRay.getRayDir())))
        {
            try {
                a=ray.getRayDir().subtract(this.axisRay.getRayDir().scale(ray.getRayDir().dotProduct(this.axisRay.getRayDir())/this.axisRay.getRayDir().lengthSquared())).lengthSquared();

            } catch (IllegalArgumentException e) {
                return null;
            }
            b= 2*ray.getRayDir().subtract(this.axisRay.getRayDir().scale(ray.getRayDir().dotProduct(this.axisRay.getRayDir())/this.axisRay.getRayDir().lengthSquared())).dotProduct(ray.getRayPoint().subtract(axisRay.getRayPoint()));
            c=-radius*radius+ray.getRayPoint().subtract(axisRay.getRayPoint()).lengthSquared();
        }
        else{
            Vector projectedRayVector=this.axisRay.getRayDir().scale(ray.getRayDir().dotProduct(this.axisRay.getRayDir())/this.axisRay.getRayDir().lengthSquared());;
            Vector pRaySubP0=pRaySubP0=ray.getRayPoint().subtract(axisRay.getRayPoint());
            Vector projectedDPVector=this.axisRay.getRayDir().scale(pRaySubP0.dotProduct(this.axisRay.getRayDir())/this.axisRay.getRayDir().lengthSquared());
            try {
                a=ray.getRayDir().subtract(projectedRayVector).lengthSquared();
            } catch (IllegalArgumentException e) {
                a=0;
            }
            try {
                b=2*ray.getRayDir().subtract(projectedRayVector).dotProduct(pRaySubP0.subtract(projectedDPVector));
            } catch (IllegalArgumentException e) {
                b=0;
            }
            try {
                c=pRaySubP0.subtract(projectedDPVector).lengthSquared()-this.radius*radius;
            } catch (IllegalArgumentException e) {
                c=-this.radius*radius;
            }

        }
/*	double a=ray.getDir().getHead().getX()*ray.getDir().getHead().getX()+ray.getDir().getHead().getY()*ray.getDir().getHead().getY();
	double b=2*(ray.getDir().getHead().getX()*ray.getP0().getX()+ray.getDir().getHead().getY()*ray.getP0().getY());
	double c=ray.getP0().getY()*ray.getP0().getY()+ray.getP0().getX()*ray.getP0().getX()-this.radius;*/

        if(Util.isZero(a))return null;
        if(Util.isZero(b*b-4*a*c)) {
            if(-b/(2*a)<0)return null;
            ArrayList<GeoPoint> result=new ArrayList<GeoPoint> ();
            result.add(new GeoPoint(this,ray.getPoint(-b/(2*a))));
            return result;
        }
        else if((b*b-4*a*c)<0) return null;
        else
        {
            double d=Math.sqrt(b*b-4*a*c);
            if((-b-d)/(2*a)>0&&(-b+d)/(2*a)>0  && ! Util.isZero((-b-d)/(2*a)))
            {
                ArrayList<GeoPoint> result=new ArrayList<GeoPoint> ();
                result.add(new GeoPoint(this,ray.getPoint((-b-d)/(2*a))));
                result.add(new GeoPoint(this,ray.getPoint((-b+d)/(2*a))));
                return result;
            }
            else if((-b+d)/(2*a)>0 && ! Util.isZero((-b+d)/(2*a)))
            {
                ArrayList<GeoPoint> result=new ArrayList<GeoPoint> ();
                result.add(new GeoPoint(this,ray.getPoint((-b+d)/(2*a))));
                return result;
            }
            else return null;
        }
    }

           /* // R(t) = o + td
            // x² + z² = r²
            // (ox+tdx)² + (oz+tdz)² = r²
            // (ox)² + 2oxtdx + (tdx)² + (oz)² + 2oztdz + (tdz)² = r²
            // t²(dx + dz) + 2t(oxdx + ozdz) + (ox)² + (oz)² - r² = 0
            // a=(dx + dz); b = 2(oxdx + ozdz); c = (ox)² + (oz)² - r²


            double a = ray.getRayDir().getHead().getX()*ray.getRayDir().getHead().getX() + ray.getRayDir().getHead().getZ()*ray.getRayDir().getHead().getZ();
            double b = 2*(ray.getPoint(0).getX()*ray.getRayDir().getHead().getX() + ray.getPoint(0).getZ()*ray.getRayDir().getHead().getZ());
            double c = ray.getPoint(0).getX()*ray.getPoint(0).getX() + ray.getPoint(0).getZ()*ray.getPoint(0).getZ() - radius*radius;

            double discr = b*b - 4*a*c;
            if (discr < 0)
            {
                return null;
            }

            double x1 = (-b+Math.sqrt(discr))/(2*a);
            double x2 = (-b-Math.sqrt(discr))/(2*a);

            float t;

            //choose the smallest and >=0 t
            if (x1 > x2)
            {
                t=x2;
            }

            if (t < 0)
            {
                t=x1;
            }


            // if both solution are <0 => NO INTERSECTION!
            if (t<0)
            {
                return null;
            }

            // normal calculation
            // f(x,y) = x² + z² - r² = 0
            // T = (dx/dt, y, dz/dt)
            // 0 = df/dt = (df/dx, y, df/dz) · T
            // N = (2x, 0, 2z)
            vec3 point = ray.getOrigin() + ray.getDirection()*t;
            vec3 normal = vec3(2*point.x, 0.0f, 2*point.z);


            // If the y-component from point computed is smaller than 0 or bigger than height => NO INTERSECTION!
            if (point.y < 0 || point.y > m_height)
            {
                return Intersection(false);
            }

            //If ray direction is not pararel to Y Plane
            if (ray.getDirection().y != 0.0f) //Paralel
            {
                //Compute t's for point intersection in the Y Plane
                float t3 = (0-ray.getOrigin().y)/ray.getDirection().y;
                float t4 = (m_height-ray.getOrigin().y)/ray.getDirection().y;
                float t2;

                //choose the smallest and >=0 t
                t2 = std::min(t3,t4);
                if (t2 < 0)
                {
                    t2 = std::max(t3,t4);
                }
                if (t2 >= 0)
                {
                    // If there is a t >= 0 compute de point and check if the point is inside the cap
                    vec3 point1 = ray.getOrigin() + ray.getDirection()*t2;
                    std::cout << "point " << point1.y << " hipo "  << point1.x*point1.x + point1.z*point1.z << " radio " << m_radius*m_radius << std::endl;
                    if (point1.x*point1.x + point1.z*point1.z <= m_radius*m_radius+0.9f)
                    {
                        // Intersection point is inside cap but, Which t is the smallest? t from cap or t from body cylinder?
                        // I choose the smallest t and check if the t is from cap and compute normal and return intersection.
                        t = std::min(t,t2);
                        if (t == t3)
                        {
                            normal = vec3(0.0f,-1.0f,0.0f);
                            return Intersection(true, point1, normal);
                        }
                        else if (t == t4)
                        {
                            normal = vec3(0.0f,1.0f,0.0f);
                            return Intersection(true, point1, normal);
                        }
                    }
                }
            }

            // Intersection in the body cylinder, compute the point and return the intersection
            point = ray.getOrigin() + ray.getDirection()*t;

            return Intersection(true, point, normal);
        }*/
    }
