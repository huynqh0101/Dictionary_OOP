package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.Dictionary.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class AddWordController implements Initializable {
    private LoginController loginController = new LoginController();
    private static final String DATA_FILE_PATH = "data/dictionaries.txt";
    private String dataChangedFile = loginController.getFileDataOfAccount();
    private Dictionary dictionary = new Dictionary();
    private Alerts alerts = new Alerts();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement(dictionary);

    @FXML
    private Button addButton;

    @FXML
    private TextField wordTargetInput;

    @FXML
    private TextArea wordExplainInput;

    @FXML
    private Label successAlert;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(dictionary, DATA_FILE_PATH);
        dictionaryManagement.insertFromFileChange(dictionary, dataChangedFile);
        updateButtonState();

        wordTargetInput.setOnKeyTyped(event -> updateButtonState());
        wordExplainInput.setOnKeyTyped(event -> updateButtonState());

        successAlert.setVisible(false);
    }

    private void updateButtonState() {
        addButton.setDisable(wordExplainInput.getText().isEmpty() || wordTargetInput.getText().isEmpty());
    }

    @FXML
    private void clickAddButton() {
        Alert alertConfirmation = alerts.alertConfirm("Add word",
                "Bạn có chắc muốn thêm từ này vào từ điển?");
        Optional<ButtonType> option = alertConfirmation.showAndWait();

        if (option.get() == ButtonType.OK) {
            String wordTarget = wordTargetInput.getText().trim();
            String wordExplain = wordExplainInput.getText().trim();
            handleAddButtonClick(wordTarget, wordExplain);
        } else if (option.get() == ButtonType.CANCEL)
            alerts.showAlertInfo("Information", "Thay đổi không được công nhận.");
    }

    private void handleAddButtonClick(String wordTarget, String wordExplain) {
        Word word = new Word(wordTarget, wordExplain);
        if (dictionaryManagement.contains(word)) {
            int indexOfWord = dictionaryManagement.dictionarySearcher(dictionary, wordTarget);
            Alert selectionAlert = alerts.alertConfirm("This word already exists",
                    "Từ này đã tồn tại.\nThay thế hoặc bổ sung nghĩa.");
            selectionAlert.getButtonTypes().clear();
            ButtonType replaceButton = new ButtonType("Thay thế");
            ButtonType insertButton = new ButtonType("Bổ sung");
            selectionAlert.getButtonTypes().addAll(replaceButton, insertButton, ButtonType.CANCEL);
            Optional<ButtonType> selection = selectionAlert.showAndWait();

            if (selection.get() == replaceButton) {
                dictionary.get(indexOfWord).setWordExplain(wordExplain);
                dictionaryManagement.saveChangeToFile("Edit", dictionary.get(indexOfWord), dataChangedFile);
                showSuccessAlert();
            }
            if (selection.get() == insertButton) {
                String oldMeaning = dictionary.get(indexOfWord).getWordExplain();
                dictionary.get(indexOfWord).setWordExplain(oldMeaning + "\n= " + wordExplain);
                dictionaryManagement.saveChangeToFile("Edit", dictionary.get(indexOfWord), dataChangedFile);
                showSuccessAlert();
            }
            if (selection.get() == ButtonType.CANCEL)
                alerts.showAlertInfo("Information", "Thay đổi không được công nhận.");
        } else {
            dictionary.add(word);
            dictionaryManagement.saveChangeToFile("Add", word, dataChangedFile);
            showSuccessAlert();
        }

        addButton.setDisable(true);
        wordTargetInput.setText("");
        wordExplainInput.setText("");
    }

    private void showSuccessAlert() {
        successAlert.setVisible(true);
        dictionaryManagement.setTimeout(() -> successAlert.setVisible(false), 1500);
    }
}
