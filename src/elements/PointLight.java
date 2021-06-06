package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class PointLight extends Light implements LightSource {


    protected Point3D position;
    protected double Kc =1d;
    protected double Kl =0d;
    protected double Kq =0d;
    /**
     * constacor for Light
     *
     * @param Intensity
     */
    public PointLight(Color Intensity, Point3D Position) {
        super(Intensity);
        position = Position;
    }

    public PointLight setKq(double kq) {
        Kq = kq;
        return this;
    }

    public PointLight setKc(double kc) {
        Kc = kc;
        return this;
    }

    public PointLight setKl(double kl) {
        Kl = kl;
        return this;
    }

    /**
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        double d=position.distance(p);
        return getIntensity().scale(1d/(Kc+Kl*d+Kq*d*d));
    }
    /**
     *
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return p.subtract(position).normalize();
    }

    @Override
    public double getDistance(Point3D point) {
        return position.distance(point);
    }

}
