/*package renderer;

import elements.Camera;
import geometries.Intersectable;
import primitives.Color;
import primitives.Ray;

import java.util.MissingResourceException;

public class Render {
    private ImageWriter imageWriter;
    private Camera camera;
    public RayTracerBase rayTracerBase;


    public RayTracerBase getRayTracerBase() {
        return rayTracerBase;
    }

    public Camera getCamera() {
        return camera;
    }

    public ImageWriter getImageWriter() {
        return imageWriter;
    }
    */


    /**
     * constructor
     * @param rayTracerBase
     * @return
     */
/*
    public Render setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }



//set
    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }
//set
    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }


 */
    /**
     * render the image and put in each pixsel his color.
     */
    /*
    public void renderImage() {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        if (camera == null)
            throw new MissingResourceException("camera is null", "Render", "Camera");
        if (rayTracerBase == null)
            throw new MissingResourceException("rayTracerBase is null", "Render", "rayTracerBase");



        int x = imageWriter.getNx();
        int y = imageWriter.getNy();


        for (int i=0;i<x;i++)
        {
            for (int j =0;j<y;++j)
            {
                Ray pixelRay = camera.constructRayThroughPixel(x,y,i,j);
                Color pixelColor = rayTracerBase.traceRay(pixelRay);
                imageWriter.writePixel(i,j,pixelColor);
            }
        }

    }


     */
    /**
     * renderImageSuperSampling.
     * For each pixel, check the average of all the colors of the pixels closest to it
     * and set for it their average color.
     * Which gives a whole effect of inaccurate cuts and more real and faithful to reality.
     *
     *
     * @param numOfRays
     */
    /*
    public void renderImageSuperSampling(int numOfRays) {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        if (camera == null)
            throw new MissingResourceException("camera is null", "Render", "Camera");
        if (rayTracerBase == null)
            throw new MissingResourceException("rayTracerBase is null", "Render", "rayTracerBase");
        int x = imageWriter.getNx();
        int y = imageWriter.getNy();


        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; ++j) {
                Color pixelColor = Color.BLACK;
                for (int k = -1 * numOfRays / 2; k < numOfRays / 2 + 1; ++k) {
                    for (int f = -1 * numOfRays / 2; f < numOfRays / 2 + 1; ++f) {

                        Ray pixelRay = camera.constructRayThroughPixelSuperSampling(x, y, i, j, k, f, numOfRays);

                        pixelColor = pixelColor.add(rayTracerBase.traceRay(pixelRay));
                    }
                }
                if (numOfRays % 2 == 0)
                    pixelColor = pixelColor.reduce(numOfRays * numOfRays + numOfRays * 2);
                else
                    pixelColor = pixelColor.reduce(numOfRays * numOfRays);

                imageWriter.writePixel(i, j, pixelColor);
            }
        }
    }


     */

    /**
     *
     * renderDepthOfField,
     * render Depth Of Field
     * Takes the color of each and every pixel ** after rendering **
     * and what is not in our focus range we blur by turning its color into its Color * 15 times and
     * to his 9 closest Neighbors.
     * @param depthOfFieldNum
     */
    /*
    public void renderDepthOfField(double depthOfFieldNum) {

        int x = imageWriter.getNx();
        int y = imageWriter.getNy();


        for (int i = 0; i < x; i++)
            for (int j = 0; j < y; j++) {
                Ray pixelRay = camera.constructRayThroughPixel(x, y, i, j);
                Intersectable.GeoPoint point = rayTracerBase.findClosestIntersection(pixelRay);
                if (point != null) {
                    Color pixelColor = imageWriter.getPixel(i,j);
                    Color orignalPixelColor = pixelColor;
                    pixelColor = pixelColor.scale(20);
                    double dis = point.point.distance(camera.getPoint());
                    if (dis < depthOfFieldNum - 100 || dis > depthOfFieldNum + 100) {
                        double tempDis = Math.abs(dis - depthOfFieldNum) - 100;
                        double tempReversDis = 0;
                        if (tempDis < 200) {
                            tempReversDis = (1 / (tempDis / 200));
                            pixelColor = pixelColor.add(orignalPixelColor.scale(tempReversDis));
                        }
                        for (int k = i - 1; k < i + 2; ++k) {
                            for (int f = j - 1; f < j + 2; f++) {
                                Ray tempPixelRay = camera.constructRayThroughPixel(x, y, k, f);
                                pixelColor = pixelColor.add(rayTracerBase.traceRay(tempPixelRay));
                            }
                        }
                        pixelColor = pixelColor.reduce(9 + tempReversDis+20);
                        imageWriter.writePixel(i, j, pixelColor);
                    }
                }
            }
    }

     */

    /**
     * Create a grid [over the picture] in the pixel color map. given the grid's
     * step and color.
     *
     * @param interval  grid's step
     * @param color grid's color
     */
    /*
    public void printGrid(int interval, Color color)
    {

        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        int x = imageWriter.getNx();
        int y = imageWriter.getNy();


        for (int i=0;i<x;i++)
        {
            for (int j =0;j<y;++j)
            {
             if(i%interval ==0||j%interval ==0)
                 imageWriter.writePixel(i,j,color);
            }
        }
    }

     */

    /**
     * write To Image
     */
    /*
    public void writeToImage()
    {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        imageWriter.writeToImage();
    }



}


     */