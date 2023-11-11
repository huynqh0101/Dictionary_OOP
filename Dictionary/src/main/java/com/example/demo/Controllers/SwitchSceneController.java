package com.example.demo.Controllers;

import com.example.demo.Dictionary.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

public class SwitchSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public SwitchSceneController(){}

    private void switchScene(String fxmlPath, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToMenuScene(ActionEvent event) throws IOException {
        switchScene("/View/MenuGui.fxml", event);
    }

    public void switchToSearchWordScene(ActionEvent event) throws IOException {
        switchScene("/View/SearchWordGui.fxml", event);
    }

    public void switchToAddWordScene(ActionEvent event) throws IOException {
        switchScene("/View/AddWordGui.fxml", event);
    }

    public void switchToTranslationScene(ActionEvent event) throws IOException {
        switchScene("/View/TranslationGui.fxml", event);
    }

    public void switchToGameScene(ActionEvent event) throws IOException {
        switchScene("/View/GameGui.fxml", event);
    }

    public void Exit() {
        Platform.exit();
        System.exit(0);
    }
}