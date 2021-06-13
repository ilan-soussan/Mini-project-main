/**
 * 
 *//*

package unittests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point3D;
import geometries.Tube;

*/
/**
 * Testing Tubes
 * 
 * @author Itamar and Raz
 *
 *//*

public class TubeTest {
	*/
/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 *//*

	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p=new Point3D(0,0,0);
		Vector v=new Vector(0,0,1);
		Ray r=new Ray(p,v);
		Tube t= new Tube(r,3);
		Point3D check1=new Point3D(0,3,1);
		 // TC01:test equilibrium class at a particular point in the Infinite Tube
		assertEquals(new Vector(0,1,0),t.getNormal(check1));
		// =============== Boundary Values Tests ==================
		Point3D check2=new Point3D(3,0,0);
		 // TC11: test the point is in front of the head of the foundation
		assertEquals(new Vector(1,0,0),t.getNormal(check2));
	}
	*/
/**
	 * Test method for {@link geometries.Tube#findIntersections(primitives.Point3D)}.
	 *//*

	@Test
	public void testFindIntersersections() {	
		Tube t=new Tube(new Ray(new Point3D(0,0,1),new Vector(0,0,1)),1d);
		// ============ Equivalence Partitions Tests ==============
		//The ray does not intersect the tube(null)
		assertNull(t.findIntersections(new Ray(new Point3D(0,0,5),new Vector(0,0,1))));
		//The ray intersect the tube in two points(two points)
		assertArrayEquals(new ArrayList<Point3D>(Arrays.asList(new Point3D(0.7071067811865, 0.7071067811865, 1.3535533905933),new Point3D(-0.7071067811865,-0.7071067811865,0.6464466094067))).toArray(),t.findIntersections(new Ray(new Point3D(1,1,1.5),new Vector(-1,-1,-0.5))).toArray() );
		//The ray intersect the tube in one point(one point)
		assertArrayEquals(new ArrayList<Point3D>(Arrays.asList(new Point3D(-1,0,0))).toArray(),t.findIntersections(new Ray(new Point3D(-1,1,1),new Vector(0,-1,-1))).toArray() );
		// =============== Boundary Values Tests ==================
		//p0 is inside the tube and v is the direction of the tube.
		assertNull(t.findIntersections(new Ray(new Point3D(0.5,0,0),new Vector(0,0,1))));
		//only p0 is on the tube (null requirement)
		assertNull(t.findIntersections(new Ray(new Point3D(1,0,0),new Vector(1,0,1))));
		//p0 and all the ray is on the tube
		assertNull(t.findIntersections(new Ray(new Point3D(1,0,0),new Vector(0,0,1))));
		//The ray is orthogonal for the tube
		assertArrayEquals(new ArrayList<Point3D>(Arrays.asList(new Point3D(1,0,0),new Point3D(-1,0,0))).toArray(),t.findIntersections(new Ray(new Point3D(2,0,0),new Vector(-1,0,0))).toArray() );
		//
		assertArrayEquals(new ArrayList<Point3D>(Arrays.asList(new Point3D(-1,0,1))).toArray(),t.findIntersections(new Ray(new Point3D(0,0,1),new Vector(-1,0,0))).toArray() );
		//
	}
}
*/
*/
