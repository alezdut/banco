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
                System.out.println("\t*** Hola " + loggedUser.getName() + "! Selecciona una de las opciones***\n");
                System.out.println("1. Revisar saldo");
                System.out.println("2. Crear Cuenta de inversion o ahorro");
                System.out.println("3. Hacer Transferencia");
                System.out.println("4. Revisar historial de movimientos");
                System.out.println("5. Cerrar Sesion");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Cargando...");
                        System.out.println("------------------------");
                        loggedUser.checkBalance();
                        System.out.println("------------------------");
                        break;
                    case 2:
                        Create.createAccount(loggedUser);
                        break;
                    case 3:
                        Create.createTransaction(loggedUser);
                        break;
                    case 4:
                        System.out.println("Cargando...");
                        ArrayList<Transaction> transactions = Get.getTransactionsByUser(loggedUser);
                        User finalLoggedUser = loggedUser;
                        System.out.println("------------------------");
                        transactions.forEach(t -> t.printTransaction(finalLoggedUser));
                        System.out.println("------------------------");
                        break;
                    case 5:
                        loggedUser = null;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 5");
                }
                   System.out.println("Presione cualquier tecla para continuar: ");
                   Scanner anyKey = new Scanner(System.in);
                   anyKey.nextLine();
            } catch (InputMismatchException e) {
                System.out.println("Debes insertar un número");
                sn.nextLine();
            } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                try {
                    System.out.println("\t***Seleccione una de las opciones***\n");
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
