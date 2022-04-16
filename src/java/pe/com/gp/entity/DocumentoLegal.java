package pe.com.gp.entity;

public class DocumentoLegal {

    static final long serialVersionUID = 1L;
    private long codDocumento;
    private String cliTipPer;
    private String cliNumDoc;
    private String cliRazSoc;
    private String cliApePat;
    private String cliApeMat;
    private String cliPriNom;
    private String cliSegNom;
    private String cliTelefono;
    private String cliCelular;
    private String cliEmail;
    private String codTipoDocLegal;
    private String desTipoDocLegal;
    private String codAbogado;
    private String razSocAbogado;
    private String codNatTer;
    private String desNatTer;
    private String plazoPerentorio;
    private String fechaVencimiento;
    private int plazoDiasUtiles;
    private String observaciones;
    private String respuesta;
    private String responsables;
    private String unidadNegocio;
    private String fechaRegistro;
    private String fechaRespuesta;
    private String diasTranscurridos;
    private String usuarioRegistro;
    private String anulado;
    private String codUniNegocio;
    private String nomUniNegocio;
    private String codEstado;
    private String nomEstado;
    private byte[] archivo;
    private String archivoMime;
    private String archivoNombre;
    private long archivoTamano;
    private int archivoNumero;
    private byte[] archivo1;
    private String archivo1Mime;
    private String archivo1Nombre;
    private long archivo1Tamano;
    private int archivo1Numero;
    private byte[] archivo2;
    private String archivo2Mime;
    private String archivo2Nombre;
    private long archivo2Tamano;
    private int archivo2Numero;
    private String fechaRecepcion;
    private boolean existe;

    public DocumentoLegal() {
    }

