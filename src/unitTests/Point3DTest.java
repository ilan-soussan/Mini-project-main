package unitTests;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

class Point3DTest {

    @Test
    void add() {
        Point3D point = new Point3D(1,2,3);
        Vector vector = new Vector(4,5,6);
        Point3D test = new Point3D(5,7,9);
        assertTrue(test.equals(point.add(vector)),"ERROR: add() result isn't correct ");
    }


    @Test
    void subtract() {
        Point3D point1 = new Point3D(10,20,15);
        Point3D point2 = new Point3D(4,5,6);
        Vector test = new Vector(6,15,9);
        assertTrue(test.equals(point1.subtract(point2)),"ERROR: subtract() result isn't correct ");
    }

    @Test
    void distanceSquared() {
        Point3D point1 = new Point3D(10,7,9);
        Point3D point2 = new Point3D(8,5,6);
        double dist = point1.distanceSquared(point2);
        assertEquals(17,dist,"ERROR: distanceSquared() result isn't correct ");
    }

    @Test
    void distance() {
        Point3D point1 = new Point3D(10,10,9);
        Point3D point2 = new Point3D(10,5,9);
        double dist = point1.distance(point2);
        assertEquals(5,dist,"ERROR: distance() result isn't correct ");

    }
}