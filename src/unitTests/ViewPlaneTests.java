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
	}

	@Test
	public void testConstructRayThroughPixelWithTriangle() {
	    Camera Cam = new Camera(Point3D.ZERO,new Vector(0,0,-1),new Vector(0,1,0));
	    Cam.setViewPlaneSize(3,3);
	    Cam.setDistance(1);
        triangle t1=new triangle(new Point3D(0, 1, -2),new Point3D(-1, -1, -2),new Point3D(1, -1, -2));
        List<Point3D> tempList;
        int count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = t1.findIntsersections(Cam.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals("Error with camere constructRayThroughPixel with triangle with one point",count,2);
        //test 2
	    triangle T2 = new triangle(new Point3D(0,20,-2),new Point3D(1,-1,-2),new Point3D(-1,-1,-2));
        tempList = null;
        count = 0;
        for(int i=0;i<3;i++)
        {
            for (int j = 0;j<3;++j)
            {
                tempList = T2.findIntsersections(Cam.constructRayThroughPixel(3,3,i,j));
                if(tempList!=null)
                {
                    count +=tempList.size();
                }
            }
        }
        assertEquals("Error with camere constructRayThroughPixel with triangle with more then one",count,1);
    }


}