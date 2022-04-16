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
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.StockRepuestos;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.form.ProductosForm;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class ProductosDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public List<Parte> muestraProductos(String empresa,String producto,String tipo,String filtro) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<Parte> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call web_muestra_productosStock(?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);  
            cs.setString(3, tipo);
            cs.setString(4, filtro);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                Parte obj = new Parte();
                obj.setCodigoProducto(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));
                obj.setDual(rs.getString("duas"));
                obj.setFamilia(rs.getString("familia"));
                obj.setClase(rs.getString("clase"));
                obj.setGrupo(rs.getString("grupo"));
                obj.setCantidadCajas(rs.getString("desc_caja"));
                obj.setVVPDol(rs.getDouble("vvp_dolar"));
                obj.setVVPSol(rs.getDouble("vvp_soles"));
                obj.setMasPrecios(rs.getString("c_c_mas_precio"));
                obj.setAnulado(rs.getString("c_fl_estado"));  
                obj.setPrecio01(rs.getDouble("precio01"));
                obj.setPrecio02(rs.getDouble("precio02"));
                obj.setPrecio03(rs.getDouble("precio03"));
                obj.setPrecio04(rs.getDouble("precio04"));
                obj.setPrecio05(rs.getDouble("precio05"));
                
                obj.setCostoProm(rs.getDouble("u_costo_prom"));
                obj.setUltCosto(rs.getDouble("ultimo_costo"));
                obj.setStockTotal(rs.getString("total"));
                obj.setStockDisponible(rs.getString("disponible"));
                obj.setStockSeguridad(rs.getString("seguridad"));
                obj.setStockAlmacenes(rs.getString("almacenes"));
                obj.setStockTemporal(rs.getString("temporal"));
                obj.setStockTotal(rs.getString("total"));
                obj.setFechaUltimaSalida(rs.getString("ultima_salida"));
                obj.setFechaUltimoIngreso(rs.getString("ultimo_ingreso"));
                
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

    public List<Parte> muestraListaProductos(String empresa,String producto,String tipo,String chkvalor) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Parte obj = null;
        List<Parte> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call web_muestra_productosStock(?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);   
            cs.setString(3, tipo); 
            cs.setString(4, chkvalor); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
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
        return list;
    }
    
    public void Grabaimagenes(String empresa,String producto,  byte[] zip) {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {
            cs = conexion.prepareCall("{call web_grabar_imagen(?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);
            cs.setBytes(3, zip);
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

    public void GrabaProducto(String empresa,ProductosForm uform) {
        LOGGER.info("<==== Inicio Metodo : ticketBajasINS ====>");
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        try {
            cs = conexion.prepareCall("{call web_GrabaProducto(?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,?,?,?,?,?,?,?,"
                    + "?,?,?,? )}");
            cs.setString(1, empresa);
            cs.setString(2, uform.getNumeroParte());
            cs.setString(3, uform.getDescripcion());
            cs.setDouble(4, uform.getVvpSoles());
            cs.setDouble(5, uform.getVvpDolar());
            cs.setString(6, uform.getActivo());
            cs.setString(7, (uform.getMasPrecios()));
            cs.setLong(8,uform.getCantidadCaja());
            cs.setString(9, uform.getDuas() );
            cs.setString(10, uform.getFamilia());
            cs.setString(11, uform.getClase());
            cs.setString(12, uform.getGrupo());
            
            cs.setDouble(13,Util.nullDou(uform.getPrecios01()));
            cs.setDouble(14,Util.nullDou(uform.getPrecios02()));
            cs.setDouble(15,Util.nullDou(uform.getPrecios03()));
            cs.setDouble(16,Util.nullDou(uform.getPrecios04()));
            cs.setDouble(17,Util.nullDou(uform.getPrecios05()));
            
            cs.setDouble(18,uform.getCostoPromedio());
            cs.setDouble(19,uform.getUltimoCosto());
            cs.setDouble(20,Util.nullDou(uform.getStockTotal()));
            cs.setDouble(21,Util.nullDou(uform.getStockDisponible()));
            cs.setDouble(22,Util.nullDou(uform.getStockSeguridad()));
            cs.setDouble(23,Util.nullDou(uform.getStockAlmacen()));
            cs.setDouble(24,Util.nullDou(uform.getStockTemporal()));
            //cs.setBytes(3, zip);
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
