package com.example.demo.App;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Application extends javafx.application.Application {
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage stage) throws Exception {
<<<<<<< HEAD:Dictionary/src/main/java/com/example/demo/App/LoginApplication.java
        FXMLLoader fxmlLoader = new FXMLLoader(LoginApplication.class.getResource("/View/Notice.fxml"));
=======
        FXMLLoader fxmlLoader = new FXMLLoader(Application.class.getResource("/View/login.fxml"));
>>>>>>> db39c4602301afa807dc01866afe97fa0e2743ea:Dictionary/src/main/java/com/example/demo/App/Application.java
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.setScene(scene);
        root.requestFocus();
        stage.show();

        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
