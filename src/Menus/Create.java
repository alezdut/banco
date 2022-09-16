package Menus;

import Usuarios.Client;
import Usuarios.User;

import java.util.Scanner;
import java.util.Set;

public class Create {
    public Client createAccount(Set unSet){
        Scanner input = new Scanner(System.in);
        boolean exit = false;
        System.out.println("\t***Crear Cuenta***\n");
        System.out.println("Ingrese su nombre: ");
        String name = input.nextLine();

        System.out.println("Ingrese su apellido: ");
        String lastName = input.nextLine();

        System.out.println("Ingrese su DNI: ");
        int document = input.nextInt();
        input.nextLine();
        Client client = new Client(name, lastName, document);

        while(true){
            System.out.println("Ingrese su nombre de usuario unico: ");
            String userName = input.nextLine();

            if(!unSet.contains(userName)){
                client.setUserName(userName);
                unSet.add(userName);
                break;
            }
            else{
                System.out.println("El nombre de usuario se encuentra en uso, seleccione uno diferente \n ");
            }
        }
        while(true) {
            System.out.println("Ingrese su contraseña: ");
            String password = input.nextLine();

            System.out.println("Ingrese nuevamente su contraseña: ");
            String passwordCopy = input.nextLine();

            if (password.equals(passwordCopy)) {
                client.setPassword(password);
                break;
            }
            else{
                System.out.println("Las contraseñas ingresadas no coinciden, intente nuevamente \n ");
            }
        }
        return client;
    }
}
