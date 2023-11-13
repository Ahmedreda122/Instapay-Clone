package InstaPayPackage;

public class InstapayAccount {
  private String username;
  private String password;
  private String numberPhone;
  private Account accountObject;

  public InstapayAccount() {
  }

  public InstapayAccount(Account account) {
    this.accountObject = account;
  }
  public String getType(){
    return this.accountObject.getType();
  }

  public double getBalance(){
    return this.accountObject.getBalance();
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getUsername() {
    return this.username;
  }

  public void setPassword(String pass) {
    this.password = pass;
  }

  public String getPassword() {
    return this.password;
  }

  public void setNumberPhone(String numberP) {
    this.numberPhone = numberP;
  }

  public String getNumberPhone() {
    return this.numberPhone;
  }

  public void setAccountObj(Account account){
    this.accountObject = account;
  }
}
