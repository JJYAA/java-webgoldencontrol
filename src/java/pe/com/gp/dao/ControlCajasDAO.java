/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.StockRepuestos;
import pe.com.gp.util.Util;

/**
 *
 * @author Administrador
 */
public class ControlCajasDAO {
    
    private static final Logger LOGGER = LogManager.getLogger();

    public void procesaBoletaAlmacen(
        String empresa ,
        String tipoboleta,
        long numeroBoleta ,
        String actividad ,
        String codAlmacen  ,
        long secuencia
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        StockRepuestos obj = null;
        List<StockRepuestos> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_procesaBoletaAlmacen(?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, tipoboleta);
            cs.setLong(3, numeroBoleta);
            cs.setString(4, actividad);
            cs.setString(5, codAlmacen);
            cs.setLong(6, secuencia);
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
    }    

    
    
    public List<StockRepuestos> muestraBoletaAlmacen(
        String empresa ,
        long secuencia
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        StockRepuestos obj = null;
        List<StockRepuestos> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_muestraBoletaAlmacen(?,?)}");
            cs.setString(1, empresa);            
            cs.setLong(2, secuencia);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new StockRepuestos();
                obj.setCodProducto(rs.getString("c_c_codigo_prod"));                
                obj.setDescripcion(rs.getString("c_t_producto"));
                obj.setCantidad(rs.getLong("cantidad"));
                obj.setCajas(rs.getInt("UNI_POR_CAJAS"));
                obj.setCantidadCajas(rs.getInt("CAJAS_VENDIDAS"));
                obj.setCantidadDev(rs.getLong("CAJAS_DEVUELTAS"));
                obj.setEntregados(rs.getLong("CAJAS_ENTREGADAS"));
                obj.setControlCajas(rs.getLong("CONTROLAR"));
                obj.setControl(rs.getLong("CONTRO_CAJA"));
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

    public String agregarCajasProducto(
        String empresa ,
        long secuencia,
        String producto,
        long cantidad,
        String tipo
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        String resultado = "";
        try { 
            cs = cn.prepareCall("{call web_agregarCajasProducto(?,?,?,?,?)}");
            cs.setString(1, empresa);            
            cs.setLong(2, secuencia);
            cs.setString(3, producto);
            cs.setLong(4, cantidad);
            cs.setString(5, tipo);
            cs.execute();
        } catch (Exception e) {
            resultado = e.toString();
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return resultado;
    }     
    
    public long validaCajasTmp(String empresa, Long secuencia,String producto,long cajas) throws Exception{
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        long resultado = 0;
        try {
            cs = cn.prepareCall("{?=call web_validaCajasTmp(?,?,?,?}");
            cs.registerOutParameter(1, Types.NUMERIC);
            cs.setString(2, empresa);   
            cs.setLong(3, secuencia);   
            cs.setString(4, producto);   
            cs.setLong(5, cajas); 
            cs.execute();
            resultado = cs.getLong(1);
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return resultado;                
    }


    public String procesaControl(
        String empresa ,
        long secuencia ,       
        String responsable ,
        String nota1 ,
        String nota2 ,
        String usuario     
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        String resultado = "exito";
        try { 
            cs = cn.prepareCall("{call web_procesaControl(?,?,?,?,?,?)}");
            cs.setString(1, empresa);            
            cs.setLong(2, secuencia);
            cs.setString(3, responsable);            
            cs.setString(4, nota1);            
            cs.setString(5, nota2);            
            cs.setString(6, usuario);            
            cs.execute();
        } catch (Exception e) {
            resultado = e.toString();
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return resultado;
    }     

    
}
