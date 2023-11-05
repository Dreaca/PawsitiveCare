package model;

import Db.DbConnection;
import Dto.ContactDto;
import Dto.CustomerDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerModel {

    public CustomerModel() {
    }



    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        boolean flag;
        String sql = "INSERT INTO customer VALUES(?,?,?)";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getCustomerId());
        pstm.setString(2,dto.getCustomerName());
        pstm.setString(3,dto.getCustomerAddress());
        int i = pstm.executeUpdate();
        saveCustomerContact(dto);
        flag = i > 0;
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
        Connection connection = DbConnection.getInstance().getConnection();
                String sql = "DELETE FROM customer WHERE custId = ?";
                PreparedStatement pstm = connection.prepareStatement(sql);
                pstm.setString(1,id);
                int i = pstm.executeUpdate();
                return i > 0;
    }

    public CustomerDto searchCustomer(String id) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,id);
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getNString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            String contact = ContactModel.getContact(custId);

            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;

    }

    public CustomerDto searchCustomerByFname(String fname) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE name LIKE ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,fname+"%");
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getNString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            String contact = ContactModel.getContact(custId);

            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;
    }
    public CustomerDto searchCustomerByLname(String Lname) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE name LIKE ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,"% "+Lname);
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String custId = resultSet.getNString("custId");
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            String contact = ContactModel.getContact(custId);

            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;
    }

    public CustomerDto searchCustomerByContact(String contact) throws SQLException {
        ContactDto conDto = ContactModel.getCustomer(contact);
        String custId = conDto.getUserId();
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM customer WHERE custId = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,custId);
        ResultSet resultSet = pstm.executeQuery();
        CustomerDto dto = null;
        if(resultSet.next()){
            String name = resultSet.getString("name");
            String address = resultSet.getString("Address");
            dto = new CustomerDto(custId,name,address,contact);
        }
        return dto;

    }

    public List<CustomerDto> getAllCustomer() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM customer";
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        ArrayList<CustomerDto> dto = new ArrayList<>();

        while (resultSet.next()){
            String Contact = ContactModel.getContact(resultSet.getString("custId"));
            dto.add(
              new CustomerDto(
                      resultSet.getString(1),
                      resultSet.getString(2),
                      resultSet.getString(3),
                      Contact,
                      null
              )
            );
        }
        return dto;
    }
}
