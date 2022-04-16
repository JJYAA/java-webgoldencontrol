package pe.com.gp.entity;

import java.io.Serializable;

public class BeanCabDocGen implements Serializable {

    static final long serialVersionUID = 1L;
    private long serieDocumento;
    private long numeroDocumento;
    private String procedenciaDocumento;
    private String tipoDocumento;
    private String formaPagoDocumento;
    private String fechaGeneracion;
    private String nota01;
    private String nota02;
    private String nota03;
    private String nota04;
    private boolean existe;

    public BeanCabDocGen() {
    }

    public long getSerieDocumento() {
        return serieDocumento;
    }

    public void setSerieDocumento(long serieDocumento) {
        this.serieDocumento = serieDocumento;
    }

    public long getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(long numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getProcedenciaDocumento() {
        return procedenciaDocumento;
    }

    public void setProcedenciaDocumento(String procedenciaDocumento) {
        this.procedenciaDocumento = procedenciaDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getFormaPagoDocumento() {
        return formaPagoDocumento;
    }

    public void setFormaPagoDocumento(String formaPagoDocumento) {
        this.formaPagoDocumento = formaPagoDocumento;
    }

    public String getFechaGeneracion() {
        return fechaGeneracion;
    }

    public void setFechaGeneracion(String fechaGeneracion) {
        this.fechaGeneracion = fechaGeneracion;
    }

    public String getNota01() {
        return nota01;
    }

    public void setNota01(String nota01) {
        this.nota01 = nota01;
    }

    public String getNota02() {
        return nota02;
    }

    public void setNota02(String nota02) {
        this.nota02 = nota02;
    }

    public String getNota03() {
        return nota03;
    }

    public void setNota03(String nota03) {
        this.nota03 = nota03;
    }

    public String getNota04() {
        return nota04;
    }

    public void setNota04(String nota04) {
        this.nota04 = nota04;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
