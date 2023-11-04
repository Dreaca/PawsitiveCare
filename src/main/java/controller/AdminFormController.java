package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import javax.naming.ldap.PagedResultsControl;
import java.io.IOException;
import java.sql.SQLException;

public class AdminFormController {
    public AnchorPane sidePane;
    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnUser;

    @FXML
    void customerOnAction(ActionEvent event) {

    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException, SQLException {
        Parent vetNode = FXMLLoader.load(this.getClass().getResource("/view/ManageEmployee.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(vetNode);
        ManageEmployeeController.getEmployees();

    }

    @FXML
    void reportOnAction(ActionEvent event) {

    }

    @FXML
    void supplierOnAction(ActionEvent event) {

    }

    @FXML
    void userBtnOnAction(ActionEvent event) {

    }

    @FXML
    void vetOnAction(ActionEvent event) throws IOException {
        Parent vetNode = FXMLLoader.load(this.getClass().getResource("/view/vetForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(vetNode);
    }

}
