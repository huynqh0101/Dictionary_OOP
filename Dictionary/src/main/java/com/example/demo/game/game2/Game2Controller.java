package com.example.demo.game.game2;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Game2Controller {
    private Parent game2;
    private Stage window;

    public void show(MouseEvent mouseEvent,String path){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DictionariesGui.fxml"));
            Parent dictionariesGui = loader.load();
            DictionaryController dictionaryController = loader.getController();

            FXMLLoader game1Loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane game1Pane = game1Loader.load();
            dictionaryController.setNode(game1Pane);

            Scene scene = new Scene(dictionariesGui);
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void BacktoChoosegame(MouseEvent mouseEvent) {
        show(mouseEvent,"/game/Choosegame.fxml");
    }
}
