package pe.com.gp.entity;

import java.io.Serializable;

public class LlamadaServicio implements Serializable {

    private int callID; // PK
    private int docNum;
    private String fechaCreacion;
    private String codCliente;
    private String nomCliente;
    private String vehPlaca;
    private String vehModelo;
    private String vehSerie;
    private String vehModel;
    private int codEstado;
    private String nomEstado;
    private int callType;
    private String codServicio;
    private String desServicio;
    private String ItemName;
    private String ItemCode;
    private String vehTransmision;
    private String codTipoOT;
    private String desTipoOT;
    private int kmIngreso;
    private int codTipoProblema;
    private String desTipoProblema;
    private String asunto;
    private boolean existe;
    private String codAseguradora;
    private String nomAseguradora;
            
    public LlamadaServicio() {
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public int getCodTipoProblema() {
        return codTipoProblema;
    }

    public void setCodTipoProblema(int codTipoProblema) {
        this.codTipoProblema = codTipoProblema;
    }

    public String getDesTipoProblema() {
        return desTipoProblema;
    }

    public void setDesTipoProblema(String desTipoProblema) {
        this.desTipoProblema = desTipoProblema;
    }

    public String getCodTipoOT() {
        return codTipoOT;
    }

    public void setCodTipoOT(String codTipoOT) {
        this.codTipoOT = codTipoOT;
    }

    public String getDesTipoOT() {
        return desTipoOT;
    }

    public void setDesTipoOT(String desTipoOT) {
        this.desTipoOT = desTipoOT;
    }

    public int getKmIngreso() {
        return kmIngreso;
    }

    public void setKmIngreso(int kmIngreso) {
        this.kmIngreso = kmIngreso;
    }

    public String getItemCode() {
        return ItemCode;
    }

    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    public String getItemName() {
        return ItemName;
    }

    public void setItemName(String ItemName) {
        this.ItemName = ItemName;
    }

    public String getVehTransmision() {
        return vehTransmision;
    }

    public void setVehTransmision(String vehTransmision) {
        this.vehTransmision = vehTransmision;
    }

    public int getCallType() {
        return callType;
    }

    public void setCallType(int callType) {
        this.callType = callType;
    }

    public String getCodServicio() {
        return codServicio;
    }

    public void setCodServicio(String codServicio) {
        this.codServicio = codServicio;
    }

    public String getDesServicio() {
        return desServicio;
    }

    public void setDesServicio(String desServicio) {
        this.desServicio = desServicio;
    }

    public int getCallID() {
        return callID;
    }

    public void setCallID(int callID) {
        this.callID = callID;
    }

    public int getDocNum() {
        return docNum;
    }

    public void setDocNum(int docNum) {
        this.docNum = docNum;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(String codCliente) {
        this.codCliente = codCliente;
    }

    public String getNomCliente() {
        return nomCliente;
    }

    public void setNomCliente(String nomCliente) {
        this.nomCliente = nomCliente;
    }

    public String getVehPlaca() {
        return vehPlaca;
    }

    public void setVehPlaca(String vehPlaca) {
        this.vehPlaca = vehPlaca;
    }

    public String getVehModelo() {
        return vehModelo;
    }

    public void setVehModelo(String vehModelo) {
        this.vehModelo = vehModelo;
    }

    public String getVehSerie() {
        return vehSerie;
    }

    public void setVehSerie(String vehSerie) {
        this.vehSerie = vehSerie;
    }

    public String getVehModel() {
        return vehModel;
    }

    public void setVehModel(String vehModel) {
        this.vehModel = vehModel;
    }

    public int getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(int codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

    public String getCodAseguradora() {
        return codAseguradora;
    }

    public void setCodAseguradora(String codAseguradora) {
        this.codAseguradora = codAseguradora;
    }

    public String getNomAseguradora() {
        return nomAseguradora;
    }

    public void setNomAseguradora(String nomAseguradora) {
        this.nomAseguradora = nomAseguradora;
    }
    
    

}
