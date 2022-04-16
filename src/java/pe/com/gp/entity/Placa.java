package pe.com.gp.entity;

import java.io.Serializable;

public class Placa implements Serializable {

    private static final long serialVersionUID = 1L;
    private String tienda = null;
    private String placa = null;
    private String serie = null;
    private String marca = null;
    private String modelo = null;
    private String anho = null;
    private String motor = null;
    private String chasis = null;
    private String color = null;
    private String cod_propietario = null;
    private String doc_propietario = null;
    private String transmision = null;
    private String combustion = null;
    private String t_transmision = "";
    private String t_combustion = "";
    private String model = null;
    private String tipo_per_prop = null;
    private String razsoc_prop = null;
    private String pater_prop = null;
    private String mater_prop = null;
    private String pri_nom_prop = null;
    private String seg_nom_prop = null;
    private String dir_prop = null;
    private String tel_prop_01 = "";
    private String tel_prop_02 = "";
    private String cel_prop_01 = "";
    private String mail_prop_01 = "";
    private String not_001 = "";
    private String not_002 = "";
    private String not_003 = "";
    private String not_004 = "";
    private String recibir_mail = "0";
    private long kilom = 0;
    private String nom_propietario;
    private String tipo_ot;
    private String anulado;
    private String cod_rec;
    private String nro_ot;

    // Nuevos
    private String clienteCodigo;
    private String clienteRazSoc;
    private String corporativo;
    private boolean existe;

    public Placa() {
    }

    public String getTienda() {
        return tienda;
    }

    public void setTienda(String tienda) {
        this.tienda = tienda;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getAnho() {
        return anho;
    }

    public void setAnho(String anho) {
        this.anho = anho;
    }

    public String getMotor() {
        return motor;
    }

    public void setMotor(String motor) {
        this.motor = motor;
    }

    public String getChasis() {
        return chasis;
    }

    public void setChasis(String chasis) {
        this.chasis = chasis;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCod_propietario() {
        return cod_propietario;
    }

    public void setCod_propietario(String cod_propietario) {
        this.cod_propietario = cod_propietario;
    }

    public String getDoc_propietario() {
        return doc_propietario;
    }

    public void setDoc_propietario(String doc_propietario) {
        this.doc_propietario = doc_propietario;
    }

    public String getTransmision() {
        return transmision;
    }

    public void setTransmision(String transmision) {
        this.transmision = transmision;
    }

    public String getCombustion() {
        return combustion;
    }

    public void setCombustion(String combustion) {
        this.combustion = combustion;
    }

    public String getT_transmision() {
        return t_transmision;
    }

    public void setT_transmision(String t_transmision) {
        this.t_transmision = t_transmision;
    }

    public String getT_combustion() {
        return t_combustion;
    }

    public void setT_combustion(String t_combustion) {
        this.t_combustion = t_combustion;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTipo_per_prop() {
        return tipo_per_prop;
    }

    public void setTipo_per_prop(String tipo_per_prop) {
        this.tipo_per_prop = tipo_per_prop;
    }

    public String getRazsoc_prop() {
        return razsoc_prop;
    }

    public void setRazsoc_prop(String razsoc_prop) {
        this.razsoc_prop = razsoc_prop;
    }

    public String getPater_prop() {
        return pater_prop;
    }

    public void setPater_prop(String pater_prop) {
        this.pater_prop = pater_prop;
    }

    public String getMater_prop() {
        return mater_prop;
    }

    public void setMater_prop(String mater_prop) {
        this.mater_prop = mater_prop;
    }

    public String getPri_nom_prop() {
        return pri_nom_prop;
    }

    public void setPri_nom_prop(String pri_nom_prop) {
        this.pri_nom_prop = pri_nom_prop;
    }

    public String getSeg_nom_prop() {
        return seg_nom_prop;
    }

    public void setSeg_nom_prop(String seg_nom_prop) {
        this.seg_nom_prop = seg_nom_prop;
    }

    public String getDir_prop() {
        return dir_prop;
    }

    public void setDir_prop(String dir_prop) {
        this.dir_prop = dir_prop;
    }

    public String getTel_prop_01() {
        return tel_prop_01;
    }

    public void setTel_prop_01(String tel_prop_01) {
        this.tel_prop_01 = tel_prop_01;
    }

    public String getTel_prop_02() {
        return tel_prop_02;
    }

    public void setTel_prop_02(String tel_prop_02) {
        this.tel_prop_02 = tel_prop_02;
    }

    public String getCel_prop_01() {
        return cel_prop_01;
    }

    public void setCel_prop_01(String cel_prop_01) {
        this.cel_prop_01 = cel_prop_01;
    }

    public String getMail_prop_01() {
        return mail_prop_01;
    }

    public void setMail_prop_01(String mail_prop_01) {
        this.mail_prop_01 = mail_prop_01;
    }

    public String getNot_001() {
        return not_001;
    }

    public void setNot_001(String not_001) {
        this.not_001 = not_001;
    }

    public String getNot_002() {
        return not_002;
    }

    public void setNot_002(String not_002) {
        this.not_002 = not_002;
    }

    public String getNot_003() {
        return not_003;
    }

    public void setNot_003(String not_003) {
        this.not_003 = not_003;
    }

    public String getNot_004() {
        return not_004;
    }

    public void setNot_004(String not_004) {
        this.not_004 = not_004;
    }

    public String getRecibir_mail() {
        return recibir_mail;
    }

    public void setRecibir_mail(String recibir_mail) {
        this.recibir_mail = recibir_mail;
    }

    public long getKilom() {
        return kilom;
    }

    public void setKilom(long kilom) {
        this.kilom = kilom;
    }

    public String getNom_propietario() {
        return nom_propietario;
    }

    public void setNom_propietario(String nom_propietario) {
        this.nom_propietario = nom_propietario;
    }

    public String getTipo_ot() {
        return tipo_ot;
    }

    public void setTipo_ot(String tipo_ot) {
        this.tipo_ot = tipo_ot;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public String getCod_rec() {
        return cod_rec;
    }

    public void setCod_rec(String cod_rec) {
        this.cod_rec = cod_rec;
    }

    public String getNro_ot() {
        return nro_ot;
    }

    public void setNro_ot(String nro_ot) {
        this.nro_ot = nro_ot;
    }

    public String getClienteCodigo() {
        return clienteCodigo;
    }

    public void setClienteCodigo(String clienteCodigo) {
        this.clienteCodigo = clienteCodigo;
    }

    public String getClienteRazSoc() {
        return clienteRazSoc;
    }

    public void setClienteRazSoc(String clienteRazSoc) {
        this.clienteRazSoc = clienteRazSoc;
    }

    public String getCorporativo() {
        return corporativo;
    }

    public void setCorporativo(String corporativo) {
        this.corporativo = corporativo;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