    public String getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(String fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public String getDiasTranscurridos() {
        return diasTranscurridos;
    }

    public void setDiasTranscurridos(String diasTranscurridos) {
        this.diasTranscurridos = diasTranscurridos;
    }

    public String getDesNatTer() {
        return desNatTer;
    }

    public void setDesNatTer(String desNatTer) {
        this.desNatTer = desNatTer;
    }

    public String getFechaRespuesta() {
        return fechaRespuesta;
    }

    public void setFechaRespuesta(String fechaRespuesta) {
        this.fechaRespuesta = fechaRespuesta;
    }

    public String getRazSocAbogado() {
        return razSocAbogado;
    }

    public void setRazSocAbogado(String razSocAbogado) {
        this.razSocAbogado = razSocAbogado;
    }

    public String getCliTipPer() {
        return cliTipPer;
    }

    public void setCliTipPer(String cliTipPer) {
        this.cliTipPer = cliTipPer;
    }

    public String getCliNumDoc() {
        return cliNumDoc;
    }

    public void setCliNumDoc(String cliNumDoc) {
        this.cliNumDoc = cliNumDoc;
    }

    public String getCliRazSoc() {
        return cliRazSoc;
    }

    public void setCliRazSoc(String cliRazSoc) {
        this.cliRazSoc = cliRazSoc;
    }

    public String getCliApePat() {
        return cliApePat;
    }

    public void setCliApePat(String cliApePat) {
        this.cliApePat = cliApePat;
    }

    public String getCliApeMat() {
        return cliApeMat;
    }

    public void setCliApeMat(String cliApeMat) {
        this.cliApeMat = cliApeMat;
    }

    public String getCliPriNom() {
        return cliPriNom;
    }

    public void setCliPriNom(String cliPriNom) {
        this.cliPriNom = cliPriNom;
    }

    public String getCliSegNom() {
        return cliSegNom;
    }

    public void setCliSegNom(String cliSegNom) {
        this.cliSegNom = cliSegNom;
    }

    public String getCliTelefono() {
        return cliTelefono;
    }

    public void setCliTelefono(String cliTelefono) {
        this.cliTelefono = cliTelefono;
    }

    public String getCliCelular() {
        return cliCelular;
    }

    public void setCliCelular(String cliCelular) {
        this.cliCelular = cliCelular;
    }

    public String getCliEmail() {
        return cliEmail;
    }

    public void setCliEmail(String cliEmail) {
        this.cliEmail = cliEmail;
    }

    public String getCodTipoDocLegal() {
        return codTipoDocLegal;
    }

    public void setCodTipoDocLegal(String codTipoDocLegal) {
        this.codTipoDocLegal = codTipoDocLegal;
    }

    public String getDesTipoDocLegal() {
        return desTipoDocLegal;
    }

    public void setDesTipoDocLegal(String desTipoDocLegal) {
        this.desTipoDocLegal = desTipoDocLegal;
    }

    public String getCodAbogado() {
        return codAbogado;
    }

    public void setCodAbogado(String codAbogado) {
        this.codAbogado = codAbogado;
    }

    public String getCodNatTer() {
        return codNatTer;
    }

    public void setCodNatTer(String codNatTer) {
        this.codNatTer = codNatTer;
    }

    public String getPlazoPerentorio() {
        return plazoPerentorio;
    }

    public void setPlazoPerentorio(String plazoPerentorio) {
        this.plazoPerentorio = plazoPerentorio;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public int getPlazoDiasUtiles() {
        return plazoDiasUtiles;
    }

    public void setPlazoDiasUtiles(int plazoDiasUtiles) {
        this.plazoDiasUtiles = plazoDiasUtiles;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getResponsables() {
        return responsables;
    }

    public void setResponsables(String responsables) {
        this.responsables = responsables;
    }

    public String getUnidadNegocio() {
        return unidadNegocio;
    }

    public void setUnidadNegocio(String unidadNegocio) {
        this.unidadNegocio = unidadNegocio;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getUsuarioRegistro() {
        return usuarioRegistro;
    }

    public void setUsuarioRegistro(String usuarioRegistro) {
        this.usuarioRegistro = usuarioRegistro;
    }

    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado;
    }

    public long getCodDocumento() {
        return codDocumento;
    }

    public void setCodDocumento(long codDocumento) {
        this.codDocumento = codDocumento;
    }

    public String getCodUniNegocio() {
        return codUniNegocio;
    }

    public void setCodUniNegocio(String codUniNegocio) {
        this.codUniNegocio = codUniNegocio;
    }

    public String getNomUniNegocio() {
        return nomUniNegocio;
    }

    public void setNomUniNegocio(String nomUniNegocio) {
        this.nomUniNegocio = nomUniNegocio;
    }

    public String getCodEstado() {
        return codEstado;
    }

    public void setCodEstado(String codEstado) {
        this.codEstado = codEstado;
    }

    public String getNomEstado() {
        return nomEstado;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public byte[] getArchivo() {
        return archivo;
    }

    public void setArchivo(byte[] archivo) {
        this.archivo = archivo;
    }

    public String getArchivoMime() {
        return archivoMime;
    }

    public void setArchivoMime(String archivoMime) {
        this.archivoMime = archivoMime;
    }

    public String getArchivoNombre() {
        return archivoNombre;
    }

    public void setArchivoNombre(String archivoNombre) {
        this.archivoNombre = archivoNombre;
    }

    public long getArchivoTamano() {
        return archivoTamano;
    }

    public void setArchivoTamano(long archivoTamano) {
        this.archivoTamano = archivoTamano;
    }

    public int getArchivoNumero() {
        return archivoNumero;
    }

    public void setArchivoNumero(int archivoNumero) {
        this.archivoNumero = archivoNumero;
    }

    public byte[] getArchivo1() {
        return archivo1;
    }

    public void setArchivo1(byte[] archivo1) {
        this.archivo1 = archivo1;
    }

    public String getArchivo1Mime() {
        return archivo1Mime;
    }

    public void setArchivo1Mime(String archivo1Mime) {
        this.archivo1Mime = archivo1Mime;
    }

    public String getArchivo1Nombre() {
        return archivo1Nombre;
    }

    public void setArchivo1Nombre(String archivo1Nombre) {
        this.archivo1Nombre = archivo1Nombre;
    }

    public long getArchivo1Tamano() {
        return archivo1Tamano;
    }

    public void setArchivo1Tamano(long archivo1Tamano) {
        this.archivo1Tamano = archivo1Tamano;
    }

    public int getArchivo1Numero() {
        return archivo1Numero;
    }

    public void setArchivo1Numero(int archivo1Numero) {
        this.archivo1Numero = archivo1Numero;
    }

    public byte[] getArchivo2() {
        return archivo2;
    }

    public void setArchivo2(byte[] archivo2) {
        this.archivo2 = archivo2;
    }

    public String getArchivo2Mime() {
        return archivo2Mime;
    }

    public void setArchivo2Mime(String archivo2Mime) {
        this.archivo2Mime = archivo2Mime;
    }

    public String getArchivo2Nombre() {
        return archivo2Nombre;
    }

    public void setArchivo2Nombre(String archivo2Nombre) {
        this.archivo2Nombre = archivo2Nombre;
    }

    public long getArchivo2Tamano() {
        return archivo2Tamano;
    }

    public void setArchivo2Tamano(long archivo2Tamano) {
        this.archivo2Tamano = archivo2Tamano;
    }

    public int getArchivo2Numero() {
        return archivo2Numero;
    }

    public void setArchivo2Numero(int archivo2Numero) {
        this.archivo2Numero = archivo2Numero;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
