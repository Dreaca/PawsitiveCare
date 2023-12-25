package controller;

import Dto.EmployeeDto;
import Dto.LoginFormDto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.LoginModel;

import java.sql.SQLException;
import java.util.List;

public class UpdateEmployeeFormController {
    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<String> cmbEmpId;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtEmpFname;

    @FXML
    private TextField txtEmpLName;

    @FXML
    private TextField txtNic;

    @FXML
    private TextField txtPass;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtUserName;

    private LoginModel loginModel = new LoginModel();
    private EmployeeModel model = new EmployeeModel();
    public void initialize() throws SQLException {
        loadData();
    }

    private void loadData() throws SQLException {
        ObservableList<String> oblist = FXCollections.observableArrayList();
        List<String> list  = model.getEmpIDs();
        for (String l : list) {
            oblist.add(l);
        }
        cmbEmpId.setItems(oblist);
    }

    @FXML
    void cancelOnAction(ActionEvent event) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    @FXML
    void updateOnAction(ActionEvent event) throws SQLException {
        String empId = cmbEmpId.getValue();
        String name = txtEmpFname.getText() + " " + txtEmpLName.getText();
        String address = txtAddress.getText();
        String contact = txtContact.getText();
        Double salary = Double.valueOf(txtSalary.getText());
        String userID = txtUserName.getText();
        String NIC = txtNic.getText();
        String pass = txtPass.getText();
        if (loginModel.updatePassword(userID, pass)) {
            var dto = new EmployeeDto(empId, name, address, contact, salary, userID, NIC);
            try {
                if (model.updateEmployee(dto)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Update Completed").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }
}
