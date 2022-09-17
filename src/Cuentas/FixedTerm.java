package Cuentas;

import Usuarios.User;
import java.util.Date;

public class FixedTerm {
    //Class attributes declaration
    private Date startDate;
    private float amount;
    private Date endDate;
    private static final double NAR = 69.5;

    //Constructor
    public FixedTerm(float amount, int monthDuration) {
        this.startDate = new Date();
        this.amount = amount;
        this.endDate = new Date(startDate.getYear(), startDate.getMonth()+ monthDuration, startDate.getDate());
    }

    public static double calculateInterest(float amount, int monthDuration){
        //Interest formula
        return ((amount * ((NAR/12)*monthDuration))/100);
    }

    //Overload to calculate the month duration of the Fixed Term with two dates
    public static double calculateInterest(float amount, Date start, Date finish){
        int monthDuration;

        //Month duration of the Fixed Term calculus
        if(start.getYear() == finish.getYear())
            monthDuration = finish.getMonth() - start.getMonth();
        else
            monthDuration = (12-start.getMonth()) + finish.getMonth();

        //Interest formula
        return ((amount * ((NAR/12)*monthDuration))/100);
    }

    @Override
    public String toString(){
        return "Plazo fijo creado el: " + this.startDate +
                "\nMonto: " + this.amount +
                "\nFinaliza el: " + this.endDate +
                "\nInteres a generar: " + FixedTerm.calculateInterest(this.amount, this.startDate, this.endDate);
    }

}
