package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {
    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point3D point);

    public Color getEmission() {
        return emission;
    }

    public void setEmission(Color emission) {
        this.emission = emission;
    }
}
