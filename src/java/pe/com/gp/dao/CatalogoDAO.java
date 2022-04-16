/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import org.apache.commons.codec.binary.Base64;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Parte;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class CatalogoDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public void EliminarItemCatalogo(Long secuencia, String empresa, String producto){
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {

            cs = conexion.prepareCall("{call web_EliminarItemCatalogo(?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, empresa);
            cs.setString(3, producto);
            cs.execute();
        } catch (SQLException e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            try {
                conexion.close(); // libera cn
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo: Genera_boleta ====>");        
    }
    

    public void AgregarItemCatalogo(Long secuencia, String empresa, String producto,Long cantidad,Double Precio){
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {

            cs = conexion.prepareCall("{call web_AgregarItemCatalogo(?,?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, empresa);
            cs.setString(3, producto);
            cs.setLong(4, cantidad);
            cs.setDouble(5, Precio);
            cs.execute();
        } catch (SQLException e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            try {
                conexion.close(); // libera cn
            } catch (Exception e) {
            }

            try {
                if (cs != null) {
                    cs.close();
                }
            } catch (Exception e) {
            }
        }
        LOGGER.info("<==== Fin Metodo: Genera_boleta ====>");        
    }
    
    public  List<Parte> muestraListaItem(String empresa,Long secuencia,String PathImg) throws Exception{
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
        
        try { 
            cs = cn.prepareCall("{call web_lista_tempo_catalogo(?,?)}");
            cs.setString(1, empresa);
            cs.setLong(2, secuencia);   
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                obj = new Parte();
                obj.setCodigoProducto(rs.getString("producto"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));
                obj.setCantidad(rs.getInt("cantidad"));
                obj.setVVPSol(rs.getDouble("precio"));
                obj.setFoto("");
                Blob blob = rs.getBlob("foto");
                if (blob!=null){
                    byte [] bytes = blob.getBytes(1, (int)blob.length());
                    if (bytes.length==0){
                        obj.setFoto("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");
                    }
                    else
                    {
                        
                        String bphoto = Base64.encodeBase64String(bytes);
                        obj.setFoto(bphoto);
                    }                    
                }
                else
                {
                    obj.setFoto("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");
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
    
    public List<ListaGenerica> listaFamilia(String empresa,String familia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaGenerica obj = null;
        List<ListaGenerica> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_lista_grupo(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, familia);   
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
    
    public List<ListaGenerica> listaClase(String empresa,String familia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaGenerica obj = null;
        List<ListaGenerica> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_lista_Clase(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, familia);   
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
    
    public List<Parte> muestraListaProductos(String empresa,String PathImg,String familia,String clase,
            String grupo,
            String filtro,
            String codigoProducto) throws Exception {
        
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
        String encodedfile = null;
        boolean existe=false;
        try { 
            cs = cn.prepareCall("{call web_muestra_catalogoproductosStock(?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, familia);   
            cs.setString(3, clase); 
            cs.setString(4, grupo); 
            cs.setString(5, filtro); 
            cs.setString(6, codigoProducto); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                existe=true;
                obj = new Parte();
                obj.setCodigoProducto(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));
                obj.setCantidadCajas(Util.nullCad(rs.getDouble("desc_caja")));
                obj.setDual(rs.getString("duas"));
                obj.setFamilia(rs.getString("familia"));
                obj.setClase(rs.getString("clase"));
                obj.setGrupo(rs.getString("grupo"));
                obj.setVVPDol(rs.getDouble("vvp_soles"));
                obj.setVVPSol(rs.getDouble("vvp_dolar"));
                obj.setMasPrecios(rs.getString("c_c_mas_precio"));
                obj.setAnulado(rs.getString("c_fl_estado"));
                Blob blob = rs.getBlob("foto");
                if (blob!=null){
                    byte [] bytes = blob.getBytes(1, (int)blob.length());
                    if (bytes.length==0)
                        obj.setFoto("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");
                    else
                    {    
                        String bphoto = Base64.encodeBase64String(bytes);
                        obj.setFoto(bphoto);
                    }                        
                }
                else
                    obj.setFoto("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");
                
                obj.setPrecioItem(rs.getDouble("precioItem"));
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
        if (!existe) list=null;
        return list;
    }
    

    public List<ListaGenerica> listaGrupo(String empresa,String clase ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        ListaGenerica obj = null;
        List<ListaGenerica> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_lista_Grupo(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, clase);   
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
