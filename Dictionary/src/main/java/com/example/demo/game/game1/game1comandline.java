package com.example.demo.game.game1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import java.util.Scanner;


public class game1comandline {
    private List<Question> questions = new ArrayList<>();

    // đọc file game
    public void readFileQuestion() {
        String path = "Dictionary\\src\\main\\java\\com\\example\\demo\\dictionary\\com.example.demo.game1\\com.example.demo.game1.txt";
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
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file " + path);
        }
    }

    // hiển thị câu hỏi
    public void showQuestion(Question q) {
        System.out.println(q.getQuestion());
        System.out.println("\t[A] " + q.getA()
                + "\n\t[B] " + q.getB()
                + "\n\t[C] " + q.getC()
                + "\n\t[D] " + q.getD());
    }

    // hiển thị các đáp án và yêu cầu nhập câu hỏi
    public void chooseAnswer(Question q) {
        System.out.print("Your choice [A/B/C/D]: ");
        Scanner sc = new Scanner(System.in);
        String answer = sc.next().toUpperCase();
        while (!(answer.equals("A") || answer.equals("B") || answer.equals("C") || answer.equals("D"))) {
            //System.out.println(answer);
            System.out.println("Invalid, please re-enter!");
            System.out.print("Your choice [A/B/C/D]: ");
            answer = sc.next().toUpperCase();
        }

        if (q.getCorrect_answer().equals(answer)) {
            System.out.println("Congratulations on your correct answer !!!");
        } else {
            System.out.println("Sorry your answer is wrong !! \n the answer is " + q.getCorrect_answer());
        }
    }

    // kiểm tra xem người chơi có muốn tiếp tục không
    public void game_continue(boolean next) {
        System.out.println("You want next ?  (Y/N): ");
        Scanner sc = new Scanner(System.in);
        String hasnext = sc.next().toUpperCase();
        if (hasnext == "N") {
            next = false;
        } else next = true;
    }

    public void play() {
        readFileQuestion();
        Collections.shuffle(questions);
        boolean next = true;
        int count = 20;
        while (next) {
            showQuestion(questions.get(0));
            chooseAnswer(questions.get(0));
            questions.remove(0);
            game_continue(next);
            count--;
            if (count <= 0) break;
        }
    }

}
