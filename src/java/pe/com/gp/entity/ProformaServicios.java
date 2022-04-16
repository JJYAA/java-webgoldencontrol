package pe.com.gp.entity;

import java.io.Serializable;

public class ProformaServicios implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigoMO;
    private String descripcion1;
    private String descripcion2;
    private String descripcion3;
    private String descripcion4;
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

    public ProformaServicios() {
    }

    public String getCodigoMO() {
        return codigoMO;
    }

    public void setCodigoMO(String codigoMO) {
        this.codigoMO = codigoMO;
    }

    public String getDescripcion1() {
        return descripcion1;
    }

    public void setDescripcion1(String descripcion1) {
        this.descripcion1 = descripcion1;
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
