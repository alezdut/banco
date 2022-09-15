import Cuentas.*;

public class Main {
    public static void main(String[] args){
        Cuenta account1 = new CuentaDeInversion(132134,2000,"ARS");
        ((CuentaDeInversion) account1).menuPlazoFijo();
    }
}
