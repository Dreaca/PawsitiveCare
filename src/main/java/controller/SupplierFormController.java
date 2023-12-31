package controller;

import Dto.SupplierDto;
import Dto.Tm.SupplierTm;
import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import model.SupplierModel;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SupplierFormController {

    public AnchorPane sidePane;
    public Label lblSupId;
    public TableColumn colDel;
    @FXML
    private ComboBox cmbType;

    @FXML
    private TableColumn<?, ?> colContact;

    @FXML
    private TableColumn<?, ?> colInvoice;

    @FXML
    private TableColumn<?, ?> colLocation;

    @FXML
    private TableColumn<?, ?> colSupName;

    @FXML
    private TableColumn<?, ?> colSupplierId;

    @FXML
    private TableColumn<?, ?> coltype;


    @FXML
    private TableView<SupplierTm> tblSupplier;

    @FXML
    private TextField txtContact;

    @FXML
    private TextField txtLocation;

    @FXML
    private TextField txtSearch;

    @FXML
    private TextField txtSupplierId;

    @FXML
    private TextField txtSupplierNAme;

    SupplierModel model = new SupplierModel();

    public void initialize() throws SQLException {
        generateSupplierId();
        setCellValueFactory();
        loadCmboBox();
        loadAllData();
    }

    @FXML
    private void generateSupplierId() throws SQLException {
        lblSupId.setText(model.generateSupplierId() );
    }

    private void setCellValueFactory() {
        colSupplierId.setCellValueFactory(new PropertyValueFactory<>("suppId"));
        colSupName.setCellValueFactory(new PropertyValueFactory<>("suppName"));
        coltype.setCellValueFactory(new PropertyValueFactory<>("type"));
        colLocation.setCellValueFactory(new PropertyValueFactory<>("location"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colInvoice.setCellValueFactory(new PropertyValueFactory<>("invoice"));
        colDel.setCellValueFactory(new PropertyValueFactory<>("delButton"));
    }

    private void loadAllData() {
        ObservableList<SupplierTm> oblist = FXCollections.observableArrayList();
        try {
            List<SupplierDto> list = model.getSupplierData();
            for (SupplierDto d : list) {
                oblist.add(
                        new SupplierTm(
                                d.getSuppId(),
                                d.getName(),
                                d.getType(),
                                d.getLocation(),
                                d.getContact(),
                                getHyperLink(),
                                getJFXbutton()
                        )
                );
            }
            tblSupplier.setItems(oblist);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private JFXButton getJFXbutton() {
        JFXButton bt = new JFXButton();
        Image image = new Image("view/Assets/icon/delete.png");
        ImageView imv = new ImageView(image);
        imv.setFitWidth(20);
        imv.setFitHeight(20);
        bt.setGraphic(imv);
        return bt;
    }

    private Hyperlink getHyperLink() {
        Hyperlink link = new Hyperlink("Invoice");
        return link;
    }



    private void loadCmboBox(){
        for (String type : Arrays.asList("Food","Medicine","Accessory","PetCare")) {
            cmbType.getItems().add(type);
        }
    }
    @FXML
    void addsupplierONAction(ActionEvent event) throws SQLException {
        if(validateFields()){
            if(validateRegex()){
                String supId = lblSupId.getText();
                String supName = txtSupplierNAme.getText();
                String type = (String) cmbType.getValue();
                String supLoc = txtLocation.getText();
                String supCon = txtContact.getText();
                var dto = new SupplierDto(supId,supName,type,supLoc,supCon);
                if(model.saveCustomer(dto)){
                    new Alert(Alert.AlertType.CONFIRMATION,"customer saved");
                    loadAllData();
                }
            }
        }else {
            // Show an alert or message indicating that some fields are empty
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Empty Fields");
            alert.setHeaderText(null);
            alert.setContentText("Please fill in all fields.");
            alert.showAndWait();
        }
    }

    private boolean validateRegex() {
        if (Pattern.matches("!|@|#|\'$\'|%|^|_|\'*\'|",txtSupplierNAme.getText())){
            new Alert(Alert.AlertType.WARNING,"Name Not Valid!!!").show();
            return false;
        } else if (!Pattern.matches(".", txtLocation.getText())) {
            new Alert(Alert.AlertType.WARNING,"Location Not Valid").show();
            return false;
        } else if (!Pattern.matches("(0|94|\\+94)([1-7]([0-9]))[0-9]{7}",txtContact.getText())) {
            new Alert(Alert.AlertType.WARNING,"Contact Not Valid").show();
            return false;
        }
        else return true;
    }

    private boolean validateFields() {
        return !txtSupplierId.getText().isEmpty() &&
                !txtSupplierNAme.getText().isEmpty() &&
                !txtContact.getText().isEmpty() &&
                !txtLocation.getText().isEmpty() &&
                cmbType.getValue() != null;
    }

    @FXML
    void searchOnAction(ActionEvent event) throws SQLException {
        String supId = txtSearch.getText();
        SupplierDto dto = model.getSupplierData(supId);
        ObservableList<SupplierTm> ob = FXCollections.observableArrayList();
        ob.add(
                new SupplierTm(
                        dto.getSuppId(),
                        dto.getName(),
                        dto.getType(),
                        dto.getLocation(),
                        dto.getContact(),
                        getHyperLink(),
                        getJFXbutton()
                )
        );
        tblSupplier.setItems(ob);
        lblSupId.setText(dto.getSuppId());
    }

    @FXML
    void searchSupplier(ActionEvent event) throws SQLException {
        searchOnAction(event);
    }

    @FXML
    void updateSupplierOnAction(ActionEvent event) throws SQLException {
        if(validateRegex()){
            String supId = lblSupId.getText();
            String supName = txtSupplierNAme.getText();
            String type = (String) cmbType.getValue();
            String supLoc = txtLocation.getText();
            String supCon = txtContact.getText();
            var dto = new SupplierDto(supId,supName,type,supLoc,supCon);
            if(model.updateSupplier(dto)){
                new Alert(Alert.AlertType.CONFIRMATION,"Supplier  updated");
                loadAllData();
            }
        }
    }

}
