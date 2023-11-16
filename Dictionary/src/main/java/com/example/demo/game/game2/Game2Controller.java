package com.example.demo.game.game2;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class Game2Controller {
    private Parent game2;
    private Stage window;
    public void BacktoChoosegame(MouseEvent mouseEvent) {
        try {
            game2 = FXMLLoader.load(getClass().getResource("/game/Choosegame.fxml"));
            Scene playgame1 = new Scene(game2);
            window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(playgame1);
            window.setTitle("Game !!!");

            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
