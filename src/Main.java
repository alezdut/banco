
import Menus.Create;
import Menus.LogIn;
import Usuarios.Client;
import Usuarios.User;

import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Set;

public class Main {
    public static void main(String[] args){
        Set<String> unSet = new HashSet<String>();
        Set<User> usersSet = new HashSet<User>();
        Scanner sn = new Scanner(System.in);
        boolean exit = false;
        boolean logged = false;
        int option;
        User loggedUser;

        while (!exit) {
            if(logged){
                System.out.println("Bienvenido!");
                System.out.println("Seleccione una de las opciones:");
                System.out.println("1. Revisar saldo");
                System.out.println("2. Crear Cuenta de inversion");
                System.out.println("3. Cerrar Sesion");
                option = sn.nextInt();

                switch (option) {
                    case 1:
                        System.out.println("Has seleccionado la opcion 1");
                        break;
                    case 2:
                        System.out.println("Has seleccionado la opcion 2");
                        break;
                    case 3:
                        loggedUser = null;
                        logged = false;
                        break;
                    default:
                        System.out.println("Solo números entre 1 y 3");
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
                            loggedUser = new LogIn().logIn(usersSet);
                            if(loggedUser != null){
                                logged = true;
                            }
                            break;
                        case 2:
                            loggedUser = new Create().createAccount(unSet);
                            if(loggedUser != null){
                                usersSet.add(loggedUser);
                                logged = true;
                            }
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
                }
            }
        }
    }
}
