package com.uniproject.view;

import com.uniproject.model.GasStation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;


public class GasStationPane extends HBox {
    public Text name;
    public Text price;
    final Button button = new Button("Zeige in Karte");
    final Region region1 = new Region();
    final Region region2 = new Region();

    public GasStationPane(GasStation gasStation) {
        String priceString = gasStation.getE5() + "€";

        this.name = new Text(gasStation.getName());
        this.price = new Text(priceString);

        this.setAlignment(Pos.CENTER);

        setHgrow(region1, Priority.ALWAYS);
        setHgrow(region2, Priority.ALWAYS);

        this.getChildren().addAll(this.price, this.region1, this.name, this.region2, this.button);
    }
}
