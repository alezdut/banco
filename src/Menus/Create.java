package Menus;

import DbConnect.DbConnect;
import Usuarios.Client;
import Usuarios.User;

import java.util.Scanner;
import java.util.Set;

public class Create {
    public boolean createAccount(){
        DbConnect connect = new DbConnect();
        connect.connect();
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

        System.out.println("Ingrese su nombre de usuario: ");
        String userName = input.nextLine();

        String password;
        String passwordCopy;
        while(true) {
            System.out.println("Ingrese su contraseña: ");
            password = input.nextLine();

            System.out.println("Ingrese nuevamente su contraseña: ");
            passwordCopy = input.nextLine();

            if (password.equals(passwordCopy)) {
                break;
            }
            else{
                System.out.println("Las contraseñas ingresadas no coinciden, intente nuevamente \n ");
            }
        }
        System.out.println("Guardando usuario en base de datos...");
        int valid = connect.execute("INSERT INTO BANK.user VALUES(0,'" + name + "', '" + lastName +"', '" + userName + "', '" + password +"')");
        if(valid == 1){
            return true;
        }
        else{
            System.out.println("ocurrio un error al guardar la informacion");
            return false;
        }
    }
}
