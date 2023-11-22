package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class DictionaryController implements Initializable {
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/View/SearchWordGui.fxml");
            }
        });

        addBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/View/AddWordGui.fxml");
            }
        });

        transBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/View/TranslationGui.fxml");
            }
        });
        tbaoBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/View/Notice.fxml");
            }
        });
        gameBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                showComponent("/game/Choosegame.fxml");
            }
        });

        t1.setShowDelay(Duration.seconds(0.4));
        t2.setShowDelay(Duration.seconds(0.4));
        t3.setShowDelay(Duration.seconds(0.4));
        t4.setShowDelay(Duration.seconds(0.4));
        t5.setShowDelay(Duration.seconds(0.4));
        t6.setShowDelay(Duration.seconds(0.4));
        showComponent("/View/Notice.fxml");

        exitBtn.setOnMouseClicked(e -> {
            Alerts alerts = new Alerts();
            Alert alertConfirm = alerts.alertConfirm("Log out", "Bạn có chắc muốn đăng xuất ứng dụng ?");
            Optional<ButtonType> option = alertConfirm.showAndWait();
            if (option.isPresent() && option.get() == ButtonType.OK) {
                Stage currentStage = (Stage) exitBtn.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/View/login.fxml"));
                try {
                    Parent loginScreen = loader.load();
                    Scene loginScene = new Scene(loginScreen);
                    currentStage.setScene(loginScene);
                    loginScene.setOnMousePressed(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            xOffset = event.getSceneX();
                            yOffset = event.getSceneY();
                        }
                    });

                    loginScreen.setOnMouseDragged(new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            currentStage.setX(event.getScreenX() - xOffset);
                            currentStage.setY(event.getScreenY() - yOffset);
                        }
                    });

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                alerts.showAlertInfo("Exit", "Tiếp tục sử dụng ứng dụng!");
            }
        });
    }
    private double xOffset = 0;
    private double yOffset = 0;

    public void setNode(Node n) {
        container1.getChildren().clear();
        container1.getChildren().add(n);
    }

    @FXML
    private void showComponent(String p) {
        try {
            AnchorPane component = FXMLLoader.load(getClass().getResource(p));
            setNode(component);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private AnchorPane container1;

    @FXML
    private Tooltip t1, t2, t3, t4, t5, t6;

    @FXML
    private Button addBtn, transBtn, searchBtn, exitBtn, tbaoBtn, gameBtn;

}
