package unitTests;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.*;
import geometries.Sphere;
import renderer.*;
import scene.Scene;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PinTest {
    private Scene scene = new Scene("Test scene");

    @Test
    public void pinTest() {
        Camera camera = new Camera(new Point3D(-1000, 50, -150), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(150, 150).setDistance(200);

        scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.30));
        Pin p1 = new Pin(new Point3D(100,50,-150),new Point3D(100,50,-140));
        Pin p2 = new Pin(new Point3D(120,70,-150),new Point3D(100,70,-140));
        Pin p3 = new Pin(new Point3D(120,30,-150),new Point3D(100,30,-140));
        Pin p4 = new Pin(new Point3D(140,90,-150),new Point3D(100,90,-140));
        Pin p5 = new Pin(new Point3D(140,50,-150),new Point3D(100,50,-140));
        Pin p6 = new Pin(new Point3D(140,10,-150),new Point3D(100,10,-140));


        List<Pin> Plist = List.of(p1,p2,p3,p4,p5,p6);
        for (Pin p:Plist) {
            for (Sphere s : p.list) {
                if (s != null) {
                    scene.geometries.add(s);
                }
            }

        }

        scene.geometries.add(

                new Triangle(new Point3D(200,0,-150),new Point3D(-2000,100,-150),new Point3D(-2000,0,-150))
               .setEmission(new Color(java.awt.Color.orange))
               .setMaterial(new Material().setKd(0.0).setKs(0.0).setShininess(0).setkR(0.3)),

                new Triangle(new Point3D(200,0,-150),new Point3D(-2000,100,-150),new Point3D(200,100,-150))
                .setEmission(new Color(java.awt.Color.orange))
                .setMaterial(new Material().setKd(0.0).setKs(0.0).setShininess(0).setkR(0.3)),

                new Plane(new Point3D(0, 0, -155), new Point3D(1, 1, -155), new Point3D(2, 0, -155))
                .setEmission(new Color(java.awt.Color.PINK))
                .setMaterial(new Material().setKd(0.0).setKs(0.0).setShininess(20).setkR(0.5)));

                /*new Plane(new Point3D(0, 0, -150), new Point3D(150, 0, 0), new Point3D(-150, 0, 0))
                        .setEmission(new Color(java.awt.Color.ORANGE))
                        .setMaterial(new Material().setKd(0.0).setKs(0.0).setShininess(20).setkR(0.5)));
                new Sphere(10, new Point3D(3, 0, 15)) //
                        .setEmission(new Color(java.awt.Color.WHITE)) //
                        .setMaterial(new Material().setKd(0.0).setKs(0.0).setShininess(20)),
                new Sphere(7.5, new Point3D(3, 0, 7.5)) //
                        .setEmission(new Color(java.awt.Color.BLUE)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(6, new Point3D(3, 0, 3.8)) //
                        .setEmission(new Color(java.awt.Color.RED)) //
                        .setMaterial(new Material().setKd(0.2).setKs(0.25).setShininess(20)),
                new Sphere(7.5, new Point3D(3, 0, 22.5)) //
                        .setEmission(new Color(java.awt.Color.YELLOW)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(6, new Point3D(3, 0, 26.3)) //
                        .setEmission(new Color(java.awt.Color.PINK)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Sphere(5, new Point3D(3, 0, 50)) //
                        .setEmission(new Color(java.awt.Color.GREEN)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)),
                new Tube(5,new Ray(new Vector(0,0,5),new Point3D(30,0,0)))
                        .setEmission(new Color(java.awt.Color.WHITE)) //
                        .setMaterial(new Material().setKd(0.25).setKs(0.25).setShininess(20)));*/

        scene.lights.add(new DirectionalLight(new Color(java.awt.Color.YELLOW), new Vector(0, 1,0)));
        scene.lights.add(new PointLight(new Color(java.awt.Color.BLUE),new Point3D(90,50,-150)));

        ImageWriter imageWriter = new ImageWriter("pin", 600, 600);
        Render render = new Render() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracerBase(new RayTracerBasic(scene));

        render.renderImage();
        render.writeToImage();
    }

/*   *//** Rotates a vector around the x-axis.
     * @param v The vector to be rotated.
            * @param angle The angle of rotation.
     * @return The resultant vector.
     *//*
    private Vector RotateX(Vector v, double angle){
        Point3D head = v.getHead();
        double x = head.getX().getCoord(), y= head.getY().getCoord(), z= head.getZ().getCoord();
        double s = Math.sin(angle), c = Math.cos(angle);
        return new Vector(x, y*c - z*s, y*s + z*c);
    }

    *//**
     * Rotates a vector around the y-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.
     *//*
    private Vector RotateY(Vector v, double angle){
        Point3D head = v.getHead();
        double x = head.getX().getCoord(), y= head.getY().getCoord(), z= head.getZ().getCoord();
        double s = Math.sin(angle), c = Math.cos(angle);
        return new Vector(x*c + z*s, y, -x*s + z*c);
    }

    *//**
     * Rotates a vector around the z-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.
     *//*
    private Vector RotateZ(Vector v, double angle){
        Point3D head = v.getHead();
        double x = head.getX().getCoord(), y= head.getY().getCoord(), z= head.getZ().getCoord();
        double s = Math.sin(angle), c = Math.cos(angle);
        return new Vector(x*c - y*s, x*s + y*c, z);
    }

    double xAngle = Math.toRadians(-13);//Looking down 4.5 degrees.
    double yAngle = Math.toRadians(-36);//Looking 8 degrees to the left.
    double zAngle = Math.toRadians(0);//Rotation around z axis is like having ones head stay in place ,
    // and spin his legs around him without turning him away from what hes looking at.

    Vector to = RotateZ(RotateY(RotateX(new Vector(0,0,-1), xAngle), yAngle), zAngle);
    Vector up = RotateZ(RotateY(RotateX(new Vector(0,1,0), xAngle), yAngle), zAngle);

    double dist = 7E3;//Factor of distance of camera to scene, used to control view maintaining the desired angle.
    Camera camera = new Camera(new Point3D(-6.3*dist, 3*dist, 10*dist), to, up) //
            .setViewPlaneSize(200, 200).setDistance(1000);*/

}
