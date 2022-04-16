package pe.com.gp.entity;

import java.io.Serializable;

public class Cliente implements Serializable {

    static final long serialVersionUID = 1L;
    private String c_c_ruc_empresa;
    private String c_c_ruc_proveedor;
    private String empresa;
    private String codigo;
    private String auxiliar;
    private String razon_social;
    private String direccion;
    private String telefono1;
    private String situacionCliente;
    private String libretaElectoral;
    private String tipo_persona;
    private String primer_nombre;
    private String Segundo_nombre;
    private String paterno;
    private String materno;
    private String bloqueoCliente;
    private String email_01;
    private String estadoCivil;
    private String sexo;
    private String fechaNacimiento;
    private String telefono2;
    private String documento;
    private String acceso;
    //private String doc_dni;
    //private String doc_ruc;
    private String ubigeo;
    private String tipo_documento;
    private String cargo;
    private String cargoDescripcion;
    private String cargoDes;
    private String celular1;
    private String celular2;
    private String contadoCredito;
    //private String nro_fax;
    private String titulo_per;
    private String tipoContacto;
    private String centroLaboral;
    private String anexo;
    private String tratamientoDatos;
    private String fechaImpresionTratamiento;
    private String email_02;
    private String d_ingreso;
    private String ruc_sn;
    private String depart;
    private String provin;
    private String distri;
    // private String co_cr;
    // private String clase_cliente = "";
    // private String codigo_fraq = "";
    // private String cred_fraq = "CO";
    private String situacion;
    private String bloqueado;
    private String formaPago;
    private String nombreAsegurado;
    private String siniestro;
    private String poliza;
    private String nacionalidad;
    private String ciudad;
    private String ruc;
    private String dni;
    private Integer diasPago;
    private String codigoDistrito;
    private String descripcionDistrito;
    private String contadoC;
    private String claseCliente;
    private String ordenCompra;
    private String fechaIniTrataDatos;
    private String nombreDistrito;
    private String codigoPostal;
    private String fax;

    // Otros que no son campos en la tabla
    private String cliAmbDoc;
    private String cliTipPerId;
    private String cliTipPer;
    private String cliTipDoc;
    private String cliNroDoc;
    private String cliClaDes;
    private double cliSalRepDol;
    private double cliSalSerDol;
    private double cliSalRepSol;
    private double cliSalSerSol;
    private String horaContacto;
    private String minutoContacto;
    private String usoVehiculo;
    private String usoVehiculoGP;
    private String obs;
    private String todoDia;
    private String listaTipCon;

    private String nroPlaca;
    private String tipoBus;
    private String clubPana;
    private String refUIF;
    private String documentoSAP;
    private boolean existe;
    private String moneda;
    private String canalVenta;
    private String indiceDireccion;
    private String docEntry;    // La Entidad Cliente debe tener un docEntry, seguro?????????
    private String ofertaVenta; // La Entidad Cliente debe tener un nro de Oferta, seguro?????????
    private String pedidoVenta; // La Entidad Cliente debe tener un nro de Pedido, seguro?????????
    private String codigoVendedor;
    private String proceso;
    
    private String preferencial;
    private String rangoInicial;
    private String rangoFinal;
    
    private String bcpSoles; 
    private String bcpsolTipo;
    private String bcpDolar;
    private String bcpdolTipo;
    private String bbvaSoles;
    private String bbvasolTipo;
    private String bbvaDolar;
    private String bbvadolTipo;
    private String scotiabankICC;
    private String interbankICC;
    
    private String tipoCuenta;
    private String cuentaBancaria;
    private String banco;

    public String getBanco() {
        return banco;
    }

    public void setBanco(String banco) {
        this.banco = banco;
    }

    
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
    
    

    public String getBcpSoles() {
        return bcpSoles;
    }

    public void setBcpSoles(String bcpSoles) {
        this.bcpSoles = bcpSoles;
    }

    public String getBcpsolTipo() {
        return bcpsolTipo;
    }

    public void setBcpsolTipo(String bcpsolTipo) {
        this.bcpsolTipo = bcpsolTipo;
    }

    public String getBcpDolar() {
        return bcpDolar;
    }

    public void setBcpDolar(String bcpDolar) {
        this.bcpDolar = bcpDolar;
    }

    public String getBcpdolTipo() {
        return bcpdolTipo;
    }

