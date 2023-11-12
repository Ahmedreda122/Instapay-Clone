package InstaPayPackage;

public abstract class Account {
    private int balance;
    private String type;
    private String mobilePhone;
    private int id;

    public void setBalance(int balance) {
        this.balance = balance;
    }
    public int getBalance() {
        return balance;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
    public int getId() {
        return id;
    }
}
