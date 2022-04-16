package pe.com.gp.entity;

import java.io.Serializable;

public class BoletaTraSis implements Serializable {

    static final long serialVersionUID = 1L;
    public long numeroBoleta;
    public String tipoBoleta;
    private String fechaTraslado;
    private String asunto;
    private String codEmpOri;
    private String nomEmpOri;
    private String otroOrigen;
    private String codEmpDes;
    private String nomEmpDes;
    private String otroDestino;
    private String codUsrRes;
    private String nomUsrRes;
    private String codUsrVB;
    private String nomUsrVB;
    private String observacion;
    private Integer itemCantidad;
    private String itemDescripcion;
    private String codEmpGen;
    private String fechaGenerado;
    public Integer numeroItem;
    private boolean existe;

    public BoletaTraSis() {
    }

    public long getNumeroBoleta() {
        return numeroBoleta;
    }

    public void setNumeroBoleta(long numeroBoleta) {
        this.numeroBoleta = numeroBoleta;
    }

    public String getTipoBoleta() {
        return tipoBoleta;
    }

    public void setTipoBoleta(String tipoBoleta) {
        this.tipoBoleta = tipoBoleta;
    }

    public String getFechaTraslado() {
        return fechaTraslado;
    }

    public void setFechaTraslado(String fechaTraslado) {
        this.fechaTraslado = fechaTraslado;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCodEmpOri() {
        return codEmpOri;
    }

    public void setCodEmpOri(String codEmpOri) {
        this.codEmpOri = codEmpOri;
    }

    public String getNomEmpOri() {
        return nomEmpOri;
    }

    public void setNomEmpOri(String nomEmpOri) {
        this.nomEmpOri = nomEmpOri;
    }

    public String getOtroOrigen() {
        return otroOrigen;
    }

    public void setOtroOrigen(String otroOrigen) {
        this.otroOrigen = otroOrigen;
    }

    public String getCodEmpDes() {
        return codEmpDes;
    }

    public void setCodEmpDes(String codEmpDes) {
        this.codEmpDes = codEmpDes;
    }

    public String getNomEmpDes() {
        return nomEmpDes;
    }

    public void setNomEmpDes(String nomEmpDes) {
        this.nomEmpDes = nomEmpDes;
    }

    public String getOtroDestino() {
        return otroDestino;
    }

    public void setOtroDestino(String otroDestino) {
        this.otroDestino = otroDestino;
    }

    public String getCodUsrRes() {
        return codUsrRes;
    }

    public void setCodUsrRes(String codUsrRes) {
        this.codUsrRes = codUsrRes;
    }

    public String getNomUsrRes() {
        return nomUsrRes;
    }

    public void setNomUsrRes(String nomUsrRes) {
        this.nomUsrRes = nomUsrRes;
    }

    public String getCodUsrVB() {
        return codUsrVB;
    }

    public void setCodUsrVB(String codUsrVB) {
        this.codUsrVB = codUsrVB;
    }

    public String getNomUsrVB() {
        return nomUsrVB;
    }

    public void setNomUsrVB(String nomUsrVB) {
        this.nomUsrVB = nomUsrVB;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Integer getItemCantidad() {
        return itemCantidad;
    }

    public void setItemCantidad(Integer itemCantidad) {
        this.itemCantidad = itemCantidad;
    }

    public String getItemDescripcion() {
        return itemDescripcion;
    }

    public void setItemDescripcion(String itemDescripcion) {
        this.itemDescripcion = itemDescripcion;
    }

    public String getCodEmpGen() {
        return codEmpGen;
    }

    public void setCodEmpGen(String codEmpGen) {
        this.codEmpGen = codEmpGen;
    }

    public String getFechaGenerado() {
        return fechaGenerado;
    }

    public void setFechaGenerado(String fechaGenerado) {
        this.fechaGenerado = fechaGenerado;
    }

    public Integer getNumeroItem() {
        return numeroItem;
    }

    public void setNumeroItem(Integer numeroItem) {
        this.numeroItem = numeroItem;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
