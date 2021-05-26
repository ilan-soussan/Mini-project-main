package unitTests;

import geometries.Cyilnder;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class CyilnderTest {

    @Test
    void getNormal() {
        Cyilnder C= new Cyilnder(2,3,new Point3D(1,2,3),new Vector(2,3,4));
        assertEquals(C.getNormal(new Point3D(1,2,3)),new Vector(2,3,4).normalize(),"getNormale func in Cyilnder error");
    }

}