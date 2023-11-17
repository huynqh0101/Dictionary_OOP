package com.example.demo.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;


public class TranslationController implements Initializable {

    @FXML
    private Tooltip tooltip1, tooltip2;

    private String sourceLanguage = "en";
    private String toLanguage = "vi";
    private boolean isToVietnameseLang = true;

    String s1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        translateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    handleOnClickTranslateBtn();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        sourceLangField.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (sourceLangField.getText().trim().isEmpty()) translateBtn.setDisable(true);
                else translateBtn.setDisable(false);
            }
        });

        translateBtn.setDisable(true);
        toLangField.setEditable(false);
        tooltip1.setShowDelay(Duration.seconds(0.4));
        tooltip2.setShowDelay(Duration.seconds(0.4));
    }

    @FXML
    private void handleOnClickTranslateBtn() throws IOException {
        String srcText = sourceLangField.getText();
        //System.out.println(srcText);
        String rootAPI = "https://script.google.com/macros/s/AKfycbxy9-PUM1B4wk5gQvpvg5a-hiFmsQ2N_FCoJgzedu0auH4vmsAxyKyOB4eDmeBbuQWX4A/exec" +
                "?q=" + URLEncoder.encode(srcText, "UTF-8") +
                "&target=" + toLanguage +
                "&source=" + sourceLanguage;


        URL url = new URL(rootAPI);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        s1 = srcText;
        toLangField.setText(response.toString());
    }

    MediaPlayer mediaPlayer;

    public void playAudioFromDataFolder(String fileName) {
        try {
            String dataFolderPath = "Data"; // Đường dẫn tương đối tới thư mục Data
            String filePath = dataFolderPath + File.separator + fileName; // Tạo đường dẫn tới tệp trong thư mục Data

            File file = new File(filePath);

            if (file.exists()) {
                Media media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.play();
            } else {
                System.out.println("Không tìm thấy tệp tin: " + fileName);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handle1() {
        if (sourceLangField.getText() == null || sourceLangField.getText().trim().isEmpty()) {
            showAlert("Bạn phải nhập nguồn dịch!", Alert.AlertType.ERROR);
        } else {
            createMP3s();
            String fileName = isToVietnameseLang ? "output_en.mp3" : "output_vi.mp3";
            playAudioFromDataFolder(fileName);
        }
    }

    @FXML
    private void handle2() {
        if (sourceLangField.getText() == null || sourceLangField.getText().trim().isEmpty()) {
            showAlert("Bạn phải nhập nguồn dịch!", Alert.AlertType.ERROR);
        } else {
            createMP3s();
            String fileName = isToVietnameseLang ? "output_vi.mp3" : "output_en.mp3";
            playAudioFromDataFolder(fileName);
        }
    }


    @FXML
    private void handleOnClickSwitchToggle() {
        sourceLangField.clear();
        toLangField.clear();
        if (isToVietnameseLang) {
            englishLabel.setLayoutX(426);
            vietnameseLabel.setLayoutX(104);
            sourceLanguage = "vi";
            toLanguage = "en";
        } else {
            englishLabel.setLayoutX(100);
            vietnameseLabel.setLayoutX(426);
            sourceLanguage = "en";
            toLanguage = "vi";
        }
        isToVietnameseLang = !isToVietnameseLang;
        System.out.println(isToVietnameseLang);
    }

    public void createMP3s() {
        String text1 = sourceLangField.getText();
        String text2 = toLangField.getText();
        String language1 = "en";
        String language2 = "vi";
        String url1 = null;
        String url2 = null;


        // Loại bỏ khoảng trắng trong văn bản
        String result1 = text1.replaceAll("\\s", "");
        String result2 = text2.replaceAll("\\s", "");
        String outputPath1 = null;
        String outputPath2 =null;


        try {
            System.out.println(isToVietnameseLang);
            String dataFolderPath = "Data";
            if (isToVietnameseLang) {
                 url1 = "https://translate.google.com/translate_tts?ie=UTF-8&client=tw-ob&tl=" + language1 + "&q=" + result1;
                 url2 = "https://translate.google.com/translate_tts?ie=UTF-8&client=tw-ob&tl=" + language2 + "&q=" + result2;
                 outputPath1 = dataFolderPath + File.separator + "output_en.mp3";
                 outputPath2 = dataFolderPath + File.separator + "output_vi.mp3";
            } else {
                 url1 = "https://translate.google.com/translate_tts?ie=UTF-8&client=tw-ob&tl=" + language2 + "&q=" + result1;
                 url2 = "https://translate.google.com/translate_tts?ie=UTF-8&client=tw-ob&tl=" + language1 + "&q=" + result2;
                 outputPath2 = dataFolderPath + File.separator + "output_en.mp3";
                 outputPath1 = dataFolderPath + File.separator + "output_vi.mp3";
            }

            CloseableHttpClient httpClient1 = HttpClients.createDefault();
            HttpGet request1 = new HttpGet(url1);

            HttpResponse response1 = httpClient1.execute(request1);
            int statusCode1 = response1.getStatusLine().getStatusCode();
            if (statusCode1 == 200) {
                byte[] audioData1 = EntityUtils.toByteArray(response1.getEntity());

                try (FileOutputStream fileOutputStream1 = new FileOutputStream(outputPath1)) {
                    fileOutputStream1.write(audioData1);
                }
            } else {
                System.out.println("Yêu cầu tiếng Anh không thành công. Mã lỗi: " + statusCode1);
            }

            CloseableHttpClient httpClient2 = HttpClients.createDefault();
            HttpGet request2 = new HttpGet(url2);

            HttpResponse response2 = httpClient2.execute(request2);
            int statusCode2 = response2.getStatusLine().getStatusCode();
            if (statusCode2 == 200) {
                byte[] audioData2 = EntityUtils.toByteArray(response2.getEntity());

                try (FileOutputStream fileOutputStream2 = new FileOutputStream(outputPath2)) {
                    fileOutputStream2.write(audioData2);
                }
            } else {
                System.out.println("Yêu cầu tiếng Vệt không thành công. Mã lỗi: " + statusCode1);
            }

            httpClient1.close();
            httpClient2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void showAlert(String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Thông báo:");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private TextArea sourceLangField, toLangField;

    @FXML
    private Button translateBtn;

    @FXML
    private Label englishLabel, vietnameseLabel;
}
