package elements;

import primitives.Color;

/**
 *   class that extends lights and is for an ambient light on an object .The constructors make the default color as black
 *   or a color we choose scale a const K.
 *
 *   @author Ilan and didi
 *
 */
public class AmbientLight extends Light {

    public AmbientLight(){
        super(Color.BLACK);
    }

    /**
     * constructor like with explain below
     * @param Ia
     * @param Ka
     */
    public AmbientLight(Color Ia,double Ka){
        super(Ia.scale(Ka));

    }


}
