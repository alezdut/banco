package Usuarios;

import Cuentas.Account;

import java.util.ArrayList;

public class Client extends User{
    private int document;
    private ArrayList<Account> accounts = new ArrayList<Account>();

    public Client(String name, String lastName, int document){
        super(name, lastName);
        this.document = document;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }
}
