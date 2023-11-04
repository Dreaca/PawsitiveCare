package model;

import Db.DbConnection;
import Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerModel {
    private String customerId;
    private String customerName;
    private String customerAddress;
    private String customerContact;
    private  String customerContact2;

    public String getCustomerContact2() {
        return customerContact2;
    }

    public void setCustomerContact2(String customerContact2) {
        this.customerContact2 = customerContact2;
    }

    @Override
    public String toString() {
        return "CustomerModel{" +
                "customerId='" + customerId + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerAddress='" + customerAddress + '\'' +
                ", customerContact='" + customerContact + '\'' +
                ", customerContact2='" + customerContact2 + '\'' +
                '}';
    }

    public CustomerModel(String customerId, String customerName, String customerAddress, String customerContact, String customerContact2) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
        this.customerContact2 = customerContact2;
    }

    public CustomerModel() {
    }

    public CustomerModel(String customerId, String customerName, String customerAddress, String customerContact) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.customerContact = customerContact;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerContact() {
        return customerContact;
    }

    public void setCustomerContact(String customerContact) {
        this.customerContact = customerContact;
    }

    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        boolean flag = false;
        String sql = "INSERT INTO customer VALUES(?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getCustomerId());
        pstm.setString(2,dto.getCustomerName());
        pstm.setString(3,dto.getCustomerAddress());
        int i = pstm.executeUpdate();
        saveCustomerContact(dto);
        if(i > 0){
            flag = true;

        }
        else flag = false;
        return flag;
    }

    private void saveCustomerContact(CustomerDto dto) throws SQLException {

        String sql = "INSERT INTO contact VALUES(?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getCustomerId());
        pstm.setString(2,ContactModel.generateContactId());
        pstm.setString(3,dto.getCustomerContact());
        pstm.executeUpdate();

    }

    public boolean deleteCustomer(String id) throws SQLException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean contactDeleted = ContactModel.deleteContact(id);
            if(contactDeleted){
                String sql = "DELETE FROM customer WHERE custId = ?";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1,id);
                pstm.executeUpdate();
                connection.commit();
            }
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
