package unitTests.geometries;

import geometries.Cylinder;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * didnt use this class in the project
 */
class CyilnderTest {

    @Test
    void getNormal() {
        Cylinder C= new Cylinder(2,3,new Point3D(1,2,3),new Vector(2,3,4));
        assertEquals(C.getNormal(new Point3D(1,2,3)),new Vector(2,3,4).normalize(),"getNormale func in Cyilnder error");
    }

}