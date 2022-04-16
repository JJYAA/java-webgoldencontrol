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
import pe.com.gp.dao.ParteDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.dao.StockDAO;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.form.StockForm;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class StockAction  extends DispatchAction {

    private static final Logger LOGGER = LogManager.getLogger();
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            StockForm uform = (StockForm) form;
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }
    

    public void muestraStockProductos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                String numeroParte = Util.nullCad(request.getParameter("codigoProducto"));
                String chkvalor = Util.nullCad(request.getParameter("chkvalor"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Parte> listaPartesTempo = new StockDAO().muestraListaProductos(empresa, numeroParte,chkvalor);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">Producto</th>");
                    sbLista.append("<th class=\"text-center\">Descripci&oacute;n</th>");
                    sbLista.append("<th class=\"text-center\">Total</th>");
                    sbLista.append("<th class=\"text-center\">Disponible</th>");
                    sbLista.append("<th class=\"text-center\">Almacen</th>");
                    sbLista.append("<th class=\"text-center\">UnixCajas</th>");
                    sbLista.append("<th class=\"text-center\"># Cajas</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (Parte parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"details-control\"></td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigoProducto())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotal())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getDisponible())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getAlmacen())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadCajas())).append("</td>");
                        sbLista.append("</tr>");
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                    jsonObjTabla.put("ctosRegs", listaPartesTempo.size());
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
