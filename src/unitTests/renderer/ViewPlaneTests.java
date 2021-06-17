package unitTests.renderer;

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
        int count = findTheNumberOFIntsersectionsOnTheShape(camera,sphere1);
        assertEquals("Error with camera constructRayThroughPixel with sphere with 2 intersections",2,count);
        //---------Test 2: 18 intersection points-----------------------
        Sphere sphere2 = new Sphere(2.5d,new Point3D(0,0,-2.5));
        Camera camera2 = new Camera(new Point3D(0,0,0.5), new Vector(0, 0, -1), new Vector(0, 1, 0)).setDistance(1);
        camera2.setViewPlaneSize(3,3);
        count = findTheNumberOFIntsersectionsOnTheShape(camera2,sphere2);
        assertEquals("Error with camera constructRayThroughPixel with sphere with 18 intersections",18,count);
        //------------Test 3: 10 intersections points----------
        Sphere sphere3 = new Sphere(2d,new Point3D(0,0,-2));
        count = findTheNumberOFIntsersectionsOnTheShape(camera2,sphere3);
        assertEquals("Error with camera constructRayThroughPixel with sphere with 10 intersections",10,count);
        //-------------Test 4: 9 intersection points------------
        Sphere sphere4 = new Sphere(4d,new Point3D(0,0,-2));
        count = findTheNumberOFIntsersectionsOnTheShape(camera2,sphere4);
        assertEquals("Error with camera constructRayThroughPixel with sphere with 9 intersections",9,count);
        //-----------Test 5: 0 intersection
        Sphere sphere5 = new Sphere(0.5,new Point3D(0,0,1));
        count = findTheNumberOFIntsersectionsOnTheShape(camera2,sphere5);
        assertEquals("Error with camera constructRayThroughPixel with sphere with 0 intersections",0,count);
    }
	@Test
	public void testConstructRayThroughPixelWithPlane() {
        Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0)).setDistance(1);
        Cam.setViewPlaneSize(3,3);
        //test 1: 9 intersections points
        Plane P1 =new Plane(new Point3D(0,0,-1),new Point3D(0,1,-1),new Point3D(1,2,-1));
        int count =findTheNumberOFIntsersectionsOnTheShape(Cam,P1);
        assertEquals("Error with camera constructRayThroughPixel with plane that parallel to viewPanel",9,count);

        //test 2:9 intersections points
        Plane P2 = new Plane(new Point3D(2,0,-1),new Point3D(0,0,-2),new Point3D(0,-10,1));
        count = findTheNumberOFIntsersectionsOnTheShape(Cam,P2);
        assertEquals("Error with camera constructRayThroughPixel with plane that not parallel to viewPanel",9,count);

        //test 2: 6 intersection points
        Plane P3 =new Plane(new Point3D(2,0,-1),new Point3D(0,0,-2),new Point3D(0,-2,1));
        count =findTheNumberOFIntsersectionsOnTheShape(Cam,P3);
        assertEquals("Error with camera constructRayThroughPixel with plane that not parallel to viewPanel",6,count);
    }
	@Test
	public void testConstructRayThroughPixelWithTriangle() {
	    Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0));
	    Cam.setViewPlaneSize(3,3);
	    Cam.setDistance(1);
        Triangle t1=new Triangle(new Point3D(0, 1, -2),new Point3D(-1, -1, -2),new Point3D(1, -1, -2));

        int count = findTheNumberOFIntsersectionsOnTheShape(Cam,t1);
        assertEquals("Error with camera constructRayThroughPixel with triangle with one point",1,count);
        //test 2
	    Triangle T2 = new Triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));

	    count = findTheNumberOFIntsersectionsOnTheShape(Cam,T2);
        assertEquals("Error with camera constructRayThroughPixel with triangle with more then one",2,count);
    }
    public int findTheNumberOFIntsersectionsOnTheShape(Camera Cam,Intersectable Shape)
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

