/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.Parte;
import pe.com.gp.util.Util;
import sun.misc.BASE64Encoder;


/**
 *
 * @author Jpalomino
 */
public class StockDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    public List<Parte> muestraListaProductos2() throws Exception {
        List<Parte> list = new ArrayList();
        Parte obj = new Parte();
        obj.setCodigoProducto("BOA212");
        list.add(obj);
        obj = new Parte();
        obj.setCodigoProducto("BIV746");
        list.add(obj);
        return list;
    }    
    
//    public static String encodeToString(BufferedImage image, String type) {
//        String imageString = null;
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();
//
//        try {
//            ImageIO.write(image, type, bos);
//            byte[] imageBytes = bos.toByteArray();
//
//            BASE64Encoder encoder = new BASE64Encoder();
//            imageString = encoder.encode(imageBytes);
//
//            bos.close();
//        } catch (IOException e) {
//            
//        }
//        return imageString;
//    }     
    
    public List<Parte> muestraListaProductos(String empresa,String producto,String chkvalor) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_muestraStockProductos(?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);   
            cs.setString(3, chkvalor); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                obj.setCodigoProducto(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));
                obj.setTotal(rs.getDouble("total"));
                obj.setDisponible(rs.getDouble("disponible"));
                obj.setCajas(rs.getString("desc_caja"));
                obj.setCantidadCajas(rs.getString("nrocajas"));
                obj.setAlmacen(rs.getString("almacenes"));
                Blob blob = rs.getBlob("foto");
                if (blob!=null){
                    byte [] bytes = blob.getBytes(1, (int)blob.length());
                    String bphoto = Base64.encodeBase64String(bytes);
                    obj.setFoto(bphoto);
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
    

    public List<Parte> muestraKardex(
            String empresa,
            String producto,
            String actividad,
            String almacen,
            String fechaIni,
            String fechaFin) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_muestraKardex(?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto); 
            cs.setString(3, actividad); 
            cs.setString(4, almacen); 
            cs.setString(5, fechaIni); 
            cs.setString(6, fechaFin); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                obj.setTipoBoleta(rs.getString("c_c_tipo_bol"));
                obj.setCodigoProducto(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("descripcion"));
                obj.setFechaBoleta(rs.getString("fecha"));//
                obj.setDesc_tipo_boleta(rs.getString("c_t_Secuencia"));
                obj.setBoleta(rs.getInt("n_n_boleta"));
                obj.setFechaBoleta(rs.getString("d_generacion"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setTot_anterior(rs.getLong("total_ant"));
                obj.setDis_anterior(rs.getLong("disponible_ant"));
                obj.setTot_posterior(rs.getLong("total_pos"));
                obj.setDis_posterior(rs.getLong("disponible_pos"));
                obj.setDocuemntoRef(rs.getString("c_n_documentoc"));
                //obj.setTipo(rs.getString("c_t_Secuencia"));
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

    public List<Parte> consultaListaBoletas(
            String empresa,
            String actividad,  
            String tipoBoleta,            
            String almacen,
            String fechaIni,
            String fechaFin) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_consultaListaBoletas(?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, actividad); 
            cs.setString(3, tipoBoleta); 
            cs.setString(4, almacen); 
            cs.setString(5, fechaIni); 
            cs.setString(6, fechaFin); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                //obj.setCodigoProducto(rs.getString("c_c_codigo_prod"));
                //obj.setDescripcion(rs.getString("descripcion"));
                obj.setFechaBoleta(rs.getString("fecha"));//
                obj.setDesc_tipo_boleta(rs.getString("c_t_boleta"));
                obj.setBoleta(rs.getInt("n_n_boleta"));
                obj.setCodigoCliente(rs.getString("c_c_cliente"));
                obj.setNombreCliente(rs.getString("c_t_razon_social"));
                obj.setFechaEntrega(rs.getString("entrega"));
                obj.setUsuario(rs.getString("usuario"));
                obj.setAlmacen(rs.getString("c_t_almacen"));
                obj.setNotaDocu(rs.getString("c_t_nota_docu"));
                obj.setTipoBoleta(rs.getString("c_c_tipo_bol"));
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
