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
        return super.getL(p);
    }

    @Override
    public Color getIntensity(Point3D p) {
        return super.getIntensity(p);
    }
}
