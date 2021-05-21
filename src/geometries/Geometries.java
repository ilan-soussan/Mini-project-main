package geometries;

import primitives.*;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    public List<Intersectable> list;
    public Geometries()
    {
        list = new LinkedList<>();
        //LinkedList uses Doubly Linked List to store its elements.
        //LinkedList is faster being node based as not much bit shifting required.
        //LinkedList implements List as well as Queue. It can acts as a queue as well.
        //LinkedList is faster in manipulation of data.
    }
    public Geometries(Intersectable...geometries){

        for (Intersectable g:geometries)
        {
            list.add(g);
        }
    }
    public void add(Intersectable... geometries){
        for (Intersectable g:geometries)
        {
            list.add(g);
        }
    }
    @Override
    public List<Point3D> findIntsersections(Ray ray) {
        return null;
    }
}
