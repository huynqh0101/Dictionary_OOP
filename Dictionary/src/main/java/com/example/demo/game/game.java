package com.example.demo.game;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class game extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        try {
            // Load Choosegame.fxml
            Parent root = FXMLLoader.load(getClass().getResource("/game/Choosegame.fxml"));

            // Tạo một Scene từ root
            Scene scene = new Scene(root);

            // Đặt Scene lên Stage
            primaryStage.setScene(scene);
            primaryStage.setTitle("Choose Game");

            // Hiển thị Stage
            primaryStage.show();
            System.out.println("yes");
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
