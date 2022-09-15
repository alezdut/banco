import java.time.LocalDate;
import java.util.Date;
import java.util.Scanner;
import Cuentas.*;

public class Main {
    public static void main(String[] args){
        Cuenta cuenta1 = new CuentaDeInversion(132134,2000,"ARS");
        ((CuentaDeInversion) cuenta1).menuPlazoFijo();
    }
}
