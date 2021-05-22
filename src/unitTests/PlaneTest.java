package unitTests;

import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PlaneTest {

    @Test
    void getNormal() {
        Point3D p1 = new Point3D(1,2,3);
        Point3D p2 = new Point3D(7,4,-6);
        Point3D p3 = new Point3D(3,7,11);
        Plane plane = new Plane(p1,p2,p3);
        Vector p1p2 = new Vector(p2.getX()- p1.getX(), p2.getY()- p1.getY(), p2.getZ()- p1.getZ());
        Vector p1p3 = new Vector(p3.getX()- p1.getX(), p3.getY()- p1.getY(), p3.getZ()- p1.getZ());
        //==========same point3D============
        assertFalse(p1.equals(p2)||p2.equals(p3)||p3.equals(p1),"there are 2 points who are the same");
        //==========same line============
        assertFalse(p1.samePointRasio(p2) || p2.samePointRasio(p3) || p3.samePointRasio(p1),"there are 2 points on same line");
        Vector normal = p1p2.crossProduct(p1p3);
        normal.normalize();
        assertTrue(normal.equals(plane.getNormal())||(normal.flip()).equals(plane.getNormal()),"ERROR: getNormal() result isn't correct ");
        //assertEquals(plane.getNormal(),normal);
    }

    @Test
    void findIntsersections(){
        //EP:ray isn't orthogonal or parallel to the plane
        Plane plane = new Plane(new Point3D(1,0,2),new Vector(1,2,3));
        // TC01: Ray's line is outside the plane (0 points)
        assertNull(plane.findIntsersections(new Ray(new Vector(5,6,4),
                new Point3D(0,0,1))),"Ray's line out of plane");
        // TC02: Ray's line is crossing the plane(1 point)
        Point3D p1 = new Point3D(7,2667,0); //this point is on the plane that interact with ray
        List<Point3D> result = plane.findIntsersections(new Ray(new Vector(3, 1, 0),
                new Point3D(-1, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result);
        //-----------------------------BVA--------------------------------------
        //Ray is parallel to the plane
        Ray r1 = new Ray(new Vector(2, -1, 0),new Point3D(2, -1, 0));
        result = plane.findIntsersections(r1);
        assertEquals(0,plane.getNormal().dotProduct(r1.getRayDir()),"this ray isn't parallel or included");
        assertEquals(0, result.size(),"this ray isn't parallel to the plane");
        //ray is included in the plane
        Ray r2 = new Ray(new Vector(2, -1, 0),new Point3D(7, 0, 0));
        result = plane.findIntsersections(r2);
        assertEquals(0,plane.getNormal().dotProduct(r2.getRayDir()),"this ray isn't parallel or included");
        assertTrue(result.size()>0,"this ray isn't included in the plane");
        //ray is orthogonal to the plane
        //P0 is before the plane
        Point3D p2 = new Point3D(0.429,-1.143,0.286);
        result = plane.findIntsersections(new Ray(new Vector(2,4,6),new Point3D(-1,0,0)));
        assertEquals(1,result.size(),"the ray isn't orthogonal");
        assertEquals(List.of(p2), result);
        //p0 is on the plane
        Point3D p3 = new Point3D(7,0,0);
        result = plane.findIntsersections(new Ray(new Vector(2,4,6),p3));
        assertEquals(1,result.size(),"the ray isn't orthogonal");
        assertEquals(List.of(p3), result);
        //p0 is after the plane
        result = plane.findIntsersections(new Ray(new Vector(2,4,6),new Point3D(0,0,1)));
        assertEquals(0,result.size(),"the ray isn't orthogonal");
        //ray isn't parallel or orthogonal and start on the plane
        result = plane.findIntsersections(new Ray(new Vector(7,4,6),p3));
        assertEquals(1,result.size(),"the ray isn't on the plane");
        //ray isn't parallel or orthogonal and start at Q0
        result = plane.findIntsersections(new Ray(new Vector(7,4,6),new Point3D(1,0,2)));
        assertEquals(1,result.size(),"the ray isn't on the plane");
    }

}