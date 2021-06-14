package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 *   class that extends lights and is for an spot light on an object .
 *  The constructors get a c color a point and a vector and will make the light at the vector direction and point position.
 *
 *   @author Ilan and didi
 *
 */

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


    /**
     * override getter
     * @param p
     * @return L of pointlight normelize with point p
     */
    @Override
    public Vector getL(Point3D p) {
return super.getL(p).normalize();
    }


    /**
     * override getter for intensity
     * @param p
     * @return the color of intensity at point p
     */
    @Override
    public Color getIntensity(Point3D p) {

        double d = position.distance(p);
        return super.getIntensity(p).scale(Math.max(0,(direction.normalize()).dotProduct(getL(p).normalize())));
    }
}
