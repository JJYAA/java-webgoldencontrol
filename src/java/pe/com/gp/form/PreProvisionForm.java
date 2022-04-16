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
 * @author Computer
 */
public class PreProvisionForm extends ActionForm implements Serializable {
    private String rucEmpresa;
    private String codTienda;
    private String selected;
    private String periodo;
    private String flagMueOcuForm;
    private String rucProveedor;
    private String fechaContable;
    private String fechaPago;
    private String operacion;
    private String fechaDocumento;
    private String serieDoc;
    private String numeroDoc;
    private String tipoDocumento;
    private String glosa;
    private String moneda;
    private String monedaStr;
    private String tipoDocumenoStr;
    private String baseImponible;
    private String fechaFin;
    private String fechaIni;
    private FormFile theFilePDF;
    private FormFile theFileXLS;
    private FormFile theFileCDR;
    private String secuencia;
    private String nota;
    private FormFile visualizapdf;
    private FormFile visualizaxml;
    private FormFile visualizacdr;
    private String formaPago;
    private String formaPagotxt;
    private String vpdf;
    private String vxml;
    private String vcdr;      
    private String hbl; 
    private String dua; 
    private String poliza;
    private String cuentaGasto;
    private String porIgv;
    private String porRetencion;
    private String tipoGasto;
    private Long asiento;

    public Long getAsiento() {
        return asiento;
    }

    public void setAsiento(Long asiento) {
        this.asiento = asiento;
    }


    
    

    public String getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(String fechaPago) {
        this.fechaPago = fechaPago;
    }

    
    public String getCodTienda() {
        return codTienda;
    }

    public void setCodTienda(String codTienda) {
        this.codTienda = codTienda;
    }

    
    public String getTipoGasto() {
        return tipoGasto;
    }

    public void setTipoGasto(String tipoGasto) {
        this.tipoGasto = tipoGasto;
    }
    
    

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
    

    public String getSelected() {
        return selected;
    }

    public void setSelected(String selected) {
        this.selected = selected;
    }

    public String getFormaPagotxt() {
        return formaPagotxt;
    }

    public void setFormaPagotxt(String formaPagotxt) {
        this.formaPagotxt = formaPagotxt;
    }

    
    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }
    
    

    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }

    
    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
        this.nota = nota;
    }

    
    public String getPorIgv() {
        return porIgv;
    }

    public void setPorIgv(String porIgv) {
        this.porIgv = porIgv;
    }

    public String getPorRetencion() {
        return porRetencion;
    }

    public void setPorRetencion(String porRetencion) {
        this.porRetencion = porRetencion;
    }
    
    

    public String getCuentaGasto() {
        return cuentaGasto;
    }

    public void setCuentaGasto(String cuentaGasto) {
        this.cuentaGasto = cuentaGasto;
    }
    
    
    
    public String getRucEmpresa() {
        return rucEmpresa;
    }

    public void setRucEmpresa(String rucEmpresa) {
        this.rucEmpresa = rucEmpresa;
    }

    
    public String getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(String secuencia) {
        this.secuencia = secuencia;
    }

    
    
    public String getHbl() {
        return hbl;
    }

    public void setHbl(String hbl) {
        this.hbl = hbl;
    }

    public String getDua() {
        return dua;
    }

    public void setDua(String dua) {
        this.dua = dua;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }
    
    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }

    
    public FormFile getVisualizapdf() {
        return visualizapdf;
    }

    public void setVisualizapdf(FormFile visualizapdf) {
        this.visualizapdf = visualizapdf;
    }

    public FormFile getVisualizaxml() {
        return visualizaxml;
    }

    public void setVisualizaxml(FormFile visualizaxml) {
        this.visualizaxml = visualizaxml;
    }

    public FormFile getVisualizacdr() {
        return visualizacdr;
    }

    public void setVisualizacdr(FormFile visualizacdr) {
        this.visualizacdr = visualizacdr;
    }

    public String getVpdf() {
        return vpdf;
    }

    public void setVpdf(String vpdf) {
        this.vpdf = vpdf;
    }

    public String getVxml() {
        return vxml;
    }

    public void setVxml(String vxml) {
        this.vxml = vxml;
    }

    public String getVcdr() {
        return vcdr;
    }

    public void setVcdr(String vcdr) {
        this.vcdr = vcdr;
    }
    

    
    public FormFile getTheFilePDF() {
        return theFilePDF;
    }

    public void setTheFilePDF(FormFile theFilePDF) {
        this.theFilePDF = theFilePDF;
    }

    public FormFile getTheFileXLS() {
        return theFileXLS;
    }

    public void setTheFileXLS(FormFile theFileXLS) {
        this.theFileXLS = theFileXLS;
    }

    public FormFile getTheFileCDR() {
        return theFileCDR;
    }

    public void setTheFileCDR(FormFile theFileCDR) {
        this.theFileCDR = theFileCDR;
    }
    
    
    public String getMonedaStr() {
        return monedaStr;
    }

    public void setMonedaStr(String monedaStr) {
        this.monedaStr = monedaStr;
    }

    public String getTipoDocumenoStr() {
        return tipoDocumenoStr;
    }

    public void setTipoDocumenoStr(String tipoDocumenoStr) {
        this.tipoDocumenoStr = tipoDocumenoStr;
    }

    public String getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(String fechaFin) {
        this.fechaFin = fechaFin;
    }

    public String getFechaIni() {
        return fechaIni;
    }

    public void setFechaIni(String fechaIni) {
        this.fechaIni = fechaIni;
    }

    
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    

    public String getFlagMueOcuForm() {
        return flagMueOcuForm;
    }

    public void setFlagMueOcuForm(String flagMueOcuForm) {
        this.flagMueOcuForm = flagMueOcuForm;
    }
    
   

    public String getFechaDocumento() {
        return fechaDocumento;
    }

    public void setFechaDocumento(String fechaDocumento) {
        this.fechaDocumento = fechaDocumento;
    }

    public String getSerieDoc() {
        return serieDoc;
    }

    public void setSerieDoc(String serieDoc) {
        this.serieDoc = serieDoc;
    }

    public String getNumeroDoc() {
        return numeroDoc;
    }

    public void setNumeroDoc(String numeroDoc) {
        this.numeroDoc = numeroDoc;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(String baseImponible) {
        this.baseImponible = baseImponible;
    }
    
    
        
}
