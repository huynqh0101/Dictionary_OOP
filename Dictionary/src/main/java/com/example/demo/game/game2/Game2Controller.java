package com.example.demo.game.game2;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.util.List;

public class Game2Controller extends game2 {
    private double xOffset = 0;
    private double yOffset = 0;

    public void show(MouseEvent mouseEvent, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DictionariesGui.fxml"));
            Parent dictionariesGui = loader.load();
            DictionaryController dictionaryController = loader.getController();

            FXMLLoader game1Loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane game1Pane = game1Loader.load();
            dictionaryController.setNode(game1Pane);

            Scene scene = new Scene(dictionariesGui);
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

            scene.setOnMousePressed(event -> {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            });

            scene.setOnMouseDragged(event -> {
                currentStage.setX(event.getScreenX() - xOffset);
                currentStage.setY(event.getScreenY() - yOffset);
            });
            currentStage.setScene(scene);
            currentStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void BacktoChoosegame(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/Choosegame.fxml");
    }

    public void Easy(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game2/Easy.fxml");
    }

    public void Medium(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game2/Medium.fxml");
    }

    public void Hard(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game2/Hard.fxml");
    }

    public void Howtoplaygame2(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game2/Howtoplaygame2.fxml");
    }
}
