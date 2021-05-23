package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;

public class Geometries implements Intersectable{
    private ArrayList<Intersectable> geometries;

    public Geometries()
    {
        geometries = new ArrayList<Intersectable>();
    }
    public Geometries(Intersectable... geometrieList)    {
        geometries = new ArrayList<Intersectable>();
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


    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        List<Point3D> tempList = null;
        geometries.forEach(geometrie -> 
                geometrie.findIntsersections(ray).forEach(Point ->
                        tempList.add(Point) ));
        if(tempList.isEmpty())
            return null;
        return tempList;
        }
    }

