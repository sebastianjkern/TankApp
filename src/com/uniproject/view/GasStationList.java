package com.uniproject.view;

import com.uniproject.controller.MainController;
import com.uniproject.model.GasStation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GasStationList extends ScrollPane {
	
    // TODO: Get from API
	
	private MainController mainController;


    final VBox vBox = new VBox();

    public GasStationList(MainController mainController) {
    	this.mainController = mainController;
    	
        List<GasStationPane> gasStationPanes = new java.util.ArrayList<GasStationPane>(Collections.<GasStationPane>emptyList());
        

        for (GasStation gasStation : this.mainController.gasStations) {
            gasStationPanes.add(new GasStationPane(gasStation));
        }

        this.setFitToHeight(true);
        this.setFitToWidth(true);

        vBox.getChildren().addAll(gasStationPanes);
        this.setContent(vBox);
    }
}
