package unitTests;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(4,-2,0);
        Point3D p3 = new Point3D(3,7,11);
        Plane plane = new Plane(p1,p2,p3);
        Vector p1p2 = new Vector(p2.getX()- p1.getX(), p2.getY()- p1.getY(), p2.getZ()- p1.getZ());
        Vector p1p3 = new Vector(p3.getX()- p1.getX(), p3.getY()- p1.getY(), p3.getZ()- p1.getZ());
        Vector normal = p1p2.crossProduct(p1p3);
        normal.normalize();
        if(plane.getNormal()==normal)
            out.println("ERROR: length() wrong value");
        if(plane.getNormal()==normal.flip())
            out.println("ERROR: length() wrong value");
//        assertTrue(plane.getNormal()==normal || plane.getNormal()==normal.flip(),"ERROR: getNormal() result isn't correct" );
    }
}