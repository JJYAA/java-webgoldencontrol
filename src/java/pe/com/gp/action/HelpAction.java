/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.dao.HelpDAO;
import pe.com.gp.entity.HelpGenerico;
import pe.com.gp.entity.Usuario;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author Administrador
 */
public class HelpAction extends DispatchAction {

 private static final String SEQ_TMP1_SESSION_KEY = "FacRepMosSeqTmp1_" + UUID.randomUUID().toString();
    private static final Logger LOGGER = LogManager.getLogger();
    
// ====================================================
	// CONSULTA DE PROVEEDOR y CLIENTE
	// ====================================================
	// Buscador de Proveedores (SAP)
	@SuppressWarnings("resource")
	public void helpClientes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
                HttpSession session = request.getSession(); 
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		JSONObject jsonObjMsg = new JSONObject();
		JSONObject jsonObjTabla = new JSONObject();
		try {
			if (Util.sesionEstaActiva(request)) {
				// Parametros
                                String empresa = (String) session.getAttribute("Empresa");                                
                                
				String descripcion = Util.nullCad(request.getParameter("descripcion"));
				String tipoBusqueda = Util.nullCad(request.getParameter("tipoBusqueda"));
				//String tipoBuscaueda = Util.nullCad(request.getParameter("tipoBuscaueda"));

				// Consulta a la BD
				List<HelpGenerico> lista = new HelpDAO().buscarCliente(empresa, descripcion, tipoBusqueda);
				// Armando tabla
				StringBuilder sbTabla = new StringBuilder();
				sbTabla.append("<div style=\"height:284px;overflow-y:auto\">");
				sbTabla.append(
						"<table id=\"tableHelpGenerico\" class=\"table nmb fs-11 mini-table table-condensed table-bordered\">");
				sbTabla.append("<thead>");
				sbTabla.append("<tr>");
				sbTabla.append("<th>C&oacute;digo</th>");
				sbTabla.append("<th>Descripci&oacute;n</th>");
				sbTabla.append("<th style=\"width:40px\">&nbsp;</th>");
				sbTabla.append("</tr>");
				sbTabla.append("</thead>");
				if (!lista.isEmpty()) {
					sbTabla.append("<tbody>");
					for (HelpGenerico obj : lista) {
						sbTabla.append("<tr>");
						sbTabla.append("</td>");
						sbTabla.append("<td>").append(Util.nullCad(obj.getCodigo())).append("</td>");
						sbTabla.append("<td>").append(Util.nullCad(obj.getDescripcion())).append("</td>");						
							sbTabla.append("<td class=\"text-center\"><button onclick=\"eligeClienteCodigo('")
									.append(Util.nullCad(obj.getCodigo()))
									.append("');\" title=\"elegir\" class=\"btn btn-xs btn-default\" type=\"button\">"
											+ Constantes.ICON_CHECK + "</button></td>");
						
						sbTabla.append("</tr>");
					}
					sbTabla.append("</tbody>");
				}
				sbTabla.append("</table>");
				sbTabla.append("</div>");
				jsonObjTabla.put("tabla", sbTabla.toString());
			} else {
				jsonObjMsg.put("tipoMsg", "relogin");
			}

			// Colocando todo en un solo objeto
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
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
				jsonObjMsg.put("msg", "GP.ERROR: " + e);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("objMensaje", jsonObjMsg);
				sb.append(jsonObject.toString());
				out = response.getWriter();
				out.write(sb.toString());
			} catch (JSONException | IOException ex) {
				LOGGER.error("GP.ERROR: {}", e);
			}
		} finally {
			Util.close(out);
		}
	}

        public void helpProductos(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
                HttpSession session = request.getSession(); 
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("cache-control", "no-cache");
		StringBuilder sb = new StringBuilder();
		PrintWriter out = null;
		JSONObject jsonObjMsg = new JSONObject();
		JSONObject jsonObjTabla = new JSONObject();
		try {
			if (Util.sesionEstaActiva(request)) {
				// Parametros
                                String empresa = (String) session.getAttribute("Empresa");                                
                                
				String descripcion = Util.nullCad(request.getParameter("descripcion"));
				String tipoBusqueda = Util.nullCad(request.getParameter("tipoBusqueda"));
				//String tipoBuscaueda = Util.nullCad(request.getParameter("tipoBuscaueda"));

				// Consulta a la BD
				List<HelpGenerico> lista = new HelpDAO().buscarProductos(empresa, descripcion, tipoBusqueda);
				// Armando tabla
				StringBuilder sbTabla = new StringBuilder();
				sbTabla.append("<div style=\"height:284px;overflow-y:auto\">");
				sbTabla.append(
						"<table id=\"tableHelpGenerico\" class=\"table nmb fs-11 mini-table table-condensed table-bordered\">");
				sbTabla.append("<thead>");
				sbTabla.append("<tr>");
				sbTabla.append("<th>C&oacute;digo</th>");
				sbTabla.append("<th>Descripci&oacute;n</th>");
				sbTabla.append("<th style=\"width:40px\">&nbsp;</th>");
				sbTabla.append("</tr>");
				sbTabla.append("</thead>");
				if (!lista.isEmpty()) {
					sbTabla.append("<tbody>");
					for (HelpGenerico obj : lista) {
						sbTabla.append("<tr>");
						sbTabla.append("</td>");
						sbTabla.append("<td>").append(Util.nullCad(obj.getCodigo())).append("</td>");
						sbTabla.append("<td>").append(Util.nullCad(obj.getDescripcion())).append("</td>");						
							sbTabla.append("<td class=\"text-center\"><button onclick=\"eligeProductoCodigo('")
									.append(Util.nullCad(obj.getCodigo()))
									.append("');\" title=\"elegir\" class=\"btn btn-xs btn-default\" type=\"button\">"
											+ Constantes.ICON_CHECK + "</button></td>");
						
						sbTabla.append("</tr>");
					}
					sbTabla.append("</tbody>");
				}
				sbTabla.append("</table>");
				sbTabla.append("</div>");
				jsonObjTabla.put("tabla", sbTabla.toString());
			} else {
				jsonObjMsg.put("tipoMsg", "relogin");
			}

			// Colocando todo en un solo objeto
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("objTabla", jsonObjTabla);
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
				jsonObjMsg.put("msg", "GP.ERROR: " + e);
				JSONObject jsonObject = new JSONObject();
				jsonObject.put("objMensaje", jsonObjMsg);
				sb.append(jsonObject.toString());
				out = response.getWriter();
				out.write(sb.toString());
			} catch (JSONException | IOException ex) {
				LOGGER.error("GP.ERROR: {}", e);
			}
		} finally {
			Util.close(out);
		}
	}
    
}
