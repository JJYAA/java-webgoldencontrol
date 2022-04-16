package pe.com.gp.entity;

import java.io.Serializable;

public class CuentaCliente implements Serializable {

    static final long serialVersionUID = 1L;
    private String cliCodigo;
    private String bancoCodigo;
    private String bancoNombre;
    private String cuentaBancaria;
    private String moneda;

    public CuentaCliente() {
    }

    public String getCliCodigo() {
        return cliCodigo;
    }

    public void setCliCodigo(String cliCodigo) {
        this.cliCodigo = cliCodigo;
    }

    public String getBancoCodigo() {
        return bancoCodigo;
    }

    public void setBancoCodigo(String bancoCodigo) {
        this.bancoCodigo = bancoCodigo;
    }

    public String getBancoNombre() {
        return bancoNombre;
    }

    public void setBancoNombre(String bancoNombre) {
        this.bancoNombre = bancoNombre;
    }

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

}
