package pe.com.gp.entity;

import java.io.Serializable;

public class ProformaRepuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigoRepuesto;
    private String descripcion;
    private Integer cantidad;
    private double descuento;
    private double porcentajeIGV;
    private double valorVentaS;
    private double valorVentaDsctoS;
    private double valorVentaD;
    private double valorVentaDsctoD;
    // totales soles
    private double totalBrutoS;
    private double totalDescuentoS;
    private double totalNetoS;
    private double totalIgvS;
    private double totalGeneralS;
    // totales dolares
    private double totalBrutoD;
    private double totalDescuentoD;
    private double totalNetoD;
    private double totalIgvD;
    private double totalGeneralD;

    public ProformaRepuestos() {
    }

    public String getCodigoRepuesto() {
        return codigoRepuesto;
    }

    public void setCodigoRepuesto(String codigoRepuesto) {
        this.codigoRepuesto = codigoRepuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getPorcentajeIGV() {
        return porcentajeIGV;
    }

    public void setPorcentajeIGV(double porcentajeIGV) {
        this.porcentajeIGV = porcentajeIGV;
    }

    public double getValorVentaS() {
        return valorVentaS;
    }

    public void setValorVentaS(double valorVentaS) {
        this.valorVentaS = valorVentaS;
    }

    public double getValorVentaDsctoS() {
        return valorVentaDsctoS;
    }

    public void setValorVentaDsctoS(double valorVentaDsctoS) {
        this.valorVentaDsctoS = valorVentaDsctoS;
    }

    public double getValorVentaD() {
        return valorVentaD;
    }

    public void setValorVentaD(double valorVentaD) {
        this.valorVentaD = valorVentaD;
    }

    public double getValorVentaDsctoD() {
        return valorVentaDsctoD;
    }

    public void setValorVentaDsctoD(double valorVentaDsctoD) {
        this.valorVentaDsctoD = valorVentaDsctoD;
    }

    public double getTotalBrutoS() {
        return totalBrutoS;
    }

    public void setTotalBrutoS(double totalBrutoS) {
        this.totalBrutoS = totalBrutoS;
    }

    public double getTotalDescuentoS() {
        return totalDescuentoS;
    }

    public void setTotalDescuentoS(double totalDescuentoS) {
        this.totalDescuentoS = totalDescuentoS;
    }

    public double getTotalNetoS() {
        return totalNetoS;
    }

    public void setTotalNetoS(double totalNetoS) {
        this.totalNetoS = totalNetoS;
    }

    public double getTotalIgvS() {
        return totalIgvS;
    }

    public void setTotalIgvS(double totalIgvS) {
        this.totalIgvS = totalIgvS;
    }

    public double getTotalGeneralS() {
        return totalGeneralS;
    }

    public void setTotalGeneralS(double totalGeneralS) {
        this.totalGeneralS = totalGeneralS;
    }

    public double getTotalBrutoD() {
        return totalBrutoD;
    }

    public void setTotalBrutoD(double totalBrutoD) {
        this.totalBrutoD = totalBrutoD;
    }

    public double getTotalDescuentoD() {
        return totalDescuentoD;
    }

    public void setTotalDescuentoD(double totalDescuentoD) {
        this.totalDescuentoD = totalDescuentoD;
    }

    public double getTotalNetoD() {
        return totalNetoD;
    }

    public void setTotalNetoD(double totalNetoD) {
        this.totalNetoD = totalNetoD;
    }

    public double getTotalIgvD() {
        return totalIgvD;
    }

    public void setTotalIgvD(double totalIgvD) {
        this.totalIgvD = totalIgvD;
    }

    public double getTotalGeneralD() {
        return totalGeneralD;
    }

    public void setTotalGeneralD(double totalGeneralD) {
        this.totalGeneralD = totalGeneralD;
    }
}
