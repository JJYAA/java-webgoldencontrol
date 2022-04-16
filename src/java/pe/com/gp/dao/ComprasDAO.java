/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaDb;
import pe.com.gp.connection.ConectaHana;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.BeanPoliza;
import pe.com.gp.entity.Cliente;
import pe.com.gp.entity.HelpGenerico;
import pe.com.gp.entity.Parte;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class ComprasDAO {
 
private static final Logger LOGGER = LogManager.getLogger();


    public String InsertaTempAuxiliar(String pEmpresa,String tipo,String poliza,Long pSecuencia) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
        try {
            cs = cn.prepareCall("{call relaciones.dbo.INSERT_TMP_TIPO_POLIZA(?,?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, tipo);
            cs.setString(3, poliza);            
            cs.setLong(4, pSecuencia);
            cs.execute();
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    } 

    public String InsertaTempPoliza(String pEmpresa,String pPoliza,Long pSecuencia) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
        try {
            cs = cn.prepareCall("{call relaciones.dbo.INSERTA_TMP_DETALLE_POLIZA(?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pPoliza);
            cs.setLong(3, pSecuencia);
            cs.execute();
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    }   

    public List<BeanPoliza> muestraPolizas(String empresa) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
         List<BeanPoliza> list = new ArrayList(); 
        try {
            cs = cn.prepareCall("{call relaciones.dbo.ListaPolizas(?)}");
            cs.setString(1, empresa);
            cs.execute();
            rs = cs.getResultSet();  
            while (rs.next()) {
                 BeanPoliza poliza = new BeanPoliza();
                 poliza.setPoliza(rs.getString("poliza"));
                 poliza.setImporte(rs.getDouble("monto_fob"));
                 list.add(poliza);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return list;
    }   
    
   public List<BeanPoliza> ListaPolizaDetalle(String pEmpresa,String pPoliza,Long pSecuencia) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
         List<BeanPoliza> list = new ArrayList(); 
        try {
            cs = cn.prepareCall("{call relaciones.dbo.LISTA_TMP_POLIZA_DETALLE(?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pPoliza);
            cs.setLong(3, pSecuencia);
            cs.execute();
            rs = cs.getResultSet();  
            while (rs.next()) {
                 BeanPoliza oPoliza = new BeanPoliza();
                 oPoliza.setMoneda(rs.getString("moneda"));
                 oPoliza.setStrmoneda(rs.getString("strmoneda"));
                 oPoliza.setStrtipoDocumento(rs.getString("strtipoDocumento"));
                 oPoliza.setDescripcion(rs.getString("descripcion"));
                 oPoliza.setC_c_item(rs.getString("c_c_item"));
                 oPoliza.setMonto_dolares(rs.getDouble("monto_dolares"));
                 oPoliza.setMonto_soles(rs.getDouble("monto_soles"));
                 oPoliza.setDocumento(rs.getString("documento"));
                 oPoliza.setCodigoProv(rs.getString("CodigoProv"));
                 oPoliza.setFecha_emision(rs.getString("fecha_emision"));
                 oPoliza.setBaseImpTotal(rs.getDouble("baseImpTotal"));
                 oPoliza.setIgvTotal(rs.getDouble("igvTotal"));
                 oPoliza.setInafecta(rs.getDouble("inafecta"));
                 oPoliza.setTotal(rs.getDouble("total"));
                 oPoliza.setTipoDocumento(rs.getString("TipoDocumento"));
                 oPoliza.setSeriedocumento(rs.getString("seriedocumento"));
                 list.add(oPoliza);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }

        return list;
    }    
       
    /*
   actualizaDatalleitem
        String  pPoliza = request.getParameter("poliza");
        item 
        fecha 
        td 
        proveedor 
        serie 
        documento 
       
     
   */
   
   
    public String actualizaDatalleitem(
            String pEmpresa,
            String pPoliza,
            Long pSecuencia,
        String  pItem ,
        String pFecha ,
        String pTd ,
        String pProveedor, 
        String pSerie ,
        String pDocumento, 
        double pBaseimponible, 
        double pInafecto ,
        double pIgv,
        double pTotal  ,
        String pMoneda
            
    ) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";         
        try {
            cs = cn.prepareCall("{call relaciones.dbo.ACTUALIZADETALLEITEM(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pPoliza);
            cs.setLong(3, pSecuencia);
            cs.setString(4, pItem);
            cs.setDate(5, java.sql.Date.valueOf(pFecha)); //2021-12-31
            cs.setString(6, pTd);
            cs.setString(7, pProveedor);
            cs.setString(8, pSerie);
            cs.setString(9, pDocumento);
            cs.setDouble(10, pBaseimponible);
            cs.setDouble(11, pInafecto);
            cs.setDouble(12, pIgv);
            cs.setDouble(13, pTotal);
            cs.setString(14, pMoneda);
            cs.execute();
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    }     
   
   public  List<BeanPoliza> BuscaDatalleitem(
            String pEmpresa,
            String pPoliza,
            Long pSecuencia,
        String  pItem               
            
    ) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
         List<BeanPoliza> list = new ArrayList(); 
        try {
            cs = cn.prepareCall("{call relaciones.dbo.BUSCA_DETALLE_ITEM(?,?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pPoliza);
            cs.setLong(3, pSecuencia);
            cs.setString(4, pItem);            
            cs.execute();
            rs = cs.getResultSet();  
            while (rs.next()) {
                 BeanPoliza oPoliza = new BeanPoliza();
                 oPoliza.setMoneda(rs.getString("moneda"));
                 oPoliza.setStrmoneda(rs.getString("strmoneda"));                 
                 oPoliza.setStrtipoDocumento(rs.getString("strtipoDocumento"));
                 oPoliza.setC_c_item(rs.getString("c_c_item"));
                 oPoliza.setMonto_dolares(rs.getDouble("monto_dolares"));
                 oPoliza.setMonto_soles(rs.getDouble("monto_soles"));
                 oPoliza.setDocumento(rs.getString("documento"));
                 oPoliza.setCodigoProv(rs.getString("CodigoProv"));
                 oPoliza.setFecha_emision(rs.getString("fecha_emision"));
                 oPoliza.setBaseImpTotal(rs.getDouble("baseImpTotal"));
                 oPoliza.setIgvTotal(rs.getDouble("igvTotal"));
                 oPoliza.setInafecta(rs.getDouble("inafecta"));
                 oPoliza.setTotal(rs.getDouble("total"));
                 oPoliza.setTipoDocumento(rs.getString("TipoDocumento"));
                 oPoliza.setSeriedocumento(rs.getString("seriedocumento"));
                 list.add(oPoliza);
            }            
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return list;
    }       
   
   
    public  String GrabarPoliza(
            String pEmpresa,
            String pPoliza,
            Long pSecuencia           
            
    ) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
        try {
            cs = cn.prepareCall("{call relaciones.dbo.GRABAR_POLIZA(?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pPoliza);
            cs.setLong(3, pSecuencia);          
            cs.execute();
            rs = cs.getResultSet();           
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    }    
    
    public  String ValidaDatosPoliza(
            String pEmpresa,
            String pPoliza,
            Long pSecuencia           
            
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call relaciones.dbo.ValidaDatosPoliza(?,?,?,?)}";
        Object[] paramIN = { pEmpresa, pPoliza,pSecuencia };
        String result = Util.nullCad(Util.sp_ejecuta(sp, java.sql.Types.VARCHAR, paramIN));
        LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
        return result;           
    }     

    
    public  int ValidaDatos(
            String pEmpresa,
            String pPoliza,
            String pItem           
            
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        int result=0;
        return result;           
    } 
    
public String EliminaAsientoPoliza60X(String rucEmpresa,String anho,String mes,String tipoComprobante,Long asiento) throws Exception {
    LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
    String sp = "{call relaciones.dbo.EliminaAsientoPoliza60(?,?,?,?,?)}";
    Object[] paramIN = { rucEmpresa, anho,mes,tipoComprobante,asiento };
    String result = Util.nullCad(Util.sp_ejecuta(sp, java.sql.Types.VARCHAR, paramIN));
    LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
    return result;     
}    
 
 public String EliminaAsientoPoliza60(String rucEmpresa,String anho,String mes,String tipoComprobante,Long asiento
    ) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
        try {
            cs = cn.prepareCall("{call relaciones.dbo.EliminaAsientoPoliza60(?,?,?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, anho);
            cs.setString(3, mes);            
            cs.setString(4, tipoComprobante);                        
            cs.setLong(5, asiento);
            cs.execute();
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    }




public JSONObject grabaAsiento_02(
        String pEmpresa  ,
        String codTienda,
        long pSecuencia,
        double pFob,
        String poliza,
        String fechaContable,
        String mes,
        String anho                        
    ) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         JSONObject rs = null;
         JSONObject jsonObjOut;
        String ls_result = "";
        String ls_tipo_comprobante = "";
        Long ln_voucher = 0l;
        
        try {
            cs = cn.prepareCall("{call relaciones.dbo.genera_Provision_60(?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, codTienda);
            cs.setLong(3, pSecuencia);
            cs.setDouble(4, pFob);
            cs.setString(5, poliza);
            cs.setString(6, fechaContable);
            cs.setString(7, mes);
            cs.setString(8, anho);
            cs.registerOutParameter(9, java.sql.Types.VARCHAR);
            cs.registerOutParameter(10, java.sql.Types.VARCHAR);
            cs.registerOutParameter(11, java.sql.Types.NUMERIC);
            cs.execute();
            ls_result = cs.getString(9);
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();            
            if ("".equals(ls_result)){
                ls_tipo_comprobante = cs.getString(10);
                ln_voucher    = cs.getLong(11);
                jsonObjMsg.put("msg", ls_result);
                jsonObjMsg.put("tipoMsg", "exito");
                jsonObjMsg.put("voucher", ln_voucher);
                jsonObjMsg.put("tipoComprobante", ls_tipo_comprobante);
                jsonArrMsg.put(jsonObjMsg);
                jsonObjOut = new JSONObject();
                jsonObjOut.put("mensaje", jsonArrMsg);
            } else
            {
                jsonObjMsg.put("msg", ls_result);
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("voucher", 0);
                jsonObjMsg.put("tipoComprobante", "");
                jsonArrMsg.put(jsonObjMsg);
                jsonObjOut = new JSONObject();
                jsonObjOut.put("mensaje", jsonArrMsg);                 
            }            
        } catch (Exception e) {
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("tipoMsg", "error");
            jsonObjMsg.put("voucher", 0);
            jsonObjMsg.put("tipoComprobante", "");            
            jsonObjMsg.put("msg", "" + e);
            jsonArrMsg.put(jsonObjMsg);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("mensaje", jsonArrMsg);
        } finally {
            Util.close(cn);
          
        }
        return jsonObjOut;
    }

    public String grabaAsiento(
            String pEmpresa,
            String pCodigo,
            String pSerieDoc,
            String pNumeroDoc,
            String pFechadocumento,
            String pFechacontable  ,          
            String pTipoDocumento,
            Double baseImponible ,
            Double Inafecto,
            Double Igv ,
            Double total ,
            Double porIgv ,
            String moneda                    
    ) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
        try {
            cs = cn.prepareCall("{call relaciones.dbo.genera_Provision_golden(?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pEmpresa);
            cs.setString(2, pCodigo);
            cs.setString(3, pSerieDoc);            
            cs.setString(4, pNumeroDoc);
            cs.setString(5, pFechadocumento);
            cs.setString(6, pFechacontable);
            cs.setString(7, pTipoDocumento);
            cs.setDouble(8, baseImponible);            
            cs.setDouble(9, Inafecto);
            cs.setDouble(10, Igv);
            cs.setDouble(11, total);
            cs.setDouble(12, porIgv);
            cs.setString(13, moneda);
            cs.execute();
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    }    
    
    
    public String InsertaTempPoliza_02(
            String rucEmpresa,String pEmpresa,String pPoliza,Long pSecuencia, 
            double pGasto,
            String pItem,
            String pCuenta,
            Long pVoucherItem,
            Long pVoucherCab) throws Exception {
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
         ResultSet rs = null;
        String pResultado = "";
        try {
            cs = cn.prepareCall("{call relaciones.dbo.INSERTA_TMP_DETALLE_02(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, rucEmpresa);
            cs.setString(2, pEmpresa);
            cs.setString(3, pPoliza);
            cs.setLong(4, pSecuencia);
            cs.setDouble(5, pGasto);
            cs.setString(6, pItem);
            cs.setString(7, pCuenta);
            cs.setLong(8, pVoucherItem);
            cs.setLong(9, pVoucherCab);
            cs.execute();
        } catch (Exception e) {
            pResultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        return pResultado;
    }      
}
