package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class VetFormController {
    public AnchorPane root;
    public TextField txtLastName;
    public TextField txtFirstName;
    public TextField txtContact;
    public TableColumn tblColVetId;
    public TableColumn tblColName;
    public TableColumn tblColContact;
    public TableColumn tblColSchedule;
    public JFXButton btnClear;
    public TextField txtID;

    public void clearOnAction(ActionEvent actionEvent) {

    }
}
