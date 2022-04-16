package pe.com.gp.entity;

import java.io.Serializable;

public class ListaRepuesto implements Serializable {

    private static final long serialVersionUID = 1L;
    private String repuesto = null;
    private String descripcion = null;
    private String estado = null;
    private String cantidad = null;
    private String tipo_desc = null;
    private String porc_desc = "";
    private double descuento = 0;

    public ListaRepuesto() {
    }

    public String getTipo_desc() {
        return tipo_desc;
    }

    public void setTipo_desc(String tipo_desc) {
        this.tipo_desc = tipo_desc;
    }

    public String getPorc_desc() {
        return porc_desc;
    }

    public void setPorc_desc(String porc_desc) {
        this.porc_desc = porc_desc;
    }

    public String getRepuesto() {
        return repuesto;
    }

    public void setRepuesto(String repuesto) {
        this.repuesto = repuesto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }
}
