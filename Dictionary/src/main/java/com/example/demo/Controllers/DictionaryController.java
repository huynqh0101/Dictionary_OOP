package com.example.demo.Controllers;

import com.example.demo.Alerts.Alerts;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class   DictionaryController implements Initializable {
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

        t1.setShowDelay(Duration.seconds(0.4));
        t2.setShowDelay(Duration.seconds(0.4));
        t3.setShowDelay(Duration.seconds(0.4));
        t4.setShowDelay(Duration.seconds(0.4));
        t5.setShowDelay(Duration.seconds(0.4));
        t6.setShowDelay(Duration.seconds(0.4));
        showComponent("/View/SearchWordGui.fxml");

        exitBtn.setOnMouseClicked(e -> {
            Alerts alerts = new Alerts();
            Alert alertConfirm = alerts.alertConfirm("Exit", "Bạn có chắc muốn thoát ứng dụng ?");
            Optional<ButtonType> option = alertConfirm.showAndWait();
            if (option.get() == ButtonType.OK) {
                System.exit(0);
            } else {
                alerts.showAlertInfo("Exit", "Tiếp tục sử dụng ứng dụng!");
            }
        });
    }

    private void setNode(Node n) {
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
    private Button addBtn, transBtn, searchBtn, exitBtn, tbaoBtn;

}
