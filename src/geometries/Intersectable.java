package geometries;

import primitives.Point3D;
import primitives.Ray;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public interface Intersectable {

        public static class GeoPoint {
        public Geometry geometry;
        public Point3D point;

        public GeoPoint(Geometry g, Point3D p) {
            point = p;
            geometry = g;
            //don't need to check if the point on the shape?
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint)) return false;
            GeoPoint geoPoint = (GeoPoint) o;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }
    }
    List<GeoPoint> findGeoIntersection(Ray ray);
    default List<Point3D> findIntsersections(Ray ray) {
        var geoList = findGeoIntersection(ray);
        return geoList == null ? null
                : geoList.stream().map(gp -> gp.point).collect(Collectors.toList());
    }
}
