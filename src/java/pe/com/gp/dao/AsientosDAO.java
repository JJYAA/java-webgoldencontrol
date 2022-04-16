/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class AsientosDAO {
          private static final Logger LOGGER = LogManager.getLogger();
        public String AsientoVentas(
            String empresa,
            String tipoDocumento,
            String documento,
            String actividad,
            String usuario
) throws Exception {
        LOGGER.info("<==== Inicio Metodo: AsientoVentas ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado="";
        try { 
            CallableStatement cs = cn.prepareCall("{call SISCONT_ASIENTO_VENTA(?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, tipoDocumento);   
            cs.setString(3, documento);
            cs.setString(4, actividad);
            cs.setString(5, usuario);
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);
            cs.execute();
            resultado = cs.getString(6);
        } catch (Exception e) {
            resultado = e.toString();
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: AsientoVentas ====>");
        return resultado;
    }
    
    public String conEliminarAsiento(
            String pEmpresa,
            String pTipoDoc,
            String pDocumento,
            String pActividad,
            String mtv,
            String mesv,
            String anho,
            String tipoOperacion,            
            long voucher  ,       
            String proceso
         ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: conAsientoCajero ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado="";
        try { 
            CallableStatement cs = cn.prepareCall("{call SISCONT_ELIMINA_ASIENTO(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pTipoDoc);
            cs.setString(3, pDocumento);
            cs.setString(4, pActividad);            
            cs.setString(5, mtv);
            cs.setString(6, mesv); 
            cs.setString(7, anho); 
            cs.setString(8, tipoOperacion); 
            cs.setLong(9, voucher);
            cs.setString(10, proceso);
            cs.registerOutParameter(11, java.sql.Types.VARCHAR);
            cs.execute();
            resultado = cs.getString(11);
            if (resultado==null) resultado="";
        } catch (Exception e) {
            resultado = e.toString();
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: conAsientoCajero ====>");
        return resultado;
    }

            
            
    public String conAsientoCajero(
            String empresa,
            String actividad,
            String caja,
            long  operacion,
            long nroAsiento           
         ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: conAsientoCajero ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado="";
        try { 
            CallableStatement cs = cn.prepareCall("{call SISCONT_ASIENTO_CANCELACION(?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, actividad); 
            cs.setString(3, caja); 
            cs.setLong(4, operacion);
            cs.setLong(5, nroAsiento);
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);
            cs.execute();
            resultado = cs.getString(6);
            if (resultado==null) resultado="";
        } catch (Exception e) {
            resultado = e.toString();
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: conAsientoCajero ====>");
        return resultado;
    }
                
        
}
