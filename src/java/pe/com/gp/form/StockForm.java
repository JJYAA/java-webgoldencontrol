/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.form;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Jpalomino
 */
public class StockForm extends ActionForm implements Serializable {
    private String empresa;
    private String producto;
    private String operacion;
    private String ubicacion;
    private String cantidadCajas;
    private String cantidad;
    private String TotalAntes;
    private String dispoAntes;
    private String TotalDesp;
    private String dispoDesp;  
    private String fechaIni;
    private String fechaFin;
    private String almacen;
    private String tipoBoleta;

    public String getTipoBoleta() {
        return tipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        this.tipoBoleta = tipoBoleta;
    }
    
    
    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
    
    

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }
    
    

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(String cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getTotalAntes() {
        return TotalAntes;
    }

    public void setTotalAntes(String TotalAntes) {
        this.TotalAntes = TotalAntes;
    }

    public String getDispoAntes() {
        return dispoAntes;
    }

    public void setDispoAntes(String dispoAntes) {
        this.dispoAntes = dispoAntes;
    }

    public String getTotalDesp() {
        return TotalDesp;
    }

    public void setTotalDesp(String TotalDesp) {
        this.TotalDesp = TotalDesp;
    }

    public String getDispoDesp() {
        return dispoDesp;
    }

    public void setDispoDesp(String dispoDesp) {
        this.dispoDesp = dispoDesp;
    }
    
    
    
    

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    
    
    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }
    
    
    
}
