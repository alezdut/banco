package Menus;

import Cuentas.Account;
import Cuentas.FixedTerm;
import Cuentas.InvestmentAccount;
import Cuentas.SavingsAccount;
import DbConnect.DbConnect;
import Usuarios.Client;
import Usuarios.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Get {
    public static User logIn() throws SQLException {
        DbConnect connect = new DbConnect();
        connect.connect();

        Scanner input = new Scanner(System.in);
        System.out.println("\t***Iniciar sesion***\n");
        System.out.println("Ingrese su nombre de usuario: ");
        String userName = input.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String password = input.nextLine();

        ResultSet user = connect.get("SELECT `user`.`user_id`,`user`.`name`,`user`.`last_name`, `user`.`user_name`, `user`.`password` FROM `BANK`.`user` WHERE user.user_name = '" + userName + "';");
        while(user.next()){
            if(user.getString("password").equals(password)){
                return new Client(user.getString("name"), user.getString("last_name"), user.getString("user_name"), user.getString("password"));
            };
        }
        System.out.println("Usuario o contraseña incorrecta. ");

        return null;
    }

    public static int getUserIdByUserName(String userName) throws SQLException {
        DbConnect connect = new DbConnect();
        connect.connect();
        int userId = -1;
        ResultSet user = connect.get("SELECT `user`.`user_id` FROM `BANK`.`user` WHERE user.user_name = '" + userName + "';");
        while(user.next()){
            userId =  Integer.parseInt(user.getString("user_id"));
        }
        return userId;
    }
    public static ArrayList<Account> getAccountsByUser(User user) {
        ArrayList<Account> accounts = null;
        try {
            DbConnect connect = new DbConnect();
            connect.connect();
            accounts = new ArrayList<Account>();
            ResultSet accountsDb = connect.get("SELECT `account`.`account_id`, `account`.`currency`, `account`.`balance`, `account`.`type`, `account`.`user_id`, `user`.`user_name` FROM `BANK`.`account` JOIN `BANK`.`user` WHERE `account`.`user_id` = `user`.`user_id` AND `user`.`user_name` = '" + user.getUserName() + "';");
            while (accountsDb.next()) {
                if (accountsDb.getString("type").equals("savings")) {
                    Account ac = new SavingsAccount(Integer.parseInt(accountsDb.getString("account_id")), user, Float.parseFloat(accountsDb.getString("balance")), accountsDb.getString("currency"));
                    accounts.add(ac);
                }
                if (accountsDb.getString("type").equals("investment")) {
                    Account ac = new InvestmentAccount(Integer.parseInt(accountsDb.getString("account_id")), user, Float.parseFloat(accountsDb.getString("balance")), accountsDb.getString("currency"));
                    accounts.add(ac);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ocurrio un error al consultar la informacion");
        }
        return accounts;
    }

    public static Account getAccountById(int accountId){
        Account account = null;
        try {
            DbConnect connect = new DbConnect();
            connect.connect();
            ResultSet accountsDb = connect.get("SELECT `account`.`account_id`, `account`.`currency`, `account`.`balance`, `account`.`type`, `account`.`user_id`, `user`.`user_name`, `user`.`name`, `user`.`last_name`, `user`.`password` FROM `BANK`.`account` JOIN `BANK`.`user` WHERE `account`.`user_id` = `user`.`user_id` AND `account`.`account_id` = '" + accountId + "';");
            while (accountsDb.next()) {
                if (accountsDb.getString("type").equals("savings")) {
                    account = new SavingsAccount(Integer.parseInt(accountsDb.getString("account_id")), new Client(accountsDb.getString("name"), accountsDb.getString("last_name"), accountsDb.getString("user_name"), accountsDb.getString("password")), Float.parseFloat(accountsDb.getString("balance")), accountsDb.getString("currency"));
                }
                if (accountsDb.getString("type").equals("investment")) {
                    account = new InvestmentAccount(Integer.parseInt(accountsDb.getString("account_id")), new Client(accountsDb.getString("name"), accountsDb.getString("last_name"), accountsDb.getString("user_name"), accountsDb.getString("password")), Float.parseFloat(accountsDb.getString("balance")), accountsDb.getString("currency"));
                }
            }
        }catch (SQLException e) {
            System.out.println("Ocurrio un error al consultar la informacion");
        }
        return account;
    }
}
