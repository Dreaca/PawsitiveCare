package controller;

import Dto.CustomerDto;
import Dto.PlaceOrderDto;
import Dto.Tm.AppointmentTm;
import Dto.Tm.OrderTm;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.CustomerModel;
import model.ItemModel;
import model.OrderModel;
import model.PlaceOrderModel;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperDesignViewer;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderFormController {
    public TableView tblOrder;
    public TableColumn colItemCode;
    public TableColumn colDescription;
    public TableColumn colQty;
    public TableColumn colUnitPrice;
    public TableColumn colAmount;
    public Label lblOrderId;
    public ComboBox cmbItemCode;
    public TextField txtUnitPrice;
    public TextField txtQty;
    public TextField txtDescription;
    public Label lblItemCount;
    public Label lblNetTotal;
    public TextField txtCustomerId;
    public TextField txtCustomerName;
    public TextField txtCustomerContact;
    public Label lblDate;

    private OrderModel model = new OrderModel();

    private PlaceOrderModel placeOrderModel = new PlaceOrderModel();
    private ItemModel itemModel = new ItemModel();
    private ObservableList<OrderTm> oblist = FXCollections.observableArrayList();
    public void itemCodeOnAction() throws SQLException {
        var dto = itemModel.getItemDesc((String) cmbItemCode.getValue());
        txtDescription.setText(dto.getDescription());
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
    }
    public void btnAddItemOnAction(){
        String itemCode = (String) cmbItemCode.getValue();
        String desc = txtDescription.getText();
        int qty = Integer.parseInt(txtQty.getText());
        double uni = Double.parseDouble(txtUnitPrice.getText());
        double amount = qty*uni;
        double total = calculateTotal()+amount;
        lblNetTotal.setText(String.valueOf(total));
        int itemCount = Integer.parseInt(lblItemCount.getText());
        itemCount++;
        lblItemCount.setText(String.valueOf(itemCount));
//        if (!oblist.isEmpty()){
//            for (int i = 0; i < tblOrder.getItems().size(); i++) {
//                if(colItemCode.getCellData(i).equals(itemCode)){
////                    int col_q = (int) colQty.getCellData(i);
////                    qty += col_q;
////                    amount = uni*col_q;
////                    oblist.get(i).setQty(qty);
////                    oblist.get(i).setAmount(amount);
//                    calculateTotal();
//                    tblOrder.refresh();
//                    itemCount++;
//                    lblItemCount.setText(String.valueOf(itemCount));
//                    return;
//                }
//            }
//        }
        var order = new OrderTm(itemCode,desc,qty,uni,amount);
        oblist.add(order);
        tblOrder.setItems(oblist);
        txtQty.clear();
        txtDescription.clear();
        txtUnitPrice.clear();
    }

    private double calculateTotal() {
        double total = 0;
        for (int i = 0; i < oblist.size(); i++) {
            double amount = oblist.get(i).getAmount();
            total = total+amount;
        }
        return total;
    }

    public void btnClearOnAction(){
        ObservableList<AppointmentTm> oblist = FXCollections.observableArrayList();
        oblist.add(null);
        tblOrder.setItems(oblist);
        tblOrder.refresh();
        cmbItemCode.promptTextProperty();
        txtDescription.clear();
        txtUnitPrice.clear();
        txtCustomerId.clear();
        txtQty.clear();
        txtCustomerName.clear();
        txtCustomerContact.clear();
        lblItemCount.setText("0");
        lblNetTotal.setText("0.0");
    }
    public void placeOrderOnAction(){
        String orderId = lblOrderId.getText();
        String custId = txtCustomerId.getText();
        LocalDate date = LocalDate.parse(lblDate.getText());


        List<OrderTm> orderList = new ArrayList<>();
        for (int i = 0; i < tblOrder.getItems().size(); i++) {
            OrderTm ordertm = oblist.get(i);
            orderList.add(ordertm);
        }
        var placeOrderDto = new PlaceOrderDto(orderId,custId,date,orderList);
        try {
            if(placeOrderModel.placeOrder(placeOrderDto)){
                createJasperReport(orderList);
                lblOrderId.setText(model.generateNextOrderId());
                btnClearOnAction();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR,e.getMessage()).show();
        }
    }

    private void createJasperReport(List<OrderTm> orderList) {
        try {
            Map parameters = new HashMap<>();
            parameters.put("OrderId", lblOrderId.getText());
            parameters.put("NetTotal", lblNetTotal.getText());

            for (int i = 0; i < orderList.size(); i++) {
                parameters.put("code",orderList.get(i).getItemCode());
                parameters.put("name",orderList.get(i).getDescription());
                parameters.put("Qty",String.valueOf(orderList.get(i).getQty()));
                parameters.put("unitPrice",String.valueOf(orderList.get(i).getUnitPrice()));
                parameters.put("amount",String.valueOf(orderList.get(i).getAmount()));
            }

            InputStream ResourceAsStream = getClass().getResourceAsStream("/report/bill.jrxml");
            JasperDesign load = JRXmlLoader.load(ResourceAsStream);
            JasperReport jasperReport = JasperCompileManager.compileReport(load);


            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());

            JasperViewer.viewReport(jasperPrint, false);
        } catch (JRException e) {
            e.printStackTrace();

        }
    }


    public void cancelOnAction(){
        
    }
    public void customerOnAction() throws SQLException {
        var cus = new CustomerModel();
        CustomerDto dt = cus.getCustomerDetail(txtCustomerName.getText());
        txtCustomerId.setText(dt.getCustomerId());
        txtCustomerContact.setText(dt.getCustomerContact());
    }
    public void addNewCustomerOnAction() throws IOException {
        Stage stage = new Stage();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/dashBoards/common/customerForm.fxml"))));
        stage.show();
    }
    public void initialize() throws SQLException {
        setCellValueFactory();
        loadAllData();
    }

    private void loadAllData() throws SQLException {
        lblOrderId.setText(model.generateNextOrderId());
        cmbItemCode.getItems().setAll(itemModel.getItemCodes());
        lblDate.setText(String.valueOf(LocalDate.now()));
    }

    private void setCellValueFactory() {
        colItemCode.setCellValueFactory(new PropertyValueFactory<>("itemCode"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("Qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    }
}
