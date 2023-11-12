package InstaPayPackage;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    DatabaseHandler databaseHandler = null;

    try {
      // create a database connection
      databaseHandler = new DatabaseHandler();

//      databaseHandler.insertRecord(new Object());
      Account a = new Wallet();
      InstapayAccount ia = new InstapayAccount(a);

//      a.setBalance(123243);
//      a.setMobilePhone("0117262334");
//      System.out.println("type : " + a.getType());
      System.out.println(databaseHandler.retrieveBalance(a));

//      databaseHandler.updateRecord(new Object());
//      System.out.println(databaseHandler.checkLogin(new Object()));
//      System.out.println(databaseHandler.isExistNumPhone(new Object()));
//      System.out.println(databaseHandler.isExistUser(new Object()));
//      databaseHandler.insertRecord(a);
      // Other operations can be performed using methods in DatabaseHandler class

    } catch (SQLException e) {
      System.err.println(e.getMessage());
    } finally {
      try {
        if (databaseHandler != null) {
          databaseHandler.closeConnection();
        }
      } catch (SQLException e) {
        System.err.println(e.getMessage());
      }
    }
  }
}
