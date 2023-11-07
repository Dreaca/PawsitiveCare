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
import java.sql.SQLException;
import java.util.Optional;

public class AdminFormController {
    public AnchorPane sidePane;
    public AnchorPane root;
    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnUser;

    @FXML
    void customerOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/customerForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);
    }

    @FXML
    void employeeOnAction(ActionEvent event) throws IOException, SQLException {
        Parent empNode = FXMLLoader.load(this.getClass().getResource("/view/employeeManage/ManageEmployee.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(empNode);

    }

    @FXML
    void reportOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/reportForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);

    }

    @FXML
    void supplierOnAction(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/supplierForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(root);

    }

    @FXML
    void userBtnOnAction(ActionEvent event) throws IOException {
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
        logout = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/common/loginForm.fxml"));
        Scene scene = new Scene(logout);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setTitle("Login");
        stage.show();

    }

    @FXML
    void vetOnAction(ActionEvent event) throws IOException {
        Parent vetNode = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/vetForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(vetNode);
    }

    public void manageStockOnAction(ActionEvent actionEvent) throws IOException {
        Parent stock = FXMLLoader.load(this.getClass().getResource("/view/dashBoards/AdminDash/manageItemForm.fxml"));
        this.sidePane.getChildren().clear();
        this.sidePane.getChildren().add(stock);
    }
}
