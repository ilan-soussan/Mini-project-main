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
	}

	@Test
	public void testConstructRayThroughPixelWithPlane() {
        Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,1),new Vector(0,1,0));
        Cam.setViewPlaneSize(3,3);
        Cam.setDistance(1);


        Plane P1 =new Plane()


    }

	@Test
	public void testConstructRayThroughPixelWithTriangle() {
	    Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0));
	    Cam.setViewPlaneSize(3,3);
	    Cam.setDistance(1);
        triangle t1=new triangle(new Point3D(0, 1, -2),new Point3D(-1, -1, -2),new Point3D(1, -1, -2));

        int count = findTheNumberOFIntsersectionsOnThePlane(Cam,t1);
        assertEquals("Error with camere constructRayThroughPixel with triangle with one point",count,2);
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

