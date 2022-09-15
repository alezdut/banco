package Cuentas;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import Usuarios.User;

public class InvestmentAccount extends Account {
    //Class member declaration
    private ArrayList<FixedTerm> activeFixedTerms;

    //Constructor
    public InvestmentAccount(int accountID, User accHolder, float balance, String currency) {
        super(accountID, accHolder, balance, currency);
    }

    @Override
    public float showAvailableBalance(){
        return balance;
    }

    public void fixedTermMenu(){
        int option = 0;
        do{
            //Printing menu on console
        System.out.println("\t***Menu de Plazo Fijo***\n");
        System.out.println("$$ Saldo Disponible: " + this.getBalance());
        System.out.println("-> Presione 1 para iniciar un nuevo plazo fijo");
        System.out.println("-> Presione 2 para consultar sus plazos fijos");
        System.out.println("-> Presione 3 para salir");

        Scanner keyboard = new Scanner(System.in);
        try {
            option = keyboard.nextByte();
            switch (option) {
                case 1 -> newFixedTerm(keyboard);
                case 2 -> getFixedTerms();
                case 3 -> {break;}
                default -> throw new InputMismatchException();
            }
        } catch (InputMismatchException e){ System.out.println("El valor ingresado no es una opcion disponible"); }
        } while (option != 3);
}

    private void newFixedTerm(Scanner keyboard) {
        //Local variables declaration
        float fixedTermAmount = 0;
        int fixedTermMonthDuration = 0;
        int opcion = 0;

        try {
            System.out.println("\t*Nuevo plazo fijo*");
            System.out.println("$$ Saldo Disponible: " + this.getBalance());
            System.out.println("Capital a invertir: "); fixedTermAmount = keyboard.nextFloat();
            //Add new insufficientBalance exception

            //MonthDuration should be a number between 1-12
            System.out.println("Meses de duracion del plazo: ");   fixedTermMonthDuration = keyboard.nextByte();
            if(fixedTermMonthDuration > 12 || fixedTermAmount < 0) throw new InputMismatchException();

            //Calculate interest
            System.out.println("El interes generado luego de " + fixedTermMonthDuration + " sera de: $" +
            FixedTerm.calculateInterest(fixedTermAmount,fixedTermMonthDuration));

            System.out.println("¿Desea efectivamente crear el plazo fijo? 1 = SI | 2 = NO\n");
            opcion = keyboard.nextByte();

            //Creating a new FixedTerm and adding it to the users account
            if(opcion == 1){
                FixedTerm abc = new FixedTerm(fixedTermAmount,fixedTermMonthDuration);
                this.activeFixedTerms.add(abc);
            } else if (opcion == 2) {
                System.out.println("Volviendo al menu de plazos fijos...\n");
                fixedTermMenu();
            }
            else throw new InputMismatchException();
        } catch (InputMismatchException e){ System.out.println("El valor ingresado no es una opcion disponible"); }
    }

    private void getFixedTerms(){
        //Redefine FixedTerm toString() method
    }

}
