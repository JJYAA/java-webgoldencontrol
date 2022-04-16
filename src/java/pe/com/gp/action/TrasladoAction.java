/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import pe.com.gp.form.TrasladoForm;

/**
 *
 * @author Administrador
 */
public class TrasladoAction extends DispatchAction {
     private static final Logger LOGGER = LogManager.getLogger();
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        //if (Util.sesionEstaActiva(request)) {
        HttpSession session = request.getSession();
        //Usuario usuario = (Usuario) session.getAttribute("Usuario");
        //Tienda tienda = (Tienda) session.getAttribute("Tienda");
        //Global global = (Global) session.getAttribute("Global");
        //GenericoForm uform = (GenericoForm) form;
        String empresa = (String) session.getAttribute("Empresa");
        //session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
        TrasladoForm uform = (TrasladoForm) form;
        uform.setFlagMueOcuForm("muestra");
        String path = request.getServletContext().getRealPath("/");
        
        //muestaLista(request,uform);
        mappingFindForward = "inicializa";
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }      
}
