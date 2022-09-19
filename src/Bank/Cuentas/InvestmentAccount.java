package Bank.Cuentas;

import java.util.ArrayList;
import java.util.Scanner;
import Bank.Usuarios.User;
import java.util.InputMismatchException;

public class InvestmentAccount extends Bank.Cuentas.Account {
    //Class attributes declaration
    private ArrayList<Bank.Cuentas.FixedTerm> activeFixedTerms = new ArrayList<>();

    //Constructor
    public InvestmentAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public String showAvailableBalance(){
        //System.out.println("Cuenta de Inversion Numero: " + getAccountID() + " Saldo: " + getCurrency() + getBalance());
        //this.getFixedTerms();
        return "Cuenta de Inversion Numero: " + getAccountID() + " Saldo: " + getCurrency() + getBalance();
    }

    public void fixedTermMenu(){
        int option = 0;
        Scanner keyboard = new Scanner(System.in);
        do{
            //Printing menu on console
        System.out.println("\t***Menu de Plazo Fijo***\n");
        System.out.println("$$ Saldo Disponible: " + this.getBalance());
        System.out.println("-> Presione 1 para iniciar un nuevo plazo fijo");
        System.out.println("-> Presione 2 para consultar sus plazos fijos");
        System.out.println("-> Presione 3 para salir");

        try {
            option = keyboard.nextByte(); keyboard.nextLine();
            switch (option) {
                case 1 -> newFixedTerm(keyboard);
                case 2 -> getFixedTerms();
                case 3 -> {return;}
                default -> throw new InputMismatchException();
            }
        } catch (InputMismatchException e){
            System.out.println("El valor ingresado no es una opcion disponible");
            return;
        }
        } while (option != 3);
}

    private void newFixedTerm(Scanner keyboard) {
        //Local variables declaration
        float fixedTermAmount;
        int fixedTermMonthDuration;
        int option;

        while (true) {
            try {
                System.out.println("\t*Nuevo plazo fijo*");
                System.out.println("$$ Saldo Disponible: " + this.getBalance());
                System.out.println("Capital a invertir: ");
                fixedTermAmount = keyboard.nextFloat(); keyboard.nextLine();
                //Add new insufficientBalance exception
                if(fixedTermAmount > this.getBalance()){
                    System.out.println("El balance de su cuenta de inversiones es insuficiente\n");
                    break;
                }

                //MonthDuration should be a number between 1-12
                System.out.println("Meses de duracion del plazo: ");
                fixedTermMonthDuration = keyboard.nextByte(); keyboard.nextLine();
                if (fixedTermMonthDuration > 12 || fixedTermAmount < 0) throw new InputMismatchException();

                //Calculate interest
                System.out.println("El interes generado luego de " + fixedTermMonthDuration + " sera de: $" +
                        Bank.Cuentas.FixedTerm.calculateInterest(fixedTermAmount, fixedTermMonthDuration));

                System.out.println("¿Desea efectivamente crear el plazo fijo? 1 = SI | 2 = NO\n");
                option = keyboard.nextByte(); keyboard.nextLine();

                //Creating a new FixedTerm and adding it to the users account
                if (option == 1) {
                    Bank.Cuentas.FixedTerm ft = new Bank.Cuentas.FixedTerm(fixedTermAmount, fixedTermMonthDuration);
                    this.activeFixedTerms.add(ft);
                    System.out.println("Plazo fijo creado con exito!");
                    System.out.println("Presione cualquier tecla para continuar: ");
                    keyboard.nextLine();
                    System.out.println("Volviendo al menu de plazos fijos...\n");
                    break;
                } else if (option == 2) {
                    System.out.println("Volviendo al menu de plazos fijos...\n");
                    break;
                } else throw new InputMismatchException();
            } catch (InputMismatchException e) {
                System.out.println("Ha ocurrido un problema o se ha solicitado volver atras");
                break;
            }
        }
        fixedTermMenu();
    }


    private void getFixedTerms(){
        Scanner keyboard = new Scanner(System.in);
        if(!activeFixedTerms.isEmpty()) {
            System.out.println("Usted tiene" + activeFixedTerms.size() + " plazos fijos activos: ");
            for (Bank.Cuentas.FixedTerm ft : activeFixedTerms) {
                System.out.println(ft);
            }
        }
        else System.out.println("Usted no tiene ningun plazo fijo activo asociado a su cuenta\n");
        System.out.println("Presione una tecla para continuar: ");
        keyboard.nextLine();
    }

    @Override
    public String getAccountType(){
        return "Cuenta de inversiones";
    }
}