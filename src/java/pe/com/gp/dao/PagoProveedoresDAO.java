/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.Banco;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Usuario;
import pe.com.gp.util.Util;
/**
 *
 * @author Computer
 */
public class PagoProveedoresDAO {
    private static final Logger LOGGER = LogManager.getLogger();
    
public List<Banco> asignarCuentasProveedor(
            String pRucEmpresa,
            String pRucProveedor,
            String cuenta
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<Banco> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_cuentas_proveedor_02(?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, cuenta); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                Banco banco = new Banco();
                banco.setCuentaBancaria(rs.getString("CUENTABANCARIA"));
                banco.setTipoCuenta(rs.getString("TIPOCUENTA"));
                banco.setTipoCuenta_t(rs.getString("T_TIPOCUENTA"));
                list.add(banco);
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
        
public List<BeanPreProvision> muestraDocumentoPendientes(
            String pRucEmpresa,
            String pRucProveedor,
            String pFormaPago,
            String periodo
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            /*ACTUALIZA CUENTAS BANCARIOAS*/
            
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_documentosPendientes_02(?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pFormaPago); 
            cs.setString(4, periodo); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRuc(rs.getString("rut"));
                obj.setRs(rs.getString("rs"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));      
                obj.setDebe(rs.getDouble("debe"));
                obj.setHaber(rs.getDouble("haber"));
                obj.setSaldo(rs.getDouble("saldo"));
                if (rs.getString("FECHAPAGO")==null)
                    obj.setFechaProPago("");
                else
                    obj.setFechaProPago(rs.getString("FECHAPAGO"));
                if (rs.getString("FECHAPAGO_GLOSA")==null)
                     obj.setFechaProPago_glosa("");
                else
                    obj.setFechaProPago_glosa(rs.getString("FECHAPAGO_GLOSA"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setCuentaBancaria(rs.getString("CUENTABANCO"));
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
    
    public List<BeanPreProvision> muestraDocumentoPendientes_02(
            String pRucEmpresa,
            String pRucProveedor,
            String pFormaPago,
            String periodo
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_documentosPendientes_03(?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pFormaPago); 
            cs.setString(4, periodo); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRuc(rs.getString("rut"));
                obj.setRs(rs.getString("rs"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));      
                obj.setDebe(rs.getDouble("debe"));
                obj.setHaber(rs.getDouble("haber"));
                obj.setSaldo(rs.getDouble("saldo"));
                if (rs.getString("FECHAPAGO")==null)
                    obj.setFechaProPago("");
                else
                    obj.setFechaProPago(rs.getString("FECHAPAGO"));
                if (rs.getString("FECHAPAGO_GLOSA")==null)
                     obj.setFechaProPago_glosa("");
                else
                    obj.setFechaProPago_glosa(rs.getString("FECHAPAGO_GLOSA"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setCuentaBancaria(rs.getString("CUENTABANCO"));
                obj.setCuentaProv(rs.getString("cuentaProv"));
                        
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
        
    public List<BeanPreProvision> HelpDocumentosPendientes(
            String pRucEmpresa,
            String pFormaPago,
            String pPeriodo
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_HelpDocumentosPendientes(?,?,?)}");
            cs.setString(1, pRucEmpresa);             
            cs.setString(2, pFormaPago); 
            cs.setString(3, pPeriodo); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRuc(rs.getString("RUT"));
                obj.setRs(rs.getString("RS"));      
                obj.setHaber(rs.getDouble("SALDO"));
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
    
    public Double saldoDocumento(
            String pRucEmpresa,
            String pFormaPago,
            String pProveedor,
            String pTipoDocumento,
            String pDocumento
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        double saldo = 0;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.SALDODOCUMENTOPORPAGAR(?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);  
            cs.setString(2, pFormaPago); 
            cs.setString(3, pProveedor);
            cs.setString(4, pTipoDocumento);
            cs.setString(5, pDocumento);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();    
                saldo = rs.getDouble("SALDO");
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
        return saldo;
    }  

public void crearTMPDocumento(
            Long pSecuencia,
            String pFecha,
            String pRucEmpresa,
            String pFormaPago,
            String pProveedor,
            String pTipoDocumento,
            String pDocumento,
            double pPago,
            String pGlosa,
            String cuentaBanco
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        double saldo = 0;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.crearTMPDocumento(?,?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, pSecuencia); 
            cs.setString(2, pFecha); 
            cs.setString(3, pRucEmpresa);  
            cs.setString(4, pFormaPago); 
            cs.setString(5, pProveedor);
            cs.setString(6, pTipoDocumento);
            cs.setString(7, pDocumento);
            cs.setDouble(8, pPago);
            cs.setString(9, pGlosa);
            cs.setString(10, cuentaBanco);
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
        
    
    public String ActualizaDocumentoProgramacion(         
            String pRucEmpresa,
            String pProveedor,
            String pTipoDocumento,
            String pDocumento,
            String pFechaPago,
            String pGlosa,
            String periodo,
            String formaPago,
            String cuentaBanco,
            String cuenta
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String result="";
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.ActualizaDocumentoProgramacion(?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);  
            cs.setString(2, pProveedor.trim());
            cs.setString(3, pTipoDocumento);
            cs.setString(4, pDocumento.trim());
            cs.setString(5, pFechaPago);
            cs.setString(6, pGlosa); 
            cs.setString(7, periodo); 
            cs.setString(8, formaPago); 
            cs.setString(9, cuentaBanco); 
            cs.setString(10, cuenta); 
            cs.execute();            
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            result = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return result;
    }  
    
    public void eliminarTMPDocumento(
            Long pSecuencia,
            String pFecha,
            String pRucEmpresa
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.elimina_secuencia_empresa(?,?,?)}");
            cs.setLong(1, pSecuencia); 
            cs.setString(2, pFecha); 
            cs.setString(3, pRucEmpresa);  
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
     
    
    public JSONObject GrabarPagoProveedor(Long Secuencia,String rucEmpresa,String rucProveedor,String formaPago,String fechacontable){
        JSONObject jsonObjOut;
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        String msg="";  
        String result=""; 
        String documento="";
        try {
            cs = conexion.prepareCall("{call relaciones.dbo.GrabarPagoProveedor(?,?,?,?,?,?,?,?)}");
            cs.setLong(1, Secuencia);
            cs.setString(2, rucEmpresa);      
            cs.setString(3, rucProveedor);           
            cs.setString(4, formaPago);
            cs.setString(5, fechacontable);
            cs.registerOutParameter(6, java.sql.Types.VARCHAR);
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.registerOutParameter(8, java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getString(6);
            msg = cs.getString(7);
            documento = cs.getString(8);
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("msg", msg);
            jsonObjMsg.put("tipoMsg",result);
            jsonObjMsg.put("documento", documento);
            jsonArrMsg.put(jsonObjMsg);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("mensaje", jsonArrMsg);            
        }catch (Exception e) {
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("tipoMsg", "error");
            jsonObjMsg.put("msg", "" + e);
            jsonArrMsg.put(jsonObjMsg);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("mensaje", jsonArrMsg);
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

       return jsonObjOut;
    }

    public List<BeanPreProvision> muestraDocumentoPendientesRango(
            String pRucEmpresa,
            String pRucProveedor,
            String pFormaPago,
            String periodo,
            String fechaIni,
            String fechaFin
        ) throws Exception {        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.LISTA_DOCUMENTOSPENDIENTES_RANGO(?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pFormaPago); 
            cs.setString(4, periodo); 
            cs.setString(5, Util.yyyymmdd(fechaIni)); 
            cs.setString(6, Util.yyyymmdd(fechaFin)); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
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
                obj.setCuentaBancaria(rs.getString("CUENTABANCO"));
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
        
    public List<BeanPreProvision> HelpDocumentosPendientesRango(
            String pRucEmpresa,
            String pFormaPago,
            String pFecha
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_HelpDocumentosPendientes_Rango(?,?,?)}");
            cs.setString(1, pRucEmpresa);  
            cs.setString(2, pFormaPago); 
            cs.setString(3, pFecha); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRuc(rs.getString("RUT"));
                obj.setRs(rs.getString("RS"));      
                obj.setHaber(rs.getDouble("SALDO"));
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
        
    
    public void actualiza_cuentabanco_proveedor(
            String pRucEmpresa,
            String pPeriodo,
            String pProveedor,
            String pFiltro
            
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.sp_actualiza_cuentabanco_proveedor(?,?,?,?)}");
            cs.setString(1, pRucEmpresa); 
            cs.setString(2, pPeriodo); 
            cs.setString(3, pProveedor);  
            cs.setString(4, pFiltro);  
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

public List<BeanPreProvision> muestraDocumentoPendientesTodos(
            String pRucEmpresa,
            String pRucProveedor,
            String pFormaPago,
            String periodo
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            /*ACTUALIZA CUENTAS BANCARIOAS*/
            
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_documentosPendientes_Todos_01(?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pFormaPago); 
            cs.setString(4, periodo); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setRuc(rs.getString("rut"));
                obj.setRs(rs.getString("rs"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));      
                obj.setDebe(rs.getDouble("debe"));
                obj.setHaber(rs.getDouble("haber"));
                obj.setSaldo(rs.getDouble("saldo"));
                if (rs.getString("FECHAPAGO")==null)
                    obj.setFechaProPago("");
                else
                    obj.setFechaProPago(rs.getString("FECHAPAGO"));
                if (rs.getString("FECHAPAGO_GLOSA")==null)
                     obj.setFechaProPago_glosa("");
                else
                    obj.setFechaProPago_glosa(rs.getString("FECHAPAGO_GLOSA"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setCuentaBancaria(rs.getString("CUENTABANCO"));
                obj.setPoliza(rs.getString("poliza"));
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
    
    

public void actualiza_saldo_cuentabanco_proveedor(
            String pRucEmpresa,
            String pPeriodo,
            String pProveedor,
            String pFiltro
            
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.sp_saldo_cuentabanco_proveedor(?,?,?,?)}");
            cs.setString(1, pRucEmpresa); 
            cs.setString(2, pPeriodo); 
            cs.setString(3, pProveedor);  
            cs.setString(4, pFiltro);  
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

public void actualiza_planilla(
            String pRucEmpresa,
            String fechacontable,
            long asiento,
            String usuario,
            long planilla,
            String tipoOperacion,
            String glosa,
            String formaPago
            
        ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.GRABAR_PLANILLA_GENERICO(?,?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa); 
            cs.setString(2, fechacontable);  
            cs.setLong(3, asiento);  
            cs.setString(4, usuario);  
            cs.setLong(5, planilla);  
            cs.setString(6, tipoOperacion); 
            cs.setString(7, glosa); 
            cs.setString(8, formaPago); 
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


public JSONObject GrabarPagoProveedorGenerico(Long Secuencia,String rucEmpresa,String rucProveedor,String formaPago,String fechacontable, 
        long asiento,
        String usuario,
        String glosa){
        JSONObject jsonObjOut;
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        String msg="";  
        String result=""; 
        String documento="";
        String planilla="";
        String tipoOperacion="";
        try {
            cs = conexion.prepareCall("{call relaciones.dbo.GRABAR_PAGO_PROVEEDOR_GOLDEN_GENERICO(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, Secuencia);
            cs.setString(2, rucEmpresa);      
            cs.setString(3, rucProveedor);           
            cs.setString(4, formaPago);
            cs.setString(5, fechacontable);
            cs.setLong(6, asiento);
            cs.setString(7, usuario);
            cs.setString(8, glosa);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR);
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);
            cs.registerOutParameter(11, java.sql.Types.VARCHAR);
            cs.registerOutParameter(12, java.sql.Types.VARCHAR);
            cs.registerOutParameter(13, java.sql.Types.VARCHAR);            
            cs.execute();
            result = cs.getString(9);
            msg = cs.getString(10);
            documento = cs.getString(11);
            planilla = cs.getString(12);
            tipoOperacion = cs.getString(13);
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("msg", msg);
            jsonObjMsg.put("tipoMsg",result);
            jsonObjMsg.put("documento", documento);
            jsonObjMsg.put("planilla", planilla);
            jsonObjMsg.put("tipoOperacion", tipoOperacion);
            
            jsonArrMsg.put(jsonObjMsg);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("mensaje", jsonArrMsg);            
        }catch (Exception e) {
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("tipoMsg", "error");
            jsonObjMsg.put("msg", "" + e);
            jsonObjMsg.put("documento", "0");
            jsonObjMsg.put("planilla", "0");
            jsonObjMsg.put("tipoOperacion", "");
            jsonArrMsg.put(jsonObjMsg);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("mensaje", jsonArrMsg);
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

       return jsonObjOut;
    }


}
 