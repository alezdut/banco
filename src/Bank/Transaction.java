package Bank;

import Bank.Cuentas.Account;
import Bank.Usuarios.User;

public class Transaction {
    private int transactionID;
    private String date;
    private float amount;
    private Account origin;
    private Account destiny;

    public Transaction(int transactionID, String date, float amount, Account origin, Account destiny) {
        this.transactionID = transactionID;
        this.date = date;
        this.amount = amount;
        this.origin = origin;
        this.destiny = destiny;
    }

    public int getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Account getOrigin() {
        return origin;
    }

    public void setOrigin(Account origin) {
        this.origin = origin;
    }

    public Account getDestiny() {
        return destiny;
    }

    public void setDestiny(Account destiny) {
        this.destiny = destiny;
    }

    public void printTransaction(User user){
        if(this.getDestiny().getAccHolder().getUserName().equals(user.getUserName()))
            System.out.println("ID: " + this.getTransactionID() + " Fecha: " + this.getDate() + " Monto: +" + this.getOrigin().getCurrency() + this.getAmount() + " de " + this.getOrigin().getAccHolder().getName() + " " + this.getOrigin().getAccHolder().getLastName());
        else
            System.out.println("ID: " + this.getTransactionID() + " Fecha: " + this.getDate() + " Monto: -" + this.getOrigin().getCurrency() + this.getAmount() + " a " + this.getDestiny().getAccHolder().getName() + " " + this.getDestiny().getAccHolder().getLastName());

    }
}
