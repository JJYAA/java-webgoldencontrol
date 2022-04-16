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
public class PedidoOfertaRepForm extends ActionForm implements Serializable {

    static final long serialVersionUID = 1L;
    private String fechaDesde;
    private String operacion;
    private String clienteTitulo;
    private String clienteTipoDocumento;
    private String clienteNumeroDocumento;
    private String clienteFlagTrataDatos;
    private String clienteNacionalidad;
    private String clienteRazonSocial;
    private String clientePrimerNombre;
    private String clienteSegundoNombre;
    private String clienteApellidoPaterno;
    private String clienteApellidoMaterno;
    private String clienteCorreo;
    private String clienteCargo;
    private String clienteCargoDes;
    private String clienteDireccion;
    private String clienteDepartamento;
    private String clienteProvincia;
    private String clienteDistrito;
    private String clienteTelefono1;
    private String clienteCelular;
    private String clienteFax;
    private String clienteFechaNacimiento;
    private String clienteEstadoCivil;
    private String clienteSexo;
    private String clienteAnexoTelefono1;
    private String clienteTelefono2;
    private String clienteTipoContacto;
    private String clienteLineaCredito;
    private String proceso;
    private String flagBtnConfirmar;
    private String moneda;
    private String formaPago;
    private String modoCompra;
    private String impSegOrdIng;
    private String numeroParte;
    private String pedPendientes;
    private String repSeparados;
    private String descripcion;
    private String stockDis;
    private String stockTotal;
    private String almacenSec;
    private String cantidad;
    private String descuento;
    private String totalBruto;
    private String totalDescuento;
    private String totalNeto;
    private String totalIgv;
    private String totalGeneral;
    private String quitarIgvDoc;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String canalVenta;
    private String recojoNumDoc;
    private String recojoNombre;
    private String vvp;
    private String cajas;
    private FormFile theFile;
    private String numeroparteAux;
    private String auxFacTipDoc;
    private String auxCliTipPer;
    private String auxCliTipDoc;
    private String auxCliFueBus;
    private String auxCliCOCR;
    private String flagMueOcuForm;
    private String documentoSAP;
    private String auxproceso;
    private String auxindicadorIGV;
    private String docEntryAux;
    private String existeRep;
    private String numItemAux;
    private String auxOfertaVenta;
    private String codEmpleadoVentas;
    private String motivoAnulacion;
    private String familia;
    private String clase;
    private String grupo;
    private String numeroPropuesta;
    private String itemCodigo;
    private String unidades;
    private String codigoproveedor;

    public String getCodigoproveedor() {
        return codigoproveedor;
    }

    public void setCodigoproveedor(String codigoproveedor) {
        this.codigoproveedor = codigoproveedor;
    }
    
    
    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    
    public String getItemCodigo() {
        return itemCodigo;
    }

    public void setItemCodigo(String itemCodigo) {
        this.itemCodigo = itemCodigo;
    }
    

    public String getNumeroPropuesta() {
        return numeroPropuesta;
    }

    public void setNumeroPropuesta(String numeroPropuesta) {
        this.numeroPropuesta = numeroPropuesta;
    }


    
    
            

    public String getFechaDesde() {
        return fechaDesde;
    }

