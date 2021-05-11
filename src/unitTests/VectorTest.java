package unitTests;

import primitives.Vector;
import primitives.Coordinate;
import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

class VectorTest {

    @org.junit.jupiter.api.Test
    void subtract() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 5, 1);
        Vector v3 = new Vector(0, -3, 2);
        assertEquals(v3,v1.subtract(v2),"ERROR: subtract() wrong value ");
    }

    @org.junit.jupiter.api.Test
    void add() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 5, 1);
        Vector v3 = new Vector(0, 3, -2);
        assertEquals(v2,v1.add(v3),"ERROR: add() wrong value ");
        //assertEquals(0,v1.dotProduct(v2)+28,"ERROR: dotProduct() wrong value");
    }

    @org.junit.jupiter.api.Test
    void scale() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(2, 4, 6);
        Vector test = v1.scale(2);
        //Vector test = v1
        assertTrue((v2.equals(test)),"ERROR: scale() function does not create a new vector");
    }

    @org.junit.jupiter.api.Test
    void dotProduct() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        assertEquals(0,v1.dotProduct(v3),"ERROR: dotProduct() for orthogonal vectors is not zero");
        assertEquals(0,v1.dotProduct(v2)+28,"ERROR: dotProduct() wrong value");

    }

    @org.junit.jupiter.api.Test
    void crossProduct() {

        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(-2, -4, -6);
        Vector v3 = new Vector(0, 3, -2);
        try { // test zero vector
            v1.crossProduct(v2);
            fail("ERROR: crossProduct() for parallel vectors does not throw an exception");
        } catch (Exception e) {}
        Vector vr = v1.crossProduct(v3);
        assertTrue(isZero(vr.length() - v1.length() * v3.length()),"ERROR: crossProduct() wrong result length");
        assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)),"ERROR: crossProduct() result is not orthogonal to its operands");
    }

    @org.junit.jupiter.api.Test
    void determinant() {
        Vector v1 = new Vector(1, 2, 3);
        Vector v2 = new Vector(1, 5, 1);
        Vector v3 = new Vector(0, -3, 2);
        assertEquals(0,v1.Determinant3Vectors(v2,v3),"ERROR: determinant() result isn't correct ");
    }

    @org.junit.jupiter.api.Test
    void lengthSquared() {
        Vector v1 = new Vector(1, 2, 3);
        assertTrue(isZero(v1.lengthSquared() - 14),"ERROR: lengthSquared() wrong value");
    }

    @org.junit.jupiter.api.Test
    void length() {
        Vector v1 = new Vector(0, 3, 4);
        assertEquals(0,v1.length() - 5,"ERROR: length() wrong value");
        }

    @org.junit.jupiter.api.Test
    void normalize() {
        Vector v = new Vector(1, 2, 3);
        Vector vCopy = new Vector(v.getHead());
        Vector vCopyNormalize = vCopy.normalize();
        assertFalse(vCopy != vCopyNormalize,"ERROR: normalize() function creates a new vector");
        assertTrue(isZero(vCopyNormalize.length() - 1),"ERROR: normalize() result is not a unit vector");
    }

    @org.junit.jupiter.api.Test
    void normalized() {
        Vector X = new Vector((double)2/3,(double)2/3,(double)1/3);
        Vector v = new Vector(2, 2, 1);
        Vector u = v.normalized();
        assertTrue(X.equals(u),"ERROR: normalized() function does not create a new vector");
    }
}