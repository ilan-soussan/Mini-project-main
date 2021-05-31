package primitives;

public class Material {
    int nShininess =0;
    double kD =0;
    double kS =0;

    public Material setKd(double kD) {
        this.kD = kD;
        return this;

    }

    public Material setKs(double kS) {
        this.kS = kS;
        return this;
    }

    public Material setShininess(int nShininess) {
        this.nShininess = nShininess;
        return this;
    }
}
