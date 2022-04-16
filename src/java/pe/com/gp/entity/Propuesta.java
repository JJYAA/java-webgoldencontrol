package pe.com.gp.entity;

import java.io.Serializable;

public class Propuesta implements Serializable {

    private String codigoTienda;
    private long numeroPropuesta;
    private String atencion;
    private String referencia;
    private String fecha;
    private String tipoOperacion;
    private String arrendatarioRazonSocial;
    private String arrendatarioPrimerNombre;
    private String arrendatarioSegungoNombre;
    private String arrendatarioApePaterno;
    private String arrendatarioApeMaterno;
    private String arrendatarioCodigo;
    private String arrendatarioNombre;
    private String arrendatarioNroDocumento;
    private String arrendatarioDireccion;
    private String arrendatarioDistrito;
    private String arrendatarioEmail;
    private String arrendatarioTipoPersona; // N, J
    private String arrendatarioClasePersona; // T01,T02 ... (Titulo)
    private String arrendatarioClaseDocumento; // 01,02 ...
    private String arrendatarioUbigeo;
    private String arrendatarioTelefono;
    private String arrendatarioFax;
    private String arrendadorCodigo;
    private String arrendadorRazSoc;
    private String arrendadorTelefono;
    private String arrendadorNroDocumento;
    private String arrendatarioRucSN;
    private String modeloCodigo;
    private String modeloDes;
    private String marca;
    private String anio;
    private String color;
    private String moneda;
    private double totalOperacion;
    private double totalOperacionSol;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String nota5;
    private String codigoAsesor;
    private String codigoSupervisor;
    private String fechaSupervisorVB;
    private String validezOferta;
    private String modVenta;
    private String modVentaDes;
    private String formaPago;
    private String formaPagoDes;
    private String modFinanciamiento;
    private String modFinanciamientoDes;
    private long numeroFicha;
    private String campanaDes;
    private String campanaCodigo;
    private String placaPartePago;
    private String placa;
    private double notaCredito;
    private double notaCreditoSol;
    private double fob;
    private double flete;
    private String mancomunadoRazSoc;
    private String tipoPago;
    private double precioLista;
    private double dscto;
    private double accesorios;
    private double otros;
    private double seguro;
    private double precioListaSol;
    private double dsctoSol;
    private double tramites;
    private double tramitesSol;
    private double accesoriosSol;
    private double otrosSol;
    private double seguroSol;
    private double partePago;
    private double partePagoSol;
    private double rfvp;
    private double cuotaInicial;
    private double montoFinanciar;
    private double totalInteres;
    private int grupo1Cantidad;
    private int grupo2Cantidad;
    private int grupo3Cantidad;
    private int grupo4Cantidad;
    private int grupo5Cantidad;
    private double grupo1Monto;
    private double grupo2Monto;
    private double grupo3Monto;
    private double grupo4Monto;
    private double grupo5Monto;
    private double teaMes;
    private double seguroMaritimo;
    private double inspeccionPreEmb;
    private double descarga;
    private double comisionTDP;
    private double gastosFinancieros;
    private double almacenaje;
    private double totComisConces;
    private double gastosOperativos;
    private String supNombre;
    private String venNombre;
    private String anulado;
    private int numeroIngresos;
    private long numFile;
    private boolean existe;
    private String tipoDoc;

    //Codigo de mancomunado
    private String codigoMancomunado;
    private String tituloMancoumando;
    private String arrendadorTitulo;
    private String localPreferido;
    private String invUsa;

    private int nroSimulacionCredito;
    private double montoFinanciarCredito;
    private double nroLetraCredito;
    private double teaMesCredito;
    private double totalInteresCredito;
    private double grupo1CantCredito;
    private double grupo2CantCredito;
    private double grupo3CantCredito;
    private double grupo4CantCredito;
    private double grupo5CantCredito;

    private double grupo1MontCredito;
    private double grupo2MontCredito;
    private double grupo3MontCredito;
    private double grupo4MontCredito;
    private double grupo5MontCredito;

