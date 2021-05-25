package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;

public class Geometries implements Intersectable{
    private List<Intersectable> geometries;

    public Geometries()
    {
        geometries = new LinkedList<Intersectable>();
    }
    public Geometries(Intersectable... geometrieList)    {
        geometries = new LinkedList<Intersectable>();
        for (int i=0;i<geometrieList.length;i++)
        {
            geometries.add(geometrieList[i]);
        }
    }
    public void add(Intersectable... geometrieList)
    {
        for (int i=0;i<geometrieList.length;i++)
    {
        geometries.add(geometrieList[i]);
    }
    }

    public  List<Intersectable> getList()
    {
        return geometries;
    }

    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        List<Point3D> tempList =new LinkedList<Point3D>();
        for (Intersectable geometrie:geometries) {
            List<Point3D> listFromGeometries = geometrie.findIntsersections(ray);
            if(listFromGeometries !=null){
            for (Point3D p:listFromGeometries) {
                tempList.add(p);
            }
            }
        }

        if(tempList.isEmpty())
            return null;
        return tempList;
        }
    }

