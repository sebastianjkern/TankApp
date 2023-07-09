package com.uniproject.controller;

import com.uniproject.model.Coordinate;
import com.uniproject.model.GasStation;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class MainController {
    // TODO: Search

    public GasStationDataFetcher gasStationDataFetcher;
    public List<GasStation> gasStations;
    public List<GasStation> searchResult;

    // Callbacks:
    public Function<Coordinate, Boolean> mapCenterCallback;
    public Function<List<GasStation>, Boolean> mapUpdateCallback;
    public Function<List<GasStation>, Boolean> listUpdateCallback;

    // Register some callback for updating the markers, gas station list and the map center
    public void registerMapCenterCallback(Function<Coordinate, Boolean> callback) {
        this.mapCenterCallback = callback;
    }

    public void registerMapUpdateCallback(Function<List<GasStation>, Boolean> callback) {
        this.mapUpdateCallback = callback;
    }

    public void registerListUpdateCallback(Function<List<GasStation>, Boolean> callback) {
        this.listUpdateCallback = callback;
    }

    public void search(String query) {
        this.searchResult = gasStations.stream().filter(n -> n.getName().contains(query)).toList();
    }

    // Creating
    public MainController(String apiKey) {
        this.gasStationDataFetcher = new GasStationDataFetcher(apiKey);
        if (requestNewLocation(51.050407, 13.737262, 5.0)) {
            // Success
        } else {
            // Error / Try Again ...
        }
    }

    // Requests data from the api through the GasStationDataFetcher
    // and stores it in the main controller
    public boolean requestNewLocation(double lat, double lon, double rad) {
        try {
            this.gasStations = gasStationDataFetcher.fetchGasStationData(lat, lon, rad);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            this.gasStations = new java.util.ArrayList<GasStation>(Collections.<GasStation>emptyList());
            return false;
        }
    }
}
