package com.uniproject.view;

import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {
    final SearchBox searchBox = new SearchBox();
    final GasStationList gasStationList = new GasStationList();

    public SideBar() {
        this.getChildren().addAll(searchBox, gasStationList);
        this.setAlignment(Pos.BASELINE_CENTER);
    }
}