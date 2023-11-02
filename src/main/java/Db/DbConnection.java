package Db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private static DbConnection dbConnection;

    private Connection connection;
    private DbConnection() throws SQLException {
        connection = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/pawsitiveCare",
                "root",
                "Ijse@1234"
        );
    }
    public DbConnection getInstance() throws SQLException {
        return (connection == null)?dbConnection = new DbConnection():dbConnection;
    }
    public Connection getConnection(){
        return connection;
    }
}
