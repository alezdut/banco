package Bank.Menus;

import Bank.Cuentas.Account;
import Bank.DbConnect.DbConnect;
import Bank.Usuarios.User;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Update {
    public static void updateBalance(Account originAccount, Account destinyAccount, float amount){
        DbConnect connect = new DbConnect();
        connect.connect();
        int valid = connect.execute("UPDATE account SET balance = " + (originAccount.getBalance() - amount) + " WHERE account_id = " + originAccount.getAccountID()) + connect.execute("UPDATE account SET balance = " + (destinyAccount.getBalance() + amount) + " WHERE account_id = " + destinyAccount.getAccountID());
        if(valid == 2){
            System.out.println("Transferencia realizada con exito.");
        }
        else{
            System.out.println("Ocurrio un error al guardar la informacion");
        }
    }
    public static void updateBalance(User loggedUser){
        DbConnect connect = new DbConnect();
        connect.connect();

        Scanner input = new Scanner(System.in);
        AtomicInteger cont = new AtomicInteger(1);
        ArrayList<Account> accounts = Bank.Menus.Get.getAccountsByUser(loggedUser);
        int origin = 0;
        if(!(accounts.size() > 0)){
            System.out.println("Usted no tiene ninguna cuenta a su nombre, porfavor cree una cuenta y vuelva a intentarlo.\n");
            return;
        }
        System.out.println("\t***Ingreso de Dinero***\n");
        System.out.println("A continuación seleccione la cuenta a la que quiere ingresar dinero: ");
        accounts.forEach(e -> {
            System.out.println(cont + ". " + e.showAvailableBalance());
            cont.getAndIncrement();
        });
        int option = input.nextInt();
        if(option <= 0 || option > accounts.size()){
            System.out.println("Cuenta inexistente, porfavor seleccione una de sus cuentas disponibles.");
            return;
        }
        origin = accounts.get(option-1).getAccountID();
        Account acc = Bank.Menus.Get.getAccountById(origin);
        input.nextLine();
        if(acc != null){
            System.out.println("\nIngrese la cantidad de dinero a ingresar: ");
            float amount = input.nextFloat();
            int valid = connect.execute("UPDATE account SET balance = " + (acc.getBalance() + amount) + " WHERE account_id = " + acc.getAccountID());
            if(valid == 1){
                System.out.println("Se registro correctamente el ingreso.");
            }
            else{
                System.out.println("Ocurrio un error al guardar la informacion");
            }
        }


    }
}
