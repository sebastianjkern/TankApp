package com.uniproject.view;

import com.uniproject.model.Price;
import com.uniproject.model.GasStation;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GasStationList extends ScrollPane {
    // TODO: Get from API
    final List<GasStation> gasStations = Arrays.asList(
            new GasStation(new Price(1, 50), "Aral", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Aral", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Aral", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f),
            new GasStation(new Price(1, 50), "Total", 1.5f, 2.5f)
    );

    final VBox vBox = new VBox();

    public GasStationList() {
        List<GasStationPane> gasStationPanes = new java.util.ArrayList<GasStationPane>(Collections.<GasStationPane>emptyList());

        for (GasStation gasStation : gasStations) {
            gasStationPanes.add(new GasStationPane(gasStation));
        }

        this.setFitToHeight(true);
        this.setFitToWidth(true);

        vBox.getChildren().addAll(gasStationPanes);
        this.setContent(vBox);
    }
}
