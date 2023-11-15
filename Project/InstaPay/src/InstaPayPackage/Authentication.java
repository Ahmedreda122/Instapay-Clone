package InstaPayPackage;

import java.sql.SQLException;

public class Authentication {
  private DatabaseHandler databaseHandler;

  public Authentication() throws SQLException {
    databaseHandler = new DatabaseHandler();
  }

  //    parameter ---> InstapayAccount Account in register ==> (username + password +
//    Account(wallet | BankAccount))
  public Boolean login(InstapayAccount account) throws SQLException {
    return databaseHandler.instapayAccountIsExisted(account);
  }

  public boolean checkDataForRegister(InstapayAccount account) throws SQLException {
    return (!databaseHandler.userNameIsRegistered(account.getUsername())) && Validation.passwordIsStrong(account.getPassword());
  }

  //        this method to check in register ---> if number phone in wallet | in bank Account -->i sExistNumPhone
//        we will check this number phone not registered before --> isNumPhoneRegistered
  public boolean verifyingAccount(InstapayAccount account) throws SQLException {
    return databaseHandler.numPhoneUsedInAccount(account) && (!databaseHandler.numPhoneIsRegistered(account.getNumberPhone()));
  }

  //    this method call insert in database after validate everything
  public void register(InstapayAccount account) throws SQLException {
    databaseHandler.insertInstapayAccount(account);
  }

  //     get myAccount
  public InstapayAccount getMyInstapayAccount(String username) {
    return databaseHandler.getInstapayAcc(username);
  }
}


