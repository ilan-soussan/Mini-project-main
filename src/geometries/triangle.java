package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.LinkedList;
import java.util.List;

public class triangle extends Polygon{

    public  triangle(Point3D p1,Point3D p2,Point3D p3)
    {
        vertices = new LinkedList<Point3D>();
        vertices.add(p1);
        vertices.add(p2);
        vertices.add(p3);
        plane =new Plane(p1,p2,p3);
    }

    @Override
    public Vector getNormal(Point3D point) {
        return plane.getNormal(point);
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {


    }
}
