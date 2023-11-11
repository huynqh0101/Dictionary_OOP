package com.example.demo.Controllers;

import com.example.demo.Dictionary.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class SearchWordController implements Initializable {
    private static final String DATA_FILE_PATH = "data/dictionary.txt";
    private Dictionary dictionary = new Dictionary();
    private DictionaryManagement dictionaryManagement = new DictionaryManagement(dictionary);
    private SwitchSceneController switchSceneController = new SwitchSceneController();
    private Alerts alerts = new Alerts();
    @FXML
    private ListView<String> listView;
    ObservableList<String> list = FXCollections.observableArrayList();
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
    private Label englishWord;
    private int firstIndexOfListFound = 0;

    private int indexOfSelectedWord;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        dictionaryManagement.insertFromFile(dictionary, DATA_FILE_PATH);
        setListDefault(0);

        findWord.setOnKeyTyped(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (findWord.getText().isEmpty()) {
                    setListDefault(0);
                } else {
                    handleOnKeyTyped();
                }
            }
        });
        listenButton.setVisible(false);
        editButton.setVisible(false);
        deleteButton.setVisible(false);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        wordExplain.setEditable(false);
        englishWord.setText("");
    }

    @FXML
    private void handleOnKeyTyped() {
        list.clear();
        String searchWord = findWord.getText().trim();
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
    private void handleClickEditBtn() {
        wordExplain.setEditable(true);
        saveButton.setVisible(true);
        cancelButton.setVisible(true);
        alerts.showAlertInfo("Information", "Bạn đã cho phép chỉnh sửa nghĩa từ này!");
    }

    @FXML
    private void handleClickSaveBtn() {
        Alert alertConfirm = alerts.alertConfirm("Update", "Bạn chắc chắn muốn cập nhật nghĩa từ này ?");
        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            dictionaryManagement.updateWord(dictionary, indexOfSelectedWord, wordExplain.getText());
            alerts.showAlertInfo("Information", "Cập nhập thành công!");
        } else alerts.showAlertInfo("Information", "Thay đổi không được công nhận!");
        saveButton.setVisible(false);
        wordExplain.setEditable(false);
        wordExplain.setText("");
        cancelButton.setVisible(false);
    }

    @FXML
    private void handleClickCancelBtn() {
        Alert alertConfirm = alerts.alertConfirm("Cancel", "Hủy chỉnh sửa nghĩa của từ ?");
        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            wordExplain.setEditable(false);
            cancelButton.setVisible(false);
            saveButton.setVisible(false);
            wordExplain.setText("");
            alerts.showAlertInfo("Information", "Hủy chỉnh sửa!");
        } else {
            wordExplain.setEditable(true);
            alerts.showAlertInfo("Information", "Tiếp tục chỉnh sửa!");
        }
    }

    @FXML
    private void handleClickDeleteBtn() {
        Alert alertWarning = alerts.alertWarning("Delete", "Bạn chắc chắn muốn xóa từ này?");
        alertWarning.getButtonTypes().add(ButtonType.CANCEL);
        Optional<ButtonType> option = alertWarning.showAndWait();
        if (option.get() == ButtonType.OK) {
            dictionaryManagement.deleteWord(dictionary, indexOfSelectedWord, DATA_FILE_PATH);
            refreshAfterDeleting();
            alerts.showAlertInfo("Information", "Xóa thành công");
        } else alerts.showAlertInfo("Information", "Thay đổi không được công nhận");
    }

    private void refreshAfterDeleting() {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(findWord.getText())) {
                list.remove(i);
                break;
            }
        listView.setItems(list);
        wordExplain.setVisible(false);
    }

    private void setListDefault(int index) {
        list.clear();
        for (int i = 0; i < dictionary.size(); i++) list.add(dictionary.get(i).getWordTarget());
        listView.setItems(list);
    }

    @FXML
    private void menuAddWordButton(ActionEvent event) throws IOException {
        switchSceneController.switchToAddWordScene(event);
    }

    @FXML
    private void backToMenuButton(ActionEvent event) throws IOException {
        switchSceneController.switchToMenuScene(event);
    }

    @FXML
    private void menuGameButton(ActionEvent event) throws IOException {
        switchSceneController.switchToGameScene(event);
    }

    @FXML
    private void menuTranslationButton(ActionEvent event) throws IOException {
        switchSceneController.switchToTranslationScene(event);
    }

    @FXML
    private void menuExitButton(ActionEvent event) {
        switchSceneController.Exit();
    }
}
