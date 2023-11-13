package InstaPayPackage;

import java.sql.SQLException;

public class UserOptionsView extends View{

    private UserOptions userOptions;

    public UserOptionsView(){
        userOptions = new UserOptions(this.account);
    }
    @Override
    void makeView() throws SQLException {
        System.out.println(
                "1. Transfer to another Instapay Account\n(You can Transfer to Bank Account only if You're Registered using Bank Account)\n" +
                "2. Transfer to Bank Account(Using MobileNumber)\n" +
                "3. Transfer To a Mobile Wallet(Using MobileNumber)\n" +
                "4. Inquire about Your balance" +
                "5. Paying utilities’ bills, where such bills can be for Gas, Electricity, or Water.");
        int option = Integer.parseInt(super.input.enterYourOption());
        if ( option  >= 1 && option  <= 5){
            System.out.println("\n");
        }
        else{
            System.out.println("Please, Try again");
            makeView();
        }

        switch(option){
            case 1 -> {
                System.out.println("To Be Continued");
            }
        }
    }
}