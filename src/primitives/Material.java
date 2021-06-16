package primitives;


/**
 * Material class.
 * For each of the geometries in our scene
 * there is a material variable of the material that is responsible for
 * diagnosing the shape and understanding its material
 * Transparency / reflection / blockages / mirroring
 *
 *
 *
 * @author Ilan and didi
 */
public class Material {
    public int nShininess =0; // Shininess
    public double kD =0d; // the surface diffuse reflectivity
    public double kS =0d; // the surface specular reflectivity
    public double kT =0d; // the surface mirroring
    public double kR =0d; // the surface Transparency




    /*

    Builder Design seters
    set the value and return the class object itself


    */

    /**
     * set Kr and return the object itself
     * @param kR
     * @return this
     */
    public Material setkR(double kR) {
        this.kR = kR;
        return this;

    }


    /**
     * set kt and return the object itself
     * @param kT
     * @return this
     */
    public Material setkT(double kT) {
        this.kT = kT;
        return this;

    }


    /**
     * set Kd and return the object itself
     * @param kD
     * @return this
     */
    public Material setKd(double kD) {
        this.kD = kD;
        return this;

    }


    /**
     * set ks and return the object itself
     * @param kS
     * @return this
     */
    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }


    /**
     * setShininess and return the object itself
     * @param nShininess
     * @return this
     */
    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }



    //getrs

    /**
     *
     * @return kT
     */
    public double getkT() {
        return kT;
    }

    /**
     *
     * @return kR
     */
    public double getkR() {
        return kR;
    }
    /**
     *
     * @return kD
     */
    public double getKd() {
        return kD;
    }
    /**
     *
     * @return kS
     */
    public double getKs() {
        return kS;
    }
    /**
     *
     * @return Shininess
     */
    public int getShininess() {
        return nShininess;
    }
}
