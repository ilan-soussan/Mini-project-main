package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;


/**
 * this class purpose is for saving a list of geometries this class implements from intersectable
 * also this class have a bounding box and put all the the shapes that interact with a ray into this box.
 */
public class Geometries implements Intersectable{
    private List<Intersectable> geometries;
    Box box;

    public Geometries()
    {
        geometries = new LinkedList<Intersectable>();
    } //constractor that make an empty list

    /**
     * @param geometrieList
     * constractor that get a list and put it in geomery
     */
    public Geometries(Intersectable... geometrieList)    {
        geometries = new LinkedList<Intersectable>();

        for (int i=0;i<geometrieList.length;i++)
        {
            geometries.add(geometrieList[i]);
        }
    }

    /**
     * @param geometrieList
     * add a shape int the shapes list and also to the box list
     */
    public void add(Intersectable... geometrieList) {
        box = geometrieList[0].getBox();
        for (int i=0;i<geometrieList.length;i++) {
            box.addBox(geometrieList[i].getBox());
            geometries.add(geometrieList[i]);
    }
}

    public  List<Intersectable> getList()
    {
        return geometries;
    }

    @Override
    public Box getBox() {
        if(box != null)
            return box;
        return null;

    }

    /**
     * @param ray
     * @return List<GeoPoint>
     *     this func will go on all off ou list  it will check if this ray interact with the box
     *     if its happening we make a list of the intersection points and if the list isnt empty(there are points)
     *     we add them to a temp list and returns it
     */
    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        List<GeoPoint> tempList =new LinkedList<GeoPoint>();
        for (Intersectable geometry:geometries) {
            if (geometry.getBox().Interact(ray)) { //check if ray interact with box
                List<GeoPoint> listFromGeometries = geometry.findGeoIntersection(ray); //chek list of points
                if (listFromGeometries != null ) {
                    for (GeoPoint gp : listFromGeometries) {
                    tempList.add(gp); //add the points to a temp list
                    }
                }
            }
        }

        if(tempList.isEmpty()) //if temp lisnt is empty return null alse rer the list
            return null;
        return tempList;
    }
    }


