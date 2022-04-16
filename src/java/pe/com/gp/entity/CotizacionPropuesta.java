package pe.com.gp.entity;

import java.io.Serializable;

public class CotizacionPropuesta implements Serializable {

    static final long serialVersionUID = 1L;
    private String cotProClase;
    private String cotProNumero;
    private long cotProNum;
    private String codEmp;
    private String cotProFecha;
    private String cotProEstado;
    private String cotProMoneda;
    private String cotProCodPtoCon;
    private String cotProDesPtoCon;
    private String cotProBooking;
    private String cotProTipo;
    private String cliNombre;
    private String cliDireccion;
    private String cliTelefono;
    private String cliFax;
    private String cliEmail;
    private String vehCodigo;
    private String vehAnio;
    private String vehCodMod;
    private String vehDesMod;
    private String vehColor;
    private String aseNombre;
    private String aseCodigo;
    private String supCodigo;
    private double cotProTotOpe;
    private long ficha;
    private byte[] archivo;
    private boolean existe;

    public CotizacionPropuesta() {
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public double getCotProTotOpe() {
        return cotProTotOpe;
    }

    public void setCotProTotOpe(double cotProTotOpe) {
        this.cotProTotOpe = cotProTotOpe;
    }

    public long getCotProNum() {
        return cotProNum;
    }

    public void setCotProNum(long cotProNum) {
        this.cotProNum = cotProNum;
    }

    public String getVehDesMod() {
        return vehDesMod;
    }

    public void setVehDesMod(String vehDesMod) {
        this.vehDesMod = vehDesMod;
    }

    public long getFicha() {
        return ficha;
    }

    public void setFicha(long ficha) {
        this.ficha = ficha;
    }

    public String getSupCodigo() {
        return supCodigo;
    }

    public void setSupCodigo(String supCodigo) {
        this.supCodigo = supCodigo;
    }

    public String getAseCodigo() {
        return aseCodigo;
    }

    public void setAseCodigo(String aseCodigo) {
        this.aseCodigo = aseCodigo;
    }

    public String getCotProClase() {
        return cotProClase;
    }

    public void setCotProClase(String cotProClase) {
        this.cotProClase = cotProClase;
    }

    public String getCotProNumero() {
        return cotProNumero;
    }

    public void setCotProNumero(String cotProNumero) {
        this.cotProNumero = cotProNumero;
    }

    public String getCotProFecha() {
        return cotProFecha;
    }

    public void setCotProFecha(String cotProFecha) {
        this.cotProFecha = cotProFecha;
    }

    public String getCotProEstado() {
        return cotProEstado;
    }

    public void setCotProEstado(String cotProEstado) {
        this.cotProEstado = cotProEstado;
    }

    public String getCotProMoneda() {
        return cotProMoneda;
    }

    public void setCotProMoneda(String cotProMoneda) {
        this.cotProMoneda = cotProMoneda;
    }

    public String getCotProCodPtoCon() {
        return cotProCodPtoCon;
    }

    public void setCotProCodPtoCon(String cotProCodPtoCon) {
        this.cotProCodPtoCon = cotProCodPtoCon;
    }

    public String getCotProDesPtoCon() {
        return cotProDesPtoCon;
    }

    public void setCotProDesPtoCon(String cotProDesPtoCon) {
        this.cotProDesPtoCon = cotProDesPtoCon;
    }

    public String getCotProBooking() {
        return cotProBooking;
    }

    public void setCotProBooking(String cotProBooking) {
        this.cotProBooking = cotProBooking;
    }

    public String getCotProTipo() {
        return cotProTipo;
    }

    public void setCotProTipo(String cotProTipo) {
        this.cotProTipo = cotProTipo;
    }

    public String getCliNombre() {
        return cliNombre;
    }

    public void setCliNombre(String cliNombre) {
        this.cliNombre = cliNombre;
    }

    public String getCliDireccion() {
        return cliDireccion;
    }

    public void setCliDireccion(String cliDireccion) {
        this.cliDireccion = cliDireccion;
    }

    public String getCliTelefono() {
        return cliTelefono;
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono = cliTelefono;
    }

    public String getCliFax() {
        return cliFax;
    }

    public void setCliFax(String cliFax) {
        this.cliFax = cliFax;
    }

    public String getVehCodigo() {
        return vehCodigo;
    }

    public void setVehCodigo(String vehCodigo) {
        this.vehCodigo = vehCodigo;
    }

    public String getVehAnio() {
        return vehAnio;
    }

    public void setVehAnio(String vehAnio) {
        this.vehAnio = vehAnio;
    }

    public String getVehCodMod() {
        return vehCodMod;
    }

    public void setVehCodMod(String vehCodMod) {
        this.vehCodMod = vehCodMod;
    }

    public String getVehColor() {
        return vehColor;
    }

    public void setVehColor(String vehColor) {
        this.vehColor = vehColor;
    }

    public String getAseNombre() {
        return aseNombre;
    }

    public void setAseNombre(String aseNombre) {
        this.aseNombre = aseNombre;
    }

}
