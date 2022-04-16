package pe.com.gp.entity;

import java.io.Serializable;

public class Parte implements Serializable {

    private static final long serialVersionUID = 1L;
    private String Codigo;
    private String Descripcion;
    private String Desc_tipo_boleta;
    private String docuemntoRef;
    private String CodProcedencia;
    private String CodCampania;
    private double DsctoProducto;
    private int DsctoMO;
    private int DsctoTDP;
    private int DsctoGP;
    private double Igv;
    private int Stk;
    private boolean BolExisPedPen;
    private String tipoDscto;
    private String cajas;
    //private boolean BolObsequio;
    private String esObsequio; // S/N
    private String anulado;
    private double CAL_TotBruto_Sol;
    private double CAL_TotBruto_Dol;
    private double CAL_TotDscto_Sol;
    private double CAL_TotDscto_Dol;
    private double CAL_TotIGV_Sol;
    private double CAL_TotIGV_Dol;
    private double CAL_TotVtaSol; // Neto
    private double CAL_TotVtaDol;
    private double CAL_TotGralVtaSol; // Total Gral
    private double CAL_TotGralVtaDol;
    private int CAL_cantidad;
    private int cantidad;
    private double CAL_descuento;
    private double descuento;
    private String accion;
    private String tipo = "R";
    private String item;
    private int secuencia;
    private int stkSeg;
    private int StkDis;
    private int StkMes;
    private int StkTotal;
    private int StkExt;
    private int StkTaller;
    private int StkDisponible;
    private int StkAlm;
    private int stkComprometido;
    private double UltCosto;
    private double CostoProm;
    private int sinMoviVenta;
    private int sinMoviCompra;
    private String UltFecSal;
    private String UltFecIng;
    private String fecApe;
    private String Ubicacion;
    private String inventariado;
    private int StkMax;
    private int StkMad;
    private int StkIni;
    private int StkTmp;
    private String codigoAlmacen;
    private String almacen;
    private String ubicaParteEnAlmacen;
    private int stockParteEnAlmacen;
    private String tipoProducto;
    private String unidadMedida;
    private String codTienda;
    private double VVPSol;
    private double VVDSol;
    private double VVPDol;
    private double VVDDol;
    private double totVtaSol;       // Neto
    private double totVtaDol;       // Neto    
    private double totGralVtaSol;   // Total Gral.
    private double totGralVtaDol;   // Total Gral.
    private double totCostoSol;
    private double totCostoDol;
    private double totDsctoSol;
    private double totDsctoDol;
    private double totIgvSol;
    private double totIgvDol;
    private double totBrutoSol;
    private double totBrutoDol;
    private double DsctoManoObra; // dscto_mo
    private double DsctoTaller; // dscto_ot
    private double DsctoMostrador;
    private double DsctoOtros;
    private double DsctoOT;
    private double DsctoCiaSeguro;
    private double DsctoMaximo;
    private String marca;
    private String marcaTDP;
    private String codigoMarca;
    private String tipoDescuento;
    private String tipoFactor;
    private String claseProducto;
    private int StkMin;
    private int mesesSinMvtoVta;
    private int mesesSinMvtoComp;
    private int diasSinMvtoVta;
    private String claseCompra;
    private String claseVenta;
    private String codigoEmpresa;
    private String codigoProducto;
    private int mes;
    private int anio;
    private int ventas;
    private int antMovCompra;
    private int antMovVenta;
    private double costoPromedio;
    private String procedencia;
    private int cant_dev;
    private double costoUnitario;
    private double costoTotal;
    private String Observado;
    private String AccImp;
    private int ComMes;
    private int VtaMes;
    private int StkTdp;
    private int Venta1M;
    private int Venta2M;
    private int Venta3M;
    private int Venta4M;
    private int Venta5M;
    private int Venta6M;
    private Double total;
    private Double disponible;
    private long NroOC;
    private String FecOC;
    private String PedPendiente;
    private String SepPendiente;
    private double tipoCambio;
    private int numPed;
    private double margen;
    private double vvp20;
    private double vvp30;
    private double vvp40;
    private double vvp50;
    private int documento;
    private double vvp;
    private double vvpDsc;
    private double pvp;
    private int boleta;
    private boolean existe;
    private String indicadorIGV;
    private int cantidad_ov;
    private double horaMatriz;
    private double precioMatriz;
    private double frecuenciaMatriz;
    private String codProveedor;
    private String codMecanico;
    private String cuentaContable;
    private String moneda;
    private String centroCostoTienda;
    private String centroCostoUniNeg;
    private String tipoArticulo;
    private int cantidadPendiente;
    private int cantidadTotal;
    private int numItem;
    private String estadoLinea;
    private String procedeLinea;
    private double precio;
    private double totalPrecio;
    private String proviene;
    private String codigoAnulado;
    private String masPrecios;
    private Double descuentoCantidad;
    private Double totalPublicoSol;
    private String foto; 
    private String dual;
    private String cantidadCajas;
    private String familia;
    private String clase;
    private String grupo;
    private Double precioItem;
    private String fechaProp;
    private String propuesta;
    private Double precio01;
    private Double precio02;
    private Double precio03;
    private Double precio04;
    private Double precio05;
    
