/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.CallableStatement;
import java.sql.Connection;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.dao.PagoProveedoresDAO;
import pe.com.gp.entity.Banco;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PreProvisionForm;
import pe.com.gp.util.Util;
import static pe.com.gp.util.Util.fecha_dia;

/**
 *
 * @author Computer
 */
public class fechaProgramacionDocAction extends DispatchAction {
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
            session.setAttribute(SEQ_TMP1_SESSION_KEY, usuario.getSecuencia());
            PreProvisionForm uform = (PreProvisionForm) form;
            uform.setFlagMueOcuForm("muestra");
            String fechadia= fecha_dia();
            uform.setFechaContable(fecha_dia());
            uform.setPeriodo(fechadia.substring(5,10));
            uform.setFlagMueOcuForm("muestra");
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }     
    
    
    
    public ActionForward inicializaRango(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            String empresa = (String) session.getAttribute("Empresa");
            session.setAttribute(SEQ_TMP1_SESSION_KEY, usuario.getSecuencia());
            PreProvisionForm uform = (PreProvisionForm) form;
            uform.setFlagMueOcuForm("muestra");
            String fechadia= fecha_dia();
            uform.setFechaIni(fechadia);
            uform.setFechaFin(fechadia);
            uform.setFechaContable(fecha_dia());
            uform.setPeriodo(fechadia.substring(5,10));
            uform.setFlagMueOcuForm("muestra");
            mappingFindForward = "inicializaRango";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }             
    
   
    public void validaSaldoDocumentos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
            Global global = (Global) session.getAttribute("Global");
            Usuario usuario = (Usuario) session.getAttribute("Usuario"); 
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            String formaPago  = Util.nullCad(request.getParameter("formaPago"));
            String proveedor  = Util.nullCad(request.getParameter("proveedor"));
            String selected  = Util.nullCad(request.getParameter("selected"));
            String fechaContable  = Util.nullCad(request.getParameter("fechaContable"));
            String cuentaBancaria="";
            if (Util.sesionEstaActiva(request)) {
            new PagoProveedoresDAO().eliminarTMPDocumento(seqTmp1,fechaContable,usuario.getRucEmpresa());                
                String items[] = selected.split(",");
                boolean salir = false;
                int i =0;
                String pGlosa="";
                while ((i<=items.length -1)&&(!salir)){
                    String element[] = items[i].split("\\|");
                    double porPagar = Util.nullDou(element[2]);
                    pGlosa= element[3];
                    double saldo = new PagoProveedoresDAO().saldoDocumento(usuario.getRucEmpresa(),formaPago,proveedor,element[0],element[1]);
                    if (porPagar>saldo)
                            salir= true;
                    else
                    {
                        new PagoProveedoresDAO().crearTMPDocumento(seqTmp1,fechaContable,usuario.getRucEmpresa(),formaPago,proveedor,element[0],element[1],porPagar,pGlosa,cuentaBancaria);
                    }
                    i++;
                }
                
                long resultCalculos = 0;
                if (salir) resultCalculos = 1;
                jsonObjMsg.put("resultado", resultCalculos);
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
    
    public ActionForward grabaPagoProveedor(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       LOGGER.info("<==== Inicio Metodo: conFacRepMostrador ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
           // request.setAttribute("listaMotivosAnulacion", new RepOfertaVentasDAO().listaMotivosAnulacion());
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            
            Global global = (Global) session.getAttribute("Global");            
            PreProvisionForm uform = (PreProvisionForm) form;
            String formaPago  = uform.getFormaPagotxt();
            String proveedor  = uform.getRucProveedor();
            String fechaContable  = uform.getFechaContable();
            //          ClientesDAO clientesDAO = new ClientesDAO();
            ActionErrors errors = new ActionErrors();
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
           
            double tipCamVta = Util.nullDou(global.getTipoCambioDolar());
            uform.setFlagMueOcuForm("oculta");
            //int nroRegsTmp = new SecuenciaDAO().contarRegistrosTempo(usuario.getRucEmpresa(),seqTmp1);
            Connection conexion = new ConectaSQL().connection();
            CallableStatement cs = null;
            String result="";               
            JSONObject jsonObjResult;
            jsonObjResult = new PagoProveedoresDAO().GrabarPagoProveedor(seqTmp1,usuario.getRucEmpresa(),proveedor,formaPago,fechaContable);                    
            String tipoMsg;
            String msg;         
            try {
                        JSONArray jsonArrMsg = (JSONArray) jsonObjResult.get("mensaje");
                        JSONObject jsonObjMsg = (JSONObject) jsonArrMsg.get(0);
                        tipoMsg = Util.nullCad(jsonObjMsg.get("tipoMsg"));
                        msg = Util.nullCad(jsonObjMsg.get("msg"));
                    } catch (Exception e) {
                        tipoMsg = "error";
                        msg = "" + e;
                    }
                    if ("exito".equals(tipoMsg)) {                         
                            errors.add("exito2", new ActionMessage(msg, false));                                                                            

                    } else {
                        errors.add("error", new ActionMessage(msg, false));
                    }                    
                
                if (!errors.isEmpty()) {
                    saveErrors(request, errors);
            }
            
            mappingFindForward = "msgGrabaRango";   //msgConFacRepMostrador
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: conFacRepMostrador ====>");

        return mapping.findForward(mappingFindForward);

    }
    
    
    
    /*PROGRAMACION DE DOCUMENTO*/
    public ActionForward grabaProgramaciondePago(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

       LOGGER.info("<==== Inicio Metodo: conFacRepMostrador ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) 
        {
           // request.setAttribute("listaMotivosAnulacion", new RepOfertaVentasDAO().listaMotivosAnulacion());
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            
            Global global = (Global) session.getAttribute("Global");            
            PreProvisionForm uform = (PreProvisionForm) form;
            String formaPago  = uform.getFormaPagotxt();
            String proveedor  = uform.getRucProveedor();
            String fechaContable  = uform.getFechaContable();
            //          ClientesDAO clientesDAO = new ClientesDAO();
            ActionErrors errors = new ActionErrors();
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
           
            double tipCamVta = Util.nullDou(global.getTipoCambioDolar());
            uform.setFlagMueOcuForm("oculta");
            //int nroRegsTmp = new SecuenciaDAO().contarRegistrosTempo(usuario.getRucEmpresa(),seqTmp1);
            Connection conexion = new ConectaSQL().connection();
            CallableStatement cs = null;
            String result="";               
            String selected  = Util.nullCad(request.getParameter("selected"));       

                String items[] = selected.split(",");
                boolean salir = false;
                int i =0;
                String pGlosa="";
                while ((i<=items.length -1)&&(!salir))
                {
                    String element[] = items[i].split("\\|");                    
                    pGlosa= element[3];
                    if ("#".equals(element[5])) element[5] = "";
                    result = new PagoProveedoresDAO().ActualizaDocumentoProgramacion(usuario.getRucEmpresa(), element[4], element[0], element[1],element[2],element[3],uform.getPeriodo(),uform.getFormaPagotxt(),element[5],element[6] );
                    if (!"".equals(result))
                        salir=true;
                    i++;
                }   
                String strRetornar="<a href=\"fechaProgramacionDocAction.do?operacion=inicializa\" class=\"btn btn-sm btn-danger\" ><span class=\"icono-izq\">Regresar</a>";                
                if (!salir) {                         
                            errors.add("exito2", new ActionMessage("Proceso de actualizado termino con exito  " + strRetornar, false));                                                                            

                } else {
                        errors.add("error", new ActionMessage("Error en actulizar la informacion " + result , false));
               } 
                
               if (!errors.isEmpty()) {
                    saveErrors(request, errors);       
               }
            mappingFindForward = "msgProgramaciondePago";   //msgConFacRepMostrador
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: conFacRepMostrador ====>");

        return mapping.findForward(mappingFindForward);

    }
            
      
    
            
    public void asignarCuentasProveedor(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                String proveedor  = Util.nullCad(request.getParameter("proveedor"));
                String cuenta  = Util.nullCad(request.getParameter("cuenta"));
               
               // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                int indice  = 1;
                 String strId = "";
                List<Banco> ListaCuentasBancariasProveedor = new PagoProveedoresDAO().asignarCuentasProveedor(usuario.getRucEmpresa(), proveedor, cuenta);
                if (ListaCuentasBancariasProveedor != null && !ListaCuentasBancariasProveedor.isEmpty()) {
                    sbLista.append("<table id=\"tablaListaCuentasBancarias\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">CUENTA BANCARIA</th>");
                    sbLista.append("<th class=\"text-center\">TIPO CUENTA</th>");
                    sbLista.append("<th class=\"text-center\"></th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    for (Banco lista : ListaCuentasBancariasProveedor) {                        
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"text-left\">").append(lista.getCuentaBancaria()).append("</td>"); 
                        sbLista.append("<td class=\"text-left\">").append(lista.getTipoCuenta_t()).append("</td>"); 
                        sbLista.append("<td class=\"text-center\"><button onclick=\"aceptarItemCuenta('").append(Util.nullCad(lista.getCuentaBancaria())).append("');\" class=\"btn btn-sm btn-default\" type=\"button\">Seleccionar</button></td>");
                        sbLista.append("</tr>");
                        indice++;
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
    
}
