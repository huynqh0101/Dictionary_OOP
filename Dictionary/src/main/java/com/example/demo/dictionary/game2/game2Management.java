package com.example.demo.dictionary.game2;

import com.example.demo.dictionary.Word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class game2Management {
    private Scanner scanner;
    List<Data> list = new ArrayList<>();

    // đọc file game
    public void insertFromFile() {
        // String fileName = scanner.nextLine();
        String fileName = "Dictionary\\src\\main\\java\\com\\example\\demo\\dictionary\\game2\\game2.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            int id = 0;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\t");
                if (parts.length >= 2) {
                    String wordTarget = parts[0].trim();
                    String wordExplain = parts[1].trim();
                    Word word = new Word(wordTarget, wordExplain);
                    Data data = new Data(word, id++, 1);
                    list.add(data);
                }
            }
        } catch (IOException e) {
            System.out.println("Error while reading the file " + fileName);
        }
    }




}
