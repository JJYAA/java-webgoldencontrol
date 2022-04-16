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
import pe.com.gp.entity.Cliente;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class ProveedorDAO {
     private static final Logger LOGGER = LogManager.getLogger();
     
    public String actualizaAccesoWEB(
            String rucEmpresa,
            String rucProveedor,
            String accesos

    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call  relaciones.dbo.actualizaProveedorExtranetWEB(?,?,?)}";
        Object[] paramIN = { rucEmpresa,rucProveedor, accesos};
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));        
        return result;
    }     
    public List<Cliente> listaProveedores(String rucEmpresa,String periodo) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Cliente emp = null;
        List<Cliente> list = new ArrayList();
         String encodedfile = null;       
        try { 
            cs = cn.prepareCall("{call relaciones.dbo.web_listado_proveedores_periodo(?,?)}");
            cs.setString(1, rucEmpresa);       
            cs.setString(2, periodo); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                emp = new Cliente();
                emp.setC_c_ruc_empresa(rucEmpresa);
                emp.setC_c_ruc_proveedor(rs.getString("c_c_codigo"));
                emp.setDireccion(rs.getString("direccion"));
                emp.setTelefono1(rs.getString("telefono1"));               
                emp.setRazon_social(rs.getString("c_t_razon_social"));
                emp.setEmail_01(rs.getString("mail"));
                emp.setAcceso(rs.getString("acceso"));
                list.add(emp);
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
    
    public String actualizaCuentasProveedor(
            String rucProveedor,
            String bcpSoles,
                String bcpDolar ,
                String bbvaSoles ,
                String bbvaDolar ,
                String scotiabankICC ,
                String interbankICC,
                
                String tipoCuentabcpSol,
                String tipoCuentabcpDol,
                    String tipoCuentabbvaSol,
            String tipoCuentabbvaDol

    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call  relaciones.dbo.actualizaCuentasProveedor(?,?,?,?,?,?,?,?,?,?,?)}";
        Object[] paramIN = { rucProveedor, bcpSoles,bcpDolar,bbvaSoles,bbvaDolar,scotiabankICC,interbankICC,tipoCuentabcpSol,tipoCuentabcpDol,tipoCuentabbvaSol,tipoCuentabbvaDol};
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));        
        return result;
    }  
    
    
            
            
   public List<Cliente> datosCuentasProveedor(String rucProveedor) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call relaciones.dbo.muestra_cuentas_proveedores(?)}");
            cs.setString(1, rucProveedor);       
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                cliente = new Cliente();
                cliente.setBcpSoles(rs.getString("cuentabcpsol"));
                cliente.setBcpsolTipo(rs.getString("tipoCtabcpsol"));
                cliente.setBcpDolar(rs.getString("cuentabcpdol"));
                cliente.setBcpdolTipo(rs.getString("tipoCtabcpdol"));
                cliente.setBbvaSoles(rs.getString("cuentabbvasol"));
                cliente.setBbvasolTipo(rs.getString("tipoCtabbvasol"));
                cliente.setBbvaDolar(rs.getString("cuentabbvadol"));
                cliente.setBbvadolTipo(rs.getString("tipoCtabbbvdol"));
                cliente.setScotiabankICC(rs.getString("cuentascotiabankICC"));
                cliente.setInterbankICC(rs.getString("cuentainterbankICC"));
                list.add(cliente);
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
    
    public List<Cliente> datosCuentasEmpresa() throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        Cliente cliente = null;
        List<Cliente> list = new ArrayList();
         String encodedfile = null;
        try { 
            cs = cn.prepareCall("{call relaciones.dbo.lista_cuentas_empresa()}");      
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {                
                cliente = new Cliente();
                cliente.setCuentaBancaria(rs.getString("c_c_cuenta_banco"));
                cliente.setTipoCuenta(rs.getString("tipoCuenta"));
                cliente.setMoneda(rs.getString("c_c_moneda"));
                cliente.setBanco(rs.getString("c_c_banco"));

                list.add(cliente);
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
    
    
public String actualizaCuentasEmpresa(
            String bcpSoles,
                String bcpDolar ,
                
                String tipoCuentabcpSol,
                String tipoCuentabcpDol

    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call  relaciones.dbo.ActualizaCuentasEmpresa(?,?,?,?)}";
        Object[] paramIN = {  bcpSoles,bcpDolar,tipoCuentabcpSol,tipoCuentabcpDol};
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));        
        return result;
    }  
    
        
        
}
