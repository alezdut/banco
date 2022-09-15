package Cuentas;

import java.util.Date;
import java.time.*;

public class PlazoFijo {
    private Date fechaDeInicio;
    //Usuario titular
    private float monto;
    private Date fechaDeVencimiento;
    private final double interesMensual = 69.5;

    public PlazoFijo(Cuenta cuenta, float monto, int mesesDeDuracion) {
        this.fechaDeInicio = new Date();
        //this.titular = cuenta.getTitular();
        this.monto = monto;
        this.fechaDeVencimiento = new Date(fechaDeInicio.getYear(),fechaDeInicio.getMonth()+mesesDeDuracion,fechaDeInicio.getDate());
    }
}
