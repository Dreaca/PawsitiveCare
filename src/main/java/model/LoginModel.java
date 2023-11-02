package model;

import Db.DbConnection;
import Dto.LoginFormDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    private static String userName;
    private static String passWord;
    private String userId;

    public LoginModel() {
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getUserId() {
        return userId;
    }

    public LoginModel(String userName, String passWord, String userId) {
        this.userName = userName;
        this.passWord = passWord;
        this.userId = userId;
    }


    public static boolean authenticate(LoginFormDto login) throws SQLException {
        userName = login.getUserName();
        passWord = login.getPassword();
        boolean isValid = false;

        String sql = "SELECT passWord FROM user WHERE userName = ?";

       Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String pw = resultSet.getString("password");
            if (userName.equals(pw)) {
                isValid = true;
            }
            else isValid = false;
        }
        return isValid;
    }

    public static String getUser(LoginFormDto login) throws SQLException {
        userName = login.getUserName();

        String sql = "SELECT userId FROM user WHERE username ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,userName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            String user = resultSet.getString("userName");
            if (user.equals(userName)){
                return resultSet.getString("userId");
            }
        }

        return null;
    }
}
