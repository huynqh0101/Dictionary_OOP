package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class NoticeController {

    @FXML
    private TextArea explanation;

    @FXML
    public void initialize() {
        loadDataFromFile();
    }

    private void loadDataFromFile() {
        String fileName = "Data/Chudetuvung.txt"; // Đường dẫn tương đối

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lines = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Get seed random
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDateStr = dateFormat.format(currentDate);
            String selectedWordsFileName = "Data/"+"selectedWords" + ".txt"; // Đường dẫn tương đối

            Random random = new Random(currentDate.getTime());
            List<String> selectedWords = readSelectedWords(selectedWordsFileName);
            //System.out.println(currentDateStr +" "+ selectedWords.get(0));
            if (!currentDateStr.equals(selectedWords.get(0))) {
                int startIndex = random.nextInt(lines.size() - 15 + 1);
                selectedWords.clear();
                selectedWords.add(currentDateStr);
                for (int i = startIndex; i < startIndex + 15 && i < lines.size(); i++) {

                    String selectedLine = lines.get(i);
                    if (!selectedLine.endsWith("///")) {
                        selectedWords.add(selectedLine);
                        markWordAsSelected(fileName, selectedLine);
                    }
                }

                writeSelectedWords(selectedWords, selectedWordsFileName);
                //System.out.println("khác");
            }

            for (int i = 1; i < selectedWords.size(); i++) {
                explanation.appendText((i) + ". " + selectedWords.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<String> readSelectedWords(String fileName) {
        // Đọc danh sách từ đã được chọn từ file
        List<String> selectedWords = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                selectedWords.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedWords;
    }

    private void writeSelectedWords(List<String> selectedWords, String fileName) {
        // Ghi danh sách từ đã được chọn vào file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String word : selectedWords) {
                writer.write(word + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void markWordAsSelected(String fileName, String selectedLine) {
        try {
            Path filePath = Paths.get(fileName);
            List<String> lines = new ArrayList<>();
            try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath.toFile()))) {
                String line;
                while ((line = fileReader.readLine()) != null) {
                    lines.add(line);
                }
            }

            for (int i = 0; i < lines.size(); i++) {
                if (lines.get(i).startsWith(selectedLine)) {
                    lines.set(i, lines.get(i) + "///");
                    break;
                }
            }

            // Ghi lại nội dung đã được cập nhật vào file
            try (BufferedWriter fileWriter = new BufferedWriter(new FileWriter(filePath.toFile()))) {
                for (String line : lines) {
                    fileWriter.write(line + "\n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
