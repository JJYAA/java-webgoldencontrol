/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.entity;

/**
 *
 * @author Computer
 */
public class BeanPreProvision {
    private String planilla;
    private String anulado;
    private String tipoCambio;
    private String rucProveedor;
    private String gravado;
    private String retencion;
    private String mes;
    private String anho;
    private String id;
    private String notaProveedor;
    private String operacion;
    private String fechaDocumento;
    private String fechaContable;
    private String serieDoc;
    private String numeroDoc;
    private String tipoDocumento;
    private String glosa;
    private String moneda;
    private String monedaStr;
    private String tipoDocumenoStr;
    private double baseImponible;
    private String flagMueOcuForm;
    private String fechaRegistro;
    private String ruc;
    private String rs;
    private String visualizapdf;
    private String visualizaxml;
    private String visualizacdr;
    
    private byte[] theFilePDF;
    private String nombreFilePDF;
    private String extensionPDf;
    
    private byte[] theFileXML;
    private String nombreFileXML;
    private String extensionXML;
    
    
    private byte[] theFileCDR;
    private String nombreFileCDR;
    private String extensionCDR;
    
    private String hbl;
    private String dua;
    private String asiento;
    private String cuentaGasto;
    
    private double porIgv;
    private double porRetencion;
    private int registro;
    private String tipoComprobante;
    private String chkmail;
    private Double debe;
    private Double haber;
    private Double saldo;
    private String fechaProPago;
    private String fechaProPago_glosa;
    private String cuentaBancaria;
    private String cuentaProv;
    
    
    private String cuentaEmpresa;
    private String cuentaChkSumEmpresa;
    private String tipoCuentaEmpresa;

    private String cuentaProveedor;
    private String cuentaChkSumProveedor;
    private String tipoCuentaProveedor;   
    private String tipoDocumentoPagar;
    private String poliza;
    private String tipogasto;

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    
    public String getPlanilla() {
        return planilla;
    }

    public void setPlanilla(String planilla) {
        this.planilla = planilla;
    }
    
    

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
    
    

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public String getTipogasto() {
        return tipogasto;
    }

    public void setTipogasto(String tipogasto) {
        this.tipogasto = tipogasto;
    }
    
    
    public String getRucProveedor() {
        return rucProveedor;
    }

    public void setRucProveedor(String rucProveedor) {
        this.rucProveedor = rucProveedor;
    }
    
    

    public String getTipoDocumentoPagar() {
        return tipoDocumentoPagar;
    }

    public void setTipoDocumentoPagar(String tipoDocumentoPagar) {
        this.tipoDocumentoPagar = tipoDocumentoPagar;
    }
    
    
    

    public String getCuentaEmpresa() {
        return cuentaEmpresa;
    }

    public void setCuentaEmpresa(String cuentaEmpresa) {
        this.cuentaEmpresa = cuentaEmpresa;
    }

    public String getCuentaChkSumEmpresa() {
        return cuentaChkSumEmpresa;
    }

    public void setCuentaChkSumEmpresa(String cuentaChkSumEmpresa) {
        this.cuentaChkSumEmpresa = cuentaChkSumEmpresa;
    }

    public String getTipoCuentaEmpresa() {
        return tipoCuentaEmpresa;
    }

    public void setTipoCuentaEmpresa(String tipoCuentaEmpresa) {
        this.tipoCuentaEmpresa = tipoCuentaEmpresa;
    }

    public String getCuentaProveedor() {
        return cuentaProveedor;
    }

    public void setCuentaProveedor(String cuentaProveedor) {
        this.cuentaProveedor = cuentaProveedor;
    }

    public String getCuentaChkSumProveedor() {
        return cuentaChkSumProveedor;
    }

    public void setCuentaChkSumProveedor(String cuentaChkSumProveedor) {
        this.cuentaChkSumProveedor = cuentaChkSumProveedor;
    }

    public String getTipoCuentaProveedor() {
        return tipoCuentaProveedor;
    }

    public void setTipoCuentaProveedor(String tipoCuentaProveedor) {
        this.tipoCuentaProveedor = tipoCuentaProveedor;
    }
    
    
    

    public String getCuentaProv() {
        return cuentaProv;
    }

