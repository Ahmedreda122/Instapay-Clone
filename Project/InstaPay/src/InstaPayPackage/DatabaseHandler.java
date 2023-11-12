package InstaPayPackage;

import java.sql.*;

public class DatabaseHandler {

  private Connection connection;

  // Constructor to establish a SQLite database connection
  public DatabaseHandler() throws SQLException {
    String jdbcUrl = "jdbc:sqlite:" + "instapay.db";
    this.connection = DriverManager.getConnection(jdbcUrl);
  }

  // Method to insert a record into(Account Wallet | BankAccount) the specified table
//  public void insertRecord(Account account) throws SQLException {
//    String query = "INSERT INTO " + account.getType() + " (balance, mobileNumber) VALUES (?, ?)";
//    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//      preparedStatement.setInt(1, account.getBalance());
//      preparedStatement.setString(2, account.getMobilePhone());
//      preparedStatement.executeUpdate();
//    }
//  }

  //  method to insert instapayAccount --> Register
  public void insertInstapayAccount(InstapayAccount account) throws SQLException {
    String query = "INSERT INTO " + "InstaPayAccount" + " (username, password, accountType, mobileNumber) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getUsername());
      preparedStatement.setString(2, account.getPassword());
      preparedStatement.setString(3, account.accountObject.getType());
      preparedStatement.setString(4, account.getNumberPhone());
      preparedStatement.executeUpdate();
    }
  }

  // Method to retrieve a Balance
  public int retrieveBalance(InstapayAccount account) throws SQLException {
    String query = "SELECT balance FROM " + account.accountObject.getType() + " WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getNumberPhone());

      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.getInt("balance");
    }
  }
// ***Waiting for Transfering Methods***

//  public void updateRecord(InstapayAccount account) throws SQLException {
//    // Assuming obj has properties type, id, and newBalance
//    String query = "UPDATE " + account.accountObject.getType() + " SET balance = ? WHERE mobileNumber = ?";
////    String query = "UPDATE " + "Wallet" + " SET balance = ? WHERE mobileNumber = ?";
//    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
//      preparedStatement.setInt(1, 666);
//      preparedStatement.setString(2, "01112223152");
//
//      preparedStatement.executeUpdate();
//    }
//  }

// Method to check is the user has an instapay account
  public boolean instapayAccountIsExisted(InstapayAccount account) throws SQLException {
    //    String query = "SELECT COUNT(*) AS count FROM InstaPayAccount WHERE obj.username = ? AND obj.password = ?";
    String query = "SELECT COUNT(*) AS count FROM InstaPayAccount WHERE username = ? AND password = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getUsername());
      preparedStatement.setString(2, account.getPassword());

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int count = resultSet.getInt("count");
        return count == 1;
      }

      return false;
    }
  }
// check if the user's number phone is exist in either bank account or wallet
  public boolean numPhoneUsedInAccount(InstapayAccount account) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM " + account.accountObject.getType()+ " WHERE mobileNumber = ? ";
//    String query = "SELECT COUNT(*) AS count FROM obj.Type WHERE mobileNumber = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getNumberPhone());

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int count = resultSet.getInt("count");
        return count == 1;
      }
      return false;
    }
  }

  public boolean numPhoneIsRegistered(InstapayAccount account) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM " + "InstaPayAccount" + " WHERE mobileNumber = ? ";
//    String query = "SELECT COUNT(*) AS count FROM obj.Type WHERE mobileNumber = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getNumberPhone());
      ResultSet resultSet = preparedStatement.executeQuery();
      if (resultSet.next()) {
        int count = resultSet.getInt("count");
        return count == 1;
      }
      return false;
    }
  }
// check if the username is duplicated as it's unique
  public boolean userNameIsRegistered(String userName) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM InstaPayAccount WHERE username = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, userName);

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int count = resultSet.getInt("count");
        return count == 1;
      }
      return false;
    }

  }

  // Close the database connection
  public void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }

  }
}