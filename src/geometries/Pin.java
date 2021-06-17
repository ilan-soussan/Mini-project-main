package geometries;

import primitives.*;
import java.util.List;

/**
 * this class is a class that we made to represent a pin of a bowling
 * its make of 7 spheres:
 * @params Head, BigS,MediumSUnder,MediumSOn,SmallSUnder,SmallSOn,Line
 * head is the head of pin, line is the red line,big and the medium and small make the special form of the pin
 * the class also countain a bounding box that we dd the all the spheres
 */
public class Pin{
    Point3D Location; //this location where the pin is on
    public Sphere Head, BigS,MediumSUnder,MediumSOn,SmallSUnder,SmallSOn,Line;
    public List<Sphere> list;
    public Box box; //Conservative Bounding Region

    /**
     * @param location
     * @param middle
     * two points the first is the like we explain earlier this location of the pin the middle is the center of the big sphere
     * I calculate in a way that all pin have always the same height and made the sane and th only different it the location
     */
    public Pin (Point3D location,Point3D middle)
    {
        Location = location;
        BigS = new Sphere(10,middle);
        BigS.setEmission(new Color(java.awt.Color.WHITE));
        Point3D MUcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() - 5); //here we calculate the center of the middle sphere that under the head only the size of the height changes!
        MediumSUnder = new Sphere(10,MUcenter);
        MediumSUnder.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Point3D MOcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() + 5); //here we calculate the center of the middle sphere that on the head only the size of the height changes!
        MediumSOn = new Sphere(10,MOcenter);
        MediumSOn.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Point3D SUcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() - 10);//here we calculate the center of the small sphere that under the head only the size of the height changes!
        SmallSUnder = new Sphere(7,SUcenter);
        SmallSUnder.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Point3D SOcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() + 10);//here we calculate the center of the small sphere that on the head only the size of the height changes!
        SmallSOn = new Sphere(7,SOcenter);
        SmallSOn.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Head = new Sphere(7,new Point3D(location.getX(),location.getY(), location.getZ() + 34));//here we calculate the center of the head sphere only the size of the height changes!
        Head.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Line = new Sphere(4,new Point3D(location.getX(),location.getY(),location.getZ() + 30)); //here we calculate the center of the line sphere
        Line.setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        list = List.of(Head,BigS,MediumSUnder,MediumSOn,SmallSUnder,SmallSOn,Line); //list of
        box = BigS.getBox(); //deafault box
        for (Sphere s:list) { //foreach sphereb add the box
            box.addBox(s.getBox());
        }
    }

}
