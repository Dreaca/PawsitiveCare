package controller;

import Dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import model.LoginModel;

import java.sql.SQLException;

public class LoginFormController {
    public TextField txtUserNAme;
    public JFXButton btnLogin;
    public PasswordField txtPassword;


    public void loginOnAction(ActionEvent actionEvent) throws SQLException {
        String userName = txtUserNAme.getText();
        String password = txtPassword.getText();

        LoginFormDto login = new LoginFormDto(userName,password);

        boolean authenticateResult = LoginModel.authenticate(login);
    }


}