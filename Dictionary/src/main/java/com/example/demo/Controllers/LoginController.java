package com.example.demo.Controllers;


import com.example.demo.Alerts.Alerts;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;

import java.util.ArrayList;

import java.util.Optional;


import javafx.scene.control.Alert.AlertType;

public class LoginController {
    private static final String fileName = "data/credentials.txt";
    @FXML
    private Button signupBtn, changePass,changescreen;
    @FXML
    private Button loginBtn1, loginBtn2, loginBtn3;
    @FXML
    private PasswordField passWord1, passWord2, passWord3;
    private boolean focusLost = false, focusLost2 = false;
    @FXML
    private PasswordField confirmPassWord1, newPassWord2,confirmpassWord2;
    @FXML
    private TextField userNameBtn2, userNameBtn, userNameBtn3;
    @FXML
    private CheckBox showPassword2, showPassword3, showPassword;
    @FXML
    private Label showLabel2, showLabel3, showLabel;
    private double xOffset = 0;
    private double yOffset = 0;

    public LoginController(){}

    @FXML
    public void registerButtonClicked() {
        String userNameText = userNameBtn.getText();
        String passwordText = passWord1.getText();
        String password1 = passWord1.getText();
        String password2 = confirmPassWord1.getText();
        boolean check = comparePasswords(password1, password2);
        if (check && focusLost == true) {
            saveCredentialsToFile(userNameText, passwordText);
            userNameBtn.setText("");
            passWord1.setText("");
            confirmPassWord1.setText("");
            showLabel2.setText("");
        } else {
            showAlert("Tạo tài khoản thất bại!!!, Kiểm tra lại tài khoản hoặc mật khẩu!", AlertType.ERROR);
        }
    }

