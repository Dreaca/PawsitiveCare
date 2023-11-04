package controller;

import Dto.EmployeeDto;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class EmployeeTileController {
    public ImageView imgEmpImg;
    public Label lblEmpName;
    public Label lblEmpId;
    public Label lblAddress;
    public Label lblContact;
    public Label lblUserId;
    public Label lblSalary;

    public void setEmployeeData(EmployeeDto employee) {
        //imgEmpImg.setImage(new Image(String.valueOf(employee.getPhoto())));
        lblEmpId.setText(employee.getEmpId());
        lblEmpName.setText(employee.getName());
        lblAddress.setText(employee.getAddress());
        lblContact.setText(employee.getContact());
        lblSalary.setText(String.valueOf(employee.getSalary()));
        lblUserId.setText(employee.getUserId());

    }
}
