package primitives;

public class Material {
    int nShininess =0;
    double kD =0;
    double kS =0;

    public Material setkD(double kD) {
        this.kD = kD;
        return this;

    }

    public Material setkS(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setnShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
