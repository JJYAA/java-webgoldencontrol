package pe.com.gp.entity;

import java.io.Serializable;

public class Cotizacion implements Serializable {

    private String codigoTienda;
    private long numeroCotizacion;
    private String atencion;
    private String referencia;
    private String fecha;
    private String tipoOperacion;
    private String clienteRazonSocial;
    private String clientePrimerNombre;
    private String clienteSegungoNombre;
    private String clienteApePaterno;
    private String clienteApeMaterno;
    private String clienteCodigo;
    private String clienteNroDocumento;
    private String clienteDireccion;
    private String clienteDistrito;
    private String clienteEmail;
    private String clienteTipoPersona; // N, J
    private String clienteClasePersona; // T01,T02 ... (Titulo)
    private String clienteClaseDocumento; // 01,02 ...
    private String vehiculoCodModelo;
    private String vehiculoMarca;
    private String vehiculoDesModelo;
    private String vehiculoAnio;
    private String vehiculoColor;
    private String moneda;
    private double totalOperacion;
    private double totalOperacionSol;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String codigoAsesor;
    private String nombreAsesor;
    private String mailAsesor;
    private String codigoSupervisor;
    private String fechaSupervisorVB;
    private String validezOferta;
    private String modalidadVta;
    private String modalidadVtaDes;
    private String formaPago;
    private String formaPagoDes;
    private String modalidadFinanc;
    private String modalidadFinancDes;
    private long numeroFicha;
    private String campanaDescripcion;
    private String campanaCodigo;
    private boolean existe;
    private String tipoDocumento;
    private long numeroDocumento;

    // Reportes
    private double precio;
    private double tramites;
    private double accesorios;
    private double accesoriosSol;
    private double otros;
    private double seguro;
    private double notaCredito;
    private double fob;
    private double flete;
    private double cyf;
    private double pagosLocales;
    private double igvPagosLocales;
    private double descuentoVehiculo;
    private double descuentoVehiculoSol;
    private String placaVehiculoUsado;
    private double precioListaSol;
    private double precioListaDol;
    private double seguroMaritimoEndoso;
    private double gasPreEndoso;
    private double gasDesEndoso;
    private double gasAlmEndoso;
    private double gasBanEndoso;
    private double comTDPEndoso;
    private double comIntEndoso;
    private double tipoCambioCotizacion;
    private double gastosOtrosEndoso;
    private double partePagoEndoso;

    public Cotizacion() {
    }

    public double getPartePagoEndoso() {
        return partePagoEndoso;
    }

    public void setPartePagoEndoso(double partePagoEndoso) {
        this.partePagoEndoso = partePagoEndoso;
    }

    public double getGastosOtrosEndoso() {
        return gastosOtrosEndoso;
    }

    public void setGastosOtrosEndoso(double gastosOtrosEndoso) {
        this.gastosOtrosEndoso = gastosOtrosEndoso;
    }

    public double getTipoCambioCotizacion() {
        return tipoCambioCotizacion;
    }

    public void setTipoCambioCotizacion(double tipoCambioCotizacion) {
        this.tipoCambioCotizacion = tipoCambioCotizacion;
    }

    public double getSeguroMaritimoEndoso() {
        return seguroMaritimoEndoso;
    }

    public void setSeguroMaritimoEndoso(double seguroMaritimoEndoso) {
        this.seguroMaritimoEndoso = seguroMaritimoEndoso;
    }

    public double getGasPreEndoso() {
        return gasPreEndoso;
    }

    public void setGasPreEndoso(double gasPreEndoso) {
        this.gasPreEndoso = gasPreEndoso;
    }

    public double getGasDesEndoso() {
        return gasDesEndoso;
    }

    public void setGasDesEndoso(double gasDesEndoso) {
        this.gasDesEndoso = gasDesEndoso;
    }

    public double getGasAlmEndoso() {
        return gasAlmEndoso;
    }

    public void setGasAlmEndoso(double gasAlmEndoso) {
        this.gasAlmEndoso = gasAlmEndoso;
    }

    public double getGasBanEndoso() {
        return gasBanEndoso;
    }

    public void setGasBanEndoso(double gasBanEndoso) {
        this.gasBanEndoso = gasBanEndoso;
    }

    public double getComTDPEndoso() {
        return comTDPEndoso;
    }

    public void setComTDPEndoso(double comTDPEndoso) {
        this.comTDPEndoso = comTDPEndoso;
    }

