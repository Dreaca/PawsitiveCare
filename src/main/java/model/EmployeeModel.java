package model;

import Db.DbConnection;
import Dto.EmployeeDto;
import javafx.scene.image.Image;

import java.io.ByteArrayInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeModel {
    private String contact;

    private static Image photo;

    public EmployeeModel() {
    }

    public static List<EmployeeDto> getEmployeeDtos() throws SQLException {
        List<EmployeeDto> employeeDtos = new ArrayList<>();
        String sql = "SELECT * FROM employee";
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            EmployeeDto emp = new EmployeeDto();
            emp.setEmpId(resultSet.getString("employeeId"));
            emp.setAddress(resultSet.getString("address"));
            emp.setName(resultSet.getString("name"));
            emp.setContact(resultSet.getString("contact"));
            emp.setSalary(resultSet.getDouble("salary"));
            emp.setUserId(resultSet.getString("userId"));
            byte[] photobyte = resultSet.getBytes("photo");
            if (photobyte != null) {
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


    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

}
