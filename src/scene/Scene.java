package scene;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * **
 * Scene class.
 * Represented by lists of Geometries and LightSources.
 * has a name, background color and a ambientLight.
 *
 * @author Ilan and didi
 *
 */
public class Scene {
    public String name = new String();
    public Color background = new Color(Color.BLACK);
    public AmbientLight ambientLight = new AmbientLight();
    public Geometries geometries = new Geometries();
    public List<LightSource> lights;

    /**
     * constructor for Secne. gets only the name and all the others are Default values.
     * @param Name
     */
    public Scene(String Name)
    {
        name = Name;
        background = new Color(Color.BLACK);
        ambientLight = new AmbientLight();
        geometries = new Geometries();
        lights = new LinkedList<LightSource>();
    }

    /*

Builder Design seters
set the value and return the class object itself


*/

    /**
     * set ambientLight
     * @param ambientLight
     * @return the class object itself
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }

    /**
     * set the list Lights
     * @param Lights (list)
     * @return the class object itself
     */
    public Scene setLight(List<LightSource> Lights) {
        this.lights = Lights;
        return this;
    }

    /**
     * set background
     * @param background
     * @return the class object itself
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * set geometries
     * @param geometries
     * @return the class object itself
     */
    public Scene setGeometries(Geometries geometries) {
        this.geometries = geometries;
        return this;
    }

    /**
     * set name
     * @param name
     * @return the class object itself
     */
    public Scene setName(String name) {
        this.name = name;
        return this;
    }
}
