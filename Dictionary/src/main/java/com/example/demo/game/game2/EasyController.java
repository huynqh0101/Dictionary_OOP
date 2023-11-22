package com.example.demo.game.game2;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;

public class EasyController extends game2 implements Initializable {
    private static List<String> wordList2;
    private static List<Answer> answers2;
    private static Answer a;
    private static List<String> playerAnswer2;

    private static final int height_answer = 4;
    private static final int weight_answer = 2;

    @FXML
    private Label eline1a, eline1b;
    @FXML
    private Label eline2a, eline2b;
    @FXML
    private Label eline3a, eline3b;
    @FXML
    private Label eline4a, eline4b;
    @FXML
    private Label notification;

    private boolean checkLine2, checkLine3;
    private boolean win;

    public void setline(String s, Label l1, Label l2) {
        if (s.length() == 2) {
            l1.setText(String.valueOf(s.charAt(0)).toUpperCase());
            l2.setText(String.valueOf(s.charAt(1)).toUpperCase());
        } else {
            l1.setText("");
            l2.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (wordList2!=null) wordList2.clear();
            if (answers2!=null) answers2.clear();
            if (a!=null) a=null;
            if (playerAnswer2!=null) playerAnswer2.clear();
            playerAnswer2 = new ArrayList<>();
            String path = "DataGame\\game2.2.txt";
            wordList2 = readFile(path);
            answers2 = UnionFind(wordList2);
            Collections.shuffle(answers2);
            begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void begin() {
        a = answers2.remove(0);
        playerAnswer2.add(a.getAnswer1());
        setline(a.getAnswer1(), eline1a, eline1b);
        setline("", eline2a, eline2b);
        setline("", eline3a, eline3b);
        setline(a.getAnswer4(), eline4a, eline4b);
        textgame2.setText("");
        notification.setText("");
        checkLine2 = false;
        checkLine3 = false;
        win = false;
        hintgame = 1;
    }

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

    public void Backtogame2(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game2/Game2.fxml");
        textgame2.setText("");
        notification.setText("");
        playerAnswer2 = new ArrayList<>();
        wordList2.clear();
        answers2.clear();
        a = null;
        checkLine2 = false;
        checkLine3 = false;
        win = false;
        hintgame = 1;
        System.gc();
    }

    @FXML
    private TextField textgame2;

    public void check(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {

            String check = textgame2.getText().toLowerCase();
            if (check.length() == weight_answer) {
                if (!win) notification.setText(result(check));
                textgame2.setText("");
            } else {
                textgame2.setText("");
                if (!win) notification.setText("Invalid.");
            }
        }
    }

    public String result(String check) {
        if (wordList2.contains(check)) {
            if (!checkLine2) {
                if (areSimilar(check, playerAnswer2.get(0))) {
                    setline(check, eline2a, eline2b);
                    playerAnswer2.add(check);
                    checkLine2 = true;
                    return "";
                } else if (check.equals(playerAnswer2.get(0))) {
                    return "Please use a different word from the one above.";
                } else return "Use the same letters from the above word row, only altering one.";
            }
            if (!checkLine3) {
                if (check.equals(a.getAnswer4())) {
                    return "Use the same letters from the above word row, only altering one.";
                }
                if (areSimilar(check, playerAnswer2.get(1))&&areSimilar(check,a.getAnswer4())) {
                    setline(check, eline3a, eline3b);
                    playerAnswer2.add(check);
                    checkLine3 = true;
                    if (checkLine2 && checkLine3) {
                        win = true;
                        return "Congratulation you win.";
                    }
                } else if (check.equals(playerAnswer2.get(1))) {
                    return "Please use a different word from the one above.";
                }
                return "Use the same letters from the above word row, only altering one.";
            }
        }
        return "Not a valid word.";
    }

    public void Backspace(MouseEvent mouseEvent) {
        if (!win) {
            if (playerAnswer2.size() == 3) {
                setline("", eline3a, eline3b);
                playerAnswer2.remove(playerAnswer2.size() - 1);
                checkLine3 = false;
                hintgame = 2;
            } else if (playerAnswer2.size() == 2) {
                setline("", eline2a, eline2b);
                playerAnswer2.remove(playerAnswer2.size() - 1);
                checkLine2 = false;
                hintgame = 1;
            }
        }
    }

    @FXML
    private static int hintgame;

    @FXML
    public void Hint(MouseEvent mouseEvent) {
        if (!win) {
            if (playerAnswer2.get(playerAnswer2.size() - 1).equals(a.getAnswer2())
                    && hintgame == 1) {
                hintgame = 2;
                checkLine2 = true;
            }
            if (hintgame == 2) {
                if (playerAnswer2.size() == 3) {
                    playerAnswer2.remove(playerAnswer2.size() - 1);
                }
                playerAnswer2.add(a.getAnswer3());
                setline(a.getAnswer3(), eline3a, eline3b);
                notification.setText("Congratulation you win.");
                win = true;
                checkLine3 = true;
                hintgame++;
            }
            if (hintgame == 1) {
                if (playerAnswer2.size() == 2) {
                    playerAnswer2.remove(playerAnswer2.size() - 1);
                }
                playerAnswer2.add(a.getAnswer2());
                setline(a.getAnswer2(), eline2a, eline2b);
                setline("", eline3a, eline3b);
                hintgame++;
                checkLine2 = true;
            }
        }
    }

    public void Next(MouseEvent mouseEvent) {
        try {
            playerAnswer2.clear();
            begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
