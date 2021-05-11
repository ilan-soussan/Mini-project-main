package geometries;
import primitives.*;

public class Cyilnder extends Tube {
    double height;

    @Override
    public Vector getNormal(Point3D point) {
        return super.getNormal(point);
    }

    public double getHeight() {
        return height;
    }
}
