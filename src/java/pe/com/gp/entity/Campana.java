package pe.com.gp.entity;

import java.io.Serializable;

public class Campana implements Serializable {

    static final long serialVersionUID = 1L;
    private String codigo;
    private String descripcion;
    private String msgCampana1;
    private String msgCampana2;
    private String msgAdicional;
    private String codUsuario;
    private String fechaRegistro;
    private String fechaIniCam;
    private boolean existe;

    public Campana() {
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMsgCampana1() {
        return msgCampana1;
    }

    public void setMsgCampana1(String msgCampana1) {
        this.msgCampana1 = msgCampana1;
    }

    public String getMsgCampana2() {
        return msgCampana2;
    }

    public void setMsgCampana2(String msgCampana2) {
        this.msgCampana2 = msgCampana2;
    }

    public String getMsgAdicional() {
        return msgAdicional;
    }

    public void setMsgAdicional(String msgAdicional) {
        this.msgAdicional = msgAdicional;
    }

    public String getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(String codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaIniCam() {
        return fechaIniCam;
    }

    public void setFechaIniCam(String fechaIniCam) {
        this.fechaIniCam = fechaIniCam;
    }

}
