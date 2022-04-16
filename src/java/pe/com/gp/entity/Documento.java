package pe.com.gp.entity;

import java.io.Serializable;

public class Documento implements Serializable {

    static final long serialVersionUID = 1L;

    String tienda;
    String tipoDocumento;
    String tipoDocumentoStr;
    String documento;
    String formaPago;
    String moneda;
    String codigo;
    String nombre;
    String fecha;
    double venta;
    double descuento;
    double neto;
    double igv;
    double total;
    String tipoVenta;
    String direccion;
    String actividad;
    String migrado;
    int cantidad_migrada;
    String caja;
    String operacion;
    String estado;
    String mtv;
    String mesv;
    String c_c_tipo_operacion;
    String vou;
    String anho;

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }
    
    

    public String getMtv() {
        return mtv;
    }

    public void setMtv(String mtv) {
        this.mtv = mtv;
    }

    public String getMesv() {
        return mesv;
    }

    public void setMesv(String mesv) {
        this.mesv = mesv;
    }

    public String getC_c_tipo_operacion() {
        return c_c_tipo_operacion;
    }

    public void setC_c_tipo_operacion(String c_c_tipo_operacion) {
        this.c_c_tipo_operacion = c_c_tipo_operacion;
    }

    public String getVou() {
        return vou;
    }

    public void setVou(String vou) {
        this.vou = vou;
    }
    
    
    
    
    
    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    public String getCaja() {
        return caja;
    }

    public void setCaja(String caja) {
        this.caja = caja;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    
    public int getCantidad_migrada() {
        return cantidad_migrada;
    }

    public void setCantidad_migrada(int cantidad_migrada) {
        this.cantidad_migrada = cantidad_migrada;
    }
    
    
    

    public String getTipoDocumentoStr() {
        return tipoDocumentoStr;
    }

    public void setTipoDocumentoStr(String tipoDocumentoStr) {
        this.tipoDocumentoStr = tipoDocumentoStr;
    }

    public String getMigrado() {
        return migrado;
    }

    public void setMigrado(String migrado) {
        this.migrado = migrado;
    }
    
    

    public double getVenta() {
        return venta;
    }

    public void setVenta(double venta) {
        this.venta = venta;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getNeto() {
        return neto;
    }

    public void setNeto(double neto) {
        this.neto = neto;
    }

    public double getIgv() {
        return igv;
    }

    public void setIgv(double igv) {
        this.igv = igv;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }
    

    
    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
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

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    
    public String getTipoVenta() {
        return tipoVenta;
    }

    public void setTipoVenta(String tipoVenta) {
        this.tipoVenta = tipoVenta;
    }
    
    

}
