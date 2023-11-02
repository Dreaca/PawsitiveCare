package model;

import Db.DbConnection;
import Dto.LoginFormDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {


    public static boolean authenticate(LoginFormDto login) throws SQLException {
        String usedUserName = login.getUserName();
        String usedPassword = login.getPassword();

        String sql = "SELECT passWord FROM user WHERE userId = ?";

       Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,login.getUserName());

        String pw = String.valueOf(pstm.executeQuery());
        if(usedPassword == pw){
            return true;
        }
        else return false;

    }
}
