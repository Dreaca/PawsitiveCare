package model;

import Db.DbConnection;
import Dto.OrderDto;
import Dto.PlaceOrderDto;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;


public class PlaceOrderModel {
    private OrderModel model = new OrderModel();
    private  ItemModel itemModel = new ItemModel();
    private OrderDetailModel orderDetailModel = new OrderDetailModel();
    public boolean placeOrder(PlaceOrderDto placeOrderDto) throws SQLException {
        String orderId = placeOrderDto.getOrderId();
        String custId = placeOrderDto.getCustId();
        LocalDate date = placeOrderDto.getDate();
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            if (model.saveOrder(orderId, custId, date)) {
                if (itemModel.updateItem(placeOrderDto.getList())) {
                    if (orderDetailModel.saveOrderDetails(placeOrderDto.getOrderId(), placeOrderDto.getList())) {
                        connection.commit();
                    }
                }
            }
            else connection.rollback();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.WARNING,e.getMessage()).show();
            connection.rollback();
        }
        finally {
            connection.setAutoCommit(true);
        }
        return true;
    }
}