    private String stockDisponible;
    private String stockAlmacenes;
    private String stockTemporal;
    private String stockTotal;
    private String stockSeguridad;
    private String fechaUltimaSalida;
    private String fechaUltimoIngreso;
    private double ultCosPro;
    private double ultCosto;  
    private String fechaBoleta;
    private String tipoBoleta;
    
    private long ant_anterior;
    private long dis_anterior;
    private long tot_anterior;
    private long ant_posterior;
    private long dis_posterior;
    private long tot_posterior;
    
    private String codigoCliente;
    private String nombreCliente;
    private String fechaEntrega;
    private String usuario;
    private String notaDocu;

    public String getNotaDocu() {
        return notaDocu;
    }

    public void setNotaDocu(String notaDocu) {
        this.notaDocu = notaDocu;
    }
    

    public String getFechaEntrega() {
        return fechaEntrega;
    }

    public void setFechaEntrega(String fechaEntrega) {
        this.fechaEntrega = fechaEntrega;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    
    

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }
    

    public String getTipoBoleta() {
        return tipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        this.tipoBoleta = tipoBoleta;
    }

    
    public String getDocuemntoRef() {
        return docuemntoRef;
    }

    public void setDocuemntoRef(String docuemntoRef) {
        this.docuemntoRef = docuemntoRef;
    }

    
    public String getDesc_tipo_boleta() {
        return Desc_tipo_boleta;
    }

    public void setDesc_tipo_boleta(String Desc_tipo_boleta) {
        this.Desc_tipo_boleta = Desc_tipo_boleta;
    }

    
    
    public long getAnt_anterior() {
        return ant_anterior;
    }

    public void setAnt_anterior(long ant_anterior) {
        this.ant_anterior = ant_anterior;
    }

    public long getDis_anterior() {
        return dis_anterior;
    }

    public void setDis_anterior(long dis_anterior) {
        this.dis_anterior = dis_anterior;
    }

    public long getTot_anterior() {
        return tot_anterior;
    }

    public void setTot_anterior(long tot_anterior) {
        this.tot_anterior = tot_anterior;
    }

    public long getAnt_posterior() {
        return ant_posterior;
    }

    public void setAnt_posterior(long ant_posterior) {
        this.ant_posterior = ant_posterior;
    }

    public long getDis_posterior() {
        return dis_posterior;
    }

    public void setDis_posterior(long dis_posterior) {
        this.dis_posterior = dis_posterior;
    }

    public long getTot_posterior() {
        return tot_posterior;
    }

    public void setTot_posterior(long tot_posterior) {
        this.tot_posterior = tot_posterior;
    }
    
    
    
    public String getFechaBoleta() {
        return fechaBoleta;
    }

    public void setFechaBoleta(String fechaBoleta) {
        this.fechaBoleta = fechaBoleta;
    }
    
    public String getPropuesta() {
        return propuesta;
    }

    public void setPropuesta(String propuesta) {
        this.propuesta = propuesta;
    }
   
    
    

    public String getFechaProp() {
        return fechaProp;
    }

    public void setFechaProp(String fechaProp) {
        this.fechaProp = fechaProp;
    }

    
    
    public String getStockAlmacenes() {
        return stockAlmacenes;
    }

    public void setStockAlmacenes(String stockAlmacenes) {
        this.stockAlmacenes = stockAlmacenes;
    }

    public String getStockTemporal() {
        return stockTemporal;
    }

