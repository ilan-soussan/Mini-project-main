package unitTests;

import geometries.triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class triangleTest {

    @Test
    void findIntsersections() {
        triangle T = new triangle(new Point3D(0,0,1),new Point3D(0,5,5),new Point3D(0,3,1));
        assertNull(T.findIntsersections(new Ray(new Vector(1,2,3),new Point3D(5,2,3))),"Ray's line out of sphere");

        T = new triangle(new Point3D(4.02,0,0),new Point3D(0,3,0),new Point3D(0,0,3));
        //assertEquals(T.findIntsersections(new Ray(new Vector(1.12,-1.02,3.29),new Point3D(0,1,0))));



       // assertEquals(T.findIntsersections(new Ray(new Vector(0,-1,3),new Point3D(0,1,0))),new Point3D(0,0,3));///נקודה בפינה

      //  assertEquals(T.findIntsersections(new Ray(new Vector(0,-1,3),new Point3D(0,1.31,0.69))),new Point3D(0,2.31,0.69));//נקודה בקצה

    //    assertNull(T.findIntsersections(new Ray(new Vector(0,-1,3),new Point3D(0,-2,-1))),);//בהמשך הצלע

    }
}