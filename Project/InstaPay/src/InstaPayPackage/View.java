package InstaPayPackage;

import java.sql.SQLException;

public abstract class View {
  InstapayAccount account;
  InputUserData input;
  Account accountT;

  public View() throws SQLException {
    input = new InputUserData();
    account = new InstapayAccount();
  }

  public void setAccount(InstapayAccount account) {
    this.account = account;
  }

  public InstapayAccount getAccount() {
    return account;
  }

  abstract void makeView() throws SQLException;
}
