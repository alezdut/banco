package Bank.Menus;

import Bank.Cuentas.Account;
import Bank.DbConnect.DbConnect;
import Bank.Usuarios.Client;
import Bank.Usuarios.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Create {
    public Client createClient(){
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
            return new Client(name, lastName, userName, password);
        }
        else{
            System.out.println("ocurrio un error al guardar la informacion");
            return null;
        }
    }

    public static void createAccount(User user) throws SQLException {
        DbConnect connect = new DbConnect();
        connect.connect();
        Scanner input = new Scanner(System.in);
        String currency;
        String type;
        System.out.println("\t***Crear Cuenta***\n");
        System.out.println("Ingrese la moneda deseada: ");
        System.out.println("1. $: ");
        System.out.println("2. US$: ");
        System.out.println("3. salir");

        int option = input.nextInt();
        switch (option){
            case 1:
                currency = "$";
                break;
            case 2:
                currency = "US$";
                break;
            default:
                return;
        }

        System.out.println("Ingrese el tipo de cuenta: ");
        System.out.println("1. Cuenta de inversion ");
        System.out.println("2. Caja de ahorro ");
        System.out.println("3. Salir ");

        option = input.nextInt();
        switch (option){
            case 1:
                type = "investment";
                break;
            case 2:
                type = "savings";
                break;
            default:
                return;
        }
        int id = Get.getUserIdByUserName(user.getUserName());
        System.out.println("Guardando en base de datos...");
        int valid = connect.execute("INSERT INTO `BANK`.`account` (`account_id`, `currency`, `balance`, `type`, `user_id`) VALUES (0, '" + currency + "', 0, '" + type +"', " + id + ");");
        if(valid == 1){
            System.out.println("Cuenta creada con exito.");
        }
        else{
            System.out.println("Ocurrio un error al guardar la informacion");
        }
    }

    public static void createTransaction(User  loggedUser){
        DbConnect connect = new DbConnect();
        connect.connect();
        Scanner input = new Scanner(System.in);
        int amount;
        int origin;
        int destiny;
        AtomicInteger cont = new AtomicInteger(1);
        ArrayList<Account> accounts = Bank.Menus.Get.getAccountsByUser(loggedUser);
        System.out.println("\t***Nueva Transferencia***\n");
        System.out.println("Seleccione la cuenta de origen: ");
        accounts.forEach(e -> {
            System.out.println(cont + ". " + e.showAvailableBalance());
            cont.getAndIncrement();
        });
        int option = input.nextInt();
        origin = accounts.get(option -1).getAccountID();
        System.out.println("Ingrese el numero de cuenta destino: ");

        destiny = input.nextInt();
        Account destinyAccount = Bank.Menus.Get.getAccountById(destiny);
        Account originAccount = Bank.Menus.Get.getAccountById(origin);
        if(destinyAccount != null){
            System.out.println("------------------------");
            System.out.println("Cuenta destino seleccionada: ");
            System.out.println("Cuenta Numero: " + destinyAccount.getAccountID());
            System.out.println("Moneda: " + destinyAccount.getCurrency());
            System.out.println("Titular: " + destinyAccount.getAccHolder().getName() + " " + destinyAccount.getAccHolder().getLastName());
            System.out.println("------------------------");
            if(!Objects.equals(originAccount.getCurrency(), destinyAccount.getCurrency())){
                System.out.println("La Divisa cuenta seleccionada no corresponde a la divisa de la cuenta origen");
                return;
            }
            System.out.println("Ingrese el monto a transferir: ");
            amount = input.nextInt();
            if(amount > originAccount.getBalance()){
                System.out.println("No cuenta con saldo suficiente en la cuenta origen.");
            }
            else{
                System.out.println("Confirma la transferencia de "+ originAccount.getCurrency() + amount + " a la cuenta numero " + destinyAccount.getAccountID() + " de " + destinyAccount.getAccHolder().getName() + " " + destinyAccount.getAccHolder().getLastName() + "?");
                System.out.println("1. Aceptar");
                System.out.println("2. Cancelar");
                option = input.nextInt();
                switch (option){
                    case 1:
                        int valid = connect.execute("INSERT INTO `BANK`.`transaction` (`transaction_id`, `date`, `amount`, `origin_account_id`, `destiny_account_id`) VALUES (0, NOW(), '" + amount + "', '" + originAccount.getAccountID() +"', " + destinyAccount.getAccountID() + ");");
                        if(valid == 1){
                            Update.updateBalance(originAccount, destinyAccount, amount);
                            break;
                        }
                        else{
                            System.out.println("Ocurrio un error al guardar la informacion");
                            break;
                        }
                    case 2:
                        System.out.println("Operacion cancelada.");
                        break;
                }
            }
        }
        else{
            System.out.println("No se encontro la cuenta destino. ");
        }
    }
}
