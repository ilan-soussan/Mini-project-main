package unitTests;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Point3D p = new Point3D(3,6,1);
        Point3D p0 = new Point3D(0,2,5);

        Tube T = new Tube(2, p0,new Vector(2,5,6));
        Vector v = T.getNormal(p);
        Vector temp = new Vector(2,5,6).normalize();
        double t = temp.scale(p.distance(p0)).length();
        Point3D O =p0.add(temp.scale(t));
        Vector normal = p.subtract(O).normalized();
        assertEquals(v,normal,"ERROR: getNormal() function does not work on Tube");


    }

}