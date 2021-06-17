package unitTests.geometries;

import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class triangleTest {

    @Test
    void findIntsersections() {


        //EP Outside
        Triangle T = new Triangle(new Point3D(0,0,1),new Point3D(0,5,5),new Point3D(0,3,1));
        assertNull(T.findIntsersections(new Ray(new Vector(1,2,3),new Point3D(5,2,3))),"Fail to detect error when the ray is out the triangle");

        //EP Inside triangle
        T = new Triangle(new Point3D(4.02,0,0),new Point3D(0,3,0),new Point3D(0,0,3));
        assertEquals(new Point3D(1.0481227651966627,1.3793206197854588,0.8384982121573301),T.findIntsersections(new Ray(new Vector(1.05,0.38,0.84),new Point3D(0,1,0))).get(0),"Fails while the beam is cut with the triangle");

        //BVA On edge's continuation
        assertNull(T.findIntsersections(new Ray(new Vector(0,-1,3),new Point3D(0,1,0))),"");///נקודה בפינה
        // BVA In vertex
        assertNull(T.findIntsersections(new Ray(new Vector(0,-1,3),new Point3D(0,1.31,0.69))),"");//נקודה בקצה
        //BVA On edge
        assertNull(T.findIntsersections(new Ray(new Vector(0.0,2.84,-0.84),new Point3D(0,-2,-1))));//בהמשך הצלע

    }
}