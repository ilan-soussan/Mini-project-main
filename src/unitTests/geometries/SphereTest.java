package unitTests.geometries;

import org.junit.jupiter.api.Test;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.*;

class SphereTest {

    @Test
    void getNormal() {
        Point3D center = new Point3D(0, 0, 0);
        Point3D p1 = new Point3D(4, 5, 6);
        Sphere sphere = new Sphere(center, p1);
        Vector normal = new Vector(p1.getX() - center.getX(), p1.getY() - center.getY(), p1.getZ() - center.getZ());
        assertEquals(sphere.getNormal(p1), normal.normalize(), "ERROR: getNormal() function does not work");
    }

    @Test
    public void findIntsersections() {
        Sphere sphere = new Sphere(1d, new Point3D(1, 0, 0));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Vector(-1, -4, 0),
                new Point3D(-1, 0, 0))), "Ray's line out of sphere");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(0.06515307716504659, 0.35505102572168223, 0);
        Point3D p2 = new Point3D(1.5348469228349528, 0.8449489742783177, 0);
        List<Point3D> result = sphere.findIntsersections(new Ray(new Vector(3, 1, 0),
                new Point3D(-1, 0, 0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p3 = new Point3D(2,0,0);
        result = sphere.findIntsersections(new Ray(new Vector(2.5,0.0,0.0),new Point3D(0.5,0,0)));
        assertEquals(1,result.size(),"Wrong number of points");
        assertEquals(List.of(p3), result);
        // TC04: Ray starts after the sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Vector(1,1,1),new Point3D(5,0,6))), "Ray's starts after sphere");
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point3D p4 = new Point3D(1.8,0.0,0.6);
        result = sphere.findIntsersections(new Ray(new Vector(2,0,-1),new Point3D(1,0,1)));
        assertEquals(1,result.size());
        assertEquals(List.of(p4), result,"The ray doesn't start in the end of sphere");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Vector(-2,0,0),new Point3D(0,0,0))),
                "Ray's doesn't start at sphere and go out of sphere");
        // **** Group: Ray's line goes through the center
        // TC13: Ray starts before the sphere (2 points)
        Point3D p5 = new Point3D(0,0,0);
        Point3D p6 = new Point3D(2,0,0);
        result = sphere.findIntsersections(new Ray(new Vector(4,0,0),new Point3D(-1,0,0)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p5, p6), result);
        // TC14: Ray starts at sphere and goes inside (1 points)
        Point3D p7 = new Point3D(1,0,-1);
        result = sphere.findIntsersections(new Ray(new Vector(0,0,-1),new Point3D(1,0,1)));
        assertEquals(1,result.size());
        assertEquals(List.of(p7), result,"The ray doesn't start in the end of sphere");
        // TC15: Ray starts inside (1 points)
        result = sphere.findIntsersections(new Ray(new Vector(0.0,0.0,-0.5),new Point3D(1,0,0.5)));
        assertEquals(1,result.size());
        assertEquals(List.of(p7), result,"The ray doesn't start in the sphere");
        // TC16: Ray starts at the center (1 points)
        result = sphere.findIntsersections(new Ray(new Vector(0,0,-1),new Point3D(1,0,0)));
        assertEquals(1,result.size());
        assertEquals(List.of(p7), result,"The ray doesn't start in the center of sphere");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Vector(3,0,0),new Point3D(2,0,0))),
                "The ray doesn't start at the sphere and go out");
        // TC18: Ray starts after sphere (0 points)
        assertNull(sphere.findIntsersections(new Ray(new Vector(3,0,0),new Point3D(3,0,0))),
                "The ray doesn't after the end of sphere");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Vector(1,0,0),new Point3D(0,0,1))),
                "The ray doesn't before the tangent of sphere");
        // TC20: Ray starts at the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Vector(2,0,0),new Point3D(1,0,1))),
                "The ray doesn't start at the tangent of sphere");
        // TC21: Ray starts after the tangent point
        assertNull(sphere.findIntsersections(new Ray(new Vector(1.5,0.0,0.0),new Point3D(2,0,1))),
                "The ray doesn't after the tangent of sphere");
        // **** Group: Special cases
        // TC22: Ray's line is outside, ray is orthogonal to ray start to sphere's center line
        assertNull(sphere.findIntsersections(new Ray(new Vector(0,0,1),new Point3D(3,0,0))),
                "The ray doesn't after the tangent of sphere");
    }
}