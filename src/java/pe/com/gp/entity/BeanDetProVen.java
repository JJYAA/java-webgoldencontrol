package pe.com.gp.entity;

import java.io.Serializable;

public class BeanDetProVen implements Serializable {

    static final long serialVersionUID = 1L;
    private String codigoEmpresa;
    private String tipoDocumento;
    private long numeroCP;
    private String tipoPropuesta;
    private String contadoCredito;

    private String fechaPropuesta;
    private String modeloVehiculo;
    private double totalOperacion;
    private String codigoSuperior;
    private String moneda;

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public long getNumeroCP() {
        return numeroCP;
    }

    public void setNumeroCP(long numeroCP) {
        this.numeroCP = numeroCP;
    }
    
    public String getTipoPropuesta() {
        return tipoPropuesta;
    }

    public void setTipoPropuesta(String tipoPropuesta) {
        this.tipoPropuesta = tipoPropuesta;
    }

    public String getContadoCredito() {
        return contadoCredito;
    }

    public void setContadoCredito(String contadoCredito) {
        this.contadoCredito = contadoCredito;
    }

    public String getFechaPropuesta() {
        return fechaPropuesta;
    }

    public void setFechaPropuesta(String fechaPropuesta) {
        this.fechaPropuesta = fechaPropuesta;
    }

    public String getModeloVehiculo() {
        return modeloVehiculo;
    }

    public void setModeloVehiculo(String modeloVehiculo) {
        this.modeloVehiculo = modeloVehiculo;
    }

    public double getTotalOperacion() {
        return totalOperacion;
    }

    public void setTotalOperacion(double totalOperacion) {
        this.totalOperacion = totalOperacion;
    }

    public String getCodigoSuperior() {
        return codigoSuperior;
    }

    public void setCodigoSuperior(String codigoSuperior) {
        this.codigoSuperior = codigoSuperior;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

}