    private String codigoColorVeh;
    private double tipoCambioVtaPropuesta;

    public Propuesta() {
    }

    public double getTipoCambioVtaPropuesta() {
        return tipoCambioVtaPropuesta;
    }

    public void setTipoCambioVtaPropuesta(double tipoCambioVtaPropuesta) {
        this.tipoCambioVtaPropuesta = tipoCambioVtaPropuesta;
    }

    public String getCodigoColorVeh() {
        return codigoColorVeh;
    }

    public void setCodigoColorVeh(String codigoColorVeh) {
        this.codigoColorVeh = codigoColorVeh;
    }

    public String getInvUsa() {
        return invUsa;
    }

    public int getNroSimulacionCredito() {
        return nroSimulacionCredito;
    }

    public void setNroSimulacionCredito(int nroSimulacionCredito) {
        this.nroSimulacionCredito = nroSimulacionCredito;
    }

    public double getMontoFinanciarCredito() {
        return montoFinanciarCredito;
    }

    public void setMontoFinanciarCredito(double montoFinanciarCredito) {
        this.montoFinanciarCredito = montoFinanciarCredito;
    }

    public double getNroLetraCredito() {
        return nroLetraCredito;
    }

    public void setNroLetraCredito(double nroLetraCredito) {
        this.nroLetraCredito = nroLetraCredito;
    }

    public double getTeaMesCredito() {
        return teaMesCredito;
    }

    public void setTeaMesCredito(double teaMesCredito) {
        this.teaMesCredito = teaMesCredito;
    }

    public double getTotalInteresCredito() {
        return totalInteresCredito;
    }

    public void setTotalInteresCredito(double totalInteresCredito) {
        this.totalInteresCredito = totalInteresCredito;
    }

    public double getGrupo1CantCredito() {
        return grupo1CantCredito;
    }

    public void setGrupo1CantCredito(double grupo1CantCredito) {
        this.grupo1CantCredito = grupo1CantCredito;
    }

    public double getGrupo2CantCredito() {
        return grupo2CantCredito;
    }

    public void setGrupo2CantCredito(double grupo2CantCredito) {
        this.grupo2CantCredito = grupo2CantCredito;
    }

    public double getGrupo3CantCredito() {
        return grupo3CantCredito;
    }

    public void setGrupo3CantCredito(double grupo3CantCredito) {
        this.grupo3CantCredito = grupo3CantCredito;
    }

    public double getGrupo4CantCredito() {
        return grupo4CantCredito;
    }

    public void setGrupo4CantCredito(double grupo4CantCredito) {
        this.grupo4CantCredito = grupo4CantCredito;
    }

    public double getGrupo5CantCredito() {
        return grupo5CantCredito;
    }

    public void setGrupo5CantCredito(double grupo5CantCredito) {
        this.grupo5CantCredito = grupo5CantCredito;
    }

    public double getGrupo1MontCredito() {
        return grupo1MontCredito;
    }

    public void setGrupo1MontCredito(double grupo1MontCredito) {
        this.grupo1MontCredito = grupo1MontCredito;
    }

    public double getGrupo2MontCredito() {
        return grupo2MontCredito;
    }

    public void setGrupo2MontCredito(double grupo2MontCredito) {
        this.grupo2MontCredito = grupo2MontCredito;
    }

    public double getGrupo3MontCredito() {
        return grupo3MontCredito;
    }

    public void setGrupo3MontCredito(double grupo3MontCredito) {
        this.grupo3MontCredito = grupo3MontCredito;
    }

    public double getGrupo4MontCredito() {
        return grupo4MontCredito;
    }

    public void setGrupo4MontCredito(double grupo4MontCredito) {
        this.grupo4MontCredito = grupo4MontCredito;
    }

    public double getGrupo5MontCredito() {
        return grupo5MontCredito;
    }

    public void setGrupo5MontCredito(double grupo5MontCredito) {
        this.grupo5MontCredito = grupo5MontCredito;
    }

    public void setInvUsa(String invUsa) {
        this.invUsa = invUsa;
    }

