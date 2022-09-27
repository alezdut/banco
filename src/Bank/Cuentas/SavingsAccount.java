package Bank.Cuentas;

import Bank.Usuarios.User;

public class SavingsAccount extends Bank.Cuentas.Account {
    //Constructor
    public SavingsAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public String showAvailableBalance(){
        return "Caja de ahorro Numero: " + getAccountID() + " Saldo: " + getCurrency() + getBalance();
    }

    @Override
    public String getAccountType(){
        return "Caja de ahorro";
    }
}
