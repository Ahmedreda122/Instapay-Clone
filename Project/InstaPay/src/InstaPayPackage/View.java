package InstaPayPackage;

import java.sql.SQLException;

public abstract class View {
    InstapayAccount account;
    InputUserData input;
    Account accountT;
    public View(){
        input = new InputUserData();
        account = new InstapayAccount();
    }
    abstract void makeView() throws SQLException;
}
