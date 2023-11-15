package InstaPayPackage;

import java.sql.SQLException;
import java.util.Scanner;

public class UserOptionsView extends View {
  private UserOptions userOptions;
  public UserOptionsView(InstapayAccount account) {
    userOptions = new UserOptions(account);
    super.account = account;
  }

  @Override
  void makeView() throws SQLException {
    System.out.println(getAccount().getUsername() + " " + this.getAccount().getNumberPhone() + " "
            + getAccount().getType());
    while (true) {
      System.out.println(
              "1. Transfer to another Instapay Account\n(You can Transfer to Bank Account only if You're Registered using Bank Account)\n" +
                      "2. Transfer to Bank Account(Using MobileNumber)\n" +
                      "3. Transfer To a Mobile Wallet(Using MobileNumber)\n" +
                      "4. Inquire about Your balance\n" +
                      "5. Paying utilities’ bills, where such bills can be for Gas, Electricity or Water." + "\n6)Exist.");

      int option = Integer.parseInt(super.input.enterYourOption());
      if (!(option >= 1 && option <= 6)) {
        System.out.println("Please, Try again");
        continue;
//      return null;
      }
      Scanner scanner = new Scanner(System.in);
      switch (option) {
        case 1, 2, 3 -> {
          String moneyStr = input.enterMoney();
          double money = Double.parseDouble(moneyStr);
          if (!userOptions.AbilityToTransfer(money)) {
            System.out.println("Transfer failed: Your account balance is insufficient for this transaction.");
            break;
          }
          switch (option) {
            case 1 -> {
              System.out.println("Enter the Username of the Instapay account you want to transfer to: ");
              String userName = scanner.nextLine();
              if (userOptions.Transfer(userName, money)) {
                System.out.println("The Money has been Transferred Successfully :)");
              }
            }
            case 2 -> {
              System.out.println("Enter the Mobile Number of the Bank Account you want to transfer to: ");
              String mobileNumber = scanner.nextLine();
              if (userOptions.TransferToBankAcc(mobileNumber, money)) {
                System.out.println("The Money has been Transferred Successfully :)");
              }
            }
            case 3 -> {
              System.out.println("Enter the Mobile Number of the Wallet you want to transfer to: ");
              String mobileNumber = scanner.nextLine();
              if (userOptions.TransferToWallet(mobileNumber, money)) {
                System.out.println("The Money has been Transferred Successfully :)");
              }
            }
          }
        }

        case 4 -> {
          System.out.println("Your Balance is : " + userOptions.getAccountBalance(account));
        }
        case 5 -> {
          View v = new PayBillView(account);
          v.makeView();
        }
        case 6 -> {
          System.out.println("Thanks");
          System.exit(0);
        }
        default -> {
          System.out.println("Invalid Option");
        }
      }
    }
  }
}
