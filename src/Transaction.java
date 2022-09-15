import Cuentas.Cuenta;

import java.util.Date;

public class Transaction {
    private String code;
    private Date date;
    private float amount;
    private Cuenta origin;
    private Cuenta destiny;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public Cuenta getOrigin() {
        return origin;
    }

    public void setOrigin(Cuenta origin) {
        this.origin = origin;
    }

    public Cuenta getDestiny() {
        return destiny;
    }

    public void setDestiny(Cuenta destiny) {
        this.destiny = destiny;
    }
}
