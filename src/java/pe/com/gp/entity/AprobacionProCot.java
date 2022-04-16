package pe.com.gp.entity;

import java.io.Serializable;

public class AprobacionProCot implements Serializable {

    static final long serialVersionUID = 1L;
    private String tipo;
    private long numero;
    private String modVta;
    private String cliente;
    private String modelo;
    private double totalOpe;
    private String vendedor;
    private String estado;
    private String impresora;
    private String email;
    private String fax;
    private String moneda;
    private double tipoCambio;
    private double saldoOpe;
    private double ultCosVeh;
    private double accesorios;
    private double marginBack;
    private double campana;
    private double nCampana;
    //private double incentivo;
    private double acuerdo;
    private double abono;
    private double margenCal;
    private double margenGP1;
    private double margenGP2;
    private String visadoGP1;
    private String visadoGP2;
    private String procesar;
    private String notificar;
    private String codModVeh;
    private double dsctoComision;
    private double dsctoIncAsesor;
    private boolean existe;

    public AprobacionProCot() {
    }

    public double getDsctoComision() {
        return dsctoComision;
    }

    public void setDsctoComision(double dsctoComision) {
        this.dsctoComision = dsctoComision;
    }

    public double getDsctoIncAsesor() {
        return dsctoIncAsesor;
    }

    public void setDsctoIncAsesor(double dsctoIncAsesor) {
        this.dsctoIncAsesor = dsctoIncAsesor;
    }

    public String getCodModVeh() {
        return codModVeh;
    }

    public void setCodModVeh(String codModVeh) {
        this.codModVeh = codModVeh;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public String getModVta() {
        return modVta;
    }

    public void setModVta(String modVta) {
        this.modVta = modVta;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public double getTotalOpe() {
        return totalOpe;
    }

    public void setTotalOpe(double totalOpe) {
        this.totalOpe = totalOpe;
    }

    public String getVendedor() {
        return vendedor;
    }

    public void setVendedor(String vendedor) {
        this.vendedor = vendedor;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getImpresora() {
        return impresora;
    }

    public void setImpresora(String impresora) {
        this.impresora = impresora;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public double getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(double tipoCambio) {
        this.tipoCambio = tipoCambio;
    }

    public double getSaldoOpe() {
        return saldoOpe;
    }

    public void setSaldoOpe(double saldoOpe) {
        this.saldoOpe = saldoOpe;
    }

    public double getUltCosVeh() {
        return ultCosVeh;
    }

    public void setUltCosVeh(double ultCosVeh) {
        this.ultCosVeh = ultCosVeh;
    }

    public double getAccesorios() {
        return accesorios;
    }

    public void setAccesorios(double accesorios) {
        this.accesorios = accesorios;
    }

    public double getMarginBack() {
        return marginBack;
    }

    public void setMarginBack(double marginBack) {
        this.marginBack = marginBack;
    }

    public double getCampana() {
        return campana;
    }

    public void setCampana(double campana) {
        this.campana = campana;
    }

    public double getnCampana() {
        return nCampana;
    }

    public void setnCampana(double nCampana) {
        this.nCampana = nCampana;
    }

    /*public double getIncentivo() {
        return incentivo;
    }

    public void setIncentivo(double incentivo) {
        this.incentivo = incentivo;
    }*/

    public double getAcuerdo() {
        return acuerdo;
    }

    public void setAcuerdo(double acuerdo) {
        this.acuerdo = acuerdo;
    }

    public double getAbono() {
        return abono;
    }

    public void setAbono(double abono) {
        this.abono = abono;
    }

    public double getMargenCal() {
        return margenCal;
    }

    public void setMargenCal(double margenCal) {
        this.margenCal = margenCal;
    }

    public double getMargenGP1() {
        return margenGP1;
    }

    public void setMargenGP1(double margenGP1) {
        this.margenGP1 = margenGP1;
    }

    public double getMargenGP2() {
        return margenGP2;
    }

    public void setMargenGP2(double margenGP2) {
        this.margenGP2 = margenGP2;
    }

    public String getVisadoGP1() {
        return visadoGP1;
    }

    public void setVisadoGP1(String visadoGP1) {
        this.visadoGP1 = visadoGP1;
    }

    public String getVisadoGP2() {
        return visadoGP2;
    }

    public void setVisadoGP2(String visadoGP2) {
        this.visadoGP2 = visadoGP2;
    }

    public String getProcesar() {
        return procesar;
    }

    public void setProcesar(String procesar) {
        this.procesar = procesar;
    }

    public String getNotificar() {
        return notificar;
    }

    public void setNotificar(String notificar) {
        this.notificar = notificar;
    }

}
