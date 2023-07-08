package com.uniproject.view;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

public class SearchBox extends HBox {
    final Text searchLabel = new Text("Suche Tankstelle:");
    final TextField searchBar = new TextField();
    final Button searchButton = new Button("Suche");

    public SearchBox() {
        setHgrow(searchBar, Priority.ALWAYS);
        this.getChildren().addAll(searchLabel, searchBar, searchButton);
        this.setAlignment(Pos.CENTER);
    }
}
