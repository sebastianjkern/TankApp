package com.uniproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        SplitPane splitPane = new SplitPane();

        MapView mapView = new MapView();

        VBox vBox = new VBox();

        HBox hBox = new HBox();

        Text searchLabel = new Text("Suche Tankstelle: ");
        TextField searchField = new TextField();

        hBox.getChildren().addAll(searchLabel, searchField);

        ScrollPane scrollPane = new ScrollPane();

        vBox.getChildren().addAll(hBox, scrollPane);

        scrollPane.setStyle("-fx-background-color: #0000AA;");

        splitPane.getItems().addAll(hBox, mapView);
        splitPane.setDividerPositions(0.3f, 0.6f);

        Scene scene = new Scene(splitPane, 1280, 720);

        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}