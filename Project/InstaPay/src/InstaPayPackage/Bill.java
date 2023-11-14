package InstaPayPackage;

import java.sql.SQLException;
// import java.util.Random;   

public abstract class Bill {
  protected String BillType;
  double Cost;

  public Bill() {
    Cost = generateCost();
  }

  public boolean AbilityToBill(InstapayAccount account) throws SQLException {
    InstaPayPackage.DatabaseHandler DB = new InstaPayPackage.DatabaseHandler();
    if (DB.retrieveBalance(account) >= Cost) {
      return true;
    }
    return false;
  }

  private double generateCost() {
    int min = 1;
    int max = 100;
    double Cost = (double) (Math.random() * (max - min + 1)) + min; // Generates a random double between 1 and 100
    return Cost;
  }

  public String getBillType() {
    return BillType;
  }

  public double getCost() {
    return Cost;
  }

}