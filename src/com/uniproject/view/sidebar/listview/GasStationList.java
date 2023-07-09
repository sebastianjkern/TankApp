package com.uniproject.view.sidebar.listview;

import com.uniproject.controller.MainController;
import com.uniproject.model.GasStation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Collections;
import java.util.List;

public class GasStationList extends ScrollPane {

    final VBox vBox = new VBox();
    final MainController mainController;

    public Boolean updateList(List<GasStation> gasStations) {
        List<GasStationPane> gasStationPanes = new java.util.ArrayList<GasStationPane>(Collections.<GasStationPane>emptyList());

        for (GasStation gasStation : this.mainController.gasStations) {
            gasStationPanes.add(new GasStationPane(gasStation, this.mainController));
        }

        vBox.getChildren().clear();
        vBox.getChildren().addAll(gasStationPanes);

        return false;
    }

    public GasStationList(MainController mainController) {
        this.mainController = mainController;
        this.mainController.registerListUpdateCallback(this::updateList);

        List<GasStationPane> gasStationPanes = new java.util.ArrayList<GasStationPane>(Collections.<GasStationPane>emptyList());

        for (GasStation gasStation : this.mainController.gasStations) {
            gasStationPanes.add(new GasStationPane(gasStation, mainController));
        }

        this.setFitToHeight(true);
        this.setFitToWidth(true);

        vBox.getChildren().addAll(gasStationPanes);
        this.setContent(vBox);
    }

}
