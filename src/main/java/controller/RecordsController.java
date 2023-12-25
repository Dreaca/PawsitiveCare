package controller;

import Dto.PetDto;
import Dto.RecordDto;
import Dto.Tm.RecordTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.PetModel;
import model.RecordsModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RecordsController {
    public Label lblPetname;
    public Label lblPetId;
    public Label lblPetBreed;
    public Label lblAge;
    public Label lblColor;
    public Label lblGender;
    public Label lblOwner;
    public TableView tblRec;
    public TableColumn recId;
    public TableColumn datecol;
    public TableColumn descCol;
    private String petId;

    private RecordsModel model = new RecordsModel();
    private PetModel p = new PetModel();

    public void takePetID(String petId) throws SQLException {
        this.petId = petId;
        setCellValueFactory();
        loadTable();
        loadData();
    }

    void loadData() throws SQLException {
        PetDto petData = p.getPetData(petId);
        lblPetId.setText(petData.getPetId());
        lblAge.setText(String.valueOf(petData.getAge()));
        lblColor.setText(petData.getColor());
        lblGender.setText(petData.getPetGender());
        lblOwner.setText(petData.getOwnerId());
        lblPetname.setText(petData.getPetName());
        lblPetBreed.setText(petData.getPetBreed());
    }

    void loadTable()  {
        ObservableList<RecordTm> oblist = FXCollections.observableArrayList();
        List<RecordDto> list = null;
        try {
            list = model.getRecords(petId);
            for (RecordDto d : list) {
                oblist.add(
                        new RecordTm(
                                d.getPetId(),
                                d.getRecordId(),
                                d.getDate(),
                                d.getDescription()
                        )
                );
            }
            tblRec.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setCellValueFactory(){
        recId.setCellValueFactory(new PropertyValueFactory<>("recordId"));
        datecol.setCellValueFactory(new PropertyValueFactory<>("date"));
        descCol.setCellValueFactory(new PropertyValueFactory<>("description"));
    }

    public void addNewRecordOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/dashBoards/pets/addNewRecord.fxml"));
        AnchorPane node = loader.load();
        AddNewRecordController controller = loader.getController();
        controller.setPetID(this.petId);
        Scene scene = new Scene(node);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
}
