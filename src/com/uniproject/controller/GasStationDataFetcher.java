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

    public float GetDouble(JSONObject object, String string) {
        try {
            return (float) object.getDouble(string);
        } catch (JSONException e) {
            return -1;
        }
    }

    // Test Values 52.521, 13.438, 5.0
    public List<GasStation> fetchGasStationData(double centerLat, double centerLon, double rad) throws Exception {
        List<GasStation> gasStationDataList = new ArrayList<>();
        String urlS = getAPIUrl(centerLat, centerLon, rad, "dist", "all", API_KEY);
        URL url = new URL(urlS);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuilder content = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }

        // Close connections
        in.close();
        conn.disconnect();

        JSONObject jsonObject = new JSONObject(content.toString());
        JSONArray stationsArray = jsonObject.getJSONArray("stations");

        for (int i = 0; i < stationsArray.length(); i++) {
            JSONObject station = stationsArray.getJSONObject(i);

            // Using constructor to create GasStation object
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

    public String getAPIUrl(double lat, double lng, double rad, String sort, String type, String apikey) {
        return String.format("https://creativecommons.tankerkoenig.de/json/list.php?lat=%f&lng=%f&rad=%f&sort=%s&type=%s&apikey=%s",
                lat, lng, rad, sort, type, apikey);
    }
}
