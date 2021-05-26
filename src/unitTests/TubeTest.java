package unitTests;

import geometries.Tube;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TubeTest {

    @Test
    void getNormal() {
        Point3D p = new Point3D(0, 1, 2);
        Point3D p0 = new Point3D(0, 0, 1);

        Tube T = new Tube(2, p0, new Vector(0, 1, 0));
        Vector v = T.getNormal(p);
        double A = v.length();
        Vector normal = new Vector(0.0, -0.41421356237309515, 1.0).normalize();
        assertEquals(v, normal, "ERROR: getNormal() function does not work on Tube");


    }

    @Test
    public void testFindIntersections() {
        Tube tube = new Tube(1, new Ray(new Vector(0, 0, 7), new Point3D(1, 0, -2)));

        // ============ Equivalence Partitions Tests ==============

        // TC01: Ray's line is outside the sphere (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(-2, 3, 0),
                new Point3D(5, 0, 0))), "Ray's line out of tube");

        // TC02: Ray starts before and crosses the sphere (2 points)
        Point3D p1 = new Point3D(1.98, 0.2, 2d);
        Point3D p2 = new Point3D(1.31, -0.95, 2.42);
        List<Point3D> result = tube.findIntsersections(new Ray(new Vector(-1.61, -2.27, 1.02),
                new Point3D(2.56, 1.2, 1.63)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p1, p2), result);

        // TC03: Ray starts inside the sphere (1 point)
        Point3D p3 =  new Point3D(1.31, -0.95, 2.42);
        result = tube.findIntsersections(new Ray(new Vector(-0.8, 1.38, 0.51), new Point3D(1.76, -0.18, 2.14)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p3), result);
        // TC04: Ray starts after the sphere (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(-0.76, -1.31, 0.48), new Point3D(0.95, -1.57, 2.65))), "Ray's starts after tube");
        // =============== Boundary Values Tests ==================

        // **** Group: Ray's line crosses the sphere (but not the center)
        // TC11: Ray starts at sphere and goes inside (1 points)
        Point3D p4 = new Point3D(1.31, -0.95, 2.42);
        result = tube.findIntsersections(new Ray(new Vector(-0.67, -1.15, 0.42),
                new Point3D(1.98, 0.2, 2)));
        assertEquals(1, result.size());
        assertEquals(List.of(p4), result, "The ray doesn't start in the end of tube");
        // TC12: Ray starts at sphere and goes outside (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(0.58, 1d, -0.37), new Point3D(1.98, 0.2, 2))),
                "Ray's doesn't start at sphere and go out of sphere");
        // **** Group: Ray's line goes through the axs and orthogonal
        // TC13: Ray starts before the sphere (2 points)
        Point3D p5 = new Point3D(2, 0, 1);
        Point3D p6 = new Point3D(0, 0, 1);
        result = tube.findIntsersections(new Ray(new Vector(-6, 0, 0),
                new Point3D(3, 0, 1)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p5, p6), result);
        // TC14: Ray starts at sphere and goes inside (1 points)
        result = tube.findIntsersections(new Ray(new Vector(-5, 0, 0), new Point3D(2, 0, 1)));
        assertEquals(1, result.size());
        assertEquals(List.of(p6), result, "The ray doesn't start on tube");
        // TC15: Ray starts on the ax (1 points)
        result = tube.findIntsersections(new Ray(new Vector(-1, 0, 0), new Point3D(1, 0, 1)));
        assertEquals(1, result.size());
        assertEquals(List.of(p6), result, "The ray doesn't start in the sphere");
        // TC16: Ray starts inside (1 points)
        result = tube.findIntsersections(new Ray(new Vector(-3.45, 0d, 0d), new Point3D(1.45, 0d, 1d)));
        assertEquals(1, result.size());
        assertEquals(List.of(p6), result, "The ray doesn't start in the center of sphere");
        // TC17: Ray starts at sphere and goes outside (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(1, 0, 0), new Point3D(2, 0, 1))),
                "Ray's doesn't start at tube and go out of tube");
        // TC18: Ray starts after sphere (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(-1, 0, 0), new Point3D(-2, 0, 1))), "Ray's starts after tube");
        // **** Group: Ray's line is tangent to the sphere (all tests 0 points)
        // TC19: Ray starts before the tangent point
        assertNull(tube.findIntsersections(new Ray(new Vector(0d, 1.8, 0d), new Point3D(1.98, 2, 2))),
                "The ray doesn't before the tangent of tube");
        // TC20: Ray starts at the tangent point
        assertNull(tube.findIntsersections(new Ray(new Vector(0d, 1.96, 0d), new Point3D(1.98, 0.2, 2))),
                "The ray doesn't start at the tangent of tube");
        // TC21: Ray starts after the tangent point
        assertNull(tube.findIntsersections(new Ray(new Vector(0d, -0.52, 0d), new Point3D(1.98, -1.24, 2))),
                "The ray doesn't after the tangent of sphere");
        // **** Group: Special cases
        // TC22: Rays parallel to the tube(outside)
        assertNull(tube.findIntsersections(new Ray(new Vector(0d, 0d, 2.66), new Point3D(1.98, 2d, 2d))),
                "The ray isn't parallel to tube");
        // TC23: Rays parallel to the tube(inside)
        assertNull(tube.findIntsersections(new Ray(new Vector(0d, 0d, 2.44), new Point3D(1.45, 0d, 1d))),
                "The ray isn't parallel to tube");
        // TC23: Rays parallel to the tube(inside)
        assertNull(tube.findIntsersections(new Ray(new Vector(0d, 0d, 3.68), new Point3D(1.98, 0.2, 2d))),
                "isn't parallel to tube");
        // **** Group: Ray's line goes through the axs
        // TC24: Ray starts before the sphere (2 points)
        Point3D p7 = new Point3D(2, 0, 1.47);
        Point3D p8 = new Point3D(1, 0, 2.44);
        result = tube.findIntsersections(new Ray(new Vector(-2.63, 0d, 2.56),
                new Point3D(2.63, 0, 0.85)));
        assertEquals(2, result.size(), "Wrong number of points");
        if (result.get(0).getX() > result.get(1).getX())
            result = List.of(result.get(1), result.get(0));
        assertEquals(List.of(p7, p8), result);
        // TC25: Ray starts at sphere and goes inside (1 points)
        result = tube.findIntsersections(new Ray(new Vector(-2.81, 0d, 2.73), new Point3D(2, 0, 1.47)));
        assertEquals(1, result.size());
        assertEquals(List.of(p6), result, "The ray doesn't start on tube");
        // TC26: Ray starts on the ax (1 points)
        result = tube.findIntsersections(new Ray(new Vector(-1.81, 0d, 1.76), new Point3D(1, 0, 2.41)));
        assertEquals(1, result.size());
        assertEquals(List.of(p6), result, "The ray doesn't start in the sphere");
        // TC27: Ray starts inside (1 points)
        result = tube.findIntsersections(new Ray(new Vector(-1.27, 0d, 1.24), new Point3D(0.47, 0d, 2.96)));
        assertEquals(1, result.size());
        assertEquals(List.of(p6), result, "The ray doesn't start in the center of sphere");
        // TC28: Ray starts at tube and goes outside (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(-0.81, 0d, 0.78), new Point3D(0, 0, 3.41))),
                "Ray's doesn't start at tube and go out of tube");
        // TC29: Ray starts after tube (0 points)
        assertNull(tube.findIntsersections(new Ray(new Vector(-0.25, 0d, 0.24), new Point3D(-0.56, 0d, 3.95))), "Ray's starts after tube");

    }
}