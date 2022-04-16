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
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.StockRepuestos;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class CambioEstadoDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    public List<StockRepuestos> muestraProductos(String empresa,String producto) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<StockRepuestos> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call web_producto_stock(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);   
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                StockRepuestos obj = new StockRepuestos();
                obj.setCodigoProducto(rs.getString("c_c_codigo_prod"));
                obj.setDescripcion(rs.getString("c_t_codigo_prod"));
                obj.setStockTotal(Util.nullCad(rs.getInt("total")));
                obj.setStockDisponible(Util.nullCad(rs.getInt("disponible")));
                obj.setStockAlm(Util.nullCad(rs.getInt("almacenes")));
                obj.setStockSeguridad(Util.nullCad(rs.getInt("seguridad")));
                obj.setUltCosPro(rs.getDouble("u_costo_prom"));
                obj.setUltCosto(rs.getDouble("ultimo_costo"));
                obj.setFechaUltimaSalida(rs.getString("ultima_salida"));
                obj.setFechaUltimoIngreso(rs.getString("ultimo_ingreso"));
                obj.setStockTemporal(rs.getInt("temporal"));
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
    
    public String GrabarCambioEstado(
            String empresa,
            String producto,
            Double costoPromedio,
            Double UltimoCosto,
            Double total,
            Double disponible,
            Double seguridad,
            Double almacenes,
            Long temporal) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado="";
        try { 
            CallableStatement cs = cn.prepareCall("{call web_actualiza_cambio_estado(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, producto);   
            cs.setDouble(3, costoPromedio);
            cs.setDouble(4, UltimoCosto);
            cs.setDouble(5, total);
            cs.setDouble(6, disponible);
            cs.setDouble(7, seguridad);
            cs.setDouble(8, almacenes);
            cs.setDouble(9, temporal);
            cs.execute();
        } catch (Exception e) {
            resultado = e.toString();
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return resultado;
    }
    
    
    
}
