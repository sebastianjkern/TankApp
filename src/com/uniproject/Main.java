package com.uniproject;

import com.uniproject.controller.MainController;
import com.uniproject.view.MainView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {

        // Load the API key from the configuration file
    	// Necessary so it can be excluded in the gitignore
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Reading the api key from file
        final String apiKey = properties.getProperty("api.key");
        System.out.println("API key: " + apiKey);

        // Initializing the main controller of the app and
        // the main view of the app with the main controller
        MainController mainController = new MainController(apiKey);
        MainView mainView = new MainView(mainController);

        Scene scene = new Scene(mainView, 1280, 720);

        // Setting a predefined style for a
        // more similar look to windows metro design
        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        // Setting some properties of the app
        // Especially loading the main view
        primaryStage.setTitle("TankApp");
        primaryStage.setScene(scene);

        // And finally show the app
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}