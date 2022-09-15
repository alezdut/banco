package Cuentas;

public abstract class Cuenta {
    private int numCuenta;
    //private Usuario titular;
    float saldo;
    String divisa;      //o enum

    public Cuenta(int numCuenta, float saldo, String divisa) {
        this.numCuenta = numCuenta;
        this.saldo = saldo;
        this.divisa = divisa;
    }

    public int getNumCuenta() {return numCuenta;}

    public void setNumCuenta(int numCuenta) {this.numCuenta = numCuenta;}

    public float getSaldo() {return saldo;}

    public void setSaldo(float saldo) {this.saldo = saldo;}

    public String getDivisa() {return divisa;}

    public void setDivisa(String divisa) {this.divisa = divisa;}

    public abstract float mostrarSaldoDisponible();
}
