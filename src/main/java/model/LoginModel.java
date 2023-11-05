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

    public static String generateNExtUserID() throws SQLException {
        String sql = "SELECT * FROM user ORDER BY userId DESC LIMIT 1";
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitUserID(resultSet.getString(1));
        }else {
            return splitUserID(null);
        }
    }

    private static String splitUserID(String userId) {
        if(userId != null){
            String newUser[] = userId.split("U");
            int num = Integer.parseInt(newUser[1]);
            num++;
            return "E00"+num;
        }else {
            return "E001";
        }
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
        String usedUserName = login.getUserName();
        String usedPassword = login.getPassword();
        boolean isValid = false;

        String sql = "SELECT passWord FROM user WHERE userName = ?";

       Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,usedUserName);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String pw = resultSet.getString("password");
            if (usedPassword.equals(pw)) {
                isValid = true;
            }
            else isValid = false;
        }
        return isValid;
    }

    public static String getUser(LoginFormDto login) throws SQLException {
       String usedUserName = login.getUserName();

        String sql = "SELECT * FROM user WHERE userName = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,usedUserName);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            if (usedUserName.equals(resultSet.getString("userName"))){
                return resultSet.getString("userId");
            }
        }

        return null;
    }
}
