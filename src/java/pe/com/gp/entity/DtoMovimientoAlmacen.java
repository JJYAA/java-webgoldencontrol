package pe.com.gp.entity;

import java.io.Serializable;

public class DtoMovimientoAlmacen implements Serializable {

    static final long serialVersionUID = 1L;
    private String nombre;
    private long numeroBoleta;
    private long nroOC;
    private String tipoRef;
    private long documentoRef;
    private int serieProveedor;
    private long facturaProveedor;
    private String nombreCliente;
    private String codigoUsuario;
    private String nombreUsuario;
    private String fechaGeneracion;
    private String fechaAceptacion;
    private String tipoDevolucion;
    private long numeroDevolucion;
    private String almacenOrigen;
    private String almacenDestino;
    private long guiaRemision;
    private String codigoCliente;
    private String existeBoleta;

    private String codigoProducto;
    private String descripcionProducto;
    private double costo;
    private double total;

    private long numeroDocumento;
    private String claseDocumento;
    private String fecha;
    private String empleado;
    private int stockAnterior;
    private int stockPosterior;
    private int cantidad;
    private int stockTaller;
    private long nroOT;
    private String tipo;
    private String documento;
    private String fechaFacturacion;
    private String aceptado;
    private String cocr; // contado o credito

    public DtoMovimientoAlmacen() {
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public long getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(long numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public long getNroOC() {
        return nroOC;
    }

    public void setNroOC(long nroOC) {
        this.nroOC = nroOC;
    }

    public String getTipoRef() {
        return tipoRef;
    }

    public void setTipoRef(String tipoRef) {
        this.tipoRef = tipoRef;
    }

    public long getDocumentoRef() {
        return documentoRef;
    }

    public void setDocumentoRef(long documentoRef) {
        this.documentoRef = documentoRef;
    }

    public int getSerieProveedor() {
        return serieProveedor;
    }

    public void setSerieProveedor(int serieProveedor) {
        this.serieProveedor = serieProveedor;
    }

    public long getFacturaProveedor() {
        return facturaProveedor;
    }

    public void setFacturaProveedor(long facturaProveedor) {
        this.facturaProveedor = facturaProveedor;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCodigoUsuario() {
        return codigoUsuario;
    }

    public void setCodigoUsuario(String codigoUsuario) {
        this.codigoUsuario = codigoUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(String fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public String getTipoDevolucion() {
        return tipoDevolucion;
    }

    public void setTipoDevolucion(String tipoDevolucion) {
        this.tipoDevolucion = tipoDevolucion;
    }

    public long getNumeroDevolucion() {
        return numeroDevolucion;
    }

    public void setNumeroDevolucion(long numeroDevolucion) {
        this.numeroDevolucion = numeroDevolucion;
    }

    public String getAlmacenOrigen() {
        return almacenOrigen;
    }

    public void setAlmacenOrigen(String almacenOrigen) {
        this.almacenOrigen = almacenOrigen;
    }

    public String getAlmacenDestino() {
        return almacenDestino;
    }

    public void setAlmacenDestino(String almacenDestino) {
        this.almacenDestino = almacenDestino;
    }

    public long getGuiaRemision() {
        return guiaRemision;
    }

    public void setGuiaRemision(long guiaRemision) {
        this.guiaRemision = guiaRemision;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getExisteBoleta() {
        return existeBoleta;
    }

    public void setExisteBoleta(String existeBoleta) {
        this.existeBoleta = existeBoleta;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getDescripcionProducto() {
        return descripcionProducto;
    }

    public void setDescripcionProducto(String descripcionProducto) {
        this.descripcionProducto = descripcionProducto;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getClaseDocumento() {
        return claseDocumento;
    }

    public void setClaseDocumento(String claseDocumento) {
        this.claseDocumento = claseDocumento;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public int getStockAnterior() {
        return stockAnterior;
    }

    public void setStockAnterior(int stockAnterior) {
        this.stockAnterior = stockAnterior;
    }

    public int getStockPosterior() {
        return stockPosterior;
    }

    public void setStockPosterior(int stockPosterior) {
        this.stockPosterior = stockPosterior;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getStockTaller() {
        return stockTaller;
    }

    public void setStockTaller(int stockTaller) {
        this.stockTaller = stockTaller;
    }

    public long getNroOT() {
        return nroOT;
    }

    public void setNroOT(long nroOT) {
        this.nroOT = nroOT;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getFechaFacturacion() {
        return fechaFacturacion;
    }

    public void setFechaFacturacion(String fechaFacturacion) {
        this.fechaFacturacion = fechaFacturacion;
    }

    public String getAceptado() {
        return aceptado;
    }

    public void setAceptado(String aceptado) {
        this.aceptado = aceptado;
    }

    public String getCocr() {
        return cocr;
    }

    public void setCocr(String cocr) {
        this.cocr = cocr;
    }

}
