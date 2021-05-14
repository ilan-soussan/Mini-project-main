package unitTests;

import org.junit.jupiter.api.Test;
import geometries.Sphere;
import primitives.Point3D;
import primitives.Vector;
import static org.junit.jupiter.api.Assertions.*;

class SphereTest {

    @Test
    void getNormal() {
        Point3D center = new Point3D(0,0,0);
        Point3D p1 = new Point3D(4,5,6);
        Sphere sphere = new Sphere(center,p1);
        Vector normal = new Vector(p1.getX()-center.getX(),p1.getY()-center.getY(), p1.getZ()-center.getZ());
        assertEquals(sphere.getNormal(p1),normal.normalize(),"ERROR: getNormal() function does not work");
    }
}