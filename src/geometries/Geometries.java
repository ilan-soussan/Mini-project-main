package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;


/**
 *  class that extends Tube
 *  our why to represent Cylinder in our scene
 *
 *   @author Ilan and didi
 *
 */
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
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        List<GeoPoint> tempList =new LinkedList<GeoPoint>();
        for (Intersectable geometry:geometries) {
            List<GeoPoint> listFromGeometries = geometry.findGeoIntersection(ray);
            if(listFromGeometries !=null){
                for (GeoPoint gp:listFromGeometries) {
                    tempList.add(gp);
                }
            }
        }

        if(tempList.isEmpty())
            return null;
        return tempList;
    }

}

