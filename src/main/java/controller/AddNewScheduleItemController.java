package controller;

import Dto.ScheduleDto;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import model.ScheduleModel;
import model.VetModel;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class AddNewScheduleItemController extends ScheduleFormController {
    public AnchorPane root;
    public DatePicker dpkSchedule;
    public TextField txtTime;

    public ComboBox cmbVet;
    public ComboBox cmbDuration;
    public ComboBox cmbHours;
    private String schedId;
    ScheduleModel mod = new ScheduleModel();


    private String generateNextScheduleId() throws SQLException {
        return mod.getNextShedId();
    }


    VetModel model = new VetModel();
    public void initialize() throws SQLException {
        setData();
    }

    public void btnBackOnAction(ActionEvent event) {
        close();
    }

    public void btnSaveOnAction(MouseEvent mouseEvent) throws SQLException {
        LocalDate date = dpkSchedule.getValue();
        String time = txtTime.getText();
        String vetName = (String) cmbVet.getValue();
        String duration = (String) cmbDuration.getValue();
        var dto = new ScheduleDto(schedId,date,time,vetName,duration);
        mod.saveScheduleItem(dto);


    }
    void close(){
        Stage sta = (Stage) root.getScene().getWindow();
        sta.close();
    }
    public void setData() throws SQLException {
        cmbDuration.getItems().setAll("8 hours","3 Hours","5 Hours");
        cmbVet.getItems().addAll(FXCollections.observableArrayList(model.getAllVetNames()));
        List<LocalTime> list = new ArrayList<>();
        list.add(LocalTime.of(6,0));
        list.add(LocalTime.of(7,0));
        list.add(LocalTime.of(8,0));
        list.add(LocalTime.of(9,0));
        list.add(LocalTime.of(10,0));
        list.add(LocalTime.of(11,0));
        list.add(LocalTime.of(12,0));
        list.add(LocalTime.of(13,0));
        list.add(LocalTime.of(14,0));
        list.add(LocalTime.of(15,0));
        list.add(LocalTime.of(16,0));
        list.add(LocalTime.of(17,0));
        list.add(LocalTime.of(18,0));
        list.add(LocalTime.of(19,0));
        list.add(LocalTime.of(20,0));
        list.add(LocalTime.of(21,0));
        cmbHours.getItems().addAll(FXCollections.observableArrayList(list));
        schedId = generateNextScheduleId();
    }
}
