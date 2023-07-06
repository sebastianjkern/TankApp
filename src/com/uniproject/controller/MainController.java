package com.uniproject.controller;

import java.util.Collections;
import java.util.List;

import com.uniproject.model.*;

public class MainController {
    // TODO: Search
    // TODO: MapView
	
	
	public GasStationDataFetcher gasStationDataFetcher;
	public List<GasStation> gasStations;
	
	
	// Creating
	public MainController(String apiKey) {
		this.gasStationDataFetcher = new GasStationDataFetcher(apiKey);
		if(requestNewLocation(51.050407, 13.737262, 5.0)) {
			// Success
		}else {
			// Error / Try Again ...
		}
	}
	
	public boolean requestNewLocation (double lat, double lon, double rad) {
		try {
			this.gasStations = gasStationDataFetcher.fetchGasStationData(lat, lon, rad);
			return true;
		}catch (Exception e) {
			System.out.println(e.toString());
			this.gasStations = new java.util.ArrayList<GasStation>(Collections.<GasStation>emptyList());
			return false;
		}
	}
}
