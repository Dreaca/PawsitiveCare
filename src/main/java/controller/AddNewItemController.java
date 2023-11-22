package controller;

import Dto.ItemDto;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.ItemModel;

import java.sql.SQLException;

public class AddNewItemController {

    public Label lblItemCode;
    public TextField txtDescription;
    public TextField txtQtO;
    public TextField txtUnitPrice;
    public AnchorPane root;
    ItemModel model = new ItemModel();

    public void cancelOnAction(ActionEvent actionEvent) {
        Stage stage = (Stage) root.getScene().getWindow();
        stage.close();
    }

    public void saveOnAction(ActionEvent actionEvent) {
        String itemCode = lblItemCode.getText();
        String desc = txtDescription.getText();
        String qtOText = txtQtO.getText();
        String unitPriceText = txtUnitPrice.getText();

        if (checkNotNull(itemCode) && checkNotNull(desc) && checkNotNull(qtOText) && checkNotNull(unitPriceText)
                && validateItemCode(itemCode) && validateDescription(desc)
                && validateQuantity(qtOText) && validateUnitPrice(unitPriceText)) {
            // Conversion after null checks and validations
            int QTO = Integer.parseInt(qtOText);
            double unitPrice = Double.parseDouble(unitPriceText);

            var dto = new ItemDto(itemCode, desc, QTO, unitPrice);
            try {
                if (model.saveItem(dto)) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Item Saved !!!").show();
                    Stage stage = (Stage) root.getScene().getWindow();
                    stage.close();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            new Alert(Alert.AlertType.ERROR, "Invalid input(s) detected! Please check your input.").show();
        }
    }
    private boolean checkNotNull(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private boolean validateItemCode(String itemCode) {
        String regex = "^[I][0-9]{3,}+$";
        return itemCode.matches(regex);
    }

    private boolean validateDescription(String desc) {
        String regex = "^[\\s\\S]*$";
        return desc.matches(regex);
    }

    private boolean validateQuantity(String qtO) {
        String regex = "^[1-9]\\d*$";
        return qtO.matches(regex);
    }

    private boolean validateUnitPrice(String unitPrice) {
        String regex = "^(?!0\\.00$)\\d+(\\.\\d{1,2})?$";
        return unitPrice.matches(regex);
    }

    public void initialize() throws SQLException {
        setLabel();
    }
    public void setLabel() throws SQLException {
        lblItemCode.setText(model.getNextItemCode());
    }
}
