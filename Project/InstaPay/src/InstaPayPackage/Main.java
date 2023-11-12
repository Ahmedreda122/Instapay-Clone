package InstaPayPackage;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) {
    DatabaseHandler databaseHandler = null;

    try {
      // create a database connection
      databaseHandler = new DatabaseHandler();

      // Example: Insert records into the "wallet" table
      databaseHandler.insertRecord(new Object());

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
