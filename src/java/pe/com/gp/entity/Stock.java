package pe.com.gp.entity;

import java.io.Serializable;

public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;
    private String repuestos = null;
    private String descripcion = null;
    private String estado = null;
    private int disponible = 0;
    private int total = 0;
    private int taller = 0;
    private String almacen = null;

    public Stock() {
    }

    public String getRepuestos() {
        return repuestos;
    }

    public void setRepuestos(String repuestos) {
        this.repuestos = repuestos;
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

    public int getDisponible() {
        return disponible;
    }

    public void setDisponible(int disponible) {
        this.disponible = disponible;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTaller() {
        return taller;
    }

    public void setTaller(int taller) {
        this.taller = taller;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }
}
