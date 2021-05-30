package primitives;

import java.util.List;

public class Ray {
    Point3D p0;
    Vector dir;
    public Ray(Vector vector,Point3D point)
    {
        dir =vector.normalize();
        p0 = point;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Ray)) return false;
        Ray other = (Ray)obj;
        return this.dir.equals(other.dir) && this.p0.equals(other.p0);
    }
    public Point3D getRayPoint() {
        return p0;
    }

    public Vector getRayDir() {
        return dir;
    }

    public  Point3D getPoint(double t)
    {
        Point3D p = new Point3D(p0.getX()+t*dir.getHead().getX(),p0.getY()+t*dir.getHead().getY(),p0.getZ()+t*dir.getHead().getZ());
        return p;
    }

    public Point3D findClosestPoint(List<Point3D> list)
    {
        if (list.isEmpty())
            return null;
        double dist = p0.distance(list.get(0));
        Point3D closestPoint = list.get(0);
        for (Point3D p:list){
            if(p0.distance(p)<dist) {
                dist = p0.distance(p);
                closestPoint = p;
            }
        }
        return closestPoint;

    }
}
