package primitives;

public class Vector {
    public Vector(Point3D head)
    {
        this.head = head;
    }
    public Vector(Double X,Double Y,Double Z)
    {
        Point3D tempPoint = new Point3D((double)X,(double)Y,(double)Z);
        if(tempPoint.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector equals to zero");
        head = tempPoint;
    }
    public Vector(int X,int Y,int Z)
    {

        Point3D tempPoint = new Point3D(X,Y,Z);
        if(tempPoint.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector equals to zero");
        head = tempPoint;
    }
    public Vector(Coordinate X,Coordinate Y,Coordinate Z)
    {
        Point3D tempPoint = new Point3D(X.coord,Y.coord,Z.coord);
        if(tempPoint.equals(Point3D.ZERO))
            throw new IllegalArgumentException("Vector equals to zero");
        head = tempPoint;
    }
    Point3D head;

    public Vector subtract(Vector v)
    {
        Vector tempVector = new Vector(head.getX()-v.head.getX(), head.getY()-v.head.getY(), head.getZ()-v.head.getZ());
        return  tempVector;
    }
    public Vector add(Vector v)
    {
        Vector tempVector = new Vector(this.head.add(v));
        return  tempVector;
    }
    public Vector scale(double number)
    {
        Vector tempVector = new Vector(head.getX()*number, head.getY()*number, head.getZ()*number);
        return  tempVector;
    }
    public Double dotProduct(Vector v)
    {
        Double sum = Double.valueOf(0);
        sum+=v.head.getX()* head.getX();
        sum+=v.head.getY()* head.getY();
        sum+=v.head.getZ()* head.getZ();
        return  sum;
    }
    public Vector crossProduct(Vector v)
    {
        double X = Determinant(head.getY(), head.getZ(),v.head.getZ(),v.head.getY());
        double Y = Determinant(head.getX(), head.getZ(),v.head.getZ(),v.head.getX());
        Y=(Y*-1);
        double Z = Determinant(head.getX(), head.getY(),v.head.getY(),v.head.getX());
        Vector tempVector = new Vector(X,Y,Z);
        return tempVector;
    }
    public double Determinant(double A,double B,double C,double D)
    {
        return ((A*C)-(B*D));
    }

    public double Determinant3Vectors(Vector x,Vector y)
    {
        double a = this.head.getX()*((x.head.getY()*y.head.getZ())-(x.head.getZ()*y.head.getY()));
        double b = this.head.getY()*((x.head.getX()*y.head.getZ())-(x.head.getZ()*y.head.getX()));
        double c = this.head.getZ()*((x.head.getX()*y.head.getY())-(x.head.getY()*y.head.getX()));
        return  (a-b+c);
    }
    public Double lengthSquared()
    {
        double vectorLength = head.getX()* head.getX();
        vectorLength += head.getY()* head.getY();
        vectorLength += head.getZ()* head.getZ();
        return vectorLength;
    }
    public double length()
    {
        return Math.sqrt(lengthSquared());
    }
    public Vector normalize()
    {
        Double vectorLength= this.length();
        if(vectorLength==1)
            return this;
        double X = head.getX()/vectorLength;
        double Y = head.getY()/vectorLength;
        double Z = head.getZ()/vectorLength;
        Point3D tempPoint = new Point3D(X,Y,Z);
        head = tempPoint;
        return this;
    }
    public Vector normalized()
    {
        Point3D tempPoint =new Point3D(head.getX(), head.getY(), head.getZ()) ;
        Vector tempVector = new Vector(normalize().getHead());
        head =tempPoint;
        return tempVector;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Vector)) return false;
        Vector other = (Vector) obj;
        return this.head.equals(other.head);
    }
    public Point3D getHead()
    {
        return head;
    }
}
