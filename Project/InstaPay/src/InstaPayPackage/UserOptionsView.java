package InstaPayPackage;

import java.sql.SQLException;
import java.util.Scanner;

public class UserOptionsView extends View {
  private UserOptions userOptions;

  public UserOptionsView() {
    userOptions = new UserOptions(this.account);
  }

  @Override
  void makeView() throws SQLException {
    System.out.println(
            "1. Transfer to another Instapay Account\n(You can Transfer to Bank Account only if You're Registered using Bank Account)\n" +
                    "2. Transfer to Bank Account(Using MobileNumber)\n" +
                    "3. Transfer To a Mobile Wallet(Using MobileNumber)\n" +
                    "4. Inquire about Your balance\n" +
                    "5. Paying utilities’ bills, where such bills can be for Gas, Electricity or Water.");
    int option = Integer.parseInt(super.input.enterYourOption());
    if (!(option >= 1 && option <= 5)) {
      System.out.println("Please, Try again");
      makeView();
      return;
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
        System.out.println("Your Balance is : " + userOptions.getMyAccount().getBalance());
      }
      case 5 -> {
//                System.out.println("PayBill");
        String op;
        while (true) {
          System.out.println("1.Water\n2.Gas\n3.Elec");
          op = super.input.enterYourOption();
          if (op.equals("1") || op.equals("2") || op.equals("3")) {
            break;
          }
          System.out.println("Please, Try again");
        }
        // method factory and type of account
        if (op.equals("1")) {
          userOptions.setBill(new BillWater());
        } else if (op.equals("2")) {
          userOptions.setBill(new BillGas());
        } else {
          userOptions.setBill(new BillElec());
        }
        // My Bill
        Bill myBill = userOptions.getBill();
        // Generated cost
        double cost = myBill.getCost();
        // My Account
        InstapayAccount myAcc = userOptions.getMyAccount();
        // My money
        double myMoney = myAcc.getBalance();
        // Bill Type
        String billType = myBill.getBillType();
        // User option input
        String choice = input.completeBill(billType, cost);
        if (choice.equals("1")) {
          if (myBill.AbilityToBill(myAcc)) {
//              userOptions.Transfer(myAcc.getType(),cost);
              System.out.println("Invoice For : "+billType+"\n"+"Cost : "+cost+"Client Name : "+myAcc.getUsername()+"Status : Done ");
          }
          else {
              System.out.println("The bill cost is higher than your balance");
          }
        }
        else {
            this.makeView();
        }
      }
      default -> System.out.println("Invalid Option");
    }
  }
}