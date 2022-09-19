package Cuentas;

import Usuarios.User;

public class SavingsAccount extends Account {
    //Constructor
    public SavingsAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public String showAvailableBalance(){
        //System.out.println("Caja de ahorro Numero: " + getAccountID() + " Saldo: " + getCurrency() + getBalance());
        return "Caja de ahorro Numero: " + getAccountID() + " Saldo: " + getCurrency() + getBalance();
    }
}
