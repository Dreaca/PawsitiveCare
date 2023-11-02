package Dto;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class LoginFormDto {
    private String userName;
    private String password;

    private String userId;

    public LoginFormDto() {
    }

    public LoginFormDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public void setUserName(String userName) {
        if(userName.startsWith("E")){
            this.userName = userName;
        }
        else new Alert(Alert.AlertType.INFORMATION,"Employee UserId must Start with an E",new ButtonType("Ok")).show();

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserID() {
        return userId;
    }
}
