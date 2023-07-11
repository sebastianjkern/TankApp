package com.uniproject.view.sidebar.listview;

import com.uniproject.controller.MainController;
import com.uniproject.model.GasStation;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;


// UI Element that shows information about one gas station
// is meant to be shown in a list, as in the GasStationList
public class GasStationPane extends HBox {
    public Text name;
    public Text price;
    final Button button = new Button("Zeige in Karte");
    final Region region1 = new Region();
    final Region region2 = new Region();

    private final MainController mainController;

    public GasStationPane(GasStation gasStation, MainController mainController) {
        this.mainController = mainController;

        // Set Texts with content
        this.name = new Text(gasStation.getBrand());
        this.price = new Text(gasStation.getE10() + "€");

        // Register button callback
        this.button.setOnAction(event -> this.mainController.mapCenterCallback.apply(gasStation.getCoordinate()));

        // Set some alignment and region parameter to
        // expand the gas station pane to maximum width
        this.setAlignment(Pos.CENTER);

        setHgrow(region1, Priority.ALWAYS);
        setHgrow(region2, Priority.ALWAYS);

        // Add all children to gas station pane
        this.getChildren().addAll(this.price, this.region1, this.name, this.region2, this.button);
    }
}
