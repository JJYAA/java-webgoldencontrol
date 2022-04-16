/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.form;

import java.io.Serializable;
import org.apache.struts.action.ActionForm;

/**
 *
 * @author Computer
 */
public class ClientesForm extends ActionForm implements Serializable {
    private String operacion;
    private String proceso;
    private String bcpSoles; 
    private String bcpsolTipo;
    private String bcpDolar;
    private String bcpdolTipo;
    private String bbvaSoles;
    private String bbvasolTipo;
    private String bbvaDolar;
    private String bbvadolTipo;
    private String scotiabankICC;
    private String interbankICC;

    public String getBcpsolTipo() {
        return bcpsolTipo;
    }

    public void setBcpsolTipo(String bcpsolTipo) {
        this.bcpsolTipo = bcpsolTipo;
    }

    public String getBcpdolTipo() {
        return bcpdolTipo;
    }

    public void setBcpdolTipo(String bcpdolTipo) {
        this.bcpdolTipo = bcpdolTipo;
    }

    public String getBbvasolTipo() {
        return bbvasolTipo;
    }

    public void setBbvasolTipo(String bbvasolTipo) {
        this.bbvasolTipo = bbvasolTipo;
    }

    public String getBbvadolTipo() {
        return bbvadolTipo;
    }

    public void setBbvadolTipo(String bbvadolTipo) {
        this.bbvadolTipo = bbvadolTipo;
    }
    
    

    public String getBcpSoles() {
        return bcpSoles;
    }

    public void setBcpSoles(String bcpSoles) {
        this.bcpSoles = bcpSoles;
    }

    public String getBcpDolar() {
        return bcpDolar;
    }

    public void setBcpDolar(String bcpDolar) {
        this.bcpDolar = bcpDolar;
    }

    public String getBbvaSoles() {
        return bbvaSoles;
    }

    public void setBbvaSoles(String bbvaSoles) {
        this.bbvaSoles = bbvaSoles;
    }

    public String getBbvaDolar() {
        return bbvaDolar;
    }

    public void setBbvaDolar(String bbvaDolar) {
        this.bbvaDolar = bbvaDolar;
    }

    public String getScotiabankICC() {
        return scotiabankICC;
    }

    public void setScotiabankICC(String scotiabankICC) {
        this.scotiabankICC = scotiabankICC;
    }

    public String getInterbankICC() {
        return interbankICC;
    }

    public void setInterbankICC(String interbankICC) {
        this.interbankICC = interbankICC;
    }

    
    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }
    
    

    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    
}
