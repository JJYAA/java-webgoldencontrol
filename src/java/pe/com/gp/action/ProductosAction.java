/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import javax.imageio.ImageIO;
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
import pe.com.gp.dao.CambioEstadoDAO;
import pe.com.gp.dao.CatalogoDAO;
import pe.com.gp.dao.ProductosDAO;
import pe.com.gp.dao.StockDAO;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.StockRepuestos;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.form.ProductosForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.Util;

/**
 *
 * @author Jpalomino
 */
public class ProductosAction extends DispatchAction {

    private static final Logger LOGGER = LogManager.getLogger();

    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            ProductosForm uform = (ProductosForm) form;
            muestaLista(request,uform);
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }
    public void muestaLista(HttpServletRequest request,ProductosForm uform)  throws Exception {
        request.setAttribute("listaFamilia", new CatalogoDAO().listaFamilia("05",uform.getFamilia())); 
        request.setAttribute("listaClase", new CatalogoDAO().listaClase("05",uform.getClase())); 
        request.setAttribute("listaGrupo", new CatalogoDAO().listaGrupo("05",uform.getGrupo() )); 
    }
    
    public void muestraStockProductos(
            ActionMapping mapping, 
            ActionForm form, 
            HttpServletRequest request, 
            HttpServletResponse response) throws Exception {
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
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                String chkvalor = Util.nullCad(request.getParameter("chkvalor"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Parte> listaPartesTempo = new ProductosDAO().muestraListaProductos(empresa, numeroParte,"3", chkvalor);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">Producto</th>");
                    sbLista.append("<th class=\"text-center\">Descripci&oacute;n</th>");
                    sbLista.append("<th class=\"text-center\">Acci&oacute;n</th>");
                    sbLista.append("<th class=\"text-center\">img</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String data = "";
                    for (Parte parte : listaPartesTempo) {

                        data = "data:image/jpeg;base64," + parte.getFoto();
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"details-control\"></td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigoProducto())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"VerProducto('" + parte.getCodigoProducto() + "')\" ><span class=\"glyphicon glyphicon-search\"></span></a>").append("</td>");
                        sbLista.append("<td>").append("<a href=\"#\" onclick=\"verImagen('").append(data).append("');\"><img src=\"").append(data).append("\" style=\"width:20px; height:20px\" ></a>").append("</td>");
                        
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

    public void VerProducto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        jsonObjMsg = new JSONObject();
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");
        JSONObject jsonObjParte = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Parte> listaPartesTempo = new ProductosDAO().muestraProductos(empresa, numeroParte,"4","");
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    jsonObjParte.put("producto", listaPartesTempo.get(0).getCodigoProducto());
                    jsonObjParte.put("descripcion", listaPartesTempo.get(0).getDescripcion());
                    jsonObjParte.put("vvpSoles", listaPartesTempo.get(0).getVVPSol());
                    jsonObjParte.put("vvpDolar", listaPartesTempo.get(0).getVVPDol());
                    jsonObjParte.put("Activo", listaPartesTempo.get(0).getAnulado());
                    jsonObjParte.put("masPrecios", listaPartesTempo.get(0).getMasPrecios());
                    jsonObjParte.put("cantidadCaja", listaPartesTempo.get(0).getCantidadCajas());
                    jsonObjParte.put("duas", listaPartesTempo.get(0).getDual());
                    jsonObjParte.put("familia", listaPartesTempo.get(0).getFamilia());
                    jsonObjParte.put("clase", listaPartesTempo.get(0).getClase());
                    jsonObjParte.put("grupo", listaPartesTempo.get(0).getGrupo());
                    
                    
                    
                    jsonObjParte.put("precio01", listaPartesTempo.get(0).getPrecio01());
                    jsonObjParte.put("precio02", listaPartesTempo.get(0).getPrecio02());
                    jsonObjParte.put("precio03", listaPartesTempo.get(0).getPrecio03());
                    jsonObjParte.put("precio04", listaPartesTempo.get(0).getPrecio04());
                    jsonObjParte.put("precio05", listaPartesTempo.get(0).getPrecio05());
                    
                    jsonObjParte.put("disponible", listaPartesTempo.get(0).getStockDisponible());
                    jsonObjParte.put("almacenes", listaPartesTempo.get(0).getStockAlmacenes() );
                    jsonObjParte.put("seguridad", listaPartesTempo.get(0).getStockSeguridad());
                    jsonObjParte.put("ultimoCosto", listaPartesTempo.get(0).getUltCosto());
                    jsonObjParte.put("costoPromedio", listaPartesTempo.get(0).getCostoProm());
                    jsonObjParte.put("total", listaPartesTempo.get(0).getStockTotal());
                    jsonObjParte.put("temporal", listaPartesTempo.get(0).getStockTemporal());
                    jsonObjParte.put("ultimoIngreso", listaPartesTempo.get(0).getFechaUltimoIngreso());
                    jsonObjParte.put("ultimaSalida", listaPartesTempo.get(0).getFechaUltimaSalida());                    
                    
                    
                    jsonObjParte.put("ctosRegs", 1);
                    jsonObjMsg.put("tipoMsg", "exito");

                } else {
                    jsonObjParte.put("ctosRegs", 0);
                    jsonObjMsg.put("tipoMsg", "error");
                }
            } else {
                jsonObjParte.put("ctosRegs", 0);
                jsonObjMsg.put("tipoMsg", "error");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objParte", jsonObjParte);
            jsonObject.put("objMensaje", jsonObjMsg);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("objParte", jsonObjParte);
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


//    public void grabarFotoProductoXXX(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
//        HttpSession session = request.getSession();
//        Global global = (Global) session.getAttribute("Global");
//        Usuario user = (Usuario) session.getAttribute("Usuario");
//        response.setContentType( "text/html; charset=iso-8859-1" );
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("cache-control", "no-cache");
//        StringBuilder sb = new StringBuilder();
//        PedidoOfertaRepForm uform = (PedidoOfertaRepForm) form;
//        PrintWriter out = response.getWriter();
//        String empresa = (String) session.getAttribute("Empresa");
//        String numeroParte = (String) uform.getNumeroparteAux();
//        String contentType =uform.getTheFile().getContentType();
//        String fileName =uform.getTheFile().getFileName();
//        int fileSize = uform.getTheFile().getFileSize();
//        byte[] fileData =uform.getTheFile().getFileData();        
//        try {
//            if (Util.sesionEstaActiva(request)) {
//                new ProductosDAO().Grabaimagenes(empresa,numeroParte, fileData);
//                String path = request.getServletContext().getRealPath("/");
//                String nombreImg = path + Constantes.DIRECTORIO_PRODUCTOS + "//" + "demo.jpg";
//                File file = new File(nombreImg);                
//                OutputStream os = new FileOutputStream(file);
//                os.write(fileData);
//                System.out.println("Write bytes to file.");
//                os.close(); 
//                out.println("exito");
//            }
//        } catch (Exception e) 
//        {
//            out.println(e.toString());
//        } finally {
//        }
//       
//        LOGGER.info("<==== Fin Metodo: agregarItem ====>");          
//    }          
    
    public void grabarFotoProducto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        ProductosForm uform = (ProductosForm) form;
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        jsonObjMsg = new JSONObject();
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");
        String numeroParte = uform.getNumeroparteAux();
        String contentType =uform.getTheFile().getContentType();
        String fileName =uform.getTheFile().getFileName();
        int fileSize = uform.getTheFile().getFileSize();
        byte[] fileData =uform.getTheFile().getFileData();          
        JSONObject jsonObjParte = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                
                new ProductosDAO().Grabaimagenes(empresa,numeroParte, fileData);
                String path = request.getServletContext().getRealPath("/");
                String nombreImg = path + Constantes.DIRECTORIO_PRODUCTOS + "//" + empresa + "-" + numeroParte + ".jpg";
                File file = new File(nombreImg);                
                OutputStream os = new FileOutputStream(file);
                os.write(fileData);
                jsonObjMsg.put("tipoMsg", "exito");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                JSONObject jsonObject = new JSONObject();                
                jsonObject.put("objMensaje", e.toString());
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

    public void GrabarProducto(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        HttpSession session = request.getSession();
        Global global = (Global) session.getAttribute("Global");
        Usuario user = (Usuario) session.getAttribute("Usuario");
         response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        ProductosForm uform = (ProductosForm) form;
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        jsonObjMsg = new JSONObject();
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        String empresa = (String) session.getAttribute("Empresa");
        String numeroParte = uform.getNumeroparteAux();
        //String contentType =uform.getTheFile().getContentType();
        //Double vvpDolar = Util.nullDou(request.getParameter("vvpDolar"));
        //Double vvpSoles = Util.nullDou(request.getParameter("vvpSoles"));
        //String descripcion = request.getParameter("descripcion");
        uform.setNumeroParte(numeroParte);        
        //String fileName =uform.getTheFile().getFileName();
        //int fileSize = uform.getTheFile().getFileSize();
        //byte[] fileData =uform.getTheFile().getFileData();          
        JSONObject jsonObjParte = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                
                new ProductosDAO().GrabaProducto(empresa,uform);
                //String path = request.getServletContext().getRealPath("/");
                //String nombreImg = path + Constantes.DIRECTORIO_PRODUCTOS + "//" + empresa + "-" + numeroParte + ".jpg";
                //File file = new File(nombreImg);                
                //OutputStream os = new FileOutputStream(file);
                //os.write(fileData);
                jsonObjMsg.put("tipoMsg", "exito");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            sb.append(jsonObject.toString());
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            try {
                JSONObject jsonObject = new JSONObject();                
                jsonObject.put("objMensaje", e.toString());
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
