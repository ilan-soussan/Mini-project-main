/**
 * 
 */

package unitTests.geometries;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import primitives.Ray;
import primitives.Vector;
import primitives.Point3D;
import geometries.Tube;


/**
 * Testing Tubes
 * 
 * @author Itamar and Raz
 *
 */

public class TubeTest {

/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point3D)}.
	 */

	@Test
	public void testGetNormal() {
		// ============ Equivalence Partitions Tests ==============
		Point3D p=new Point3D(0,0,0);
		Vector v=new Vector(0,0,1);
		Ray r=new Ray(v,p);
		Tube t= new Tube(3,r);
		Point3D check1=new Point3D(0,3,1);
	}

/**
	 * Test method for {@link geometries.Tube#findGeoIntersection(Ray)} (primitives.Point3D)}.
	 */

}
