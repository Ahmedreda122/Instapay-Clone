package InstaPayPackage;

import java.sql.SQLException;

public abstract class View {
    InstapayAccount account;
    InputUserData input;
    Account accountT;
    abstract void makeView() throws SQLException;
}
