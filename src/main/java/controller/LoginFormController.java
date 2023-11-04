package controller;

import Dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.LoginModel;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUserNAme;
    public JFXButton btnLogin;
    public PasswordField txtPassword;
    public AnchorPane root;


    public void loginOnAction(ActionEvent actionEvent) throws SQLException {
        String userName = txtUserNAme.getText();
        String password = txtPassword.getText();

        LoginFormDto login = new LoginFormDto(userName,password);

        boolean authenticateResult = LoginModel.authenticate(login);

        if(authenticateResult){
            String currentUser = LoginModel.getUser(login);
            if(currentUser.equals("Admin")){
               loadAdminDash();
            } else if (currentUser.startsWith("E")) {
                loadEmployeeDash();
            }
        }
        else {
            System.out.println("Login Failed");}
    }

    private void loadAdminDash() {
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBoards/adminDash.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Admin Dashboard");
        stage.setScene(scene);
        stage.centerOnScreen();
    }
    private void loadEmployeeDash(){
        AnchorPane anchorPane = null;
        try {
            anchorPane = FXMLLoader.load(getClass().getResource("/view/dashBoards/empDash.fxml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setTitle("Employee Dashboard");
        stage.setScene(scene);
        stage.centerOnScreen();
    }


}
