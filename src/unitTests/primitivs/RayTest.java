package unitTests.primitivs;

import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import geometries.Intersectable.*;


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

    @Test
    void findClosestGeoPoint(){
        Ray ray = new Ray(new Vector(-1,0,3), new Point3D(1,0,0));
        //--------------------EP: closest in the middle of list-------------------
        Sphere sphere1 = new Sphere(1d,new Point3D(0,3,0));
        GeoPoint geo1 = new GeoPoint(sphere1,new Point3D(0,3,0));
        Triangle triangle = new Triangle(new Point3D(0,-1,0.39),new Point3D(0,1,0.39),new Point3D(2,0,0.39));
        GeoPoint geo2 = new GeoPoint(triangle,new Point3D(0.87,0,0.39));
        Sphere sphere2 = new Sphere(0.78,new Point3D(0,0,4));
        GeoPoint geo3 = new GeoPoint(sphere2,new Point3D(0,0,4));
        List<GeoPoint> GeopointList = Arrays.asList(geo1,geo2,geo3);
        assertEquals(GeopointList.get(1),ray.findClosestGeoPoint(GeopointList),"the EP test didn't work");
        //BVA
        //------------------------------Empty list-------------------------------
        List<GeoPoint> emptyList = Arrays.asList();
        assertNull(ray.findClosestGeoPoint(emptyList),"empty list test doesn't work");
        //---------------------- closest is at the index 0------------------------
        List<GeoPoint> firstList = Arrays.asList(geo2,geo1,geo3);
        assertEquals(firstList.get(0),ray.findClosestGeoPoint(firstList),"the BVA first point test didn't work");
        //---------------------- closest is at the index 2------------------------
        List<GeoPoint> lastList = Arrays.asList(geo1,geo3,geo2);
        assertEquals(lastList.get(2),ray.findClosestGeoPoint(lastList),"the BVA last point test didn't work");


    }
}