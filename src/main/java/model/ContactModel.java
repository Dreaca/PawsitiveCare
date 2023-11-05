package model;

import Db.DbConnection;
import Dto.ContactDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactModel {

    public static String getContact(String custId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM contact WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,custId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return resultSet.getString("contact");
        }
        return null;
    }

    public static ContactDto getCustomer(String contact) throws SQLException {
        String sql = "SELECT * FROM contact WHRER contact = ?";

        ContactDto contactDto = null;
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,contact);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            String contId = resultSet.getString("contId");
            String contactNo = resultSet.getString("contact");
            String customerId = resultSet.getString("custId");
            contactDto = new ContactDto(contId,contactNo,customerId);
        }
        return contactDto;
    }



    public ContactModel() {
    }

    public static String generateContactId() throws SQLException {
        String sql = "SELECT * FROM contact ORDER BY contId DESC LIMIT 1";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()){
            return splitContId(resultSet.getString("contId"));
        }
        return splitContId(null);
    }

    private static String splitContId(String curContID) {
        if(curContID!=null){
            String [] split = curContID.split("Con");
            int id = Integer.parseInt(split[1]);
            id++;
            return "Con0"+id;
        }else {
            return "Con01";
        }
    }
}
