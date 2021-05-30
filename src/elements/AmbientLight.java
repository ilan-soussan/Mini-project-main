package elements;

import primitives.Color;

public class AmbientLight {

    Color intensity;
    public AmbientLight(){
        intensity = Color.BLACK;
    }

    public AmbientLight(Color Ia,double Ka){
        intensity = Ia.scale(Ka);
    }

    public Color getIntensity() {
        return intensity;
    }

}
