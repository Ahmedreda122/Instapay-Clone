package InstaPayPackage;
import java.sql.SQLException;
public class Main {
  public static void main(String[] args) throws SQLException {
    View v = new UserOptionsView();
    v.makeView();
  }
}
// enter password with patter digits not less than 5 + not less than special char + not less than 2 char
//909090as###