    public double getComIntEndoso() {
        return comIntEndoso;
    }

    public void setComIntEndoso(double comIntEndoso) {
        this.comIntEndoso = comIntEndoso;
    }

    public double getPrecioListaSol() {
        return precioListaSol;
    }

    public void setPrecioListaSol(double precioListaSol) {
        this.precioListaSol = precioListaSol;
    }

    public double getPrecioListaDol() {
        return precioListaDol;
    }

    public void setPrecioListaDol(double precioListaDol) {
        this.precioListaDol = precioListaDol;
    }

    public double getDescuentoVehiculoSol() {
        return descuentoVehiculoSol;
    }

    public void setDescuentoVehiculoSol(double descuentoVehiculoSol) {
        this.descuentoVehiculoSol = descuentoVehiculoSol;
    }

    public double getAccesoriosSol() {
        return accesoriosSol;
    }

    public void setAccesoriosSol(double accesoriosSol) {
        this.accesoriosSol = accesoriosSol;
    }

    public String getPlacaVehiculoUsado() {
        return placaVehiculoUsado;
    }

    public void setPlacaVehiculoUsado(String placaVehiculoUsado) {
        this.placaVehiculoUsado = placaVehiculoUsado;
    }

    public double getDescuentoVehiculo() {
        return descuentoVehiculo;
    }

