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
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.dao.ControlCajasDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.entity.StockRepuestos;
import pe.com.gp.form.TrasladoForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author Administrador
 */
public class ControlCajaAction  extends DispatchAction {
    
    private static final String SEQ_TMP1_SESSION_KEY = "FacRepMosSeqTmp1_" + UUID.randomUUID().toString();    
    private static final Logger LOGGER = LogManager.getLogger();

    
    
    public ActionForward btnConControl(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");            
        HttpSession session = request.getSession();
        ActionErrors errors = new ActionErrors();
        String mappingFindForward;
        TrasladoForm uform = (TrasladoForm) form;
        if (Util.sesionEstaActiva(request)) {
            String empresa = (String) session.getAttribute("Empresa");        
            Long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));            
            String resultado = new ControlCajasDAO().procesaControl(empresa, seqTmp1,"","","","");
            if ("exito".equals(resultado)) 
                errors.add("exito2", new ActionMessage("Transacci&oacute;n terminada con exito ", false));
             else
                errors.add("error", new ActionMessage("No se puede generar la Transaccion", false));
            if (!errors.isEmpty()) {
                saveErrors(request, errors);
            }
             uform.setFlagMueOcuForm("oculta");
             mappingFindForward = "msgConControlCajas";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: conFacRepMostrador ====>");
        return mapping.findForward(mappingFindForward);
    }    

            
            
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");            
        HttpSession session = request.getSession();
        String mappingFindForward;
        String empresa = (String) session.getAttribute("Empresa");
        session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));        
        //if (Util.sesionEstaActiva(request)) {        
        //Usuario usuario = (Usuario) session.getAttribute("Usuario");
        //Tienda tienda = (Tienda) session.getAttribute("Tienda");
        //Global global = (Global) session.getAttribute("Global");
        //GenericoForm uform = (GenericoForm) form;                
        TrasladoForm uform = (TrasladoForm) form;
        uform.setFlagMueOcuForm("muestra");
        String path = request.getServletContext().getRealPath("/");
        String PathImg = path + Constantes.DIRECTORIO_IMAGENES ;        
       // List<Parte> listaPartesTempo = new CatalogoDAO().muestraListaProductos(empresa, "", "1",PathImg);
       // request.setAttribute("data", listaPartesTempo);         
       // muestaLista(request,uform);
        mappingFindForward = "inicializa";
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }    

    public void buscarBoletaFactura(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        Long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
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
                String boletaIngreso = Util.nullCad(request.getParameter("Ntoboleta"));
                long boleta = Util.nullLon(boletaIngreso);
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                new ControlCajasDAO().procesaBoletaAlmacen(empresa, "005",boleta,"%","%",seqTmp1);   
                
                List<StockRepuestos> listaPartesTempo = new ControlCajasDAO().muestraBoletaAlmacen(empresa,seqTmp1);   
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablasBoletasFactura\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"text-center\">Producto</th>");
                    sbLista.append("<th class=\"text-center\">Descripci&oacute;n</th>");
                    sbLista.append("<th class=\"text-center\">Cant.</th>");
                    sbLista.append("<th class=\"text-center\">UnixCajas</th>");
                    sbLista.append("<th class=\"text-center\">#Cajas</th>");
                    sbLista.append("<th class=\"text-center\">NC Dev.</th>");
                    sbLista.append("<th class=\"text-center\">Entregados</th>");
                    sbLista.append("<th class=\"text-center\">Control</th>");
                    sbLista.append("<th class=\"text-center\">Cajas</th>");
                    sbLista.append("<th class=\"text-center\">Eliminar</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (StockRepuestos parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodProducto())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidad())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadDev())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getEntregados())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getControl())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getControlCajas())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EliminiarItem('").append(parte.getCodProducto()).append("')\" class=\"btn btn-sm\"><span class=\"glyphicon glyphicon-remove\"></span> </a>").append("</td>");
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
            }
            else
            {
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
        }catch (Exception e) {
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
    

    public void buscarProducto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        HttpSession session = request.getSession();
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();       
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");  
        long nroCajas = 0;
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String cantidad = Util.nullCad(request.getParameter("cantidad"));
                String codigoProducto = Util.nullCad(request.getParameter("codigoProducto")); 
                String tipo = "01"; 
                long ln_cantidad = Util.nullLon(cantidad);                
                String control = request.getParameter("control");
                Long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));             
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = "";
                if ("0".equals(control)){
                    if (new ControlCajasDAO().validaCajasTmp(empresa, seqTmp1,codigoProducto,ln_cantidad)>0)
                        resultado = new ControlCajasDAO().agregarCajasProducto(empresa, seqTmp1,codigoProducto,ln_cantidad,tipo);   
                } else 
                    resultado = new ControlCajasDAO().agregarCajasProducto(empresa, seqTmp1,codigoProducto,ln_cantidad,tipo);   
                
                List<StockRepuestos> listaPartesTempo = new ControlCajasDAO().muestraBoletaAlmacen(empresa,seqTmp1);                   
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) 
                {
                    sbLista.append("<table id=\"tablasBoletasFactura\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"text-center\">Producto</th>");
                    sbLista.append("<th class=\"text-center\">Descripci&oacute;n</th>");
                    sbLista.append("<th class=\"text-center\">Cant.</th>");
                    sbLista.append("<th class=\"text-center\">UnixCajas</th>");
                    sbLista.append("<th class=\"text-center\">#Cajas</th>");
                    sbLista.append("<th class=\"text-center\">NC Dev.</th>");
                    sbLista.append("<th class=\"text-center\">Entregados</th>");
                    sbLista.append("<th class=\"text-center\">Control</th>");
                    sbLista.append("<th class=\"text-center\">Cajas</th>");
                    sbLista.append("<th class=\"text-center\">Eliminar</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (StockRepuestos parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodProducto())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidad())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadDev())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getEntregados())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getControl())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getControlCajas())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EliminiarItem('").append(parte.getCodProducto()).append("')\" class=\"btn btn-sm\"><span class=\"glyphicon glyphicon-remove\"></span> </a>").append("</td>");
                        sbLista.append("</tr>");
                        nroCajas +=  parte.getControlCajas();
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                    jsonObjTabla.put("ctosRegs", listaPartesTempo.size());
                    jsonObjTabla.put("ctosCajas", nroCajas);
                } else {
                    jsonObjTabla.put("ctosRegs", 0);
                    jsonObjTabla.put("ctosCajas", nroCajas);
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
                jsonObjMsg.put("tipoMsg", "relogin");                
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objTotales", jsonObjTotales);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());            
        }catch (Exception e) {
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


    public ActionForward eliminarItems(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        HttpSession session = request.getSession();
        StringBuilder sb = new StringBuilder();       
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();        
        long nroCajas=0;
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String empresa = (String) session.getAttribute("Empresa");                       
                String codigoProducto = Util.nullCad(request.getParameter("codigoProducto")); 
                String tipo = "02";
                long ln_cantidad = 0; 
                Long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));             
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new ControlCajasDAO().agregarCajasProducto(empresa, seqTmp1,codigoProducto,ln_cantidad,tipo);   
                List<StockRepuestos> listaPartesTempo = new ControlCajasDAO().muestraBoletaAlmacen(empresa,seqTmp1);                   
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) 
                {
                    sbLista.append("<table id=\"tablasBoletasFactura\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"text-center\">Producto</th>");
                    sbLista.append("<th class=\"text-center\">Descripci&oacute;n</th>");
                    sbLista.append("<th class=\"text-center\">Cant.</th>");
                    sbLista.append("<th class=\"text-center\">UnixCajas</th>");
                    sbLista.append("<th class=\"text-center\">#Cajas</th>");
                    sbLista.append("<th class=\"text-center\">NC Dev.</th>");
                    sbLista.append("<th class=\"text-center\">Entregados</th>");
                    sbLista.append("<th class=\"text-center\">Control</th>");
                    sbLista.append("<th class=\"text-center\">Cajas</th>");
                    sbLista.append("<th class=\"text-center\">Eliminar</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (StockRepuestos parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodProducto())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidad())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadCajas())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidadDev())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getEntregados())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getControl())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getControlCajas())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"EliminiarItem('").append(parte.getCodProducto()).append("')\" class=\"btn btn-sm\"><span class=\"glyphicon glyphicon-remove\"></span> </a>").append("</td>");
                        sbLista.append("</tr>");
                        nroCajas+= parte.getControlCajas();
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                    jsonObjTabla.put("ctosRegs", listaPartesTempo.size());
                    jsonObjTabla.put("ctosCajas", nroCajas);
                } else {
                    jsonObjTabla.put("ctosRegs", 0);
                    jsonObjTabla.put("ctosCajas", nroCajas);
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
                jsonObjMsg.put("tipoMsg", "relogin");                
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objTotales", jsonObjTotales);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());            
        }catch (Exception e) {
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
        mappingFindForward = "inicializa";
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }    

    
}
