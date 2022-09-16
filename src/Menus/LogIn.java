package Menus;

import Usuarios.User;

import java.util.Iterator;
import java.util.Scanner;
import java.util.Set;

public class LogIn {
    public static User logIn(Set<User> userSet){
        while(true) {
            Scanner input = new Scanner(System.in);
            System.out.println("\t***Iniciar sesion***\n");
            System.out.println("Ingrese su nombre de usuario: ");
            String userName = input.nextLine();

            System.out.println("Ingrese su contraseña: ");
            String password = input.nextLine();
            for (Iterator<User> it = userSet.iterator(); it.hasNext(); ) {
                User u = it.next();
                if (u.getUserName().equals(userName)) {
                    if (u.getPassword().equals(password)) {
                        return u;
                    }
                }
            }
            System.out.println("Usuario o contraseña incorrecta. ");
            System.out.println("Pulse 1 para reintentar o cualquier tecla para salir. ");
            String option = input.nextLine();
            if(!option.equals(1)){
                break;
            }
        }
        return null;
    }
}
