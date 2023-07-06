package com.uniproject.view;

import com.uniproject.controller.MainController;

import javafx.scene.control.SplitPane;

public class MainView extends SplitPane {
	private MainController mainController;
	private MapPane mapView;
	private SideBar sideBar;
    
    public MainView(MainController mainController) {
    	this.mainController = mainController;
    	this.mapView = new MapPane();
    	this.sideBar = new SideBar(this.mainController);
        this.getItems().addAll(sideBar, mapView);
        this.setDividerPositions(0.3f, 0.6f);
    }

}
