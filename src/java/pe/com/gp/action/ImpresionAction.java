/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.dao.ImpresionDAO;
import pe.com.gp.entity.Documento;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.ImpresionForm;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class ImpresionAction extends DispatchAction {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            
            ImpresionForm uform = (ImpresionForm) form;
            uform.setFechaFinal(Util.fecha_dia());
            uform.setFechaInicial(Util.fecha_dia());
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }  
    
    public void listaDocumentosFacturados(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
       
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String fechaIni = Util.nullCad(Util.yyyymmdd(request.getParameter("fechaIni")));
                String fechaFin = Util.nullCad(Util.yyyymmdd(request.getParameter("fechaFin")));
                String tipoDocu = Util.nullCad(request.getParameter("tipoDocu"));
                
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Documento> listaDocumentos = new ImpresionDAO().listaDocumentosFacturados(empresa, fechaIni,fechaFin,tipoDocu);
                if (listaDocumentos != null && !listaDocumentos.isEmpty()) {
                    sbLista.append("<table id=\"tablaListaDocumentos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"text-center\">T.Docu</th>");
                    sbLista.append("<th class=\"text-center\">Fecha G.</th>");
                    sbLista.append("<th class=\"text-center\">MD</th>");
                    sbLista.append("<th class=\"text-center\">Documento</th>");                                        
                    sbLista.append("<th class=\"text-center\">Cliente</th>");
                    sbLista.append("<th class=\"text-center\">Direcci&oacuote;n</th>");
                    sbLista.append("<th class=\"text-center\">Total</th>");
                    sbLista.append("<th class=\"text-center\">Ver</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (Documento documento : listaDocumentos) {
                        String cadenaOfertVenta = ""; // "  <a href=\"PedidoOfertaRepAction.do?operacion=visualizaDocumento&empresa=" + empresa +"&actividad=" + documento.getTienda() + "&tipoDocumento=" + documento.getTipo() + "&documento=" + documento.getStrNumero() + "\" target=\"_blank\" title=\"Imprimir\"> Ver</a> " ;
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(documento.getTipoDocumento() )).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(documento.getFecha() )).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(documento.getMoneda())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(documento.getDocumento())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(documento.getNombre())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(documento.getDireccion())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullDou(documento.getTotal())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(cadenaOfertVenta).append("</td>");
                        sbLista.append("</tr>");
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                    jsonObjTabla.put("ctosRegs", listaDocumentos.size());
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
        

    
}
