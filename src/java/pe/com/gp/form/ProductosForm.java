/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.form;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

/**
 *
 * @author Jpalomino
 */
public class ProductosForm extends ActionForm implements Serializable {
     private String operacion;
    private String numeroParte;
    private String numeroparteAux;
    private String descripcion;
    private FormFile theFile;
    private Double vvpSoles ;
    private Double vvpDolar   ; 
    private String activo     ;
    private String masPrecios ;
    private Long cantidadCaja ;
    private String duas    ;
    private String familia;
    private String clase;
    private String grupo ;
    
    private String precios01;
    private String precios02;
    private String precios03;
    private String precios04;
    private String precios05;
    
    private String stockDisponible;
    private String stockTotal;
    private String stockSeguridad;   
    private String stockTemporal;
    private String stockAlmacen;
    private Double costoPromedio;
    private Double ultimoCosto;

    public String getStockAlmacen() {
        return stockAlmacen;
    }

    public void setStockAlmacen(String stockAlmacen) {
        this.stockAlmacen = stockAlmacen;
    }
    
    

    public String getStockTemporal() {
        return stockTemporal;
    }

    public void setStockTemporal(String stockTemporal) {
        this.stockTemporal = stockTemporal;
    }

    public Double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(Double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public Double getUltimoCosto() {
        return ultimoCosto;
    }

    public void setUltimoCosto(Double ultimoCosto) {
        this.ultimoCosto = ultimoCosto;
    }
    
    

    public String getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(String stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

    public String getStockSeguridad() {
        return stockSeguridad;
    }

    public void setStockSeguridad(String stockSeguridad) {
        this.stockSeguridad = stockSeguridad;
    }
    

    
    
    public String getPrecios01() {
        return precios01;
    }

    public void setPrecios01(String precios01) {
        this.precios01 = precios01;
    }

    public String getPrecios02() {
        return precios02;
    }

    public void setPrecios02(String precios02) {
        this.precios02 = precios02;
    }

    public String getPrecios03() {
        return precios03;
    }

    public void setPrecios03(String precios03) {
        this.precios03 = precios03;
    }

    public String getPrecios04() {
        return precios04;
    }

    public void setPrecios04(String precios04) {
        this.precios04 = precios04;
    }

    public String getPrecios05() {
        return precios05;
    }

    public void setPrecios05(String precios05) {
        this.precios05 = precios05;
    }
    
    
    


    
    public String getNumeroparteAux() {
        return numeroparteAux;
    }

    public void setNumeroparteAux(String numeroparteAux) {
        this.numeroparteAux = numeroparteAux;
    }

    

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public FormFile getTheFile() {
        return theFile;
    }

    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }

    public Double getVvpSoles() {
        return vvpSoles;
    }

    public void setVvpSoles(Double vvpSoles) {
        this.vvpSoles = vvpSoles;
    }

    public Double getVvpDolar() {
        return vvpDolar;
    }

    public void setVvpDolar(Double vvpDolar) {
        this.vvpDolar = vvpDolar;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }



    public String getMasPrecios() {
        return masPrecios;
    }

    public void setMasPrecios(String masPrecios) {
        this.masPrecios = masPrecios;
    }

    public Long getCantidadCaja() {
        return cantidadCaja;
    }

    public void setCantidadCaja(Long cantidadCaja) {
        this.cantidadCaja = cantidadCaja;
    }

    public String getDuas() {
        return duas;
    }

    public void setDuas(String duas) {
        this.duas = duas;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }
    
    
            
            
}
