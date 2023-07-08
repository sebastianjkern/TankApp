package com.uniproject.model;

public class GasStation {
    private String id;
    private String name;
    private String brand;
    private String street;
    private String place;
    private float lat;
    private float lng;
    private float dist;
    private float diesel;
    private float e5;
    private float e10;
    private boolean isOpen;
    private String houseNumber;
    private int postCode;

    public GasStation(String id, String name, String brand, String street, String place, float lat, float lng, float dist,
                      float diesel, float e5, float e10, boolean isOpen, String houseNumber, int postCode) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.street = street;
        this.place = place;
        this.lat = lat;
        this.lng = lng;
        this.dist = dist;
        this.diesel = diesel;
        this.e5 = e5;
        this.e10 = e10;
        this.isOpen = isOpen;
        this.houseNumber = houseNumber;
        this.postCode = postCode;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBrand() {
        return brand;
    }

    public String getStreet() {
        return street;
    }

    public String getPlace() {
        return place;
    }

    public float getLat() {
        return lat;
    }

    public float getLng() {
        return lng;
    }

    public float getDist() {
        return dist;
    }

    public float getDiesel() {
        return diesel;
    }

    public float getE5() {
        return e5;
    }

    public float getE10() {
        return e10;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public int getPostCode() {
        return postCode;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public void setLng(float lng) {
        this.lng = lng;
    }

    public Coordinate getCoordinate() {
        return new Coordinate(lng, lat);
    }

    public void setDist(float dist) {
        this.dist = dist;
    }

    public void setDiesel(float diesel) {
        this.diesel = diesel;
    }

    public void setE5(float e5) {
        this.e5 = e5;
    }

    public void setE10(float e10) {
        this.e10 = e10;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }
}
