package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * class that extends lights and implements lightsource and is for an directional light on an object .
 *    The constructors get a color a vector and light at the vector direction.
 *
 *    @author Ilan and didi
 */
public class DirectionalLight extends Light implements LightSource {

    private Vector direction;

    /**
     * constacor for Light
     * use the constractor of his father
     * @param Intensity
     * @param Direction
     */
    public DirectionalLight(Color Intensity,Vector Direction ) {
        super(Intensity);
        direction = Direction;
    }


    /**
     * override of a getter for intensity
     * @param p
     * @return
     */
    @Override
    public Color getIntensity(Point3D p) {
        return getIntensity();
    }

    /**
     * override of getter of L
     * @param p
     * @return
     */
    @Override
    public Vector getL(Point3D p) {
        return direction.normalize(); }


    /**
     * override of getdisance
     * @param point
     * @return infenity distance
     */
    @Override
    public double getDistance(Point3D point) {
        return  Double.POSITIVE_INFINITY;
    }
}
