package InstaPayPackage;

import java.sql.SQLException;

public class UserOptions {
    private DatabaseHandler DB;
    private InstapayAccount myAccount;

    public UserOptions(InstapayAccount account){
        this.myAccount = account;
        try {
            DB = new DatabaseHandler();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    InstapayAccount getMyAccount(){
        return myAccount;
    }
    public boolean TransferToWallet(String mobileNumber, double money){
        return DB.updateBalance("Wallet", money, mobileNumber) &&
        DB.updateBalance(myAccount.getType(), -money, myAccount.getNumberPhone());
    }
    public boolean TransferToBankAcc(String mobileNumber, double money){
        return DB.updateBalance("BankAccount", money, mobileNumber) &&
        DB.updateBalance(myAccount.getType(), -money, myAccount.getNumberPhone());
    }

    public boolean AbilityToTransfer(double money){
        return !(myAccount.getBalance() < money);
    }
    public boolean Transfer(String userName, double money){
        InstapayAccount recipientAccount = DB.getInstapayAcc(userName);
        if (recipientAccount == null){
            System.out.println("This userName is not Exist");
            return false;
        }

        if (recipientAccount.getType().equals("Wallet")){
           return TransferToWallet(recipientAccount.getNumberPhone(), money);
        }
        else if (recipientAccount.getType().equals("BankAccount") &&
                myAccount.getType().equals("BankAccount"))
        {
            return TransferToBankAcc(recipientAccount.getNumberPhone(), money);
        }
        else{
            System.out.println("You're trying to transfer money from your wallet to a bank account, which is not allowed.");
        }
        return false;
    }
}