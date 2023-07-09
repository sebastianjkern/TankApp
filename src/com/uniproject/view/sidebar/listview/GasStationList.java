package com.uniproject.view.sidebar.listview;

import com.uniproject.controller.MainController;
import com.uniproject.model.GasStation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.List;

// UI Element for showing a list of gas stations
// Provides functionality for updating list if new data is fetched
public class GasStationList extends ScrollPane {
    final VBox vBox = new VBox();
    final MainController mainController;

    // Update content of the list from the gas stations from the given list
    public Boolean updateList(List<GasStation> gasStations) {
        List<GasStationPane> gasStationPanes = new java.util.ArrayList<GasStationPane>(Collections.<GasStationPane>emptyList());

        for (GasStation gasStation : gasStations) {
            gasStationPanes.add(new GasStationPane(gasStation, this.mainController));
        }

        vBox.getChildren().clear();
        vBox.getChildren().addAll(gasStationPanes);

        return false;
    }

    public GasStationList(MainController mainController) {
        // Register maincontroller with class and
        // register the updateList callback to the main controller
        this.mainController = mainController;
        this.mainController.registerListUpdateCallback(this::updateList);

        // Update the list a first time
        this.updateList(mainController.gasStations);

        this.setFitToHeight(true);
        this.setFitToWidth(true);

        this.setContent(vBox);
    }
}
