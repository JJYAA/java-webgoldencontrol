/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.dao.ComprasDAO;
import pe.com.gp.dao.PreProvisionDAO;
import pe.com.gp.dao.ProductosDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.entity.BeanPoliza;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.ComprasMigrarForm;
import pe.com.gp.form.RegistroVentasForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;
import static pe.com.gp.util.Util.fecha_dia;

/**
 *
 * @author Computer
 */
public class ComprasMigrarAction extends DispatchAction {
    
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEQ_TMP1_SESSION_KEY = "FacRepMosSeqTmp1_" + UUID.randomUUID().toString();
  
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            ComprasMigrarForm uform = (ComprasMigrarForm) form;
            session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuencia());
            
            uform.setFechaContable(Util.obtenerFechaActual("dd/MM/yyyy"));
            
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
                    
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }  
    
    public void muestraPolizas(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');  
        DecimalFormat nf = new DecimalFormat("###,###.##",simbolo);  
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                int estado;
                String mensaje;                 
                //List<BeanPoliza> listaPolizas = new ComprasDAO().muestraPolizas(empresa);
                JSONObject jObjRegistro = new JSONObject();								
		jObjRegistro.put("empresa", empresa);	
                jObjRegistro.put("poliza", "%");
                JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA, jObjRegistro);
                try {
                        estado = Util.nullNum(jsonObjResult.get("Estado"));
                        mensaje = Util.nullCad(jsonObjResult.get("Mensaje"));
                        
                } catch (Exception e) {
                        estado = 0;
                        mensaje = "" + e;
                }      
                if (estado==0)
                {    
                    JSONArray poliza = jsonObjResult.getJSONArray("listadoPoliza");
                    if (jsonObjResult.length()>0) {
                        //JSONArray arregloJson = new JSONArray(jsonObjResult);
                        sbLista.append("<table id=\"tablaListaPolizas\">");
                        sbLista.append("<thead>");
                        sbLista.append("<tr>");                    
                        sbLista.append("<th class=\"text-center\">Poliza</th>");
                        sbLista.append("<th class=\"text-center\">Importe</th>");
                        sbLista.append("<th class=\"text-center\"></th>");
                        sbLista.append("<th class=\"text-center\"></th>");
                        sbLista.append("</tr>");
                        sbLista.append("</thead>");
                        sbLista.append("<tbody>");
                        String data = "";


                        for(int i=0;i<poliza.length();i++){
                           JSONObject obj = poliza.getJSONObject(i);                         
                            sbLista.append("<tr>");
                            sbLista.append("<td>").append("<a href=\"#\" onclick=\"editarPoliza('" + Util.nullCad(obj.get("poliza")) + "')\" >" + Util.nullCad(obj.get("poliza")) + "</a>").append("</td>");                        
                            String output = nf.format(obj.get("monto_fob"));
                            sbLista.append("<td class=\"text-right\">").append(output).append("</td>");
                            sbLista.append("<td class=\"text-center\"><button onclick=\"cargaPolizasDetalle('")
                            .append(Util.nullCad(obj.get("poliza"))).append("','").append(output)
                            .append("');\" title=\"elegir\" class=\"btn btn-xs btn-default\" type=\"button\">"
                            + Constantes.ICON_ARROW_RIGHT + "</button></td>");        
                            if (Util.nullLon(obj.get("voucher"))==0){
                               sbLista.append("<td class=\"text-center\">").append("").append("</td>");
                            }
                            else
                            {   data = Util.nullCad(Util.nullCad(obj.get("poliza"))) + "','" + Util.nullCad(Util.nullCad(obj.get("anho"))) + "','" + Util.nullCad(Util.nullCad(obj.get("mes")))  + "','" +  Util.nullCad(Util.nullCad(obj.get("tipo_comprobante")))  + "','" +  Util.nullCad(Util.nullCad(obj.get("voucher")));
                                sbLista.append("<td class=\"text-center\"><button onclick=\"EliminarAsintoPoliza('")
                                .append(data).append("');\" title=\"Eliminar Asiento\" class=\"btn btn-xs btn-default\" type=\"button\">"
                                + Constantes.ICON_CLEAR + "</button></td>");
                            }
                            sbLista.append("</tr>");
                        }
                        sbLista.append("</tbody>");
                        sbLista.append("</table>");
                        jsonObjTabla.put("ctosRegs", jsonObjResult.length());
                    } else {
                        jsonObjTabla.put("ctosRegs", 0);
                    }
                    jsonObjTabla.put("tabla", sbLista.toString());

                    // Mensajes
                    jsonObjMsg = new JSONObject();
                    if (!"".equals(Util.nullCad(msgError))) {
                        jsonObjMsg.put("tipoMsg", "error");
                        jsonObjMsg.put("msgError", msgError);
                    } else {
                        jsonObjMsg.put("tipoMsg", "exito");
                    }
                }
                else
                {
                    jsonObjMsg = new JSONObject();
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", mensaje);                    
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objTotales", jsonObjTotales);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }
    

    
    
     public void muestraPolizasDetalle(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');  
        DecimalFormat nf = new DecimalFormat("###,###.##",simbolo);          
        String empresa = (String) session.getAttribute("Empresa");
        String  pPoliza = request.getParameter("poliza");
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                long pSecuencia = (Long)session.getAttribute(SEQ_TMP1_SESSION_KEY);
                new PreProvisionDAO().ELIMINA_TMP_PREPROVISION(user.getRucEmpresa(), pSecuencia);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
               // String pResultado = new ComprasDAO().InsertaTempPoliza(empresa,pPoliza,pSecuencia);
                //List<BeanPoliza> listaPolizas = new ComprasDAO().ListaPolizaDetalle(empresa,pPoliza,pSecuencia);
                double monto_fob = 0;
                String mes="";
                String anho="";
                String tipo_comprobante="";
                String fechaContable = "";
                String voucher_cab="";
                double total_sol=0;
                double total_dol=0;  
                JSONObject jObjRegistro = new JSONObject();								
		jObjRegistro.put("empresa", empresa);
                jObjRegistro.put("poliza", pPoliza);
                JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA_DETALLE, jObjRegistro);
                JSONArray poliza = jsonObjResult.getJSONArray("listadoPoliza_detalle");                
                if (poliza.length()>0) {
                    sbLista.append("<table id=\"tablaListaPolizasDetalle\"   >");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">.</th>"); 
                    sbLista.append("<th class=\"text-center\">Descripcion</th>");  
                    sbLista.append("<th class=\"text-center\">Moneda_S/.</th>");  
                    sbLista.append("<th class=\"text-center\">Moneda_US$</th>"); 
                    sbLista.append("<th class=\"text-center\">Asiento</th>"); 
                    sbLista.append("<th class=\"text-center\">.</th>"); 
                    sbLista.append("<th class=\"text-center\">Fecha Contable.</th>"); 
                    sbLista.append("<th class=\"text-center\">Fecha_Doc.</th>"); 
                    sbLista.append("<th class=\"text-center\">Tipo_Doc</th>"); 
                    sbLista.append("<th class=\"text-center\">Proveedor</th>"); 
                    sbLista.append("<th class=\"text-center\">Serie</th>"); 
                    sbLista.append("<th class=\"text-center\">Documento</th>");  
                    sbLista.append("<th class=\"text-rigth\">Moneda</th>");
                    
                    sbLista.append("<th class=\"text-rigth\">Base Imp.</th>");  
                    sbLista.append("<th class=\"text-rigth\">Inafecto</th>");  
                    sbLista.append("<th class=\"text-rigth\">I.G.V</th>");  
                    sbLista.append("<th class=\"text-rigth\">Total</th>");  
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String data = "";
                    String pattern = "###,###,###.00";
                    long nroAsiento ;
                     for(int i=0;i<poliza.length();i++)
                     {
                        JSONObject obj = poliza.getJSONObject(i); 
                        nroAsiento = Util.nullLon(obj.get("voucher"));
                        if (nroAsiento>0)
                        {
                            new ComprasDAO().InsertaTempPoliza_02(
                                    user.getRucEmpresa(),
                                    user.getCodTienda(), 
                                    Util.nullCad(obj.get("poliza")),
                                    pSecuencia,
                                    Util.nullDou(obj.get("monto_soles")) , 
                                    Util.nullCad(obj.get("c_c_item")),
                                    Util.nullCad(obj.get("c_c_cuenta")) ,
                                    Util.nullLon(obj.get("voucher")),
                                    Util.nullLon(obj.get("voucher_cab")) 
                            );
                        }
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"details-control\"></td>"); // (para plugin DataTables)
                        sbLista.append("<td class=\"text-center\"><input type=\"hidden\" id=\"poliza\" name=\"poliza\" class=\"poliza\" value=\"" + obj.get("poliza") + "\"  ><input type=\"hidden\" id=\"item\" name=\"item\" class=\"item\" value=\"" + obj.get("c_c_item") + "\"  ><button onclick=\"editItem('")
			.append(Util.nullCad(obj.get("c_c_item"))).append("','").append(Util.nullCad(obj.get("descripcion")))
			.append("');\" title=\"elegir\" class=\"btn btn-xs btn-default\" type=\"button\">"
			+ Constantes.ICON_EDIT + "</button></td>");  
                        sbLista.append("<td width=\"100%\">").append(Util.nullCad(obj.get("descripcion"))).append("</td>");  
                        String output = nf.format(obj.get("monto_soles"));
                        sbLista.append("<td class=\"text-right\">").append(output).append("</td>");    
                        output = nf.format(obj.get("monto_dolares"));
                        total_sol+=Util.nullDou(obj.get("monto_soles"));
                        total_dol+=Util.nullDou(obj.get("monto_dolares"));        
                        sbLista.append("<td class=\"text-right\">").append(output).append("</td>");     
                        sbLista.append("<td>").append(Util.nullCad(obj.get("voucher"))).append("</td>");   
                        //if ("01".equals(Util.nullCad(obj.get("c_c_item")))||("02".equals(Util.nullCad(obj.get("c_c_item")))))
                        //    sbLista.append("<td class=\"text-center\"><input type=\"checkbox\" id=\"chk\" name=\"chk\" class=\"chk\"></td>");  
                        //else
                            sbLista.append("<td class=\"text-center\"><input type=\"checkbox\" disabled=\"true\" id=\"chk\" name=\"chk\" class=\"chk\"></td>");   
                        sbLista.append("<td>").append(obj.get("fechacontable")).append("</td>"); 
                        sbLista.append("<td>").append(obj.get("fecha_emision")).append("</td>");  
                        if ("".equals(obj.get("tipoDocumento")))
                             sbLista.append("<td>").append("").append("</td>");  
                        else if ("01".equals(obj.get("tipoDocumento")))
                            sbLista.append("<td>").append("FACTURA").append("</td>");  
                        else
                            sbLista.append("<td>").append("EMBALAJE").append("</td>");  
                       sbLista.append("<td>").append(obj.get("codigoProv")).append("</td>");   
                        sbLista.append("<td>").append(obj.get("seriedoc")).append("</td>");                        
                        sbLista.append("<td>").append(obj.get("documento")).append("</td>"); 
                        if ("".equals(obj.get("moneda")))
                             sbLista.append("<td>").append("").append("</td>");                          
                        else if ("1".equals(obj.get("moneda")))
                            sbLista.append("<td>").append("S/.").append("</td>");    
                        else
                            sbLista.append("<td>").append("US$").append("</td>");    
                        sbLista.append("<td class=\"text-right\">").append(nf.format(obj.get("baseImpTotal"))).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(nf.format(obj.get("inafecta"))).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(nf.format(obj.get("igvTotal"))).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(nf.format(obj.get("total"))).append("</td>");                       
                        monto_fob = Util.nullDou(obj.get("monto_fob"));
                        
                        anho = Util.nullCad(obj.get("anho_poliza"));
                        mes = Util.nullCad(obj.get("mes_poliza"));
                        tipo_comprobante = Util.nullCad(obj.get("tipocomprobante_poliza"));
                        voucher_cab = Util.nullCad(obj.get("voucher_cab"));
                        fechaContable = Util.nullCad(obj.get("fechacontable_poliza"));
                           sbLista.append("</tr>");
                    }
                     
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                    jsonObjTabla.put("ctosRegs", poliza.length());
                } else {
                    jsonObjTabla.put("ctosRegs", 0);
                }
                jsonObjTabla.put("tabla", sbLista.toString());
                jsonObjTotales.put("soles", nf.format(total_sol));
                jsonObjTotales.put("dolar", nf.format(total_dol));        
                jsonObjTotales.put("totalsoles", Util.redondear( monto_fob + total_sol,2));  
                jsonObjTotales.put("anho", anho);
                jsonObjTotales.put("mes", mes);
                jsonObjTotales.put("tipo_comprobante", tipo_comprobante);
                jsonObjTotales.put("fecha_contable", fechaContable);
                jsonObjTotales.put("voucher_cab", voucher_cab);
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objTotales", jsonObjTotales);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }   
          
    
    
     public void cargaPolizasDetalle(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");
        String  pPoliza = request.getParameter("poliza");
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                long pSecuencia = (Long)session.getAttribute(SEQ_TMP1_SESSION_KEY);
                StringBuilder sbLista = new StringBuilder();
                String pResultado = new ComprasDAO().InsertaTempPoliza(empresa,pPoliza,pSecuencia);
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(pResultado))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                }
            }  else
            {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }      
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }   
     
     
     public void actualizaDatalleitem(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();        
        String empresa = (String) session.getAttribute("Empresa");
        String  pPoliza = request.getParameter("poliza");
        String  item = request.getParameter("item");
        String  fecha = request.getParameter("fecha");
        String  td = request.getParameter("td");
        String  proveedor = request.getParameter("proveedor");
        String  serie = request.getParameter("serie");
        String  documento = request.getParameter("documento");
        String  moneda = request.getParameter("moneda");
        double  baseimponible = Util.nullDou(request.getParameter("baseimponible"));
        double  inafecto = Util.nullDou(request.getParameter("inafecto"));
        double  igv = Util.nullDou(request.getParameter("igv"));
        double  total = Util.nullDou(request.getParameter("total"));
        String fechacontable = request.getParameter("fechacontable");
       // fecha = Util.yyyymmdd(fecha);
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                long pSecuencia = (Long)session.getAttribute(SEQ_TMP1_SESSION_KEY);
                // Lista los repuestos que estan en el Tempo
               // fecha  = Util.yyyymmdd(fecha);
                StringBuilder sbLista = new StringBuilder();
                JSONObject jObjRegistro = new JSONObject();								
		jObjRegistro.put("empresa", empresa);
		jObjRegistro.put("item", item);
                jObjRegistro.put("poliza", pPoliza);
                jObjRegistro.put("codigoProveedor", proveedor);
                jObjRegistro.put("serieDocumento", serie);
                jObjRegistro.put("numeroDocumento", documento);
                jObjRegistro.put("tipoDocumento", td);
                jObjRegistro.put("igvTotal", igv);
                jObjRegistro.put("baseImponible", baseimponible);
                jObjRegistro.put("inafecto", inafecto);
                jObjRegistro.put("igv", igv);
                jObjRegistro.put("total", total);
                jObjRegistro.put("fechaDocumento", fecha);
                jObjRegistro.put("moneda", moneda); 
                jObjRegistro.put("fechacontable", fechacontable); 
                int estado;
                String mensaje;                
                JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_ACTUALIZA_POLIZA, jObjRegistro);
                try {
                        estado = Util.nullNum(jsonObjResult.get("Estado"));
                        mensaje = Util.nullCad(jsonObjResult.get("Mensaje"));
                        
                    } catch (Exception e) {
                        estado = 0;
                        mensaje = "" + e;
                    }
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objTotales", jsonObjTotales);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    } 
     
