package InstaPayPackage;

import java.sql.SQLException;

public class LoginView extends View {

  Authentication authentication;
  View userOptionsView;

  public LoginView() throws SQLException {
    authentication = new Authentication();
  }

  @Override
  void makeView() throws SQLException {
    account.setUsername(input.enterUserName());
    account.setPassword(input.enterPassword());
    if (authentication.login(account)) {
      userOptionsView = new UserOptionsView(authentication.getMyInstapayAccount(account.getUsername()));
      userOptionsView.makeView();
    } else {
      System.out.println("please try again\nYour password or username is wroung");
    }
  }
}
