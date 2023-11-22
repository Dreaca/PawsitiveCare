import java.io.*;
import java.sql.*;

public class Inserter {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            // Establishing the connection to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/pawsitiveCare", "root", "Ijse@1234");

            // Read the content of the image file into a byte array
            File imageFile = new File("/home/buddhika/Desktop/aiml/PawsitiveCare/src/main/resources/view/Assets/image/veterinary.png"); // Replace with your image file path
            FileInputStream fis = new FileInputStream(imageFile);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];

            int length;
            while ((length = fis.read(buffer)) != -1) {
                bos.write(buffer, 0, length);
            }
            byte[] imageData = bos.toByteArray();

            // SQL query to update an existing record with the sample blob data
            String updateQuery = "UPDATE employee SET image = ? WHERE employeeId = ?"; // Replace 'images' with your table name

            int imageIdToUpdate = 1; // Change the image ID of the record you want to update
            preparedStatement = connection.prepareStatement(updateQuery);
            preparedStatement.setBytes(1, imageData);
            preparedStatement.setString(2, "E003");
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Sample blob data inserted into the database successfully.");
            } else {
                System.out.println("Failed to insert sample blob data into the database.");
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Closing resources
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

