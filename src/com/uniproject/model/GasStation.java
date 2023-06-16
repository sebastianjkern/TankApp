package com.uniproject.model;

public class GasStation {
    public Price price;
    public String name;
    public Float longitude;
    public Float latitude;

    public GasStation(Price price, String name, Float longitude, Float latitude) {
        this.price = price;
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
    }
}
