package scene;

import elements.AmbientLight;
import geometries.Geometries;
import primitives.Color;

public class Scene {
    public String name = new String();
    public Color background = new Color(Color.BLACK);
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();


    public Scene(String Name)
    {
        name = Name;
        background = new Color(Color.BLACK);
        ambientLight = new AmbientLight();
        geometries = new Geometries();
    }

    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    public Scene setName(String name) {
        this.name = name;
        return this;
    }
}
