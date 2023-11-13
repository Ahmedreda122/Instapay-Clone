package InstaPayPackage;

import java.sql.SQLException;
// import java.util.Random;   

public abstract class Bill {
    String BillType;
    double Cost;

    public Bill() {
        Cost=GenerateCost();
    }

    public boolean AbilityToBill(InstapayAccount accont, int cost) throws SQLException {
        InstaPayPackage.DatabaseHandler DB = new InstaPayPackage.DatabaseHandler();
        if (DB.retrieveBalance(accont) >= cost)
        {
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
    public void GenerateInvoice(InstapayAccount accont)
    {
        System.out.println("Invoice For : "+BillType+"\n"+"Cost : "+Cost+"Client Name : "+accont.getUsername()+"Status : Done ");
    }
    public abstract  void SetType(String Type);
}