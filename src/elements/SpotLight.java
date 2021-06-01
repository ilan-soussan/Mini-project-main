package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

public class SpotLight extends PointLight {


    private Vector direction;
    /**
     * constacor for Light
     *
     * @param Intensity
     * @param Position
     */
    public SpotLight(Color Intensity, Point3D Position,Vector Direction) {
        super(Intensity, Position);
        direction = Direction;
    }

    @Override
    public Vector getL(Point3D p) {
return super.getL(p).normalize();
    }

    @Override
    public Color getIntensity(Point3D p) {

        double d = position.distance(p);
        return super.getIntensity(p).scale(Math.max(0,(direction.normalize()).dotProduct(getL(p).normalize())));
        //Vector v = position.subtract(p).normalize();
        //Color intensityL = getIntensity().scale(Math.max((1/(Kc+Kl*d+Kq*d*d)),direction.dotProduct(v)));
        //return intensityL.scale(1d/(Kc+Kl*d+Kq*d*d));
    }
}
