package pe.com.gp.entity;

import java.io.Serializable;

public class Cita implements Serializable {

    static final long serialVersionUID = 1L;
    private String codEmp;
    private String placa;
    private String expreso;
    private String espera;
    private String fir;
    private String noFir;
    private boolean existe;

    public Cita() {
    }

    public String getNoFir() {
        return noFir;
    }

    public void setNoFir(String noFir) {
        this.noFir = noFir;
    }

    public String getFir() {
        return fir;
    }

    public void setFir(String fir) {
        this.fir = fir;
    }

    public String getCodEmp() {
        return codEmp;
    }

    public void setCodEmp(String codEmp) {
        this.codEmp = codEmp;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getExpreso() {
        return expreso;
    }

    public void setExpreso(String expreso) {
        this.expreso = expreso;
    }

    public String getEspera() {
        return espera;
    }

    public void setEspera(String espera) {
        this.espera = espera;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