    public void setFechaDesde(String fechaDesde) {
        this.fechaDesde = fechaDesde;
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
    
    

    public FormFile getTheFile() {
        return theFile;
    }

    public void setTheFile(FormFile theFile) {
        this.theFile = theFile;
    }
    
    

    public String getNumeroparteAux() {
        return numeroparteAux;
    }

    public void setNumeroparteAux(String numeroparteAux) {
        this.numeroparteAux = numeroparteAux;
    }

    
    public String getCajas() {
        return cajas;
    }

    public void setCajas(String cajas) {
        this.cajas = cajas;
    }

    
    public String getMotivoAnulacion() {
        return motivoAnulacion;
    }

    public String getVvp() {
        return vvp;
    }

    public void setVvp(String vvp) {
        this.vvp = vvp;
    }

    public void setMotivoAnulacion(String motivoAnulacion) {
        this.motivoAnulacion = motivoAnulacion;
    }
     
     
    public String getCodEmpleadoVentas() {
        return codEmpleadoVentas;
    }

    public void setCodEmpleadoVentas(String codEmpleadoVentas) {
        this.codEmpleadoVentas = codEmpleadoVentas;
    }
    
    
    public String getAuxOfertaVenta() {
        return auxOfertaVenta;
    }

    public void setAuxOfertaVenta(String auxOfertaVenta) {
        this.auxOfertaVenta = auxOfertaVenta;
    }
    
    

    public String getNumItemAux() {
        return numItemAux;
    }

    public void setNumItemAux(String numItemAux) {
        this.numItemAux = numItemAux;
    }
    
    

    public String getExisteRep() {
        return existeRep;
    }

    public void setExisteRep(String existeRep) {
        this.existeRep = existeRep;
    }
    
    

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

    
    public String getDocEntryAux() {
        return docEntryAux;
    }

    public void setDocEntryAux(String docEntryAux) {
        this.docEntryAux = docEntryAux;
    }

    public String getAuxindicadorIGV() {
        return auxindicadorIGV;
    }

    public void setAuxindicadorIGV(String auxindicadorIGV) {
        this.auxindicadorIGV = auxindicadorIGV;
    }

    public String getAuxproceso() {
        return auxproceso;
    }

    public void setAuxproceso(String auxproceso) {
        this.auxproceso = auxproceso;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getDocumentoSAP() {
        return documentoSAP;
    }

    public void setDocumentoSAP(String documentoSAP) {
        this.documentoSAP = documentoSAP;
    }


    public String getRecojoNumDoc() {
        return recojoNumDoc;
    }

    public void setRecojoNumDoc(String recojoNumDoc) {
        this.recojoNumDoc = recojoNumDoc;
    }

    public String getRecojoNombre() {
        return recojoNombre;
    }

    public void setRecojoNombre(String recojoNombre) {
        this.recojoNombre = recojoNombre;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public String getFlagMueOcuForm() {
        return flagMueOcuForm;
    }

    public void setFlagMueOcuForm(String flagMueOcuForm) {
        this.flagMueOcuForm = flagMueOcuForm;
    }

    public String getAuxCliCOCR() {
        return auxCliCOCR;
    }

    public void setAuxCliCOCR(String auxCliCOCR) {
        this.auxCliCOCR = auxCliCOCR;
    }

    public String getAuxCliFueBus() {
        return auxCliFueBus;
    }

    public void setAuxCliFueBus(String auxCliFueBus) {
        this.auxCliFueBus = auxCliFueBus;
    }

    public String getAuxCliTipDoc() {
        return auxCliTipDoc;
    }

    public void setAuxCliTipDoc(String auxCliTipDoc) {
        this.auxCliTipDoc = auxCliTipDoc;
    }

    public String getAuxCliTipPer() {
        return auxCliTipPer;
    }

    public void setAuxCliTipPer(String auxCliTipPer) {
        this.auxCliTipPer = auxCliTipPer;
    }

    public String getAuxFacTipDoc() {
        return auxFacTipDoc;
    }

    public void setAuxFacTipDoc(String auxFacTipDoc) {
        this.auxFacTipDoc = auxFacTipDoc;
    }

    public String getClienteLineaCredito() {
        return clienteLineaCredito;
    }

    public void setClienteLineaCredito(String clienteLineaCredito) {
        this.clienteLineaCredito = clienteLineaCredito;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getModoCompra() {
        return modoCompra;
    }

    public void setModoCompra(String modoCompra) {
        this.modoCompra = modoCompra;
    }

    public String getNumeroParte() {
        return numeroParte;
    }

    public void setNumeroParte(String numeroParte) {
        this.numeroParte = numeroParte;
    }

    public String getPedPendientes() {
        return pedPendientes;
    }

    public void setPedPendientes(String pedPendientes) {
        this.pedPendientes = pedPendientes;
    }

    public String getRepSeparados() {
        return repSeparados;
    }

    public void setRepSeparados(String repSeparados) {
        this.repSeparados = repSeparados;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getStockDis() {
        return stockDis;
    }

    public void setStockDis(String stockDis) {
        this.stockDis = stockDis;
    }

    public String getAlmacenSec() {
        return almacenSec;
    }

    public void setAlmacenSec(String almacenSec) {
        this.almacenSec = almacenSec;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescuento() {
        return descuento;
    }

    public void setDescuento(String descuento) {
        this.descuento = descuento;
    }

    public String getTotalBruto() {
        return totalBruto;
    }

    public void setTotalBruto(String totalBruto) {
        this.totalBruto = totalBruto;
    }

    public String getTotalDescuento() {
        return totalDescuento;
    }

    public void setTotalDescuento(String totalDescuento) {
        this.totalDescuento = totalDescuento;
    }

    public String getTotalNeto() {
        return totalNeto;
    }

    public void setTotalNeto(String totalNeto) {
        this.totalNeto = totalNeto;
    }

    public String getTotalIgv() {
        return totalIgv;
    }

    public void setTotalIgv(String totalIgv) {
        this.totalIgv = totalIgv;
    }

    public String getTotalGeneral() {
        return totalGeneral;
    }

    public void setTotalGeneral(String totalGeneral) {
        this.totalGeneral = totalGeneral;
    }

    public String getImpSegOrdIng() {
        return impSegOrdIng;
    }

    public void setImpSegOrdIng(String impSegOrdIng) {
        this.impSegOrdIng = impSegOrdIng;
    }

    public String getQuitarIgvDoc() {
        return quitarIgvDoc;
    }

    public void setQuitarIgvDoc(String quitarIgvDoc) {
        this.quitarIgvDoc = quitarIgvDoc;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(String nota4) {
        this.nota4 = nota4;
    }

    public String getClienteTipoContacto() {
        return clienteTipoContacto;
    }

    public void setClienteTipoContacto(String clienteTipoContacto) {
        this.clienteTipoContacto = clienteTipoContacto;
    }

    public String getClienteFlagTrataDatos() {
        return clienteFlagTrataDatos;
    }

    public void setClienteFlagTrataDatos(String clienteFlagTrataDatos) {
        this.clienteFlagTrataDatos = clienteFlagTrataDatos;
    }

    public String getFlagBtnConfirmar() {
        return flagBtnConfirmar;
    }

    public void setFlagBtnConfirmar(String flagBtnConfirmar) {
        this.flagBtnConfirmar = flagBtnConfirmar;
    }

    public String getClienteTitulo() {
        return clienteTitulo;
    }

    public void setClienteTitulo(String clienteTitulo) {
        this.clienteTitulo = clienteTitulo;
    }

    public String getClienteTipoDocumento() {
        return clienteTipoDocumento;
    }

    public void setClienteTipoDocumento(String clienteTipoDocumento) {
        this.clienteTipoDocumento = clienteTipoDocumento;
    }

    public String getClienteNumeroDocumento() {
        return clienteNumeroDocumento;
    }

    public void setClienteNumeroDocumento(String clienteNumeroDocumento) {
        this.clienteNumeroDocumento = clienteNumeroDocumento;
    }

    public String getClienteNacionalidad() {
        return clienteNacionalidad;
    }

    public void setClienteNacionalidad(String clienteNacionalidad) {
        this.clienteNacionalidad = clienteNacionalidad;
    }

    public String getClienteRazonSocial() {
        return clienteRazonSocial;
    }

    public void setClienteRazonSocial(String clienteRazonSocial) {
        this.clienteRazonSocial = clienteRazonSocial;
    }

    public String getClientePrimerNombre() {
        return clientePrimerNombre;
    }

    public void setClientePrimerNombre(String clientePrimerNombre) {
        this.clientePrimerNombre = clientePrimerNombre;
    }

    public String getClienteSegundoNombre() {
        return clienteSegundoNombre;
    }

    public void setClienteSegundoNombre(String clienteSegundoNombre) {
        this.clienteSegundoNombre = clienteSegundoNombre;
    }

    public String getClienteApellidoPaterno() {
        return clienteApellidoPaterno;
    }

    public void setClienteApellidoPaterno(String clienteApellidoPaterno) {
        this.clienteApellidoPaterno = clienteApellidoPaterno;
    }

    public String getClienteApellidoMaterno() {
        return clienteApellidoMaterno;
    }

    public void setClienteApellidoMaterno(String clienteApellidoMaterno) {
        this.clienteApellidoMaterno = clienteApellidoMaterno;
    }

    public String getClienteCorreo() {
        return clienteCorreo;
    }

    public void setClienteCorreo(String clienteCorreo) {
        this.clienteCorreo = clienteCorreo;
    }

    public String getClienteCargo() {
        return clienteCargo;
    }

    public void setClienteCargo(String clienteCargo) {
        this.clienteCargo = clienteCargo;
    }

    public String getClienteCargoDes() {
        return clienteCargoDes;
    }

    public void setClienteCargoDes(String clienteCargoDes) {
        this.clienteCargoDes = clienteCargoDes;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public String getClienteDepartamento() {
        return clienteDepartamento;
    }

    public void setClienteDepartamento(String clienteDepartamento) {
        this.clienteDepartamento = clienteDepartamento;
    }

    public String getClienteProvincia() {
        return clienteProvincia;
    }

    public void setClienteProvincia(String clienteProvincia) {
        this.clienteProvincia = clienteProvincia;
    }

    public String getClienteDistrito() {
        return clienteDistrito;
    }

    public void setClienteDistrito(String clienteDistrito) {
        this.clienteDistrito = clienteDistrito;
    }

    public String getClienteTelefono1() {
        return clienteTelefono1;
    }

    public void setClienteTelefono1(String clienteTelefono1) {
        this.clienteTelefono1 = clienteTelefono1;
    }

    public String getClienteCelular() {
        return clienteCelular;
    }

    public void setClienteCelular(String clienteCelular) {
        this.clienteCelular = clienteCelular;
    }

    public String getClienteFax() {
        return clienteFax;
    }

    public void setClienteFax(String clienteFax) {
        this.clienteFax = clienteFax;
    }

    public String getClienteFechaNacimiento() {
        return clienteFechaNacimiento;
    }

    public void setClienteFechaNacimiento(String clienteFechaNacimiento) {
        this.clienteFechaNacimiento = clienteFechaNacimiento;
    }

    public String getClienteEstadoCivil() {
        return clienteEstadoCivil;
    }

    public void setClienteEstadoCivil(String clienteEstadoCivil) {
        this.clienteEstadoCivil = clienteEstadoCivil;
    }

    public String getClienteSexo() {
        return clienteSexo;
    }

    public void setClienteSexo(String clienteSexo) {
        this.clienteSexo = clienteSexo;
    }

    public String getClienteAnexoTelefono1() {
        return clienteAnexoTelefono1;
    }

    public void setClienteAnexoTelefono1(String clienteAnexoTelefono1) {
        this.clienteAnexoTelefono1 = clienteAnexoTelefono1;
    }

    public String getClienteTelefono2() {
        return clienteTelefono2;
    }

    public void setClienteTelefono2(String clienteTelefono2) {
        this.clienteTelefono2 = clienteTelefono2;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    
}
