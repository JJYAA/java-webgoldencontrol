/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.dao.AlmacenesDAO;
import pe.com.gp.dao.StockDAO;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.StockForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;
import static pe.com.gp.util.Util.fecha_dia;

/**
 *
 * @author Administrador
 */
public class BoletaAlmacenAction extends DispatchAction {

    private static final Logger LOGGER = LogManager.getLogger();

    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            StockForm uform = (StockForm) form;
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia());
            uform.setEmpresa(empresa);
            muestaLista(request, uform);

            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }

    public void muestaLista(HttpServletRequest request, StockForm form) throws Exception {
        request.setAttribute("listaAlmacen", new AlmacenesDAO().listaAlmacenes(form.getEmpresa()));
        request.setAttribute("listaTipoBoletas", new AlmacenesDAO().listaTipoBoleta(form.getEmpresa()));

    }

    public void consultaListaBoletas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                String tipoBoleta = Util.nullCad(request.getParameter("tipoBoleta"));
                String fechaIni = Util.nullCad(request.getParameter("fechaInicio"));
                String fechaFin = Util.nullCad(request.getParameter("fechaFinal"));
                String Almacen = request.getParameter("almacen");
                String actividad = request.getParameter("actividad");
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Parte> listaPartesTempo = new StockDAO().consultaListaBoletas(empresa, actividad, tipoBoleta, Almacen, fechaIni, fechaFin);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">Boleta</th>");
                    sbLista.append("<th class=\"text-center\">Tipo</th>");
                    sbLista.append("<th class=\"text-center\">Fecha</th>");
                    sbLista.append("<th class=\"text-center\">Cliente</th>");
                    sbLista.append("<th class=\"text-center\">Razon social</th>");
                    sbLista.append("<th class=\"text-center\">Entrega</th>");
                    sbLista.append("<th class=\"text-center\">Usuario</th>");
                    sbLista.append("<th class=\"text-center\">Almacen</th>");
                    sbLista.append("<th class=\"text-center\">Docu.</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (Parte parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        //sbLista.append("<td class=\"details-control\"></td>");
                        //
                        
                        sbLista.append("<td>").append("<a href=\"BoletaAlmacenAction.do?operacion=boletaAlmacen&empresa=" + empresa + "&tipoBoleta=" + parte.getTipoBoleta() +"&actividad=00000&almacen=00&boleta=" + parte.getBoleta() + " \" target=\"_blank\"   >").append(parte.getBoleta()).append("</a>").append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDesc_tipo_boleta())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getFechaBoleta())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigoCliente())).append("</td>"); //
                        sbLista.append("<td>").append(Util.nullCad(parte.getNombreCliente())).append("</td>");
                        String ls_pendiente = parte.getFechaEntrega();
                        if (ls_pendiente == null) {
                            ls_pendiente = "";
                        }
                        if ("PENDIENTE".equals(ls_pendiente)) {
                            sbLista.append("<td style=\"background-color:red\">").append(Util.nullCad(parte.getFechaEntrega())).append("</td>");
                        } else {
                            sbLista.append("<td>").append(Util.nullCad(parte.getFechaEntrega())).append("</td>");
                        }
                        sbLista.append("<td>").append(Util.nullCad(parte.getUsuario())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getAlmacen())).append("</td>");
                        sbLista.append("<td>").append(parte.getNotaDocu()).append("</td>");
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

   

    public void boletaAlmacen(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        Connection conn = new ConectaSQL().connection();
        ByteArrayOutputStream baos = null;
        ServletOutputStream out = null;        
        try {
            String path = request.getServletContext().getRealPath("/");
           
            Map parameters = new HashMap();
            String ls_empresa = (String) request.getParameter("empresa");
            String ls_tipoBoleta = (String) request.getParameter("tipoBoleta");
            String ls_actividad = (String) request.getParameter("actividad");
            String ls_almacen = (String) request.getParameter("almacen");
            String ls_boleta = (String) request.getParameter("boleta");
            Long ln_boleta = Util.nullLon(ls_boleta);
            parameters.put("pempresa", ls_empresa);
            parameters.put("ptipo", ls_tipoBoleta);
            parameters.put("pactividad", ls_actividad);
            parameters.put("palmacen", ls_almacen);
            parameters.put("pboleta", ln_boleta);
            //params.put("empNo", "00003");    
            String fileJasper = "boletaAlmacen.jasper";
            
            String jasper = path + Constantes.DIRECTORIO_IREPORT + "//" + fileJasper;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, parameters, conn);
            String filename;
            @SuppressWarnings("unused")
            String time = new SimpleDateFormat("HHmmssSSS").format(new java.util.Date());
            baos = new ByteArrayOutputStream();
            response.setContentType("application/pdf");
            filename = "prueba" + ".pdf";
            response.setHeader("content-disposition", "inline; filename=\"" + filename + "\"");
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            out = response.getOutputStream();
            baos.writeTo(out);
            baos.flush();
            baos.close();             

        } catch (Exception e) {
             LOGGER.info("<==== FINAL Metodo: inicializa ====>" + e.toString());
            throw e;
        } finally {
            Util.close(conn);
            if (baos != null) {
                try {
                    baos.close();
                } catch (final Exception e) {
                     LOGGER.info("<==== FINAL Metodo: inicializa ====>" + e.toString());
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final Exception e) {
                     LOGGER.info("<==== FINAL Metodo: inicializa ====>" + e.toString());
                }
            }
        }

    }

}
