package com.example.demo.game;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class ChoosegameController {
    public void Choosegame1(MouseEvent mouseEvent) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/game1/Game1.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Game 1");

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lỗi");
        }
    }

    public void Choosegame2(MouseEvent mouseEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/game/game2/Game2.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Game 2");

            stage.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("lỗi");
        }
    }
}
