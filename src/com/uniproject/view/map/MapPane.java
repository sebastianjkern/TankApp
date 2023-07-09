package com.uniproject.view.map;

import com.uniproject.controller.MainController;
import com.uniproject.model.Coordinate;
import com.uniproject.model.GasStation;
import javafx.concurrent.Worker;
import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import netscape.javascript.JSObject;

import java.util.List;
import java.util.stream.Collectors;

public class MapPane extends StackPane {
    final private WebView webView = new WebView();
    final public WebEngine webEngine = webView.getEngine();

    final private MainController mainController;

    // Convert the gas stations to string
    // Allows them to be pushed in the webengine
    private String gasStationsToString(List<GasStation> gasStations) {
        return gasStations.stream().map(n -> n.getCoordinate().toString()).collect(Collectors.joining(",", "[", "]"));
    }

    // Callback for updating the center of the map
    // For example if the button for showing a gas station is clicked
    public Boolean UpdateMapCenter(Coordinate coordinate) {
        webEngine.executeScript("window.center = " + coordinate.toString() + ";");
        webEngine.executeScript("updateCenter();");
        return true;
    }

    // Callback for updating the markers in the map
    // Needed if the gas stations are reloaded
    public Boolean UpdateGasStations(List<GasStation> gasStations) {
        System.out.println("Updating markers");
        webEngine.executeScript("window.gasStations = " + gasStationsToString(gasStations) + ";");
        webEngine.executeScript("updateMarkers();");
        return true;
    }

    public MapPane(MainController mainController) {
        this.mainController = mainController;

        // Try to load the content of the webview
        // handle any error that may occur
        try {
            webEngine.load(this.getClass().getResource("/resources/index.html").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("An internal error occured");
            System.exit(1);
        }

        // Register callbacks to the webview
        // for updating the markers and
        // the map center
        this.mainController.registerMapCenterCallback(this::UpdateMapCenter);
        this.mainController.registerMapUpdateCallback(this::UpdateGasStations);

        // Initialize some functionality after the content is loaded successfully
        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                // Update the markers after the map is loaded
                this.mainController.mapUpdateCallback.apply(this.mainController.gasStations);

                // Connect the web content to java callback
                // Allows for dynamic content loading, when map is dragged
                // (in theory)
                JSObject window = (JSObject) webEngine.executeScript("window");
                window.setMember("app", this);
            }
        });

        // Add map as only child to map pane
        getChildren().add(webView);
    }

    // Refresh the map if the stack pane is resized
    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        this.webView.resize(v, v1);
    }

    // Callback function for javascript map events
    public void onMapCenterChange(String center, String radius) {
        // Convert data from Strings to correct data types
        String[] results = center.split(",");
        float longitude = Float.parseFloat(results[0]);
        float latitude = Float.parseFloat(results[1]);
        float rad = Float.parseFloat(radius);

        // Limit radius to 15 kilometers to reduce load on server
        rad = Math.min(rad, 15);

        // Load new gas station information from api
        this.mainController.requestNewLocation(latitude, longitude, rad);

        // Update listview and markers
        this.mainController.listUpdateCallback.apply(mainController.gasStations);
        this.mainController.mapUpdateCallback.apply(mainController.gasStations);
    }
}
