package controller;

import Dto.CustomerDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.ContactModel;
import model.CustomerModel;

import java.sql.SQLException;

public class CustomerFormController {
    public TextField txtCusAddress;
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

    private CustomerModel customerModel = new CustomerModel();
    public void initialize(){
        setCellValueFactory();
        loadAllCustomer();
    }

    private void loadAllCustomer() {


    }

    private void setCellValueFactory() {
        colCustomerID.setCellValueFactory(new PropertyValueFactory<>("Customer ID"));
        colCustomerName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colCustomerAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colCustomerContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void customerContactSearch(ActionEvent event) {

    }

    @FXML
    void customerDeleteOnAction(ActionEvent event) {
        String id = txtCustomerID.getText();
        boolean isDeleted = false;
        try {
            isDeleted = customerModel.deleteCustomer(id);
            if(isDeleted){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Deleted !");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

    }

    @FXML
    void customerLnameSearchOnAction(ActionEvent event) {

    }

    @FXML
    void customerSaveOnaction(ActionEvent event)  {
        String Id = txtCustomerID.getText();
        String Fname = txtCustomerFname.getText();
        String Lname = txtCustomerLname.getText();
        String Address = txtCusAddress.getText();
        String contact = txtContactNo.getText();
        String fullname = Fname+" "+Lname;


        var dto = new CustomerDto(Id,fullname,Address,contact);
        try{
            boolean isSaved = customerModel.saveCustomer(dto);
            tblCustomer.refresh();
            if(isSaved){
                new Alert(Alert.AlertType.CONFIRMATION,"Customer Saved Successfully").show();
                clearFields();
            }
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();;
        }

    }



    private void clearFields() {
        txtCustomerID.clear();
        txtCusAddress.clear();
        txtCustomerFname.clear();
        txtCustomerLname.clear();
        txtContactNo.clear();
        txtContact2nd.clear();
    }

    @FXML
    void customerSearchOnaction(ActionEvent event) {

    }

    @FXML
    void txtCustomerFnameSearch(ActionEvent event) {

    }
}
