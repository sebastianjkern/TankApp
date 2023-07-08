package com.uniproject.model;

public class Coordinate {
    float latitude;
    float longitude;

    @Override
    public String toString() {
        return "[" + longitude + "," + latitude + "]";
    }

    public Coordinate(float longitude, float latitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
