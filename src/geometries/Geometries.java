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
    Box box = new Box();

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
        for (int i=0;i<geometrieList.length;i++) {
            box.add(geometrieList[i].getBox());
            geometries.add(geometrieList[i]);
    }
}

    public  List<Intersectable> getList()
    {
        return geometries;
    }

    @Override
    public Box getBox() {
        Box inner = new Box();
        for (Intersectable Inter: this.getList()) { //here we go on all the shapes in our list
            if (inner != null) { //if the new isnt null
                if (box == null) { //only is the box is null now
                    box = new Box(inner.pMin, inner.pMax); //the box will be the new box we created
                }
                else { //we add to the box the intersectable box
                    box.add(Inter.getBox());
                }
            }
        }
        return box;

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


