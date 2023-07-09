package com.uniproject.model;

// Data model of a coordinate
public class Coordinate {
    final float latitude;
    final float longitude;

    // Overrides the to String method,
    // for converting lists of coordinates to javascript compatible code
    @Override
    public String toString() {
        return "[" + longitude + "," + latitude + "]";
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }

    public Coordinate(float longitude, float latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