    public void setDescuentoVehiculo(double descuentoVehiculo) {
        this.descuentoVehiculo = descuentoVehiculo;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public String getMailAsesor() {
        return mailAsesor;
    }

    public void setMailAsesor(String mailAsesor) {
        this.mailAsesor = mailAsesor;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public double getFob() {
        return fob;
    }

    public void setFob(double fob) {
        this.fob = fob;
    }

    public double getFlete() {
        return flete;
    }

    public void setFlete(double flete) {
        this.flete = flete;
    }

    public double getCyf() {
        return cyf;
    }

    public void setCyf(double cyf) {
        this.cyf = cyf;
    }

    public double getPagosLocales() {
        return pagosLocales;
    }

    public void setPagosLocales(double pagosLocales) {
        this.pagosLocales = pagosLocales;
    }

    public double getIgvPagosLocales() {
        return igvPagosLocales;
    }

    public void setIgvPagosLocales(double igvPagosLocales) {
        this.igvPagosLocales = igvPagosLocales;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTramites() {
        return tramites;
    }

    public void setTramites(double tramites) {
        this.tramites = tramites;
    }

    public double getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(double accesorios) {
        this.accesorios = accesorios;
    }

    public double getOtros() {
        return otros;
    }

    public void setOtros(double otros) {
        this.otros = otros;
    }

    public double getSeguro() {
        return seguro;
    }

    public void setSeguro(double seguro) {
        this.seguro = seguro;
    }

    public double getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(double notaCredito) {
        this.notaCredito = notaCredito;
    }

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public double getTotalOperacionSol() {
        return totalOperacionSol;
    }

    public void setTotalOperacionSol(double totalOperacionSol) {
        this.totalOperacionSol = totalOperacionSol;
    }

    public String getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public long getNumeroCotizacion() {
        return numeroCotizacion;
    }

    public void setNumeroCotizacion(long numeroCotizacion) {
        this.numeroCotizacion = numeroCotizacion;
    }

    public String getAtencion() {
        return atencion;
    }

    public void setAtencion(String atencion) {
        this.atencion = atencion;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

    public String getClienteSegungoNombre() {
        return clienteSegungoNombre;
    }

    public void setClienteSegungoNombre(String clienteSegungoNombre) {
        this.clienteSegungoNombre = clienteSegungoNombre;
    }

    public String getClienteApePaterno() {
        return clienteApePaterno;
    }

    public void setClienteApePaterno(String clienteApePaterno) {
        this.clienteApePaterno = clienteApePaterno;
    }

    public String getClienteApeMaterno() {
        return clienteApeMaterno;
    }

    public void setClienteApeMaterno(String clienteApeMaterno) {
        this.clienteApeMaterno = clienteApeMaterno;
    }

    public String getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(String clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public String getClienteNroDocumento() {
        return clienteNroDocumento;
    }

    public void setClienteNroDocumento(String clienteNroDocumento) {
        this.clienteNroDocumento = clienteNroDocumento;
    }

    public String getClienteDireccion() {
        return clienteDireccion;
    }

    public void setClienteDireccion(String clienteDireccion) {
        this.clienteDireccion = clienteDireccion;
    }

    public String getClienteDistrito() {
        return clienteDistrito;
    }

    public void setClienteDistrito(String clienteDistrito) {
        this.clienteDistrito = clienteDistrito;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTipoPersona() {
        return clienteTipoPersona;
    }

    public void setClienteTipoPersona(String clienteTipoPersona) {
        this.clienteTipoPersona = clienteTipoPersona;
    }

    public String getClienteClasePersona() {
        return clienteClasePersona;
    }

    public void setClienteClasePersona(String clienteClasePersona) {
        this.clienteClasePersona = clienteClasePersona;
    }

    public String getClienteClaseDocumento() {
        return clienteClaseDocumento;
    }

    public void setClienteClaseDocumento(String clienteClaseDocumento) {
        this.clienteClaseDocumento = clienteClaseDocumento;
    }

    public String getVehiculoCodModelo() {
        return vehiculoCodModelo;
    }

    public void setVehiculoCodModelo(String vehiculoCodModelo) {
        this.vehiculoCodModelo = vehiculoCodModelo;
    }

    public String getVehiculoMarca() {
        return vehiculoMarca;
    }

    public void setVehiculoMarca(String vehiculoMarca) {
        this.vehiculoMarca = vehiculoMarca;
    }

    public String getVehiculoDesModelo() {
        return vehiculoDesModelo;
    }

    public void setVehiculoDesModelo(String vehiculoDesModelo) {
        this.vehiculoDesModelo = vehiculoDesModelo;
    }

    public String getVehiculoAnio() {
        return vehiculoAnio;
    }

    public void setVehiculoAnio(String vehiculoAnio) {
        this.vehiculoAnio = vehiculoAnio;
    }

    public String getVehiculoColor() {
        return vehiculoColor;
    }

    public void setVehiculoColor(String vehiculoColor) {
        this.vehiculoColor = vehiculoColor;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTotalOperacion() {
        return totalOperacion;
    }

    public void setTotalOperacion(double totalOperacion) {
        this.totalOperacion = totalOperacion;
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

    public String getCodigoAsesor() {
        return codigoAsesor;
    }

    public void setCodigoAsesor(String codigoAsesor) {
        this.codigoAsesor = codigoAsesor;
    }

    public String getCodigoSupervisor() {
        return codigoSupervisor;
    }

    public void setCodigoSupervisor(String codigoSupervisor) {
        this.codigoSupervisor = codigoSupervisor;
    }

    public String getFechaSupervisorVB() {
        return fechaSupervisorVB;
    }

    public void setFechaSupervisorVB(String fechaSupervisorVB) {
        this.fechaSupervisorVB = fechaSupervisorVB;
    }

    public String getValidezOferta() {
        return validezOferta;
    }

    public void setValidezOferta(String validezOferta) {
        this.validezOferta = validezOferta;
    }

    public String getModalidadVta() {
        return modalidadVta;
    }

    public void setModalidadVta(String modalidadVta) {
        this.modalidadVta = modalidadVta;
    }

    public String getModalidadVtaDes() {
        return modalidadVtaDes;
    }

    public void setModalidadVtaDes(String modalidadVtaDes) {
        this.modalidadVtaDes = modalidadVtaDes;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getFormaPagoDes() {
        return formaPagoDes;
    }

    public void setFormaPagoDes(String formaPagoDes) {
        this.formaPagoDes = formaPagoDes;
    }

    public String getModalidadFinanc() {
        return modalidadFinanc;
    }

    public void setModalidadFinanc(String modalidadFinanc) {
        this.modalidadFinanc = modalidadFinanc;
    }

    public String getModalidadFinancDes() {
        return modalidadFinancDes;
    }

    public void setModalidadFinancDes(String modalidadFinancDes) {
        this.modalidadFinancDes = modalidadFinancDes;
    }

    public long getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(long numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getCampanaDescripcion() {
        return campanaDescripcion;
    }

    public void setCampanaDescripcion(String campanaDescripcion) {
        this.campanaDescripcion = campanaDescripcion;
    }

    public String getCampanaCodigo() {
        return campanaCodigo;
    }

    public void setCampanaCodigo(String campanaCodigo) {
        this.campanaCodigo = campanaCodigo;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }
}
