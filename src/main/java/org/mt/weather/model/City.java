package org.mt.weather.model;

import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;

import com.google.common.base.MoreObjects;

public class City {

    /** 3 letter code */
    public String code;

    @GeoSpatialIndexed
    private Point location;

    public static City ofCoordinates(String code, double lat, double lon) {
	return new City(code, new Point(lat, lon));
    }

    protected City() {
    }

    protected City(String code, Point location) {
	this.code = code;
	this.location = location;
    }

    public String getCode() {
	return code;
    }

    public void setCode(String code) {
	this.code = code;
    }

    public Point getLocation() {
	return location;
    }

    public void setLocation(GeoJsonPoint location) {
	this.location = location;
    }

    @Override
    public String toString() {
	return MoreObjects.toStringHelper(this)
		.add("code", code)
		.add("location", location)
		.toString();
    }
}
