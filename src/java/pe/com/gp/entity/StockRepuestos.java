package pe.com.gp.entity;

import java.io.Serializable;

public class StockRepuestos implements Serializable {

    private static final long serialVersionUID = 1L;
    private String codigoProducto;
    private String reemplazo;
    private String descripcion;
    private String ubicacion;
    private String tipoDescuento;
    private String stockDisponible;
    private String stockTotal;
    private String stockSeguridad;
    private String pendiente;
    private String comprometido;
    private String disponibleAlmacen;
    private String stockAlm;
    private String stockSeg;
    private String stockCom;
    private String ventaMes;
    private Integer ventaM1;
    private Integer ventaM2;
    private Integer ventaM3;
    private Integer ventaM4;
    private Integer ventaM5;
    private Integer ventaM6;
    private String stockTDP;
    private String mesStock;
    private String vvpSoles;
    private String vvdSoles;
    private Integer movVta;
    private Integer nroDias;
    private Integer stockPA;
    private Integer stockGR;
    private Integer stockGT;
    private Integer stockSQ;
    private Integer stockCT;
    private Integer stockMM;
    private Integer stockPI;
    private Integer stockGV;
    private Integer stockGS;
    private Integer stockGL;
    private Integer stockHI;
    private Integer stockAR;
    private Integer stockTM;
    private Integer salidaMes;
    private String fechaUltimaSalida;
    private String fechaUltimoIngreso;
    private String almacen;
    private Integer stockA1;
    private Integer stockA2;
    private String ubicacionA1;
    private String ubicacionA2;
    private Integer salidaAcumuladaAnio;
    private Long cantidad;
    private String responsable;
    private String fechaGeneracion;
    private String tipo;
    private String documento;
    private String popEmpresa;
    private String popAlmacen;
    private Integer popStockDisponible;
    private String popUbicacion;
    private String marca;
    private double ultCosPro;
    private double ultCosto;
    private String codProducto;
    private Integer stockTemporal;
    private Integer cajas;
    private Integer cantidadCajas;
    private Long cantidadDev;
    private Long controlCajas;
    private Long control;
    private Long entregados;

    public Long getControl() {
        return control;
    }

    public void setControl(Long control) {
        this.control = control;
    }

    
    
    public Long getControlCajas() {
        return controlCajas;
    }

    public void setControlCajas(Long controlCajas) {
        this.controlCajas = controlCajas;
    }
    
    

    public Long getEntregados() {
        return entregados;
    }

    public void setEntregados(Long entregados) {
        this.entregados = entregados;
    }
    
    
    public Long getCantidadDev() {
        return cantidadDev;
    }

    public void setCantidadDev(Long cantidadDev) {
        this.cantidadDev = cantidadDev;
    }
    
    

    public Integer getCajas() {
        return cajas;
    }

    public void setCajas(Integer cajas) {
        this.cajas = cajas;
    }

    public Integer getCantidadCajas() {
        return cantidadCajas;
    }

    public void setCantidadCajas(Integer cantidadCajas) {
        this.cantidadCajas = cantidadCajas;
    }
    
    

    public Integer getStockTemporal() {
        return stockTemporal;
    }

    public void setStockTemporal(Integer stockTemporal) {
        this.stockTemporal = stockTemporal;
    }
    

    public String getStockSeguridad() {
        return stockSeguridad;
    }

    public void setStockSeguridad(String stockSeguridad) {
        this.stockSeguridad = stockSeguridad;
    }
    

    public String getStockTotal() {
        return stockTotal;
    }

    public void setStockTotal(String stockTotal) {
        this.stockTotal = stockTotal;
    }

    public StockRepuestos() {
    }

    public String getCodProducto() {
        return codProducto;
    }

