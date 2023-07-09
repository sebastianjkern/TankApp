package com.uniproject.controller;

import com.uniproject.model.GasStation;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GasStationDataFetcher {
    private final String API_KEY;

    public GasStationDataFetcher(String apiKey) {
        this.API_KEY = apiKey;
    }

    // Sometimes the api doesn't respond with every field we expect,
    // therefor we check if values are convertible otherwise
    // we respond wit obviously wrong data
    public float GetDouble(JSONObject object, String string) {
        try {
            return (float) object.getDouble(string);
        } catch (JSONException e) {
            return -1;
        }
    }

    public List<GasStation> fetchGasStationData(double centerLat, double centerLon, double rad) throws Exception {

        // Initialize list and buid url from given data
        List<GasStation> gasStationDataList = new ArrayList<>();
        String urlS = getAPIUrl(centerLat, centerLon, rad, "dist", "all", API_KEY);
        URL url = new URL(urlS);

        // Build HTTP Connection to JSON API Server and request data
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        // Read data from response and combine into single
        // stringbuilder object
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Close connections
        in.close();
        conn.disconnect();

        // Read API response with json library
        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray stationsArray = jsonObject.getJSONArray("stations");

        for (int i = 0; i < stationsArray.length(); i++) {
            JSONObject station = stationsArray.getJSONObject(i);

            // Convert unstructured data to data model
            GasStation gasStationData = new GasStation(
                    station.getString("id"),
                    station.getString("name"),
                    station.getString("brand"),
                    station.getString("street"),
                    station.getString("place"),
                    GetDouble(station, "lat"),
                    GetDouble(station, "lng"),
                    GetDouble(station, "dist"),
                    GetDouble(station, "diesel"),
                    GetDouble(station, "e5"),
                    GetDouble(station, "e10"),
                    station.getBoolean("isOpen"),
                    station.getString("houseNumber"),
                    station.getInt("postCode")
            );

            gasStationDataList.add(gasStationData);
        }

        return gasStationDataList;
    }

    // Build the API query string from given data,
    // especially the needed latitude and longitude
    public String getAPIUrl(double lat, double lng, double rad, String sort, String type, String apikey) {
        return String.format("https://creativecommons.tankerkoenig.de/json/list.php?lat=%f&lng=%f&rad=%f&sort=%s&type=%s&apikey=%s",
                lat, lng, rad, sort, type, apikey);
    }
}
