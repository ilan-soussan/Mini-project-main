package geometries;

import primitives.*;


/**
 * an abstract class that implements from intersectable and have a color and a variabble of material of this shape
 * its a simple class that define some shapen with clours and material
 */
public abstract class Geometry implements Intersectable {
    protected Color emission = Color.BLACK; //default color
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
