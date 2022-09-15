package Cuentas;

import Usuarios.User;

public abstract class Account {
    //Class member declaration
    private int accountID;
    private User accHolder;
    float balance;
    String currency;

    //Constructor
    public Account(int accountID, User accHolder, float balance, String currency) {
        this.accountID = accountID;
        this.accHolder = accHolder;
        this.balance = balance;
        this.currency = currency;
    }

    //Getters and Setters
    public User getAccHolder() {return accHolder;}

    public void setAccHolder(User accHolder) {this.accHolder = accHolder;}

    public int getAccountID() {return accountID;}

    public void setAccountID(int accountID) {this.accountID = accountID;}

    public float getBalance() {return balance;}

    public void setBalance(float balance) {this.balance = balance;}

    public String getCurrency() {return currency;}

    public void setCurrency(String currency) {this.currency = currency;}

    public abstract float showAvailableBalance();
}
