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
 * @author Computer
 */
public class ArchivoBancoForm extends ActionForm implements Serializable {
    private String flagMueOcuForm;
    private String operacion;
    private String rucProveedor;
    private String formaPago;
    private String selected;
    private String fechaIni;
    private String fechaFin;
    private String tipoBancoMoneda;
    private String bancoMoneda;
    private String buscarPor;
    private String planilla;

    public String getPlanilla() {
        return planilla;
    }

    public void setPlanilla(String planilla) {
        this.planilla = planilla;
    }
    
    

    public String getBuscarPor() {
        return buscarPor;
    }

    public void setBuscarPor(String buscarPor) {
        this.buscarPor = buscarPor;
    }
    
    

    public String getBancoMoneda() {
        return bancoMoneda;
    }

    public void setBancoMoneda(String bancoMoneda) {
        this.bancoMoneda = bancoMoneda;
    }
    
    

    public String getTipoBancoMoneda() {
        return tipoBancoMoneda;
    }

    public void setTipoBancoMoneda(String tipoBancoMoneda) {
        this.tipoBancoMoneda = tipoBancoMoneda;
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
    
    

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }
    

    public String getFlagMueOcuForm() {
        return flagMueOcuForm;
    }

    public void setFlagMueOcuForm(String flagMueOcuForm) {
        this.flagMueOcuForm = flagMueOcuForm;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    
            
}
