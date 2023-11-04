package model.Cards;

import com.mysql.cj.result.BinaryStreamValueFactory;
import javafx.scene.image.Image;

public class EmployeeCard {

    public String employeeId;
    public String name;
    public String address;
    public String contact;
    public Double salary;

    public String userId;
    public Image employeeImage;

    @Override
    public String toString() {
        return "EmployeeCard{" +
                "employeeId='" + employeeId + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", contact='" + contact + '\'' +
                ", salary=" + salary +
                ", userId='" + userId + '\'' +
                ", employeeImage=" + employeeImage +
                '}';
    }

    public EmployeeCard() {
    }

    public EmployeeCard(String employeeId, String name, String address, String contact, Double salary, String userId, Image employeeImage) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.salary = salary;
        this.userId = userId;
        this.employeeImage = employeeImage;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
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

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Image getEmployeeImage() {
        return employeeImage;
    }

    public void setEmployeeImage(Image employeeImage) {
        this.employeeImage = employeeImage;
    }
}
