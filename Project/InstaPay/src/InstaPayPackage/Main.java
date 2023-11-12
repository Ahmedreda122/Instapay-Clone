package InstaPayPackage;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) throws SQLException {
    View v = new ViewRegister();
    v.makeView();
    // hallo

  }
}
// enter password with patter digits not less than 5 + not less than special char + not less than 2 char
