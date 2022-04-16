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
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class ArchivoBancoDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    public List<BeanPreProvision> muestraDocumentoCancelados(
            String pRucEmpresa,
            String pRucProveedor,
            String pFormaPago,
            String tipoBancoMoneda,
            String fechaIni,
            String fechaFin
        ) throws Exception {        
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_pagos_programacion_02(?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pFormaPago); 
            cs.setString(4, tipoBancoMoneda); 
            cs.setString(5, fechaIni); 
            cs.setString(6, fechaFin); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRucProveedor(rs.getString("rucProveedor"));
                obj.setRuc(rs.getString("rut"));
                obj.setRs(rs.getString("rs"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));      
                obj.setDebe(rs.getDouble("debe"));
                obj.setHaber(rs.getDouble("haber"));
                obj.setSaldo(rs.getDouble("haber") - rs.getDouble("debe") );
                if (rs.getString("FECHAPAGO")==null)
                    obj.setFechaProPago("");
                else
                    obj.setFechaProPago(rs.getString("FECHAPAGO"));
                if (rs.getString("FECHAPAGO_GLOSA")==null)
                     obj.setFechaProPago_glosa("");
                else
                    obj.setFechaProPago_glosa(rs.getString("FECHAPAGO_GLOSA"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setCuentaBancaria(rs.getString("cuenta_bancaria"));
                
                
                obj.setCuentaEmpresa(rs.getString("cuentaEmpresa"));
                obj.setCuentaChkSumEmpresa(rs.getString("cuentaChkSumEmpresa"));
                obj.setTipoCuentaEmpresa(rs.getString("tipoCuentaEmpresa"));
                obj.setCuentaProveedor(rs.getString("cuentaProveedor"));
                obj.setCuentaChkSumProveedor(rs.getString("cuentaChkSumProveedor"));
                obj.setTipoCuentaProveedor(rs.getString("tipoCuentaProveedor"));
                obj.setTipoDocumentoPagar(rs.getString("tipoDocumentoPagar"));
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
     
    public List<BeanPreProvision> listaPlanillasGeneradas(
            String pRucEmpresa,
            String fechaIni,
            String fechaFin
        ) throws Exception {        
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.listaPlanillasGeneradas_01(?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, fechaIni); 
            cs.setString(3, fechaFin); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setMes(rs.getString("mesv"));
                obj.setAnho(rs.getString("anho"));
                obj.setTipoComprobante(rs.getString("tipo_operacion"));
                obj.setAsiento(rs.getString("ASIENTO"));
                obj.setRucProveedor(rs.getString("rucProveedor"));
                obj.setRs(rs.getString("rs"));
                obj.setPlanilla(rs.getString("PLANILLA"));
                obj.setGlosa(rs.getString("GLOSA"));
                obj.setNumeroDoc(rs.getString("DOCUMENTO"));
                obj.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
                obj.setDebe(rs.getDouble("IMPORTE"));
                obj.setCuentaBancaria(rs.getString("CUENTABANCO"));
                obj.setCuentaEmpresa(rs.getString("cuentaEmpresa"));
                obj.setCuentaChkSumEmpresa(rs.getString("cuentaChkSumEmpresa"));
                obj.setTipoCuentaEmpresa(rs.getString("tipoCuentaEmpresa"));
                obj.setCuentaProveedor(rs.getString("cuentaProveedor"));
                obj.setCuentaChkSumProveedor(rs.getString("cuentaChkSumProveedor"));
                obj.setTipoCuentaProveedor(rs.getString("tipoCuentaProveedor"));
                obj.setTipoDocumentoPagar(rs.getString("tipoDocumentoPagar"));   
                obj.setHaber(rs.getDouble("haber"));
                obj.setAnulado(rs.getString("anulado"));
                obj.setMoneda(rs.getString("moneda"));
                obj.setFechaContable(rs.getString("D_CONTABLE"));
                obj.setFechaRegistro(rs.getString("D_REGISTRO"));
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
    
    public List<BeanPreProvision> listaPlanillas(
            String pRucEmpresa,
            Long planilla
        ) throws Exception {        
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.listaPlanillas_01(?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setLong(2, planilla); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRucProveedor(rs.getString("rucProveedor"));
                obj.setRuc(rs.getString("rucProveedor"));
                obj.setRs(rs.getString("rs"));
                obj.setPlanilla(rs.getString("PLANILLA"));
                obj.setGlosa(rs.getString("GLOSA"));
                obj.setNumeroDoc(rs.getString("DOCUMENTO"));
                obj.setTipoDocumento(rs.getString("TIPO_DOCUMENTO"));
                obj.setDebe(rs.getDouble("IMPORTE"));                
                obj.setCuentaBancaria(rs.getString("CUENTABANCO"));
                obj.setCuentaEmpresa(rs.getString("cuentaEmpresa"));
                obj.setCuentaChkSumEmpresa(rs.getString("cuentaChkSumEmpresa"));
                obj.setTipoCuentaEmpresa(rs.getString("tipoCuentaEmpresa"));
                obj.setCuentaProveedor(rs.getString("cuentaProveedor"));
                obj.setCuentaChkSumProveedor(rs.getString("cuentaChkSumProveedor"));
                obj.setTipoCuentaProveedor(rs.getString("tipoCuentaProveedor"));
                obj.setTipoDocumentoPagar(rs.getString("tipoDocumentoPagar"));   
                obj.setHaber(rs.getDouble("haber"));
                obj.setMoneda(rs.getString("moneda"));
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
    
    
    public String EliminarVoucherPlanilla(
            String pRucEmpresa,
            Long planilla,
            String mes,
            String anho,
            String tipoComprobante,
            Long voucher,
            String usuario
        ) throws Exception {        
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        String resultado="" ;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.EliminarVoucherPlanilla(?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setLong(2, planilla); 
            cs.setString(3, mes);
            cs.setString(4, anho);
            cs.setString(5, tipoComprobante);
            cs.setLong(6, voucher); 
            cs.setString(7, usuario);
            cs.execute();
            
        } catch (Exception e) {
            resultado = e.toString();
            
        } finally {
            Util.close(cn);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return resultado;
    }     
    
    
        
}
