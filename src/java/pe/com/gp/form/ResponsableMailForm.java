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
public class ResponsableMailForm  extends ActionForm implements Serializable {
    String operacion;
    String mail01;
    String mail02;
    String mail03;
    String mail04;
    String mail05;
    String activo1;
    String activo2;
    String activo3;
    String activo4;
    String activo5;
    boolean chk1;
    boolean chk2;
    boolean chk3;
    boolean chk4;
    boolean chk5;

    public boolean isChk2() {
        return chk2;
    }

    public void setChk2(boolean chk2) {
        this.chk2 = chk2;
    }

    public boolean isChk3() {
        return chk3;
    }

    public void setChk3(boolean chk3) {
        this.chk3 = chk3;
    }

    public boolean isChk4() {
        return chk4;
    }

    public void setChk4(boolean chk4) {
        this.chk4 = chk4;
    }

    public boolean isChk5() {
        return chk5;
    }

    public void setChk5(boolean chk5) {
        this.chk5 = chk5;
    }
    
    public boolean isChk1() {
        return chk1;
    }

    public void setChk1(boolean chk1) {
        this.chk1 = chk1;
    }
    
    
    public String getOperacion() {
        return operacion;
    }

    public void setOperacion(String operacion) {
        this.operacion = operacion;
    }
    
    public String getMail01() {
        return mail01;
    }

    public void setMail01(String mail01) {
        this.mail01 = mail01;
    }

    public String getMail02() {
        return mail02;
    }

    public void setMail02(String mail02) {
        this.mail02 = mail02;
    }

    public String getMail03() {
        return mail03;
    }

    public void setMail03(String mail03) {
        this.mail03 = mail03;
    }

    public String getMail04() {
        return mail04;
    }

    public void setMail04(String mail04) {
        this.mail04 = mail04;
    }

    public String getMail05() {
        return mail05;
    }

    public void setMail05(String mail05) {
        this.mail05 = mail05;
    }

    public String getActivo1() {
        return activo1;
    }

    public void setActivo1(String activo1) {
        this.activo1 = activo1;
    }

    public String getActivo2() {
        return activo2;
    }

    public void setActivo2(String activo2) {
        this.activo2 = activo2;
    }

    public String getActivo3() {
        return activo3;
    }

    public void setActivo3(String activo3) {
        this.activo3 = activo3;
    }

    public String getActivo4() {
        return activo4;
    }

    public void setActivo4(String activo4) {
        this.activo4 = activo4;
    }

    public String getActivo5() {
        return activo5;
    }

    public void setActivo5(String activo5) {
        this.activo5 = activo5;
    }
    
    
    
}
