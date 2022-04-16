/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
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
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.dao.PreProvisionDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.dao.UbigeoDAO;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.form.PreProvisionForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.EnviaCorreo;
import pe.com.gp.util.Util;
import static pe.com.gp.util.Util.fecha_dia;

/**
 *
 * @author Computer
 */
public class PreProvisionAction  extends DispatchAction {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEQ_TMP1_SESSION_KEY = "FacRepMosSeqTmp1_" + UUID.randomUUID().toString();
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            String empresa = (String) session.getAttribute("Empresa");           
            PreProvisionForm uform = (PreProvisionForm) form;
            session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));
            uform.setCodTienda(usuario.getCodTienda());
            uform.setRucEmpresa(usuario.getRucEmpresa());
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia()); 
            uform.setFechaContable(fecha_dia());   
            uform.setPorIgv("18.00");
            uform.setFlagMueOcuForm("muestra");
            cargaListas(request, uform); 
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    } 
    
  

   public void cargaListas(HttpServletRequest request, PreProvisionForm uform) throws Exception {
        HttpSession session = request.getSession();
        request.setAttribute("listaGastos", new PreProvisionDAO().listaCuentasGastos(uform.getRucEmpresa()));
        
         List<ListaGenerica> listaTipoGastos = listaTipoGastos(uform.getCodTienda());
        request.setAttribute("listaTipoGastos", listaTipoGastos );
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
       
  public List<ListaGenerica> listaTipoGastos(String codTienda) throws Exception {
      
        ArrayList<ListaGenerica> listatipoGastos = new ArrayList<>();
        JSONObject jObjRegistro = new JSONObject();        
	jObjRegistro.put("empresa", codTienda);	
        JSONObject jsonObjResult = consumeRestJsonTipo02(Constantes.URL_LISTA_TIPO_GASTOS, jObjRegistro);
        int estado = 0; //Util.nullNum(jsonObjResult.get("Estado"));
        if (estado==1){
                ListaGenerica listaObj = new ListaGenerica();
                listaObj.setIndice("");
                listaObj.setDescripcion("Nuinguno");
                listatipoGastos.add(listaObj);            
        }
        else
        {
            JSONArray listaGastos = jsonObjResult.getJSONArray("listadoTipoGastosPoliza");          
            for(int i=0;i<listaGastos.length();i++){
                JSONObject obj = listaGastos.getJSONObject(i); 
                ListaGenerica listaObj = new ListaGenerica();
                listaObj.setIndice(Util.nullCad(obj.get("poliza")));
                listaObj.setDescripcion(Util.nullCad(obj.getString("polizaDescripcion")));
                listatipoGastos.add(listaObj);
            }
        }
        return listatipoGastos;
    }     
    
   
   
    public void muestraPreProvisiones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";         
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                String fechaIni  = Util.nullCad(request.getParameter("fechaIni"));
                String fechaFin  = Util.nullCad(request.getParameter("fechaFin"));
                String filtro  = Util.nullCad(request.getParameter("filtro"));
                fechaIni = Util.yyyymmdd(fechaIni);
                fechaFin = Util.yyyymmdd(fechaFin);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_Documento_PreProvision(usuario.getRucEmpresa(),"%",fechaIni,fechaFin,filtro);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table id=\"tablaListaPreProvision\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">Asiento</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                                        
                    sbLista.append("<th class=\"text-center\">Ruc</th>");
                    sbLista.append("<th class=\"text-center\">Razon social</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">Serie</th>");
                    sbLista.append("<th class=\"text-center\">Documento</th>");
                    sbLista.append("<th class=\"text-center\">Fecha</th>");
                    sbLista.append("<th class=\"text-center\">Base Imp.</th>");
                    sbLista.append("<th class=\"text-center\">Moneda</th>");
                    //sbLista.append("<th class=\"text-center\">Glosa</th>");
                    sbLista.append("<th class=\"text-center\">F.Contable</th>");
                    sbLista.append("<th class=\"text-center\">Registro</th>");
                    sbLista.append("<th class=\"text-center\">F.Pago</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    for (BeanPreProvision parte : ListaPreProvision) {
                        sbLista.append("<tr>");
                        
                        strValor= parte.getRuc().trim() + "," + parte.getTipoDocumento().trim() + "," +  parte.getSerieDoc().trim() + "," +  parte.getNumeroDoc().trim() ;

                        sbLista.append("<td>").append(Util.nullCad(parte.getAsiento())).append("</td>");                        
                        if ("0".equals(parte.getAsiento()))
                            if (parte.getRegistro()==0)
                                sbLista.append("<td class=\"text-center\">").append("<input type=\"hidden\" id=\"ruc\" class=\"ruc\"  value='" + parte.getRuc() + "' >  <input type=\"checkbox\" id=\"chk\"  name=\"chk\"  class=\"chk\" disabled=\"true\" onclick=\"myCambio(event)\"  >").append("</td>");
                            else
                                sbLista.append("<td class=\"text-center\">").append("<input type=\"hidden\" id=\"ruc\" class=\"ruc\"  value='" + parte.getRuc() + "' >  <input type=\"checkbox\" id=\"chk\"  name=\"chk\"  class=\"chk\"  onclick=\"myCambio(event)\"  >").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append("").append("</td>");
                        
                        if ("0".equals(parte.getAsiento()))
                            sbLista.append("<td class=\"text-center\">").append("").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append("").append("</td>"); 
                           //sbLista.append("<td class=\"text-center\">").append("<a href=\"#\"  disabled=\"disabled\" onclick=\"EliminarAsiento('" + parte.getRuc() + "','" + parte.getMes() + "','" +  parte.getAsiento()  + "','" +   parte.getTipoDocumento() + "','" + parte.getTipoComprobante()  + "','" + parte.getSerieDoc()  + "','" +  parte.getNumeroDoc() + "')\" ><span class=\"glyphicon glyphicon-remove\"></span></a>").append("</td>");
                        
                        sbLista.append("<td><input type=\"hidden\" id=\"valor\" class=\"valor\" value='" + strValor + "'  >").append(Util.nullCad(parte.getRuc())).append("</td>");                        
                        sbLista.append("<td>").append(Util.nullCad(parte.getRs())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTipoDocumenoStr())).append("</td>");                        
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getSerieDoc())).append("</td>");                       
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getNumeroDoc())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFechaDocumento())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getBaseImponible())).append("</td>"); //
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getMonedaStr())).append("</td>");
                        //sbLista.append("<td>").append(Util.nullCad(parte.getGlosa())).append("</td>");  
                        if (parte.getFechaContable()==null)
                            sbLista.append("<td class=\"text-center\">").append(".").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFechaContable())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFechaRegistro())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append("").append("</td>");  
                        if ("0".equals(parte.getAsiento()))
                            sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EliminarDocumento('" + parte.getRuc() + "','" + parte.getId() + "','" +  parte.getTipoDocumento() + "','" +  parte.getSerieDoc()  + "','" +  parte.getNumeroDoc() + "')\" ><span class=\"glyphicon glyphicon-remove\"></span></a>").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append("&nbsp;").append("</td>");
                        
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EditarDocumento('" +  parte.getRuc() + "','" + parte.getId() + "','" +  parte.getTipoDocumento() + "','" +  parte.getSerieDoc()  + "','" +  parte.getNumeroDoc() +  "','" + parte.getAsiento() +  "')\" ><span class=\"glyphicon glyphicon-edit\"></span></a>").append("</td>");
                        sbLista.append("</tr>");
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                } 
                jsonObjTabla.put("tabla", sbLista.toString());

                // Mensajes
               jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                    jsonObjMsg.put("msgError", "");
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }      
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
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
            
   public void grabarPreProvision(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                String pRucProveedor =Util.nullCad(request.getParameter("rucProveedor")).trim();
                String serieDoc = Util.nullCad(request.getParameter("serieDoc"));
                String numeroDoc = Util.nullCad(request.getParameter("numeroDoc"));
                String tipoDocumento = Util.nullCad(request.getParameter("tipoDocumento"));
                String glosa = Util.nullCad(request.getParameter("glosa"));                                               
                numeroDoc= Util.completarCerosIzq(Util.nullLon(numeroDoc),"0000000");
                String fechaContable  = Util.nullCad(request.getParameter("fechaContable"));
                String hbl = Util.nullCad(request.getParameter("hbl"));
                String dua = Util.nullCad(request.getParameter("dua"));
                String gastos = Util.nullCad(request.getParameter("gastos"));
                String gravado =  Util.nullCad(request.getParameter("gravado"));
                String retencion =  Util.nullCad(request.getParameter("retencion"));
                String notaProveedor = Util.nullCad(request.getParameter("notaProveedor"));
                String chkMail =  Util.nullCad(request.getParameter("chkMail"));
                String porgravado =  Util.nullCad(request.getParameter("porgravado"));
                String porretencion =  Util.nullCad(request.getParameter("porretencion"));
                double pPorgravado = Util.nullDou(porgravado);
                double pPorretencion = Util.nullDou(porretencion);
                String poliza = Util.nullCad(request.getParameter("poliza"));
                String tipoGasto = Util.nullCad(request.getParameter("tipoGasto"));
                if ("".equals(fechaContable)) fechaContable="1970-01-01";
                // Lista los repuestos que estan en el Tempo
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                StringBuilder sbLista = new StringBuilder();

                String resultado = "";
                if ("1".equals(chkMail))
                {
                     resultado = new PreProvisionDAO().grabarActualizaProvision(pRucEmpresa,
                     pRucProveedor,   serieDoc, numeroDoc, tipoDocumento, glosa,fechaContable,hbl,dua,gastos,gravado,retencion,
                     pPorgravado,pPorretencion,notaProveedor,seqTmp1,"1",tipoGasto,poliza);                      
                            List<String> listaEmaNot = new PreProvisionDAO().listaMailsNotificacion(usuario.getRucEmpresa(),"01");	
                            List<String> listaEmaNotProv = new PreProvisionDAO().listaMailsNotificacionProveedor(usuario.getRucEmpresa(),pRucProveedor);	
		                    // Se envia el correo en un nuevo hilo
                            final String mailNombreFrom = global.getNombreEmailEnvAut();
                            final String mailCorreoFrom = global.getEmailEnvAut();
                            final String mailClaveFrom = global.getClaveEmailEnvAut();
                            final String mailDestinos = Util.separarListaB(listaEmaNotProv);
                            final String mailCopia = Util.separarListaB(listaEmaNot);
                            final String documento = serieDoc + "-" + numeroDoc;
                            final String mensaje = notaProveedor;
                            try
                            {
                                    new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                    String result = new EnviaCorreo().prepara(
                                    mailNombreFrom,
                                    mailCorreoFrom,
                                    mailClaveFrom,
                                    "COMENTARIOS DEL DOCUMENTO",
                                    mailDestinos, 
                                    mailCopia, 
                                    mensaje,
                                    null,
                                    "T01");
                                    if (result != null) 
                                    {
                                       LOGGER.error("GP.ERROR: {}", result);
                                    }
                                    }
				}).start();
					                        
                            }catch (Exception ex) {
				  LOGGER.error("GP.ERROR: {}", "Error al enviar la OT al correo del Contacto" + ex.toString() );
                            }																					
                } else {
                     resultado = new PreProvisionDAO().grabarActualizaProvision(pRucEmpresa,
                     pRucProveedor,   serieDoc, numeroDoc, tipoDocumento, glosa,fechaContable,hbl,dua,gastos,gravado,retencion,
                     pPorgravado,pPorretencion,notaProveedor,seqTmp1,"2",tipoGasto,poliza);   
                            List<String> listaEmaNot = new PreProvisionDAO().listaMailsNotificacion(usuario.getRucEmpresa(),"01");	
                            List<String> listaEmaNotProv = new PreProvisionDAO().listaMailsNotificacionProveedor(usuario.getRucEmpresa(),pRucProveedor);	
		                    // Se envia el correo en un nuevo hilo
                            final String mailNombreFrom = global.getNombreEmailEnvAut();
                            final String mailCorreoFrom = global.getEmailEnvAut();
                            final String mailClaveFrom = global.getClaveEmailEnvAut();
                            final String mailDestinos = Util.separarListaB(listaEmaNotProv);
                            final String mailCopia = Util.separarListaB(listaEmaNot);
                            final String documento = serieDoc + "-" + numeroDoc;
                            final String mensaje = "DOCUMENTO SE ENCUENTRA PROVISIONADO";
                            try
                            {
                                    new Thread(new Runnable() {
                                    @Override
                                    public void run() {
                                    String result = new EnviaCorreo().prepara(
                                    mailNombreFrom,
                                    mailCorreoFrom,
                                    mailClaveFrom,
                                    "PROVISION DEL DOCUMENTO",
                                    mailDestinos, 
                                    mailCopia, 
                                    mensaje,
                                    null,
                                    "T01");
                                    if (result != null) 
                                    {
                                       LOGGER.error("GP.ERROR: {}", result);
                                    }
                                    }
				}).start();
					                        
                            }catch (Exception ex) {
				  LOGGER.error("GP.ERROR: {}", "Error al enviar la OT al correo del Contacto" + ex.toString() );
                            }                     
                     
                     
                }                
                
                
                 jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(resultado))) {
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
          
    
    
            
    public void EliminarDocumento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                //String pRucProveedor = usuario.getRucProveedor();
                String pRucProveedor =Util.nullCad(request.getParameter("rucProveedor"));
                String indice = Util.nullCad(request.getParameter("indice"));
                String serieDoc = Util.nullCad(request.getParameter("serieDoc"));
                String numeroDoc = Util.nullCad(request.getParameter("numeroDoc"));
                String tipoDocumento = Util.nullCad(request.getParameter("tipoDocumento"));
                
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new PreProvisionDAO().eliminarDocumento(pRucEmpresa,
                 pRucProveedor, indice,  serieDoc, numeroDoc, tipoDocumento);
                 jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(resultado))) {
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
        
    
    public void EditarDocumento(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");                        
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonDatos;
        JSONObject jsonObjTabla = new JSONObject();
        try { 
            if (Util.sesionEstaActiva(request)) {
                String empresa = (String) session.getAttribute("Empresa");
                String msgError = "";
                String pRucProveedor =Util.nullCad(request.getParameter("rucProveedor"));
                String serieDoc = request.getParameter("serieDoc");
                String numeroDoc = request.getParameter("numeroDoc");
                String tipoDocumento = request.getParameter("tipoDocumento");
                String indice = request.getParameter("indice");
                StringBuilder sbLista = new StringBuilder();
                List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_busca_PreProvision(
                        usuario.getRucEmpresa(),pRucProveedor,indice,
                        serieDoc,numeroDoc,tipoDocumento);
                jsonDatos = new JSONObject();
                jsonDatos.put("serieDoc", ListaPreProvision.get(0).getSerieDoc());
                jsonDatos.put("numeroDoc", ListaPreProvision.get(0).getNumeroDoc());
                jsonDatos.put("tipoDocumento", ListaPreProvision.get(0).getTipoDocumento());
                jsonDatos.put("glosa", ListaPreProvision.get(0).getGlosa());
                jsonDatos.put("moneda", ListaPreProvision.get(0).getMoneda());
                jsonDatos.put("fechaDocumento", ListaPreProvision.get(0).getFechaDocumento()) ;
                jsonDatos.put("baseImponible", ListaPreProvision.get(0).getBaseImponible()); 
                jsonDatos.put("visualizapdf", ListaPreProvision.get(0).getVisualizapdf());
                jsonDatos.put("visualizaxml", ListaPreProvision.get(0).getVisualizaxml());
                jsonDatos.put("visualizacdr", ListaPreProvision.get(0).getVisualizacdr()); 
                jsonDatos.put("fechaContable", ListaPreProvision.get(0).getFechaContable());
                jsonDatos.put("cuentaGasto", ListaPreProvision.get(0).getCuentaGasto());
                jsonDatos.put("hbl", ListaPreProvision.get(0).getHbl());
                jsonDatos.put("dua", ListaPreProvision.get(0).getDua());
                jsonDatos.put("gravado", ListaPreProvision.get(0).getGravado());
                jsonDatos.put("retencion", ListaPreProvision.get(0).getRetencion()); 
                jsonDatos.put("porIgv", ListaPreProvision.get(0).getPorIgv()); 
                jsonDatos.put("porRetencion", ListaPreProvision.get(0).getPorRetencion()); 
                jsonDatos.put("notaproveedor", ListaPreProvision.get(0).getNotaProveedor()); 
                jsonDatos.put("chkmail", ListaPreProvision.get(0).getChkmail()); 
                
                jsonDatos.put("poliza", ListaPreProvision.get(0).getPoliza()); 
                jsonDatos.put("tipoGasto", ListaPreProvision.get(0).getTipogasto()); 
                
                jsonDatos.put("tipoMsg","exito");
                
            } else {
                jsonDatos = new JSONObject();
                jsonDatos.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objDatos", jsonDatos);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                sb = new StringBuilder();
                jsonDatos = new JSONObject();
                jsonDatos.put("tipoMsg", "error");
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objMensaje", jsonDatos);
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

  public void convertByteArrayToDoc(byte[] b, String rutaArchivo, String nombreArchivo, String mime,HttpServletResponse response) {
        LOGGER.info("<==== Inicio Metodo: convertByteArrayToDoc ====>");
        OutputStream out;
        Random rnd = new Random();
        byte[] byteArray = new byte[4];
        rnd.nextBytes(byteArray);
        try {
            response.setContentType("application/" + mime);
            response.setHeader("Content-Disposition", "attachment; filename=\"" + nombreArchivo);
            out = new FileOutputStream(rutaArchivo);
            out = response.getOutputStream();
            out.write(b);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
            LOGGER.info(e);
        }
        LOGGER.info("<==== Inicio Metodo: convertByteArrayToDoc ====>");
    }    
    public void muestraArchivo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = null;
        ServletOutputStream out = null;
        Connection cn = new ConectaSQL().connection();
        //RequerimientoAdmDAO requerimientoAdmDAO = new RequerimientoAdmDAO();
        try {
            String path = request.getServletContext().getRealPath("/");
            HttpSession session = request.getSession();
        
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            response.setContentType("application/json");            
            //FacRepMostradorForm uform = (FacRepMostradorForm) form;
            String tipo = request.getParameter("tipo");
            String proveedor = request.getParameter("rucProveedor");
            String serieDoc = request.getParameter("serieDoc");
            String numeroDoc = request.getParameter("numeroDoc");
            String tipoDocumento = request.getParameter("tipoDocumento");
            String indice = request.getParameter("indice");
            StringBuilder sbLista = new StringBuilder();
            List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_busca_PreProvision(
                        usuario.getRucEmpresa(),proveedor,indice,
                        serieDoc,numeroDoc,tipoDocumento);
            String fileName = path + Constantes.DIRECTORIO_TMP_PDF + "/" + ListaPreProvision.get(0).getNombreFilePDF();   
            String name=ListaPreProvision.get(0).getExtensionPDf();
            String extension="";
            Byte[] ArrByte;
            try {
                if ("PDF".equals(tipo))
                    convertByteArrayToDoc(ListaPreProvision.get(0).getTheFilePDF(),fileName, ListaPreProvision.get(0).getNombreFilePDF(),ListaPreProvision.get(0).getExtensionPDf(),response);
                 if ("XML".equals(tipo))
                    convertByteArrayToDoc(ListaPreProvision.get(0).getTheFileXML(),fileName, ListaPreProvision.get(0).getNombreFileXML(),ListaPreProvision.get(0).getExtensionXML(),response);
                 if ("CDR".equals(tipo))
                    convertByteArrayToDoc(ListaPreProvision.get(0).getTheFileCDR(),fileName, ListaPreProvision.get(0).getNombreFileCDR(),ListaPreProvision.get(0).getExtensionCDR(),response);
                        
           } catch (Exception e) {
                    System.out.println("Exception: PDF " + e);
            }


        } catch (Exception e) {

            throw e;
        } finally {
            Util.close(cn);
            if (baos != null) {
                try {
                    baos.close();
                } catch (final Exception e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final Exception e) {
                }
            }
        }
    }    
            

   public void generaAsiento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                String selected = Util.nullCad(request.getParameter("selected")).trim();
                String asiento  = "0"; //Util.nullCad(request.getParameter("asiento")).trim();
                int pASiento = Util.nullNum(asiento);
                String arrElem[] = selected.split("\\#");
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                jsonObjMsg = new JSONObject();
                int i=0;
                int salir=0;
                String resultado="";
                String pRucProveedor ="";
                String pIndice =""; 
                String pTipoDocumento ="";
                String pSerieDoc ="";
                String pNumeroDoc ="";                
                while ((i<arrElem.length)&&(salir!=1))
                {
                    String arrItem[] = arrElem[i].split(",");
                    pRucProveedor = arrItem[0] ;
                    pIndice  = arrItem[1] ;
                    pTipoDocumento = arrItem[2] ;
                    pSerieDoc = arrItem[3] ;
                    pNumeroDoc = arrItem[4] ;
                    resultado = new PreProvisionDAO().generaAsiento(pRucEmpresa,arrItem[0],arrItem[1],arrItem[2],arrItem[3],pASiento);
                    salir = (!"".equals(Util.nullCad(resultado)))?1:0;
                    i++;
                    pASiento++;
                }
                if (salir==1) {
                        jsonObjMsg.put("tipoMsg", "error");
                        jsonObjMsg.put("msgError", resultado);
                } else 
                {
                        jsonObjMsg.put("tipoMsg", "exito");
                        jsonObjMsg.put("msgError", "");
                        /*-------------------------------------------*/
                        List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_busca_PreProvision(
                        usuario.getRucEmpresa(),pRucProveedor,pIndice,
                        pSerieDoc,pNumeroDoc,pTipoDocumento);                                             
                        JSONObject jObjRegistro = new JSONObject();								
                        jObjRegistro.put("empresa", usuario.getCodTienda());
                        jObjRegistro.put("item", ListaPreProvision.get(0).getTipogasto());
                        jObjRegistro.put("poliza", ListaPreProvision.get(0).getPoliza());
                        jObjRegistro.put("codigoProveedor", pRucProveedor);
                        jObjRegistro.put("serieDocumento", pSerieDoc);
                        jObjRegistro.put("numeroDocumento", pNumeroDoc);
                        jObjRegistro.put("tipoDocumento", pTipoDocumento);
                        jObjRegistro.put("igvTotal", 0);
                        jObjRegistro.put("baseImponible", ListaPreProvision.get(0).getGravado());
                        jObjRegistro.put("inafecto", 0);
                        jObjRegistro.put("igv", ListaPreProvision.get(0).getPorIgv());
                        jObjRegistro.put("total", 0);
                        jObjRegistro.put("fechaDocumento", ListaPreProvision.get(0).getFechaDocumento());
                        jObjRegistro.put("moneda", ListaPreProvision.get(0).getMoneda()); 
                        jObjRegistro.put("fechacontable", ListaPreProvision.get(0).getFechaContable()); 
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
                        /*-------------------*/
                }                    
                
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }      
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
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
          
    
    public void EliminarAsiento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                //String pRucProveedor = usuario.getRucProveedor();
                String pRucProveedor =Util.nullCad(request.getParameter("rucProveedor"));
                String mes = Util.nullCad(request.getParameter("mes"));
                long asiento = Util.nullNum(request.getParameter("asiento"));
                String serieDoc = Util.nullCad(request.getParameter("serieDoc"));
                String numeroDoc = Util.nullCad(request.getParameter("numeroDoc"));
                String tipoDocumento = Util.nullCad(request.getParameter("tipoDocumento"));
                String tipoComprobante = Util.nullCad(request.getParameter("tipoComprobante"));
                String anho = Util.nullCad(request.getParameter("anho"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new PreProvisionDAO().EliminarAsiento(pRucEmpresa,
                 pRucProveedor, serieDoc, numeroDoc, tipoDocumento,tipoComprobante,mes,asiento,anho);
                 jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(resultado))) {
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
    
    
    public void adicionarGastosTemporal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));

                String importe =Util.nullCad(request.getParameter("importe"));
                String gasto = Util.nullCad(request.getParameter("gasto"));
                String rucproveedor = Util.nullCad(request.getParameter("rucProveedor"));
                String descripcion = Util.nullCad(request.getParameter("descripcion"));
                double pimporte = Util.nullDou(importe);               
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new PreProvisionDAO().AdicionaTemporalGastos(seqTmp1,pRucEmpresa,rucproveedor,pimporte,gasto,descripcion);
                 jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(resultado))) {
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
            
     public void muestraGastosTemporal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        double dbTotal = 0;
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                String rucproveedor = Util.nullCad(request.getParameter("rucProveedor"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_temporal_gasto(usuario.getRucEmpresa(),rucproveedor,seqTmp1);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table id=\"tablaAddGastos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"text-center\">Descripcion</th>");
                    sbLista.append("<th class=\"text-center\">Importe</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    for (BeanPreProvision parte : ListaPreProvision) {
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getGlosa())).append("</td>");
                        sbLista.append("<td class=\"text-right\" >").append(Util.nullCad(parte.getBaseImponible())).append("</td>");                        
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EliminarItemTemporal('" + parte.getId() +  "','" + rucproveedor + "')\" ><span class=\"glyphicon glyphicon-remove\"></span></a>").append("</td>");
                        sbLista.append("</tr>");
                        dbTotal+=parte.getBaseImponible();
                    }
                    sbLista.append("<tr>");
                    sbLista.append("<td>").append("TOTAL").append("</td>");
                    sbLista.append("<td class=\"text-right\">").append(dbTotal).append("</td>");
                    sbLista.append("<td>").append("").append("</td>");
                    sbLista.append("</tr>");
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                } 
                jsonObjTabla.put("tabla", sbLista.toString());                
                // Mensajes
               jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                    jsonObjMsg.put("msgError", "");
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }      
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objTabla", jsonObjTabla);
            jsonObject.put("objTotal", dbTotal);
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
           
    
   public void cargaDatosTemporal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                //String pRucProveedor = usuario.getRucProveedor();
                String pRucProveedor =Util.nullCad(request.getParameter("rucProveedor"));
                String serieDoc = Util.nullCad(request.getParameter("serieDoc"));
                String numeroDoc = Util.nullCad(request.getParameter("numeroDoc"));
                String tipoDocumento = Util.nullCad(request.getParameter("tipoDocumento"));
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new PreProvisionDAO().cargaDatosTemporal(pRucEmpresa,
                 pRucProveedor, serieDoc, numeroDoc, tipoDocumento,seqTmp1);
                 jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(resultado))) {
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
   
   
    public void EliminarItemTemporal(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                //String pRucProveedor = usuario.getRucProveedor();
                String pRucProveedor =Util.nullCad(request.getParameter("rucProveedor"));
                long item = Util.nullLon(request.getParameter("item"));                
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new PreProvisionDAO().EliminarItemTemporal(seqTmp1,pRucEmpresa,pRucProveedor, item);
                 jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(resultado))) {
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
            

    public ActionForward inicializaFecha(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            String empresa = (String) session.getAttribute("Empresa");
            PreProvisionForm uform = (PreProvisionForm) form;
            session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));
            uform.setRucEmpresa(usuario.getRucEmpresa());
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia()); 
            uform.setFechaContable(fecha_dia());
            uform.setFlagMueOcuForm("muestra");
            cargaListas(request, uform); 
            mappingFindForward = "inicializaFecha";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    } 

    public ActionForward pipili(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            String empresa = (String) session.getAttribute("Empresa");
            PreProvisionForm uform = (PreProvisionForm) form;
            
            mappingFindForward = "pipili";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    } 
    
    
    public void muestraPreProvisiones_TMP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";         
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                String buscarPor  = Util.nullCad(request.getParameter("buscarPor"));
                String fechaIni  = Util.nullCad(request.getParameter("fechaIni"));
                String fechaFin  = Util.nullCad(request.getParameter("fechaFin"));
                String filtro  = Util.nullCad(request.getParameter("filtro"));
                String fechaContable  = Util.nullCad(request.getParameter("fechaContable"));
                fechaIni = Util.yyyymmdd(fechaIni);
                fechaFin = Util.yyyymmdd(fechaFin);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                new PreProvisionDAO().ELIMINA_TMP_PREPROVISION(usuario.getRucEmpresa(),seqTmp1);
                new PreProvisionDAO().carga_documentosPreProvision(usuario.getRucEmpresa(),"%",seqTmp1,fechaIni,fechaFin,fechaContable,buscarPor);
                List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_Documento_PreProvision_TMP(usuario.getRucEmpresa(),seqTmp1);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table id=\"tablaListaPreProvision\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">Asiento</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                                        
                    sbLista.append("<th class=\"text-center\">Ruc</th>");
                    sbLista.append("<th class=\"text-center\">Razon social</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">Serie</th>");
                    sbLista.append("<th class=\"text-center\">Documento</th>");
                    sbLista.append("<th class=\"text-center\">Fecha</th>");
                    sbLista.append("<th class=\"text-center\">Base Imp.</th>");
                    sbLista.append("<th class=\"text-center\">Moneda</th>");
                    //sbLista.append("<th class=\"text-center\">Glosa</th>");
                    sbLista.append("<th class=\"text-center\">F.Contable</th>");
                    sbLista.append("<th class=\"text-center\">Registro</th>");
                    sbLista.append("<th class=\"text-center\">F.Pago</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    for (BeanPreProvision parte : ListaPreProvision) {
                        sbLista.append("<tr>");
                        
                        strValor= parte.getRuc().trim() + "," + parte.getTipoDocumento().trim() + "," +  parte.getSerieDoc().trim() + "," +  parte.getNumeroDoc().trim() + "," +   parte.getId();

                        sbLista.append("<td>").append(Util.nullCad(parte.getAsiento())).append("</td>");                        
                        if ("0".equals(parte.getAsiento()))
                            if (parte.getRegistro()==0)
                                sbLista.append("<td class=\"text-center\">").append("<input type=\"hidden\" id=\"ruc\" class=\"ruc\"  value='" + parte.getRuc() + "' >  <input type=\"checkbox\" id=\"chk\"  name=\"chk\"  class=\"chk\" disabled=\"true\" onclick=\"myCambio(event)\"  >").append("</td>");
                            else
                                sbLista.append("<td class=\"text-center\">").append("<input type=\"hidden\" id=\"ruc\" class=\"ruc\"  value='" + parte.getRuc() + "' >  <input type=\"checkbox\" id=\"chk\"  name=\"chk\"  class=\"chk\"  onclick=\"myCambio(event)\"  >").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append("").append("</td>");
                        
                        if ("0".equals(parte.getAsiento())) {
                            sbLista.append("<td class=\"text-center\">").append("").append("</td>"); }
                        else{
                             //sbLista.append("<td class=\"text-center\">").append("").append("</td>");
                           sbLista.append("<td class=\"text-center\">").append("<a href=\"#\"  disabled=\"true\" onclick=\"EliminarAsiento('" + parte.getRuc() + "','" + parte.getMes() + "','" +  parte.getAsiento()  + "','" +   parte.getTipoDocumento() + "','" + parte.getTipoComprobante()  + "','" + parte.getSerieDoc()  + "','" +  parte.getNumeroDoc()  + "','" +  parte.getAnho() + "')\" ><span class=\"glyphicon glyphicon-remove\"></span></a>").append("</td>");
                        }
                        sbLista.append("<td><input type=\"hidden\" id=\"valor\" class=\"valor\" value='" + strValor + "'  >").append(Util.nullCad(parte.getRuc())).append("</td>");                        
                        sbLista.append("<td>").append(Util.nullCad(parte.getRs())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTipoDocumenoStr())).append("</td>");                        
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getSerieDoc())).append("</td>");                       
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getNumeroDoc())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFechaDocumento())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getBaseImponible())).append("</td>"); //
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getMonedaStr())).append("</td>");
                        //sbLista.append("<td>").append(Util.nullCad(parte.getGlosa())).append("</td>");  
                        if (parte.getFechaContable()==null)
                            sbLista.append("<td class=\"text-center\">").append(".").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFechaContable())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFechaRegistro())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append("").append("</td>");  
                        if ("0".equals(parte.getAsiento())){
                             sbLista.append("<td class=\"text-center\">").append("&nbsp;").append("</td>");
                            //sbLista.append("<td class=\"text-center\">").append("<a href=\"#\"   disabled=\"true\"  onclick=\"EliminarDocumento('" + parte.getRuc() + "','" + parte.getId() + "','" +  parte.getTipoDocumento() + "','" +  parte.getSerieDoc()  + "','" +  parte.getNumeroDoc() + "')\" ><span class=\"glyphicon glyphicon-remove\"></span></a>").append("</td>");
                        }
                        else
                            sbLista.append("<td class=\"text-center\">").append("&nbsp;").append("</td>");
                        
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EditarDocumento('" +  parte.getRuc() + "','" + parte.getId() + "','" +  parte.getTipoDocumento() + "','" +  parte.getSerieDoc()  + "','" +  parte.getNumeroDoc() +  "','" + parte.getAsiento() +  "')\" ><span class=\"glyphicon glyphicon-edit\"></span></a>").append("</td>");
                        sbLista.append("</tr>");
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                } 
                jsonObjTabla.put("tabla", sbLista.toString());

                // Mensajes
               jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                    jsonObjMsg.put("msgError", "");
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }      
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
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

    
   public void generaAsiento_02(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String pRucEmpresa = usuario.getRucEmpresa();
                String selected = Util.nullCad(request.getParameter("selected")).trim();
                String fechaContable = Util.nullCad(request.getParameter("fechaContable"));
                String asiento  = "0"; //Util.nullCad(request.getParameter("asiento")).trim();
                int pASiento = Util.nullNum(asiento);
                String arrElem[] = selected.split("\\#");
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                jsonObjMsg = new JSONObject();
                int i=0;
                int salir=0;
                String resultado="";
                String pRucProveedor="";
                String pIndice="";
                String pTipoDocumento="";
                String pSerieDoc="";
                String pNumeroDoc="";
               
                while ((i<arrElem.length)&&(salir!=1))
                {
                    String arrItem[] = arrElem[i].split(",");
                    pRucProveedor = arrItem[0] ;                    
                    pTipoDocumento = arrItem[1] ;
                    pSerieDoc = arrItem[2] ;
                    pNumeroDoc = arrItem[3] ; 
                    pIndice = arrItem[4] ; 
                    JSONObject jsonObjResult;
                    jsonObjResult = new PreProvisionDAO().generaAsiento_02(pRucEmpresa,arrItem[0],arrItem[1],arrItem[2],arrItem[3],fechaContable);
                    String tipoMsg;
                    String msg;
                    try {
                        JSONArray jsonArrMsg = (JSONArray) jsonObjResult.get("mensaje");
                        jsonObjMsg = (JSONObject) jsonArrMsg.get(0);
                        tipoMsg = Util.nullCad(jsonObjMsg.get("tipoMsg"));
                        msg = Util.nullCad(jsonObjMsg.get("msg"));
                        salir  = 0 ;
                    } catch (Exception e) {
                        tipoMsg = "error";
                        msg = "" + e;
                        salir = 1;
                    }                    
                    i++;
                    pASiento++;
                    if ("exito".equals(tipoMsg))
                    {
                        /*-------------------------------------------*/
                        List<BeanPreProvision> ListaPreProvision = new PreProvisionDAO().Lista_busca_PreProvision(
                            usuario.getRucEmpresa(),pRucProveedor,pIndice,
                            pSerieDoc,pNumeroDoc,pTipoDocumento);          
                        if (!"".equals(ListaPreProvision.get(0).getPoliza()))
                        {
                            JSONArray jsonArrDatos = (JSONArray) jsonObjResult.get("datos");
                            JSONObject jsonObjDatos = (JSONObject) jsonArrDatos.get(0);                                                               
                            JSONObject jObjRegistro = new JSONObject();								
                            jObjRegistro.put("empresa", usuario.getCodTienda());
                            jObjRegistro.put("item", ListaPreProvision.get(0).getTipogasto());
                            jObjRegistro.put("poliza", ListaPreProvision.get(0).getPoliza());
                            jObjRegistro.put("codigoProveedor", pRucProveedor);
                            jObjRegistro.put("serieDocumento", pSerieDoc);
                            jObjRegistro.put("numeroDocumento", pNumeroDoc);
                            jObjRegistro.put("tipoDocumento", pTipoDocumento);
                            jObjRegistro.put("igvTotal", Util.nullDou(jsonObjDatos.get("igvTotal")));
                            jObjRegistro.put("baseImponible", ListaPreProvision.get(0).getBaseImponible());
                            jObjRegistro.put("inafecto", 0);
                            jObjRegistro.put("igv", ListaPreProvision.get(0).getPorIgv());
                            jObjRegistro.put("total", Util.nullDou(jsonObjDatos.get("total")));
                            jObjRegistro.put("fechaDocumento", ListaPreProvision.get(0).getFechaDocumento());
                            jObjRegistro.put("moneda", ListaPreProvision.get(0).getMoneda()); 
                            jObjRegistro.put("fechacontable", ListaPreProvision.get(0).getFechaContable());
                            jObjRegistro.put("tipoCambio", Util.nullDou(jsonObjDatos.get("tipocambio")));
                            jObjRegistro.put("asiento", Util.nullLon(jsonObjDatos.get("asiento")));
                            jObjRegistro.put("asientodatos", Util.nullCad(jsonObjDatos.get("asientodatos")));
                            Boolean bExisto;
                            String mensajeWS="";                
                            JSONObject jsonObjResultWS = consumeRestJsonTipo02(Constantes.URL_ACTUALIZA_POLIZA, jObjRegistro);
                            try {
                                    bExisto = Util.nullBoo(jsonObjResultWS.get("estado"));                                   
                                } catch (Exception e) {
                                    bExisto = false;
                                    mensajeWS = "" + e;
                                }  
                        }
                        /*-------------------*/                        
                        
                    } 
                    else 
                    {
                        jsonObjMsg.put("tipoMsg", "error");
                        jsonObjMsg.put("msgError", msg);   
                        salir=1;
                        resultado = msg;
                    }
                }
                if (salir==1) {
                        jsonObjMsg.put("tipoMsg", "error");
                        jsonObjMsg.put("msgError", resultado);
                } else {
                        jsonObjMsg.put("tipoMsg", "exito");
                        jsonObjMsg.put("msgError", "");
                }                    
                
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }      
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
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

   
}
