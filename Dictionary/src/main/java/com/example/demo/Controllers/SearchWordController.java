package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import com.example.demo.Dictionary.*;
import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchWordController implements Initializable {
    private LoginController loginController = new LoginController();
    private static final String DATA_FILE_PATH = "data/dictionaries.txt";
    private String dataChangedFile = loginController.getFileDataOfAccount();
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement(dictionary);
    private Alerts alerts = new Alerts();
    ObservableList<String> list = FXCollections.observableArrayList();
    @FXML
    private ListView<String> listView;
    @FXML
    private TextField findWord;
    @FXML
    private TextArea wordExplain;
    @FXML
    private Button deleteButton;
    @FXML
    private Button editButton;
    @FXML
    private Button listenButton;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;
    @FXML
    private Label englishWord, labelWar;
    private int firstIndexOfListFound = 0;
    private int indexOfSelectedWord;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(dictionary, DATA_FILE_PATH);
        dictionaryManagement.insertFromFileChange(dictionary, dataChangedFile);
        setListDefault(0);

        findWord.setOnKeyTyped(event -> {
            if (findWord.getText().isEmpty()) {
                setListDefault(0);
            } else {
                handleOnKeyTyped();
            }
        });

        listenButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        wordExplain.setEditable(false);
        labelWar.setVisible(false);
        englishWord.setText("");
    }

    @FXML
    private void handleOnKeyTyped() {
        list.clear();
        String searchWord = findWord.getText().toLowerCase().trim();
        list = dictionaryManagement.lookupWord(dictionary, searchWord);
        if (list.isEmpty()) {
            setListDefault(firstIndexOfListFound);
            listenButton.setVisible(false);
            editButton.setVisible(false);
            deleteButton.setVisible(false);
            saveButton.setVisible(false);
            cancelButton.setVisible(false);
            wordExplain.setEditable(false);
            wordExplain.setText("");
            englishWord.setText("");
            labelWar.setVisible(true);
            dictionaryManagement.setTimeout(() -> labelWar.setVisible(false), 2000);
        } else {
            listView.setItems(list);
            firstIndexOfListFound = dictionaryManagement.dictionarySearcher(dictionary, list.get(0));
        }
    }

    @FXML
    private void handleMouseClickAWord(MouseEvent arg0) {
        String selectedWord = listView.getSelectionModel().getSelectedItem();
        if (selectedWord != null) {
            indexOfSelectedWord = dictionaryManagement.dictionarySearcher(dictionary, selectedWord);
            if (indexOfSelectedWord == -1) return;
            findWord.setText(dictionary.get(indexOfSelectedWord).getWordTarget());
            wordExplain.setText(dictionary.get(indexOfSelectedWord).getWordExplain());
            wordExplain.setVisible(true);
            wordExplain.setEditable(false);
            listenButton.setVisible(true);
            editButton.setVisible(true);
            deleteButton.setVisible(true);
            englishWord.setText(selectedWord);
        }
    }

    @FXML
    private void handleListenButton() {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        Voice voice = VoiceManager.getInstance().getVoice("kevin");
        voice.allocate();
        voice.speak(dictionary.get(indexOfSelectedWord).getWordTarget());
    }

    @FXML
    private void handleEditButton() {
        wordExplain.setEditable(true);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
        alerts.showAlertInfo("Information", "Bạn đã cho phép chỉnh sửa nghĩa của từ này!");
    }

    @FXML
    private void handleSaveButton() {
        Alert alertConfirm = alerts.alertConfirm("Update", "Bạn có chắc muốn cập nhật nghĩa của từ này ?");
        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            dictionaryManagement.updateWord(dictionary, indexOfSelectedWord, wordExplain.getText());
            dictionaryManagement.saveChangeToFile("Edit", dictionary.get(indexOfSelectedWord), dataChangedFile);
            alerts.showAlertInfo("Information", "Cập nhập thành công!");
        } else alerts.showAlertInfo("Information", "Cập nhập KHÔNG thành công!");
        saveButton.setVisible(false);
        wordExplain.setEditable(false);
        wordExplain.setText("");
        cancelButton.setVisible(false);
        wordExplain.setText(dictionary.get(indexOfSelectedWord).getWordExplain());
    }

    @FXML
    private void handleCancelButton() {
        Alert alertConfirm = alerts.alertConfirm("Cancel", "Bạn có chắc muốn hủy chỉnh sửa nghĩa của từ ?");
        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            wordExplain.setEditable(false);
            cancelButton.setVisible(false);
            saveButton.setVisible(false);
            wordExplain.setText(dictionary.get(indexOfSelectedWord).getWordExplain());
            alerts.showAlertInfo("Information", "Hủy chỉnh sửa!");
        } else {
            wordExplain.setEditable(true);
            alerts.showAlertInfo("Information", "Tiếp tục chỉnh sửa!");
        }
    }

    @FXML
    private void handleDeleteButton() {
        Alert alertWarning = alerts.alertWarning("Delete", "Bạn có chắc muốn xóa từ này?");
        alertWarning.getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> option = alertWarning.showAndWait();
        if (option.get() == ButtonType.OK) {
            dictionaryManagement.saveChangeToFile("Delete", dictionary.get(indexOfSelectedWord), dataChangedFile);
            dictionaryManagement.deleteWord(dictionary, indexOfSelectedWord);
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).equals(findWord.getText())) {
                    list.remove(i);
                    break;
                }
            }
            setListDefault(0);
            wordExplain.setText("");
            englishWord.setText("");
            findWord.setText("");
            alerts.showAlertInfo("Information", "Xóa thành công!");
        } else alerts.showAlertInfo("Information", "Hủy bỏ xóa từ!");
    }

    private void setListDefault(int index) {
        list.clear();
        for (int i = 0; i < index + 15; i++) list.add(dictionary.get(i).getWordTarget());
        listView.setItems(list);
    }
}
