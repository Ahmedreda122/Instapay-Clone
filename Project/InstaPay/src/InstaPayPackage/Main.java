package InstaPayPackage;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws SQLException {
    View v = new ViewRegister();
    v.makeView();
//    Authentication a = new Authentication();
//    InputUserData inputUserData = new InputUserData();
//    InstapayAccount account = new InstapayAccount();
//    account.setPassword("1234aa%%%%");
//    account.setUsername("abdelhman");
//    if(a.login(account)){
//      System.out.println("su");
//    }else{
//      System.out.println("failed");
//    }
//    System.out.println(Validation.passwordIsStrong("1234aa%%%%"));
//    account.setUsername("Ahmed reda");
//    account.setPassword("4444ar$%#");
//    account.setNumberPhone("01112223654");

//    DatabaseHandler databaseHandler = null;
//
//    try {
//      // create a database connection
//      databaseHandler = new DatabaseHandler();
//
////      databaseHandler.insertRecord(new Object());
//      Account a = new Wallet();
//      InstapayAccount ia = new InstapayAccount(a);
//
////      a.setBalance(123243);
////      a.setMobilePhone("0117262334");
//      System.out.println("type : " + a.getType());
////      System.out.println(databaseHandler.retrieveBalance(ia));
//
////      databaseHandler.updateRecord(new Object());
////      System.out.println(databaseHandler.checkLogin(new Object()));
////      System.out.println(databaseHandler.isExistNumPhone(new Object()));
////      System.out.println(databaseHandler.isExistUser(new Object()));
////      databaseHandler.insertRecord(a);
//      // Other operations can be performed using methods in DatabaseHandler class
//
//    } catch (SQLException e) {
//      System.err.println(e.getMessage());
//    } finally {
//      try {
//        if (databaseHandler != null) {
//          databaseHandler.closeConnection();
//        }
//      } catch (SQLException e) {
//        System.err.println(e.getMessage());
//      }
//    }
  }
}
// enter password with patter digits not less than 5 + not less than special char + not less than 2 char
