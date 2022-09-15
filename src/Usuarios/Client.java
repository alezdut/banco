package Usuarios;

import Cuentas.Cuenta;

import java.util.ArrayList;

public class Client extends User{
    private int document;
    private ArrayList<Cuenta> accounts = new ArrayList<Cuenta>();

    public Client(String name, String lastName, String userName, String passWord, int document, ArrayList<Cuenta> accounts){
        super(name, lastName, userName, passWord);
        this.document = document;
        this.accounts = accounts;
    }

    public int getDocument() {
        return document;
    }

    public void setDocument(int document) {
        this.document = document;
    }

    public ArrayList<Cuenta> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Cuenta> accounts) {
        this.accounts = accounts;
    }
}
