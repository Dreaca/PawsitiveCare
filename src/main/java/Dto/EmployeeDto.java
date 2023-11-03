package Dto;

public class EmployeeDto {
    private String employeeId;
    private String name;
    private String address;
    private  String contact;
    private String userId;

    public EmployeeDto(String employeeId, String name, String address, String contact, String userId) {
        this.employeeId = employeeId;
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.userId = userId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getContact() {
        return contact;
    }

    public String getUserId() {
        return userId;
    }

    public EmployeeDto() {
    }
}
