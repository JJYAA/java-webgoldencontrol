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
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.util.Util;

/**
 *
 * @author Administrador
 */
public class AlmacenesDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public List<ListaGenerica> listaAlmacenes(String empresa ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaGenerica obj = null;
        List<ListaGenerica> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_lista_almacenes(?)}");
            cs.setString(1, empresa);            
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new ListaGenerica();
                obj.setIndice(rs.getString("indice"));
                obj.setDescripcion(rs.getString("descripcion"));
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
    
    public List<ListaGenerica> listaTipoBoleta(String empresa ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaGenerica obj = null;
        List<ListaGenerica> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_lista_TipoBoleta(?)}");
            cs.setString(1, empresa);            
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new ListaGenerica();
                obj.setIndice(rs.getString("indice"));
                obj.setDescripcion(rs.getString("descripcion"));
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
    
}
