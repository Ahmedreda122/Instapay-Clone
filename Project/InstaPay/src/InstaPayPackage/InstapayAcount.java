package InstaPayPackage;

public class InstapayAcount {
    private String username;
    private String password;
    private String numberPhone;
    public Account accountObject;
    public  void InstapayAccount(){}
        public void InstapayAccount(Account account){
        this.accountObject = account;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }
    public void setPassword(String pass){
        this.password = pass;
    }
    public String getPassword(){
        return this.password;
    }
    public void setNumberPhone(String numberP){
        this.numberPhone = numberP;
    }
    public String getNumberPhone(){
        return this.numberPhone;
    }
}
