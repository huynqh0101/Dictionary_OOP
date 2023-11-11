package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class GameController {
    private SwitchSceneController switchSceneController = new SwitchSceneController();


    @FXML
    private void menuAddWordButton(ActionEvent event) throws IOException {
        switchSceneController.switchToAddWordScene(event);
    }

    @FXML
    private void backToMenuButton(ActionEvent event) throws IOException {
        switchSceneController.switchToMenuScene(event);
    }

    @FXML
    private void menuSearchWordButton(ActionEvent event) throws IOException {
        switchSceneController.switchToSearchWordScene(event);
    }

    @FXML
    private void menuTranslationButton(ActionEvent event) throws IOException {
        switchSceneController.switchToTranslationScene(event);
    }

    @FXML
    private void menuExitButton(ActionEvent event) {
        switchSceneController.Exit();
    }
}
