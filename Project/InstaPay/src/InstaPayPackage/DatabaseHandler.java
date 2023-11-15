package InstaPayPackage;

import java.sql.*;

public class DatabaseHandler {
  private Connection connection;

  // Constructor to establish a SQLite database connection
  public DatabaseHandler() throws SQLException {
    String jdbcUrl = "jdbc:sqlite:" + "instapay.db";
    this.connection = DriverManager.getConnection(jdbcUrl);
  }
  //  method to insert instapay Account --> Register
  public void insertInstapayAccount(InstapayAccount account) throws SQLException {
    String query = "INSERT INTO InstaPayAccount (username, password, accountType, mobileNumber) VALUES (?, ?, ?, ?)";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getUsername());
      preparedStatement.setString(2, account.getPassword());
      preparedStatement.setString(3, account.getType());
      preparedStatement.setString(4, account.getNumberPhone());
      preparedStatement.executeUpdate();
    }
  }

  // Method to retrieve a Balance
  public double retrieveBalance(InstapayAccount account) throws SQLException {
    String query = "SELECT balance FROM " + account.getType() + " WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, account.getNumberPhone());

      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.getDouble("balance");
    }
  }

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
    String query = "SELECT COUNT(*) AS count FROM " + account.getType() + " WHERE mobileNumber = ? ";
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

//  check if the phone number is registered before in InstaPayAccount table
  public boolean numPhoneIsRegistered(String numberPhone) throws SQLException {
    String query = "SELECT COUNT(*) AS count FROM InstaPayAccount  WHERE mobileNumber = ? ";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, numberPhone);
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
  // updating balance by increasing/decreasing
  public boolean updateBalance(String tableName, double amount, String mobileNumber) {
    String query = "UPDATE " + tableName + " SET balance = balance + ? WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setDouble(1, amount);
      preparedStatement.setString(2, mobileNumber);
      preparedStatement.executeUpdate();
      return true;
    } catch (SQLException e) {
//      System.out.println("mobileNumber Does not exist in + tablename + "\n" + e);
      return false;
    }
  }


  public InstapayAccount getInstapayAcc(String userName) {
    String query = "SELECT * , COUNT(*) AS count FROM InstaPayAccount WHERE username = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, userName);
      return retrieveAcc(preparedStatement);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
//  to get type of my instapay account
  public String getType(String numberPhone){
    String query = "SELECT accountType FROM InstaPayAccount WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, numberPhone);
      ResultSet res = preparedStatement.executeQuery();
      return res.getString("accountType");
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  public boolean checkTypeUsingNumber(String mobilePhone, String tableName){
    String query = "SELECT COUNT(*) AS count FROM  " + tableName  + " WHERE mobileNumber = ?";
    try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
      preparedStatement.setString(1, mobilePhone);
      ResultSet res = preparedStatement.executeQuery();
      if(res.next()){
        int count = res.getInt("count");
                return (count == 1);
      }
      return  false;
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  private InstapayAccount retrieveAcc(PreparedStatement preparedStatement) throws SQLException {
    ResultSet resultSet = preparedStatement.executeQuery();
    int count = resultSet.getInt("count");
    if (count < 1) {
      return null;
    }
    InstapayAccount account = new InstapayAccount();
    Account externalAccount = null;
    account.setNumberPhone(resultSet.getString("mobileNumber"));
    account.setUsername(resultSet.getString("username"));
    String accountType = resultSet.getString("accountType");
    if (accountType.equals("Wallet")) {
      externalAccount = new Wallet();
    } else if (accountType.equals("BankAccount")) {
      externalAccount = new BankAccount();
    }
    account.setAccountObj(externalAccount);
    return account;
  }

  // Close the database connection
  public void closeConnection() throws SQLException {
    if (connection != null && !connection.isClosed()) {
      connection.close();
    }
  }
}
