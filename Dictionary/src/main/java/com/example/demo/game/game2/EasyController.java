package com.example.demo.game.game2;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EasyController extends game2 implements Initializable {
    private static List<String> wordList;
    private static List<Answer> answers;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        String path = "src\\main\\java\\com\\example\\demo\\game\\game2\\game2.2.txt";
        wordList = readFile(path);
        answers = UnionFind(wordList);
    }

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
            currentStage.setScene(scene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void Backtogame2(MouseEvent mouseEvent) {
        show(mouseEvent,"/game/game2/Game2.fxml");
    }

    public void Howtoplaygame2(MouseEvent mouseEvent) {
        show(mouseEvent,"/game/game2/Howtoplaygame2.fxml");
    }
}
