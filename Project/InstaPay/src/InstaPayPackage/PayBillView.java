package InstaPayPackage;

import java.sql.SQLException;

public class PayBillView extends View{
    UserOptions userOptions;
    public PayBillView(InstapayAccount account){
        userOptions = new UserOptions(account);
        this.account = account;
    }
    @Override
    void makeView() throws SQLException {
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
            if (myBill.AbilityToBill(myAcc))
            {
                userOptions.payBill(myAcc, cost);
//            userOptions.Transfer(myAcc.getType(),cost);
                System.out.println("Invoice For : "+ billType+"\n"+"Cost : "+cost+"Client Name : "+myAcc.getUsername()+"Status : Done ");
            }
            else
            {
                System.out.println("The bill cost is higher than your balance");
            }
        }
        else
        {
            this.makeView();
        }
    }
}
