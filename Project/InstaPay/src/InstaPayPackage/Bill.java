package InstaPayPackage;

import java.sql.SQLException;
// import java.util.Random;   

public class Bill {
    String BillType;

    public Bill(String type) {
        BillType = type;
    }

    public boolean AbilityToBill(InstapayAccount accont, int cost) throws SQLException {
        InstaPayPackage.DatabaseHandler DB = new InstaPayPackage.DatabaseHandler();
        if (DB.retrieveBalance(accont) >= cost) {
            return true;
        }
        return false;
    }

    public double GenerateCost() {
        int min = 1;
        int max = 100;
        double Cost = (double) (Math.random() * (max - min + 1)) + min; // Generates a random double between 1 and 100
        return Cost;
    }
}