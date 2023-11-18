package com.example.demo.game.game2;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Howtoplaygame2 {
    public void show(MouseEvent mouseEvent, String path) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DictionariesGui.fxml"));
            Parent g2 = loader.load();
            DictionaryController dictionaryController = loader.getController();

            FXMLLoader game2Loader = new FXMLLoader(getClass().getResource(path));
            AnchorPane game2 = game2Loader.load();
            dictionaryController.setNode(game2);

            Scene scene = new Scene(g2);
            Stage currentStage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            currentStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void Backtogame2(MouseEvent mouseEvent) {
        show(mouseEvent,"/game/game2/Game2.fxml");
    }
}
