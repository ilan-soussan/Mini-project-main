package geometries;

import elements.AmbientLight;
import elements.Camera;
import elements.DirectionalLight;
import org.junit.Test;
import primitives.*;
import renderer.ImageWriter;
import renderer.RayTracerBasic;
import renderer.Render;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pin{
    Point3D Location;
    public Sphere Head, BigS,MediumSUnder,MediumSOn,SmallSUnder,SmallSOn,Line;
    public List<Sphere> list;
    public Pin (Point3D location,Point3D middle)
    {
        Location = location;
        BigS = new Sphere(10,middle);
        BigS.setEmission(new Color(java.awt.Color.WHITE));
        Point3D MUcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() - 5);
        MediumSUnder = new Sphere(10,MUcenter);
        MediumSUnder.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Point3D MOcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() + 5);
        MediumSOn = new Sphere(10,MOcenter);
        MediumSOn.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Point3D SUcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() - 10);
        SmallSUnder = new Sphere(7,SUcenter);
        SmallSUnder.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Point3D SOcenter = new Point3D(middle.getX(),middle.getY(), middle.getZ() + 10);
        SmallSOn = new Sphere(7,SOcenter);
        SmallSOn.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Head = new Sphere(7,new Point3D(location.getX(),location.getY(), location.getZ() + 34));
        Head.setEmission(new Color(java.awt.Color.WHITE)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        Line = new Sphere(4,new Point3D(location.getX(),location.getY(),location.getZ() + 30));
        Line.setEmission(new Color(java.awt.Color.RED)).setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(30));
        list = List.of(Head,BigS,MediumSUnder,MediumSOn,SmallSUnder,SmallSOn,Line);
    }

    /*@Override
    public List<GeoPoint> findGeoIntersection(Ray ray) {
        //make a list of the 6 parms below and make a forech loop
        List<GeoPoint> glist = new LinkedList<>();
        for (Sphere s:list) {
           glist.addAll(s.findGeoIntersection(ray));
        }
        if (glist != null)
            return  glist;
        return null;
    }*/



}
