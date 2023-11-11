package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

import java.io.*;
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
        String filePath = "C:\\Users\\Huy\\IdeaProjects\\Dictionary_OOP\\dictionaries.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            List<String> lines = new ArrayList<>();
            String line;

            // Đọc tất cả các dòng từ file
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Lấy ngày hiện tại để làm seed cho số ngẫu nhiên
            Date currentDate = new Date();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
            String currentDateStr = dateFormat.format(currentDate);

            // Tạo tên file selectedWords với ngày hiện tại
            String selectedWordsFileName = "C:\\Users\\Huy\\IdeaProjects\\Dictionary_OOP\\selectedWords_" + currentDateStr + ".txt";

            // Tạo một số ngẫu nhiên với seed là ngày hiện tại
            Random random = new Random(currentDate.getTime());

            // Đọc danh sách từ đã được chọn trong ngày
            List<String> selectedWords = readSelectedWords(selectedWordsFileName);

            // Nếu danh sách trống hoặc đã hết từ, thực hiện random lại
            if (selectedWords.isEmpty() || selectedWords.size() >= lines.size()) {
                // Chọn một vị trí ngẫu nhiên để bắt đầu
                int startIndex = random.nextInt(lines.size() - 15 + 1);

                // Lưu lại danh sách từ đã được chọn với ngày hiện tại
                selectedWords.clear();
                selectedWords.add(currentDateStr);
                for (int i = startIndex; i < startIndex + 15 && i < lines.size(); i++) {
                    selectedWords.add(lines.get(i));
                }

                // Lưu danh sách vào file
                writeSelectedWords(selectedWords, selectedWordsFileName);
            }

            // In danh sách từ đã được chọn lên TextArea
            for (int i = 1; i < selectedWords.size(); i++) {
                String[] parts = selectedWords.get(i).split("\t");
                String wordE = capitalize(parts[0]);
                explanation.appendText((i) + ". " + wordE + ":  " + parts[1] + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String capitalize(String str) {
        return str.substring(0, 1).toUpperCase() + str.substring(1);
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
}
