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
public class ProgramaPagosPolizaAction extends DispatchAction {
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
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia()); 
            uform.setFechaContable(fecha_dia());
            uform.setFlagMueOcuForm("muestra");
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    } 
    
    
    public void muestraDocumentoPendientes(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
                String formaPago  = Util.nullCad(request.getParameter("formaPago"));
                String proveedor  = Util.nullCad(request.getParameter("proveedor"));
                String periodo  = Util.nullCad(request.getParameter("periodo"));
               // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                new PagoProveedoresDAO().actualiza_saldo_cuentabanco_proveedor(usuario.getRucEmpresa(), periodo,proveedor, formaPago);
                List<BeanPreProvision> ListaPreProvision = new PagoProveedoresDAO().muestraDocumentoPendientesTodos(usuario.getRucEmpresa(), proveedor, formaPago,periodo);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table id=\"tablaListaDocPendientes\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">RUC</th>");
                    sbLista.append("<th class=\"text-center\">RAZON SOCIAL</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">DOCUMENTO</th>");
                    sbLista.append("<th class=\"text-center\">SALDO</th>");
                    sbLista.append("<th class=\"text-center\">A PAGAR</th>");
                    sbLista.append("<th class=\"text-center\">POLIZA</th>");
                    sbLista.append("<th class=\"text-center\">F.PROGRAMADA</th>");
                    sbLista.append("<th class=\"text-center\">Cuenta Bancaria</th>");                    
                    sbLista.append("<th class=\"text-center\">Glosa</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    for (BeanPreProvision parte : ListaPreProvision) {
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append("<input type=\"hidden\" id=\"ruc\" name=\"ruc\" value='" + parte.getRuc() + "'  > ").append(Util.nullCad(parte.getRuc())).append("</td>");                        
                        sbLista.append("<td>").append(Util.nullCad(parte.getRs())).append("</td>");
                        sbLista.append("<td>").append("<input type=\"hidden\" id=\"tipodocumento\" name=\"tipodocumento\" value='" + parte.getTipoDocumento() + "'  > ").append(Util.nullCad(parte.getTipoDocumento())).append("</td>");       
                        sbLista.append("<td>").append("<input type=\"hidden\" id=\"documento\" name=\"documento\" value='" + parte.getNumeroDoc() + "'  > ").append(Util.nullCad(parte.getNumeroDoc())).append("</td>");    
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getSaldo())).append("</td>");  
                        sbLista.append("<td class=\"text-center\">").append("<input type=\"text\" id=\"debe\" name=\"debe\" style=\"text-align: right;\"   onchange=\"myCalculo(event)\"  class=\"decimal-2\" >").append("</td>"); 
                        sbLista.append("<td>").append(Util.nullCad(parte.getPoliza())).append("</td>"); 
                        sbLista.append("<td>").append(Util.nullCad(parte.getFechaProPago())).append("</td>"); 
                        sbLista.append("<td>").append("<input type=\"text\" readonly=\"true\" id=\"cuentabanco\" name=\"cuentabanco\" value='" + Util.nullCad(parte.getCuentaBancaria()) + "' size=\"20\"  >").append("</td>");                         
                        sbLista.append("<td>").append("<input type=\"text\" id=\"glosa\" name=\"glosa\" size=\"20\" maxlength=\"60\" >").append("</td>"); 
                        
                        sbLista.append("</tr>");
                    }
                    /*
                    sbLista.append("<tr>");
                    sbLista.append("<td>").append("").append("</td>");
                    sbLista.append("<td>").append("").append("</td>");
                    sbLista.append("<td>").append("").append("</td>");  
                    sbLista.append("<td>").append("").append("</td>");  
                    sbLista.append("<td>").append("").append("</td>"); 
                    sbLista.append("<td class=\"text-center\">").append("<input type=\"text\" readonly=\"true\" style=\"text-align: right;\" id=\"totaldebe\" class=\"decimal-2\" >").append("</td>"); 
                    sbLista.append("<td>").append("").append("</td>");  
                    sbLista.append("<td>").append("").append("</td>");                     
                    sbLista.append("<td>").append("").append("</td>"); 
                    sbLista.append("<td>").append("").append("</td>"); 
                    sbLista.append("</tr>");
                    */
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
    
    public ActionForward grabaPagoProveedorPoliza(ActionMapping mapping, ActionForm form,
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
            Long asiento = Util.nullLon(uform.getAsiento());
            String glosa = Util.nullCad(uform.getGlosa());
            //          ClientesDAO clientesDAO = new ClientesDAO();
            ActionErrors errors = new ActionErrors();
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
           
            double tipCamVta = Util.nullDou(global.getTipoCambioDolar());
            uform.setFlagMueOcuForm("oculta");
            //int nroRegsTmp = new SecuenciaDAO().contarRegistrosTempo(usuario.getRucEmpresa(),seqTmp1);
            Connection conexion = new ConectaSQL().connection();
            CallableStatement cs = null;
            String result="";     
            String tipoMsg="";
            String msg=""; 
            String documento="";
            String planilla = "";  
            String tipoOperacion="";
            JSONObject jsonObjResult;
            jsonObjResult = new PagoProveedoresDAO().GrabarPagoProveedorGenerico(seqTmp1,usuario.getRucEmpresa(),proveedor,formaPago,fechaContable,asiento,usuario.getCodigo(),glosa);                    

            try {
                        JSONArray jsonArrMsg = (JSONArray) jsonObjResult.get("mensaje");
                        JSONObject jsonObjMsg = (JSONObject) jsonArrMsg.get(0);
                        tipoMsg = Util.nullCad(jsonObjMsg.get("tipoMsg"));
                        msg = Util.nullCad(jsonObjMsg.get("msg"));
                        documento = Util.nullCad(jsonObjMsg.get("documento"));
                        planilla = Util.nullCad(jsonObjMsg.get("planilla"));
                        tipoOperacion = Util.nullCad(jsonObjMsg.get("tipoOperacion"));
                    } catch (Exception e) {
                        tipoMsg = "error";
                        msg = "" + e;
                    }
                    if ("exito".equals(tipoMsg)) { 
                        Long nroAsiento = Util.nullLon(documento);
                        Long nroPlanilla = Util.nullLon(planilla);
                        new PagoProveedoresDAO().actualiza_planilla( usuario.getRucEmpresa(), fechaContable,nroAsiento , usuario.getCodigo(), nroPlanilla,tipoOperacion,glosa,formaPago);
                        String mensaje="Proceso termino con exito, Se genero el asiento Nro :" + documento + " Nro PLANILLA nro <a href=\"ArchivoBancoAction.do?operacion=visualizaPlanilla&planilla=" + planilla+ "&anulado=N\" target=\"_blank\" >"+ planilla + "</a>" ;
                        errors.add("exito2", new ActionMessage(mensaje, false));                                                                            
                    } else {
                        errors.add("error", new ActionMessage(msg, false));
                    }                    
                
                if (!errors.isEmpty()) {
                    saveErrors(request, errors);
            }
            
            mappingFindForward = "msgGrabaPagoPoliza";   //msgConFacRepMostrador
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: conFacRepMostrador ====>");

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
            if (Util.sesionEstaActiva(request)) {
            new PagoProveedoresDAO().eliminarTMPDocumento(seqTmp1,fechaContable,usuario.getRucEmpresa());                
                String items[] = selected.split(",");
                boolean salir = false;
                String pGlosa="";
                String cuentaBanco="";
                int i =0;
                while ((i<=items.length -1)&&(!salir)){
                    String element[] = items[i].split("\\|");
                    double porPagar = Util.nullDou(element[2]);
                    pGlosa = element[3];
                    if (".".equals(pGlosa)) {
                        pGlosa="";
                    }
                    proveedor = element[4].trim();
                    cuentaBanco = element[5].trim();
                    double saldo = new PagoProveedoresDAO().saldoDocumento(usuario.getRucEmpresa(),formaPago,proveedor,element[0],element[1]);
                    if (porPagar>saldo)
                            salir= true;
                    else
                    {
                        new PagoProveedoresDAO().crearTMPDocumento(seqTmp1,fechaContable,usuario.getRucEmpresa(),formaPago,proveedor,element[0],element[1],porPagar,pGlosa,cuentaBanco);
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
    
}
