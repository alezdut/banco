package Menus;

import Cuentas.Account;
import DbConnect.DbConnect;

public class Update {
    public static void updateBalance(Account originAccount, Account destinyAccount, float amount){
        DbConnect connect = new DbConnect();
        connect.connect();
        int valid = connect.execute("UPDATE `BANK`.`account` SET `balance` = " + (originAccount.getBalance() - amount) + " WHERE `account_id` = " + originAccount.getAccountID() + ";") + connect.execute("UPDATE `BANK`.`account` SET `balance` = " + (destinyAccount.getBalance() + amount) + " WHERE `account_id` = " + destinyAccount.getAccountID() + ";");
        if(valid == 2){
            System.out.println("Transferencia realizada con exito.");
        }
        else{
            System.out.println("ocurrio un error al guardar la informacion");
        }
    }
}
