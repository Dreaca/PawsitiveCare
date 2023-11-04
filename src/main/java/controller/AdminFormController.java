package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class AdminFormController {
    public AnchorPane sidePane;
    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnUser;

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/customerForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException, SQLException {
        Parent empNode = FXMLLoader.load(this.getClass().getResource("/view/employeeManage/ManageEmployee.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(empNode);

    }

    @FXML
    void reportOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/reportForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);

    }

    @FXML
    void supplierOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/supplierForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);

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
