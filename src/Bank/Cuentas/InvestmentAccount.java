package Bank.Cuentas;

import Bank.Usuarios.User;

public class InvestmentAccount extends Bank.Cuentas.Account {
    //Constructor
    public InvestmentAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public String showAvailableBalance(){
        return "Cuenta de Inversion Numero: " + getAccountID() + " Saldo: " + getCurrency() + getBalance();
    }

    @Override
    public String getAccountType(){
        return "Cuenta de inversiones";
    }
}
