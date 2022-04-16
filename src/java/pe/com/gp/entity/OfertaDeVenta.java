package pe.com.gp.entity;

import java.io.Serializable;

public class OfertaDeVenta implements Serializable {

    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    private int DocEntry;
    private int DocNum;
    private String CardCode;
    private String CardName;
    private int CntctCode;
    private String DocStatus;
    private String TaxDate;
    private String DocDueDate;
    private String Comments;
    private int SlpCode;
    private double GrosProfit;
    private double DiscPrcnt;
    private double VatSum;
    private double DocTotal;
    private int OwnerCode;
    private String PayToCode;
    private int TrnspCode;
    private String AgentCode;
    private int GroupNum;
    private String PeyMethod;
    private String U_SYP_CCTIENDA;
    private String U_SYP_CCUN;
    private String DocCur;    
    private boolean existe;
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.
    // LOS ATRIBUTOS DEBEN LLAMARSE COMO LOS CAMPOS SAP. CREERIA QUE LOS DE TIPO FECHA LOS CREEMOS COMO STRING, HAY QUE EVALUAR.

    public OfertaDeVenta() {
    }

    /**
     * Codigo del Documento
     *
     * @return
     */
    public int getDocEntry() {
        return DocEntry;
    }

    /**
     * Codigo del Documento
     *
     * @param DocEntry
     */
    public void setDocEntry(int DocEntry) {
        this.DocEntry = DocEntry;
    }

    /**
     * Numero del Documento
     *
     * @return
     */
    public int getDocNum() {
        return DocNum;
    }

    /**
     * Numero del Documento
     *
     * @param DocNum
     */
    public void setDocNum(int DocNum) {
        this.DocNum = DocNum;
    }

    /**
     * Codigo de Cliente
     *
     * @return
     */
    public String getCardCode() {
        return CardCode;
    }

    /**
     * Codigo de Cliente
     *
     * @param CardCode
     */
    public void setCardCode(String CardCode) {
        this.CardCode = CardCode;
    }

    /**
     * Nombre de Cliente
     *
     * @return
     */
    public String getCardName() {
        return CardName;
    }

    /**
     * Nombre de Cliente
     *
     * @param CardName
     */
    public void setCardName(String CardName) {
        this.CardName = CardName;
    }

    /**
     * Persona de Contacto
     *
     * @return
     */
    public int getCntctCode() {
        return CntctCode;
    }

    /**
     * Persona de Contacto
     *
     * @param CntctCode
     */
    public void setCntctCode(int CntctCode) {
        this.CntctCode = CntctCode;
    }

    /**
     * Estatus del Documento
     *
     * @return
     */
    public String getDocStatus() {
        return DocStatus;
    }

    /**
     * Estatus del Documento
     *
     * @param DocStatus
     */
    public void setDocStatus(String DocStatus) {
        this.DocStatus = DocStatus;
    }

    /**
     * Fecha de Documento
     *
     * @return
     */
    public String getTaxDate() {
        return TaxDate;
    }

    /**
     * Fecha de Documento
     *
     * @param TaxDate
     */
    public void setTaxDate(String TaxDate) {
        this.TaxDate = TaxDate;
    }

    /**
     * Fecha de Vencimiento
     *
     * @return
     */
    public String getDocDueDate() {
        return DocDueDate;
    }

    /**
     * Fecha de Vencimiento
     *
     * @param DocDueDate
     */
    public void setDocDueDate(String DocDueDate) {
        this.DocDueDate = DocDueDate;
    }

    /**
     * Comentarios
     *
     * @return
     */
    public String getComments() {
        return Comments;
    }

    /**
     * Comentarios
     *
     * @param Comments
     */
    public void setComments(String Comments) {
        this.Comments = Comments;
    }

    /**
     * Empleado del Departamento de Ventas
     *
     * @return
     */
    public int getSlpCode() {
        return SlpCode;
    }

    /**
     * Empleado del Departamento de Ventas
     *
     * @param SlpCode
     */
    public void setSlpCode(int SlpCode) {
        this.SlpCode = SlpCode;
    }

    /**
     * Ganancia Bruta
     *
     * @return
     */
    public double getGrosProfit() {
        return GrosProfit;
    }

