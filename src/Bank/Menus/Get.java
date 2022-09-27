package Bank.Menus;

import Bank.Cuentas.Account;
import Bank.Cuentas.InvestmentAccount;
import Bank.Cuentas.SavingsAccount;
import Bank.DbConnect.DbConnect;
import Bank.Usuarios.Admin;
import Bank.Usuarios.Client;
import Bank.Usuarios.User;
import Bank.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

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

        ResultSet user = connect.get("SELECT `user`.`user_id`,`user`.`name`,`user`.`last_name`, `user`.`user_name`, `user`.`password`, `user`.`isAdmin` FROM `BANK`.`user` WHERE user.user_name = '" + userName + "';");
        while(user.next()){
            if(user.getString("password").equals(password)){
                if(user.getString("isAdmin") != null){
                    return new Admin(user.getString("name"), user.getString("last_name"), user.getString("user_name"), user.getString("password"));
                }
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

    public static ArrayList<Transaction> getTransactionsByUser(User user){
        DbConnect connect = new DbConnect();
        connect.connect();
        Scanner input = new Scanner(System.in);
        ArrayList<Account> accounts = Get.getAccountsByUser(user);
        AtomicInteger cont = new AtomicInteger(1);
        System.out.println("\t***Historial de Movimientos***\n");
        System.out.println("Seleccione la cuenta que desea consultar: ");
        accounts.forEach(e -> {
            System.out.println(cont + ". " + e.showAvailableBalance());
            cont.getAndIncrement();
        });
        int option = input.nextInt();
        int selected = accounts.get(option -1).getAccountID();

        System.out.println("Cargando...");
        Account account = Get.getAccountById(selected);
        ArrayList<Transaction> transactions = new ArrayList<Transaction>();

        try{
            ResultSet transactionsDb = connect.get("SELECT `transaction`.`transaction_id`, `transaction`.`date`, `transaction`.`amount`, `transaction`.`origin_account_id`, `transaction`.`destiny_account_id` FROM `BANK`.`transaction` WHERE `transaction`.`origin_account_id` = " + account.getAccountID() + " OR `transaction`.`destiny_account_id` = " + account.getAccountID() + ";");
            while (transactionsDb.next()) {
                Transaction tc = new Transaction(Integer.parseInt(transactionsDb.getString("transaction_id")), transactionsDb.getString("date"), Float.parseFloat(transactionsDb.getString("amount")), Get.getAccountById(Integer.parseInt(transactionsDb.getString("origin_account_id"))), Get.getAccountById(Integer.parseInt(transactionsDb.getString("destiny_account_id"))));
                transactions.add(tc);
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return transactions;
    }

    public static ArrayList<Client> getAllUsers(){
        ArrayList<Client> users = new ArrayList<Client>();
        try {
            DbConnect connect = new DbConnect();
            connect.connect();
            ResultSet usersDb = connect.get("SELECT `user`.`name`, `user`.`last_name`, `user`.`user_name`, `user`.`password` FROM `BANK`.`user` WHERE `user`.`isAdmin` IS NULL;");
            while (usersDb.next()) {
                Client client = new Client(usersDb.getString("name"), usersDb.getString("last_name"), usersDb.getString("user_name"), usersDb.getString("password"));
                users.add(client);
            }
        }catch (SQLException e) {
            System.out.println("Ocurrio un error al consultar la informacion");
        }
        return users;
    }

    public static float getGeneralBalancePesos(){
        float total = 0;
        try {
            DbConnect connect = new DbConnect();
            connect.connect();
            ResultSet balancesDb = connect.get("SELECT `account`.`balance` FROM `BANK`.`account` WHERE `account`.`currency` = '$';");
            while (balancesDb.next()) {
                total += balancesDb.getFloat("balance");
            }
        }catch (SQLException e) {
            System.out.println("Ocurrio un error al consultar la informacion");
        }
        return total;
    }

    public static float getGeneralBalanceDollars(){
        float total = 0;
        try {
            DbConnect connect = new DbConnect();
            connect.connect();
            ResultSet balancesDb = connect.get("SELECT `account`.`balance` FROM `BANK`.`account` WHERE `account`.`currency` = 'US$';");
            while (balancesDb.next()) {
                total += balancesDb.getFloat("balance");
            }
        }catch (SQLException e) {
            System.out.println("Ocurrio un error al consultar la informacion");
        }
        return total;
    }
}
