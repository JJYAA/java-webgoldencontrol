package pe.com.gp.entity;

import java.io.Serializable;
import java.sql.Date;

public class Proforma implements Serializable {

    private static final long serialVersionUID = 1L;
    private String local;
    private long numero;
    private Date fecha;
    private Cliente cliente;
    private double bruto_soles;
    private double des_soles;
    private double igv_soles;
    private double bruto_dolares;
    private double des_dolares;
    private double igv_dolares;
    private String pro_proforma;
    private String nota1;
    private String nota2;
    private String nota3;
    private String nota4;
    private String num_ot;
    private long tempo_item;
    private double tipo_cambio;
    private String moneda;
    private String siniestro;
    private String poliza;
    private double monto_fran;
    private String nom_asegurado;
    private String pla_vehiculo;
    private String mod_vehiculo;
    private String col_vehiculo;
    private String mot_vehiculo;
    private String cha_vehiculo;
    private String kil_vehiculo;
    private String anu_proforma;
    private String ser_vehiculo;
    private String ano_vehiculo;
    private String mar_vehiculo;
    private String cod_usuario;
    private double totalNeto_soles;
    private double totalGeneral_soles;
    private double totalNeto_dolares;
    private double totalGeneral_dolares;
    private String usuario; // vendedor que genera la proforma
    private String fecha_doc;
    private String hora_doc;
    private String impNota1;
    private String impNota2;
    private String total_general;
    private String ExisteProforma;
    private String codigoCanal;

    public Proforma() {
    }

    public String getCodigoCanal() {
        return codigoCanal;
    }

