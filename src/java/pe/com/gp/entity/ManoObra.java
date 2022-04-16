package pe.com.gp.entity;

import java.io.Serializable;

public class ManoObra implements Serializable {

    private static final long serialVersionUID = 1L;

    private String descripcion2 = "";
    private String descripcion3 = "";
    private String descripcion4 = "";

    //-- Datos Calculados
    private double CAL_TotBruto_Sol;// NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotBruto_Dol;// NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotDscto_Sol; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotDscto_Dol; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotIGV_Sol; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotIGV_Dol; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotVtaSol; // Neto // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotVtaDol; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotGralVtaSol; // Total Gral // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private double CAL_TotGralVtaDol; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private String accion;
    private String tipo = "S"; // POR FAVOR NO INICIALIZAR NADA PARA PODER USAR EN OTROS MODULOS
    //----
    private String tipoDocumento;
    private String documento;
    private String ordenCompra;
    private int numDocumento;
    private int numOrdenCompra;
    private Integer CAL_cantidad; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private Double CAL_descuento; // NO USAR ESTE TIPO DE NOMENCLATURA DE VARIABLES, USAR camelCase
    private Double vvp;
    private Double vvpDsc;
    private Double pvp;
    private String rea;

    private String codigo;
    private String descripcion;
    private double vvpSol;
    private double vvpDol;
    private String fecApe;
    private String anulado;
    private double costoSol;
    private double costoDol;
    private double costo;
    private Double igv;
    private int cantidad;
    private double descuento;
    private String realizado;
    private String moneda;
    private String monedaSimbolo;
    private int item;
    private long secuencia;
    private String codMecanico;
    private double horaMatriz;
    private double precioMatriz;
    private double frecuenciaMatriz;

    // Inicio SAP
    private String moTipo;
    private String codProveedor;
    // Fin SAP

    private boolean existe;

    public ManoObra() {
    }

    public String getCodProveedor() {
        return codProveedor;
    }

    public void setCodProveedor(String codProveedor) {
        this.codProveedor = codProveedor;
    }

    public String getMoTipo() {
        return moTipo;
    }

    public void setMoTipo(String moTipo) {
        this.moTipo = moTipo;
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

    public String getCodMecanico() {
        return codMecanico;
    }

    public void setCodMecanico(String codMecanico) {
        this.codMecanico = codMecanico;
    }

    public String getMonedaSimbolo() {
        return monedaSimbolo;
    }

    public void setMonedaSimbolo(String monedaSimbolo) {
        this.monedaSimbolo = monedaSimbolo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public int getNumDocumento() {
        return numDocumento;
    }

    public void setNumDocumento(int numDocumento) {
        this.numDocumento = numDocumento;
    }

    public int getNumOrdenCompra() {
        return numOrdenCompra;
    }

    public void setNumOrdenCompra(int numOrdenCompra) {
        this.numOrdenCompra = numOrdenCompra;
    }

    public String getDescripcion2() {
        return descripcion2;
    }

    public void setDescripcion2(String descripcion2) {
        this.descripcion2 = descripcion2;
    }

    public String getDescripcion3() {
        return descripcion3;
    }

    public void setDescripcion3(String descripcion3) {
        this.descripcion3 = descripcion3;
    }

    public String getDescripcion4() {
        return descripcion4;
    }

    public void setDescripcion4(String descripcion4) {
        this.descripcion4 = descripcion4;
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

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getOrdenCompra() {
        return ordenCompra;
    }

    public void setOrdenCompra(String ordenCompra) {
        this.ordenCompra = ordenCompra;
    }

    public Integer getCAL_cantidad() {
        return CAL_cantidad;
    }

    public void setCAL_cantidad(Integer CAL_cantidad) {
        this.CAL_cantidad = CAL_cantidad;
    }

    public Double getCAL_descuento() {
        return CAL_descuento;
    }

    public void setCAL_descuento(Double CAL_descuento) {
        this.CAL_descuento = CAL_descuento;
    }

    public Double getVvp() {
        return vvp;
    }

    public void setVvp(Double vvp) {
        this.vvp = vvp;
    }

    public Double getVvpDsc() {
        return vvpDsc;
    }

    public void setVvpDsc(Double vvpDsc) {
        this.vvpDsc = vvpDsc;
    }

    public Double getPvp() {
        return pvp;
    }

    public void setPvp(Double pvp) {
        this.pvp = pvp;
    }

    public String getRea() {
        return rea;
    }

    public void setRea(String rea) {
        this.rea = rea;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getVvpSol() {
        return vvpSol;
    }

    public void setVvpSol(double vvpSol) {
        this.vvpSol = vvpSol;
    }

    public double getVvpDol() {
        return vvpDol;
    }

    public void setVvpDol(double vvpDol) {
        this.vvpDol = vvpDol;
    }

    public String getFecApe() {
        return fecApe;
    }

    public void setFecApe(String fecApe) {
        this.fecApe = fecApe;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public double getCostoSol() {
        return costoSol;
    }

    public void setCostoSol(double costoSol) {
        this.costoSol = costoSol;
    }

    public double getCostoDol() {
        return costoDol;
    }

    public void setCostoDol(double costoDol) {
        this.costoDol = costoDol;
    }

    public Double getIgv() {
        return igv;
    }

    public void setIgv(Double igv) {
        this.igv = igv;
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

    public String getRealizado() {
        return realizado;
    }

    public void setRealizado(String realizado) {
        this.realizado = realizado;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public long getSecuencia() {
        return secuencia;
    }

    public void setSecuencia(long secuencia) {
        this.secuencia = secuencia;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
