package controller;

import Dto.EmployeeDto;
import Dto.LoginFormDto;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.EmployeeModel;
import model.LoginModel;

import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.Objects;
import java.util.regex.Pattern;

public class AddNewEmployeeFormController {

    public Label lbluserID;
    public TextField txtEmpNIC;
    @FXML
    private JFXButton btnCancel;

    @FXML
    private JFXButton btnSave;

    @FXML
    private JFXButton btnUpload;

    @FXML
    private CheckBox chkAdmin;

    @FXML
    private ImageView empImg;

    @FXML
    private Label lblEmployeeID;

    @FXML
    private TextField txtConfirmPass;

    @FXML
    private TextField txtEmpAddress;

    @FXML
    private TextField txtEmpContact;

    @FXML
    private TextField txtEmpFirstNAme;

    @FXML
    private TextField txtEmpLastName;

    @FXML
    private TextField txtEmpSalary;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtUserName;
    private Stage stage;
    public static boolean savedEmployee = false;

    public void setStage(Stage stage){
        this.stage = stage;
    }

    private EmployeeModel employeeModel = new EmployeeModel();
    private LoginModel logModel = new LoginModel();
    public void initialize() throws SQLException {
        lblEmployeeID.setText(EmployeeModel.generateNextEmpId());
        lbluserID.setText(LoginModel.generateNExtUserID());
    }

    @FXML
    void btnCancelOnAction(ActionEvent event) {
        ManageEmployeeController.stage.close();
    }

    @FXML
    void btnSaveOnAction(String image) {
        if(checkValidity()){
            String id = lblEmployeeID.getText();
            String name = txtEmpFirstNAme.getText() + " " + txtEmpLastName.getText();
            String address = txtEmpAddress.getText();
            String contact = txtEmpContact.getText();
            Double salary = Double.valueOf(txtEmpSalary.getText());
            String userId = lbluserID.getText();
            String userName = txtUserName.getText();
            String newPw = txtPassword.getText();
            String confPw = txtConfirmPass.getText();
            String NIC = txtEmpNIC.getText();
            String imagePath = image;


            var dto = new EmployeeDto(id, name, address, contact, salary, userId, NIC);

            try {
                boolean con = confirmPass(newPw, confPw);
                if (con) {
                    var LDto = new LoginFormDto(userId, userName, newPw);
                    boolean userSaved = logModel.saveUser(LDto);

                    if (userSaved) {
                        boolean isSaved = employeeModel.saveEmployee(dto);

                        if (isSaved) {
                            savedEmployee = isSaved;
                            new Alert(Alert.AlertType.CONFIRMATION, "User Saved !").show();
                        }
                    }
                }
            } catch (SQLException | FileNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }

    }

    private boolean checkValidity() {
        if(!Pattern.matches("[A-za-z]&^([\\s][0-9])",txtEmpFirstNAme.getText())){
            new Alert(Alert.AlertType.WARNING,"Firstname Can not Include Spaces or numbers").show();
        }
        else if(!Pattern.matches("[A-za-z]&^([\\s][0-9])",txtEmpLastName.getText())){
            new Alert(Alert.AlertType.WARNING,"Last name can not Include spaces or Numbers").show();
        } else if (!Pattern.matches("[A-Za-z0-9]&[/]",txtEmpAddress.getText())){
            new Alert(Alert.AlertType.WARNING,"Address Can't Include Special characters").show();
        }else if(!Pattern.matches("",txtEmpContact.getText())){
            new Alert(Alert.AlertType.WARNING,"Contact wrong").show();
        }
        else if(!Pattern.matches("[0-9]{9}[V|v|x|X]|[0-9]{12}",txtEmpNIC.getText())){
            new Alert(Alert.AlertType.WARNING,"NIC is wrong").show();
        } else if (Pattern.matches("^[\\s]",txtUserName.getText())) {
            new Alert(Alert.AlertType.WARNING,"User Name Cannot include Spaces").show();
        }else if(!Pattern.matches("^[\\s]&{4,}",txtPassword.getText())){
            new Alert(Alert.AlertType.WARNING,"Password needs to be more than 4 characters and Can't include spaces").show();
        }
        return true;
    }

    private boolean confirmPass(String newPw, String confPw) {
        return Objects.equals(newPw, confPw);
    }

    @FXML
    void btnUploadOnAction(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Employee Image");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif")
        );
        Stage stage = (Stage) empImg.getScene().getWindow();
        java.io.File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null) {
            btnSaveOnAction(selectedFile.getName());
        }

    }

    @FXML
    void onCheckPassWord(KeyEvent event) {
        String newPassword = txtPassword.getText();
        String confirmNewPassword = txtConfirmPass.getText();

        if (newPassword.equals(confirmNewPassword)) {
            //passwordMatchLabel.setText("Passwords match");
            txtConfirmPass.setStyle("-fx-text-fill: green;");
        } else {
            //passwordMatchLabel.setText("Passwords do not match");
            txtConfirmPass.setStyle("-fx-text-fill: red;");
        }
    }

}