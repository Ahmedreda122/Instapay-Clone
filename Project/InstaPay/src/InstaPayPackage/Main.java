package InstaPayPackage;
import java.sql.SQLException;
public class Main {
  public static void main(String[] args) throws SQLException {
    View c = new LoginView();
    View v = new UserOptionsView();
    c.makeView();
    v.makeView();
  }
}
// enter password with patter digits not less than 5 + not less than special char + not less than 2 char
//909090as###