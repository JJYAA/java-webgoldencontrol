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
import pe.com.gp.entity.Cliente;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class ClienteDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public Cliente getDatosCliente(String empresa , String codigo) throws Exception {
        Cliente cliente = null;
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call web_busca_cliente(?,?)}");
            cs.setString(1, empresa);
            cs.setString(2, codigo);
            cs.execute();
            rs = (ResultSet) cs.getResultSet(); 
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setPaterno(rs.getString("c_t_paterno"));
                cliente.setMaterno(rs.getString("c_t_materno"));
                cliente.setPrimer_nombre(rs.getString("c_t_nombre_primero"));
                cliente.setSegundo_nombre(rs.getString("c_t_nombre_segundo"));
                cliente.setDireccion(rs.getString("direccion"));
                cliente.setRazon_social(rs.getString("c_t_razon_social"));
                cliente.setTelefono1(rs.getString("telefono1"));
                cliente.setTelefono2(rs.getString("telefono2"));
                cliente.setCelular1(rs.getString("celular1"));                 
                cliente.setCodigo(rs.getString("c_c_codigo"));
                cliente.setTipo_persona(rs.getString("c_c_tipo_persona"));                
                cliente.setTipo_documento(rs.getString("c_c_tipo_documento"));
                cliente.setFormaPago(rs.getString("formaPago"));
                cliente.setUbigeo(rs.getString("ubigeo"));
                cliente.setDepart(rs.getString("departamento"));
                cliente.setProvin(rs.getString("provincia"));
                cliente.setDistri(rs.getString("distrito"));
                cliente.setExiste(true);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
       return cliente; 
    }
    
    public String actualizaCliente(Cliente cliente) throws Exception {
        LOGGER.info("<==== Inicio Metodo: Agrega_Item_tmp ====>");
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        String result="";
        try {

            cs = conexion.prepareCall("{call web_crea_cliente_02("
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?,?,?,?,?,"
                    + "?)}");
            cs.setString(1, cliente.getEmpresa());
            cs.setString(2, cliente.getCodigo());
            cs.setString(3, cliente.getAuxiliar());
            cs.setString(4, cliente.getRazon_social());
            cs.setString(5, cliente.getPaterno());
            cs.setString(6, cliente.getMaterno());
            cs.setString(7, cliente.getPrimer_nombre());
            cs.setString(8, cliente.getSegundo_nombre());
            cs.setString(9, cliente.getTipo_persona());
            cs.setString(10, cliente.getTipo_documento());
            cs.setString(11, cliente.getDireccion()) ;
            cs.setString(12, cliente.getFormaPago()) ;
            cs.setString(13, cliente.getTelefono1());      
            cs.setString(14, cliente.getTelefono2());
            cs.setString(15, cliente.getCelular1());
            cs.setString(16, cliente.getUbigeo());
            cs.execute();
        } catch (Exception e) {
            result = e.toString();
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
        return result;
    }
    
    
}
