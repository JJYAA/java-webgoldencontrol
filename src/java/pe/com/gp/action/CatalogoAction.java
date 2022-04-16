/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;
import pe.com.gp.dao.CatalogoDAO;
import pe.com.gp.dao.ProductosDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.CatalogoForm;
import pe.com.gp.form.GenericoForm;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class CatalogoAction  extends DispatchAction {
    private static final String SEQ_TMP1_SESSION_KEY = "FacRepMosSeqTmp1_" + UUID.randomUUID().toString();
    private static final Logger LOGGER = LogManager.getLogger();

    private List<Parte> listaProductos = new ArrayList<Parte>();

    public List<Parte> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(List<Parte> listaProductos) {
        this.listaProductos = listaProductos;
    }
    
    
    
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
        session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
        CatalogoForm uform = (CatalogoForm) form;
        String path = request.getServletContext().getRealPath("/");
        String PathImg = path + Constantes.DIRECTORIO_IMAGENES ;        
       // List<Parte> listaPartesTempo = new CatalogoDAO().muestraListaProductos(empresa, "", "1",PathImg);
       // request.setAttribute("data", listaPartesTempo);         
        muestaLista(request,uform);
        mappingFindForward = "inicializa";
        LOGGER.info("<==== Fin Metodo: inicializa ====>");
        return mapping.findForward(mappingFindForward);
    }    
    
    public void muestaLista(HttpServletRequest request,CatalogoForm uform)  throws Exception {
        request.setAttribute("listaFamilia", new CatalogoDAO().listaFamilia("05",uform.getFamilia())); 
        request.setAttribute("listaClase", new CatalogoDAO().listaClase("05",uform.getClase())); 
        request.setAttribute("listaGrupo", new CatalogoDAO().listaGrupo("05",uform.getGrupo() )); 
        //request.setAttribute("listaClase", listaPartesTempo); 
        //request.setAttribute("listaGrupo", listaPartesTempo); 
    }

    
    public void muestraCatalogo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotal = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();        
        try {
            if (Util.sesionEstaActiva(request)) {
                
                String path = request.getServletContext().getRealPath("/");
                String PathImg = path + Constantes.DIRECTORIO_IMAGENES ;                        
                HttpSession session = request.getSession();
                String empresa = (String) session.getAttribute("Empresa");
                String familia = request.getParameter("familia");
                String clase = request.getParameter("clase");
                String grupo = request.getParameter("grupo");
                String filtro = request.getParameter("filtro");
                String codigoProducto = request.getParameter("codigoProducto");
                List<Parte> listaPartesTempo = new CatalogoDAO().muestraListaProductos(empresa, PathImg,familia,clase,grupo,filtro,codigoProducto);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) 
                {                                    
                    double maximo = listaPartesTempo.size() - 1;
                    double ln_filas = 0;
                    if (maximo < 3) {
                        ln_filas = 0;
                    } else 
                    {
                        double calculo = (maximo - 3) % 4;
                        if (calculo == 0) {
                            ln_filas = (maximo - 3) / 4;
                        }  else 
                        {
                            ln_filas = Math.ceil((maximo - 3) / 4);
                        }
                    }
                    StringBuilder sbLista = new StringBuilder();
                    int elemento = 0;
                    boolean terminar = false;
                    for (int fila = 0; fila <= ln_filas; fila++) 
                    {
                        sbLista.append("<div class=\"row\" >");
                        terminar = false;
                        int indice = -1;
                        while (!terminar) 
                        {
                            indice++;
                            sbLista.append("<div class=\"col-md-3 col-sm-4\">");
                             sbLista.append("<div class=\"thumbnail\" > ");
                            StringBuilder append = sbLista.append("<img alt=\"..\"  src=\"data:image/jpeg;charset=utf-8;base64,").append(listaPartesTempo.get(elemento).getFoto()).append("\" style=\"height:220px\">");
                              sbLista.append("<div class=\"caption\">");
                               sbLista.append("<center><h3>").append(listaPartesTempo.get(elemento).getCodigoProducto()).append("</h3></center>");
                               sbLista.append("<h4>Precio: <span class=\"red\"><strike>S/ 15.80</strike></span>").append("    ").append(listaPartesTempo.get(elemento).getPrecioItem()).append(" S/ </h4>");
                               sbLista.append("<p>");
                               sbLista.append(listaPartesTempo.get(elemento).getDescripcion());
                               sbLista.append("</p>");
                               sbLista.append("<p class=\"text-center\">");
                               //sbLista.append("<a href=\"#\" class=\"btn btn-primary\" onclick=\"AgregarItem('").append(listaPartesTempo.get(elemento).getCodigoProducto()).append("')\" role=\"button\"><i class=\"fas fa-cart-plus\"></i><span class=\"icono-izq\">Agregar al carrito</span></a>");
                               //sbLista.append("<a href=\"#\" class=\"btn btn-primary\" onclick=\"AgregarItem('").append(listaPartesTempo.get(elemento).getCodigoProducto()).append("','").append('a').append("') role=\"button\"><i class=\"fas fa-cart-plus\"></i><span class=\"icono-izq\">Agregar al carrito</span></a>");
                               sbLista.append("<a href=\"#\" class=\"btn btn-primary\" onclick=\"AgregarItem('").append(listaPartesTempo.get(elemento).getCodigoProducto()).append("','").append(listaPartesTempo.get(elemento).getPrecioItem().toString()).append("')\"  role=\"button\"><i class=\"fas fa-cart-plus\"></i><span class=\"icono-izq\">Agregar al carrito</span></a>");
                               sbLista.append("</p>");                                    
                              sbLista.append("</div>");
                             sbLista.append("</div>");
                            sbLista.append("</div>");
                            elemento++;
                            if ((indice == 3) || (elemento > maximo)) 
                            {
                               terminar = true;
                            }                                                
                        }
                         sbLista.append("</div>");
                    }
                    jsonObjMsg = new JSONObject();
                    jsonObjTabla.put("tabla", sbLista.toString()); 
                    jsonObjMsg.put("tipoMsg", "exito");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("objMensaje", jsonObjMsg);            
                    jsonObject.put("objTabla", jsonObjTabla);
                    sb.append(jsonObject.toString());
                    out = response.getWriter();
                    out.write(sb.toString());
                } else
                {
                    jsonObjMsg = new JSONObject();
                    jsonObjTabla.put("tabla", ""); 
                    jsonObjMsg.put("tipoMsg", "error");
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("objMensaje", jsonObjMsg);            
                    jsonObject.put("objTabla", jsonObjTabla);                    
                    sb.append(jsonObject.toString());
                    out = response.getWriter();
                    out.write(sb.toString());                    
                }
            }
        }catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }    
        
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }
    
    
    public void EliminarItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotal = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        //String[] indicador = {"", "", ""};
        try {
            if (Util.sesionEstaActiva(request)) {
                HttpSession session = request.getSession();
                ///long seqTmp1 = Util.nullLon(session.getAttribute("facRepMosSeqTmp1"));
                //long seqTmp2 = Util.nullLon(session.getAttribute("facRepMosSeqTmp2"));
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                String empresa = (String) session.getAttribute("Empresa");
                double Total=0;
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                String msgError = "";
                String producto="";
                long cantidad= 1;
                double precio=152.15;
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                new CatalogoDAO().EliminarItemCatalogo(seqTmp1,empresa,numeroParte);
                StringBuilder sbLista = new StringBuilder();
                String path = request.getServletContext().getRealPath("/");
                String PathImg = path + Constantes.DIRECTORIO_IMAGENES ;
                List<Parte> listaPartesTempo =  new CatalogoDAO().muestraListaItem(empresa,seqTmp1,PathImg);
                sbLista.append("<table id=\"tablaPartesTempo\" class=\"table table-striped\"  >");
                for (Parte parte : listaPartesTempo) 
                {
                            //parte.getFoto();
                    Total = Total + parte.getVVPSol();
                    sbLista.append("<tr>");
                    sbLista.append("<td>").append("<img alt=\"..\"  src=\"data:image/jpeg;charset=utf-8;base64,").append(parte.getFoto()).append("\" style=\"width:30;height:30px\">").append("</td>");
                    sbLista.append("<td>").append(parte.getCodigoProducto()).append("</td>");
                    sbLista.append("<td>").append(parte.getDescripcion()).append("</td>");
                    sbLista.append("<td>").append(parte.getVVPSol()).append("</td>");
                    sbLista.append("<td>").append("<input type=\"text\" id=\"cantidad\" name =\"cantidad\">").append("</td>");
                    sbLista.append("<td>").append("<input type=\"text\" id=\"descuento\" name =\"descuento\">").append("</td>");
                    sbLista.append("<td>").append("<a href=\"#\" onclick= EliminarItem('").append(parte.getCodigoProducto()).append("') class=\"btn btn-primary btn-xs\" role=\"button\"><i class=\"fas fa-cart-plus\"></i><span class=\"icono-izq\">Retirar</span></a>").append("</td>");
                    sbLista.append("</tr>");                    
                }   
                sbLista.append("</table>");
                jsonObjTabla.put("tabla", sbLista.toString()); 
                jsonObjTotal.put("total", String.valueOf(Total)); 
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
            jsonObject.put("objTotal", jsonObjTotal);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }            
    
            
    public void AgregarItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotal = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        //String[] indicador = {"", "", ""};
        try {
            if (Util.sesionEstaActiva(request)) {
                HttpSession session = request.getSession();
                ///long seqTmp1 = Util.nullLon(session.getAttribute("facRepMosSeqTmp1"));
                //long seqTmp2 = Util.nullLon(session.getAttribute("facRepMosSeqTmp2"));
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                String empresa = (String) session.getAttribute("Empresa");
                double Total=0;
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                String msgError = "";
                long cantidad= 1;
                
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                Double precio  = Util.nullDou(request.getParameter("precio"));
                new CatalogoDAO().AgregarItemCatalogo(seqTmp1,empresa,numeroParte,cantidad,precio);
                StringBuilder sbLista = new StringBuilder();
                String path = request.getServletContext().getRealPath("/");
                String PathImg = path + Constantes.DIRECTORIO_IMAGENES ;
                List<Parte> listaPartesTempo =  new CatalogoDAO().muestraListaItem(empresa,seqTmp1,PathImg);
                sbLista.append("<table id=\"tablaPartesTempo\" class=\"table table-striped\"  >");
                for (Parte parte : listaPartesTempo) 
                {
                            //parte.getFoto();
                    Total = Total + parte.getVVPSol();
                    sbLista.append("<tr>");
                    sbLista.append("<td>").append("<img alt=\"..\"  src=\"data:image/jpeg;charset=utf-8;base64,").append(parte.getFoto()).append("\" style=\"width:30;height:30px\">").append("</td>");
                    sbLista.append("<td>").append(parte.getCodigoProducto()).append("</td>");
                    sbLista.append("<td>").append(parte.getDescripcion()).append("</td>");
                    sbLista.append("<td>").append(parte.getVVPSol()).append("</td>");                    
                    sbLista.append("<td>").append("<input type=\"text\" id=\"textos\"  value=\"1\" class=\"textos\" >").append("</td>");
                    sbLista.append("<td>").append("<input type=\"text\" id=\"descuento\" name =\"descuento\">").append("</td>");                    
                    sbLista.append("<td>").append("<a href=\"#\" onclick= EliminarItem('").append(parte.getCodigoProducto()).append("') class=\"btn btn-primary btn-xs\" role=\"button\"><i class=\"fas fa-cart-plus\"></i><span class=\"icono-izq\">Retirar</span></a>").append("</td>");
                    sbLista.append("</tr>");                    
                }   
                sbLista.append("</table>");
                    jsonObjTabla.put("tabla", sbLista.toString()); 
                    jsonObjTotal.put("total", String.valueOf(Total)); 
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
            jsonObject.put("objTotal", jsonObjTotal);
            jsonObject.put("objTabla", jsonObjTabla);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
    }            
    
    
}
