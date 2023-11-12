package InstaPayPackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseHandler {

  private Connection connection;

  // Constructor to establish a SQLite database connection
  public DatabaseHandler() throws SQLException {
    String jdbcUrl = "jdbc:sqlite:" + "instapay.db";
    this.connection = DriverManager.getConnection(jdbcUrl);
  }

  // Method to insert a record into the specified table
  public void insertRecord(Object obj) throws SQLException {
//    String query = "INSERT INTO " + obj.type + " (balance, mobileNumber) VALUES (?, ?)";
    String query = "INSERT INTO " + "Wallet" + " (balance, mobileNumber) VALUES (?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, 500);
      preparedStatement.setString(2, "01112223152");

      preparedStatement.executeUpdate();
    }
  }

  // Close the database connection
  public void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }
}
