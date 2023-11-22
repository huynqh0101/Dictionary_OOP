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

public class MediumController extends game2 implements Initializable {
    private static List<String> wordList3;
    private static List<Answer> answers3;
    private static Answer a;
    private static List<String> playerAnswer3;

    private static final int height_answer = 4;
    private static final int weight_answer = 3;

    @FXML
    private Label line1a, line1b, line1c;
    @FXML
    private Label line2a, line2b, line2c;
    @FXML
    private Label line3a, line3b, line3c;
    @FXML
    private Label line4a, line4b, line4c;
    @FXML
    private Label notification;

    private boolean checkLine2, checkLine3;
    private boolean win;

    public void setline(String s, Label l1, Label l2, Label l3) {
        if (s.length() == 3) {
            l1.setText(String.valueOf(s.charAt(0)).toUpperCase());
            l2.setText(String.valueOf(s.charAt(1)).toUpperCase());
            l3.setText(String.valueOf(s.charAt(2)).toUpperCase());
        } else {
            l1.setText("");
            l2.setText("");
            l3.setText("");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            if (wordList3 != null) wordList3.clear();
            if (answers3 != null) answers3.clear();
            if (a != null) a = null;
            if (playerAnswer3!=null) playerAnswer3.clear();
            playerAnswer3 = new ArrayList<>();
            String path = "DataGame\\game2.3.txt";
            wordList3 = readFile(path);
            answers3 = UnionFind(wordList3);
            Collections.shuffle(answers3);
            begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void begin() {
        a = answers3.remove(0);
        playerAnswer3.add(a.getAnswer1());
        setline(a.getAnswer1(), line1a, line1b, line1c);
        setline("", line2a, line2b, line2c);
        setline("", line3a, line3b, line3c);
        setline(a.getAnswer4(), line4a, line4b, line4c);
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
        playerAnswer3.clear();
        wordList3.clear();
        answers3.clear();
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
        if (wordList3.contains(check)) {
            if (!checkLine2) {
                if (areSimilar(check, playerAnswer3.get(0))) {
                    setline(check, line2a, line2b, line2c);
                    playerAnswer3.add(check);
                    checkLine2 = true;
                    return "";
                } else if (check.equals(playerAnswer3.get(0))) {
                    return "Please use a different word from the one above.";
                } else return "Use the same letters from the above word row, only altering one.";
            }
            if (!checkLine3) {
                if (check.equals(a.getAnswer4())) {
                    return "Use the same letters from the above word row, only altering one.";
                }
                if (areSimilar(check, playerAnswer3.get(1))&&areSimilar(check,a.getAnswer4())) {
                    setline(check, line3a, line3b, line3c);
                    playerAnswer3.add(check);
                    checkLine3 = true;
                    if (checkLine2 && checkLine3) {
                        win = true;
                        return "Congratulation you win.";
                    }
                } else if (check.equals(playerAnswer3.get(1))) {
                    return "Please use a different word from the one above.";
                }
                return "Use the same letters from the above word row, only altering one.";
            }
        }
        return "Not a valid word.";
    }

    public void Backspace(MouseEvent mouseEvent) {
        if (!win) {
            if (playerAnswer3.size() == 3) {
                setline("", line3a, line3b, line3c);
                playerAnswer3.remove(playerAnswer3.size() - 1);
                checkLine3 = false;
                hintgame = 2;
            } else if (playerAnswer3.size() == 2) {
                setline("", line2a, line2b, line2c);
                playerAnswer3.remove(playerAnswer3.size() - 1);
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
            if (playerAnswer3.get(playerAnswer3.size() - 1).equals(a.getAnswer2())
                    && hintgame == 1) {
                hintgame = 2;
                checkLine2 = true;
            }
            if (hintgame == 2) {
                if (playerAnswer3.size() == 3) {
                    playerAnswer3.remove(playerAnswer3.size() - 1);
                }
                playerAnswer3.add(a.getAnswer3());
                setline(a.getAnswer3(), line3a, line3b, line3c);
                notification.setText("Congratulation you win.");
                win = true;
                checkLine3 = true;
                hintgame++;
            }
            if (hintgame == 1) {
                if (playerAnswer3.size() == 2) {
                    playerAnswer3.remove(playerAnswer3.size() - 1);
                }
                playerAnswer3.add(a.getAnswer2());
                setline(a.getAnswer2(), line2a, line2b, line2c);
                setline("", line3a, line3b, line3c);
                hintgame++;
                checkLine2 = true;
            }
        }
    }

    public void Next(MouseEvent mouseEvent) {
        try {
            playerAnswer3.clear();
            begin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
