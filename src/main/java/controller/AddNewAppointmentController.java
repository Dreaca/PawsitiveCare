package controller;

import Dto.AppointmentDto;
import Dto.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AppointmentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Pattern;

public class AddNewAppointmentController {
    public AnchorPane root;
    public Label lblAppId;
    public DatePicker dpkDate;
    public Label lblPrice;
    public TextField txtTime;
    public TextField txtCustomer;
    public TextField txtCustomerContact;
    public ComboBox cmbApType;

    private AppointmentModel model = new AppointmentModel();

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    public void initialize() throws SQLException {
        loadData();
        lblAppId.setText(model.getNextAppid());
        cmbApType.setOnAction(event -> {
            lblPrice.setText(getPriceFor(AppointmentDto.AppType.valueOf(((AppointmentDto.AppType) cmbApType.getValue()).name())));
        });
    }

    public void loadData(){
            cmbApType.getItems().addAll(AppointmentDto.AppType.values());
    }
    public void AddAppointmentOnAction(ActionEvent actionEvent) {

        String appId = lblAppId.getText();
        String customer = txtCustomer.getText();
        AppointmentDto.AppType type = (AppointmentDto.AppType) cmbApType.getValue();
        double price = Double.valueOf(lblPrice.getText());
        String time = txtTime.getText();
        String contact = txtCustomerContact.getText();
        String date = String.valueOf(dpkDate.getValue());
        if (checkValidity()) {
            var dto = new AppointmentDto(appId,customer,contact,type,time,date,price);
            CustomerDto cus = new CustomerDto(customer,contact);
            try {
                boolean isSaved = model.addAppointment(dto,cus);
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION,"Appointment Saved").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
            }
        }

    }

    private boolean checkValidity() {
        boolean matches = Pattern.matches("(App)[0-9]{1,}", lblAppId.getText());
        if(!matches){
            new Alert(Alert.AlertType.ERROR,"wrong AppId").show();
        }
        if(!Pattern.matches("[0-9][0-9](:)[0-9][0-9]",txtTime.getText())){
            new Alert(Alert.AlertType.ERROR,"Time format wrong").show();
        }
        if(!Pattern.matches("[A-za-z]\\s[A-za-z]",txtCustomer.getText())){
            new Alert(Alert.AlertType.ERROR,"Customer Name Wrong");
        }
        if(!Pattern.matches("[0-9]",txtCustomerContact.getText())){
            new Alert(Alert.AlertType.ERROR,"Customer Contact is wrong").show();
        }
         return true;
    }

    private String getPriceFor(AppointmentDto.AppType type) {
        String price = " ";
        switch (type) {
            case CHECKUP:
                price = "500";
                return price;
            case SURGERY:
                price =  "1500";
                return price;
            case VACCINATION:
                price =  "1000";
                return price;
        }
        return "Not applicaple";
    }


    public void AddCustomerOnACtion(ActionEvent actionEvent) throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/customerForm.fxml"))));
        stage.centerOnScreen();
        stage.show();
    }
    public void loadAppointments(){

    }
}
