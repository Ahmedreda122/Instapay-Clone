package InstaPayPackage;

import java.sql.SQLException;

public class Main {
  public static void main(String[] args) throws SQLException {
    InputUserData input = new InputUserData();

    while (true) {
      System.out.println("1)Login\n2)Register\n3)Exist.");
      String op = input.enterYourOptionInMain();
      View c;
      if (op.equals("1")) {
        c = new LoginView();
        c.makeView();
      } else if (op.equals("2")) {
        c = new ViewRegister();
        c.makeView();
        c = new LoginView();
        c.makeView();
      } else if (op.equals("3")) {
        System.exit(0);
      } else {
        System.out.println("Invalid option\nPlease try again.");
      }
    }
  }
}
// enter password with patter digits not less than 5 + not less than special char + not less than 2 char
// 909090as###
// 01112223152