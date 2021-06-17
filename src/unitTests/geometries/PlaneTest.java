package unitTests.geometries;

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
        assertNull(plane.findIntsersections(new Ray(new Vector(-0.38,-5.85,0.0),
                new Point3D(-3,0,0))),"Ray's line out of plane");
        // TC02: Ray's line is crossing the plane(1 point)
        Point3D p1 = new Point3D(0.42253521126760596,-1.6901408450704227,0); //this point is on the plane that interact with ray
        List<Point3D> result = plane.findIntsersections(new Ray(new Vector(4.73, -3.3, 0d),
                new Point3D(-2, 0, 0)));
        assertEquals(1, result.size(), "Wrong number of points");
        assertEquals(List.of(p1), result);
        //-----------------------------BVA--------------------------------------
        //Ray is parallel to the plane
        Ray r1 = new Ray(new Vector(1, -2, 1),new Point3D(3, 0, 0));
        result = plane.findIntsersections(r1);
        assertEquals(0,plane.getNormal().dotProduct(r1.getRayDir()),"this ray isn't parallel or included");
        assertNull(result,"this ray isn't parallel to the plane");
        //ray is included in the plane
        Ray r2 = new Ray(new Vector(1,2,3),new Point3D(0,2,1));
        result = plane.findIntsersections(r2);
        assertNull(result,"this ray isn't included in the plane"); //the normaldir equal 0 so we return null(can't divide by zero)
        //ray is orthogonal to the plane
        //P0 is before the plane
        Point3D p2 = new Point3D(1.1919246435845212,-0.739623217922607,2.0140376782077394);
        result = plane.findIntsersections(new Ray(new Vector(-3.74,-0.94,1.87),new Point3D(3.14,-0.25,1.04)));
        assertEquals(1,result.size(),"the ray isn't orthogonal");
        assertEquals(List.of(p2), result);
        //p0 is on the plane
        result = plane.findIntsersections(new Ray(new Vector(2,4,6),p2));
        assertNull(result,"the ray isn't orthogonal");
        //p0 is after the plane
        result = plane.findIntsersections(new Ray(new Vector(-1.28,-0.32,0.64),new Point3D(0.68,-0.86,2.27)));
        assertNull(result,"the ray isn't orthogonal");
        //ray isn't parallel or orthogonal and start on the plane
        result = plane.findIntsersections(new Ray(new Vector(-3.82,8.7,-2.01),p2));
        assertNull(result,"the ray isn't on the plane");
        //ray isn't parallel or orthogonal and start at Q0
        result = plane.findIntsersections(new Ray(new Vector(-2.33,-3.67,-2d),plane.getQ0()));
        assertNull(result,"the ray isn't on the plane");
    }

}