package controller;

import Dto.EmployeeDto;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import model.EmployeeModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static model.EmployeeModel.getEmployeeDtos;

public class ManageEmployeeController {

    public VBox empVbox;
    public HBox employeeHbox;
    @FXML
    private AnchorPane employeeAnchor;

    @FXML
    private AnchorPane employeeCard;

    public void initialize() throws IOException, SQLException {

        List<EmployeeDto> employeeDtos= EmployeeModel.getEmployeeDtos();
        for (EmployeeDto empDto:employeeDtos) {
            FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/EmployeeTile.fxml"));
            Parent tile = loader.load();
            EmployeeTileController controller = loader.getController();
            controller.setEmployeeData(empDto);
            this.employeeCard.getChildren().clear();
            this.employeeCard.getChildren().add(tile);
            
        }
    }


}
