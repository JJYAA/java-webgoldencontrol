package pe.com.gp.entity;

import java.io.Serializable;

public class ConfiguracionDimension implements Serializable {

    private String codigoTienda;
    private String centroCostoUniNeg;
    private String centroCostoTienda;
    private String codigoArea;
    private String codigoAlmacenRep001;
    private String codigoAlmacenRep002;
    private String codigoAlmacenSer001;
    private String glosa;
    private String unidadNegocioDefecto;
    private boolean existe;
    private String tipoDocEntrega;

    public String getTipoDocEntrega() {
        return tipoDocEntrega;
    }

    public void setTipoDocEntrega(String tipoDocEntrega) {
        this.tipoDocEntrega = tipoDocEntrega;
    }
    
    

    public ConfiguracionDimension() {
    }

    public String getUnidadNegocioDefecto() {
        return unidadNegocioDefecto;
    }

    public void setUnidadNegocioDefecto(String unidadNegocioDefecto) {
        this.unidadNegocioDefecto = unidadNegocioDefecto;
    }

    public String getGlosa() {
        return glosa;
    }

    public void setGlosa(String glosa) {
        this.glosa = glosa;
    }

    public String getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public String getCentroCostoUniNeg() {
        return centroCostoUniNeg;
    }

    public void setCentroCostoUniNeg(String centroCostoUniNeg) {
        this.centroCostoUniNeg = centroCostoUniNeg;
    }

    public String getCentroCostoTienda() {
        return centroCostoTienda;
    }

    public void setCentroCostoTienda(String centroCostoTienda) {
        this.centroCostoTienda = centroCostoTienda;
    }

    public String getCodigoArea() {
        return codigoArea;
    }

    public void setCodigoArea(String codigoArea) {
        this.codigoArea = codigoArea;
    }

    public String getCodigoAlmacenRep001() {
        return codigoAlmacenRep001;
    }

    public void setCodigoAlmacenRep001(String codigoAlmacenRep001) {
        this.codigoAlmacenRep001 = codigoAlmacenRep001;
    }

    public String getCodigoAlmacenRep002() {
        return codigoAlmacenRep002;
    }

    public void setCodigoAlmacenRep002(String codigoAlmacenRep002) {
        this.codigoAlmacenRep002 = codigoAlmacenRep002;
    }

    public String getCodigoAlmacenSer001() {
        return codigoAlmacenSer001;
    }

    public void setCodigoAlmacenSer001(String codigoAlmacenSer001) {
        this.codigoAlmacenSer001 = codigoAlmacenSer001;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
