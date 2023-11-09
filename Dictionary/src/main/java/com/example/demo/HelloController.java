package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {
    @FXML
    private Label hellolabel;

    @FXML
    protected void onHelloButtonClick() {
        hellolabel.setText("Welcome to JavaFX Application!");
    }
}