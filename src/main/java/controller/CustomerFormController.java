package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class CustomerFormController {
    @FXML
    private JFXButton btnDeleteCustomer;

    @FXML
    private JFXButton btnSaveCustomer;

    @FXML
    private JFXButton btnUpdate;

    @FXML
    private TableColumn<?, ?> colCustomerAddress;

    @FXML
    private TableColumn<?, ?> colCustomerContact;

    @FXML
    private TableColumn<?, ?> colCustomerID;

    @FXML
    private TableColumn<?, ?> colCustomerName;

    @FXML
    private TableColumn<?, ?> colCustomerPets;

    @FXML
    private RadioButton rdbtnCustomer2ndNum;

    @FXML
    private TableView<?> tblCustomer;

    @FXML
    private TextField txtContact2nd;

    @FXML
    private TextField txtContactNo;

    @FXML
    private TextField txtCustomerFname;

    @FXML
    private TextField txtCustomerID;

    @FXML
    private TextField txtCustomerLname;

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void customerContactSearch(ActionEvent event) {

    }

    @FXML
    void customerDeleteOnAction(ActionEvent event) {

    }

    @FXML
    void customerLnameSearchOnAction(ActionEvent event) {

    }

    @FXML
    void customerSaveOnaction(ActionEvent event) {

    }

    @FXML
    void customerSearchOnaction(ActionEvent event) {

    }

    @FXML
    void txtCustomerFnameSearch(ActionEvent event) {

    }
}
