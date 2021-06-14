package elements;

import primitives.Color;
import primitives.Point3D;
import primitives.Vector;

/**
 * an public interface that all lights will use.
 * this interface contain a getter of intensity and L and distance
 */

public interface LightSource {

    /**
     * a declarcion of a func that tell us the intencity of a light at some point
     * @param p
     * @return
     */
    Color getIntensity(Point3D p);

    /**
     *a declarcion of a func that tell us the vec between light and object of a light at some point
     * @param p
     * @return
     */
    Vector getL(Point3D p);

    /**
     * getter of dist 2 points
     * @param point
     * @return
     */
    double getDistance(Point3D point);
}
