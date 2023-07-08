package com.uniproject.view;

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

    private String gasStationsToString(List<GasStation> gasStations) {
        return gasStations.stream().map(n -> n.getCoordinate().toString()).collect(Collectors.joining(",", "[", "]"));
    }

    public Boolean UpdateMapCenter(Coordinate coordinate) {
        webEngine.executeScript("window.center = " + coordinate.toString() + ";");
        webEngine.executeScript("updateCenter();");
        return true;
    }

    public Boolean UpdateGasStations(List<GasStation> gasStations) {
        webEngine.executeScript("window.gasStations = " + gasStationsToString(gasStations) + ";");
        webEngine.executeScript("updateMarkers();");
        return true;
    }

    public MapPane(MainController mainController) {
        mainController.registerMapCenterCallback(this::UpdateMapCenter);
        mainController.registerMapUpdateCallback(this::UpdateGasStations);

        try {
            webEngine.load(this.getClass().getResource("/resources/index.html").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("An internal error occured");
            System.exit(1);
        }

        webEngine.getLoadWorker().stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == Worker.State.SUCCEEDED) {
                mainController.mapUpdateCallback.apply(mainController.gasStations);

                JSObject window = (JSObject) webEngine.executeScript("window");

                JavaApp javaApp = new JavaApp();
                window.setMember("app", javaApp);
            }
        });

        getChildren().add(webView);
    }

    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        this.webView.resize(v, v1);
    }

    public static class JavaApp {
        public void onClick() {
            System.out.println("Dragged");
        }

    }
}
