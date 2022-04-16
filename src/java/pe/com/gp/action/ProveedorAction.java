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
import pe.com.gp.dao.ProveedorDAO;
import pe.com.gp.entity.Cliente;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.ClientesForm;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class ProveedorAction extends DispatchAction {
    private static final Logger LOGGER = LogManager.getLogger();
    
    public ActionForward inicializaCuentaEmpresa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            ClientesForm uform = (ClientesForm) form;
            List<Cliente> cuentaBancariaEmpresa = new ProveedorDAO().datosCuentasEmpresa();
            request.setAttribute("periodo", Util.obtenerAnioActual());
            for (Cliente cuentas : cuentaBancariaEmpresa){
                if ("0001".equals(cuentas.getBanco())){
                    if ("S".equals(cuentas.getMoneda())){
                        uform.setBcpsolTipo(cuentas.getTipoCuenta());
                        uform.setBcpSoles(cuentas.getCuentaBancaria());
                    }
                    if ("D".equals(cuentas.getMoneda())){
                        uform.setBcpdolTipo(cuentas.getTipoCuenta());
                        uform.setBcpDolar(cuentas.getCuentaBancaria());
                    }                    
                }
                
            }
            mappingFindForward = "inicializaCuentaEmpresa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }     
    
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            ClientesForm uform = (ClientesForm) form;
            uform.setProceso("01");
            request.setAttribute("periodo", Util.obtenerAnioActual());
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    } 
    
    public ActionForward inicializaCuenta(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            ClientesForm uform = (ClientesForm) form;
            uform.setProceso("02");
            request.setAttribute("periodo", Util.obtenerAnioActual());
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    } 
    
    
    public void muestraListaProveedores(
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
        JSONObject jsonObjMsg; 
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String empresa = (String) session.getAttribute("Empresa");
                String msgError = "";
                String codigo = request.getParameter("codigo");
                String proceso = request.getParameter("proceso");
                String periodo = request.getParameter("periodo");
                StringBuilder sbLista = new StringBuilder();
                List<Cliente> listaClientes = new ProveedorDAO().listaProveedores(usuario.getRucEmpresa(),periodo);
                if (listaClientes != null && !listaClientes.isEmpty()) {
                    sbLista.append("<table id=\"tablaProveedores\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"text-center\">RUC</th>");
                    sbLista.append("<th class=\"text-center\">Razon Social</th>");
                    sbLista.append("<th class=\"text-center\">Direccion</th>");
                    sbLista.append("<th class=\"text-center\">Telefono</th>");
                    sbLista.append("<th class=\"text-center\">Mail</th>");
                    sbLista.append("<th class=\"text-center\">Activos</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String ls_chk="";
                    for (Cliente cli : listaClientes) {

                        sbLista.append("<tr>");
                        if ("01".equals(proceso)) {
                            sbLista.append("<td>").append(Util.nullCad(cli.getC_c_ruc_proveedor())).append("</td>");
                        }
                        else  
                        {
                            sbLista.append("<td>").append("<a href=\"#\"  onclick=cuentasProveedor('" + Util.nullCad(cli.getC_c_ruc_proveedor()) + "')>" + Util.nullCad(cli.getC_c_ruc_proveedor()) + "<a/>").append("</td>");
                        }
                        sbLista.append("<td>").append(Util.nullCad(cli.getRazon_social())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(cli.getDireccion())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(cli.getTelefono1())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(cli.getEmail_01())).append("</td>");
                        ls_chk = "1".equals(cli.getAcceso())?"checked":"";
                        if ("01".equals(proceso)) {
                            sbLista.append("<td class=\"text-center\">").append("<input type=\"hidden\" id=\"ruc\" class=\"ruc\"  value='" + cli.getC_c_ruc_proveedor() + "' >  <input type=\"hidden\" id=\"cambio\"  >    <input type=\"checkbox\" id=\"chk\"  name=\"chk\" " + ls_chk + " class=\"chk\"  onclick=\"myCambio(event)\"  >").append("</td>");
                        }
                        else
                        {
                            sbLista.append("<td class=\"text-center\">").append("<input type=\"hidden\" id=\"ruc\" class=\"ruc\"  value='" + cli.getC_c_ruc_proveedor() + "' >  <input type=\"hidden\" id=\"cambio\"  >    <input type=\"checkbox\" id=\"chk\"  name=\"chk\" " + ls_chk + " class=\"chk\" disabled=\"true\"  onclick=\"myCambio(event)\"  >").append("</td>");
                        }
                        sbLista.append("</tr>");
                    }
                    sbLista.append("</tbody>");
                    sbLista.append("</table>");
                    jsonObjTabla.put("ctosRegs", listaClientes.size());
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
    
    
            
    public void actualizaAccesoProv(
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
        JSONObject jsonObjMsg; 
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String empresa = (String) session.getAttribute("Empresa");
                String msgError = "";
                String selected = request.getParameter("selected");
                String elem[] = selected.split("\\#");
                for(int i=0;i<elem.length;i++){
                    String items[] = elem[i].split(",");
                    new ProveedorDAO().actualizaAccesoWEB(usuario.getRucEmpresa(), items[0],items[1]);
                }
                 
   
                // Mensajes
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "exito");
                
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
    
    
    
public void actualizacuentas(
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
        JSONObject jsonObjMsg; 
        JSONObject jsonObjTabla = new JSONObject();
        String resultado ="";
        try {
             if (Util.sesionEstaActiva(request)) {
                String rucProveedor = Util.nullCad(request.getParameter("rucProvedor"));
                String bcpSoles = request.getParameter("bcpSoles");
                String bcpDolar = Util.nullCad(request.getParameter("bcpDolar"));
                String bbvaSoles = Util.nullCad(request.getParameter("bbvaSoles"));
                String bbvaDolar = Util.nullCad(request.getParameter("bbvaDolar"));
                String scotiabankICC = Util.nullCad(request.getParameter("scotiabankICC"));
                String interbankICC = Util.nullCad(request.getParameter("interbankICC"));
                
                String tipoCuentabcpSol = Util.nullCad(request.getParameter("tipobcpSoles"));
                String tipoCuentabcpDol = Util.nullCad(request.getParameter("tipobcpDolar"));
                String tipoCuentabbvaSol = Util.nullCad(request.getParameter("tipobbvaSoles"));
                String tipoCuentabbvaDol = Util.nullCad(request.getParameter("tipobbvaDolar"));
                
               resultado = new ProveedorDAO().actualizaCuentasProveedor(rucProveedor,bcpSoles,bcpDolar,bbvaSoles,bbvaDolar,scotiabankICC,interbankICC,
                       tipoCuentabcpSol,
                       tipoCuentabcpDol,
                       tipoCuentabbvaSol,
                       tipoCuentabbvaDol        
                       );
             } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            jsonObjMsg = new JSONObject();
            if ("".equals(resultado))
                jsonObjMsg.put("tipoMsg", "exito");   
            else
                jsonObjMsg.put("tipoMsg", "error");   
 
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


public void datosCuentas(
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
        JSONObject jsonObjMsg; 
        JSONObject jsonObjTabla = new JSONObject();
        String resultado ="";
        JSONObject jsonObjDatos = new JSONObject();
        try { 
             if (Util.sesionEstaActiva(request)) {
                String rucProveedor = Util.nullCad(request.getParameter("rucProvedor"));
               List<Cliente> cuentaProveedor = new ProveedorDAO().datosCuentasProveedor(rucProveedor);
               if (cuentaProveedor.size()==0){
                   jsonObjDatos.put("bcpSoles", "");
                   jsonObjDatos.put("bcpDolar", "");
                   jsonObjDatos.put("bbvaSoles", "");
                   jsonObjDatos.put("bbvaDolar", "");
                   jsonObjDatos.put("scotiabankICC", "");
                   jsonObjDatos.put("interbankICC", ""); 
                   jsonObjDatos.put("tipobcpSoles", "0");
                   jsonObjDatos.put("tipobcpDolar", "0");
                   jsonObjDatos.put("tipobbvaSoles", "0");
                   jsonObjDatos.put("tipobbvaDolar", "0");                   
               }
               else
               {
                   jsonObjDatos.put("bcpSoles", cuentaProveedor.get(0).getBcpSoles());
                   jsonObjDatos.put("bcpDolar", cuentaProveedor.get(0).getBcpDolar());
                   jsonObjDatos.put("bbvaSoles", cuentaProveedor.get(0).getBbvaSoles());
                   jsonObjDatos.put("bbvaDolar", cuentaProveedor.get(0).getBbvaDolar());
                   jsonObjDatos.put("scotiabankICC", cuentaProveedor.get(0).getScotiabankICC());
                   jsonObjDatos.put("interbankICC", cuentaProveedor.get(0).getInterbankICC());

                   jsonObjDatos.put("tipobcpSoles", cuentaProveedor.get(0).getBcpsolTipo());
                   jsonObjDatos.put("tipobcpDolar", cuentaProveedor.get(0).getBcpdolTipo());
                   jsonObjDatos.put("tipobbvaSoles", cuentaProveedor.get(0).getBbvasolTipo());
                   jsonObjDatos.put("tipobbvaDolar", cuentaProveedor.get(0).getBbvadolTipo());
                                      
               }
             } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            jsonObjMsg = new JSONObject();
            if ("".equals(resultado))
                jsonObjMsg.put("tipoMsg", "exito");   
            else
                jsonObjMsg.put("tipoMsg", "error");   
 
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objDatos", jsonObjDatos);
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



    public void grabarCuentaEmpresa(
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
        JSONObject jsonObjMsg; 
        JSONObject jsonObjTabla = new JSONObject();
        String resultado ="";
        try {
             if (Util.sesionEstaActiva(request)) {
                String bcpSoles = request.getParameter("bcpSoles");
                String tipoCuentabcpSol = Util.nullCad(request.getParameter("bcpsolTipo"));
                
                String bcpDolar = Util.nullCad(request.getParameter("bcpDolar"));
                String tipoCuentabcpDol = Util.nullCad(request.getParameter("bcpdolTipo"));
                

                
               resultado = new ProveedorDAO().actualizaCuentasEmpresa(bcpSoles,bcpDolar,tipoCuentabcpSol,tipoCuentabcpDol);
             } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            jsonObjMsg = new JSONObject();
            if ("".equals(resultado))
                jsonObjMsg.put("tipoMsg", "exito");   
            else
                jsonObjMsg.put("tipoMsg", "error");   
 
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
