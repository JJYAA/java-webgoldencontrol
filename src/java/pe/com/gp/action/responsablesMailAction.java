/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

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
import pe.com.gp.dao.FechaDAO;
import pe.com.gp.dao.PedidoDAO;
import pe.com.gp.dao.ResponsableMailDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.entity.BeanResponsablesMail;
import pe.com.gp.entity.Cliente;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Tienda;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.form.ResponsableMailForm;
import pe.com.gp.util.Util;

/**
 *
 * @author Computer
 */
public class responsablesMailAction extends DispatchAction {
private static final Logger LOGGER = LogManager.getLogger();

    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            ResponsableMailForm uform = (ResponsableMailForm) form;
            List<BeanResponsablesMail> responsables = new ResponsableMailDAO().listaMailResponsables(usuario.getRucEmpresa());
            uform.setMail01(responsables.get(0).getMail01());
            uform.setMail02(responsables.get(1).getMail02());
            uform.setMail03(responsables.get(2).getMail03());
            uform.setMail04(responsables.get(3).getMail04());
            uform.setMail05(responsables.get(4).getMail05());
            if ("1".equals(responsables.get(0).getActivo1()))
                uform.setChk1(true); 
            else
                uform.setChk1(false); 
            if ("1".equals(responsables.get(1).getActivo2()))
                uform.setChk2(true); 
            else
                uform.setChk2(false); 
            if ("1".equals(responsables.get(2).getActivo3()))
                uform.setChk3(true); 
            else
                uform.setChk3(false); 
            if ("1".equals(responsables.get(3).getActivo4()))
                uform.setChk4(true); 
            else
                uform.setChk4(false); 
            if ("1".equals(responsables.get(4).getActivo5()))
                uform.setChk5(true); 
            else
                uform.setChk5(false);                         

            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }   
    
    public ActionForward grabar(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            Tienda tienda = (Tienda) session.getAttribute("Tienda");
            Global global = (Global) session.getAttribute("Global");
            String docEntry = Util.nullCad(request.getParameter("DocEntry"));
            ResponsableMailForm uform = (ResponsableMailForm) form;
            new ResponsableMailDAO().actualizaMail(usuario.getRucEmpresa(), 
                    uform.getMail01(), uform.getMail02(), uform.getMail03(), uform.getMail04(), uform.getMail05(), 
                    uform.isChk1()?"1":"0", uform.isChk2()?"1":"0", uform.isChk3()?"1":"0", uform.isChk4()?"1":"0", uform.isChk5()?"1":"0");
            mappingFindForward = "inicializa";
            
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }
    
}
