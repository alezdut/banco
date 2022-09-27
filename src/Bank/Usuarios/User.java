package Bank.Usuarios;

import Bank.Cuentas.Account;
import Bank.Menus.Get;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public abstract class User {
    private String name;
    private String lastName;
    private String userName;
    private String password;
    private ArrayList<Account> accounts;
    public User(String name, String lastName, String userName, String password){
        this.name = name;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
    }
    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setName(String name){
        this.name = name;
    }
    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void checkBalance() {
        ArrayList<Account> accounts = Get.getAccountsByUser(this);
        AtomicReference<Float> saldoTotalPesos = new AtomicReference<>(0.0F);
        AtomicReference<Float> saldoTotalDolares = new AtomicReference<>(0.0F);
        accounts.forEach(e -> {
            System.out.println(e.showAvailableBalance());
            if(Objects.equals(e.getCurrency(), "$")){
                saldoTotalPesos.updateAndGet(v -> (v + e.getBalance()));
            }
            if(Objects.equals(e.getCurrency(), "US$")){
                saldoTotalDolares.updateAndGet(v -> (v + e.getBalance()));
            }

        });
        System.out.println("");
        System.out.println("Saldo Acumulado en Pesos: " + saldoTotalPesos);
        System.out.println("Saldo Acumulado en Dolares: " + saldoTotalDolares);
    }
    public abstract boolean isAdmin();


}
