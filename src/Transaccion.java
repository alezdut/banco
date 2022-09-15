public class Transaccion {
    private String codigo;
    private Date fecha;
    private float monto;
    private Account origen;
    private Account destino;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public float getMonto() {
        return monto;
    }

    public void setMonto(float monto) {
        this.monto = monto;
    }

    public Account getOrigen() {
        return origen;
    }

    public void setOrigen(Account origen) {
        this.origen = origen;
    }

    public Account getDestino() {
        return destino;
    }

    public void setDestino(Account destino) {
        this.destino = destino;
    }
}
