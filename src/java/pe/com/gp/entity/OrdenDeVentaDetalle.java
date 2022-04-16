package pe.com.gp.entity;

import java.io.Serializable;

public class OrdenDeVentaDetalle implements Serializable {

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
    private int LineNum;
    private String ItemCode;
    private String Dscription;
    private double Price;
    private double DiscPrcnt;
    private int Quantity;
    private String TaxCode;
    private String AcctCode;
    private String WhsCode;
    private String OcrCode2;
    private String OcrCode;
    private String U_SYP_SERV_REP;
    private String U_SYP_COPROVEEDOR;
    private String U_SYP_NOPROVEEDOR;
    private String LineStatus;
    private double PriceBefDi;
    private String Currency;
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

    public OrdenDeVentaDetalle() {
    }

    /**
     * Moneda del precio
     *
     * @return
     */
    public String getCurrency() {
        return Currency;
    }

    /**
     * Moneda del precio
     *
     * @param Currency
     */
    public void setCurrency(String Currency) {
        this.Currency = Currency;
    }

    /**
     * Precio antes del descuento
     *
     * @return
     */
    public double getPriceBefDi() {
        return PriceBefDi;
    }

    /**
     * Precio antes del descuento
     *
     * @param PriceBefDi
     */
    public void setPriceBefDi(double PriceBefDi) {
        this.PriceBefDi = PriceBefDi;
    }

    /**
     * Numero de Linea
     *
     * @return
     */
    public int getLineNum() {
        return LineNum;
    }

    /**
     * Numero de Linea
     *
     * @param LineNum
     */
    public void setLineNum(int LineNum) {
        this.LineNum = LineNum;
    }

    /**
     * Codigo del Item
     *
     * @return
     */
    public String getItemCode() {
        return ItemCode;
    }

    /**
     * Codigo del Item
     *
     * @param ItemCode
     */
    public void setItemCode(String ItemCode) {
        this.ItemCode = ItemCode;
    }

    /**
     * Descripcion del Item
     *
     * @return
     */
    public String getDscription() {
        return Dscription;
    }

    /**
     * Descripcion del Item
     *
     * @param Dscription
     */
    public void setDscription(String Dscription) {
        this.Dscription = Dscription;
    }

    /**
     * Precio del Item
     *
     * @return
     */
    public double getPrice() {
        return Price;
    }

    /**
     * Precio del Item
     *
     * @param Price
     */
    public void setPrice(double Price) {
        this.Price = Price;
    }

    /**
     * Descuento por Linea
     *
     * @return
     */
    public double getDiscPrcnt() {
        return DiscPrcnt;
    }

    /**
     * Descuento por Linea
     *
     * @param DiscPrcnt
     */
    public void setDiscPrcnt(double DiscPrcnt) {
        this.DiscPrcnt = DiscPrcnt;
    }

    /**
     * Cantidad
     *
     * @return
     */
    public int getQuantity() {
        return Quantity;
    }

    /**
     * Cantidad
     *
     * @param Quantity
     */
    public void setQuantity(int Quantity) {
        this.Quantity = Quantity;
    }

    /**
     * Indicador de Impuestos
     *
     * @return
     */
    public String getTaxCode() {
        return TaxCode;
    }

    /**
     * Indicador de Impuestos
     *
     * @param TaxCode
     */
    public void setTaxCode(String TaxCode) {
        this.TaxCode = TaxCode;
    }

    /**
     * Codigo de Cuenta (Cuenta de Mayor)
     *
     * @return
     */
    public String getAcctCode() {
        return AcctCode;
    }

    /**
     * Codigo de Cuenta (Cuenta de Mayor)
     *
     * @param AcctCode
     */
    public void setAcctCode(String AcctCode) {
        this.AcctCode = AcctCode;
    }

    /**
     * Codigo de Almacen
     *
     * @return
     */
    public String getWhsCode() {
        return WhsCode;
    }

    /**
     * Codigo de Almacen
     *
     * @param WhsCode
     */
    public void setWhsCode(String WhsCode) {
        this.WhsCode = WhsCode;
    }

    /**
     * CC Tienda
     *
     * @return
     */
    public String getOcrCode2() {
        return OcrCode2;
    }

    /**
     * CC Tienda
     *
     * @param OcrCode2
     */
    public void setOcrCode2(String OcrCode2) {
        this.OcrCode2 = OcrCode2;
    }

    /**
     * CC Unidad de Negocio (Norma de Reparto)
     *
     * @return
     */
    public String getOcrCode() {
        return OcrCode;
    }

    /**
     * CC Unidad de Negocio (Norma de Reparto)
     *
     * @param OcrCode
     */
    public void setOcrCode(String OcrCode) {
        this.OcrCode = OcrCode;
    }

    /**
     * Servicio - Repuesto
     *
     * @return
     */
    public String getU_SYP_SERV_REP() {
        return U_SYP_SERV_REP;
    }

    /**
     * Servicio - Repuesto
     *
     * @param U_SYP_SERV_REP
     */
    public void setU_SYP_SERV_REP(String U_SYP_SERV_REP) {
        this.U_SYP_SERV_REP = U_SYP_SERV_REP;
    }

    /**
     * Codigo Proveedor
     *
     * @return
     */
    public String getU_SYP_COPROVEEDOR() {
        return U_SYP_COPROVEEDOR;
    }

    /**
     * Codigo Proveedor
     *
     * @param U_SYP_COPROVEEDOR
     */
    public void setU_SYP_COPROVEEDOR(String U_SYP_COPROVEEDOR) {
        this.U_SYP_COPROVEEDOR = U_SYP_COPROVEEDOR;
    }

    /**
     * Nombre Proveedor
     *
     * @return
     */
    public String getU_SYP_NOPROVEEDOR() {
        return U_SYP_NOPROVEEDOR;
    }

    /**
     * Nombre Proveedor
     *
     * @param U_SYP_NOPROVEEDOR
     */
    public void setU_SYP_NOPROVEEDOR(String U_SYP_NOPROVEEDOR) {
        this.U_SYP_NOPROVEEDOR = U_SYP_NOPROVEEDOR;
    }

    /**
     * Estatus de la Linea
     *
     * @return
     */
    public String getLineStatus() {
        return LineStatus;
    }

    /**
     * Estatus de la Linea
     *
     * @param LineStatus
     */
    public void setLineStatus(String LineStatus) {
        this.LineStatus = LineStatus;
    }

}
