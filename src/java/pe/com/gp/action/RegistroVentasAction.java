/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
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
import pe.com.gp.dao.AsientosDAO;
import pe.com.gp.dao.ImpresionDAO;
import pe.com.gp.dao.TiendaDAO;
import pe.com.gp.entity.Documento;
import pe.com.gp.entity.DtoTienda;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.RegistroVentasForm;
import pe.com.gp.util.Util;
import static pe.com.gp.util.Util.fecha_dia;

/**
 *
 * @author Administrador
 */
public class RegistroVentasAction extends DispatchAction {
    
    
    private static final Logger LOGGER = LogManager.getLogger();
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            RegistroVentasForm uform = (RegistroVentasForm) form;
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia());
            uform.setEmpresa(empresa);
             muestaLista(request,uform);  
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }
    public ActionForward inicializaMigrar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            RegistroVentasForm uform = (RegistroVentasForm) form;
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia());
            uform.setEmpresa(empresa);
            muestaLista(request,uform); 
            /*
            ventas   1bfYDf3FOXg
            cajero  mQWrEs_VpGM            
            */
            String tipo=  Util.desencriptar(uform.getTipo());
            if (tipo.equals("ventas"))
                uform.setFlagMueOcuForm("muestraVentas");
            else
                uform.setFlagMueOcuForm("muestraCajero");
            mappingFindForward = "inicializaMigrar";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }    
    public void muestaLista(HttpServletRequest request,RegistroVentasForm form)  throws Exception 
    {                
        request.setAttribute("listaTiendas", new TiendaDAO().listaTiendas(form.getEmpresa())); 
    }    
    public void muestraListaRegistroVentas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        double neto=0;
        double igv=0;
        double total=0;
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String codigo = Util.nullCad(request.getParameter("codigo"));
                String fechaIni  = Util.nullCad(request.getParameter("fechaIni"));
                String fechaFin  = Util.nullCad(request.getParameter("fechaFin"));                
                String tipoDocumento = request.getParameter("tipoDocumento");
                String Actividad = request.getParameter("actividad");
                String formaPago = request.getParameter("formaPago");
                String tipoVenta = request.getParameter("tipoVenta");            
                
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Documento> listaPartesTempo = new ImpresionDAO().muestraListaRegistroVentas(empresa, codigo,fechaIni,fechaFin,tipoDocumento,Actividad,formaPago,tipoVenta,"0");
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">Tienda</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">Documento</th>");
                    sbLista.append("<th class=\"text-center\">F.Pago</th>");
                    sbLista.append("<th class=\"text-center\">MD</th>");
                    sbLista.append("<th class=\"text-center\">Codigo</th>");
                    sbLista.append("<th class=\"text-center\">Nombre</th>");
                    sbLista.append("<th class=\"text-center\">Fecha</th>");
                    sbLista.append("<th class=\"text-center\">Neto</th>");
                    sbLista.append("<th class=\"text-center\">IGV</th>");
                    sbLista.append("<th class=\"text-center\">Total</th>");
                    sbLista.append("<th class=\"text-center\">Tipo Venta</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (Documento parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"details-control\"></td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTienda())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTipoDocumento())).append("</td>");                       
                        sbLista.append("<td>").append(Util.nullCad(parte.getDocumento())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getFormaPago())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getMoneda())).append("</td>"); //
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigo())).append("</td>");
                        sbLista.append("<td class=\"text-left\">").append(Util.nullCad(parte.getNombre())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getFecha())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getNeto())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getIgv())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotal())).append("</td>");                                                
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTipoVenta())).append("</td>");                                                
                        sbLista.append("</tr>");
                        neto = neto + parte.getNeto();
                        igv = igv + + parte.getIgv();
                        total = total + parte.getTotal();
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
            jsonObjTotales.put("neto", Util.redondear(neto,2));
            jsonObjTotales.put("igv", Util.redondear(igv,2));
            jsonObjTotales.put("total",  Util.redondear(total,2) );        
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
    
     public void muestraListaRegistroVentasMigrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        double neto=0;
        double igv=0;
        double total=0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setDecimalSeparator('.');
	symbols.setGroupingSeparator(',');      
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00", symbols);
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String codigo = Util.nullCad(request.getParameter("codigo"));
                String fechaIni  = Util.nullCad(request.getParameter("fechaIni"));
                String fechaFin  = Util.nullCad(request.getParameter("fechaFin"));                
                String tipoDocumento = request.getParameter("tipoDocumento");
                String Actividad = request.getParameter("actividad");
                String formaPago = request.getParameter("formaPago");
                String tipoVenta = request.getParameter("tipoVenta"); 
                String pendientes = request.getParameter("pendientes"); 
                
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Documento> listaPartesTempo = new ImpresionDAO().muestraListaRegistroVentas(empresa, codigo,fechaIni,fechaFin,tipoDocumento,Actividad,formaPago,tipoVenta,pendientes);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");                  
                    sbLista.append("<th class=\"text-center\">Tienda</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">Documento</th>");
                    sbLista.append("<th class=\"text-center\">MD</th>");
                    sbLista.append("<th class=\"text-center\">Codigo</th>");
                    sbLista.append("<th class=\"text-center\">Nombre</th>");
                    sbLista.append("<th class=\"text-center\">Fecha</th>");
                    sbLista.append("<th class=\"text-center\">Neto</th>");
                    sbLista.append("<th class=\"text-center\">IGV</th>");
                    sbLista.append("<th class=\"text-center\">Total</th>");
                    sbLista.append("<th class=\"text-center\">Tipo Venta</th>");
                    sbLista.append("<th class=\"text-center\">Asiento</th>");
                    sbLista.append("<th class=\"text-center\">").append("Asiento").append("<input type=\"checkbox\" id=\"chkbxTodos\" name=\"chkbxTodos\" class=\"chkbxTodos\" onclick=\"seleccinadosTodos()\" />").append("</th>");                   
                    sbLista.append("<th class=\"text-center\">").append("Eliminar").append("<input type=\"checkbox\" id=\"chkbxTodosDel\" name=\"chkbxTodosDel\" class=\"chkbxTodosDel\" onclick=\"seleccinadosElimina()\" />").append("</th>");                   
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String CadSelected="";
                    for (Documento parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTienda())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTipoDocumentoStr())).append("</td>");                       
                        sbLista.append("<td>").append(Util.nullCad(parte.getDocumento())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getMoneda())).append("</td>"); //
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigo())).append("</td>");
                        sbLista.append("<td class=\"text-left\">").append(Util.nullCad(parte.getNombre())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getFecha())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(decimalFormat.format(parte.getNeto())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(decimalFormat.format(parte.getIgv())).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(decimalFormat.format(parte.getTotal())).append("</td>");                                                
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getTipoVenta())).append("</td>");                                                
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getMigrado())).append("</td>");                                                
                        CadSelected = parte.getTienda() + "|" + parte.getTipoDocumento() + "|" + parte.getDocumento();
                        if (parte.getCantidad_migrada()==0)
                            sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbx\" class=\"chkbx\" onclick=seleccinados()  data-selected=\"").append(CadSelected).append("\">").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbx\" disabled=\"true\" class=\"chkbxTodos\" onclick=seleccinados()  data-selected=\"").append(CadSelected).append("\"  >").append("</td>");
                        
                        CadSelected = empresa + "|" +  parte.getTipoDocumento() + "|" +  parte.getDocumento() + "|" + parte.getTienda()  + "|" +  parte.getMtv() + "|" + parte.getMesv() + "|" + parte.getC_c_tipo_operacion()  + "|" + parte.getVou();
       
                        if (parte.getCantidad_migrada()==0)
                            sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbxDel\" disabled=\"true\" class=\"chkbxTodosDel\" onclick=seleccinadosDel() data-selected=\"").append(CadSelected).append("\"  >").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbxDel\" class=\"chkbxDel\" onclick=seleccinadosDel()  data-selected=\"").append(CadSelected).append("\">").append("</td>");
                        
                        sbLista.append("</tr>");
                        neto = neto + parte.getNeto();
                        igv = igv + + parte.getIgv();
                        total = total + parte.getTotal();
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
            
            
            
            jsonObjTotales.put("neto", decimalFormat.format(neto));
            jsonObjTotales.put("igv", decimalFormat.format(igv));
            jsonObjTotales.put("total", decimalFormat.format(total));        
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
     
     
             
    public ActionForward conAsientoVentas(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request))
        {
            String resultCalculos="";
            ActionErrors errors = new ActionErrors();
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            RegistroVentasForm uform = (RegistroVentasForm) form;
            String docSelected = uform.getDocSelected();
            AsientosDAO asiento = new AsientosDAO();
            String[] arrSelected = docSelected.split(",");
            int i = 0;
            String msg;
            String  pActividad="";
            String  pTipoDoc="";
            String pDocumento="";
            Boolean salir = false;                
            String[] arrayExiste = new String[2];
            int ln_max = arrSelected.length - 1;
            while ((i <= ln_max) && (!salir)) {
               String[] AuxItem = arrSelected[i].split("\\|");
               pActividad = AuxItem[0];
               pTipoDoc = AuxItem[1];
               pDocumento = AuxItem[2];
               resultCalculos = asiento.AsientoVentas(empresa, pTipoDoc,pDocumento,pActividad,"SA");
               if (!"".equals(resultCalculos)) {
                   salir=true;
               }
               i++;
            }
            
            if (!salir)
                errors.add("exito2", new ActionMessage("<strong>" + "Transacci&oacute;n terminada. Proceso termino con exito" + "</strong>", false));                                                                            
            else 
                errors.add("error", new ActionMessage(resultCalculos, false));
            
            if (!errors.isEmpty()) 
                saveErrors(request, errors);                  
            uform.setFlagMueOcuForm("ocultar");
            mappingFindForward = "msgRegistroVentas";
        } 
        else 
        {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }                   

     public void muestraListaCajeroMigrar(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: muestraListaCajeroMigrar ====>");
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
        double neto=0;
        double igv=0;
        double total=0;
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	symbols.setDecimalSeparator('.');
	symbols.setGroupingSeparator(',');      
        DecimalFormat decimalFormat = new DecimalFormat("#,###,##0.00", symbols);
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String fechaIni  = Util.nullCad(request.getParameter("fechaIni"));
                String fechaFin  = Util.nullCad(request.getParameter("fechaFin"));                
                String Actividad = "%";
                String Cajero = "%";
                // Lista los repuestos que estan en el Tempo
                fechaIni = Util.yyyymmdd(fechaIni);
                fechaFin = Util.yyyymmdd(fechaFin);
                StringBuilder sbLista = new StringBuilder();
                List<Documento> listaPartesTempo = new ImpresionDAO().muestraListaCajeroMigrar(empresa, Cajero,fechaIni,fechaFin,Actividad);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");                  
                    sbLista.append("<th class=\"text-center\">Tienda</th>");
                    sbLista.append("<th class=\"text-center\">Caja</th>");
                    sbLista.append("<th class=\"text-center\">Operacion</th>");                  
                    sbLista.append("<th class=\"text-center\">Codigo</th>");
                    sbLista.append("<th class=\"text-center\">Nombre</th>");
                    sbLista.append("<th class=\"text-center\">Fecha</th>");
                    sbLista.append("<th class=\"text-center\">Anulado</th>");
                    sbLista.append("<th class=\"text-center\">Asiento</th>");
                    sbLista.append("<th class=\"text-center\">").append("Asiento").append("<br>").append("<input type=\"checkbox\" id=\"chkbxTodos\" name=\"chkbxTodos\" class=\"chkbxTodos\" onclick=\"seleccinadosTodos()\" />").append("</th>");                    
                    sbLista.append("<th class=\"text-center\">").append("Eliminar").append("<br>").append("<input type=\"checkbox\" id=\"chkbxTodosDel\" name=\"chkbxTodosDel\" class=\"chkbxTodosDel\" onclick=\"seleccinadosElimina()\" />").append("</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String CadSelected="";                    
                    for (Documento parte : listaPartesTempo) {
                        
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getTienda())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCaja())).append("</td>");                       
                        sbLista.append("<td>").append(Util.nullCad(parte.getOperacion())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigo())).append("</td>"); //                        
                        sbLista.append("<td class=\"text-left\">").append(Util.nullCad(parte.getNombre())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getFecha())).append("</td>");  
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getEstado())).append("</td>");  
                        sbLista.append("<td class=\"text-center\">").append(Util.nullCad(parte.getMigrado())).append("</td>");                                                
                        
                        if ("SI".equals(parte.getEstado())){
                           sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbx\" disabled=\"true\" class=\"chkbxTodos\" onclick=seleccinados()  data-selected=\"").append(CadSelected).append("\"  >").append("</td>");
                           sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbxDel\" disabled=\"true\" class=\"chkbxTodosDel\" onclick=seleccinadosDel() data-selected=\"").append(CadSelected).append("\"  >").append("</td>");
                        } 
                        else
                        {
                            CadSelected = parte.getActividad()+ "|" + parte.getCaja()+ "|" + parte.getOperacion();                        
                            if (parte.getCantidad_migrada()==0)
                                sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbx\" class=\"chkbx\" onclick=seleccinados()  data-selected=\"").append(CadSelected).append("\">").append("</td>");
                            else
                                sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbx\" disabled=\"true\" class=\"chkbxTodos\" onclick=seleccinados()  data-selected=\"").append(CadSelected).append("\"  >").append("</td>");
                            CadSelected = empresa + "||" +  parte.getOperacion() + "|" + parte.getActividad()  + "|" +  parte.getMtv() + "|" + parte.getMesv() + "|" + parte.getC_c_tipo_operacion()  + "|" + parte.getVou() + "|" + parte.getAnho() ;     
                            if (parte.getCantidad_migrada()==0)
                                sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbxDel\" disabled=\"true\" class=\"chkbxTodosDel\" onclick=seleccinadosDel() data-selected=\"").append(CadSelected).append("\"  >").append("</td>");
                            else
                                sbLista.append("<td class=\"text-center\" >").append("<input type=\"checkbox\" name=\"chkbxDel\" class=\"chkbxDel\" onclick=seleccinadosDel()  data-selected=\"").append(CadSelected).append("\">").append("</td>");                                                    
                        }
                        
                        
                        sbLista.append("</tr>");
                        neto = neto + parte.getNeto();
                        igv = igv + + parte.getIgv();
                        total = total + parte.getTotal();
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
            
            
            
            jsonObjTotales.put("neto", decimalFormat.format(neto));
            jsonObjTotales.put("igv", decimalFormat.format(igv));
            jsonObjTotales.put("total", decimalFormat.format(total));        
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
        LOGGER.info("<==== Fin Metodo: muestraListaCajeroMigrar ====>");
    }
     

    public ActionForward conAsientoCajero(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request))
        {
            String resultCalculos="";
            ActionErrors errors = new ActionErrors();
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            RegistroVentasForm uform = (RegistroVentasForm) form;
            String docSelected = uform.getDocSelected();
            AsientosDAO asiento = new AsientosDAO();
            Long nroAsiento = Util.nullLon(uform.getSecuencia());
            String[] arrSelected = docSelected.split(",");
            int i = 0;
            String msg;
            String pCaja="";
            String  pActividad="";
            long pOperacion=0;
            Boolean salir = false;                
            String[] arrayExiste = new String[2];
            int ln_max = arrSelected.length - 1;
            while ((i <= ln_max) && (!salir)) {
               String[] AuxItem = arrSelected[i].split("\\|");
               pActividad = AuxItem[0];
               pCaja = AuxItem[1];
               pOperacion = Util.nullLon(AuxItem[2]);
               resultCalculos = asiento.conAsientoCajero(empresa, pActividad,pCaja,pOperacion,nroAsiento);
               nroAsiento++;
               if (!"".equals(resultCalculos)) {
                   salir=true;
               }
               i++;
            }            
            if (!salir)
                errors.add("exito2", new ActionMessage("<strong>" + "Transacci&oacute;n terminada. Proceso termino con exito" + "</strong>", false));                                                                            
            else 
                errors.add("error", new ActionMessage(resultCalculos, false));
            
            if (!errors.isEmpty()) 
                saveErrors(request, errors);                  
            uform.setFlagMueOcuForm("ocultar");
            mappingFindForward = "msgRegistroVentas";
        } 
        else 
        {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }                   

    
    

    public ActionForward conEliminarAsiento(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request))
        {
            String resultCalculos="";
            ActionErrors errors = new ActionErrors();
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            RegistroVentasForm uform = (RegistroVentasForm) form;
            String docSelected = uform.getDocSelectedDel();
            AsientosDAO asiento = new AsientosDAO();
            Long nroAsiento = Util.nullLon(uform.getSecuencia());
            String[] arrSelected = docSelected.split(",");
            int i = 0;
            String msg;
            String pMTV;
            String  pMESV;
            String  pTIPOOPERACION;
            String  pEMPRESA;
            String  pTIPODOCUMENTO;
            String  pDOCUMENTO;  
            String pACTIVIDAD;
            String pANHO;
            long pVOU=0;
            Boolean salir = false;                
            String[] arrayExiste = new String[2];
            int ln_max = arrSelected.length - 1;
            while ((i <= ln_max) && (!salir)) {
               String[] AuxItem = arrSelected[i].split("\\|");
               pEMPRESA = AuxItem[0];
               pTIPODOCUMENTO = AuxItem[1];
               pDOCUMENTO = AuxItem[2];
               pACTIVIDAD = AuxItem[3];               
               pMTV = AuxItem[4];
               pMESV = AuxItem[5];
               pTIPOOPERACION = AuxItem[6];
               pVOU = Util.nullLon(AuxItem[7]);
               pANHO = AuxItem[8];
               resultCalculos = asiento.conEliminarAsiento(pEMPRESA,pTIPODOCUMENTO,pDOCUMENTO,pACTIVIDAD,pMTV, pMESV,pANHO,pTIPOOPERACION,pVOU,uform.getProceso());
               nroAsiento++;
               if (!"".equals(resultCalculos)) {
                   salir=true;
               }
               i++;
            }            
            if (!salir)
                errors.add("exito2", new ActionMessage("<strong>" + "Transacci&oacute;n terminada. Proceso termino con exito" + "</strong>", false));                                                                            
            else 
                errors.add("error", new ActionMessage(resultCalculos, false));
            
            if (!errors.isEmpty()) 
                saveErrors(request, errors);                  
            uform.setFlagMueOcuForm("ocultar");
            mappingFindForward = "msgRegistroVentas";
        } 
        else 
        {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }                   
            
}
