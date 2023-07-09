package com.uniproject.view.sidebar.search;

import com.uniproject.controller.MainController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;

import java.util.Objects;

// UI Element that shows the search box and the search button
public class SearchBox extends HBox {
    final Text searchLabel = new Text("Suche Tankstelle:");
    final TextField searchBar = new TextField();
    final Button searchButton = new Button("Suche");

    final MainController mainController;

    private void search(String query) {
        if (Objects.equals(searchBar.getText(), "")) {
            mainController.listUpdateCallback.apply(mainController.gasStations);
        } else {
            mainController.search(searchBar.getText());
            mainController.listUpdateCallback.apply(mainController.searchResult);
        }
    }

    public SearchBox(MainController mainController) {
        this.mainController = mainController;

        setHgrow(searchBar, Priority.ALWAYS);
        this.getChildren().addAll(searchLabel, searchBar, searchButton);
        this.setAlignment(Pos.CENTER);

        // Register event handler in case search button is clicked
        searchButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                search(searchBar.getText());
            }
        });

        // Register event handler in case the textfield registers event search for gas station
        searchBar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                search(searchBar.getText());
            }
        });
    }
}
