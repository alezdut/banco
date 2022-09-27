package Bank.Usuarios;

import Bank.Cuentas.Account;
import Bank.DbConnect.DbConnect;
import Bank.Menus.Get;
import Bank.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Admin extends User{

    public Admin(String name, String lastName, String userName, String password) {
        super(name, lastName, userName, password);
    }

    @Override
    public boolean isAdmin() {
        return true;
    }

    public ArrayList<Transaction> getDailyTransactions() {
        DbConnect connect = new DbConnect();
        connect.connect();
        ArrayList<Transaction> dailyTransactions = new ArrayList<>();
        try{
            System.out.println("Cargando...");
            ResultSet transactionsDb = connect.get("SELECT `transaction`.`transaction_id`, `transaction`.`date`, `transaction`.`amount`, `transaction`.`origin_account_id`, `transaction`.`destiny_account_id` FROM `BANK`.`transaction` WHERE `transaction`.`date` = NOW() ;");
            while (transactionsDb.next()) {
                Transaction tc = new Transaction(Integer.parseInt(transactionsDb.getString("transaction_id")), transactionsDb.getString("date"), Float.parseFloat(transactionsDb.getString("amount")), Get.getAccountById(Integer.parseInt(transactionsDb.getString("origin_account_id"))), Get.getAccountById(Integer.parseInt(transactionsDb.getString("destiny_account_id"))));
                dailyTransactions.add(tc);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return dailyTransactions;
    }

    public int getTotalAccounts(){
        int totalAccounts;
        DbConnect connect = new DbConnect();
        connect.connect();
        ResultSet accounts = connect.get("SELECT COUNT(*) as count_acc FROM `BANK`.`account`");
        try {
            totalAccounts = accounts.getInt("count_acc");
        }
        catch (SQLException e){
            throw new RuntimeException();
        }
        return totalAccounts;
    }

}
