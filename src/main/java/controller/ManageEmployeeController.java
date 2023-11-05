package controller;

import Dto.EmployeeDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ManageEmployeeController {

    public VBox empVbox;
    public HBox employeeHbox;
    public JFXButton btnAddEmployee;
    public JFXButton btnUpdateEmployee;
    @FXML
    private AnchorPane employeeAnchor;

    @FXML
    private AnchorPane employeeCard;

    public void initialize() throws IOException, SQLException {

        loadAllEmployees();

    }

    public void loadAllEmployees() throws SQLException, IOException {
        List<EmployeeDto> employeeDtos= EmployeeModel.getEmployeeDtos();
        for (EmployeeDto empDto:employeeDtos) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/employeeManage/EmployeeTile.fxml"));
            Parent tile = loader.load();
            EmployeeTileController controller = loader.getController();
            controller.setEmployeeData(empDto);
            this.employeeCard.getChildren().clear();
            this.employeeCard.getChildren().add(tile);

        }
    }

    public void addnewEmployee(ActionEvent actionEvent) {


    }
    public void updateOnAction(){

    }
}
