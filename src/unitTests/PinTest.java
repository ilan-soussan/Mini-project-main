package unitTests;

import elements.*;
import geometries.*;
import org.junit.Test;
import primitives.*;
import geometries.Sphere;
import renderer.*;
import scene.Scene;

import java.util.List;

public class PinTest {
    private Scene scene = new Scene("Test scene");

    /** Rotates a vector around the x-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.*/

    private Vector RotateX(Vector v, double angle){
        Point3D head = v.getHead();
        double s = Math.sin(angle), c = Math.cos(angle);
        double x = head.getX(), y= head.getY(), z= head.getZ();
        return new Vector(x, y*c - z*s, y*s + z*c);
    }

    /**
     * Rotates a vector around the y-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.*/

    private Vector TurnOnY(Vector v, double angle){
        Point3D head = v.getHead();
        double s = Math.sin(angle), c = Math.cos(angle);
        double x = head.getX(), y= head.getY(), z= head.getZ();
        return new Vector(x*c + z*s, y, -x*s + z*c);
    }

    /**
     * Rotates a vector around the z-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.*/

    private Vector TurnOnZ(Vector v, double angle){
        Point3D head = v.getHead();
        double s = Math.sin(angle), c = Math.cos(angle);
        double x = head.getX(), y= head.getY(), z= head.getZ();
        return new Vector(x*c - y*s, x*s + y*c, z);
    }
    double xAngle = Math.toRadians(-5);//Looking right 5 radians.
    double yAngle = Math.toRadians(20);//Looking 2.4 degrees to the left.
    double zAngle = Math.toRadians(0);//Rotation around z axis is like having ones head stay in place




    Vector toward = TurnOnZ(TurnOnY(RotateX(new Vector(1,0,0), xAngle), yAngle), zAngle);
    Vector up = TurnOnZ(TurnOnY(RotateX(new Vector(0,0,1), xAngle), yAngle), zAngle);




    @Test
    public void pinTest() {
        Camera camera = new Camera(new Point3D(-1800, 0, 100), toward, up) //(-1000,50,740)
                .setViewPlaneSize(150, 150).setDistance(200);//(110)
/*
        Camera camera = new Camera(new Point3D(-1800, -110, 175), toward, up) //(-1000,50,740)
                .setViewPlaneSize(150, 150).setDistance(200);//(110)*/
        /*Camera camera = new Camera(new Point3D(-1000, 50, -150), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(150, 150).setDistance(800);*/

        //here we make our 6npins
        Pin p1 = new Pin(new Point3D(50,50,-150),new Point3D(50,50,-140));
        Pin p2 = new Pin(new Point3D(100,90,-150),new Point3D(100,90,-140));
        Pin p3 = new Pin(new Point3D(100,10,-150),new Point3D(100,10,-140));
        Pin p4 = new Pin(new Point3D(150,130,-150),new Point3D(150,130,-140));
        Pin p5 = new Pin(new Point3D(150,50,-150),new Point3D(150,50,-140));
        Pin p6 = new Pin(new Point3D(150,-30,-150),new Point3D(150,-30,-140));


        //add all sphere to the geometry list
        List<Pin> Plist = List.of(p1,p2,p3,p4,p5,p6);
        for (Pin p:Plist) {
            for (Sphere s : p.list) {
                if (s != null) {
                    scene.geometries.add(s);
                }
            }
        }


        //we make here a parket
        Point3D P1 = new Point3D(200,-50,-150);
        Triangle T1;
        Triangle T2;
        for(int i = 0; i <= 7; i++) {

            T1 = new Triangle(P1,new Point3D(200,-25 + i*25,-150),new Point3D(-1200,P1.getY(),-150));
            T1.setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200).setkR(0.2));