    public void setBcpdolTipo(String bcpdolTipo) {
        this.bcpdolTipo = bcpdolTipo;
    }

    public String getBbvaSoles() {
        return bbvaSoles;
    }

    public void setBbvaSoles(String bbvaSoles) {
        this.bbvaSoles = bbvaSoles;
    }

    public String getBbvasolTipo() {
        return bbvasolTipo;
    }

    public void setBbvasolTipo(String bbvasolTipo) {
        this.bbvasolTipo = bbvasolTipo;
    }

    public String getBbvaDolar() {
        return bbvaDolar;
    }

    public void setBbvaDolar(String bbvaDolar) {
        this.bbvaDolar = bbvaDolar;
    }

    public String getBbvadolTipo() {
        return bbvadolTipo;
    }

    public void setBbvadolTipo(String bbvadolTipo) {
        this.bbvadolTipo = bbvadolTipo;
    }

    public String getScotiabankICC() {
        return scotiabankICC;
    }

    public void setScotiabankICC(String scotiabankICC) {
        this.scotiabankICC = scotiabankICC;
    }

    public String getInterbankICC() {
        return interbankICC;
    }

    public void setInterbankICC(String interbankICC) {
        this.interbankICC = interbankICC;
    }
    
    

    public String getC_c_ruc_empresa() {
        return c_c_ruc_empresa;
    }

    public void setC_c_ruc_empresa(String c_c_ruc_empresa) {
        this.c_c_ruc_empresa = c_c_ruc_empresa;
    }

    public String getC_c_ruc_proveedor() {
        return c_c_ruc_proveedor;
    }

    public void setC_c_ruc_proveedor(String c_c_ruc_proveedor) {
        this.c_c_ruc_proveedor = c_c_ruc_proveedor;
    }

    public String getAcceso() {
        return acceso;
    }

    public void setAcceso(String acceso) {
        this.acceso = acceso;
    }
    



    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    
    
    public String getPreferencial() {
        return preferencial;
    }

    public void setPreferencial(String preferencial) {
        this.preferencial = preferencial;
    }

    public String getRangoInicial() {
        return rangoInicial;
    }

    public void setRangoInicial(String rangoInicial) {
        this.rangoInicial = rangoInicial;
    }

    public String getRangoFinal() {
        return rangoFinal;
    }

    public void setRangoFinal(String rangoFinal) {
        this.rangoFinal = rangoFinal;
    }
    
    
    

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    

    public String getCodigoVendedor() {
        return codigoVendedor;
    }

    public void setCodigoVendedor(String codigoVendedor) {
        this.codigoVendedor = codigoVendedor;
    }
    
    

    public Cliente() {
    }

    public String getOfertaVenta() {
        return ofertaVenta;
    }

    public void setOfertaVenta(String ofertaVenta) {
        this.ofertaVenta = ofertaVenta;
    }

    public String getPedidoVenta() {
        return pedidoVenta;
    }

    public void setPedidoVenta(String pedidoVenta) {
        this.pedidoVenta = pedidoVenta;
    }

    public String getDocEntry() {
        return docEntry;
    }

    public void setDocEntry(String docEntry) {
        this.docEntry = docEntry;
    }

    public String getIndiceDireccion() {
        return indiceDireccion;
    }

    public void setIndiceDireccion(String indiceDireccion) {
        this.indiceDireccion = indiceDireccion;
    }

    public String getCanalVenta() {
        return canalVenta;
    }

