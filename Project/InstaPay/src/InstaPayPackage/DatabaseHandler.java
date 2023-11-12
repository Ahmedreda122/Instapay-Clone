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
  public void insertRecord(Account account) throws SQLException {
    String query = "INSERT INTO " + "Wallet" + " (balance, mobileNumber) VALUES (?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, account.getBalance());
      preparedStatement.setString(2, account.getMobilePhone());
      preparedStatement.executeUpdate();
    }
  }
//  method to insert instapayAccount
public void insertIstapayAccount(InstapayAcount account) throws SQLException {
//    String query = "INSERT INTO " + obj.type + " (balance, mobileNumber) VALUES (?, ?)";
  String query = "INSERT INTO " + "InstaPayAccount" + " (username, password, accountType, mobileNumber) VALUES (?, ?, ?, ?)";
  try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
    preparedStatement.setString(1, account.getUsername());
    preparedStatement.setString(2, account.getPassword());
    preparedStatement.setString(3, account.accountObject.getType());
    preparedStatement.setString(4, account.getNumberPhone());
    preparedStatement.executeUpdate();
  }
}

  // Method to retrieve a record from the specified table by ID
  public void retrieveRecord(Object obj) throws SQLException {
//    String query = "SELECT obj.balance FROM " + obj.type + " WHERE mobileNumber = ?";
    String query = "SELECT balance FROM " + "Wallet" + " WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, "01112223152");

      ResultSet resultSet = preparedStatement.executeQuery();

      while (resultSet.next()) {
        int retrievedBalance = resultSet.getInt("balance");
        System.out.println("Balance: " + retrievedBalance);
      }
    }
  }

  public void updateRecord(Object obj) throws SQLException {
    // Assuming obj has properties type, id, and newBalance
//    String query = "UPDATE " + obj.type + " SET balance = ? WHERE mobileNumber = ?";
    String query = "UPDATE " + "Wallet" + " SET balance = ? WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setInt(1, 666);
      preparedStatement.setString(2, "01112223152");

      preparedStatement.executeUpdate();
    }
  }

  public boolean checkLogin(Object obj) throws SQLException {
    //    String query = "SELECT COUNT(*) AS count FROM InstaPayAccount WHERE obj.username = ? AND obj.password = ?";
    String query = "SELECT COUNT(*) AS count FROM InstaPayAccount WHERE username = ? AND password = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, "Abdallah");
      preparedStatement.setString(2, "123456");

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int count = resultSet.getInt("count");
        return count == 1;
      }

      return false;
    }
  }

  //  method to check if number is exist or no
  // please try to make method that perform only one task  ... thanks
  public boolean mobilePhoneIsExist(Object o) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM Wallet WHERE mobileNumber = ? ";
//    String query = "SELECT COUNT(*) AS count FROM obj.Type WHERE mobileNumber = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, "01112223152");

      ResultSet resultSet = preparedStatement.executeQuery();

      if (resultSet.next()) {
        int count = resultSet.getInt("count");
        return count == 1;
      }
      return false;
    }
  }
    // checking existing of num or username
//  public boolean check(Object obj) throws SQLException {
    public boolean checkNumOrUsr (Object obj,boolean isNum) throws SQLException {
      if (isNum) {
        String query = "SELECT COUNT(*) AS count FROM Wallet WHERE mobileNumber = ? ";
//    String query = "SELECT COUNT(*) AS count FROM obj.Type WHERE mobileNumber = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
          preparedStatement.setString(1, "01112223152");

          ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count == 1;
          }
          return false;
        }
      } else {
        String query = "SELECT COUNT(*) AS count FROM InstaPayAccount WHERE username = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
          preparedStatement.setString(1, "Abdallah");

          ResultSet resultSet = preparedStatement.executeQuery();

          if (resultSet.next()) {
            int count = resultSet.getInt("count");
            return count == 1;
          }
          return false;
        }
      }
    }
    // Close the database connection
    public void closeConnection () throws SQLException {
      if (connection != null && !connection.isClosed()) {
        connection.close();
      }

  }
}