    @FXML
    public void check(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            String username = userNameBtn2.getText();
            String password = passWord2.getText();

            if (authenticate(username, password)) {
                showAlert("Bạn đã đăng nhập thành công !", AlertType.INFORMATION);
                Node currentNode = loginBtn2;
                showComponent(currentNode, "/View/DictionariesGui.fxml");

            } else {
                showAlert("Đăng nhập thất bại !!!, Kiểm tra lại tài khoản hoặc mật khẩu!", AlertType.ERROR);
            }
        }
    }

    @FXML
    public void loginButtonclick(ActionEvent event) throws IOException {
        String username = userNameBtn2.getText();
        String password = passWord2.getText();

        if (authenticate(username, password)) {
            showAlert("Bạn đã đăng nhập thành công !", AlertType.INFORMATION);
            Node currentNode = loginBtn2;
            showComponent(currentNode, "/View/DictionariesGui.fxml");

        } else {
            showAlert("Đăng nhập thất bại !!!, Kiểm tra lại tài khoản hoặc mật khẩu!", AlertType.ERROR);
        }
    }

    @FXML
    public void ChangeButtonclick2(ActionEvent event) throws IOException {
        String username = userNameBtn3.getText();
        String password = passWord3.getText();
        String newpassWord = newPassWord2.getText();
        String confirmPass = confirmpassWord2.getText();


        boolean check = comparePasswords(newpassWord, confirmPass);
        if (check && focusLost2 == true) {
            changeCredentialsToFile(username,newpassWord);
            System.out.println(check);
            userNameBtn3.setText("");
            passWord3.setText("");
            newPassWord2.setText("");
            confirmpassWord2.setText("");
            showLabel3.setText("");
            showAlert("Đổi mật khẩu thành công !", AlertType.INFORMATION);
        } else {
            showAlert("Đổi mật khẩu thất bại!!!, Kiểm tra lại tài khoản hoặc mật khẩu!", AlertType.ERROR);
        }
    }
    private void changeCredentialsToFile(String userName, String passChange) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            ArrayList<String> fileContent = new ArrayList<>();

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Username: ")) {
                    String existingUsername = line.substring("Username: ".length());
                    fileContent.add(line);  // Add the existing Username line
                    if (existingUsername.equals(userName)) {
                        // Replace the existing password line with the new password
                        fileContent.add("Password: " + passChange);
                        reader.readLine();  // Skip the existing password line
                    }
                } else {
                    fileContent.add(line);
                }
            }
            reader.close();

            // Write the updated content back to the file
            FileWriter fileWriter = new FileWriter(fileName, false);
            BufferedWriter writer = new BufferedWriter(fileWriter);
            for (String contentLine : fileContent) {
                writer.write(contentLine);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveCredentialsToFile(String userName, String password) {
        try {
            boolean userExists = checkIfUserExists(fileName, userName);

            if (!userExists) {
                FileWriter fileWriter = new FileWriter(fileName, true);
                BufferedWriter writer = new BufferedWriter(fileWriter);

                writer.write("Username: " + userName);
                writer.newLine();
                writer.write("Password: " + password);
                writer.newLine();
                writer.write("Data: dataAccount/" + userName + ".txt");
                writer.newLine();

                BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dataAccount/" + userName + ".txt"));
                bufferedWriter.write("");
                bufferedWriter.close();
                writer.close();

                showAlert("Bạn đã tạo tài khoản thành công !", AlertType.INFORMATION);

            } else {
                showAlert("Tài khoản này đã tồn tại ! Hãy tạo tài khoản khác.", AlertType.INFORMATION);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean authenticate(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            String storedUsername = null;
            String storedPassword = null;
            String storedData = null;

            while ((line = reader.readLine()) != null) {
                if (line.contains("Username")) {
                    String temp = line.split(":", 2)[0].trim();
                    storedUsername = line.split(":", 2)[1].trim();
                }
                if (line.contains("Password")) {
                    storedPassword = line.split(":", 2)[1].trim();
                }
                if (line.contains("Data")) {
                    storedData = line.split(":", 2)[1].trim();
                }

                if (storedUsername != null && storedPassword != null && storedData != null) {
                    if (username.equals(storedUsername) && password.equals(storedPassword)) {
                        BufferedWriter writer = new BufferedWriter(new FileWriter("data/loginAccount.txt"));
                        writer.write(storedUsername);
                        writer.close();
                        getFileDataOfAccount();
                        return true;
                    }
                    storedUsername = null;
                    storedPassword = null;
                    storedData = null;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Đăng nhập thất bại
    }

    @FXML
    private void exitBtnClick() {
        Alerts alerts = new Alerts();
        Alert alertConfirm = alerts.alertConfirm("Exit", "Bạn có chắc muốn thoát ứng dụng ?");
        Optional<ButtonType> option = alertConfirm.showAndWait();
        if (option.get() == ButtonType.OK) {
            System.exit(0);
        } else {
            alerts.showAlertInfo("Exit", "Tiếp tục sử dụng ứng dụng!");
        }
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

    public String getFileDataOfAccount() {
        String path = "";
        try {
            BufferedReader reader = new BufferedReader(new FileReader("data/loginAccount.txt"));
            String line = reader.readLine();
            path += "dataAccount/" + line + ".txt";
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    @FXML
    public void handlePasswordKeyReleased() {
        String password1 = passWord1.getText();
        if (password1.length() < 8) {
            showAlert("Độ dài mật khẩu không đủ 8 ký tự !", AlertType.ERROR);
        } else {
            focusLost = true;
        }
    }

    @FXML
    public void handlePasswordKeyReleased2() {
        String password1 = newPassWord2.getText();
        if (password1.length() < 8) {
            showAlert("Độ dài mật khẩu không đủ 8 ký tự !", AlertType.ERROR);
        } else {
            focusLost2 = true;
        }
    }

    @FXML
    public boolean checkPasswordUsername() {
        String username = userNameBtn3.getText();
        String password = passWord3.getText();

        //System.out.println(username + " " + password);
        if (authenticate(username, password)) {
            return true;
        } else {
            showAlert("Tài khoản hoặc mật khẩu không chính xác hãy kiểm tra lại!", AlertType.ERROR);
            return false;
        }
    }

    @FXML
    private boolean comparePasswords(String password1, String password2) {
        String s2 = "";
        if (password2.length() >= 8) {
            if (password1.equals(s2) && password2.equals(s2)) {
                showAlert("Bạn phải nhập mật khẩu !", AlertType.ERROR);
                return false;
            }
            if (password1.equals(password2)) {
                return true;
            } else {
                return false;
            }
        } else {
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
    protected void showpassword1() {
        if (showPassword.isSelected()) {
            showLabel.setText(passWord2.getText());
        } else {
            showLabel.setText("");
        }
    }

    @FXML
    protected void showpassword2() {
        if (showPassword2.isSelected()) {
            showLabel2.setText(passWord1.getText());
        } else {
            showLabel2.setText("");
        }
    }

    @FXML
    protected void showpassword3() {
        if (showPassword3.isSelected()) {
            showLabel3.setText(newPassWord2.getText());
        } else {
            showLabel3.setText("");
        }
    }


    @FXML
    protected void loginBtnClick() {
        Node currentNode = loginBtn1;
        showComponent(currentNode, "/View/login.fxml");
    }

    @FXML
    protected void changeBtnClick() {
        Node currentNode = changePass;
        showComponent(currentNode, "/View/changePass.fxml");
    }
    @FXML
    protected void changeScreenClick() {
        Node currentNode = changescreen;
        showComponent(currentNode, "/View/changePass.fxml");
    }

    @FXML
    protected void loginBtnClick2() {
        Node currentNode = loginBtn3;
        showComponent(currentNode, "/View/login.fxml");
    }

    @FXML
    protected void signupBtnClick() {
        Node currentNode = signupBtn;
        showComponent(currentNode, "/View/signup.fxml");
    }


    @FXML
    private void showComponent(Node currentNode, String path) {
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
