import Cuentas.Account;
import Usuarios.User;

import java.util.ArrayList;

public class Bank {
    private final String name = "BankApp";
    private ArrayList<Account> accounts = new ArrayList<Account>();
    private ArrayList<User> users = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
