package com.example.demo.game;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ChoosegameController {
    @FXML
    public AnchorPane container1;

    public void setContainer1(AnchorPane container1) {
        try {
            this.container1 = (AnchorPane) FXMLLoader.load(getClass().getResource("/View/DictionariesGui.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public AnchorPane getContainer1() {
        return this.container1;
    }

    public void Choosegame1(MouseEvent mouseEvent) throws Exception {
        show(mouseEvent, "/game/game1/Game1.fxml");
    }

    public void Choosegame2(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game2/Game2.fxml");
    }

    public void show(MouseEvent mouseEvent, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DictionariesGui.fxml"));
            Parent dictionariesGui = loader.load();
            DictionaryController dictionaryController = loader.getController();
            // Load Game1.fxml
            FXMLLoader game1Loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane game1Pane = game1Loader.load();
            dictionaryController.setNode(game1Pane);

            Scene scene = new Scene(dictionariesGui);
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
