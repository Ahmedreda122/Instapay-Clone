package InstaPayPackage;

public abstract class Account {
  private double balance;
  private String type;
  public double getBalance() {
    return balance;
  }
  public void setType(String type) {
    this.type = type;
  }

  public String getType() {
    return type;
  }

}
