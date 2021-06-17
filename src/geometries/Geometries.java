package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.*;


/**
 * this class purpose is for saving a list of geometries this class implements from intersectable
 * also this class have a bounding box and put all the the shapes that interact with a ray into this box.
 */
public class Geometries implements Intersectable {
    private List<Intersectable> geometries;
    private Box mainBox;
    Box box;

    public Geometries() {
        geometries = new LinkedList<Intersectable>();
    } //constractor that make an empty list

    /**
     * @param geometrieList constractor that get a list and put it in geomery
     */
    public Geometries(Intersectable... geometrieList) {
        geometries = new LinkedList<Intersectable>();
        add(geometrieList);
    }

    /**
     * @param geometrieList add a shape int the shapes list and also to the box list
     */
    public void add(Intersectable... geometrieList) {
        List<Box> tempListBox = new LinkedList<>();
        Box tempBox = null;
        Box mainBoxForGeomtris = null;
        int i = 0;

        for (; i < geometrieList.length; i++) {
            if (i % 2 == 0) {
                tempBox = geometrieList[i].getBox();
                if (mainBoxForGeomtris == null)
                    mainBoxForGeomtris = new Box(tempBox.pMin, tempBox.pMax);
                else
                    mainBoxForGeomtris.addBox(tempBox);
            } else {
                Box temp = geometrieList[i].getBox().addToNewBox(tempBox);
                temp.listOfBoxses.add(geometrieList[i].getBox());
                temp.listOfBoxses.add(tempBox);
                tempListBox.add(temp);
            }
            geometries.add(geometrieList[i]);
        }


        if (i % 2 == 1)
            mainBoxForGeomtris.listOfBoxses.add(tempBox);

        for (Box b : tempListBox) {
            mainBoxForGeomtris.addBox(b);
            mainBoxForGeomtris.listOfBoxses.add(b);
        }


        if (mainBox == null)
            mainBox = new Box(mainBoxForGeomtris.pMin, mainBoxForGeomtris.pMax);
        else
            mainBox.addBox(mainBoxForGeomtris);


        mainBox.listOfBoxses.add(mainBoxForGeomtris);
    }


    public List<Intersectable> getList() {
        return geometries;
    }

    @Override
    public Box getBox() {
        if (box != null)
            return box;
        return null;

    }

    /**
     * @param ray
     * @return List<GeoPoint>
     * this func will go on all off ou list  it will check if this ray interact with the box
     * if its happening we make a list of the intersection points and if the list isnt empty(there are points)
     * we add them to a temp list and returns it
     */
/*    @Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {

        List<GeoPoint> List = findGeoIntersection(ray,mainBox);
        return List;
    }
    private List<GeoPoint> findGeoIntersection(Ray ray,Box b)
    {
        List<GeoPoint> List = new LinkedList<GeoPoint>();
        if(b.Interact(ray))
        {
            for(Intersectable geometry : geometries)
                if(b==geometry.getBox()) {
                    List<GeoPoint> tempList = new LinkedList<>();
                    List<GeoPoint> listFromGeometries = geometry.findGeoIntersection(ray); //chek list of points
                    if (listFromGeometries != null) {
                        for (GeoPoint gp : listFromGeometries) {
                            tempList.add(gp); //add the points to a temp list
                        }
                    }
                    return tempList;
                }

            if(!b.listOfBoxses.isEmpty()) {
                for (Box b2 : b.listOfBoxses) {
                    List<GeoPoint> tempList = findGeoIntersection(ray, b2);
                    if(!b.listOfBoxses.isEmpty()) {
                        for (GeoPoint point : tempList)
                            List.add(point);
                    }
                }
            }


        }
        return List;
    }*/
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


            /*
        List<GeoPoint> tempList = new LinkedList<GeoPoint>();


        if (mainBox.Interact(ray))
            for (Box b : mainBox.listOfBoxses)
                if (b.Interact(ray))
                    for (Box b2 : b.listOfBoxses)
                        if (b2.Interact(ray)) {
                            if (b2.listOfBoxses == null) {
                                if (b2.Interact(ray)) {
                                    for (Intersectable geometry : geometries) {
                                        List<GeoPoint> listFromGeometries = geometry.findGeoIntersection(ray); //chek list of points
                                        if (listFromGeometries != null) {
                                            for (GeoPoint gp : listFromGeometries) {
                                                tempList.add(gp); //add the points to a temp list
                                            }
                                        }
                                    }
                                }
                            } else
                                for (Box b3 : b2.listOfBoxses) {
                                    if (b3.Interact(ray)) {
                                        for (Intersectable geometry : geometries) {
                                            if(geometry.getBox()==b3) {
                                                List<GeoPoint> listFromGeometries = geometry.findGeoIntersection(ray); //chek list of points
                                                if (listFromGeometries != null) {
                                                    for (GeoPoint gp : listFromGeometries) {
                                                        tempList.add(gp); //add the points to a temp list
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }

                        }


        if (tempList.isEmpty()) //if temp lisnt is empty return null alse rer the list
            return null;
        return tempList;
        */





