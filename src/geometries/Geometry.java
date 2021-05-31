package geometries;

import primitives.*;

public abstract class Geometry implements Intersectable {
    protected Color emission = Color.BLACK;
    public abstract Vector getNormal(Point3D point);
Material material = new Material();

    public Color getEmission() {
        return emission;
    }

    public Geometry setEmission(Color emission) {
        this.emission = emission;
        return this;
    }

    public Geometry setMaterial(Material material) {
        this.material = material;
        return this;
    }

    public Material getMaterial() {
        return material;
    }
}
