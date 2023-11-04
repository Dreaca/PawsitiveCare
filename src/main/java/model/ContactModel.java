package model;

import Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactModel {
    private String contactId;
    private String custId;
    private String contactNo1;
    private String contactNo2;

    public ContactModel(String contactId, String custId, String contactNo1, String contactNo2) {
        this.contactId = contactId;
        this.custId = custId;
        this.contactNo1 = contactNo1;
        this.contactNo2 = contactNo2;
    }

    public static boolean deleteContact(String id) throws SQLException {
        String sql = "DELETE FROM contact WHERE userId = ?";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        int i = pstm.executeUpdate();
        return i > 0;
    }

    @Override
    public String toString() {
        return "ContactModel{" +
                "contactId='" + contactId + '\'' +
                ", custId='" + custId + '\'' +
                ", contactNo1='" + contactNo1 + '\'' +
                ", contactNo2='" + contactNo2 + '\'' +
                '}';
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getContactNo1() {
        return contactNo1;
    }

    public void setContactNo1(String contactNo1) {
        this.contactNo1 = contactNo1;
    }

    public String getContactNo2() {
        return contactNo2;
    }

    public void setContactNo2(String contactNo2) {
        this.contactNo2 = contactNo2;
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
            return "Con"+id;
        }else {
            return "Con01";
        }
    }
}
