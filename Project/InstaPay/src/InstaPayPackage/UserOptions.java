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
    public boolean TransferToWallet(String mobileNumber, double money){
        return DB.updateBalance("Wallet", money, mobileNumber) &&
        DB.updateBalance(myAccount.getType(), -money, myAccount.getNumberPhone());
    }
    public boolean TransferToBankAcc(String mobileNumber, double money){
        return DB.updateBalance("BankAccount", money, mobileNumber) &&
        DB.updateBalance(myAccount.getType(), -money, myAccount.getNumberPhone());
    }

    public boolean AbilityToTransfer(double money){
        //  System.out.println("Transfer failed: Your account balance is insufficient for this transaction.");
        return !(myAccount.getBalance() < money);
    }
    public boolean Transfer(InstapayAccount recipientAccount, double money){
        if (recipientAccount.getType().equals("Wallet")){
           return TransferToWallet(recipientAccount.getNumberPhone(), money);
        }
        else if (recipientAccount.getType().equals("BankAccount") &&
                myAccount.getType().equals("BankAccount"))
        {
            return TransferToBankAcc(recipientAccount.getNumberPhone(), money);
        }
//        else{
//            System.out.println("You're trying to transfer money from your wallet to a bank account, which is not allowed.");
//        }
        return false;
    }
}