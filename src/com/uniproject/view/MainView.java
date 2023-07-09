package com.uniproject.view;

import com.uniproject.controller.MainController;
import com.uniproject.view.map.MapPane;
import com.uniproject.view.sidebar.SideBar;
import javafx.scene.control.SplitPane;

// Top level class for the GUI
// Splits the app in two
// views and initializes them
public class MainView extends SplitPane {
    public MainView(MainController mainController) {
        MapPane mapView = new MapPane(mainController);
        SideBar sideBar = new SideBar(mainController);
        this.getItems().addAll(sideBar, mapView);
        this.setDividerPositions(0.3f, 0.6f);
    }
}
