package com.example.demo.Controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.scene.control.Alert.AlertType;

public class LoginController {
    @FXML
    private Button signupBtn;
    @FXML
    private Button loginBtn1;
    @FXML
    private Button loginBtn2;
    @FXML
    private PasswordField passWord1;
    private boolean focusLost = false;
    @FXML
    private PasswordField confirmPassWord1;
    @FXML
    private TextField userNameBtn2;
    @FXML
    private PasswordField passWord2;
    @FXML
    private TextField userNameBtn;
    @FXML
    private CheckBox showPassword;
    @FXML
    private CheckBox showPassword2;
    @FXML
    private Label showLabel;
    @FXML
    private Label showLabel2;

    @FXML
    public void registerButtonClicked() {
        String userNameText = userNameBtn.getText();
        String passwordText = passWord1.getText();
        boolean ktra = comparePasswords();
        if(ktra) {
            saveCredentialsToFile(userNameText, passwordText);
        }
    }
    @FXML
    public void loginButtonclick() {
        String username = userNameBtn2.getText();
        String password = passWord2.getText();

        System.out.println(username + " " +password);
        if (authenticate(username, password)) {
            showAlert("Bạn đã tạo tài khoản thành công !", AlertType.INFORMATION);
        } else {
            showAlert("Đăng nhập thất bại !!!, Kiểm tra lại tài khoản hoặc mật khẩu!", AlertType.ERROR);
        }
    }

    private void saveCredentialsToFile(String userName, String password) {
        try {
            String fileName = "Data/credentials.txt";

            boolean userExists = checkIfUserExists(fileName, userName);

            if (!userExists) {
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("Username: " + userName);
                writer.newLine();
                writer.write("Password: " + password);
                writer.newLine();
                writer.close();
                showAlert("Bạn đã tạo tài khoản thành công !", AlertType.INFORMATION);
            } else {
                showAlert("Tài khoản này đã tồn tại !", AlertType.INFORMATION);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Data/credentials.txt"))) {
            String line;
            String storedUsername = null;
            String storedPassword = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Username")) {
                    storedUsername = line.split(":", 2)[1].trim();
                }
                if (line.contains("Password")) {
                    storedPassword = line.split(":", 2)[1].trim();
                }

                if (storedUsername != null && storedPassword != null) {
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        return true;
                    }
                    storedUsername = null;
                    storedPassword = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Đăng nhập thất bại
    }

    private boolean checkIfUserExists(String fileName, String targetUsername) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String existingUsername = line.substring("Username: ".length());
                    if (existingUsername.equals(targetUsername)) {
                        reader.close();
                        return true; // Tài khoản đã tồn tại
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Tài khoản chưa tồn tại
    }


    @FXML
    public void handlePasswordKeyReleased() {
        String password1 = passWord1.getText();
        if (password1.length() < 8) {
            showAlert("Độ dài mật khẩu không đủ 8 ký tự !", AlertType.ERROR);
        }
        focusLost = true;
    }

    @FXML
    private boolean comparePasswords() {
        String s2="";
        String password1 = passWord1.getText();
        String password2 = confirmPassWord1.getText();
            if(password1.equals(s2) && password2.equals(s2)) {
                showAlert("Bạn phải nhập mật khẩu !", AlertType.ERROR);
                return false;
            }
            if (password1.equals(password2)) {
                return true;
            } else {
                showAlert("Mật khẩu không giống nhau !", AlertType.ERROR);
                return false;
            }
    }
    private void showAlert(String message, AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle("Thông báo:");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void exitBtnClick() {
        Platform.exit();
    }

    @FXML
    protected void loginBtnClick() {
        Node currentNode = loginBtn1;
        showComponent(currentNode, "login.fxml");
    }

    @FXML
    protected void signupBtnClick() {
        Node currentNode = signupBtn;
        showComponent(currentNode, "signup.fxml");
    }
    @FXML
    protected void showpassword1() {
        if(showPassword.isSelected()) {
            showLabel.setText(passWord2.getText());
        } else {
            showLabel.setText("");
        }
    }
    @FXML
    protected void showpassword2() {
        if(showPassword2.isSelected()) {
            showLabel2.setText(passWord1.getText());
        } else {
            showLabel2.setText("");
        }
    }

    @FXML
    private void showComponent(Node currentNode, String path) {
        try {
            // Tạo một PauseTransition để tạo độ trễ trước khi thay đổi màn hình
            PauseTransition delay = new PauseTransition(Duration.seconds(0.2));
            delay.setOnFinished(event -> {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
                    Parent root = loader.load();
                    Scene scene = new Scene(root);
                    Stage currentStage = (Stage) currentNode.getScene().getWindow();
                    currentStage.close();

                    Stage stage = new Stage();
                    stage.initStyle(StageStyle.TRANSPARENT);
                    stage.setScene(scene);
                    root.requestFocus();
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            delay.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
