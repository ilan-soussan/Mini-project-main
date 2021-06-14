package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

/**
 *  class that extends lights and implements lightsource and is for an point light on an object .
 *  The constructors get a c color a point and light at the point position.
 *
 *   @author Ilan and didi
 *
 */

public class PointLight extends Light implements LightSource {


    protected Point3D position;
    protected double Kc =1d;
    protected double Kl =0d;
    protected double Kq =0d;
    /**
     * constacor for Light choose the location and color of light
     *
     * @param Intensity
     * @param Position
     */
    public PointLight(Color Intensity, Point3D Position) {
        super(Intensity);
        position = Position;
    }

    /**
     * setter kq
     * @param kq
     * @return pointlight
     */
    public PointLight setKq(double kq) {
        Kq = kq;
        return this;
    }

    /**
     * setter kc
     * @param kc
     * @return pointlight
     */
    public PointLight setKc(double kc) {
        Kc = kc;
        return this;
    }
    /**
     * setter kl
     * @param kl
     * @return pointlight
     */
    public PointLight setKl(double kl) {
        Kl = kl;
        return this;
    }

    /**
     * override for getintensity
     * calculate the dist of a point and the light position
     * and then calculate intensity of this point
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d=position.distance(p);
        return getIntensity().scale(1d/(Kc+Kl*d+Kq*d*d));
    }
    /**
     * override  of getl calculate l of some point and normalize it
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalize();
    }


    /**
     * override of distance between position and some point
     * @param point
     * @return
     */
    @Override
    public double getDistance(Point3D point) {
        return position.distance(point);
    }

}
