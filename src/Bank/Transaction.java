package Bank;

import Bank.Cuentas.Account;

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

    @Override
    public String toString(){
        String s = "Transaction ID: " + this.getTransactionID() +
                    "\nDate: " + this.getDate() +
                    "\nAmount: " + this.getAmount() +
                    "\nCurrency: " + this.getOrigin().getCurrency() +
                    "\nFrom account: " +
                    "\n\tAccount ID: " + this.getOrigin().getAccountID() +
                    "\n\tAccount type: " + this.getOrigin().getAccountType() +
                    "To account: " +
                    "\n\tAccount ID: " + this.getDestiny().getAccountID() +
                    "\n\tAccount type: " + this.getDestiny().getAccountType();
        return s;
    }
}
