package com.example.demo.game.game1;

//import javafx.application.Platform;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Game1Controller {

    private Parent game1;
    private Stage window;

    public Game1Controller() {

    }

    public void BacktoChoosegame(MouseEvent mouseEvent) {
        try {
            game1 = FXMLLoader.load(getClass().getResource("/game/Choosegame.fxml"));
            Scene playgame1 = new Scene(game1);
            window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(playgame1);
            window.setTitle("Game !!!");

            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void Howtoplaygame1(MouseEvent mouseEvent) {
        try {
            game1 = FXMLLoader.load(getClass().getResource("/game/game1/Howtoplaygame1.fxml"));
            Scene playgame1 = new Scene(game1);
            window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(playgame1);
            window.setTitle("Game 1");

            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public void Settinggame1(MouseEvent mouseEvent) {
        try {
            game1 = FXMLLoader.load(getClass().getResource("/game/game1/Settinggame1.fxml"));
            Scene playgame1 = new Scene(game1);
            window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(playgame1);
            window.setTitle("Game 1");

            window.show();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void Backtogame1(MouseEvent mouseEvent) {
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


    @FXML
    private Label questiongame;
    @FXML
    private CheckBox a;
    @FXML
    private CheckBox b;
    @FXML
    private CheckBox c;
    @FXML
    private CheckBox d;
    @FXML
    private Label score;
    private int Score = 0;

    @FXML
    private Button submitanswer;

    @FXML
    private Button next_question;
    @FXML
    private Button back_question;

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
        //result_answer = (Label) game1.lookup("#result_answer");

        submitanswer = (Button) game1.lookup("#submitanswer");
        back_question = (Button) game1.lookup("#back_question");
        next_question = (Button) game1.lookup("#next_question");

    }

    public void Playgame1(MouseEvent mouseEvent) throws Exception {
        try {
            game1 = FXMLLoader.load(getClass().getResource("/game/game1/Playgame1.fxml"));
            Scene playgame1 = new Scene(game1);
            window = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
            window.setScene(playgame1);
            window.setTitle("Game 1");
            innitialize();
            readFileQuestion();
            ShowQuestion(1);

            window.show();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // đọc file game
    public void readFileQuestion() {
        String path = "src\\main\\java\\com\\example\\demo\\game\\game1\\game1.txt";
        //String path = "com.example.demo.game1.txt";
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
            //System.out.println("đọc file thành công");
        } catch (IOException e) {
            System.out.println("Lỗi đọc file " + path);
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
        //System.out.println(player.get(count_question).getChooseAnswer());
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
        //d.setDisable(true);
        // mediumspringgreen
        //d.setStyle("-fx-background-color: lightblue;");
        //d.setStyle("-fx-background-color: mediumspringgreen;");
        //d.setStyle("-fx-background-color: tomato;");
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
        //a.setStyle("-fx-text-fill: 0x333333ff;");
        //Color customColor = Color.web("#ff6347");
        //Color customColor = Color.web("#333333ff");
        setColorAnswer("A", "white");
        setColorAnswer("B", "white");
        setColorAnswer("C", "white");
        setColorAnswer("D", "white");
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

                System.out.println(a.getStyle());
                setColorAnswer(player1.getChooseAnswer(), "tomato");
                setColorAnswer(q.getCorrect_answer(), "mediumspringgreen");

            } else {
                setDisableCheckBox(false, false, false, false);
                cleanAnswer();
                //setColorAnswer("A","white");
            }


        } catch (
                Exception e) {
            e.printStackTrace();
            System.out.println("lỗi showquestion");
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

    @FXML
    public void Restart(MouseEvent mouseEvent) {
        Collections.shuffle(questions);
        this.count_question = 1;
        ShowQuestion(1);
        submitanswer.setDisable(true);
        player = new ArrayList<>();
        newPlayer();
        Score = 0;
        setDisableCheckBox(false, false, false, false);
        setSelectCheckBox(false, false, false, false);
        cleanAnswer();
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