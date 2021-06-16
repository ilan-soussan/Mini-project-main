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
        double x = head.getX(), y= head.getY(), z= head.getZ();
        double s = Math.sin(angle), c = Math.cos(angle);
        return new Vector(x, y*c - z*s, y*s + z*c);
    }

    /**
     * Rotates a vector around the y-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.*/

    private Vector RotateY(Vector v, double angle){
        Point3D head = v.getHead();
        double x = head.getX(), y= head.getY(), z= head.getZ();
        double s = Math.sin(angle), c = Math.cos(angle);
        return new Vector(x*c + z*s, y, -x*s + z*c);
    }

    /**
     * Rotates a vector around the z-axis.
     * @param v The vector to be rotated.
     * @param angle The angle of rotation.
     * @return The resultant vector.*/

    private Vector RotateZ(Vector v, double angle){
        Point3D head = v.getHead();
        double x = head.getX(), y= head.getY(), z= head.getZ();
        double s = Math.sin(angle), c = Math.cos(angle);
        return new Vector(x*c - y*s, x*s + y*c, z);
    }
    double xAngle = Math.toRadians(0);//Looking down 4.5 degrees.
    double yAngle = Math.toRadians(10);//Looking 8 degrees to the left.(70)
    double zAngle = Math.toRadians(0);//Rotation around z axis is like having ones head stay in place ,




    /*
    double xAngle = Math.toRadians(-5);//Looking down 4.5 degrees.
    double yAngle = Math.toRadians(23);//Looking 8 degrees to the left.(70)
    double zAngle = Math.toRadians(0);//Rotation around z axis is like having ones head stay in place ,
    // and spin his legs around him without turning him away from what hes looking at.
*/
    Vector toward = RotateZ(RotateY(RotateX(new Vector(1,0,0), xAngle), yAngle), zAngle);
    Vector up = RotateZ(RotateY(RotateX(new Vector(0,0,1), xAngle), yAngle), zAngle);




    @Test
    public void pinTest() {
        Camera camera = new Camera(new Point3D(-1500, 50, -50), toward, up) //(-1000,50,740)
                .setViewPlaneSize(150, 150).setDistance(110);//(110)
/*
        Camera camera = new Camera(new Point3D(-1800, -110, 175), toward, up) //(-1000,50,740)
                .setViewPlaneSize(150, 150).setDistance(200);//(110)*/
        /*Camera camera = new Camera(new Point3D(-1000, 50, -150), new Vector(1, 0, 0), new Vector(0, 0, 1)) //
                .setViewPlaneSize(150, 150).setDistance(800);*/

        /*scene.setAmbientLight(new AmbientLight(new Color(java.awt.Color.BLACK), 0.30));
        Pin p1 = new Pin(new Point3D(50,50,-150),new Point3D(50,50,-130));
        Pin p2 = new Pin(new Point3D(100,90,-150),new Point3D(100,90,-130));
        Pin p3 = new Pin(new Point3D(100,10,-150),new Point3D(100,10,-130));
        Pin p4 = new Pin(new Point3D(150,130,-150),new Point3D(150,130,-130));
        Pin p5 = new Pin(new Point3D(150,50,-150),new Point3D(150,50,-130));
        Pin p6 = new Pin(new Point3D(150,-30,-150),new Point3D(150,-30,-130));*/

        Pin p1 = new Pin(new Point3D(50,50,-150),new Point3D(50,50,-140));
        Pin p2 = new Pin(new Point3D(100,90,-150),new Point3D(100,90,-140));
        Pin p3 = new Pin(new Point3D(100,10,-150),new Point3D(100,10,-140));
        Pin p4 = new Pin(new Point3D(150,130,-150),new Point3D(150,130,-140));
        Pin p5 = new Pin(new Point3D(150,50,-150),new Point3D(150,50,-140));
        Pin p6 = new Pin(new Point3D(150,-30,-150),new Point3D(150,-30,-140));


        List<Pin> Plist = List.of(p1,p2,p3,p4,p5,p6);
        for (Pin p:Plist) {
            for (Sphere s : p.list) {
                if (s != null) {
                    scene.geometries.add(s);
                }
            }
        }


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



                new Plane(new Point3D(0, 340, -155), new Point3D(1, 340, -3), new Point3D(2, 340, -55))
                        .setEmission(new Color(java.awt.Color.CYAN))
                        .setMaterial(new Material()),

                new Plane(new Point3D(0, -220, -155), new Point3D(1, -220, -3), new Point3D(2, -220, -55))
                        .setEmission(new Color(java.awt.Color.CYAN))
                        .setMaterial(new Material()),

                new Plane(new Point3D(350, 400, -155), new Point3D(350, 30, -3), new Point3D(350, 96, -55))
                        .setEmission(new Color(java.awt.Color.CYAN))
                        .setMaterial(new Material()),



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




                new Sphere(20,new Point3D(-1200,50,-129))
                        .setEmission(new Color(java.awt.Color.RED))
                        .setMaterial(new Material().setKs(0.5).setShininess(50).setkR(0.3)));
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


        scene.lights.add( new SpotLight(new Color(1000, 600, 0), new Point3D(-1000, 50, 0),new Vector(0,0,1))
                .setKl(0.0004).setKq(0.0000006));

        scene.lights.add( new SpotLight(new Color(1000, 600, 0), new Point3D(-800, 50, -0),new Vector(0,0,1))
                .setKl(0.0004).setKq(0.0000006));

        scene.lights.add( new SpotLight(new Color(1000, 600, 0), new Point3D(-600, 50, 0),new Vector(0,0,1))
                .setKl(0.0004).setKq(0.0000006));

        scene.lights.add(new DirectionalLight(new Color(java.awt.Color.WHITE), new Vector(-125, -125,-190)));

        for (int i = 0; i <= 11;i++){
            scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(i * -100,-44,-130),new Vector(0,1,-1))
                    .setKl(0.0001).setKq(0.00005));
            scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(i * -100,144,-130),new Vector(0,-1,-1))
                    .setKl(0.0001).setKq(0.00005));
        }

        /*scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(0,0,-130),new Vector(0,1,-1))
                .setKl(0.0001).setKq(0.0000005));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(-100,0,-130),new Vector(0,1,-1))
                .setKl(0.0001).setKq(0.0000005));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(-200,0,-130),new Vector(0,1,-1))
                .setKl(0.0001).setKq(0.0000005));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(-300,0,-130),new Vector(0,1,-1))
                .setKl(0.0001).setKq(0.0000005));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(-400,0,-130),new Vector(0,1,-1))
                .setKl(0.0001).setKq(0.0000005));
        scene.lights.add(new SpotLight(new Color(java.awt.Color.BLUE), new Point3D(-500,0,-130),new Vector(0,1,-1))
                .setKl(0.0001).setKq(0.0000005));*/

        ImageWriter imageWriter = new ImageWriter("Picture1", 1000, 1000);
        RenderMultiThread render = new RenderMultiThread() //
                .setImageWriter(imageWriter) //
                .setCamera(camera) //
                .setRayTracerBase(new RayTracerBasic(scene))
                .setMultithreading(3)
                ;


        render.renderImage();
        //render.renderImageSuperSampling(3);
        //render.renderImage();
        render.renderDepthOfField(camera.getPoint().distance(new Point3D(-1200,50,-129))+20);
       // render.renderImage();
        render.writeToImage();

    }

}




/*Point3D P1 = new Point3D(200,-50,-150);
        Point3D P2 = new Point3D(-1200,-50,150);
        for (int i = 0; i <= 7; i++)
        {
            scene.geometries.add (
            new Triangle(P1,new Point3D(-1200,P1.getY(),-150),new Point3D(200,-25 + i*25,-150))
            .setEmission(new Color(102,51,0))
            .setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200).setkR(0.5)),

            new Triangle(P2,new Point3D(-1200,-25 + i*25,-150),new Point3D(200,-25 + i*25,-150))
            .setEmission(new Color(java.awt.Color.ORANGE))
            .setMaterial(new Material().setKd(0.95).setKs(0.5).setShininess(200).setkR(0.5)));
            P1 = new Point3D(200,-25 + i*25,-150);
            P2 = new Point3D(-1200,-25 + i*25,-150);




        }
*/