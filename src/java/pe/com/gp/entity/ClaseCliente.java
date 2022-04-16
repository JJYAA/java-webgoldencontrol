package pe.com.gp.entity;

import java.io.Serializable;

public class ClaseCliente implements Serializable {

    static final long serialVersionUID = 1L;
    private String codigoClase;
    private String claseCliente;
    //private String tipoCliente;
    private String flgActivo;
    private boolean existe;

    public ClaseCliente() {
    }

    public String getCodigoClase() {
        return codigoClase;
    }

    public void setCodigoClase(String codigoClase) {
        this.codigoClase = codigoClase;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getClaseCliente() {
        return claseCliente;
    }

    public void setClaseCliente(String claseCliente) {
        this.claseCliente = claseCliente;
    }

//    public String getTipoCliente() {
//        return tipoCliente;
//    }
//
//    public void setTipoCliente(String tipoCliente) {
//        this.tipoCliente = tipoCliente;
//    }
    public String getFlgActivo() {
        return flgActivo;
    }

    public void setFlgActivo(String flgActivo) {
        this.flgActivo = flgActivo;
    }
}
