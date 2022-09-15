package Usuarios;

import Cuentas.Account;

import java.util.ArrayList;

public class User {
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private int document;
    private ArrayList<Account> accounts = new ArrayList<Account>();
    public void User(String name, String lastName, String userName, String password, int document){
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.document = document;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }
}
