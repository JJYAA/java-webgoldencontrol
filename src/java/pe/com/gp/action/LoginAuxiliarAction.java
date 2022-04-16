package pe.com.gp.action;

import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.json.JSONObject;
import pe.com.gp.dao.AutenticaDAO;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.LoginForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.dao.GlobalDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.dao.TiendaDAO;
import pe.com.gp.entity.ConfiguracionDimension;
import pe.com.gp.entity.DtoTienda;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Tienda;
import pe.com.gp.listas.ListaTiendas;
import pe.com.gp.util.Util;

public class LoginAuxiliarAction extends DispatchAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String TOMCAT_USUARIO_SESSION_KEY = "TomcatUsuarioLogin_" + UUID.randomUUID().toString();
    private static final String TOMCAT_TIENDA_SESSION_KEY = "TomcatTiendaLogin_" + UUID.randomUUID().toString();
    
    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward="Teclado";
            HttpSession session = request.getSession();                        
          

        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }
    
  
@Override
public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) 
    		throws Exception {
    ActionForward forward = mapping.findForward("Teclado");
    HttpSession session = request.getSession();
    ActionErrors errors = new ActionErrors();    
    LoginForm uForm = (LoginForm) form;
    AutenticaDAO autenticaDAO = new AutenticaDAO();
    String lsUsr = Util.nullCad(uForm.getUsuario());
    String lsPsw = Util.nullCad(uForm.getContrasena());
    if (uForm.getOperacion().equals("logout")){ 
         session.removeAttribute("Usuario");
         session.removeAttribute("Tienda");
         session.removeAttribute("Global");
         session.removeAttribute("ID");
         session.removeAttribute("Tienda");
         session.removeAttribute(TOMCAT_USUARIO_SESSION_KEY);
         session.removeAttribute(TOMCAT_TIENDA_SESSION_KEY);
         session.removeAttribute("listOpcMnuConAcc");
         session.invalidate();   
         uForm.setProceso("01");
         uForm.setOperacion("");
         cargaListas(request,uForm);
         forward = mapping.findForward("Teclado");           
    }
    else if ((uForm.getProceso()==null)&&uForm.getOperacion().equals("inicializa")){ 
        cargaListas(request,uForm);
        uForm.setProceso("01");
    }
    else if (lsUsr.length()==0&&lsPsw.length()==0) {
         uForm.setProceso("");
         cargaListas(request,uForm);
         forward = mapping.findForward("Teclado");           
    }
    else if (lsUsr.length() < 1) {
        errors = new ActionErrors();
        cargaListas(request,uForm);
        errors.add("error", new ActionMessage("error","Se debe ingresar el usuario"));
    }
    else if (lsPsw.length() < 1) {
        errors = new ActionErrors();
        cargaListas(request,uForm);
        errors.add("error", new ActionMessage("error","Se debe ingresar la contraseña"));
    }
    else
    {                    
                    Usuario user = autenticaDAO.autenticaWEB(uForm.getEmpresa(), uForm.getUsuario(), uForm.getContrasena());
                    if (user.getExiste() == false) 
                    {
                        errors = new ActionErrors();
                         cargaListas(request,uForm);  
                        errors.add("error", new ActionMessage("error", Constantes.MSG_USER_NO_REGISTRADO));
                    } else {
                        
                        GlobalDAO globalDAO = new GlobalDAO();
                        Global global = globalDAO.getDatosGlobales(uForm.getEmpresa());
                        if (!global.getExiste()) {
                            errors = new ActionErrors();
                             cargaListas(request,uForm);  
                            errors.add("error", new ActionMessage("error", Constantes.MSG_TIPO_CAMBIO));
                        } else {
                            
                            
                            
                            String path = session.getServletContext().getRealPath("/");
                            final String codUsuLog = Util.nullCad(user.getCodigo());
                            final String codTieLog = Util.nullCad(user.getCodTieLog());
                            new SecuenciaDAO().SecuenciaEmpresa(user.getRucEmpresa(),"00001");
                            long secuenciaTemporal = new SecuenciaDAO().getSecuenciaTemporal(user.getRucEmpresa(),"00001"); // NO CREAR SECUENCIAS AQUI, CREARLAS EN SU MODULO RESPECTIVO. GRACIAS
                            long secuenciaProforma = 200; //new SecuenciaDAO().getSecuenciaTemporal(); // NO CREAR SECUENCIAS AQUI, CREARLAS EN SU MODULO RESPECTIVO. GRACIAS                        
                            Tienda tienda = new TiendaDAO().obtenerDatosTiendaActividad(uForm.getEmpresa(),uForm.getTienda()); /// ESTO DEBERIA LEER DE SAP
                            user.setSecuencia(secuenciaTemporal);
                            
                            //user.setCodigoEmpleadoVenta(autenticaDAO.codigoEmpleadoVenta(uForm.getUsuario()));
                            user.setPath(path);

                            // ===================
                            // Variables de sesion
                            // ===================   
                            
                            session.setAttribute("Usuario", user);
                            session.setAttribute("Empresa", uForm.getEmpresa());
                            session.setAttribute("Tienda", tienda);
                            session.setAttribute("Global", global);
                            session.setAttribute("ID", session.getId());

                            // Variables SOLO para usar en el monitoreo del Tomcat
                            session.setAttribute(TOMCAT_TIENDA_SESSION_KEY, codTieLog);
                            session.setAttribute(TOMCAT_USUARIO_SESSION_KEY, user.getNombre());
                            
                            //uForm.setUsuario(Util.encriptar(uForm.getUsuario()));
                            //uForm.setContrasena(Util.encriptar(uForm.getContrasena()));
                            // ===================
                            // Menus y Accesos
                            // ===================  
                            session.setAttribute("listOpcMnuConAcc", new AutenticaDAO().opcMenusConAcceso(user.getRucEmpresa(), uForm.getUsuario(), Constantes.COD_SISGP));

                            // ==============
                            // Log de accesos
                            // ==============
                            // acabello: Lo estoy colocando en un hilo separado
                            // para que no interfiera en el tiempo de logueo.
                            final String ip = Util.obtenerIP(request);
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    String result = new AutenticaDAO().insertarLogAccesos(
                                            codTieLog,
                                            ip,
                                            codUsuLog,
                                            null,
                                            Constantes.COD_SISGP_WEB,
                                            null
                                    );
                                    if (result != null) {
                                        LOGGER.error("GP.ERROR: {}", result);
                                    }
                                }
                            }).start();

                            // Redireccionar a otra pagina que no sea el index
                            /*switch (Util.nullCad(uForm.getAuxiliar())) {
                                case "aprLiqOtEspecial": // Aprobacion de Liquidacion Especial de OT
                                    forward = mapping.findForward("aprLiqOtEspecial");
                                    break;
                                case "aprOrdSalOT": // Aprobacion de Aprobacion de OT para Orden de Salida
                                    forward = mapping.findForward("aprOrdSalOT");
                                    break;
                                default:
                                    forward = mapping.findForward("mnuMain");
                                    break;
                            }*/
                            forward = mapping.findForward("mnuMain");
                        }                    
                    }            
        
    } 
    if (!errors.isEmpty()) {
            saveErrors(request, errors);
    }     
                                  
    return forward;  
}




 public void cargaListas(HttpServletRequest request, LoginForm uform) throws Exception {
    List<ListaGenerica> listaEmpresas = new ListaTiendas().listaTiendasEmpresa("%");
    List<ListaGenerica> listaTiendas = new TiendaDAO().listaTiendasEmpresa2("",uform.getEmpresa());
    request.setAttribute("listaEmpresas", listaEmpresas);      
    request.setAttribute("listaTiendas", listaTiendas);      
 }

  
    
    
}
