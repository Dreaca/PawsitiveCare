package model;

import Db.DbConnection;
import Dto.SupplierDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierModel {
    public List<String> getSupplierIds() throws SQLException {
        List<String> list = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT suppId FROM supplier");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(
                    resultSet.getString(1)
            );
        }
        return list;
    }

    public List<SupplierDto> getSupplierData() throws SQLException {
        List<SupplierDto> list = new ArrayList<>();
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier");
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list.add(
              new SupplierDto(
                      resultSet.getString("suppId"),
                      resultSet.getString("name"),
                      resultSet.getString("type"),
                      resultSet.getString("location"),
                      resultSet.getString("contact")
              )
            );
        }
        return list;
    }

    public String generateSupplierId() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT suppId FROM supplier ORDER BY suppId DESC LIMIT 1");
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return getNext(resultSet.getString(1));
        }
        else return "SUP001";
    }

    private String getNext(String string) {
        if (!string.equals(null )){
            String[] id = string.split("SUP");
            int id1 = Integer.parseInt(id[1]);
            id1++;
            return String.format("SUP%03d",id1);
        }
        else return "SUP001";
    }

    public boolean saveCustomer(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("INSERT INTO supplier VALUES (?,?,?,?,?)");
        pstm.setString(1,dto.getSuppId());
        pstm.setString(2,dto.getLocation());
        pstm.setString(3,dto.getName());
        pstm.setString(4,dto.getType());
        pstm.setString(5,dto.getContact());
        return pstm.executeUpdate() > 0;
    }

    public SupplierDto getSupplierData(String supId) throws SQLException {
        SupplierDto list = new SupplierDto();
        Connection connection =DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM supplier WHERE suppId = ?");
        pstm.setString(1,supId);
        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()){
            list =(
                    new SupplierDto(
                            resultSet.getString("suppId"),
                            resultSet.getString("name"),
                            resultSet.getString("type"),
                            resultSet.getString("location"),
                            resultSet.getString("contact")
                    )
            );
        }
        return list;
    }
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement("UPDATE supplier SET name = ?, location = ?, type = ?, contact = ? WHERE suppId = ?");
        pstm.setString(1,dto.getName());
        pstm.setString(2,dto.getLocation());
        pstm.setString(3,dto.getType());
        pstm.setString(5,dto.getContact());
        pstm.setString(4,dto.getSuppId());
        return pstm.executeUpdate() > 0;
    }
}
