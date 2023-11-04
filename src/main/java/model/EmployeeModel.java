package model;

import Db.DbConnection;
import Dto.EmployeeDto;
import controller.EmployeeTileController;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private String empId;
    private String name;
    private String address;
    private String contact;

    private double salary;
    private  String userId;
    private static Image photo;

    public EmployeeModel(String empId, String name, String address, String contact, double salary, String userId, Image photo) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.salary = salary;
        this.userId = userId;
        this.photo = photo;
    }

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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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
}