    public void setCodigoCanal(String codigoCanal) {
        this.codigoCanal = codigoCanal;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public long getNumero() {
        return numero;
    }

    public void setNumero(long numero) {
        this.numero = numero;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getBruto_soles() {
        return bruto_soles;
    }

    public void setBruto_soles(double bruto_soles) {
        this.bruto_soles = bruto_soles;
    }

    public double getDes_soles() {
        return des_soles;
    }

    public void setDes_soles(double des_soles) {
        this.des_soles = des_soles;
    }

    public double getIgv_soles() {
        return igv_soles;
    }

    public void setIgv_soles(double igv_soles) {
        this.igv_soles = igv_soles;
    }

    public double getBruto_dolares() {
        return bruto_dolares;
    }

    public void setBruto_dolares(double bruto_dolares) {
        this.bruto_dolares = bruto_dolares;
    }

    public double getDes_dolares() {
        return des_dolares;
    }

    public void setDes_dolares(double des_dolares) {
        this.des_dolares = des_dolares;
    }

    public double getIgv_dolares() {
        return igv_dolares;
    }

    public void setIgv_dolares(double igv_dolares) {
        this.igv_dolares = igv_dolares;
    }

    public String getPro_proforma() {
        return pro_proforma;
    }

    public void setPro_proforma(String pro_proforma) {
        this.pro_proforma = pro_proforma;
    }

    public String getNota1() {
        return nota1;
    }

    public void setNota1(String nota1) {
        this.nota1 = nota1;
    }

    public String getNota2() {
        return nota2;
    }

    public void setNota2(String nota2) {
        this.nota2 = nota2;
    }

    public String getNota3() {
        return nota3;
    }

    public void setNota3(String nota3) {
        this.nota3 = nota3;
    }

    public String getNota4() {
        return nota4;
    }

    public void setNota4(String nota4) {
        this.nota4 = nota4;
    }

    public String getNum_ot() {
        return num_ot;
    }

    public void setNum_ot(String num_ot) {
        this.num_ot = num_ot;
    }

    public long getTempo_item() {
        return tempo_item;
    }

    public void setTempo_item(long tempo_item) {
        this.tempo_item = tempo_item;
    }

    public double getTipo_cambio() {
        return tipo_cambio;
    }

    public void setTipo_cambio(double tipo_cambio) {
        this.tipo_cambio = tipo_cambio;
    }

    public String getMoneda() {
        return moneda;
    }

    public void setMoneda(String moneda) {
        this.moneda = moneda;
    }

    public String getSiniestro() {
        return siniestro;
    }

    public void setSiniestro(String siniestro) {
        this.siniestro = siniestro;
    }

    public String getPoliza() {
        return poliza;
    }

    public void setPoliza(String poliza) {
        this.poliza = poliza;
    }

    public double getMonto_fran() {
        return monto_fran;
    }

    public void setMonto_fran(double monto_fran) {
        this.monto_fran = monto_fran;
    }

    public String getNom_asegurado() {
        return nom_asegurado;
    }

    public void setNom_asegurado(String nom_asegurado) {
        this.nom_asegurado = nom_asegurado;
    }

    public String getPla_vehiculo() {
        return pla_vehiculo;
    }

    public void setPla_vehiculo(String pla_vehiculo) {
        this.pla_vehiculo = pla_vehiculo;
    }

    public String getMod_vehiculo() {
        return mod_vehiculo;
    }

    public void setMod_vehiculo(String mod_vehiculo) {
        this.mod_vehiculo = mod_vehiculo;
    }

    public String getCol_vehiculo() {
        return col_vehiculo;
    }

    public void setCol_vehiculo(String col_vehiculo) {
        this.col_vehiculo = col_vehiculo;
    }

    public String getMot_vehiculo() {
        return mot_vehiculo;
    }

    public void setMot_vehiculo(String mot_vehiculo) {
        this.mot_vehiculo = mot_vehiculo;
    }

    public String getCha_vehiculo() {
        return cha_vehiculo;
    }

    public void setCha_vehiculo(String cha_vehiculo) {
        this.cha_vehiculo = cha_vehiculo;
    }

    public String getKil_vehiculo() {
        return kil_vehiculo;
    }

    public void setKil_vehiculo(String kil_vehiculo) {
        this.kil_vehiculo = kil_vehiculo;
    }

    public String getAnu_proforma() {
        return anu_proforma;
    }

    public void setAnu_proforma(String anu_proforma) {
        this.anu_proforma = anu_proforma;
    }

    public String getSer_vehiculo() {
        return ser_vehiculo;
    }

    public void setSer_vehiculo(String ser_vehiculo) {
        this.ser_vehiculo = ser_vehiculo;
    }

    public String getAno_vehiculo() {
        return ano_vehiculo;
    }

    public void setAno_vehiculo(String ano_vehiculo) {
        this.ano_vehiculo = ano_vehiculo;
    }

    public String getMar_vehiculo() {
        return mar_vehiculo;
    }

    public void setMar_vehiculo(String mar_vehiculo) {
        this.mar_vehiculo = mar_vehiculo;
    }

    public String getCod_usuario() {
        return cod_usuario;
    }

    public void setCod_usuario(String cod_usuario) {
        this.cod_usuario = cod_usuario;
    }

    public double getTotalNeto_soles() {
        return totalNeto_soles;
    }

    public void setTotalNeto_soles(double totalNeto_soles) {
        this.totalNeto_soles = totalNeto_soles;
    }

    public double getTotalGeneral_soles() {
        return totalGeneral_soles;
    }

    public void setTotalGeneral_soles(double totalGeneral_soles) {
        this.totalGeneral_soles = totalGeneral_soles;
    }

    public double getTotalNeto_dolares() {
        return totalNeto_dolares;
    }

    public void setTotalNeto_dolares(double totalNeto_dolares) {
        this.totalNeto_dolares = totalNeto_dolares;
    }

    public double getTotalGeneral_dolares() {
        return totalGeneral_dolares;
    }

    public void setTotalGeneral_dolares(double totalGeneral_dolares) {
        this.totalGeneral_dolares = totalGeneral_dolares;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getFecha_doc() {
        return fecha_doc;
    }

    public void setFecha_doc(String fecha_doc) {
        this.fecha_doc = fecha_doc;
    }

    public String getHora_doc() {
        return hora_doc;
    }

    public void setHora_doc(String hora_doc) {
        this.hora_doc = hora_doc;
    }

    public String getTotal_general() {
        return total_general;
    }

    public void setTotal_general(String total_general) {
        this.total_general = total_general;
    }

    public String getImpNota1() {
        return impNota1;
    }

    public void setImpNota1(String impNota1) {
        this.impNota1 = impNota1;
    }

    public String getImpNota2() {
        return impNota2;
    }

    public void setImpNota2(String impNota2) {
        this.impNota2 = impNota2;
    }

    public String getExisteProforma() {
        return ExisteProforma;
    }

    public void setExisteProforma(String ExisteProforma) {
        this.ExisteProforma = ExisteProforma;
    }

}
