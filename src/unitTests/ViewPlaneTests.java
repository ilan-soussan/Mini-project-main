package unitTests;

import primitives.*;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import elements.Camera;
import geometries.*;

public class ViewPlaneTests {

	@Test
	public void testConstructRayThroughPixelWithSphere() {
        Sphere sphere1 = new Sphere(1d,new Point3D(0,0,-3));
        Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, -1, 0)).setDistance(1);
        camera.setViewPlaneSize(3,3);
        //------Test 1: 2 interaction points------------------------
        List<Point3D> tempList;
        int count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = sphere1.findIntsersections(camera.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals(2,count);
        //---------Test 2: 18 intersection points-----------------------
        Sphere sphere2 = new Sphere(2.5d,new Point3D(-2.5,0,0));
        Camera camera2 = new Camera(new Point3D(0,0,0.5), new Vector(0, 0, -1), new Vector(0, -1, 0)).setDistance(1);
        count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = sphere2.findIntsersections(camera2.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals(18,count);
        //------------Test 3: 10 intersections points----------
        Sphere sphere3 = new Sphere(2d,new Point3D(0,0,-2));
        count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = sphere3.findIntsersections(camera2.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals(10,count);
        //-------------Test 4: 9 intersection points------------
        Sphere sphere4 = new Sphere(4d,new Point3D(0,0,-2));
        count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = sphere4.findIntsersections(camera2.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals(9,count);
        //-----------Test 5: 0 intersection
        Sphere sphere5 = new Sphere(0.5,new Point3D(0,0,1));
        count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = sphere5.findIntsersections(camera2.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals(0,count);

    }

	@Test
	public void testConstructRayThroughPixelWithPlane() {
        Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(1,1,0));
        Cam.setViewPlaneSize(3,3);
        Cam.setDistance(1);
        Plane P1 =new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0.5,0.5,0.5));
        //test 1
        int count =findTheNumberOFIntsersectionsOnThePlane(Cam,P1);
        assertEquals("Error with camere constructRayThroughPixel with plane thet parallel to viewPanel",count,9);


        Plane P2 =new Plane(new Point3D(1,0,0),new Point3D(0,1,0),new Point3D(0,0,1));
        //test 2
        count =findTheNumberOFIntsersectionsOnThePlane(Cam,P2);
        assertEquals("Error with camere constructRayThroughPixel with plane thet not parallel to viewPanel",count,6);
    }

	@Test
	public void testConstructRayThroughPixelWithTriangle() {
	    Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0));
	    Cam.setViewPlaneSize(3,3);
	    Cam.setDistance(1);
        triangle t1=new triangle(new Point3D(0, 1, -2),new Point3D(-1, -1, -2),new Point3D(1, -1, -2));

        int count = findTheNumberOFIntsersectionsOnThePlane(Cam,t1);
        assertEquals("Error with camere constructRayThroughPixel with triangle with one point",2,count);
        //test 2
	    triangle T2 = new triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));

	    count = findTheNumberOFIntsersectionsOnThePlane(Cam,T2);
        assertEquals("Error with camere constructRayThroughPixel with triangle with more then one",count,1);
    }
    public int findTheNumberOFIntsersectionsOnThePlane(Camera Cam,Intersectable Shape)
    {
        List<Point3D> tempList;
        int count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = Shape.findIntsersections(Cam.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        return count;
    }
}

