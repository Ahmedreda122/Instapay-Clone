package InstaPayPackage;

import java.sql.SQLException;

public class ViewRegister extends View {


  public ViewRegister() throws SQLException {
  }

  @Override
  public void makeView() throws SQLException {

    while (true) {
      account.setUsername(input.enterUserName());
      account.setPassword(input.enterPassword());
      if (authentication.checkDataForRegister(account)) {
        break;
      } else {
        System.out.println("Your username is used Or your password is weak.");
      }
    }
    while (true) {
      System.out.println("Enter your account type that you will registered with it(bankAccount or Wallet) : ");
      String accountType = input.scanner.nextLine();
      if (accountType.equals("Wallet")) {
        accountT = new Wallet();
        break;
      } else if (accountType.equals("BankAccount")) {
        accountT = new BankAccount();
        break;
      } else System.out.println("This account type Not Exist.");
    }
    account.setAccountObj(accountT);
    account.setNumberPhone(input.enterMobilePhone());
    if (authentication.verifyingAccount(account))
      authentication.register(account);
    else {
      System.out.println("This number phone used before Or Not exist for entered type");
      return;
    }
    System.out.println("Your have been registered.");
  }
}
