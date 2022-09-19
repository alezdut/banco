package Bank;

import Bank.Cuentas.Account;
import Bank.Menus.Create;
import Bank.Menus.Get;
import Bank.Usuarios.User;

import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Main {
    public static void main(String[] args){
        Set<String> unSet = new HashSet<String>();
        Set<User> usersSet = new HashSet<User>();
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        int option;
        User loggedUser = null;
        while (!exit) {
            if(loggedUser != null){
                try{
                System.out.println("Hola " + loggedUser.getName() + "!");
                System.out.println("Seleccione una de las opciones:");
                System.out.println("1. Revisar saldo");
                System.out.println("2. Crear Cuenta de inversion o ahorro");
                System.out.println("3. Hacer Transferencia");
                System.out.println("4. Revisar historial de transacciones");
                System.out.println("5. Cerrar Sesion");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("------------------------");
                        ArrayList<Account> accounts = Get.getAccountsByUser(loggedUser);
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
                        System.out.println("------------------------");
                        break;
                    case 2:
                        Create.createAccount(loggedUser);
                        break;
                    case 3:
                        Create.createTransaction(loggedUser);
                        break;
                    case 4:
                        ArrayList<Transaction> transactions = Get.getTransactionsByUser(loggedUser);
                        transactions.forEach(transaction -> {System.out.println(transaction);});
                    case 5:
                        loggedUser = null;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
                }
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.next();
            } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try {
                    System.out.println("Seleccione una de las opciones:");
                    System.out.println("1. Iniciar Sesión");
                    System.out.println("2. Crear Cuenta");
                    System.out.println("3. Salir");
                    option = sn.nextInt();

                    switch (option) {
                        case 1:
                            loggedUser =  Get.logIn();
                            break;
                        case 2:
                            loggedUser = new Create().createClient();
                            break;
                        case 3:
                            exit = true;
                            break;
                        default:
                            System.out.println("Solo números entre 1 y 3");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Debes insertar un número");
                    sn.next();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