    public void setCodProducto(String codProducto) {
        this.codProducto = codProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getUltCosPro() {
        return ultCosPro;
    }

    public void setUltCosPro(double ultCosPro) {
        this.ultCosPro = ultCosPro;
    }

    public double getUltCosto() {
        return ultCosto;
    }

    public void setUltCosto(double ultCosto) {
        this.ultCosto = ultCosto;
    }

    public String getPopEmpresa() {
        return popEmpresa;
    }

    public void setPopEmpresa(String popEmpresa) {
        this.popEmpresa = popEmpresa;
    }

    public String getPopAlmacen() {
        return popAlmacen;
    }

    public void setPopAlmacen(String popAlmacen) {
        this.popAlmacen = popAlmacen;
    }

    public Integer getPopStockDisponible() {
        return popStockDisponible;
    }

    public void setPopStockDisponible(Integer popStockDisponible) {
        this.popStockDisponible = popStockDisponible;
    }

    public String getPopUbicacion() {
        return popUbicacion;
    }

    public void setPopUbicacion(String popUbicacion) {
        this.popUbicacion = popUbicacion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
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

    public Integer getSalidaAcumuladaAnio() {
        return salidaAcumuladaAnio;
    }

    public void setSalidaAcumuladaAnio(Integer salidaAcumuladaAnio) {
        this.salidaAcumuladaAnio = salidaAcumuladaAnio;
    }

    public Integer getStockA1() {
        return stockA1;
    }

    public void setStockA1(Integer stockA1) {
        this.stockA1 = stockA1;
    }

    public Integer getStockA2() {
        return stockA2;
    }

    public void setStockA2(Integer stockA2) {
        this.stockA2 = stockA2;
    }

    public String getUbicacionA1() {
        return ubicacionA1;
    }

    public void setUbicacionA1(String ubicacionA1) {
        this.ubicacionA1 = ubicacionA1;
    }

    public String getUbicacionA2() {
        return ubicacionA2;
    }

    public void setUbicacionA2(String ubicacionA2) {
        this.ubicacionA2 = ubicacionA2;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getVvdSoles() {
        return vvdSoles;
    }

    public void setVvdSoles(String vvdSoles) {
        this.vvdSoles = vvdSoles;
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

    public Integer getSalidaMes() {
        return salidaMes;
    }

    public void setSalidaMes(Integer salidaMes) {
        this.salidaMes = salidaMes;
    }

    public String getCodigoProducto() {
        return codigoProducto;
    }

    public void setCodigoProducto(String codigoProducto) {
        this.codigoProducto = codigoProducto;
    }

    public String getReemplazo() {
        return reemplazo;
    }

    public void setReemplazo(String reemplazo) {
        this.reemplazo = reemplazo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public String getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(String stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    public String getPendiente() {
        return pendiente;
    }

    public void setPendiente(String pendiente) {
        this.pendiente = pendiente;
    }

    public String getComprometido() {
        return comprometido;
    }

    public void setComprometido(String comprometido) {
        this.comprometido = comprometido;
    }

    public String getDisponibleAlmacen() {
        return disponibleAlmacen;
    }

    public void setDisponibleAlmacen(String disponibleAlmacen) {
        this.disponibleAlmacen = disponibleAlmacen;
    }

    public String getStockAlm() {
        return stockAlm;
    }

    public void setStockAlm(String stockAlm) {
        this.stockAlm = stockAlm;
    }

    public String getStockSeg() {
        return stockSeg;
    }

    public void setStockSeg(String stockSeg) {
        this.stockSeg = stockSeg;
    }

    public String getStockCom() {
        return stockCom;
    }

    public void setStockCom(String stockCom) {
        this.stockCom = stockCom;
    }

    public String getVentaMes() {
        return ventaMes;
    }

    public void setVentaMes(String ventaMes) {
        this.ventaMes = ventaMes;
    }

    public Integer getVentaM1() {
        return ventaM1;
    }

    public void setVentaM1(Integer ventaM1) {
        this.ventaM1 = ventaM1;
    }

    public Integer getVentaM2() {
        return ventaM2;
    }

    public void setVentaM2(Integer ventaM2) {
        this.ventaM2 = ventaM2;
    }

    public Integer getVentaM3() {
        return ventaM3;
    }

    public void setVentaM3(Integer ventaM3) {
        this.ventaM3 = ventaM3;
    }

    public Integer getVentaM4() {
        return ventaM4;
    }

    public void setVentaM4(Integer ventaM4) {
        this.ventaM4 = ventaM4;
    }

    public Integer getVentaM5() {
        return ventaM5;
    }

    public void setVentaM5(Integer ventaM5) {
        this.ventaM5 = ventaM5;
    }

    public Integer getVentaM6() {
        return ventaM6;
    }

    public void setVentaM6(Integer ventaM6) {
        this.ventaM6 = ventaM6;
    }

    public String getStockTDP() {
        return stockTDP;
    }

    public void setStockTDP(String stockTDP) {
        this.stockTDP = stockTDP;
    }

    public String getMesStock() {
        return mesStock;
    }

    public void setMesStock(String mesStock) {
        this.mesStock = mesStock;
    }

    public String getVvpSoles() {
        return vvpSoles;
    }

    public void setVvpSoles(String vvpSoles) {
        this.vvpSoles = vvpSoles;
    }

    public Integer getMovVta() {
        return movVta;
    }

    public void setMovVta(Integer movVta) {
        this.movVta = movVta;
    }

    public Integer getNroDias() {
        return nroDias;
    }

    public void setNroDias(Integer nroDias) {
        this.nroDias = nroDias;
    }

    public Integer getStockPA() {
        return stockPA;
    }

    public void setStockPA(Integer stockPA) {
        this.stockPA = stockPA;
    }

    public Integer getStockGR() {
        return stockGR;
    }

    public void setStockGR(Integer stockGR) {
        this.stockGR = stockGR;
    }

    public Integer getStockGT() {
        return stockGT;
    }

    public void setStockGT(Integer stockGT) {
        this.stockGT = stockGT;
    }

    public Integer getStockSQ() {
        return stockSQ;
    }

    public void setStockSQ(Integer stockSQ) {
        this.stockSQ = stockSQ;
    }

    public Integer getStockCT() {
        return stockCT;
    }

    public void setStockCT(Integer stockCT) {
        this.stockCT = stockCT;
    }

    public Integer getStockMM() {
        return stockMM;
    }

    public void setStockMM(Integer stockMM) {
        this.stockMM = stockMM;
    }

    public Integer getStockPI() {
        return stockPI;
    }

    public void setStockPI(Integer stockPI) {
        this.stockPI = stockPI;
    }

    public Integer getStockGV() {
        return stockGV;
    }

    public void setStockGV(Integer stockGV) {
        this.stockGV = stockGV;
    }

    public Integer getStockGS() {
        return stockGS;
    }

    public void setStockGS(Integer stockGS) {
        this.stockGS = stockGS;
    }

    public Integer getStockGL() {
        return stockGL;
    }

    public void setStockGL(Integer stockGL) {
        this.stockGL = stockGL;
    }

    public Integer getStockHI() {
        return stockHI;
    }

    public void setStockHI(Integer stockHI) {
        this.stockHI = stockHI;
    }

    public Integer getStockAR() {
        return stockAR;
    }

    public void setStockAR(Integer stockAR) {
        this.stockAR = stockAR;
    }

    public Integer getStockTM() {
        return stockTM;
    }

    public void setStockTM(Integer stockTM) {
        this.stockTM = stockTM;
    }
}
