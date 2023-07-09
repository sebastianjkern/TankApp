package com.uniproject.view.sidebar;

import com.uniproject.controller.MainController;
import com.uniproject.view.sidebar.listview.GasStationList;
import com.uniproject.view.sidebar.search.SearchBox;
import javafx.geometry.Pos;
import javafx.scene.layout.VBox;

// Top level class for the sidebar
// Initializes the searchbar and the gas station list
public class SideBar extends VBox {

    public SideBar(MainController mainController) {
        SearchBox searchBox = new SearchBox(mainController);
        GasStationList gasStationList = new GasStationList(mainController);
        this.getChildren().addAll(searchBox, gasStationList);
        this.setAlignment(Pos.BASELINE_CENTER);
    }
}