    public void setCanalVenta(String canalVenta) {
        this.canalVenta = canalVenta;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getDocumentoSAP() {
        return documentoSAP;
    }

    public void setDocumentoSAP(String documentoSAP) {
        this.documentoSAP = documentoSAP;
    }

    public String getRefUIF() {
        return refUIF;
    }

    public void setRefUIF(String refUIF) {
        this.refUIF = refUIF;
    }

    public String getCargoDes() {
        return cargoDes;
    }

    public void setCargoDes(String cargoDes) {
        this.cargoDes = cargoDes;
    }

    public String getClubPana() {
        return clubPana;
    }

    public void setClubPana(String clubPana) {
        this.clubPana = clubPana;
    }

    public String getNroPlaca() {
        return nroPlaca;
    }

    public void setNroPlaca(String nroPlaca) {
        this.nroPlaca = nroPlaca;
    }

    public String getTipoBus() {
        return tipoBus;
    }

    public void setTipoBus(String tipoBus) {
        this.tipoBus = tipoBus;
    }

    public String getHoraContacto() {
        return horaContacto;
    }

    public void setHoraContacto(String horaContacto) {
        this.horaContacto = horaContacto;
    }

    public String getMinutoContacto() {
        return minutoContacto;
    }

    public void setMinutoContacto(String minutoContacto) {
        this.minutoContacto = minutoContacto;
    }

    public String getUsoVehiculo() {
        return usoVehiculo;
    }

    public void setUsoVehiculo(String usoVehiculo) {
        this.usoVehiculo = usoVehiculo;
    }

    public String getUsoVehiculoGP() {
        return usoVehiculoGP;
    }

    public void setUsoVehiculoGP(String usoVehiculoGP) {
        this.usoVehiculoGP = usoVehiculoGP;
    }

    public String getCliAmbDoc() {
        return cliAmbDoc;
    }

    public void setCliAmbDoc(String cliAmbDoc) {
        this.cliAmbDoc = cliAmbDoc;
    }

    public String getCliTipPerId() {
        return cliTipPerId;
    }

    public void setCliTipPerId(String cliTipPerId) {
        this.cliTipPerId = cliTipPerId;
    }

    public String getCliTipPer() {
        return cliTipPer;
    }

    public void setCliTipPer(String cliTipPer) {
        this.cliTipPer = cliTipPer;
    }

    public String getCliTipDoc() {
        return cliTipDoc;
    }

    public void setCliTipDoc(String cliTipDoc) {
        this.cliTipDoc = cliTipDoc;
    }

    public String getCliNroDoc() {
        return cliNroDoc;
    }

    public void setCliNroDoc(String cliNroDoc) {
        this.cliNroDoc = cliNroDoc;
    }

    public String getCliClaDes() {
        return cliClaDes;
    }

    public void setCliClaDes(String cliClaDes) {
        this.cliClaDes = cliClaDes;
    }

    public double getCliSalRepDol() {
        return cliSalRepDol;
    }

    public void setCliSalRepDol(double cliSalRepDol) {
        this.cliSalRepDol = cliSalRepDol;
    }

    public double getCliSalSerDol() {
        return cliSalSerDol;
    }

    public void setCliSalSerDol(double cliSalSerDol) {
        this.cliSalSerDol = cliSalSerDol;
    }

    public double getCliSalRepSol() {
        return cliSalRepSol;
    }

    public void setCliSalRepSol(double cliSalRepSol) {
        this.cliSalRepSol = cliSalRepSol;
    }

    public double getCliSalSerSol() {
        return cliSalSerSol;
    }

    public void setCliSalSerSol(double cliSalSerSol) {
        this.cliSalSerSol = cliSalSerSol;
    }

    public String getContadoC() {
        return contadoC;
    }

    public void setContadoC(String contadoC) {
        this.contadoC = contadoC;
    }

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getProvin() {
        return provin;
    }

    public void setProvin(String provin) {
        this.provin = provin;
    }

    public String getDistri() {
        return distri;
    }

    public void setDistri(String distri) {
        this.distri = distri;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getNombreDistrito() {
        return nombreDistrito;
    }

    public void setNombreDistrito(String nombreDistrito) {
        this.nombreDistrito = nombreDistrito;
    }

    public String getFechaIniTrataDatos() {
        return fechaIniTrataDatos;
    }

    public void setFechaIniTrataDatos(String fechaIniTrataDatos) {
        this.fechaIniTrataDatos = fechaIniTrataDatos;
    }

    public String getCargoDescripcion() {
        return cargoDescripcion;
    }

    public void setCargoDescripcion(String cargoDescripcion) {
        this.cargoDescripcion = cargoDescripcion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public Integer getDiasPago() {
        return diasPago;
    }

    public void setDiasPago(Integer diasPago) {
        this.diasPago = diasPago;
    }

    public String getCodigoDistrito() {
        return codigoDistrito;
    }

    public void setCodigoDistrito(String codigoDistrito) {
        this.codigoDistrito = codigoDistrito;
    }

    public String getDescripcionDistrito() {
        return descripcionDistrito;
    }

    public void setDescripcionDistrito(String descripcionDistrito) {
        this.descripcionDistrito = descripcionDistrito;
    }

    public String getClaseCliente() {
        return claseCliente;
    }

    public void setClaseCliente(String claseCliente) {
        this.claseCliente = claseCliente;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getAuxiliar() {
        return auxiliar;
    }

    public void setAuxiliar(String auxiliar) {
        this.auxiliar = auxiliar;
    }

    public String getRazon_social() {
        return razon_social;
    }

    public void setRazon_social(String razon_social) {
        this.razon_social = razon_social;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono1() {
        return telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }

    public String getSituacionCliente() {
        return situacionCliente;
    }

    public void setSituacionCliente(String situacionCliente) {
        this.situacionCliente = situacionCliente;
    }

    public String getLibretaElectoral() {
        return libretaElectoral;
    }

    public void setLibretaElectoral(String libretaElectoral) {
        this.libretaElectoral = libretaElectoral;
    }

    public String getTipo_persona() {
        return tipo_persona;
    }

    public void setTipo_persona(String tipo_persona) {
        this.tipo_persona = tipo_persona;
    }

    public String getPrimer_nombre() {
        return primer_nombre;
    }

    public void setPrimer_nombre(String primer_nombre) {
        this.primer_nombre = primer_nombre;
    }

    public String getSegundo_nombre() {
        return Segundo_nombre;
    }

    public void setSegundo_nombre(String Segundo_nombre) {
        this.Segundo_nombre = Segundo_nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getBloqueoCliente() {
        return bloqueoCliente;
    }

    public void setBloqueoCliente(String bloqueoCliente) {
        this.bloqueoCliente = bloqueoCliente;
    }

    public String getEmail_01() {
        return email_01;
    }

    public void setEmail_01(String email_01) {
        this.email_01 = email_01;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono2() {
        return telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getUbigeo() {
        return ubigeo;
    }

    public void setUbigeo(String ubigeo) {
        this.ubigeo = ubigeo;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getContadoCredito() {
        return contadoCredito;
    }

    public void setContadoCredito(String contadoCredito) {
        this.contadoCredito = contadoCredito;
    }

    public String getTitulo_per() {
        return titulo_per;
    }

    public void setTitulo_per(String titulo_per) {
        this.titulo_per = titulo_per;
    }

    public String getTipoContacto() {
        return tipoContacto;
    }

    public void setTipoContacto(String tipoContacto) {
        this.tipoContacto = tipoContacto;
    }

    public String getCentroLaboral() {
        return centroLaboral;
    }

    public void setCentroLaboral(String centroLaboral) {
        this.centroLaboral = centroLaboral;
    }

    public String getAnexo() {
        return anexo;
    }

    public void setAnexo(String anexo) {
        this.anexo = anexo;
    }

    public String getTratamientoDatos() {
        return tratamientoDatos;
    }

    public void setTratamientoDatos(String tratamientoDatos) {
        this.tratamientoDatos = tratamientoDatos;
    }

    public String getFechaImpresionTratamiento() {
        return fechaImpresionTratamiento;
    }

    public void setFechaImpresionTratamiento(String fechaImpresionTratamiento) {
        this.fechaImpresionTratamiento = fechaImpresionTratamiento;
    }

    public String getEmail_02() {
        return email_02;
    }

    public void setEmail_02(String email_02) {
        this.email_02 = email_02;
    }

    public String getD_ingreso() {
        return d_ingreso;
    }

    public void setD_ingreso(String d_ingreso) {
        this.d_ingreso = d_ingreso;
    }

    public String getRuc_sn() {
        return ruc_sn;
    }

    public void setRuc_sn(String ruc_sn) {
        this.ruc_sn = ruc_sn;
    }

    public String getSituacion() {
        return situacion;
    }

    public void setSituacion(String situacion) {
        this.situacion = situacion;
    }

    public String getBloqueado() {
        return bloqueado;
    }

    public void setBloqueado(String bloqueado) {
        this.bloqueado = bloqueado;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getNombreAsegurado() {
        return nombreAsegurado;
    }

    public void setNombreAsegurado(String nombreAsegurado) {
        this.nombreAsegurado = nombreAsegurado;
    }

    public String getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(String siniestro) {
        this.siniestro = siniestro;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public String getTodoDia() {
        return todoDia;
    }

    public void setTodoDia(String todoDia) {
        this.todoDia = todoDia;
    }

    public String getListaTipCon() {
        return listaTipCon;
    }

    public void setListaTipCon(String listaTipCon) {
        this.listaTipCon = listaTipCon;
    }

}
