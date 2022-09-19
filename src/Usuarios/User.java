package Usuarios;

import Cuentas.Account;

import java.util.ArrayList;

public abstract class User {
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private ArrayList<Account> accounts;
    public User(String name, String lastName, String userName, String password){
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
