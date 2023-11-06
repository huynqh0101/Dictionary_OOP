package com.example.demo.dictionary.game2;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class game2comandline {
    private List<String> list = new ArrayList<>();

    // đọc file game
    public void readFileQuestion() {
        String path = "game2.txt";
        try (BufferedReader bf = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = bf.readLine()) != null) {
                list.add(line);
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc file " + path);
        }
    }
}
