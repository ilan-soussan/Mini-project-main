package unitTests;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Point3D p = new Point3D(0,1,1);
        Point3D p0 = new Point3D(0,0,1);

        Tube T = new Tube(2, p0,new Vector(0,1,0));
        Vector v = T.getNormal(p);
        double A = v.length();
        Vector normal = new Vector(1,1,0);
        assertEquals(v,normal,"ERROR: getNormal() function does not work on Tube");


    }

}