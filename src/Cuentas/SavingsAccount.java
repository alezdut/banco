package Cuentas;

import Usuarios.User;

public class SavingsAccount extends Account {
    //Constructor
    public SavingsAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public void showAvailableBalance(){
        System.out.println("Usted tiene $" + this.getBalance() + " saldo disponible en su caja de ahorro.");
    }
}
