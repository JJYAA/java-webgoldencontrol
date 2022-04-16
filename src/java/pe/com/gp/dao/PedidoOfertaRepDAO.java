/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.Cliente;
import pe.com.gp.entity.Parte;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class PedidoOfertaRepDAO {
    
    private static final Logger LOGGER = LogManager.getLogger();

    public String insertaPropuestaTempo(String empresa, String actividad,long operacion, long seqTmp2) throws Exception
    {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call web_inserta_propuesta_tempo(?,?,?,?)}";
        Object[] paramIN = { empresa, actividad,operacion, seqTmp2 };
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));
        LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
        return result;        
    }    
    
    public String insertaParteEnTempo(String empresa, String codigo, 
            long seqTmp2, long cantidad, 
            Double descuento, Double vvpSol, Double vvpDol,String descripcion,
            String itemProducto) throws Exception
    {
        LOGGER.info("<==== Inicio Metodo: insertaParteEnTempo ====>");
        String sp = "{call web_actualiza_tempo_02(?,?,?,?,?,?,?,?,?)}";
        Object[] paramIN = { empresa, codigo, seqTmp2, cantidad, descuento, vvpSol, vvpDol, descripcion,itemProducto };
        String result = Util.nullCad(Util.sp_ejecuta(sp, null, paramIN));
        LOGGER.info("<==== Fin Metodo: insertaParteEnTempo ====>");
        return result;        
    }
    
    public JSONObject GrabarFactura(  
            String tipoDocSunat,
            String Actividad,
            String Usuario,
            Cliente cliente,
            long Secuencia,
            Parte totales,
            String tipoProceso){
        LOGGER.info("<==== Inicio Metodo: Agrega_Item_tmp ====>");
        JSONObject jsonObjOut;
        Connection conexion = new ConectaSQL().connection();
        CallableStatement cs = null;
        String result="";
        try {

            cs = conexion.prepareCall("{call web_crea_factura_02(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
            cs.setLong(1, Secuencia);
            cs.setString(2, cliente.getEmpresa());           
            cs.setString(3, tipoDocSunat);
            cs.setString(4, cliente.getCodigo());
            cs.setString(5, cliente.getAuxiliar());
            cs.setString(6, cliente.getRazon_social());
            cs.setString(7, cliente.getPaterno());
            cs.setString(8, cliente.getMaterno());
            cs.setString(9, cliente.getPrimer_nombre());
            cs.setString(10, cliente.getSegundo_nombre());
            
            cs.setString(11, cliente.getTipo_persona());
            cs.setString(12, cliente.getTipo_documento());
            cs.setString(13, cliente.getDireccion()) ;
            cs.setString(14, cliente.getFormaPago()) ;
            cs.setString(15, cliente.getMoneda()) ;
            cs.setString(16, cliente.getTelefono1()) ;
            cs.setString(17, cliente.getTelefono2()) ;
            cs.setString(18, cliente.getCelular1());
            cs.setString(19, Usuario);
            cs.setString(20, "00002"); //Actividad
            
            cs.setDouble(21, totales.getTotBrutoSol());
            cs.setDouble(22, totales.getTotDsctoSol());
            cs.setDouble(23, totales.getTotVtaSol());
            cs.setDouble(24, totales.getTotIgvSol());
            cs.setDouble(25, totales.getTotGralVtaSol());
            cs.setString(26, tipoProceso);
            cs.registerOutParameter(27, java.sql.Types.VARCHAR);
            cs.execute();
            result = cs.getString(27);
            JSONArray jsonArrMsg = new JSONArray();
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("msg", result);
            jsonObjMsg.put("tipoMsg", "exito");
            jsonArrMsg.put(jsonObjMsg);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("mensaje", jsonArrMsg);            
        } catch (Exception e) {
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
    
}
