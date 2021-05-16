package primitives;

public class Point3D {
    private double X;
    private double Y;
    private double Z;
    public static final Point3D ZERO =new Point3D(0,0,0);
    public Point3D(double x,double y,double z)
    {
        X=x;
        Y=y;
        Z=z;
    }
    public Point3D(int x,int y,int z)
    {
        X=x;
        Y=y;
        Z=z;
    }
    public Point3D add(Vector vector)
    {
        Point3D temp = new Point3D(X+vector.head.X,Y+vector.head.Y,Z+vector.head.Z);
        return  temp;
    }
    public Vector subtract(Point3D point)
    {
        Point3D tempPoint = new Point3D(X-point.X,Y-point.Y,Z- point.Z);
        Vector tempVector = new Vector((tempPoint));
        return tempVector;
    }
    public double distanceSquared(Point3D point)
    {
        double tempX = X-point.X;
        double tempY = Y-point.Y;
        double tempZ = Z-point.Z;

        tempX = tempX*tempX;
        tempY = tempY*tempY;
        tempZ = tempZ*tempZ;
        return (tempX+tempY+tempZ);
    }
    public boolean samePoiontRasio(Point3D point)
    {
        double RasioX = this.getX()/point.getX();
        double RasioY = this.getY()/point.getY();
        double RasioZ = this.getZ()/point.getZ();
        if(RasioX == RasioY && RasioX == RasioZ)
            return true;
        return false;
    }
    public double distance(Point3D point)
    {
        return Math.sqrt(distanceSquared(point));
    }
    public double getX() {
        return X;
    }
    public double getY() {
        return Y;
    }
    public double getZ() {
        return Z;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Point3D)) return false;
        Point3D other = (Point3D)obj;
        return (this.X == other.X && this.Y == other.Y &&this.Z == other.Z) ;
    }

}
