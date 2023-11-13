package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.Dictionary.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class SwitchSceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Alerts alerts = new Alerts();

    public SwitchSceneController(){}

    private void switchScene(String fxmlPath, ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource(fxmlPath));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToLoginScene(ActionEvent event) throws IOException {
        switchScene("/View/login.fxml", event);
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
        Alert alertConfirm = alerts.alertConfirm("Confirmation", "Bạn có chắc muốn thoát ứng dụng ?");
        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            Platform.exit();
        }
    }
}