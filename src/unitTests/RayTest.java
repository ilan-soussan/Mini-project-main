package unitTests;

import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Vector(-1,0,3), new Point3D(1,0,0));
        //--------------------EP: closest in the middle of list-------------------
        List<Point3D> point3DList = Arrays.asList(new Point3D(0,0,4),new Point3D(0.87,0,0.39),new Point3D(0,3,0));
        assertEquals(point3DList.get(1),ray.findClosestPoint(point3DList),"the EP test didn't work");
        //BVA
        //------------------------------Empty list-------------------------------
        List<Point3D> emptyList = Arrays.asList();
        assertNull(ray.findClosestPoint(emptyList),"empty list test doesn't work");
        //---------------------- closest is at the index 0------------------------
        List<Point3D> firstList = Arrays.asList(new Point3D(0.87,0,0.39),new Point3D(0,0,4),new Point3D(0,3,0));
        assertEquals(firstList.get(0),ray.findClosestPoint(firstList),"the BVA first point test didn't work");
        //---------------------- closest is at the index 2------------------------
        List<Point3D> lastList = Arrays.asList(new Point3D(0,0,4),new Point3D(0,3,0),new Point3D(0.87,0,0.39));
        assertEquals(lastList.get(2),ray.findClosestPoint(lastList),"the BVA last point test didn't work");
    }
}