package pe.com.gp.entity;

import java.io.Serializable;

public class TarjetaDeEquipo implements Serializable {

    private String customer;
    private String custmrName;
    private String manufSN;
    private String internalSN;
    private String itemCode;
    private String itemName;
    private boolean existe;

    public TarjetaDeEquipo() {
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCustmrName() {
        return custmrName;
    }

    public void setCustmrName(String custmrName) {
        this.custmrName = custmrName;
    }

    public String getManufSN() {
        return manufSN;
    }

    public void setManufSN(String manufSN) {
        this.manufSN = manufSN;
    }

    public String getInternalSN() {
        return internalSN;
    }

    public void setInternalSN(String internalSN) {
        this.internalSN = internalSN;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
    }

}
