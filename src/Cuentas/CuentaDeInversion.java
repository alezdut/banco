package Cuentas;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CuentaDeInversion extends Cuenta{
    public CuentaDeInversion(int numCuenta, float saldo, String divisa) {
        super(numCuenta, saldo, divisa);
    }

    @Override
    public float mostrarSaldoDisponible(){
        return saldo;
    }

    public void menuPlazoFijo(){
        int opcion = 0;
        do{
        System.out.println("\t***Menu de Plazo Fijo***\n");
        System.out.println("$$ Saldo Disponible: " + this.getSaldo());
        System.out.println("-> Presione 1 para iniciar un nuevo plazo fijo");
        System.out.println("-> Presione 2 para consultar sus plazos fijos");
        System.out.println("-> Presione 3 para salir");
        Scanner teclado = new Scanner(System.in);
        try {
            opcion = teclado.nextByte();
            switch (opcion) {
                case 1 -> crearPlazoFijo();
                case 2 -> mostrarPlazosFijos();
                case 3 -> {break;}
                default -> throw new InputMismatchException();
            }
        } catch (InputMismatchException e){ System.out.println("El valor ingresado no es una opcion disponible"); }
        } while (opcion != 3);
}

    private void crearPlazoFijo() {

    }

    private void mostrarPlazosFijos(){

    }

}
