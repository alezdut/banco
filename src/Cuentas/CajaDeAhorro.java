package Cuentas;

public class CajaDeAhorro extends Cuenta{
    public CajaDeAhorro(int numCuenta, float saldo, String divisa) {
        super(numCuenta, saldo, divisa);
    }
    @Override
    public float mostrarSaldoDisponible(){
        return saldo;
    }
}
