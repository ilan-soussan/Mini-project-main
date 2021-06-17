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

    public Box(){ //default constructor for a box
        pMin = new Point3D(0,0,0);
        pMax = new Point3D(0,0,0);
    }

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
        /*if(TxMin < 0)
            TxMin *= -1;*/
        TxMax = pMax.getX()/ray.getRayDir().getHead().getX();
        /*if(TxMax < 0)
            TxMax *= -1;*/
        TyMin = pMin.getY()/ray.getRayDir().getHead().getY();
        /*if(TyMin < 0)
            TyMin *= -1;*/
        TyMax = pMax.getY()/ray.getRayDir().getHead().getY();
        /*if(TyMax < 0)
            TyMax *= -1;*/
        TzMin = pMin.getZ()/ray.getRayDir().getHead().getZ();
        /*if(TzMin < 0)
            TzMin *= -1;*/
        TzMax = pMax.getZ()/ray.getRayDir().getHead().getZ();
        /*if(TzMax < 0)
            TzMax *= -1;*/

        //here we compare between all of our ts to see the ray in the area of the box
        if((TxMin < TyMax && TxMin < TzMax) || (TyMin < TxMax && TyMin < TzMax) ||(TzMin < TxMax && TzMin < TyMax))
            return true;
        else if((TxMax > TyMin && TxMax > TzMin) || (TyMax > TxMin && TyMax > TzMin) || (TzMax > TxMin && TzMax > TyMin))
            return true;
        else
            return false;
    }

    /**
     * @param b
     * this function choose between the minimal and maximal coordinates of the two item we put together
     * and then put them in pmax and pmin
     */
    public void add(Box b){
        if (b != null ) {
            pMin = new Point3D(
                    Math.min(pMin.getX(), b.pMin.getX()),
                    Math.min(pMin.getY(), b.pMin.getY()),
                    Math.min(pMin.getZ(), b.pMin.getZ()));
            pMax = new Point3D(
                    Math.min(pMax.getX(), b.pMax.getX()),
                    Math.min(pMax.getY(), b.pMax.getY()),
                    Math.min(pMax.getZ(), b.pMax.getZ()));
        }
    }

}