// Consumir Rest Json
    public JSONObject consumeRestJson(String url, JSONObject jsonObjIn) throws Exception {
        JSONObject jsonObjOut;
        try {
            CloseableHttpClient httpClient = HttpClientBuilder.create().build();
            HttpPost getRequest = new HttpPost(url);
            StringEntity params = new StringEntity(new String(jsonObjIn.toString().getBytes(Charset.forName("ISO-8859-1")), Charset.forName("UTF-8")));
            getRequest.addHeader("Content-Type", "application/json; charset=utf-8");
            getRequest.addHeader("Accept", "application/json");
            getRequest.setEntity(params);
            HttpResponse httpResponse = httpClient.execute(getRequest);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader((httpResponse.getEntity().getContent())));
                StringBuilder builder = new StringBuilder();
                String output;
                while ((output = br.readLine()) != null) {
                    builder.append(output);
                }
                String jsonBuilder = builder.toString();
                jsonObjOut = new JSONObject(jsonBuilder);
            } else {
                throw new Exception(Util.nullCad(httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            JSONObject jsonObjMsg = new JSONObject();
            jsonObjMsg.put("tipoMsg", "error");
            jsonObjMsg.put("msg", e);
            jsonObjOut = new JSONObject();
            jsonObjOut.put("objMensaje", jsonObjMsg);
        }
        return jsonObjOut;
    }
     
   
    public JSONObject consumeRestJsonTipo02(String url, JSONObject jsonObjIn) throws Exception {
        JSONObject jsonObjOut;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(url);
            HttpEntity httpEntity = new StringEntity(jsonObjIn.toString(), ContentType.APPLICATION_JSON);
            httpPost.setEntity(httpEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                jsonObjOut = new JSONObject(EntityUtils.toString(httpResponse.getEntity(), "utf-8"));
            } else {
                jsonObjOut = new JSONObject();
                jsonObjOut.put("Estado", 1);
                jsonObjOut.put("Mensaje", Util.nullCad(httpResponse.getStatusLine().getStatusCode()));
            }
        } catch (Exception e) {
            jsonObjOut = new JSONObject();
            jsonObjOut.put("Estado", 1);
            jsonObjOut.put("Mensaje", "" + e.toString());
        }
        return jsonObjOut;
    }     
     
     public void editItem(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjItem = new JSONObject();
        JSONObject jsonObjDatos = new JSONObject();        
        String empresa = (String) session.getAttribute("Empresa");
        String  pPoliza = request.getParameter("poliza");
        String  pItem = request.getParameter("item");
       
           
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                long pSecuencia = (Long)session.getAttribute(SEQ_TMP1_SESSION_KEY);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                JSONObject jObjRegistro = new JSONObject();								
		jObjRegistro.put("empresa", empresa);
                jObjRegistro.put("poliza", pPoliza);
                jObjRegistro.put("item", pItem);
                JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA_DETALLE_ITEM, jObjRegistro);
                JSONArray poliza = jsonObjResult.getJSONArray("listadoPoliza_detalle_item");  
                if (poliza.length()>0) {
                    
                     for(int i=0;i<poliza.length();i++){
                         JSONObject obj = poliza.getJSONObject(i);
                         jsonObjDatos.put("strtipoDocumento", obj.get("tipoDocumento"));
                         jsonObjDatos.put("descripcion", obj.get("c_c_item"));
                         jsonObjDatos.put("c_c_item", obj.get("c_c_item"));
                         jsonObjDatos.put("monto_dolares", obj.get("monto_dolares"));
                         jsonObjDatos.put("monto_soles", obj.get("monto_soles"));
                         jsonObjDatos.put("documento",  obj.get("documento"));
                         jsonObjDatos.put("CodigoProv", obj.get("codigoProv"));
                         jsonObjDatos.put("fecha_emision", obj.get("fecha_emision"));
                         jsonObjDatos.put("baseImpTotal", obj.get("baseImpTotal"));
                         jsonObjDatos.put("igvTotal",  obj.get("igvTotal"));
                         jsonObjDatos.put("inafecta", obj.get("inafecta"));
                         jsonObjDatos.put("total", obj.get("total"));
                         jsonObjDatos.put("TipoDocumento",obj.get("tipoDocumento"));
                         jsonObjDatos.put("seriedocumento", obj.get("seriedoc") ); 
                         jsonObjDatos.put("moneda", obj.get("moneda") ); 
                         jsonObjDatos.put("fechacontable", obj.get("fechacontable") ); 
                     }                                                                     
                }
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "exito");
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objDatos", jsonObjDatos);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }        
     
     
             
     public void GrabarPoliza(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjItem = new JSONObject();
        JSONObject jsonObjDatos = new JSONObject();        
        String empresa = (String) session.getAttribute("Empresa");
        String  pPoliza = request.getParameter("poliza");    
           
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                long pSecuencia = (Long)session.getAttribute(SEQ_TMP1_SESSION_KEY);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultad = new ComprasDAO().GrabarPoliza(empresa,pPoliza,pSecuencia);

                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "exito");
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objDatos", jsonObjDatos);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }     
     
     
   public void validaDatosFactura(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjCliente;
        jsonObjMsg = new JSONObject();
        jsonObjCliente = new JSONObject();
        StringBuilder sb = new StringBuilder();
        boolean existe = false;
        try {
            jsonObjMsg.put("tipoMsg", "exito");
            jsonObjMsg.put("msgError", "");
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa"); 
            String seleccion= request.getParameter("seleccion");
            String poliza= request.getParameter("poliza");
            seleccion = seleccion.substring(0,seleccion.length()-1);
            String arr[] =seleccion.split(",");
            //long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            
            //long pAuxSecuencia = (Long)session.getAttribute(SEQ_TMP2_SESSION_KEY);
            if (Util.sesionEstaActiva(request)) {
                boolean salir = false;
                int i=0;
                JSONArray datosPoliza = null;
                while((i<arr.length)&&(!salir))
                {
                    String elem[] =arr[i].split("\\|");                    
                    JSONObject jObjRegistro = new JSONObject();								
                    jObjRegistro.put("empresa", empresa);
                    jObjRegistro.put("poliza", elem[0]);
                    jObjRegistro.put("item", elem[1]);							
                    JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA_VALIDA_ITEM, jObjRegistro);
                    JSONArray polizaValida = jsonObjResult.getJSONArray("validaPolizaItem");                  
                    JSONObject obj = polizaValida.getJSONObject(0); 
                    if ("1".equals(Util.nullCad(obj.get("exito"))))
                        salir=true;
                     i++;
                }
                if (salir){
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msg", "Existe error inconsistente");                   
                }
                jsonObjMsg.put("resultado", "");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());

            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
      
   
   
   
           
    public void generaAsientoZZZZZZZZZZzz(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjCliente;
        jsonObjMsg = new JSONObject();
        jsonObjCliente = new JSONObject();
        StringBuilder sb = new StringBuilder();
        boolean existe = false;
        try {
            jsonObjMsg.put("tipoMsg", "exito");
            jsonObjMsg.put("msgError", "");
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa"); 
            String seleccion= request.getParameter("seleccion");
            seleccion = seleccion.substring(0,seleccion.length()-1);
            String arr[] =seleccion.split(",");
            //long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            
            //long pAuxSecuencia = (Long)session.getAttribute(SEQ_TMP2_SESSION_KEY);
            if (Util.sesionEstaActiva(request)) {
                boolean salir = false;
                int i=0;
                JSONArray datosPoliza = null;
                while((i<arr.length)&&(!salir))
                {
                    String elem[] =arr[i].split("\\|");                    
                    JSONObject jObjRegistro = new JSONObject();								
                    jObjRegistro.put("empresa", empresa);
                    jObjRegistro.put("poliza", elem[0]);
                    jObjRegistro.put("item", elem[1]);							
                    JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA_VALIDA_ITEM, jObjRegistro);
                    JSONArray polizaValida = jsonObjResult.getJSONArray("validaPolizaItem");                  
                    JSONObject obj = polizaValida.getJSONObject(0); 
                    if ("0".equals(Util.nullCad(obj.get("exito")))){
                        jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA_DETALLE_ITEM, jObjRegistro);
                        polizaValida = jsonObjResult.getJSONArray("listadoPoliza_detalle_item");                  
                        obj = polizaValida.getJSONObject(0);
                        
                        String resultado = new ComprasDAO().grabaAsiento(
                                "empresa", 
                                Util.nullCad(obj.get("codigoProv")), 
                                Util.nullCad(obj.get("seriedoc")), 
                                Util.nullCad(obj.get("documento")), 
                                Util.nullCad(obj.get("fecha_emision")), 
                                Util.nullCad(obj.get("fechacontable")), 
                                Util.nullCad(obj.get("tipoDocumento")),  
                                Util.nullDou(obj.get("baseImpTotal")),
                                Util.nullDou(obj.get("inafecta")),
                                Util.nullDou(obj.get("igvTotal")),
                                Util.nullDou(obj.get("total")),
                                Util.nullDou(obj.get("igv")),
                                Util.nullCad(obj.get("moneda")));
                    }
                       
                     i++;
                }
                if (salir){
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msg", "Existe error inconsistente");                   
                }
                jsonObjMsg.put("resultado", "");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());

            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
      
    
    public void muestraPoliza(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjDatos = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");
        String poliza = Util.nullCad(request.getParameter("poliza"));
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                //List<BeanPoliza> listaPolizas = new ComprasDAO().muestraPolizas(empresa);
                JSONObject jObjRegistro = new JSONObject();								
		jObjRegistro.put("empresa", empresa);	
                jObjRegistro.put("poliza", poliza);
                JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_POLIZA, jObjRegistro);
                JSONArray jpoliza = jsonObjResult.getJSONArray("listadoPoliza");
                JSONObject obj = jpoliza.getJSONObject(0);   
                jsonObjDatos.put("montoFob", obj.get("monto_fob"));
                jsonObjDatos.put("montoCbm", obj.get("totalcbm"));
                jsonObjDatos.put("cerrado", obj.get("cerrado"));
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objDatos", jsonObjDatos);           
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }
    
                

     public void actualizaPoliza(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjItem = new JSONObject();
        JSONObject jsonObjDatos = new JSONObject();        
        String empresa = (String) session.getAttribute("Empresa");
        String  pPoliza = request.getParameter("poliza");
        Double  pMontofob = Util.nullDou(request.getParameter("montofob"));
        Double  pMontocbm = Util.nullDou(request.getParameter("montocbm"));
        String  pCerrado = request.getParameter("cerrado");
       
           
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                long pSecuencia = (Long)session.getAttribute(SEQ_TMP1_SESSION_KEY);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                JSONObject jObjRegistro = new JSONObject();								
		jObjRegistro.put("empresa", empresa);
                jObjRegistro.put("poliza", pPoliza);
                jObjRegistro.put("montoFob", pMontofob);
                jObjRegistro.put("montoCbm", pMontocbm);
                jObjRegistro.put("cerrado", pCerrado);
                JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_ACTUALIZA_POLIZA_CAB, jObjRegistro);
                JSONArray resultado = jsonObjResult.getJSONArray("estado");  
                JSONObject obj = resultado.getJSONObject(0);   
                jsonObjMsg = new JSONObject();
                Boolean valor = Util.nullBoo(obj.get("exito"));
                if (valor) {
                    jsonObjMsg.put("tipoMsg", "exito"); 
                }
                else {
                    jsonObjMsg.put("tipoMsg", "false");}
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objDatos", jsonObjDatos);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }        

    public void generaAsiento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjCliente;
        jsonObjMsg = new JSONObject();
        jsonObjCliente = new JSONObject();
        StringBuilder sb = new StringBuilder();
        boolean existe = false;
        try {
            jsonObjMsg.put("tipoMsg", "exito");
            jsonObjMsg.put("msgError", "");
            HttpSession session = request.getSession();
            Global global = (Global) session.getAttribute("Global");
            Usuario user = (Usuario) session.getAttribute("Usuario");
            String poliza= request.getParameter("poliza");
            String fechaContable = request.getParameter("fechaContable");
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            
            //long pAuxSecuencia = (Long)session.getAttribute(SEQ_TMP2_SESSION_KEY);
            if (Util.sesionEstaActiva(request)) {
                boolean salir = false;
                int i=0;
                JSONObject jsonObjResult;
                jsonObjResult = new ComprasDAO().grabaAsiento_02(user.getRucEmpresa(),user.getCodTienda(),seqTmp1,0,poliza,fechaContable, fechaContable.substring(3, 5),fechaContable.substring(6, 10));
                String tipoMsg;
                String msg;
                Long ln_voucher = 0L;
                String ls_tipoComprobante="";       
                try 
                {
                        JSONArray jsonArrMsg = (JSONArray) jsonObjResult.get("mensaje");
                        JSONObject jsonObjMsgX = (JSONObject) jsonArrMsg.get(0);
                        tipoMsg = Util.nullCad(jsonObjMsgX.get("tipoMsg"));
                        msg = Util.nullCad(jsonObjMsgX.get("msg")); 
                        ln_voucher = Util.nullLon(jsonObjMsgX.get("voucher"));  
                        ls_tipoComprobante =  Util.nullCad(jsonObjMsgX.get("tipoComprobante"));
                       
                } catch (Exception e) {
                        tipoMsg = "error";
                        msg = "" + e;
                }
                if ("error".equals(tipoMsg)){
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msg", msg);                   
                }
                else { 
                    StringBuilder sbLista = new StringBuilder();
                    JSONObject jObjRegistro = new JSONObject();                    
                    jObjRegistro.put("empresa", user.getCodTienda());
                    jObjRegistro.put("poliza", poliza);
                    jObjRegistro.put("fechacontable", fechaContable);
                    jObjRegistro.put("mes", fechaContable.substring(3, 5));
                    jObjRegistro.put("anho", fechaContable.substring(6, 10));
                    jObjRegistro.put("tipovoucher", ls_tipoComprobante);
                    jObjRegistro.put("voucher", ln_voucher);
                    Boolean estado=false;
                    String msgError="";
                    JSONObject jsonObjResultado = consumeRestJsonTipo02(Constantes.URL_ACTUALIZA_VOUCHER_POLIZA, jObjRegistro);
                    try {
                        //estado = Util.nullNum(jsonObjResultado.get("Estado"));
                        JSONArray jsonArrMsg = (JSONArray) jsonObjResultado.get("estado");
                        jsonObjMsg = (JSONObject) jsonArrMsg.get(0);                        
                        estado = Util.nullBoo(jsonObjMsg.get("exito"));
                        
                    } catch (Exception e) {
                        //estado = 0;
                        msgError = "" + e;
                    }
                    jsonObjMsg = new JSONObject();
                    if (!estado) {
                        jsonObjMsg.put("tipoMsg", "error");
                        jsonObjMsg.put("msgError", msgError);
                    } else {
                        jsonObjMsg.put("tipoMsg", "exito");
                    }
                }
                jsonObjMsg.put("resultado", "");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());

            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }

    public void EliminarAsintoPoliza(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjCliente;
        jsonObjMsg = new JSONObject();
        jsonObjCliente = new JSONObject();
        StringBuilder sb = new StringBuilder();
        boolean existe = false;
        try {
            jsonObjMsg.put("tipoMsg", "exito");
            jsonObjMsg.put("msgError", "");
            HttpSession session = request.getSession();
            Global global = (Global) session.getAttribute("Global");
            Usuario user = (Usuario) session.getAttribute("Usuario");
            String poliza= request.getParameter("poliza");
            String anho= request.getParameter("anho");
            String mes= request.getParameter("mes");
            String tipoComprobante= request.getParameter("tipoComprobante");
            String asiento= request.getParameter("asiento");
            
            
            //long pAuxSecuencia = (Long)session.getAttribute(SEQ_TMP2_SESSION_KEY);
            if (Util.sesionEstaActiva(request)) {
                boolean salir = false;
                int i=0;
                StringBuilder sbLista = new StringBuilder();
                JSONObject jObjRegistro = new JSONObject();                    
                jObjRegistro.put("empresa", user.getCodTienda());
                jObjRegistro.put("poliza", poliza);
                jObjRegistro.put("mes", mes);
                jObjRegistro.put("anho", anho);
                jObjRegistro.put("tipovoucher", tipoComprobante);
                jObjRegistro.put("voucher", asiento);
                boolean estado=false;
                String msgError="";
                JSONObject jsonObjResultado = consumeRestJsonTipo02(Constantes.URL_ELIMINA_VOUCHER_POLIZA, jObjRegistro);
                try {                    
                    //estado = Util.nullBoo(jsonObjResultado.get("estado"));
                        JSONArray jsonArrMsg = (JSONArray) jsonObjResultado.get("estado");
                        jsonObjMsg = (JSONObject) jsonArrMsg.get(0);                        
                        estado = Util.nullBoo(jsonObjMsg.get("exito"));
                        
                } catch (Exception e) {
                    //estado = 0;                    
                    msgError = "" + e;
                }                
                if (!estado){
                    msgError = msgError;
                }
                else 
                {
                    JSONObject jsonObjResult;
                    String resultado = new ComprasDAO().EliminaAsientoPoliza60(user.getRucEmpresa(),anho, mes, tipoComprobante,Util.nullLon(asiento));
                    if (!"".equals(resultado)){
                        jsonObjMsg.put("tipoMsg", "error");
                        jsonObjMsg.put("msg", resultado);                   
                    }
                    else { 

                        jsonObjMsg = new JSONObject();
                        if (!"".equals(Util.nullCad(""))) {
                            jsonObjMsg.put("tipoMsg", "error");
                            jsonObjMsg.put("msgError", msgError);
                        } else {
                            jsonObjMsg.put("tipoMsg", "exito");
                        }
                    }
                    jsonObjMsg.put("resultado", "");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("objMensaje", jsonObjMsg);
                    sb.append(jsonObject.toString());
                    out = response.getWriter();
                    out.write(sb.toString());                    
                }
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "error");
                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonObjMsg);
                sb.append(jsonObject.toString());
                out = response.getWriter();
                out.write(sb.toString());
            } catch (JSONException | IOException ex) {
                LOGGER.error("GP.ERROR: {}", e);
            }
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }
            
    

}
