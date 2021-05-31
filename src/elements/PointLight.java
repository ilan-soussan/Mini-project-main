package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Util;
import primitives.Vector;

public class PointLight extends Light implements LightSource {


    private Point3D position;
    private double Kc =1d;
    private double Kl =0d;
    private double Kq =0d;
    Color intensityL;
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
        return intensityL;
    }

    /**
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        Double d = position.distance(p);
        intensityL = getIntensity().scale(1/(Kc+Kl*d+Kq*d*d));
        Vector v = position.subtract(p);
        return  v;
    }
}
