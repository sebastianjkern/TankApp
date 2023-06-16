package com.uniproject.view;

import javafx.scene.control.SplitPane;

public class MainView extends SplitPane {
    final MapPane mapView = new MapPane();
    final SideBar sideBar = new SideBar();

    public MainView() {
        this.getItems().addAll(sideBar, mapView);
        this.setDividerPositions(0.3f, 0.6f);
    }

}
