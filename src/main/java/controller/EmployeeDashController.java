package controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;

public class EmployeeDashController {
    public JFXButton btnCustomer;
    public JFXButton btnAppointments;
    public JFXButton btnUser;
    public AnchorPane root;
    public JFXButton btnPets;
    public JFXButton btnSchedule;
    public JFXButton btnOrders;
    public AnchorPane sidePane;

    @FXML
    public void logoutOnAction() throws IOException {
        Alert logout = new Alert(Alert.AlertType.CONFIRMATION);
        logout.setTitle("Confirmation");
        logout.setContentText("Are you sure to logout from the system? ");
        logout.getButtonTypes().setAll(ButtonType.YES,ButtonType.CANCEL);
        Optional<ButtonType> result = logout.showAndWait();
        if (result.isPresent()&&result.get() == ButtonType.YES){
            logout();
        }
    }

    private void logout() throws IOException {
        AnchorPane logout;
        logout = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/Login/loginForm.fxml"));
        Scene scene = new Scene(logout);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

    }

    @FXML
    void appointmentsOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/EmployeeDash/appointmentsForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(parent);

    }

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/EmployeeDash/Customer.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(parent);
    }

    @FXML
    void ordersOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/EmployeeDash/orderForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(parent);
    }

    @FXML
    void petOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/EmployeeDash/petForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(parent);
    }

    @FXML
    void scheduleOnAction(ActionEvent event) throws IOException {
        Parent parent = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/EmployeeDash/petForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(parent);
    }
}

