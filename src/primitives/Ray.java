package primitives;

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

}
