package com.example.demo.game;

import javafx.scene.layout.AnchorPane;

public class SharedData {
    private static SharedData instance;

    private AnchorPane sharedAnchorPane;

    private SharedData() {
        // Private constructor to enforce singleton pattern
    }

    public static synchronized SharedData getInstance() {
        if (instance == null) {
            instance = new SharedData();
        }
        return instance;
    }

    public AnchorPane getSharedAnchorPane() {
        return sharedAnchorPane;
    }

    public void setSharedAnchorPane(AnchorPane anchorPane) {
        this.sharedAnchorPane = anchorPane;
    }
}

