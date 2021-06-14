package elements;

import primitives.Color;

/**
 * Light abstract class that lights source will extends
 * @params intensity
 * all lights will have intencity (a colour)
 */
abstract class Light {
private Color intensity;

    /**
     * constacor for Light
     * @param Intensity
     */
    protected Light(Color Intensity)
{
    intensity = Intensity;
}

    /**
     * getter of intencity
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
