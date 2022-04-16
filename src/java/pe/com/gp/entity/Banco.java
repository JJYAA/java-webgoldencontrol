package pe.com.gp.entity;

import java.io.Serializable;

public class Banco implements Serializable {

    private String codigo;
    private String nombre;
    private String moneda;
    private String cuenta;
    private String cuentaBancaria;
    private boolean existe;
    private String tipoCuenta;
    private String tipoCuenta_t;

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    
    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getTipoCuenta_t() {
        return tipoCuenta_t;
    }

    public void setTipoCuenta_t(String tipoCuenta_t) {
        this.tipoCuenta_t = tipoCuenta_t;
    }
    
    

    public Banco() {
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public boolean isExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
