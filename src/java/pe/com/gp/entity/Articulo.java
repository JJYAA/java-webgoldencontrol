package pe.com.gp.entity;

import java.io.Serializable;

public class Articulo implements Serializable {

    private String ItemCode;
    private String ItemName;
    private String U_SYP_MARCA;
    private boolean existe;

    public Articulo() {
    }

    public String getU_SYP_MARCA() {
        return U_SYP_MARCA;
    }

    public void setU_SYP_MARCA(String U_SYP_MARCA) {
        this.U_SYP_MARCA = U_SYP_MARCA;
    }

    public boolean getExiste() {
        return existe;
    }

    public void setExiste(boolean existe) {
        this.existe = existe;
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

}
