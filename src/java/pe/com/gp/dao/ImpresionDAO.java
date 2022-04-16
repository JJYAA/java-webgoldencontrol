/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.Documento;
import pe.com.gp.entity.Parte;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class ImpresionDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public List<Documento> listaDocumentosFacturados(String empresa,String fechaI,String fechaF,String tipoDoc) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Documento obj = null;
        List<Documento> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_listaDocumentosFacturados(?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, fechaI);   
            cs.setString(3, fechaF); 
            cs.setString(4, tipoDoc); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Documento();
                obj.setDocumento(rs.getString("c_n_documentoc"));
                obj.setFecha(rs.getString("d_documentoc"));
                obj.setNombre(rs.getString("c_t_razon_social"));
                obj.setTipoDocumento(rs.getString("t_tipo_doc"));                
                obj.setMoneda(rs.getString("t_moneda"));
                obj.setTipoDocumento(rs.getString("c_c_tipo_doc"));
                obj.setMoneda(rs.getString("c_fl_moneda"));
                obj.setDireccion(rs.getString("direccion"));
                //obj.setTotal(Util.nullCad(rs.getDouble("n_i_total")));
                obj.setTienda(rs.getString("c_c_actividad"));
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
    
   
    public List<Documento> muestraListaRegistroVentas(
            String empresa,
            String codigo,
            String fechaI,
            String fechaF,
            String tipoDoc,
            String actividad,
            String formaPago,
            String tipoVenta ,
            String pendientes
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Documento obj = null;
        List<Documento> list = new ArrayList();
        String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_registro_ventas(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, codigo);   
            cs.setString(3, fechaI);   
            cs.setString(4, fechaF);             
            cs.setString(5, tipoDoc); 
            cs.setString(6, actividad);
            cs.setString(7, formaPago);
            cs.setString(8, tipoVenta);
            cs.setString(9, pendientes);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Documento();
                obj.setDocumento(rs.getString("c_n_documentoc"));
                obj.setFecha(rs.getString("d_documentoc"));
                obj.setNombre(rs.getString("c_t_razon_social"));                           
                obj.setMoneda(rs.getString("moneda"));
                obj.setCodigo(rs.getString("c_c_codigo"));
                //obj.setTipoDocumento(rs.getString("c_c_tipo_doc"));
                //obj.setMoneda(rs.getString("c_fl_moneda"));
                obj.setDireccion(rs.getString("direccion"));
                obj.setTotal(rs.getDouble("n_i_total"));
                obj.setTienda(rs.getString("c_c_actividad"));
                obj.setFormaPago(rs.getString("c_fl_pago"));
                obj.setNeto(rs.getDouble("n_i_neto"));
                obj.setIgv(rs.getDouble("n_i_igv"));
                obj.setTipoVenta(rs.getString("tventa"));
                obj.setTipoDocumentoStr(rs.getString("tipo_doc_str"));
                obj.setTipoDocumento(rs.getString("c_c_tipo_doc"));
                obj.setMigrado(Util.nullCad(rs.getString("migrado")));
                obj.setCantidad_migrada(rs.getInt("cant_migrado"));
                
                obj.setMtv(rs.getString("mtv"));
                obj.setMesv(rs.getString("mesv"));
                obj.setC_c_tipo_operacion(rs.getString("c_c_tipo_operacion"));
                obj.setVou(rs.getString("vou"));
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

    public List<Documento> muestraListaCajeroMigrar(
            String empresa,
            String cajero,
            String fechaI,
            String fechaF,
            String actividad           
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Documento obj = null;
        List<Documento> list = new ArrayList();
        String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call SISCONT_lista_operaciones_cajero(?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, cajero);
            cs.setString(3, fechaI);   
            cs.setString(4, fechaF);               
            cs.setString(5, actividad);   
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Documento();
                obj.setActividad(rs.getString("c_c_actividad"));
                obj.setTienda(rs.getString("t_actividad"));
                obj.setCaja(rs.getString("c_c_caja"));
                obj.setOperacion(rs.getString("n_n_operacion"));
                obj.setNombre(rs.getString("c_t_nombre"));
                obj.setCodigo(rs.getString("c_c_codigo"));
                obj.setFecha(rs.getString("d_fecha"));
                obj.setEstado(rs.getString("c_fl_estado"));
                obj.setMigrado(Util.nullCad(rs.getString("migrado")));  
                obj.setCantidad_migrada(rs.getInt("cantidadMigrado"));
                
                obj.setMtv(rs.getString("mtv"));
                obj.setMesv(rs.getString("mesv"));
                obj.setC_c_tipo_operacion(rs.getString("c_c_tipo_operacion"));
                obj.setVou(rs.getString("vou"));                
                obj.setAnho(rs.getString("anho"));
                
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
