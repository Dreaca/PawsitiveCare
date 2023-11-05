package model;

import Db.DbConnection;
import Dto.EmployeeDto;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private String name;
    private String address;
    private String contact;

    private double salary;
    private  String userId;
    private static Image photo;

    public EmployeeModel() {
    }

    public static List<EmployeeDto> getEmployeeDtos() throws SQLException {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            EmployeeDto emp = new EmployeeDto();
            emp.setEmpId(resultSet.getString("employeeId"));
            emp.setAddress(resultSet.getString("address"));
            emp.setName(resultSet.getString("name"));
            emp.setContact(resultSet.getString("contact"));
            emp.setSalary(resultSet.getDouble("salary"));
            emp.setUserId(resultSet.getString("userId"));
            byte[] photobyte = resultSet.getBytes("photo");
            if (photobyte != null){
                ByteArrayInputStream bt = new ByteArrayInputStream(photobyte);
                photo = new Image(bt);
                emp.setPhoto(photo);
            }
            employeeDtos.add(emp);
            resultSet.close();
            return employeeDtos;
        }

        return null;
    }

    public static String generateNextEmpId() throws SQLException {
        String sql = "SELECT * FROM employee ORDER BY employeeID DESC LIMIT 1 ";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()){
            return splitEmpID(resultSet.getString(1));
        }else {
            return splitEmpID(null);
        }
    }

    private static String splitEmpID(String empId) {
        if( empId != null ){
            String [] id = empId.split("E");
            int num = Integer.parseInt(id[1]);
            num++;
            return "E0"+num;
        }else {
            return "E001";
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Image getPhoto() {
        return photo;
    }

    public void setPhoto(Image photo) {
        this.photo = photo;
    }

    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        String sql = "INSERT INTO employee VALUES(?,?,?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1,dto.getEmpId());
        pstm.setString(2,dto.getName());
        pstm.setString(3,dto.getAddress());
        pstm.setString(4,dto.getContact());
        pstm.setDouble(5,dto.getSalary());
        pstm.setString(6,dto.getUserId());
        pstm.setBlob(7, (Blob) null);
        int i = pstm.executeUpdate();
        return i > 0;
    }
}
