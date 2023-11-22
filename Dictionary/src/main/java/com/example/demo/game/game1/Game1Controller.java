package com.example.demo.game.game1;

import com.example.demo.Controllers.DictionaryController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game1Controller {

    private AnchorPane game1;
    private double xOffset = 0;
    private double yOffset = 0;

    public Game1Controller() {
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

    public void Backtogame1(MouseEvent mouseEvent) {
        show(mouseEvent, "/game/game1/Game1.fxml");
    }

    @FXML
    private Label questiongame;
    @FXML
    private CheckBox a,b,c,d;

    @FXML
    private Label score;
    private int Score = 0;

    @FXML
    private Button submitanswer, next_question,back_question;

    private int max_count_question = 10;
    private int count_question = 1;
    public static List<Question> questions;

    public void innitialize() {
        questions = new ArrayList<Question>();
        questiongame = (Label) game1.lookup("#questiongame");
        a = (CheckBox) game1.lookup("#a");
        b = (CheckBox) game1.lookup("#b");
        c = (CheckBox) game1.lookup("#c");
        d = (CheckBox) game1.lookup("#d");
        score = (Label) game1.lookup("#score");
        submitanswer = (Button) game1.lookup("#submitanswer");
        back_question = (Button) game1.lookup("#back_question");
        next_question = (Button) game1.lookup("#next_question");
    }

    public void Playgame1(MouseEvent mouseEvent) throws Exception {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/DictionariesGui.fxml"));
            Parent dictionariesGui = loader.load();
            DictionaryController dictionaryController = loader.getController();
            // Load Game1.fxml
            FXMLLoader game1Loader = new FXMLLoader(getClass().getResource("/game/game1/Playgame1.fxml"));
            game1 = game1Loader.load();
            dictionaryController.setNode(game1);

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
            innitialize();
            readFileQuestion();
            reset();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // read file game
    public void readFileQuestion() {
        String path = "DataGame\\game1.txt";
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                String q = line;
                String a = bf.readLine();
                String b = bf.readLine();
                String c = bf.readLine();
                String d = bf.readLine();
                String correctAnswer = bf.readLine();
                Question question = new Question(q, a, b, c, d, correctAnswer);
                questions.add(question);
                newPlayer();
            }
            Collections.shuffle(questions);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ChooseanswerA(MouseEvent mouseEvent) {
        if (a.isSelected()) {
            player.get(count_question - 1).setChooseAnswer("A");
            submitanswer.setDisable(false);
            b.setSelected(false);
            c.setSelected(false);
            d.setSelected(false);
        } else {
            player.get(count_question - 1).setChooseAnswer("NO");
            submitanswer.setDisable(true);
        }
    }

    public void ChooseanswerB(MouseEvent mouseEvent) {
        if (b.isSelected()) {
            player.get(count_question - 1).setChooseAnswer("B");
            submitanswer.setDisable(false);
            a.setSelected(false);
            c.setSelected(false);
            d.setSelected(false);
        } else {
            player.get(count_question - 1).setChooseAnswer("NO");
            submitanswer.setDisable(true);
        }
    }

    public void ChooseanswerC(MouseEvent mouseEvent) {
        if (c.isSelected()) {
            player.get(count_question - 1).setChooseAnswer("C");
            submitanswer.setDisable(false);
            a.setSelected(false);
            b.setSelected(false);
            d.setSelected(false);
        } else {
            player.get(count_question - 1).setChooseAnswer("NO");
            submitanswer.setDisable(true);
        }
    }

    public void ChooseanswerD(MouseEvent mouseEvent) {
        submitanswer.setDisable(true);
        if (d.isSelected()) {
            player.get(count_question - 1).setChooseAnswer("D");
            submitanswer.setDisable(false);
            a.setSelected(false);
            b.setSelected(false);
            c.setSelected(false);
        } else {
            player.get(count_question - 1).setChooseAnswer("NO");
            submitanswer.setDisable(true);
        }
    }

    private Question q;

    public void setSelectCheckBox(boolean b1, boolean b2, boolean b3, boolean b4) {
        a.setSelected(b1);
        b.setSelected(b2);
        c.setSelected(b3);
        d.setSelected(b4);
    }

    public void setDisableCheckBox(boolean b1, boolean b2, boolean b3, boolean b4) {
        a.setDisable(b1);
        b.setDisable(b2);
        c.setDisable(b3);
        d.setDisable(b4);
    }

    // set Correct answer after submit
    public void setColorAnswer(String s, String color) {
        if (s.equals("A")) {
            a.setStyle("-fx-background-color: " + color + ";");
        } else if (s.equals("B")) {
            b.setStyle("-fx-background-color: " + color + ";");
        } else if (s.equals("C")) {
            c.setStyle("-fx-background-color: " + color + ";");
        } else if (s.equals("D")) {
            d.setStyle("-fx-background-color: " + color + ";");
        }
    }

    public void cleanAnswer() {
        setColorAnswer("A", "transparent");
        setColorAnswer("B", "transparent");
        setColorAnswer("C", "transparent");
        setColorAnswer("D", "transparent");
    }

    @FXML
    public void ShowQuestion(int i) {
        try {
            q = questions.get(i - 1);
            questiongame.setText(i + ". " + q.getQuestion());
            a.setText("A. " + q.getA());
            b.setText("B. " + q.getB());
            c.setText("C. " + q.getC());
            d.setText("D. " + q.getD());
            score.setText("Score: " + Score);
            if (i == 1) {
                back_question.setDisable(true);
            } else back_question.setDisable(false);

            if (i == max_count_question) {
                next_question.setDisable(true);
            } else next_question.setDisable(false);
            Player player1 = player.get(i - 1);

            if (player1.getChooseAnswer().equals("NO")) {
                submitanswer.setDisable(true);
                setSelectCheckBox(false, false, false, false);
            } else {
                if (player1.getChooseAnswer().equals("A")) {
                    setSelectCheckBox(true, false, false, false);
                } else if (player1.getChooseAnswer().equals("B")) {
                    setSelectCheckBox(false, true, false, false);
                } else if (player1.getChooseAnswer().equals("C")) {
                    setSelectCheckBox(false, false, true, false);
                } else if (player1.getChooseAnswer().equals("D")) {
                    setSelectCheckBox(false, false, false, true);
                }
                submitanswer.setDisable(false);
            }

            if (player1.isCheckSubmit()) {
                cleanAnswer();
                setDisableCheckBox(true, true, true, true);
                submitanswer.setDisable(true);
                setColorAnswer(player1.getChooseAnswer(), "tomato");
                setColorAnswer(q.getCorrect_answer(), "mediumspringgreen");
            } else {
                setDisableCheckBox(false, false, false, false);
                cleanAnswer();
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Back_question(MouseEvent mouseEvent) throws Exception {
        try {
            if (count_question > 1) {
                ShowQuestion(--count_question);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    public void Next_question(MouseEvent mouseEvent) throws Exception {
        try {
            if (this.count_question < max_count_question) {
                ShowQuestion(++count_question);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void reset() {
        ShowQuestion(1);
        submitanswer.setDisable(true);
        player = new ArrayList<>();
        newPlayer();
        Score = 0;
        setDisableCheckBox(false, false, false, false);
        setSelectCheckBox(false, false, false, false);
        cleanAnswer();
    }

    @FXML
    public void Restart(MouseEvent mouseEvent) {
        Collections.shuffle(questions);
        this.count_question = 1;
        reset();
    }

    private static List<Player> player = new ArrayList<>();

    public void newPlayer() {
        for (int i = 0; i < max_count_question; i++) {
            String chooseAnswer = "NO";
            boolean checkCorrect = false;
            boolean checkSubmit = false;
            player.add(new Player(chooseAnswer, checkCorrect, checkSubmit));
        }
    }

    public void Submitanswer(MouseEvent mouseEvent) {
        String Correct_answer = questions.get(count_question - 1).getCorrect_answer();
        String Player_answer = player.get(count_question - 1).getChooseAnswer();
        boolean checkCorrect = Correct_answer.equals(Player_answer);
        if (checkCorrect) Score += 10;
        player.get(count_question - 1).setCheckCorrect(checkCorrect);
        player.get(count_question - 1).setCheckSubmit(true);
        ShowQuestion(count_question);
    }
}