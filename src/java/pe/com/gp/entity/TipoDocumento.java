package pe.com.gp.entity;

public class TipoDocumento {

    private static final long serialVersionUID = 1L;
    private String codigoDocumento;
    private String tipoDocumento;
    private String longitudTipDoc;
    private String extranjeroSN;
    private String tipoPersona;
    private String rucSN;
    private String abreviatura;
    private String descripcion;
    private String ambSN;
    private boolean existe;

    public TipoDocumento() {
    }

    public String getAmbSN() {
        return ambSN;
    }

    public void setAmbSN(String ambSN) {
        this.ambSN = ambSN;
    }

    public String getCodigoDocumento() {
        return codigoDocumento;
    }

    public void setCodigoDocumento(String codigoDocumento) {
        this.codigoDocumento = codigoDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getLongitudTipDoc() {
        return longitudTipDoc;
    }

    public void setLongitudTipDoc(String longitudTipDoc) {
        this.longitudTipDoc = longitudTipDoc;
    }

    public String getExtranjeroSN() {
        return extranjeroSN;
    }

    public void setExtranjeroSN(String extranjeroSN) {
        this.extranjeroSN = extranjeroSN;
    }

    public String getTipoPersona() {
        return tipoPersona;
    }

    public void setTipoPersona(String tipoPersona) {
        this.tipoPersona = tipoPersona;
    }

    public String getRucSN() {
        return rucSN;
    }

    public void setRucSN(String rucSN) {
        this.rucSN = rucSN;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
