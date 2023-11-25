package InstaPayPackage;

import java.sql.SQLException;

public class UserOptions {
  private DatabaseHandler DB;
  private APIS api;
  private InstapayAccount myAccount;
  private Bill bill;

  public UserOptions(InstapayAccount account) throws SQLException {
    this.myAccount = account;
    api = new APIS();
    try {
      DB = new DatabaseHandler();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
  InstapayAccount getMyAccount() {
    return myAccount;
  }

  public Bill getBill() {
    return this.bill;
  }

  public void setBill(Bill bill) {
    this.bill = bill;
  }

  public boolean TransferToWallet(String mobileNumber, double money) throws SQLException {
    return api.RequestToWithdrawFromMyAccount(money, mobileNumber, "Wallet") &&
            api.RequestToWithdrawFromMyAccount(-money,myAccount.getNumberPhone() , myAccount.getType());
  }

  public boolean TransferToBankAcc(String mobileNumber, double money) throws SQLException{
    return api.RequestToWithdrawFromMyAccount(money, mobileNumber, "BankAccount") &&
            api.RequestToWithdrawFromMyAccount(-money, myAccount.getNumberPhone(), myAccount.getType());
  }

  public boolean AbilityToTransfer(double money) throws SQLException {
    System.out.println("IN userOptions");
    System.out.println("balace : " + getAccountBalance(myAccount));
    System.out.println("MYAccount : " + myAccount.getNumberPhone() + " " + myAccount.getType() + " " + myAccount.getNumberPhone());
    return (getAccountBalance(myAccount) >= money) && money > 0;
  }

  public void payBill(InstapayAccount account, double cost) {
    DB.updateBalance(account.getType(), cost, account.getNumberPhone());
  }

  public boolean Transfer(String userName, double money) throws SQLException{
    InstapayAccount recipientAccount = DB.getInstapayAcc(userName);
    if (recipientAccount == null) {
      System.out.println("This userName is not Exist");
      return false;
    }

    String accountType = recipientAccount.getType();
    if (accountType.equals("Wallet")) {
      return TransferToWallet(recipientAccount.getNumberPhone(), money);
    } else if (accountType.equals("BankAccount") &&
            myAccount.getType().equals("BankAccount")) {
      return TransferToBankAcc(recipientAccount.getNumberPhone(), money);
    } else {
      System.out.println("You're trying to transfer money from your wallet to a bank account, which is not allowed.");
    }
    return false;
  }

  public double getAccountBalance(InstapayAccount account) throws SQLException {
    return api.getBalance(account.getNumberPhone(), account.getType());
  }
  public boolean checkTypeAccountWithMobileNumber(String mobile, String tableN)  throws SQLException{
    return api.mobilePhoneIsExist(mobile, tableN);
  }

//  instapay account
//  chcek if this username is exist or no
  public boolean checkExistenceUser(String username) throws SQLException{
    return DB.userNameIsRegistered(username);
  }
////  check if this number phone is exist or no
//  public  boolean numberPhoneIsExist(String numberPhone) throws SQLException {
//    return DB.numPhoneIsRegistered(numberPhone);
//  }
//  public String getTypeAccount(String mobileNum){
//    return DB.getType(mobileNum);
//  }
}