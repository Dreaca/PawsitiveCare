package model;

import Db.DbConnection;
import Dto.CustomerDto;

import java.sql.*;
import java.time.LocalDate;

public class OrderModel {
    public String generateNextOrderId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM orders ORDER BY orderId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitOrderID(resultSet.getString("orderId"));
        }
       else return null;
    }

    private String splitOrderID(String string) {
        if(!string.equals(null)){
            String b [] = string.split("O");
            int id = Integer.parseInt(b[1]);
            id++;
            return String.format("O0%02d",id);
        }
        else return "O001";
    }

    public boolean saveOrder(String orderId, String custId, LocalDate date) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO orders VALUES(?,?,?)");
        pstm.setString(1,orderId);
        pstm.setString(2,custId);
        pstm.setDate(3, Date.valueOf(date));
        int i = pstm.executeUpdate();
        return i>0;
    }
}
