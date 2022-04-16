package pe.com.gp.entity;

import java.io.Serializable;

public class AccesorioVehiculo implements Serializable {

    private String vehModel;
    private String vehDescripcion;
    private String accCodigo;
    private String accDescripcion;
    private String accDetalle;
    private double accPrecio;
    private String accPrecioStr;

    public AccesorioVehiculo() {
    }

    public String getAccPrecioStr() {
        return accPrecioStr;
    }

    public void setAccPrecioStr(String accPrecioStr) {
        this.accPrecioStr = accPrecioStr;
    }

    public String getVehModel() {
        return vehModel;
    }

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public String getVehDescripcion() {
        return vehDescripcion;
    }

    public void setVehDescripcion(String vehDescripcion) {
        this.vehDescripcion = vehDescripcion;
    }

    public String getAccCodigo() {
        return accCodigo;
    }

    public void setAccCodigo(String accCodigo) {
        this.accCodigo = accCodigo;
    }

    public String getAccDescripcion() {
        return accDescripcion;
    }

    public void setAccDescripcion(String accDescripcion) {
        this.accDescripcion = accDescripcion;
    }

    public String getAccDetalle() {
        return accDetalle;
    }

    public void setAccDetalle(String accDetalle) {
        this.accDetalle = accDetalle;
    }

    public double getAccPrecio() {
        return accPrecio;
    }

    public void setAccPrecio(double accPrecio) {
        this.accPrecio = accPrecio;
    }

}