    public String getLocalPreferido() {
        return localPreferido;
    }

    public void setLocalPreferido(String localPreferido) {
        this.localPreferido = localPreferido;
    }

    public String getArrendadorTitulo() {
        return arrendadorTitulo;
    }

    public void setArrendadorTitulo(String arrendadorTitulo) {
        this.arrendadorTitulo = arrendadorTitulo;
    }

    public String getTituloMancoumando() {
        return tituloMancoumando;
    }

    public void setTituloMancoumando(String tituloMancoumando) {
        this.tituloMancoumando = tituloMancoumando;
    }

    public String getCodigoMancomunado() {
        return codigoMancomunado;
    }

    public void setCodigoMancomunado(String codigoMancomunado) {
        this.codigoMancomunado = codigoMancomunado;
    }

    public String getArrendadorNroDocumento() {
        return arrendadorNroDocumento;
    }

    public void setArrendadorNroDocumento(String arrendadorNroDocumento) {
        this.arrendadorNroDocumento = arrendadorNroDocumento;
    }

    public String getArrendadorTelefono() {
        return arrendadorTelefono;
    }

    public void setArrendadorTelefono(String arrendadorTelefono) {
        this.arrendadorTelefono = arrendadorTelefono;
    }

    public String getTipoDoc() {
        return tipoDoc;
    }

