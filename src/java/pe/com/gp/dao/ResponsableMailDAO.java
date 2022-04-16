/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.BeanResponsablesMail;
import pe.com.gp.entity.Parte;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class ResponsableMailDAO {
private static final Logger LOGGER = LogManager.getLogger();
    
    public List<BeanResponsablesMail> listaMailResponsables(String rucEmpresa) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanResponsablesMail> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.listaMailResponsables(?)}");
            cs.setString(1, rucEmpresa);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanResponsablesMail obj = new BeanResponsablesMail();
                if (rs.getInt("indice")==1){
                    obj.setMail01(rs.getString("mail"));
                    obj.setActivo1(rs.getString("activo"));
                }
                if (rs.getInt("indice")==2){
                    obj.setMail02(rs.getString("mail"));
                    obj.setActivo2(rs.getString("activo"));                    
                }
                if (rs.getInt("indice")==3){
                    obj.setMail03(rs.getString("mail"));
                    obj.setActivo3(rs.getString("activo"));                    
                }
                if (rs.getInt("indice")==4){
                    obj.setMail04(rs.getString("mail"));
                    obj.setActivo4(rs.getString("activo"));                    
                }
                if (rs.getInt("indice")==5){
                    obj.setMail05(rs.getString("mail"));
                    obj.setActivo5(rs.getString("activo"));                    
                }           
                list.add(obj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return list;
    }    

    public void actualizaMail(String rucEmpresa,
            String mail1,String mail2,String mail3,String mail4,String mail5,
            String chk1,String chk2,String chk3,String chk4,String chk5) {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {
            cs = conexion.prepareCall("{call relaciones.dbo.graba_responsables_mail(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, mail1);
            cs.setString(3, mail2);
            cs.setString(4, mail3);
            cs.setString(5, mail4);
            cs.setString(6, mail5);
            cs.setString(7, chk1);
            cs.setString(8, chk2);
            cs.setString(9, chk3);
            cs.setString(10, chk4);
            cs.setString(11, chk5);
            cs.execute();

        } catch (Exception ex) {
            LOGGER.error("ERROR: {}", ex);
            System.out.println(ex.getMessage());
        } finally {
            try {
                conexion.close();
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo : ticketBajasINS ====>");
    }       
}
