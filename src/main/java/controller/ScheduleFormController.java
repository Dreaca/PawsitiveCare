package controller;

import Db.DbConnection;
import Dto.AppointmentDto;
import Dto.ScheduleDto;
import Dto.Tm.ScheduleTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import model.AppointmentModel;
import model.ScheduleModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ScheduleFormController {
    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colVetName;
    public TableColumn colDuration;
    public TableColumn colMod;
    public TableView tblSchedule;

    public  ScheduleModel model = new ScheduleModel();
    public Label vaccineCount;
    public Label surgCount;
    public Label checkupCount;

    ObservableList<ScheduleTm> oblist = FXCollections.observableArrayList();
    public void initialize() throws SQLException {
        setCellValueFactory();
        loadData();
    }


    public void printScheduleOnAction(ActionEvent event) {
        createJasperReport(oblist);
    }

    private void createJasperReport(ObservableList<ScheduleTm> oblist) {

        try {
            InputStream stream = getClass().getResourceAsStream("/report/schedule.jrxml");
            JasperDesign load = JRXmlLoader.load(stream);
            JasperReport report = JasperCompileManager.compileReport(load);
            JasperPrint print = JasperFillManager.fillReport(report,null, DbConnection.getInstance().getConnection());

            JasperViewer.viewReport(print);
        } catch (JRException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mailVetsOnAction(ActionEvent event) {

    }

    public void refreshOnAction(ActionEvent event){
        oblist.clear();
        loadData();
    }

    public void addScheduleOnAction(ActionEvent event) throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/dashBoards/EmployeeDash/addNewScheduleItem.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void loadData(){

        try {
            var Amodel = new AppointmentModel();
            vaccineCount.setText(Amodel.count(AppointmentDto.AppType.VACCINATION));
            surgCount.setText(Amodel.count(AppointmentDto.AppType.SURGERY));
            checkupCount.setText(Amodel.count(AppointmentDto.AppType.CHECKUP));
            List<ScheduleDto> list = model.getScheduleData();
            for (ScheduleDto d: list) {
                oblist.add(
                        new ScheduleTm(
                                d.getDate(),
                                d.getTime(),
                                d.getVetName(),
                                d.getDuration(),
                                getJFXButton()
                        )
                );
            }
            tblSchedule.setItems(oblist);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private JFXButton getJFXButton() {
        Image image = new Image("/view/Assets/icon/settings.png");
        ImageView imageView = new ImageView(image);
        JFXButton bt = new JFXButton();
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            bt.setGraphic(imageView);
            return bt;
    }

    public void setCellValueFactory(){
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        colVetName.setCellValueFactory(new PropertyValueFactory<>("vetName"));
        colMod.setCellValueFactory(new PropertyValueFactory<>("modButton"));
    }

}
