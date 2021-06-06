package primitives;

public class Material {
    public int nShininess =0;
   public double kD =0d;
    public double kS =0d;
    public   double kT =0d;
    public   double kR =0d;

    public Material setkR(double kR) {
        this.kR = kR;
        return this;

    }

    public Material setkT(double kT) {
        this.kT = kT;
        return this;

    }

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

    public double getKd() {
        return kD;
    }

    public double getKs() {
        return kS;
    }

    public int getShininess() {
        return nShininess;
    }
}
