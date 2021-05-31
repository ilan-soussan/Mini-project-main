package elements;

import primitives.Color;

/**
 * Light abstract class
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
     *
     * @return intensity
     */
    public Color getIntensity() {
        return intensity;
    }
}
