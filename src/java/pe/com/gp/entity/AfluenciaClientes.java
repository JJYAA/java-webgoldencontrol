package pe.com.gp.entity;

import java.io.Serializable;

public class AfluenciaClientes implements Serializable {

    // ========================
    // Generales
    // ========================
    static final long serialVersionUID = 1L;
    private String operacion;
    private String flagMueOcuForm;
    private boolean existe;

    // ========================
    // Cliente
    // ========================
    private String cliCodDep;
    private String cliNomDep;
    private String cliCodPro;
    private String cliNomPro;
    private String cliCodDis;
    private String cliNomDis;
    private String cliSexo;
    private String cliEdad;

    // ========================
    // Afluencia
    // ========================
    private String codigoTienda;
    private String nombreTienda;
    private String codigoAsesor;
    private String nombreAsesor;
    private String origenContacto;
    private String puntoContacto;
    private Integer acumulado;
    private String fecha;
    private String vinoPorEvento;

    public AfluenciaClientes() {
    }

    public String getVinoPorEvento() {
        return vinoPorEvento;
    }

    public void setVinoPorEvento(String vinoPorEvento) {
        this.vinoPorEvento = vinoPorEvento;
    }

    public Integer getAcumulado() {
        return acumulado;
    }

    public void setAcumulado(Integer acumulado) {
        this.acumulado = acumulado;
    }

    public String getCliSexo() {
        return cliSexo;
    }

    public void setCliSexo(String cliSexo) {
        this.cliSexo = cliSexo;
    }

    public String getCliEdad() {
        return cliEdad;
    }

    public void setCliEdad(String cliEdad) {
        this.cliEdad = cliEdad;
    }

    public String getPuntoContacto() {
        return puntoContacto;
    }

    public void setPuntoContacto(String puntoContacto) {
        this.puntoContacto = puntoContacto;
    }

    public String getNombreTienda() {
        return nombreTienda;
    }

    public void setNombreTienda(String nombreTienda) {
        this.nombreTienda = nombreTienda;
    }

    public String getCliNomDep() {
        return cliNomDep;
    }

    public void setCliNomDep(String cliNomDep) {
        this.cliNomDep = cliNomDep;
    }

    public String getCliNomPro() {
        return cliNomPro;
    }

    public void setCliNomPro(String cliNomPro) {
        this.cliNomPro = cliNomPro;
    }

    public String getCliNomDis() {
        return cliNomDis;
    }

    public void setCliNomDis(String cliNomDis) {
        this.cliNomDis = cliNomDis;
    }

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }

    public String getFlagMueOcuForm() {
        return flagMueOcuForm;
    }

    public void setFlagMueOcuForm(String flagMueOcuForm) {
        this.flagMueOcuForm = flagMueOcuForm;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCliCodDep() {
        return cliCodDep;
    }

    public void setCliCodDep(String cliCodDep) {
        this.cliCodDep = cliCodDep;
    }

    public String getCliCodPro() {
        return cliCodPro;
    }

    public void setCliCodPro(String cliCodPro) {
        this.cliCodPro = cliCodPro;
    }

    public String getCliCodDis() {
        return cliCodDis;
    }

    public void setCliCodDis(String cliCodDis) {
        this.cliCodDis = cliCodDis;
    }

    public String getCodigoTienda() {
        return codigoTienda;
    }

    public void setCodigoTienda(String codigoTienda) {
        this.codigoTienda = codigoTienda;
    }

    public String getCodigoAsesor() {
        return codigoAsesor;
    }

    public void setCodigoAsesor(String codigoAsesor) {
        this.codigoAsesor = codigoAsesor;
    }

    public String getNombreAsesor() {
        return nombreAsesor;
    }

    public void setNombreAsesor(String nombreAsesor) {
        this.nombreAsesor = nombreAsesor;
    }

    public String getOrigenContacto() {
        return origenContacto;
    }

    public void setOrigenContacto(String origenContacto) {
        this.origenContacto = origenContacto;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
