package model;

import javafx.scene.image.Image;

public class EmployeeModel {
    private static String employeeId;
    private static String name;
    private static String address;
    private  static String contact;
    private static String userId;
    private static Image employeeImage;
    public static String getEmployeeId() {
        return employeeId;
    }

    public static void setEmployeeId(String employeeId) {
        EmployeeModel.employeeId = employeeId;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        EmployeeModel.name = name;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        EmployeeModel.address = address;
    }

    public static String getContact() {
        return contact;
    }

    public static void setContact(String contact) {
        EmployeeModel.contact = contact;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        EmployeeModel.userId = userId;
    }

    public static Image getEmployeeImage() {
        return employeeImage;
    }

    public static void setEmployeeImage(Image employeeImage) {
        EmployeeModel.employeeImage = employeeImage;
    }

    public EmployeeModel() {
    }


}
