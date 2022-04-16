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
import org.json.JSONArray;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class PreProvisionDAO {
     private static final Logger LOGGER = LogManager.getLogger();
     
     
    public List<ListaGenerica> listaCuentasGastos(String rucEmpresa) throws Exception {
        ArrayList<ListaGenerica> listaProvincias = new ArrayList<>();
        Connection cn = new ConectaSQL().connection();
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            cs = cn.prepareCall("{call relaciones.dbo.LISTA_CUENTAS_GASTO(?)}");
            cs.setString(1, rucEmpresa);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                ListaGenerica obj = new ListaGenerica();
                obj.setIndice(rs.getString("indice"));
                obj.setDescripcion(rs.getString("descripcion"));
                listaProvincias.add(obj);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            Util.close(cn);
            Util.close(cs);
            Util.close(rs);
        }
        return listaProvincias;
    }     
   
    public List<BeanPreProvision> Lista_Documento_PreProvision(
            String pRucEmpresa,
            String pRucProveedor,
            String pFechaIni,
            String pFechaFin,
            String pFiltro) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.LISTA_DOCUMENTO_PREPROVISION_PAGOS(?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pFechaIni);  
            cs.setString(4, pFechaFin);  
            cs.setString(5, pFiltro);
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setMes(rs.getString("mes"));
                obj.setAsiento(rs.getString("voucher"));
                obj.setId(rs.getString("indice"));
                obj.setFechaDocumento(rs.getString("fechaDoc"));
                obj.setSerieDoc(rs.getString("serieDoc"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setGlosa(rs.getString("glosa"));
                obj.setMoneda(rs.getString("moneda"));
                obj.setMonedaStr(rs.getString("monedaStr"));
                obj.setTipoDocumenoStr(rs.getString("tipoDocumento"));
                obj.setTipoDocumenoStr(rs.getString("tipoDocumentoStr"));
                obj.setBaseImponible(rs.getDouble("baseImponible")); 
                obj.setFechaRegistro(rs.getString("fechaRegistro"));
                obj.setRs(rs.getString("rs"));
                obj.setRuc(rs.getString("rut"));
                obj.setFechaContable(rs.getString("fechaContable"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setRegistro(rs.getInt("reg"));
                obj.setTipoComprobante(rs.getString("tipoComprobante"));
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
    
    
    public String grabarActualizaProvision(
            String pRucEmpresa,
            String pRucProveedor,
            String serieDoc,
            String numeroDoc,
            String tipoDocumento,
            String glosa,
            String fechaContable,
            String hbl,
            String dua,
            String gastos,
            String gravado,
            String retencion,
            double pPorgravado,
            double pPorretencion  ,
            String pNotaProveedor,
            long secuencia,
            String tipo,
            String tipoGasto,
            String poliza
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String pFecha = Util.yyyymmdd(fechaContable);

        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.EditPrePagado(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, serieDoc);  
            cs.setString(4, numeroDoc);  
            cs.setString(5, tipoDocumento);  
            cs.setString(6, glosa);  
            cs.setDate(7, java.sql.Date.valueOf(pFecha)); 
            cs.setString(8, hbl); 
            cs.setString(9, dua); 
            cs.setString(10, gastos);
            cs.setString(11, gravado);
            cs.setString(12, retencion);
            cs.setDouble(13, pPorgravado);
            cs.setDouble(14, pPorretencion);
            cs.setString(15, pNotaProveedor);
            cs.setLong(16, secuencia);            
            cs.setString(17, tipo);
            cs.setString(18, tipoGasto);
            cs.setString(19, poliza);           
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }    

            
    
            
    public String grabarPreProvision(
            String pRucEmpresa,
            String pRucProveedor,
            String serieDoc,
            String numeroDoc,
            String tipoDocumento,
            String glosa,
            String moneda,
            String fechaDoc,
            String baseImponible
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        double pBaseImp = Util.nullDou(baseImponible);
        String pFecha = Util.yyyymmdd(fechaDoc);

        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.actualizaPrePagado(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, serieDoc);  
            cs.setString(4, numeroDoc);  
            cs.setString(5, tipoDocumento);  
            cs.setString(6, glosa);  
            cs.setString(7, moneda);  
            cs.setDate(8, java.sql.Date.valueOf(pFecha));              
            cs.setDouble(9, pBaseImp);  
            
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }    

    
 public String eliminarDocumento(
            String pRucEmpresa,
            String pRucProveedor,
            String pIndice,
            String serieDoc,
            String numeroDoc,
            String tipoDocumento
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.EliminarPrePagado(?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor); 
            cs.setString(3, pIndice);            
            cs.setString(4, serieDoc);  
            cs.setString(5, numeroDoc);  
            cs.setString(6, tipoDocumento);   
            
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }        
 
    public List<BeanPreProvision> Lista_busca_PreProvision(
            String pRucEmpresa,
            String pRucProveedor,
            String pIndice,
            String pSerieDoc,
            String pNumeroDoc,
            String pTipoDoc) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.LISTA_BUSCA_PREPROVISION(?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setString(3, pIndice);  
            cs.setString(4, pSerieDoc);  
            cs.setString(5, pNumeroDoc); 
            cs.setString(6, pTipoDoc); 
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setId(rs.getString("indice"));
                obj.setFechaDocumento(rs.getString("fechaDoc"));
                obj.setSerieDoc(rs.getString("serieDoc"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setGlosa(rs.getString("glosa"));
                obj.setMoneda(rs.getString("moneda"));
                obj.setMonedaStr(rs.getString("monedaStr"));
                obj.setTipoDocumenoStr(rs.getString("tipoDocumento"));
                obj.setTipoDocumenoStr(rs.getString("tipoDocumentoStr"));
                obj.setBaseImponible(rs.getDouble("baseImponible")); 
                obj.setFechaRegistro(rs.getString("fechaRegistro"));
                
                obj.setTheFilePDF(rs.getBytes("filePdf"));
                obj.setNombreFilePDF(rs.getString("nombrePdf"));
                obj.setExtensionPDf(rs.getString("ExtensionPdf"));
                

                obj.setTheFileXML(rs.getBytes("filexml"));
                obj.setNombreFileXML(rs.getString("nombrexml"));
                obj.setExtensionXML(rs.getString("Extensionxml"));

                obj.setTheFileCDR(rs.getBytes("fileCdr"));
                obj.setNombreFileCDR(rs.getString("nombreCdr"));
                obj.setExtensionCDR(rs.getString("ExtensionCdR"));
                
                obj.setVisualizapdf(rs.getString("visualizapdf"));
                obj.setVisualizaxml(rs.getString("visualizaxml"));
                obj.setVisualizacdr(rs.getString("visualizacdr"));                
                obj.setFechaContable(rs.getString("fechaContable"));
                obj.setDua(rs.getString("dua"));
                obj.setHbl(rs.getString("hbl"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setGravado(rs.getString("gravado"));
                obj.setRetencion(rs.getString("retencion"));
                
                obj.setPorIgv(rs.getDouble("porIgv"));
                obj.setPorRetencion(rs.getDouble("porretencion"));
                obj.setNotaProveedor(rs.getString("notaproveedor"));
                
                obj.setChkmail(rs.getString("chkmailprov"));
                obj.setTipogasto(rs.getString("tipogasto"));
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
     
    
    public String generaAsiento(
            String pRucEmpresa,
            String pRucProveedor,  
             String tipoDocumento,
            String serieDoc,
            String numeroDoc,
            int asiento
           
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado="";
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.generaProvision(?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor); 
            cs.setString(3, tipoDocumento);  
            cs.setString(4, serieDoc);  
            cs.setString(5, numeroDoc);   
            cs.setInt(6, asiento);   
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.execute();
            resultado = cs.getString(7);
            if (resultado==null) resultado="";
        } catch (Exception e) {
            resultado = e.toString();
            //LOGGER.error("GP.ERROR: {}", e);
            //throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return resultado;
    }                
  
        
public String cargaDatosTemporal(
            String pRucEmpresa,
            String pRucProveedor,
            String serieDoc,
            String numeroDoc,
            String tipoDocumento,
            long secuencia
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.cargaDatosTemporal(?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);           
            cs.setString(3, serieDoc);  
            cs.setString(4, numeroDoc);  
            cs.setString(5, tipoDocumento);   
            cs.setLong(6, secuencia); 
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }             
        
    public String EliminarAsiento(
            String pRucEmpresa,
            String pRucProveedor,
            String serieDoc,
            String numeroDoc,
            String tipoDocumento,
            String tipoComprobante,
            String mes,
            long asiento,
            String anho
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.EliminarAsiento(?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);           
            cs.setString(3, serieDoc);  
            cs.setString(4, numeroDoc);  
            cs.setString(5, tipoDocumento); 
            cs.setString(6, tipoComprobante); 
            cs.setString(7, mes); 
            cs.setLong(8, asiento); 
            cs.setString(9, anho); 
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }            
    
     public List<String> listaMailsNotificacionProveedor(String rucEmpresa,String rucProveedor) throws Exception 
    {
        Connection cn = new ConectaSQL().connection();
	CallableStatement cs = null;
	ResultSet rs = null;
	List<String> lista = new ArrayList<>();
	if (cn != null) 
        {
	    try {
	        cs = cn.prepareCall("{call relaciones.dbo.listaMailNotificacionesProveedor(?,?)}");
                cs.setString(1, rucEmpresa);
	        cs.setString(2, rucProveedor);
	        cs.execute();
	       	rs = (ResultSet) cs.executeQuery();
	        while (rs.next()) {
                    if (!"".equals(rs.getString("conmail")))
                        lista.add(rs.getString("conmail"));
                    if (!"".equals(rs.getString("mail")))
                    lista.add(rs.getString("mail"));
	        }
	    } catch (Exception e) {	               
	                throw e;
	    } finally {
	                Util.close(cn);
	                Util.close(rs);
	                Util.close(cs);
	    }
	}	        
	return lista;        
    }
    public List<String> listaMailsNotificacion(String rucEmpresa,String nivel) throws Exception 
    {
	        
	Connection cn = new ConectaSQL().connection();
	CallableStatement cs = null;
	ResultSet rs = null;
	List<String> lista = new ArrayList<>();
	if (cn != null) 
        {
	    try {
	        cs = cn.prepareCall("{call relaciones.dbo.listaMailNotificaciones(?,?)}");
                cs.setString(1, rucEmpresa);
	        cs.setString(2, nivel);
	        cs.execute();
	       	rs = (ResultSet) cs.executeQuery();
	        while (rs.next()) {
                    lista.add(rs.getString("email"));
	        }
	    } catch (Exception e) {	               
	                throw e;
	    } finally {
	                Util.close(cn);
	                Util.close(rs);
	                Util.close(cs);
	    }
	}	        
	return lista;
    }  
    
    public String AdicionaTemporalGastos(
            long secuencia,
            String pRucEmpresa,
            String pRucProveedor,
            double importe,
            String gasto,
            String descripcion
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.AdicionaTemporalGastos(?,?,?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, pRucEmpresa);
            cs.setString(3, pRucProveedor);           
            cs.setDouble(4, importe);  
            cs.setString(5, gasto);  
            cs.setString(6, descripcion);  
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }     
    
    
    public List<BeanPreProvision> Lista_temporal_gasto(
            String pRucEmpresa,
            String pRucProveedor,
            long pSecuencia
            ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.lista_temporal_gasto(?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setLong(3, pSecuencia);  
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setGlosa(rs.getString("descripcion"));
                obj.setBaseImponible(rs.getDouble("importe"));
                obj.setId(rs.getString("indice"));
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
    
    
 public String EliminarItemTemporal(
            long secuencia,
            String pRucEmpresa,
            String pRucProveedor,
            long  item
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.EliminarItemTemporal(?,?,?,?)}");
            cs.setLong(1, secuencia);
            cs.setString(2, pRucEmpresa);
            cs.setString(3, pRucProveedor);           
            cs.setLong(4, item);  
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return "";
    }        
    
   public String ELIMINA_TMP_PREPROVISION(String empresa, long seqTmp2) throws Exception
    {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call relaciones.dbo.SP_ELIMINA_TMP_PREPROVISION(?,?)}";
        Object[] paramIN = { empresa, seqTmp2 };
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));
        LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
        return result;        
    }  
   
public String carga_documentosPreProvision(
            
            String pRucEmpresa,
            String pRucProveedor,
            long pSecuencia,
            String pFechaIni,
            String pFechaFin,
            String pContable,
            String pBuscarPor) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado  = "";
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.SP_CARGA_DOCUMENTOS_PREPROVISION_02(?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor);  
            cs.setLong(3, pSecuencia);  
            cs.setString(4, pFechaIni);  
            cs.setString(5, pFechaFin);  
            cs.setString(6, pContable);              
            cs.setString(7, pBuscarPor);  
            cs.execute();
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            resultado = e.toString();
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return resultado;
    } 
 
public List<BeanPreProvision> Lista_Documento_PreProvision_TMP(
            String pRucEmpresa,
            long  secuencia) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        List<BeanPreProvision> list = new ArrayList();
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.LISTA_DOCUMENTO_PREPROVISION_TMP(?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setLong(2, secuencia);  
            cs.execute();
            rs = cs.getResultSet();
            while (rs.next()) {
                BeanPreProvision obj = new BeanPreProvision();
                obj.setMes(rs.getString("mes"));
                obj.setAsiento(rs.getString("voucher"));
                obj.setId(rs.getString("indice"));
                obj.setFechaDocumento(rs.getString("fechaDoc"));
                obj.setSerieDoc(rs.getString("serieDoc"));
                obj.setNumeroDoc(rs.getString("numeroDoc"));
                obj.setTipoDocumento(rs.getString("tipoDocumento"));
                obj.setGlosa(rs.getString("glosa"));
                obj.setMoneda(rs.getString("moneda"));
                obj.setMonedaStr(rs.getString("monedaStr"));
                obj.setTipoDocumenoStr(rs.getString("tipoDocumento"));
                obj.setTipoDocumenoStr(rs.getString("tipoDocumentoStr"));
                obj.setBaseImponible(rs.getDouble("baseImponible")); 
                obj.setFechaRegistro(rs.getString("fechaRegistro"));
                obj.setRs(rs.getString("rs"));
                obj.setRuc(rs.getString("rut"));
                obj.setFechaContable(rs.getString("fechaContable"));
                obj.setCuentaGasto(rs.getString("cuenta"));
                obj.setRegistro(rs.getInt("reg"));
                obj.setTipoComprobante(rs.getString("tipoComprobante"));
                obj.setAnho(rs.getString("anho"));
                obj.setPoliza(rs.getString("poliza"));
                obj.setTipogasto(rs.getString("tipogasto"));
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
     

public JSONObject generaAsiento_02(
            String pRucEmpresa,
            String pRucProveedor,  
             String tipoDocumento,
            String serieDoc,
            String numeroDoc,
            String fechaContable
           
    ) throws Exception {
        LOGGER.info("<==== Inicio Metodo: listaPartesEnTempo ====>");
        Connection cn = new ConectaSQL().connection();
        ResultSet rs = null;
        String resultado="";
        JSONObject jsonObjOut;
        JSONArray jsonArrMsg = new JSONArray();
        JSONObject jsonObjMsg = new JSONObject();
        
        JSONArray jsonArrDatos = new JSONArray();
        JSONObject jsonObjDatos = new JSONObject();
        
        
        double igvTotal = 0;
        double total=0;
        long asiento=0;
        String asientodatos="";
        double tipocambio = 0;
        try { 
            CallableStatement cs = cn.prepareCall("{call relaciones.dbo.generaProvision_02(?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, pRucEmpresa);
            cs.setString(2, pRucProveedor); 
            cs.setString(3, tipoDocumento);  
            cs.setString(4, serieDoc);  
            cs.setString(5, numeroDoc);   
            cs.setString(6, fechaContable);   
            cs.registerOutParameter(7, java.sql.Types.VARCHAR);
            cs.registerOutParameter(8, java.sql.Types.DOUBLE);
            cs.registerOutParameter(9, java.sql.Types.DOUBLE);
            cs.registerOutParameter(10, java.sql.Types.DOUBLE);
            cs.registerOutParameter(11, java.sql.Types.VARCHAR);
            cs.registerOutParameter(12, java.sql.Types.DOUBLE);
            cs.execute();
            resultado = cs.getString(7);
            igvTotal = cs.getDouble(8);
            total = cs.getDouble(9); 
            asiento =  cs.getLong(10); 
            asientodatos = cs.getString(11);    
            tipocambio = cs.getDouble(12);
            if ("".equals(resultado)){
                jsonObjMsg.put("msg", resultado);
                jsonObjMsg.put("tipoMsg", "exito");
                
                jsonObjDatos.put("igvTotal",igvTotal);
                jsonObjDatos.put("total",total);                
                jsonObjDatos.put("asiento",asiento);  
                jsonObjDatos.put("asientodatos",asientodatos);  
                jsonObjDatos.put("tipocambio",tipocambio);  
                
                
                jsonArrMsg.put(jsonObjMsg);
                jsonArrDatos.put(jsonObjDatos);
                jsonObjOut = new JSONObject();
                jsonObjOut.put("mensaje", jsonArrMsg);
                jsonObjOut.put("datos", jsonArrDatos);
            } else
            {
                jsonObjMsg.put("msg", resultado);
                jsonObjMsg.put("tipoMsg", "error");
                jsonArrMsg.put(jsonObjMsg);
                jsonObjOut = new JSONObject();
                jsonObjOut.put("mensaje", jsonArrMsg);                 
            }
                resultado="";
        } catch (Exception e) {
                resultado = e.toString();          
                jsonObjMsg.put("msg", resultado);
                jsonObjMsg.put("tipoMsg", "error");
                jsonArrMsg.put(jsonObjMsg);
                jsonObjOut = new JSONObject();
                jsonObjOut.put("mensaje", jsonArrMsg);  
        } finally {
            Util.close(cn);
            Util.close(rs);
        }
        LOGGER.info("<==== Fin Metodo: listaPartesEnTempo ====>");
        return jsonObjOut;
    }                
  
    

}
