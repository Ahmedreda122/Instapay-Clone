package InstaPayPackage;

import java.sql.SQLException;

public class LoginView extends View {


  View userOptionsView;

  public LoginView() throws SQLException {
  }


  @Override
  void makeView() throws SQLException {
    account.setUsername(input.enterUserName());
    account.setPassword(input.enterPassword());
    if (super.authentication.login(account)) {
      userOptionsView = new UserOptionsView(authentication.getMyInstapayAccount(account.getUsername()));
      userOptionsView.makeView();
    } else {
      System.out.println("please try again\nYour password or username is wroung");
    }
  }
}
