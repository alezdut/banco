package Cuentas;

import Usuarios.User;
import java.util.Date;

public class FixedTerm {
    //Class member declaration
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

}