    public void setTipoDoc(String tipoDoc) {
        this.tipoDoc = tipoDoc;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public int getNumeroIngresos() {
        return numeroIngresos;
    }

    public void setNumeroIngresos(int numeroIngresos) {
        this.numeroIngresos = numeroIngresos;
    }

    public long getNumFile() {
        return numFile;
    }

    public void setNumFile(long numFile) {
        this.numFile = numFile;
    }

    public String getSupNombre() {
        return supNombre;
    }

    public void setSupNombre(String supNombre) {
        this.supNombre = supNombre;
    }

    public String getVenNombre() {
        return venNombre;
    }

    public void setVenNombre(String venNombre) {
        this.venNombre = venNombre;
    }

    public String getArrendadorCodigo() {
        return arrendadorCodigo;
    }

    public void setArrendadorCodigo(String arrendadorCodigo) {
        this.arrendadorCodigo = arrendadorCodigo;
    }

    public String getArrendatarioRucSN() {
        return arrendatarioRucSN;
    }

    public void setArrendatarioRucSN(String arrendatarioRucSN) {
        this.arrendatarioRucSN = arrendatarioRucSN;
    }

    public String getNota5() {
        return nota5;
    }

    public void setNota5(String nota5) {
        this.nota5 = nota5;
    }

    public String getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public long getNumeroPropuesta() {
        return numeroPropuesta;
    }

    public void setNumeroPropuesta(long numeroPropuesta) {
        this.numeroPropuesta = numeroPropuesta;
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

    public String getTipoOperacion() {
        return tipoOperacion;
    }

    public void setTipoOperacion(String tipoOperacion) {
        this.tipoOperacion = tipoOperacion;
    }

    public String getArrendatarioRazonSocial() {
        return arrendatarioRazonSocial;
    }

    public void setArrendatarioRazonSocial(String arrendatarioRazonSocial) {
        this.arrendatarioRazonSocial = arrendatarioRazonSocial;
    }

    public String getArrendatarioPrimerNombre() {
        return arrendatarioPrimerNombre;
    }

    public void setArrendatarioPrimerNombre(String arrendatarioPrimerNombre) {
        this.arrendatarioPrimerNombre = arrendatarioPrimerNombre;
    }

    public String getArrendatarioSegungoNombre() {
        return arrendatarioSegungoNombre;
    }

    public void setArrendatarioSegungoNombre(String arrendatarioSegungoNombre) {
        this.arrendatarioSegungoNombre = arrendatarioSegungoNombre;
    }

    public String getArrendatarioApePaterno() {
        return arrendatarioApePaterno;
    }

    public void setArrendatarioApePaterno(String arrendatarioApePaterno) {
        this.arrendatarioApePaterno = arrendatarioApePaterno;
    }

    public String getArrendatarioApeMaterno() {
        return arrendatarioApeMaterno;
    }

    public void setArrendatarioApeMaterno(String arrendatarioApeMaterno) {
        this.arrendatarioApeMaterno = arrendatarioApeMaterno;
    }

    public String getArrendatarioCodigo() {
        return arrendatarioCodigo;
    }

    public void setArrendatarioCodigo(String arrendatarioCodigo) {
        this.arrendatarioCodigo = arrendatarioCodigo;
    }

    public String getArrendatarioNombre() {
        return arrendatarioNombre;
    }

    public void setArrendatarioNombre(String arrendatarioNombre) {
        this.arrendatarioNombre = arrendatarioNombre;
    }

    public String getArrendatarioNroDocumento() {
        return arrendatarioNroDocumento;
    }

    public void setArrendatarioNroDocumento(String arrendatarioNroDocumento) {
        this.arrendatarioNroDocumento = arrendatarioNroDocumento;
    }

    public String getArrendatarioDireccion() {
        return arrendatarioDireccion;
    }

    public void setArrendatarioDireccion(String arrendatarioDireccion) {
        this.arrendatarioDireccion = arrendatarioDireccion;
    }

    public String getArrendatarioDistrito() {
        return arrendatarioDistrito;
    }

    public void setArrendatarioDistrito(String arrendatarioDistrito) {
        this.arrendatarioDistrito = arrendatarioDistrito;
    }

    public String getArrendatarioEmail() {
        return arrendatarioEmail;
    }

    public void setArrendatarioEmail(String arrendatarioEmail) {
        this.arrendatarioEmail = arrendatarioEmail;
    }

    public String getArrendatarioTipoPersona() {
        return arrendatarioTipoPersona;
    }

    public void setArrendatarioTipoPersona(String arrendatarioTipoPersona) {
        this.arrendatarioTipoPersona = arrendatarioTipoPersona;
    }

    public String getArrendatarioClasePersona() {
        return arrendatarioClasePersona;
    }

    public void setArrendatarioClasePersona(String arrendatarioClasePersona) {
        this.arrendatarioClasePersona = arrendatarioClasePersona;
    }

    public String getArrendatarioClaseDocumento() {
        return arrendatarioClaseDocumento;
    }

    public void setArrendatarioClaseDocumento(String arrendatarioClaseDocumento) {
        this.arrendatarioClaseDocumento = arrendatarioClaseDocumento;
    }

    public String getArrendatarioUbigeo() {
        return arrendatarioUbigeo;
    }

    public void setArrendatarioUbigeo(String arrendatarioUbigeo) {
        this.arrendatarioUbigeo = arrendatarioUbigeo;
    }

    public String getModeloCodigo() {
        return modeloCodigo;
    }

    public void setModeloCodigo(String modeloCodigo) {
        this.modeloCodigo = modeloCodigo;
    }

    public String getModeloDes() {
        return modeloDes;
    }

    public void setModeloDes(String modeloDes) {
        this.modeloDes = modeloDes;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public double getTotalOperacionSol() {
        return totalOperacionSol;
    }

    public void setTotalOperacionSol(double totalOperacionSol) {
        this.totalOperacionSol = totalOperacionSol;
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

    public String getModVenta() {
        return modVenta;
    }

    public void setModVenta(String modVenta) {
        this.modVenta = modVenta;
    }

    public String getModVentaDes() {
        return modVentaDes;
    }

    public void setModVentaDes(String modVentaDes) {
        this.modVentaDes = modVentaDes;
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

    public String getModFinanciamiento() {
        return modFinanciamiento;
    }

    public void setModFinanciamiento(String modFinanciamiento) {
        this.modFinanciamiento = modFinanciamiento;
    }

    public String getModFinanciamientoDes() {
        return modFinanciamientoDes;
    }

    public void setModFinanciamientoDes(String modFinanciamientoDes) {
        this.modFinanciamientoDes = modFinanciamientoDes;
    }

    public long getNumeroFicha() {
        return numeroFicha;
    }

    public void setNumeroFicha(long numeroFicha) {
        this.numeroFicha = numeroFicha;
    }

    public String getCampanaDes() {
        return campanaDes;
    }

    public void setCampanaDes(String campanaDes) {
        this.campanaDes = campanaDes;
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

    public String getPlacaPartePago() {
        return placaPartePago;
    }

    public void setPlacaPartePago(String placaPartePago) {
        this.placaPartePago = placaPartePago;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getArrendadorRazSoc() {
        return arrendadorRazSoc;
    }

    public void setArrendadorRazSoc(String arrendadorRazSoc) {
        this.arrendadorRazSoc = arrendadorRazSoc;
    }

    public double getNotaCredito() {
        return notaCredito;
    }

    public void setNotaCredito(double notaCredito) {
        this.notaCredito = notaCredito;
    }

    public double getNotaCreditoSol() {
        return notaCreditoSol;
    }

    public void setNotaCreditoSol(double notaCreditoSol) {
        this.notaCreditoSol = notaCreditoSol;
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

    public String getMancomunadoRazSoc() {
        return mancomunadoRazSoc;
    }

    public void setMancomunadoRazSoc(String mancomunadoRazSoc) {
        this.mancomunadoRazSoc = mancomunadoRazSoc;
    }

    public String getArrendatarioTelefono() {
        return arrendatarioTelefono;
    }

    public void setArrendatarioTelefono(String arrendatarioTelefono) {
        this.arrendatarioTelefono = arrendatarioTelefono;
    }

    public String getArrendatarioFax() {
        return arrendatarioFax;
    }

    public void setArrendatarioFax(String arrendatarioFax) {
        this.arrendatarioFax = arrendatarioFax;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public void setTipoPago(String tipoPago) {
        this.tipoPago = tipoPago;
    }

    public double getPrecioLista() {
        return precioLista;
    }

    public void setPrecioLista(double precioLista) {
        this.precioLista = precioLista;
    }

    public double getDscto() {
        return dscto;
    }

    public void setDscto(double dscto) {
        this.dscto = dscto;
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

    public double getPrecioListaSol() {
        return precioListaSol;
    }

    public void setPrecioListaSol(double precioListaSol) {
        this.precioListaSol = precioListaSol;
    }

    public double getDsctoSol() {
        return dsctoSol;
    }

    public void setDsctoSol(double dsctoSol) {
        this.dsctoSol = dsctoSol;
    }

    public double getTramites() {
        return tramites;
    }

    public void setTramites(double tramites) {
        this.tramites = tramites;
    }

    public double getTramitesSol() {
        return tramitesSol;
    }

    public void setTramitesSol(double tramitesSol) {
        this.tramitesSol = tramitesSol;
    }

    public double getAccesoriosSol() {
        return accesoriosSol;
    }

    public void setAccesoriosSol(double accesoriosSol) {
        this.accesoriosSol = accesoriosSol;
    }

    public double getOtrosSol() {
        return otrosSol;
    }

    public void setOtrosSol(double otrosSol) {
        this.otrosSol = otrosSol;
    }

    public double getSeguroSol() {
        return seguroSol;
    }

    public void setSeguroSol(double seguroSol) {
        this.seguroSol = seguroSol;
    }

    public double getPartePago() {
        return partePago;
    }

    public void setPartePago(double partePago) {
        this.partePago = partePago;
    }

    public double getPartePagoSol() {
        return partePagoSol;
    }

    public void setPartePagoSol(double partePagoSol) {
        this.partePagoSol = partePagoSol;
    }

    public double getRfvp() {
        return rfvp;
    }

    public void setRfvp(double rfvp) {
        this.rfvp = rfvp;
    }

    public double getCuotaInicial() {
        return cuotaInicial;
    }

    public void setCuotaInicial(double cuotaInicial) {
        this.cuotaInicial = cuotaInicial;
    }

    public double getMontoFinanciar() {
        return montoFinanciar;
    }

    public void setMontoFinanciar(double montoFinanciar) {
        this.montoFinanciar = montoFinanciar;
    }

    public double getTotalInteres() {
        return totalInteres;
    }

    public void setTotalInteres(double totalInteres) {
        this.totalInteres = totalInteres;
    }

    public int getGrupo1Cantidad() {
        return grupo1Cantidad;
    }

    public void setGrupo1Cantidad(int grupo1Cantidad) {
        this.grupo1Cantidad = grupo1Cantidad;
    }

    public int getGrupo2Cantidad() {
        return grupo2Cantidad;
    }

    public void setGrupo2Cantidad(int grupo2Cantidad) {
        this.grupo2Cantidad = grupo2Cantidad;
    }

    public int getGrupo3Cantidad() {
        return grupo3Cantidad;
    }

    public void setGrupo3Cantidad(int grupo3Cantidad) {
        this.grupo3Cantidad = grupo3Cantidad;
    }

    public int getGrupo4Cantidad() {
        return grupo4Cantidad;
    }

    public void setGrupo4Cantidad(int grupo4Cantidad) {
        this.grupo4Cantidad = grupo4Cantidad;
    }

    public int getGrupo5Cantidad() {
        return grupo5Cantidad;
    }

    public void setGrupo5Cantidad(int grupo5Cantidad) {
        this.grupo5Cantidad = grupo5Cantidad;
    }

    public double getGrupo1Monto() {
        return grupo1Monto;
    }

    public void setGrupo1Monto(double grupo1Monto) {
        this.grupo1Monto = grupo1Monto;
    }

    public double getGrupo2Monto() {
        return grupo2Monto;
    }

    public void setGrupo2Monto(double grupo2Monto) {
        this.grupo2Monto = grupo2Monto;
    }

    public double getGrupo3Monto() {
        return grupo3Monto;
    }

    public void setGrupo3Monto(double grupo3Monto) {
        this.grupo3Monto = grupo3Monto;
    }

    public double getGrupo4Monto() {
        return grupo4Monto;
    }

    public void setGrupo4Monto(double grupo4Monto) {
        this.grupo4Monto = grupo4Monto;
    }

    public double getGrupo5Monto() {
        return grupo5Monto;
    }

    public void setGrupo5Monto(double grupo5Monto) {
        this.grupo5Monto = grupo5Monto;
    }

    public double getTeaMes() {
        return teaMes;
    }

    public void setTeaMes(double teaMes) {
        this.teaMes = teaMes;
    }

    public double getSeguroMaritimo() {
        return seguroMaritimo;
    }

    public void setSeguroMaritimo(double seguroMaritimo) {
        this.seguroMaritimo = seguroMaritimo;
    }

    public double getInspeccionPreEmb() {
        return inspeccionPreEmb;
    }

    public void setInspeccionPreEmb(double inspeccionPreEmb) {
        this.inspeccionPreEmb = inspeccionPreEmb;
    }

    public double getDescarga() {
        return descarga;
    }

    public void setDescarga(double descarga) {
        this.descarga = descarga;
    }

    public double getComisionTDP() {
        return comisionTDP;
    }

    public void setComisionTDP(double comisionTDP) {
        this.comisionTDP = comisionTDP;
    }

    public double getGastosFinancieros() {
        return gastosFinancieros;
    }

    public void setGastosFinancieros(double gastosFinancieros) {
        this.gastosFinancieros = gastosFinancieros;
    }

    public double getAlmacenaje() {
        return almacenaje;
    }

    public void setAlmacenaje(double almacenaje) {
        this.almacenaje = almacenaje;
    }

    public double getTotComisConces() {
        return totComisConces;
    }

    public void setTotComisConces(double totComisConces) {
        this.totComisConces = totComisConces;
    }

    public double getGastosOperativos() {
        return gastosOperativos;
    }

    public void setGastosOperativos(double gastosOperativos) {
        this.gastosOperativos = gastosOperativos;
    }

}
