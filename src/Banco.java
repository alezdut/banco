import Cuentas.Cuenta;
import Usuarios.User;

import java.util.ArrayList;

public class Banco {
    private final String name = "BankApp";
    private ArrayList<Cuenta> accounts = new ArrayList<Cuenta>();
    private ArrayList<User> users = new ArrayList<User>();

    public String getName() {
        return name;
    }

    public ArrayList<Cuenta> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Cuenta> accounts) {
        this.accounts = accounts;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }
}
