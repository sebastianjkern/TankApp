package com.uniproject.view;

import javafx.scene.layout.StackPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class MapPane extends StackPane {
    final private WebView webView = new WebView();
    final private WebEngine webEngine = webView.getEngine();

    public MapPane() {
        try {
            webEngine.load(this.getClass().getResource("/resources/index.html").toExternalForm());
        } catch (NullPointerException e) {
            System.out.println("An internal error occured");
            System.exit(1);
        }

        getChildren().add(webView);
    }

    @Override
    public void resize(double v, double v1) {
        super.resize(v, v1);
        this.webView.resize(v, v1);
    }
}
