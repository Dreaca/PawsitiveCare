package Dto;

import javafx.scene.image.Image;

public class EmployeeDto {
        private String empId;
        private String name;
        private String address;
        private String contact;

        private double salary;
        private  String userId;
        private Image photo;

    public EmployeeDto(String empId, String name, String address, String contact, double salary, String userId, Image photo) {
        this.empId = empId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.salary = salary;
        this.userId = userId;
        this.photo = photo;
    }

    public EmployeeDto() {
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
