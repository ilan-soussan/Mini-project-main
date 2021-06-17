package geometries;

import primitives.Point3D;
import primitives.Ray;

/**
 * Boundig box class we will use this class ro create a bounding box foe each shape
 *
 *   @author Ilan and didi
 */
class Box {
    Point3D pMin,pMax; //two points in the end of the imageniry cube

    public Point3D getP1() {
        return pMin;
    }

    public Point3D getP2() {
        return pMax;
    }

    public Box(Point3D P1, Point3D P2) //constractor
    {
        pMin = P1;
        pMax = P2;
    }

    /**
     * @param ray
     * @return boolean
     * this func check if there is intersections with the bounding box
     * we will check when the ray is in our bounding box area by checcking the min/max tx,ty,tz
     * and then check if the ray is in all of those areas
     */
    public boolean Interact(Ray ray) {
        //next 3 if cause cant devide by zero so we assume there is intersctionl cause we dont have choice
        if(ray.getRayDir().getHead().getX() == 0)
            return true;
        if(ray.getRayDir().getHead().getY() == 0)
            return true;
        if(ray.getRayDir().getHead().getZ() == 0)
            return true;
        ray.getRayDir().normalize(); //normelize our ray for the calculate
        //the points needs to start at 0,0,0
        pMin = pMin.add(new Point3D(0,0,0).subtract(ray.getRayPoint()));
        pMax = pMax.add(new Point3D(0,0,0).subtract(ray.getRayPoint()));
        double TxMax,TxMin,TyMax,TyMin,TzMax,TzMin;
        //here we will calculate the t we need to scale so the ray will pass in each area of the graph
        // also we will check if the scale is under 0 if thats the case we need to scale by -1


        TxMin = pMin.getX()/ray.getRayDir().getHead().getX();
        TxMax = pMax.getX()/ray.getRayDir().getHead().getX();
        TyMin = pMin.getY()/ray.getRayDir().getHead().getY();
        TyMax = pMax.getY()/ray.getRayDir().getHead().getY();
        TzMin = pMin.getZ()/ray.getRayDir().getHead().getZ();
        TzMax = pMax.getZ()/ray.getRayDir().getHead().getZ();

        if(TxMin > TxMax)
        {
            double t =TxMin;
            TxMin = TxMax;
            TxMax = t;
        }
        if(TyMin > TyMax)
        {
            double t =TyMin;
            TyMin = TyMax;
            TyMax = t;
        }
        if(TzMin > TzMax)
        {
            double t =TzMin;
            TzMin = TzMax;
            TzMax = t;
        }

/*        if(TxMin<0)
            TxMin *= -1;
        if(TxMax<0)
            TxMax *= -1;
        if(TyMin<0)
            TyMin *= -1;
        if(TyMax<0)
            TyMax *= -1;
        if(TzMax<0)
            TzMax *= -1;
        if (TzMin<0)
            TzMin *=-1;*/



        //here we compare between all of our ts to see the ray in the area of the box
       if((TxMax < TyMin || TxMax < TzMin))
           return false;
       if ((TxMin > TyMax || TxMin > TzMax))
           return false;
       if ((TyMin > TxMax || TyMin > TzMax))
           return false;
       if(TyMax < TxMin || TyMax < TzMin)
           return false;
       if (TzMin > TxMax || TzMin > TyMax)
           return false;
       if(TzMax < TxMin && TzMax < TyMin)
            return false;
        return true;

    }

    /**
     * @param b
     * this function choose between the minimal and maximal coordinates of the two item we put together
     * and then put them in pmax and pmin
     */
    public void addBox(Box b){
        if(this.pMax == null && this.pMin == null)
        {
            this.pMin = b.pMin;
            this.pMax = b.pMax;
        }
        if (b != null ) {
            pMin = new Point3D(
                    Math.min(pMin.getX(), b.pMin.getX()),
                    Math.min(pMin.getY(), b.pMin.getY()),
                    Math.min(pMin.getZ(), b.pMin.getZ()));
            pMax = new Point3D(
                    Math.max(pMax.getX(), b.pMax.getX()),
                    Math.max(pMax.getY(), b.pMax.getY()),
                    Math.max(pMax.getZ(), b.pMax.getZ()));
        }


    }

}




