package com.uniproject.view;

import com.uniproject.controller.MainController;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

public class SideBar extends VBox {
    private MainController mainController;
    private SearchBox searchBox;
    private GasStationList gasStationList;

    public SideBar(MainController mainController) {
        this.mainController = mainController;
        this.searchBox = new SearchBox();
        this.gasStationList = new GasStationList(this.mainController);
        this.getChildren().addAll(searchBox, gasStationList);
        this.setAlignment(Pos.BASELINE_CENTER);
    }
}