    public void setCuentaProv(String cuentaProv) {
        this.cuentaProv = cuentaProv;
    }
    
    
    
    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }
    
    

    public String getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(String cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }
    
    

    public String getFechaProPago() {
        return fechaProPago;
    }

    public void setFechaProPago(String fechaProPago) {
        this.fechaProPago = fechaProPago;
    }

    public String getFechaProPago_glosa() {
        return fechaProPago_glosa;
    }

    public void setFechaProPago_glosa(String fechaProPago_glosa) {
        this.fechaProPago_glosa = fechaProPago_glosa;
    }
    
    

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }
    
    
    public Double getDebe() {
        return debe;
    }

    public void setDebe(Double debe) {
        this.debe = debe;
    }

    public Double getHaber() {
        return haber;
    }

    public void setHaber(Double haber) {
        this.haber = haber;
    }
    
    
    public String getChkmail() {
        return chkmail;
    }

    public void setChkmail(String chkmail) {
        this.chkmail = chkmail;
    }


    
    

    public String getTipoComprobante() {
        return tipoComprobante;
    }

    public void setTipoComprobante(String tipoComprobante) {
        this.tipoComprobante = tipoComprobante;
    }
    
    
    

    public int getRegistro() {
        return registro;
    }

    public void setRegistro(int registro) {
        this.registro = registro;
    }
    
    

    public String getNotaProveedor() {
        return notaProveedor;
    }

    public void setNotaProveedor(String notaProveedor) {
        this.notaProveedor = notaProveedor;
    }

    
    public double getPorIgv() {
        return porIgv;
    }

    public void setPorIgv(double porIgv) {
        this.porIgv = porIgv;
    }

    public double getPorRetencion() {
        return porRetencion;
    }

    public void setPorRetencion(double porRetencion) {
        this.porRetencion = porRetencion;
    }

    
    
    

    public String getGravado() {
        return gravado;
    }

    public void setGravado(String gravado) {
        this.gravado = gravado;
    }

    public String getRetencion() {
        return retencion;
    }

    public void setRetencion(String retencion) {
        this.retencion = retencion;
    }
    
    

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    
    public String getCuentaGasto() {
        return cuentaGasto;
    }

    public void setCuentaGasto(String cuentaGasto) {
        this.cuentaGasto = cuentaGasto;
    }
    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
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
    
    

    public String getFechaContable() {
        return fechaContable;
    }

    public void setFechaContable(String fechaContable) {
        this.fechaContable = fechaContable;
    }
    
    

    public byte[] getTheFilePDF() {
        return theFilePDF;
    }

    public void setTheFilePDF(byte[] theFilePDF) {
        this.theFilePDF = theFilePDF;
    }

    public String getNombreFilePDF() {
        return nombreFilePDF;
    }

    public void setNombreFilePDF(String nombreFilePDF) {
        this.nombreFilePDF = nombreFilePDF;
    }

    public String getExtensionPDf() {
        return extensionPDf;
    }

    public void setExtensionPDf(String extensionPDf) {
        this.extensionPDf = extensionPDf;
    }

    public byte[] getTheFileXML() {
        return theFileXML;
    }

    public void setTheFileXML(byte[] theFileXML) {
        this.theFileXML = theFileXML;
    }

    public String getNombreFileXML() {
        return nombreFileXML;
    }

    public void setNombreFileXML(String nombreFileXML) {
        this.nombreFileXML = nombreFileXML;
    }

    public String getExtensionXML() {
        return extensionXML;
    }

    public void setExtensionXML(String extensionXML) {
        this.extensionXML = extensionXML;
    }

    public byte[] getTheFileCDR() {
        return theFileCDR;
    }

    public void setTheFileCDR(byte[] theFileCDR) {
        this.theFileCDR = theFileCDR;
    }

    public String getNombreFileCDR() {
        return nombreFileCDR;
    }

    public void setNombreFileCDR(String nombreFileCDR) {
        this.nombreFileCDR = nombreFileCDR;
    }

    public String getExtensionCDR() {
        return extensionCDR;
    }

    public void setExtensionCDR(String extensionCDR) {
        this.extensionCDR = extensionCDR;
    }
    
  

    public String getVisualizapdf() {
        return visualizapdf;
    }

    public void setVisualizapdf(String visualizapdf) {
        this.visualizapdf = visualizapdf;
    }

    public String getVisualizaxml() {
        return visualizaxml;
    }

    public void setVisualizaxml(String visualizaxml) {
        this.visualizaxml = visualizaxml;
    }

    public String getVisualizacdr() {
        return visualizacdr;
    }

    public void setVisualizacdr(String visualizacdr) {
        this.visualizacdr = visualizacdr;
    }
    
    
    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getRs() {
        return rs;
    }

    public void setRs(String rs) {
        this.rs = rs;
    }
    
    

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
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

    public double getBaseImponible() {
        return baseImponible;
    }

    public void setBaseImponible(double baseImponible) {
        this.baseImponible = baseImponible;
    }

    public String getFlagMueOcuForm() {
        return flagMueOcuForm;
    }

    public void setFlagMueOcuForm(String flagMueOcuForm) {
        this.flagMueOcuForm = flagMueOcuForm;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
    
}