    public void setStockTemporal(String stockTemporal) {
        this.stockTemporal = stockTemporal;
    }

    
    public String getFechaUltimaSalida() {
        return fechaUltimaSalida;
    }

    public void setFechaUltimaSalida(String fechaUltimaSalida) {
        this.fechaUltimaSalida = fechaUltimaSalida;
    }

    public String getFechaUltimoIngreso() {
        return fechaUltimoIngreso;
    }

    public void setFechaUltimoIngreso(String fechaUltimoIngreso) {
        this.fechaUltimoIngreso = fechaUltimoIngreso;
    }

    public double getUltCosPro() {
        return ultCosPro;
    }

    public void setUltCosPro(double ultCosPro) {
        this.ultCosPro = ultCosPro;
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
    

    public Double getPrecio01() {
        return precio01;
    }

    public void setPrecio01(Double precio01) {
        this.precio01 = precio01;
    }

    public Double getPrecio02() {
        return precio02;
    }

    public void setPrecio02(Double precio02) {
        this.precio02 = precio02;
    }

    public Double getPrecio03() {
        return precio03;
    }

    public void setPrecio03(Double precio03) {
        this.precio03 = precio03;
    }

    public Double getPrecio04() {
        return precio04;
    }

    public void setPrecio04(Double precio04) {
        this.precio04 = precio04;
    }

    public Double getPrecio05() {
        return precio05;
    }

    public void setPrecio05(Double precio05) {
        this.precio05 = precio05;
    }
    

    public Double getPrecioItem() {
        return precioItem;
    }

    public void setPrecioItem(Double precioItem) {
        this.precioItem = precioItem;
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
    
    

    public String getDual() {
        return dual;
    }

    public void setDual(String dual) {
        this.dual = dual;
    }

    public String getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(String cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }
    
    
    

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
    
    

    public Double getTotalPublicoSol() {
        return totalPublicoSol;
    }

    public void setTotalPublicoSol(Double totalPublicoSol) {
        this.totalPublicoSol = totalPublicoSol;
    }
    
    
    public Double getDescuentoCantidad() {
        return descuentoCantidad;
    }

    public void setDescuentoCantidad(Double descuentoCantidad) {
        this.descuentoCantidad = descuentoCantidad;
    }
    
    
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) { 
        this.total = total;
    }

    public Double getDisponible() {
        return disponible;
    }

    public void setDisponible(Double disponible) {
        this.disponible = disponible;
    }

    
    public String getCajas() {
        return cajas;
    }

    public void setCajas(String cajas) {
        this.cajas = cajas;
    }

    
    public String getMasPrecios() {
        return masPrecios;
    }

    public void setMasPrecios(String masPrecios) {
        this.masPrecios = masPrecios;
    }
            
            

    public String getCodigoAnulado() {
        return codigoAnulado;
    }

    public void setCodigoAnulado(String codigoAnulado) {
        this.codigoAnulado = codigoAnulado;
    }
    
    

    public String getProviene() {
        return proviene;
    }

    public void setProviene(String proviene) {
        this.proviene = proviene;
    }
    
    

    public Parte() {
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getTotalPrecio() {
        return totalPrecio;
    }

    public void setTotalPrecio(double totalPrecio) {
        this.totalPrecio = totalPrecio;
    }

    public String getProcedeLinea() {
        return procedeLinea;
    }

    public void setProcedeLinea(String procedeLinea) {
        this.procedeLinea = procedeLinea;
    }

    public String getEstadoLinea() {
        return estadoLinea;
    }

    public void setEstadoLinea(String estadoLinea) {
        this.estadoLinea = estadoLinea;
    }

    public int getNumItem() {
        return numItem;
    }

    public void setNumItem(int numItem) {
        this.numItem = numItem;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public int getCantidadPendiente() {
        return cantidadPendiente;
    }

    public void setCantidadPendiente(int cantidadPendiente) {
        this.cantidadPendiente = cantidadPendiente;
    }

    public String getTipoArticulo() {
        return tipoArticulo;
    }

    public void setTipoArticulo(String tipoArticulo) {
        this.tipoArticulo = tipoArticulo;
    }

    public String getCentroCostoUniNeg() {
        return centroCostoUniNeg;
    }

    public void setCentroCostoUniNeg(String centroCostoUniNeg) {
        this.centroCostoUniNeg = centroCostoUniNeg;
    }

    public String getCentroCostoTienda() {
        return centroCostoTienda;
    }

    public void setCentroCostoTienda(String centroCostoTienda) {
        this.centroCostoTienda = centroCostoTienda;
    }

    public double getHoraMatriz() {
        return horaMatriz;
    }

    public void setHoraMatriz(double horaMatriz) {
        this.horaMatriz = horaMatriz;
    }

    public double getPrecioMatriz() {
        return precioMatriz;
    }

    public void setPrecioMatriz(double precioMatriz) {
        this.precioMatriz = precioMatriz;
    }

    public double getFrecuenciaMatriz() {
        return frecuenciaMatriz;
    }

    public void setFrecuenciaMatriz(double frecuenciaMatriz) {
        this.frecuenciaMatriz = frecuenciaMatriz;
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getCodMecanico() {
        return codMecanico;
    }

    public void setCodMecanico(String codMecanico) {
        this.codMecanico = codMecanico;
    }

    public String getCuentaContable() {
        return cuentaContable;
    }

    public void setCuentaContable(String cuentaContable) {
        this.cuentaContable = cuentaContable;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getCantidad_ov() {
        return cantidad_ov;
    }

    public void setCantidad_ov(int cantidad_ov) {
        this.cantidad_ov = cantidad_ov;
    }

    public String getIndicadorIGV() {
        return indicadorIGV;
    }

    public void setIndicadorIGV(String indicadorIGV) {
        this.indicadorIGV = indicadorIGV;
    }

    public String getCodTienda() {
        return codTienda;
    }

    public void setCodTienda(String codTienda) {
        this.codTienda = codTienda;
    }

    public int getDiasSinMvtoVta() {
        return diasSinMvtoVta;
    }

    public void setDiasSinMvtoVta(int diasSinMvtoVta) {
        this.diasSinMvtoVta = diasSinMvtoVta;
    }

    public double getVvpDsc() {
        return vvpDsc;
    }

    public void setVvpDsc(double vvpDsc) {
        this.vvpDsc = vvpDsc;
    }

    public double getPvp() {
        return pvp;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public int getBoleta() {
        return boleta;
    }

    public void setBoleta(int boleta) {
        this.boleta = boleta;
    }

    public double getVvp() {
        return vvp;
    }

    public void setVvp(double vvp) {
        this.vvp = vvp;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public double getMargen() {
        return margen;
    }

    public void setMargen(double margen) {
        this.margen = margen;
    }

    public double getVvp20() {
        return vvp20;
    }

    public void setVvp20(double vvp20) {
        this.vvp20 = vvp20;
    }

    public double getVvp30() {
        return vvp30;
    }

    public void setVvp30(double vvp30) {
        this.vvp30 = vvp30;
    }

    public double getVvp40() {
        return vvp40;
    }

    public void setVvp40(double vvp40) {
        this.vvp40 = vvp40;
    }

    public double getVvp50() {
        return vvp50;
    }

    public void setVvp50(double vvp50) {
        this.vvp50 = vvp50;
    }

    public double getTotCostoSol() {
        return totCostoSol;
    }

    public void setTotCostoSol(double totCostoSol) {
        this.totCostoSol = totCostoSol;
    }

    public double getTotCostoDol() {
        return totCostoDol;
    }

    public void setTotCostoDol(double totCostoDol) {
        this.totCostoDol = totCostoDol;
    }

    public double getTotDsctoSol() {
        return totDsctoSol;
    }

    public void setTotDsctoSol(double totDsctoSol) {
        this.totDsctoSol = totDsctoSol;
    }

    public double getTotDsctoDol() {
        return totDsctoDol;
    }

    public void setTotDsctoDol(double totDsctoDol) {
        this.totDsctoDol = totDsctoDol;
    }

    public double getTotIgvSol() {
        return totIgvSol;
    }

    public void setTotIgvSol(double totIgvSol) {
        this.totIgvSol = totIgvSol;
    }

    public double getTotIgvDol() {
        return totIgvDol;
    }

    public void setTotIgvDol(double totIgvDol) {
        this.totIgvDol = totIgvDol;
    }

    public double getTotBrutoSol() {
        return totBrutoSol;
    }

    public void setTotBrutoSol(double totBrutoSol) {
        this.totBrutoSol = totBrutoSol;
    }

    public double getTotBrutoDol() {
        return totBrutoDol;
    }

    public void setTotBrutoDol(double totBrutoDol) {
        this.totBrutoDol = totBrutoDol;
    }

    public double getTotVtaDol() {
        return totVtaDol;
    }

    public void setTotVtaDol(double totVtaDol) {
        this.totVtaDol = totVtaDol;
    }

    public double getTotGralVtaDol() {
        return totGralVtaDol;
    }

    public void setTotGralVtaDol(double totGralVtaDol) {
        this.totGralVtaDol = totGralVtaDol;
    }

    public double getTotVtaSol() {
        return totVtaSol;
    }

    public void setTotVtaSol(double totVtaSol) {
        this.totVtaSol = totVtaSol;
    }

    public double getTotGralVtaSol() {
        return totGralVtaSol;
    }

    public void setTotGralVtaSol(double totGralVtaSol) {
        this.totGralVtaSol = totGralVtaSol;
    }

    public int getNumPed() {
        return numPed;
    }

    public void setNumPed(int numPed) {
        this.numPed = numPed;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public int getCant_dev() {
        return cant_dev;
    }

    public void setCant_dev(int cant_dev) {
        this.cant_dev = cant_dev;
    }

    public String getMarcaTDP() {
        return marcaTDP;
    }

    public void setMarcaTDP(String marcaTDP) {
        this.marcaTDP = marcaTDP;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String Codigo) {
        this.Codigo = Codigo;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String Descripcion) {
        this.Descripcion = Descripcion;
    }

    public String getCodProcedencia() {
        return CodProcedencia;
    }

    public void setCodProcedencia(String CodProcedencia) {
        this.CodProcedencia = CodProcedencia;
    }

    public String getCodCampania() {
        return CodCampania;
    }

    public void setCodCampania(String CodCampania) {
        this.CodCampania = CodCampania;
    }

    public double getDsctoProducto() {
        return DsctoProducto;
    }

    public void setDsctoProducto(double DsctoProducto) {
        this.DsctoProducto = DsctoProducto;
    }

    public int getDsctoMO() {
        return DsctoMO;
    }

    public void setDsctoMO(int DsctoMO) {
        this.DsctoMO = DsctoMO;
    }

    public int getDsctoTDP() {
        return DsctoTDP;
    }

    public void setDsctoTDP(int DsctoTDP) {
        this.DsctoTDP = DsctoTDP;
    }

    public int getDsctoGP() {
        return DsctoGP;
    }

    public void setDsctoGP(int DsctoGP) {
        this.DsctoGP = DsctoGP;
    }

    public double getIgv() {
        return Igv;
    }

    public void setIgv(double Igv) {
        this.Igv = Igv;
    }

    public int getStk() {
        return Stk;
    }

    public void setStk(int Stk) {
        this.Stk = Stk;
    }

    public boolean isBolExisPedPen() {
        return BolExisPedPen;
    }

    public void setBolExisPedPen(boolean BolExisPedPen) {
        this.BolExisPedPen = BolExisPedPen;
    }

    public String getTipoDscto() {
        return tipoDscto;
    }

    public void setTipoDscto(String tipoDscto) {
        this.tipoDscto = tipoDscto;
    }

    /*public boolean getBolObsequio() {
        return BolObsequio;
    }

    public void setBolObsequio(boolean BolObsequio) {
        this.BolObsequio = BolObsequio;
    }*/
    public String getEsObsequio() {
        return esObsequio;
    }

    public void setEsObsequio(String esObsequio) {
        this.esObsequio = esObsequio;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public double getCAL_TotBruto_Sol() {
        return CAL_TotBruto_Sol;
    }

    public void setCAL_TotBruto_Sol(double CAL_TotBruto_Sol) {
        this.CAL_TotBruto_Sol = CAL_TotBruto_Sol;
    }

    public double getCAL_TotBruto_Dol() {
        return CAL_TotBruto_Dol;
    }

    public void setCAL_TotBruto_Dol(double CAL_TotBruto_Dol) {
        this.CAL_TotBruto_Dol = CAL_TotBruto_Dol;
    }

    public double getCAL_TotDscto_Sol() {
        return CAL_TotDscto_Sol;
    }

    public void setCAL_TotDscto_Sol(double CAL_TotDscto_Sol) {
        this.CAL_TotDscto_Sol = CAL_TotDscto_Sol;
    }

    public double getCAL_TotDscto_Dol() {
        return CAL_TotDscto_Dol;
    }

    public void setCAL_TotDscto_Dol(double CAL_TotDscto_Dol) {
        this.CAL_TotDscto_Dol = CAL_TotDscto_Dol;
    }

    public double getCAL_TotIGV_Sol() {
        return CAL_TotIGV_Sol;
    }

    public void setCAL_TotIGV_Sol(double CAL_TotIGV_Sol) {
        this.CAL_TotIGV_Sol = CAL_TotIGV_Sol;
    }

    public double getCAL_TotIGV_Dol() {
        return CAL_TotIGV_Dol;
    }

    public void setCAL_TotIGV_Dol(double CAL_TotIGV_Dol) {
        this.CAL_TotIGV_Dol = CAL_TotIGV_Dol;
    }

    public double getCAL_TotVtaSol() {
        return CAL_TotVtaSol;
    }

    public void setCAL_TotVtaSol(double CAL_TotVtaSol) {
        this.CAL_TotVtaSol = CAL_TotVtaSol;
    }

    public double getCAL_TotVtaDol() {
        return CAL_TotVtaDol;
    }

    public void setCAL_TotVtaDol(double CAL_TotVtaDol) {
        this.CAL_TotVtaDol = CAL_TotVtaDol;
    }

    public double getCAL_TotGralVtaSol() {
        return CAL_TotGralVtaSol;
    }

    public void setCAL_TotGralVtaSol(double CAL_TotGralVtaSol) {
        this.CAL_TotGralVtaSol = CAL_TotGralVtaSol;
    }

    public double getCAL_TotGralVtaDol() {
        return CAL_TotGralVtaDol;
    }

    public void setCAL_TotGralVtaDol(double CAL_TotGralVtaDol) {
        this.CAL_TotGralVtaDol = CAL_TotGralVtaDol;
    }

    public int getCAL_cantidad() {
        return CAL_cantidad;
    }

    public void setCAL_cantidad(int CAL_cantidad) {
        this.CAL_cantidad = CAL_cantidad;
    }

    public double getCAL_descuento() {
        return CAL_descuento;
    }

    public void setCAL_descuento(double CAL_descuento) {
        this.CAL_descuento = CAL_descuento;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(int secuencia) {
        this.secuencia = secuencia;
    }

    public int getStkSeg() {
        return stkSeg;
    }

    public void setStkSeg(int stkSeg) {
        this.stkSeg = stkSeg;
    }

    public int getStkDis() {
        return StkDis;
    }

    public void setStkDis(int StkDis) {
        this.StkDis = StkDis;
    }

    public int getStkMes() {
        return StkMes;
    }

    public void setStkMes(int StkMes) {
        this.StkMes = StkMes;
    }

    public int getStkTotal() {
        return StkTotal;
    }

    public void setStkTotal(int StkTotal) {
        this.StkTotal = StkTotal;
    }

    public int getStkExt() {
        return StkExt;
    }

    public void setStkExt(int StkExt) {
        this.StkExt = StkExt;
    }

    public int getStkTaller() {
        return StkTaller;
    }

    public void setStkTaller(int StkTaller) {
        this.StkTaller = StkTaller;
    }

    public int getStkAlm() {
        return StkAlm;
    }

    public void setStkAlm(int StkAlm) {
        this.StkAlm = StkAlm;
    }

    public int getStkComprometido() {
        return stkComprometido;
    }

    public void setStkComprometido(int stkComprometido) {
        this.stkComprometido = stkComprometido;
    }

    public double getUltCosto() {
        return UltCosto;
    }

    public void setUltCosto(double UltCosto) {
        this.UltCosto = UltCosto;
    }

    public double getCostoProm() {
        return CostoProm;
    }

    public void setCostoProm(double CostoProm) {
        this.CostoProm = CostoProm;
    }

    public int getSinMoviVenta() {
        return sinMoviVenta;
    }

    public void setSinMoviVenta(int sinMoviVenta) {
        this.sinMoviVenta = sinMoviVenta;
    }

    public int getSinMoviCompra() {
        return sinMoviCompra;
    }

    public void setSinMoviCompra(int sinMoviCompra) {
        this.sinMoviCompra = sinMoviCompra;
    }

    public String getUltFecSal() {
        return UltFecSal;
    }

    public void setUltFecSal(String UltFecSal) {
        this.UltFecSal = UltFecSal;
    }

    public String getUltFecIng() {
        return UltFecIng;
    }

    public void setUltFecIng(String UltFecIng) {
        this.UltFecIng = UltFecIng;
    }

    public String getFecApe() {
        return fecApe;
    }

    public void setFecApe(String fecApe) {
        this.fecApe = fecApe;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public String getInventariado() {
        return inventariado;
    }

    public void setInventariado(String inventariado) {
        this.inventariado = inventariado;
    }

    public int getStkMax() {
        return StkMax;
    }

    public void setStkMax(int StkMax) {
        this.StkMax = StkMax;
    }

    public int getStkMad() {
        return StkMad;
    }

    public void setStkMad(int StkMad) {
        this.StkMad = StkMad;
    }

    public int getStkIni() {
        return StkIni;
    }

    public void setStkIni(int StkIni) {
        this.StkIni = StkIni;
    }

    public int getStkTmp() {
        return StkTmp;
    }

    public void setStkTmp(int StkTmp) {
        this.StkTmp = StkTmp;
    }

    public String getCodigoAlmacen() {
        return codigoAlmacen;
    }

    public void setCodigoAlmacen(String codigoAlmacen) {
        this.codigoAlmacen = codigoAlmacen;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getUbicaParteEnAlmacen() {
        return ubicaParteEnAlmacen;
    }

    public void setUbicaParteEnAlmacen(String ubicaParteEnAlmacen) {
        this.ubicaParteEnAlmacen = ubicaParteEnAlmacen;
    }

    public int getStockParteEnAlmacen() {
        return stockParteEnAlmacen;
    }

    public void setStockParteEnAlmacen(int stockParteEnAlmacen) {
        this.stockParteEnAlmacen = stockParteEnAlmacen;
    }

    public String getTipoProducto() {
        return tipoProducto;
    }

    public void setTipoProducto(String tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getVVPSol() {
        return VVPSol;
    }

    public void setVVPSol(double VVPSol) {
        this.VVPSol = VVPSol;
    }

    public double getVVDSol() {
        return VVDSol;
    }

    public void setVVDSol(double VVDSol) {
        this.VVDSol = VVDSol;
    }

    public double getVVPDol() {
        return VVPDol;
    }

    public void setVVPDol(double VVPDol) {
        this.VVPDol = VVPDol;
    }

    public double getVVDDol() {
        return VVDDol;
    }

    public void setVVDDol(double VVDDol) {
        this.VVDDol = VVDDol;
    }

    public double getDsctoManoObra() {
        return DsctoManoObra;
    }

    public void setDsctoManoObra(double DsctoManoObra) {
        this.DsctoManoObra = DsctoManoObra;
    }

    public double getDsctoTaller() {
        return DsctoTaller;
    }

    public void setDsctoTaller(double DsctoTaller) {
        this.DsctoTaller = DsctoTaller;
    }

    public double getDsctoMostrador() {
        return DsctoMostrador;
    }

    public void setDsctoMostrador(double DsctoMostrador) {
        this.DsctoMostrador = DsctoMostrador;
    }

    public double getDsctoOtros() {
        return DsctoOtros;
    }

    public void setDsctoOtros(double DsctoOtros) {
        this.DsctoOtros = DsctoOtros;
    }

    public double getDsctoOT() {
        return DsctoOT;
    }

    public void setDsctoOT(double DsctoOT) {
        this.DsctoOT = DsctoOT;
    }

    public double getDsctoCiaSeguro() {
        return DsctoCiaSeguro;
    }

    public void setDsctoCiaSeguro(double DsctoCiaSeguro) {
        this.DsctoCiaSeguro = DsctoCiaSeguro;
    }

    public double getDsctoMaximo() {
        return DsctoMaximo;
    }

    public void setDsctoMaximo(double DsctoMaximo) {
        this.DsctoMaximo = DsctoMaximo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getCodigoMarca() {
        return codigoMarca;
    }

    public void setCodigoMarca(String codigoMarca) {
        this.codigoMarca = codigoMarca;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public String getTipoFactor() {
        return tipoFactor;
    }

    public void setTipoFactor(String tipoFactor) {
        this.tipoFactor = tipoFactor;
    }

    public String getClaseProducto() {
        return claseProducto;
    }

    public void setClaseProducto(String claseProducto) {
        this.claseProducto = claseProducto;
    }

    public int getStkMin() {
        return StkMin;
    }

    public void setStkMin(int StkMin) {
        this.StkMin = StkMin;
    }

    public int getMesesSinMvtoVta() {
        return mesesSinMvtoVta;
    }

    public void setMesesSinMvtoVta(int mesesSinMvtoVta) {
        this.mesesSinMvtoVta = mesesSinMvtoVta;
    }

    public int getMesesSinMvtoComp() {
        return mesesSinMvtoComp;
    }

    public void setMesesSinMvtoComp(int mesesSinMvtoComp) {
        this.mesesSinMvtoComp = mesesSinMvtoComp;
    }

    public String getClaseCompra() {
        return claseCompra;
    }

    public void setClaseCompra(String claseCompra) {
        this.claseCompra = claseCompra;
    }

    public String getClaseVenta() {
        return claseVenta;
    }

    public void setClaseVenta(String claseVenta) {
        this.claseVenta = claseVenta;
    }

    public String getCodigoEmpresa() {
        return codigoEmpresa;
    }

    public void setCodigoEmpresa(String codigoEmpresa) {
        this.codigoEmpresa = codigoEmpresa;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public int getVentas() {
        return ventas;
    }

    public void setVentas(int ventas) {
        this.ventas = ventas;
    }

    public int getAntMovCompra() {
        return antMovCompra;
    }

    public void setAntMovCompra(int antMovCompra) {
        this.antMovCompra = antMovCompra;
    }

    public int getAntMovVenta() {
        return antMovVenta;
    }

    public void setAntMovVenta(int antMovVenta) {
        this.antMovVenta = antMovVenta;
    }

    public double getCostoPromedio() {
        return costoPromedio;
    }

    public void setCostoPromedio(double costoPromedio) {
        this.costoPromedio = costoPromedio;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public double getCostoUnitario() {
        return costoUnitario;
    }

    public void setCostoUnitario(double costoUnitario) {
        this.costoUnitario = costoUnitario;
    }

    public double getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(double costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getAccImp() {
        return AccImp;
    }

    public void setAccImp(String AccImp) {
        this.AccImp = AccImp;
    }

    public String getObservado() {
        return Observado;
    }

    public void setObservado(String Observado) {
        this.Observado = Observado;
    }

    public int getVenta1M() {
        return Venta1M;
    }

    public void setVenta1M(int Venta1M) {
        this.Venta1M = Venta1M;
    }

    public int getVenta2M() {
        return Venta2M;
    }

    public void setVenta2M(int Venta2M) {
        this.Venta2M = Venta2M;
    }

    public int getVenta3M() {
        return Venta3M;
    }

    public void setVenta3M(int Venta3M) {
        this.Venta3M = Venta3M;
    }

    public int getVenta4M() {
        return Venta4M;
    }

    public void setVenta4M(int Venta4M) {
        this.Venta4M = Venta4M;
    }

    public int getComMes() {
        return ComMes;
    }

    public void setComMes(int ComMes) {
        this.ComMes = ComMes;
    }

    public int getVtaMes() {
        return VtaMes;
    }

    public void setVtaMes(int VtaMes) {
        this.VtaMes = VtaMes;
    }

    public int getStkTdp() {
        return StkTdp;
    }

    public void setStkTdp(int StkTdp) {
        this.StkTdp = StkTdp;
    }

    public int getVenta5M() {
        return Venta5M;
    }

    public void setVenta5M(int Venta5M) {
        this.Venta5M = Venta5M;
    }

    public int getVenta6M() {
        return Venta6M;
    }

    public void setVenta6M(int Venta6M) {
        this.Venta6M = Venta6M;
    }

    public long getNroOC() {
        return NroOC;
    }

    public void setNroOC(long NroOC) {
        this.NroOC = NroOC;
    }

    public String getFecOC() {
        return FecOC;
    }

    public void setFecOC(String FecOC) {
        this.FecOC = FecOC;
    }

    public String getPedPendiente() {
        return PedPendiente;
    }

    public void setPedPendiente(String PedPendiente) {
        this.PedPendiente = PedPendiente;
    }

    public String getSepPendiente() {
        return SepPendiente;
    }

    public void setSepPendiente(String SepPendiente) {
        this.SepPendiente = SepPendiente;
    }

    public int getStkDisponible() {
        return StkDisponible;
    }

    public void setStkDisponible(int StkDisponible) {
        this.StkDisponible = StkDisponible;
    }
}
