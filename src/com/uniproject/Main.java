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
        Properties properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }

        final String apiKey = properties.getProperty("api.key");
        System.out.println("API key: " + apiKey);


        MainController mainController = new MainController(apiKey);
        MainView mainView = new MainView(mainController);


        Scene scene = new Scene(mainView, 1280, 720);

        JMetro jMetro = new JMetro(Style.LIGHT);
        jMetro.setScene(scene);

        primaryStage.setTitle("TankApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}