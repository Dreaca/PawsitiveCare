package controller;

import Dto.PetDto;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import model.CustomerModel;
import model.PetModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;

public class AddNewPetController {

    public ComboBox cmbBreed;
    public ComboBox cmbGender;
    public TextField txtOwner;
    public Label lblPetId;
    public TextField txtColor;
    public TextField txtPetName;
    public TextField txtPetAge;
    PetModel model = new PetModel();

    public void savePetOnAction() throws SQLException {
        String petId = lblPetId.getText();
        String petName = txtPetName.getText();
        int age = Integer.parseInt(txtPetAge.getText());
        String petBreed = String.valueOf(cmbBreed.getValue());
        String gender = (String) cmbGender.getValue();
        String ownerId = CustomerModel.getCustomerId(txtOwner.getText());
        String color = txtColor.getText();

        var dto = new PetDto(petId,petName,age,petBreed,gender,ownerId,color);
        boolean isSaved = PetModel.savePet(dto);
        if (isSaved) {
            new Alert(Alert.AlertType.CONFIRMATION,"Pet Saved ").show();
//            PetFormController.loadAllData();
        }else{
            new Alert(Alert.AlertType.ERROR,"Something went wrong").show();
        }

    }
    public void initialize() throws SQLException {
        lblPetId.setText(model.getNextPetId());
        loadBreedAndGender();
    }
    private void loadBreedAndGender() {
        for (String string : Arrays.asList("Dog", "Cat", "Bird", "other")) {
            cmbBreed.getItems().add(string);
        }
        for(String s : Arrays.asList("Male","Female")){
            cmbGender.getItems().add(s);
        }
    }

    public void cancelPetOnAction(ActionEvent event) {


    }
}