            T2 = new Triangle(new Point3D(-1200,P1.getY(),-150),new Point3D(200,-25 + i*25,-150),new Point3D(-1200,-25 + i*25,-150));
            T2.setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200).setkR(0.2));

            if(i % 2 == 0)
            {
                T1.setEmission(new Color(255,128,0));
                T2.setEmission(new Color(255,128,0));
            }
            else
            {
                T1.setEmission(new Color(java.awt.Color.orange));
                T2.setEmission(new Color(java.awt.Color.orange));
            }

            scene.geometries.add(T1,T2);
            P1 = new Point3D(200,-25 + i*25,-150);

        }


        scene.geometries.add(

                //this are the black barrier's
                new Triangle(new Point3D(-1200,-50,-150),new Point3D(200,-50,-130),new Point3D(200,-50,-150))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200).setkR(0)),

                new Triangle(new Point3D(200,-50,-130),new Point3D(-1200,-50,-150),new Point3D(-1200,-50,-130))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200).setkR(0)),

                new Triangle(new Point3D(200,150,-150),new Point3D(-1200,150,-150),new Point3D(200,150,-130))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200)),

                new Triangle(new Point3D(-1200,150,-150),new Point3D(-1200,150,-130),new Point3D(200,150,-130))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.95).setKs(0.1).setShininess(200)),


                //here are the walls of glass and the flooor
                new Plane(new Point3D(0, 290, -155), new Point3D(1, 290, -3), new Point3D(2, 290, -55))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setkT(0.65).setShininess(40).setkR(0.4)),

                new Plane(new Point3D(0, -170, -155), new Point3D(1, -170, -55), new Point3D(2, -170, -5))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setShininess(40).setkT(0.65).setkR(0.4)),

                new Plane(new Point3D(250,0,0), new Point3D(250,1,6),new Point3D(250,9,1))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setShininess(40).setkT(0.65)),

                new Plane(new Point3D(0, 0, -155), new Point3D(1, 1, -155), new Point3D(2, 0, -155))
                        .setEmission(new Color(java.awt.Color.GRAY))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(20)),


                //this plane repressent the sky
                new Plane(new Point3D(0, 340, -155), new Point3D(1, 340, -3), new Point3D(2, 340, -55))
                        .setEmission(new Color(java.awt.Color.CYAN))
                        .setMaterial(new Material()),

                new Plane(new Point3D(0, -220, -155), new Point3D(1, -220, -3), new Point3D(2, -220, -55))
                        .setEmission(new Color(java.awt.Color.CYAN))
                        .setMaterial(new Material()),

                new Plane(new Point3D(350, 400, -155), new Point3D(350, 30, -3), new Point3D(350, 96, -55))
                        .setEmission(new Color(java.awt.Color.CYAN))
                        .setMaterial(new Material()),


                //here we make the light over the maslul
                new Sphere(30, new Point3D(-1000, 50, 70))
                        .setEmission(new Color(java.awt.Color.BLACK))
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.7)),

                new Sphere(15, new Point3D(-1000, 50, 70))
                        .setEmission(new Color(java.awt.Color.WHITE))
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),

                new Sphere(30, new Point3D(-800, 50, 70)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.7)),
                new Sphere(15, new Point3D(-800, 50, 70)) //
                        .setEmission(new Color(java.awt.Color.WHITE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),

                new Sphere(30, new Point3D(-600, 50, 70)) //
                        .setEmission(new Color(java.awt.Color.BLACK)) //
                        .setMaterial(new Material().setKd(0.4).setKs(0.3).setShininess(100).setkT(0.7)),
                new Sphere(15, new Point3D(-600, 50, 70)) //
                        .setEmission(new Color(java.awt.Color.WHITE)) //
                        .setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(100)),



                //this is the red ball
                new Sphere(20,new Point3D(-1200,50,-129))
                        .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setKs(0.5).setShininess(50).setkR(0.3)));

        //the lights over the maslul
        scene.lights.add( new SpotLight(new Color(1000, 600, 0), new Point3D(-1000, 50, 0),new Vector(0,0,1))
                .setKl(0.0004).setKq(0.0000006));

        scene.lights.add( new SpotLight(new Color(1000, 600, 0), new Point3D(-800, 50, -0),new Vector(0,0,1))
                .setKl(0.0004).setKq(0.0000006));

        scene.lights.add( new SpotLight(new Color(1000, 600, 0), new Point3D(-600, 50, 0),new Vector(0,0,1))
                .setKl(0.0004).setKq(0.0000006));

        scene.lights.add(new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(-125, -125,-190)));

        //spot light that are on the maslul
        for (int i = 0; i <= 11;i++){
            scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(i * -100,-44,-130),new Vector(0,1,-1))
                    .setKl(0.0005).setKq(0.00009));
            scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(i * -100,144,-130),new Vector(0,-1,-1))
                    .setKl(0.0005).setKq(0.00009));
        }

        ImageWriter imageWriter = new ImageWriter("Picture2", 500, 500);
        RenderMultiThread render = new RenderMultiThread() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracerBase(new RayTracerBasic(scene))
                 .setMultithreading(6);

        render.renderImageSuperSampling(3);
        render.renderDepthOfField(camera.getPoint().distance(new Point3D(-1200,50,-129))+30);
        //render.renderImage();
        render.writeToImage();

    }

}