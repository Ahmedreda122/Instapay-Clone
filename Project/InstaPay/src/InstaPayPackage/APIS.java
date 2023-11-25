package InstaPayPackage;

import java.sql.SQLException;

public class APIS {
    private  InternalDB IDB;
    public  APIS() throws SQLException {
        IDB = new InternalDB();
    }
    public  boolean mobilePhoneIsExist(String mobilePhone, String accountType) throws SQLException{
        return IDB.checkTypeUsingNumber(mobilePhone, accountType);
    }
    public  double getBalance(String mobilePhone, String accountType) throws SQLException{
        return IDB.retrieveBalance(mobilePhone, accountType);
    }
    public  boolean RequestToWithdrawFromMyAccount(double amount, String mobileNumber, String accountType) throws SQLException{
        return IDB.updateBalance(accountType, amount, mobileNumber);
    }
}