    /**
     * Ganancia Bruta
     *
     * @param GrosProfit
     */
    public void setGrosProfit(double GrosProfit) {
        this.GrosProfit = GrosProfit;
    }

    /**
     * Porcentaje de descuento para el Documento
     *
     * @return
     */
    public double getDiscPrcnt() {
        return DiscPrcnt;
    }

    /*
    Porcentaje de descuento para el Documento
     */
    public void setDiscPrcnt(double DiscPrcnt) {
        this.DiscPrcnt = DiscPrcnt;
    }

    /**
     * Impuesto Total
     *
     * @return
     */
    public double getVatSum() {
        return VatSum;
    }

    /**
     * Impuesto Total
     *
     * @param VatSum
     */
    public void setVatSum(double VatSum) {
        this.VatSum = VatSum;
    }

    /**
     * Total del Documento
     *
     * @return
     */
    public double getDocTotal() {
        return DocTotal;
    }

    /**
     * Total del Documento
     *
     * @param DocTotal
     */
    public void setDocTotal(double DocTotal) {
        this.DocTotal = DocTotal;
    }

    /**
     * Titular del Documento
     *
     * @return
     */
    public int getOwnerCode() {
        return OwnerCode;
    }

    /**
     * Titular del Documento
     *
     * @param OwnerCode
     */
    public void setOwnerCode(int OwnerCode) {
        this.OwnerCode = OwnerCode;
    }

    /**
     * Pagar a (Destinatario de Factura)
     *
     * @return
     */
    public String getPayToCode() {
        return PayToCode;
    }

    /**
     * Pagar a (Destinatario de Factura)
     *
     * @param PayToCode
     */
    public void setPayToCode(String PayToCode) {
        this.PayToCode = PayToCode;
    }

    /**
     * Forma de Envio
     *
     * @return
     */
    public int getTrnspCode() {
        return TrnspCode;
    }

    /**
     * Forma de Envio
     *
     * @param TrnspCode
     */
    public void setTrnspCode(int TrnspCode) {
        this.TrnspCode = TrnspCode;
    }

    /**
     * Codigo de Agente o Responsable
     *
     * @return
     */
    public String getAgentCode() {
        return AgentCode;
    }

    /**
     * Codigo de Agente o Responsable
     *
     * @param AgentCode
     */
    public void setAgentCode(String AgentCode) {
        this.AgentCode = AgentCode;
    }

    /**
     * Codigo de Condicion de Pago
     *
     * @return
     */
    public int getGroupNum() {
        return GroupNum;
    }

    /**
     * Codigo de Condicion de Pago
     *
     * @param GroupNum
     */
    public void setGroupNum(int GroupNum) {
        this.GroupNum = GroupNum;
    }

    /**
     * Forma de Pago
     *
     * @return
     */
    public String getPeyMethod() {
        return PeyMethod;
    }

    /**
     * Forma de Pago
     *
     * @param PeyMethod
     */
    public void setPeyMethod(String PeyMethod) {
        this.PeyMethod = PeyMethod;
    }

    /**
     * CC Tienda
     *
     * @return
     */
    public String getU_SYP_CCTIENDA() {
        return U_SYP_CCTIENDA;
    }

    /**
     * CC Tienda
     *
     * @param U_SYP_CCTIENDA
     */
    public void setU_SYP_CCTIENDA(String U_SYP_CCTIENDA) {
        this.U_SYP_CCTIENDA = U_SYP_CCTIENDA;
    }

    /**
     * CC Unidad de Negocio
     *
     * @return
     */
    public String getU_SYP_CCUN() {
        return U_SYP_CCUN;
    }

    /**
     * CC Unidad de Negocio
     *
     * @param U_SYP_CCUN
     */
    public void setU_SYP_CCUN(String U_SYP_CCUN) {
        this.U_SYP_CCUN = U_SYP_CCUN;
    }

    /**
     * Moneda del Documento
     *
     * @return
     */
    public String getDocCur() {
        return DocCur;
    }

    /**
     * Moneda del Documento
     *
     * @param DocCur
     */
    public void setDocCur(String DocCur) {
        this.DocCur = DocCur;
    }

    /**
     * Existencia de Documento en la Base de Datos
     *
     * @return
     */
    public boolean getExiste() {
        return existe;
    }

    /**
     * Existencia de Documento en la Base de Datos
     *
     * @param existe
     */
    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
