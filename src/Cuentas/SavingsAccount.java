package Cuentas;

import Usuarios.User;

public class SavingsAccount extends Account {
    //Constructor
    public SavingsAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public float showAvailableBalance(){
        return balance;
    }
}
