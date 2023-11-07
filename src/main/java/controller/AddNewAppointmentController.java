package controller;

import Dto.AppointmentDto;
import Dto.CustomerDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.AppointmentModel;

import java.io.IOException;
import java.lang.invoke.SwitchPoint;
import java.sql.SQLException;
import java.sql.Time;
import java.time.chrono.Chronology;

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
    }

    public void loadData(){
            cmbApType.getItems().addAll(AppointmentDto.AppType.values());
    }
    public void AddAppointmentOnAction(ActionEvent actionEvent) {

        String appId = lblAppId.getText();
        String customer = txtCustomer.getText();
        AppointmentDto.AppType type = (AppointmentDto.AppType) cmbApType.getValue();
        lblPrice.setText(getPriceFor(type));
        String time = txtTime.getText();
        String contact = txtCustomerContact.getText();
        String date = String.valueOf(dpkDate.getValue());
        var dto = new AppointmentDto(appId,customer,type,time,date);
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

    private String getPriceFor(AppointmentDto.AppType type) {
        String price = " ";
        switch (type) {
            case CHECKUP:
                price = "500";
                break;
            case SURGERY:
                price =  "1500";
                break;
            case VACCINATION:
                price =  "1000";
                break;
        }
        return price;
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
