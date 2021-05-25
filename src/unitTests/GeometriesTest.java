package unitTests;

import geometries.*;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GeometriesTest {

    @Test
    void add() {
    }

    @Test
    void findIntsersections() {
        Geometries g1 = new Geometries(
                new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1)),
                new Sphere(1d,new Point3D(6,0,0)),
                new Cyilnder(1d,1d,new Ray(new Vector(0,0,1),new Point3D(0,0,4))),
                new triangle(new Point3D(4.43,-2.81,0),new Point3D(4.94,1.17,0),new Point3D(4.7,0,3)),
                new Tube(1d,new Ray(new Vector(0,0,-10),new Point3D(3,0,5)))
        );
        //---------------EP----------------
        List<Point3D> list = g1.findIntsersections(new Ray(new Vector(-4.16,-1.63,1.11),new Point3D(3.58,0.81,3.3)));
        assertTrue(list.size() >= 1,"there no more than one interception");    //////////// התכוונת יותר מ2?
        //---------------------BVA----------------------
        //---------------Empty collection---------------
        Geometries g2 = new Geometries();
        assertTrue(g2.getList().isEmpty(),"this list isn't empty");
        //-------------Non sharp is intersection--------
        list = g1.findIntsersections(new Ray(new Vector(0,-6,0),new Point3D(-8,0,0)));
        assertNull(list,"there is more than 0 interception");
        //-----------Only one sharp has interaction---------
        list = g1.findIntsersections(new Ray(new Vector(3.4,-3.34,4.94),new Point3D(-4,0,0)));
        assertEquals(1,list.size(),"there is more than 1 interception");
        //-----------------All shape interact---------------

        g1 = new Geometries(
                new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1)),
                new Sphere(1d,new Point3D(6,0,0)),
                //new Cyilnder(4.0,2,new Ray(new Vector(0,0,4),new Point3D(1,0,0))),
                //new Tube(0.5,new Ray(new Vector(0,0,7),new Point3D(3.8,0d,-2)))
                new triangle(new Point3D(4.43,-2.81,0),new Point3D(4.94,1.17,0),new Point3D(4.7,0,3))
        );
        list = g1.findIntsersections(new Ray(new Vector(-7.6,-0.99,3.6),new Point3D(6,0,0)));
        assertEquals(g1.getList().size(),list.size(),"not all shapes interact");
    }
}