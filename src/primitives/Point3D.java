package primitives;



/**
 * Point3D class.
 * Represented by 3 values for x, y, z
 *
 *
 *
 * @author Ilan and didi
 */
public class Point3D {

    private double X;
    private double Y;
    private double Z;


    public static final Point3D ZERO =new Point3D(0,0,0); //point Zero

    /**
     * constructor for point3D that gets 3 double value
     * @param x
     * @param y
     * @param z
     */
    public Point3D(double x,double y,double z)
    {
        X=x;
        Y=y;
        Z=z;
    }
    /**
     * constructor for point3D that gets 3 int value
     * @param x
     * @param y
     * @param z
     */
    public Point3D(int x,int y,int z)
    {
        X=x;
        Y=y;
        Z=z;
    }

    /**
     * add Vector to point and return new point in the new place.
     * @param vector
     * @return Point3D
     */
    public Point3D add(Vector vector)
    {
        Point3D temp = new Point3D(X+vector.head.X,Y+vector.head.Y,Z+vector.head.Z);
        return  temp;
    }

    /**
     * subtract point from another one and return new vector
     * @param point
     * @return vector
     */
    public Vector subtract(Point3D point)
    {
        Point3D tempPoint = new Point3D(X-point.X,Y-point.Y,Z- point.Z);
        Vector tempVector = new Vector((tempPoint));
        return tempVector;
    }

    /**
     * distance Squared between two points
     * @param point
     * @return (double)distance Squared
     */
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

    /**
     * boolean func that return true if the ration between the x,y,z of the points is equals
     * @param point
     * @return true or false
     */
    public boolean samePointRasio(Point3D point)
    {
        double RasioX = this.getX()/point.getX();
        double RasioY = this.getY()/point.getY();
        double RasioZ = this.getZ()/point.getZ();
        if(RasioX == RasioY && RasioX == RasioZ)
            return true;
        return false;
    }

    /**
     * distance  between two  points
     * @param point
     * @return (double) distance
     */
    public double distance(Point3D point)
    {
        return Math.sqrt(distanceSquared(point));
    }

    //getrs
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
