package controller;

import Dto.PetDto;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.LightBase;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lombok.NoArgsConstructor;
import model.CustomerModel;
import model.PetModel;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@NoArgsConstructor
public class UpdatePetController implements Initializable {
    public AnchorPane root;
    public TextField txtPetName;
    public TextField txtOwner;
    public ComboBox cmbBreed;
    public ComboBox cmbGender;
    public TextField txtColor;
    public Label lblPetID;
    public String  petId;
    public TextField txtAge;

    private PetModel model = new PetModel();

    private void loadData() throws SQLException {
        cmbBreed.getItems().addAll("Dog","Cat","Bird","Other");
        cmbGender.getItems().addAll("Male","Female");
    }

    public void doneOnAction() throws SQLException {
        String id = lblPetID.getText();
        String name = txtPetName.getText();
        int age = Integer.parseInt(txtAge.getText());
        String breed = (String) cmbBreed.getValue();
        String gender = cmbGender.getValue().toString();
        String ownerid = CustomerModel.getCustomerId(txtOwner.getText());
        String color = txtColor.getText();

        var dto = new PetDto(id,name,age,breed,gender,ownerid,color);
        try {
            if (model.updatePet(dto)) {
                new Alert(Alert.AlertType.CONFIRMATION,"Pet updated").show();
                Stage stage = (Stage) root.getScene().getWindow();
                stage.close();
            }
        }
        catch (SQLException e){
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }


    }
    public void cancelOnAction(){
        Stage window = (Stage) root.getScene().getWindow();
        window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            loadData();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setPetId(String petId) {
        this.petId = petId;
        lblPetID.setText(petId);
    }

    public void addNewRecordOnAction(MouseEvent mouseEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/dashBoards/pets/addNewRecord.fxml"));
        AnchorPane rot = loader.load();
        AddNewRecordController controller = loader.getController();
        controller.setPetID(lblPetID.getText());
        Scene scene = new Scene(rot);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
