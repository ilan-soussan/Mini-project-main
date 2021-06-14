package renderer;

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

    public Render setRayTracerBase(RayTracerBase rayTracerBase) {
        this.rayTracerBase = rayTracerBase;
        return this;
    }


    public Render setCamera(Camera camera) {
        this.camera = camera;
        return this;
    }

    public Render setImageWriter(ImageWriter imageWriter) {
        this.imageWriter = imageWriter;
        return this;
    }

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
    public void renderImage(boolean DepthOfField,double depthOfFieldNum) {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        if (camera == null)
            throw new MissingResourceException("camera is null", "Render", "Camera");
        if (rayTracerBase == null)
            throw new MissingResourceException("rayTracerBase is null", "Render", "rayTracerBase");
        if(!DepthOfField)
        {
            renderImage();
            return;
        }

        int x = imageWriter.getNx();
        int y = imageWriter.getNy();


        for (int i=0;i<x;i++)
        {
            for (int j =0;j<y;++j)
            {
                Ray pixelRay = camera.constructRayThroughPixel(x,y,i,j);
                Color pixelColor = rayTracerBase.traceRay(pixelRay);

                Intersectable.GeoPoint point = rayTracerBase.findClosestIntersection(pixelRay);
                if (point != null)
                {
                    double dis = point.point.distance(camera.getPoint());
                    if(dis<depthOfFieldNum-20||dis>depthOfFieldNum+20)
                    {
                        double tempDis = Math.abs(dis-depthOfFieldNum)-20;
                        double tempReversDis =0;
                        if(tempDis<20)
                        {
                            tempReversDis = (1/(tempDis/20));
                            pixelColor = pixelColor.scale(tempReversDis);
                        }
                        for (int k=i-1;k<i+2&&k<x&&k>0;++k)
                        {
                            for(int f = j-1;f<j+2&&f<y&&f>0;f++)
                            {
                                Ray tempPixelRay = camera.constructRayThroughPixel(x,y,k,f);
                                pixelColor = pixelColor.add(rayTracerBase.traceRay(tempPixelRay));
                            }
                        }
                        pixelColor=pixelColor.reduce(9+tempReversDis);
                    }
                }
                imageWriter.writePixel(i,j,pixelColor);
            }
        }

    }


    public void renderImageSuperSampling(int numOfRays,boolean DepthOfField,double depthOfFieldNum) {
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


                if (DepthOfField) {
                    Ray pixelRay = camera.constructRayThroughPixel(x, y, i, j);
                    Intersectable.GeoPoint point = rayTracerBase.findClosestIntersection(pixelRay);
                    if (point != null) {
                        double dis = point.point.distance(camera.getPoint());
                        if (dis < depthOfFieldNum - 20 || dis > depthOfFieldNum + 20) {
                            double tempDis = Math.abs(dis - depthOfFieldNum) - 20;
                            double tempReversDis = 0;
                            if (tempDis < 20) {
                                tempReversDis = (1 / (tempDis / 20));
                                pixelColor = pixelColor.scale(tempReversDis);
                            }
                            for (int k = i - 1; k < i + 2 && k < x && k > 0; ++k) {
                                for (int f = j - 1; f < j + 2 && f < y && f > 0; f++) {
                                    Ray tempPixelRay = camera.constructRayThroughPixel(x, y, k, f);
                                    pixelColor = pixelColor.add(rayTracerBase.traceRay(tempPixelRay));
                                }
                            }
                            pixelColor = pixelColor.reduce(9 + tempReversDis);

                        }
                    }
                }
                imageWriter.writePixel(i, j, pixelColor);
            }
        }
    }


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
    public void writeToImage()
    {
        if (imageWriter == null)
            throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
        imageWriter.writeToImage();
    }



}
