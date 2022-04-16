package pe.com.gp.action;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
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
import pe.com.gp.dao.ClienteDAO;
import pe.com.gp.dao.FechaDAO;
import pe.com.gp.dao.ParteDAO;
import pe.com.gp.dao.PedidoDAO;
import pe.com.gp.dao.PedidoOfertaRepDAO;
import pe.com.gp.dao.SecuenciaDAO;
import pe.com.gp.dao.UbigeoDAO;
import pe.com.gp.entity.Cliente;
import pe.com.gp.entity.ConfiguracionDimension;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.ListaGenerica;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.Tienda;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.PedidoOfertaRepForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.SunatUtils;
import pe.com.gp.util.Util;

public class PedidoOfertaRepAction extends DispatchAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final String SEQ_TMP1_SESSION_KEY = "FacRepMosSeqTmp1_" + UUID.randomUUID().toString();
    private static final String SEQ_TMP2_SESSION_KEY = "FacRepMosSeqTmp2_" + UUID.randomUUID().toString();

    public ActionForward inicializa(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            PedidoOfertaRepForm uform = (PedidoOfertaRepForm) form;
            uform.setAuxFacTipDoc("03");
            uform.setClienteTipoDocumento("1");
            uform.setFlagMueOcuForm("muestra");
            session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));
            session.setAttribute(SEQ_TMP2_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));
            uform.setClienteDepartamento("15");
            uform.setClienteProvincia("01");
            uform.setClienteDistrito("01");             
            cargaListas(request, uform);
            uform.setAuxproceso("FACBOL");
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }

    public ActionForward inicializaCot(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            PedidoOfertaRepForm uform = (PedidoOfertaRepForm) form;
            uform.setAuxFacTipDoc("03");
            uform.setClienteTipoDocumento("1");
            uform.setFlagMueOcuForm("muestra");
            session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));
            session.setAttribute(SEQ_TMP2_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal2(empresa));
            uform.setClienteDepartamento("15");
            uform.setAuxproceso("COTIZACION");
            uform.setClienteProvincia("01");
            uform.setClienteDistrito("01");             
            cargaListas(request, uform);
              
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }    
    
    public ActionForward VisualizarOrdenVenta(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            Tienda tienda = (Tienda) session.getAttribute("Tienda");
            Global global = (Global) session.getAttribute("Global");
            String docEntry = Util.nullCad(request.getParameter("DocEntry"));
            PedidoOfertaRepForm uform = (PedidoOfertaRepForm) form;
            FechaDAO fechaDAO = new FechaDAO();
            String fechaFin = fechaDAO.obtenerFechaActual();
            Cliente cliente = new PedidoDAO().getDatosClienteOfertaVenta(docEntry);
            uform.setClienteDepartamento("");
            uform.setClienteDepartamento("");
            uform.setClienteProvincia("");
            uform.setMoneda(cliente.getMoneda());
            uform.setCanalVenta(cliente.getCanalVenta());
            uform.setDocumentoSAP(cliente.getDocumentoSAP());
            uform.setClienteTitulo(cliente.getTipo_persona());
            uform.setAuxOfertaVenta(cliente.getOfertaVenta());
            uform.setDocEntryAux(docEntry);
            if ("TNJ".equals(cliente.getTipo_persona())) {
                uform.setAuxFacTipDoc("FR");
            } else {
                uform.setAuxFacTipDoc("BR");
            }
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
            new SecuenciaDAO().deleteMotivoAnulacion(seqTmp2);
            uform.setClienteNumeroDocumento(cliente.getCodigo());
            uform.setClienteRazonSocial(cliente.getRazon_social());
            cargaListas(request, uform);
            uform.setClienteDireccion(cliente.getIndiceDireccion());
            uform.setFlagMueOcuForm("muestra");
            uform.setAuxproceso("ORDVENTAVISU");
            mappingFindForward = "recibirCotizacion";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }

    public ActionForward recibirCotizacion(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            Tienda tienda = (Tienda) session.getAttribute("Tienda");
            Global global = (Global) session.getAttribute("Global");
            String docEntry = Util.nullCad(request.getParameter("DocEntry"));
            PedidoOfertaRepForm uform = (PedidoOfertaRepForm) form;;
            Cliente cliente = new PedidoDAO().getDatosClienteOfertaVenta(docEntry);
            uform.setClienteDepartamento("");
            uform.setClienteDepartamento("");
            uform.setClienteProvincia("");
            uform.setMoneda(cliente.getMoneda());
            uform.setCanalVenta(cliente.getCanalVenta());
            uform.setDocumentoSAP(cliente.getDocumentoSAP());
            uform.setClienteTitulo(cliente.getTipo_persona());
            uform.setAuxOfertaVenta(cliente.getOfertaVenta());
            uform.setDocEntryAux(docEntry);
            if ("TNJ".equals(cliente.getTipo_persona())) {
                uform.setAuxFacTipDoc("FR");
            } else {
                uform.setAuxFacTipDoc("BR");
            }
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
            new SecuenciaDAO().deleteMotivoAnulacion(seqTmp2);
            uform.setClienteNumeroDocumento(cliente.getCodigo());
            uform.setClienteRazonSocial(cliente.getRazon_social());
            
            uform.setClienteDireccion(cliente.getIndiceDireccion());
            uform.setFlagMueOcuForm("muestra");
            uform.setAuxproceso("01");
            cargaListas(request, uform);
                     
            

            mappingFindForward = "recibirCotizacion";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }

    public void cargaListas(HttpServletRequest request, PedidoOfertaRepForm uform) throws Exception {

        request.setAttribute("listaDepartamento", new UbigeoDAO().obtenerDepartamento(uform.getClienteDepartamento() ));
        request.setAttribute("listaProvincia", new UbigeoDAO().obtenerProvincia(uform.getClienteDepartamento(),uform.getClienteProvincia() ));
        request.setAttribute("listaDistrito", new UbigeoDAO().obtenerDistrito(uform.getClienteDepartamento(),uform.getClienteProvincia(),uform.getClienteDistrito() ));

    }

//    public JSONArray tipDocPorTitPerJSON(String tituloPersona) {
//        LOGGER.info("<==== Inicio Metodo: titulosPersonaJSON (JSONObject) ====>");
//        JSONArray jArray = new JSONArray();
//        JSONObject jsonObj;
//        if (tituloPersona.equals("TPJ")) {
//            jsonObj = new JSONObject();
//            jsonObj.put("indice", "6");
//            jsonObj.put("descripcion", "Registro Unico de Contribuyentes");
//            jArray.put(jsonObj);
//        } else {
//            jsonObj = new JSONObject();
//            jsonObj.put("indice", "0");
//            jsonObj.put("descripcion", "Otros Tipos De Documentos");
//            jArray.put(jsonObj);
//            jsonObj = new JSONObject();
//            jsonObj.put("indice", "1");
//            jsonObj.put("descripcion", "Documento Nacional de Identidad (DNI)");
//            jArray.put(jsonObj);
//            jsonObj = new JSONObject();
//            jsonObj.put("indice", "4");
//            jsonObj.put("descripcion", "Carnet de Extranjeria");
//            jArray.put(jsonObj);
//        }
//        return jArray;
//    }

    public JSONArray ubigeoJSON(String tipo, String codDep, String codPro) {
        LOGGER.info("<==== Inicio Metodo: ubigeoJSON (JSONObject) ====>");
        JSONArray jArray = new JSONArray();
        try {
            List<ListaGenerica> list = new ArrayList<>();
            switch (tipo) {
                case "departamentos": {
                    list = new UbigeoDAO().listaDepartamentos();
                    break;
                }
                case "provincias": {
                    list = new UbigeoDAO().listaProvincias(codDep);
                    break;
                }
                case "distritos": {
                    list = new UbigeoDAO().listaDistritos(codDep, codPro);
                    break;
                }
            }
            for (ListaGenerica obj : list) {
                String indice = Util.nullCad(obj.getIndice());
                String descripcion = Util.nullCad(obj.getDescripcion());
                JSONObject jsonObj = new JSONObject();
                jsonObj.put("indice", indice);
                jsonObj.put("descripcion", descripcion);
                jArray.put(jsonObj);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        }
        LOGGER.info("<==== Fin Metodo: ubigeoJSON (JSONObject) ====>");
        return jArray;
    }

    public void ubigeoJSON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: ubigeoJSON ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        try {
            if (Util.sesionEstaActiva(request)) {
                String tipo = Util.nullCad(request.getParameter("auxTipo"));
                String codDep = Util.nullCad(request.getParameter("cboDepartamentos"));
                String codPro = Util.nullCad(request.getParameter("cboProvincias"));
                JSONObject jObject = new JSONObject();
                JSONArray jArray = ubigeoJSON(tipo, codDep, codPro);
                jObject.put(tipo, jArray);
                sb.append(jObject.toString());
            } else {
                sb.append("relogin");
            }
            out = response.getWriter();
            out.write(sb.toString());
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            Util.close(out);
        }
        LOGGER.info("<==== Fin Metodo: ubigeoJSON ====>");
    }

        
        
        
//    public void tipDocPorTitPerJSON(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: ubigeoJSON ====>");
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("cache-control", "no-cache");
//        StringBuilder sb = new StringBuilder();
//        PrintWriter out = null;
//        try {
//            if (Util.sesionEstaActiva(request)) {
//                String tituloPersona = Util.nullCad(request.getParameter("tituloPersona"));
//                JSONObject jObject = new JSONObject();
//                //JSONArray jArray = ubigeoJSON(tipo, codDep, codPro);
//                JSONArray jArray = tipDocPorTitPerJSON(tituloPersona);
//                jObject.put("documentos", jArray);
//                sb.append(jObject.toString());
//            } else {
//                sb.append("relogin");
//            }
//            out = response.getWriter();
//            out.write(sb.toString());
//        } catch (Exception e) {
//            LOGGER.error("GP.ERROR: {}", e);
//        } finally {
//            Util.close(out);
//        }
//        LOGGER.info("<==== Fin Metodo: ubigeoJSON ====>");
//    }

    public void muestraListaProductos(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        double impuesto = 0;
        try {
            if (Util.sesionEstaActiva(request)) {
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
                //session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
                //session.setAttribute(SEQ_TMP2_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));   
                String msgError = "";
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Parte> listaPartesTempo = new ParteDAO().muestraListaProductos(empresa, numeroParte);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaPartesProductos\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th>Item</th>");
                    sbLista.append("<th>Descripci&oacute;n</th>");
                    sbLista.append("<th>Precio</th>");
                    sbLista.append("<th class=\"text-center\">Acciones</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (Parte parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"details-control\"></td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getItem())).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getVVPSol())).append("</td>");
                        sbLista.append("<td class=\"text-center\"><button onclick=\"aceptarItem('").append(Util.nullCad(parte.getItem())).append("','").append(Util.nullCad(parte.getVVPSol())).append("');\" class=\"btn btn-sm btn-default\" type=\"button\">Seleccionar</button></td>");
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

//    public void muestraItemsPedidoOferta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
//        HttpSession session = request.getSession();
//        Global global = (Global) session.getAttribute("Global");
//        Usuario user = (Usuario) session.getAttribute("Usuario");
//        String empresa = (String) session.getAttribute("Empresa");
//        response.setContentType("application/json");
//        response.setCharacterEncoding("UTF-8");
//        response.setHeader("cache-control", "no-cache");
//        StringBuilder sb = new StringBuilder();
//        PrintWriter out = null;
//        JSONObject jsonObjMsg;
//        JSONObject jsonObjTotales = new JSONObject();
//        JSONObject jsonObjTabla = new JSONObject();
//        double impuesto = 0;
//        try {
//            if (Util.sesionEstaActiva(request)) {
//                session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
//                session.setAttribute(SEQ_TMP2_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
//                String msgError = "";
//                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
//                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
//                // Parametros
//                String docEntry = Util.nullCad(request.getParameter("docEntry"));
//                String moneda = Util.nullCad(request.getParameter("moneda"));
//
//                ParteDAO parteDAO = new ParteDAO();
//
//                // Eliminar parte del tempo
//                new SecuenciaDAO().eliminaSecuencia("PA", seqTmp2);
//                new SecuenciaDAO().eliminaSecuencia("PA", seqTmp1);
//                new RepOfertaVentasDAO().ListaProductos(docEntry, seqTmp2, user.getCodTieLog(), 3);
//
//                // Reprocesar calculos
//                String resultCalculos = parteDAO.procesaCalculos(empresa, seqTmp2);
//
//                Parte parteTotales = parteDAO.obtieneTotalesTempo("05", seqTmp1);
//                double totBrutoSol = Util.nullDou(parteTotales.getTotBrutoSol());
//                double totDsctoSol = Util.nullDou(parteTotales.getTotDsctoSol());
//                double totVtaSol = Util.nullDou(parteTotales.getTotVtaSol());
//                double totIgvSol = Util.nullDou(parteTotales.getTotIgvSol());
//                double totGralVtaSol = Util.nullDou(parteTotales.getTotGralVtaSol());
//                double totBrutoDol = Util.nullDou(parteTotales.getTotBrutoDol());
//                double totDsctoDol = Util.nullDou(parteTotales.getTotDsctoDol());
//                double totVtaDol = Util.nullDou(parteTotales.getTotVtaDol());
//                double totIgvDol = Util.nullDou(parteTotales.getTotIgvDol());
//                double totGralVtaDol = Util.nullDou(parteTotales.getTotGralVtaDol());
//                // Totales                                                               
//                jsonObjTotales.put("totBrutoSol", totBrutoSol);
//                jsonObjTotales.put("totDsctoSol", totDsctoSol);
//                jsonObjTotales.put("totVtaSol", totVtaSol);
//                jsonObjTotales.put("totIgvSol", totIgvSol);
//                jsonObjTotales.put("totGralVtaSol", totGralVtaSol);
//                jsonObjTotales.put("totBrutoDol", totBrutoDol);
//                jsonObjTotales.put("totDsctoDol", totDsctoDol);
//                jsonObjTotales.put("totVtaDol", totVtaDol);
//                jsonObjTotales.put("totIgvDol", totIgvDol);
//                jsonObjTotales.put("totGralVtaDol", totGralVtaDol);
//
//                // Lista los repuestos que estan en el Tempo
//                StringBuilder sbLista = new StringBuilder();
//                List<Parte> listaPartesTempo = parteDAO.listaPartesTempo("05", seqTmp2);
//                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
//                    sbLista.append("<table id=\"tablaPartesTempo\">");
//                    sbLista.append("<thead>");
//                    sbLista.append("<tr>");
//                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
//                    sbLista.append("<th>N&uacute;mero&nbsp;Parte</th>");
//                    sbLista.append("<th>Descripci&oacute;n</th>");
//                    sbLista.append("<th>Oferta Venta</th>");
//                    sbLista.append("<th>Cantidad</th>");
//                    sbLista.append("<th>Dscto.</th>");
//                    sbLista.append("<th>IGV</th>");
//                    sbLista.append("<th>VVP&nbsp;Unit</th>");
//                    sbLista.append("<th>VVPT.Dscto.</th>");
//                    sbLista.append("<th>VVP</th>");
//                    //  sbLista.append("<th>Ubicaci&oacute;n</th>");
//                    sbLista.append("<th>Ind.Imp</th>");
//                    sbLista.append("<th class=\"text-center\">Acciones</th>");
//                    sbLista.append("</tr>");
//                    sbLista.append("</thead>");
//                    sbLista.append("<tbody>");
//                    for (Parte parte : listaPartesTempo) {
//                        sbLista.append("<tr>");
//                        sbLista.append("<td class=\"details-control\"></td>"); // (para plugin DataTables)
//                        sbLista.append("<td>").append(Util.nullCad(parte.getCodigo())).append("</td>");
//                        sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
//                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidad_ov())).append("</td>");
//                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidad())).append("</td>");
//                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getDescuento())).append("</td>");
//                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getIgv())).append("</td>");
//                        switch (moneda) {
//                            case "1":
//                                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getVVPSol())).append("</td>");
//                                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotVtaSol())).append("</td>");
//                                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotGralVtaSol())).append("</td>");
//                                break;
//                            case "2":
//                                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getVVPDol())).append("</td>");
//                                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotVtaDol())).append("</td>");
//                                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotGralVtaDol())).append("</td>");
//                                break;
//                        }
//                        // sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getUbicacion())).append("</td>");
//
//                        sbLista.append("<td class=\"text-right\">");
//                        sbLista.append("<select id=\"igv\" name=\"igv\" class=\"form-control mini-select fs-11\" onchange=\"cambioImpuesto(this,'").append(parte.getCodigo()).append("',").append(parte.getItem()).append(")\">");
//                        if ("IGV".equals(parte.getIndicadorIGV())) {
//                            sbLista.append("<option value=\"IGV\" selected=\"selected\">IGV</option>");
//                            sbLista.append("<option value=\"EXO_IGV\">EXO IGV</option>");
//                        } else {
//                            sbLista.append("<option value=\"IGV\">IGV</option>");
//                            sbLista.append("<option value=\"EXO_IGV\" selected=\"selected\">EXO IGV</option>");
//                        }
//                        sbLista.append("</select>");
//                        sbLista.append("</td>");
//                        sbLista.append("<td class=\"text-center\"><button onclick=\"eliminarItem('").append(Util.nullCad(parte.getCodigo())).append("');\" class=\"btn btn-sm btn-default\" type=\"button\">Eliminar</button></td>");
//                        sbLista.append("</tr>");
//                    }
//                    sbLista.append("</tbody>");
//                    sbLista.append("</table>");
//                    jsonObjTabla.put("ctosRegs", listaPartesTempo.size());
//                } else {
//                    jsonObjTabla.put("ctosRegs", 0);
//                }
//                jsonObjTabla.put("tabla", sbLista.toString());
//
//                // Mensajes
//                jsonObjMsg = new JSONObject();
//                if (!"".equals(Util.nullCad(msgError))) {
//                    jsonObjMsg.put("tipoMsg", "error");
//                    jsonObjMsg.put("msgError", msgError);
//                }
//            } else {
//                jsonObjMsg = new JSONObject();
//                jsonObjMsg.put("tipoMsg", "relogin");
//            }
//            JSONObject jsonObject = new JSONObject();
//            jsonObject.put("objMensaje", jsonObjMsg);
//            jsonObject.put("objTotales", jsonObjTotales);
//            jsonObject.put("objTabla", jsonObjTabla);
//            sb.append(jsonObject.toString());
//            out = response.getWriter();
//            out.write(sb.toString());
//        } catch (Exception e) {
//            LOGGER.error("GP.ERROR: {}", e);
//            try {
//                sb = new StringBuilder();
//                jsonObjMsg = new JSONObject();
//                jsonObjMsg.put("tipoMsg", "error");
//                jsonObjMsg.put("msgError", "GP.ERROR: " + e);
//                JSONObject jsonObject = new JSONObject();
//                jsonObject.put("objMensaje", jsonObjMsg);
//                sb.append(jsonObject.toString());
//                out = response.getWriter();
//                out.write(sb.toString());
//            } catch (JSONException | IOException ex) {
//                LOGGER.error("GP.ERROR: {}", e);
//            }
//        } finally {
//            if (out != null) {
//                out.flush();
//                out.close();
//            }
//        }
//        LOGGER.info("<==== Fin Metodo: agregarItem ====>");
//
//    }

    public void buscaParteSAP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: buscaParte ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjParte = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                String Empresa = (String) session.getAttribute("Empresa");
                ConfiguracionDimension confRep = (ConfiguracionDimension) session.getAttribute("ConfigRepuestos");
                String codTieLog = Util.nullCad(usuario.getCodTieLog());
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
                String msgError = "";
                String msgInfo = "";
                boolean hayErrores = false;
                String descripcion = "";
                String disponible = "0";
                String cajas = "0";
                int stkAlm = 0;
                String total = "0";
                String itemRepuesto = "0";
                double dsctoGP = 0;
                ParteDAO parteDAO = new ParteDAO();

                // Parametros
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                String auxFacTipDoc = Util.nullCad(request.getParameter("auxFacTipDoc"));
                int cantidad = Util.nullNum(request.getParameter("cantidad"));
                String codCliente = Util.nullCad(request.getParameter("codCliente"));
                String Moneda = Util.nullCad(request.getParameter("moneda"));
                String auxProceso = Util.nullCad(request.getParameter("auxProceso"));
                String existeRep = "N";
                String uniCajas="0";
                String anuladoSN="N";
                if (!"".equals(codCliente)||"COTIZACION".equals(auxProceso)) 
                {
                    if (!"".equals(numeroParte)) {
                        if ("03".equals(auxFacTipDoc) || "01".equals(auxFacTipDoc)) {

                            // Busca parte en la Lista de Precios
                            Parte objParteLPR = parteDAO.buscaParteLPR(Empresa, numeroParte);
                            if (objParteLPR.getExiste() == true) {
                                String codigo = Util.nullCad(objParteLPR.getCodigo());
                                descripcion = Util.nullCad(objParteLPR.getDescripcion());
                                String anulado = Util.nullCad(objParteLPR.getAnulado());
                                anuladoSN = Util.nullCad(objParteLPR.getAnulado());
                                total = Util.nullCad(objParteLPR.getTotal());
                                disponible = Util.nullCad(objParteLPR.getDisponible());
                                cajas = Util.nullCad(objParteLPR.getCajas());
                                // Verifica stock disponible

                                if ("0".equals(anulado)) {
                                    msgError = "N\u00famero de Parte anulado";
                                    hayErrores = true;
                                } else {
                                    // Busca parte en Tempo
                                    Parte objParteTMP = parteDAO.buscaParteEnTempo(Empresa, codigo, seqTmp2);
                                    if (objParteTMP.getExiste()) {
                                        existeRep = "S";
                                        itemRepuesto = objParteTMP.getItem();
                                        msgError = "N\u00famero de Parte ya existe";
                                        hayErrores = true;
                                    } else {
                                        // Busca parte en el Stock
                                        //Parte objParteSRG = new RepuestosDAO().buscarParteSRG(codTieLog, codigo, "00");
                                        //pedPendiente = Util.nullCad(objParteSRG.getPedPendiente());
                                        //sepPendiente = Util.nullCad(objParteSRG.getSepPendiente());
                                        stkAlm = 100; //Util.nullNum(objParteSRG.getStkAlm());
                                        existeRep = "N";
                                    }
                                }

                            } else {
                                msgError = "N\u00famero de Parte no existe";
                                hayErrores = true;
                            }
                        } else {
                            msgError = "Tipo de Documento de Venta inv\u00e1lido";
                            hayErrores = true;
                        }
                    } else {
                        msgError = "N\u00famero de Parte no existe";
                        hayErrores = true;
                    }
                } else {
                    msgError = "Codigo de Cliente inv\u00e1lido";
                    hayErrores = true;
                }

                // Mensaje
                jsonObjMsg = new JSONObject();
                if (hayErrores == true) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("tipoMsg", "exito");
                    jsonObjMsg.put("msgInfo", msgInfo);
                }

                // Parte
                List<Parte> listaPartesTempo = parteDAO.listaPartesTempo(Empresa, seqTmp2);
                jsonObjParte.put("descripcion", descripcion);
                jsonObjParte.put("stockDis", disponible);
                jsonObjParte.put("stockTotal", total);
                jsonObjParte.put("cajas", cajas);
                jsonObjParte.put("dscto", dsctoGP);
                jsonObjParte.put("itemRepuesto", itemRepuesto);
                jsonObjParte.put("existeRep", existeRep);
                jsonObjParte.put("ctosRegs", Util.nullNum(listaPartesTempo.size()));
               

            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
                jsonObjMsg.put("msgError", "");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
            jsonObject.put("objParte", jsonObjParte);
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
        LOGGER.info("<==== Fin Metodo: buscaParte ====>");
    }

    public void eliminarItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        //String[] indicador = {"", "", ""};
        try {
            if (Util.sesionEstaActiva(request)) {
                HttpSession session = request.getSession();
                ///long seqTmp1 = Util.nullLon(session.getAttribute("facRepMosSeqTmp1"));
                //long seqTmp2 = Util.nullLon(session.getAttribute("facRepMosSeqTmp2"));
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                String empresa = (String) session.getAttribute("Empresa");
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
                String msgError = "";

                // Parametros
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                String moneda = Util.nullCad(request.getParameter("moneda"));
                ParteDAO parteDAO = new ParteDAO();

                // Eliminar parte del tempo
                String resultDelParteTmp = parteDAO.eliminarParteDelTempo(empresa, numeroParte, seqTmp2);
                //parteDAO.InsertaParteTempoNOTA(numeroParte, seqTmp2,nota);

                // Reprocesar calculos
                String resultCalculos = parteDAO.procesaCalculos(empresa, seqTmp2);

                if ((resultDelParteTmp == null && "".equals(resultDelParteTmp)) && (resultCalculos == null && "".equals(resultCalculos))) {
                    msgError = "Error: (" + resultDelParteTmp + "," + resultCalculos + ")";
                } else {
                    Parte parteTotales = parteDAO.obtieneTotalesTempo(empresa, seqTmp2);
                    
                    
                    double totBrutoSol = Util.nullDou(parteTotales.getTotBrutoSol());
                    double totDsctoSol = Util.nullDou(parteTotales.getTotDsctoSol());
                    double totVtaSol = Util.nullDou(parteTotales.getTotVtaSol());
                    double totIgvSol = Util.nullDou(parteTotales.getTotIgvSol());
                    double totGralVtaSol = Util.nullDou(parteTotales.getTotGralVtaSol());
                    double totBrutoDol = Util.nullDou(parteTotales.getTotBrutoDol());
                    double totDsctoDol = Util.nullDou(parteTotales.getTotDsctoDol());
                    double totVtaDol = Util.nullDou(parteTotales.getTotVtaDol());
                    double totIgvDol = Util.nullDou(parteTotales.getTotIgvDol());
                    double totGralVtaDol = Util.nullDou(parteTotales.getTotGralVtaDol());

                    // Totales                                                               
                    jsonObjTotales.put("totBrutoSol", totBrutoSol);
                    jsonObjTotales.put("totDsctoSol", totDsctoSol);
                    jsonObjTotales.put("totVtaSol", totVtaSol);
                    jsonObjTotales.put("totIgvSol", totIgvSol);
                    jsonObjTotales.put("totGralVtaSol", totGralVtaSol);
                    jsonObjTotales.put("totBrutoDol", totBrutoDol);
                    jsonObjTotales.put("totDsctoDol", totDsctoDol);
                    jsonObjTotales.put("totVtaDol", totVtaDol);
                    jsonObjTotales.put("totIgvDol", totIgvDol);
                    jsonObjTotales.put("totGralVtaDol", totGralVtaDol);

                    JSONObject jsonObjAux = new JSONObject();
                    jsonObjAux = cadenaTabla(moneda, request);
                    JSONObject jsonObjDoc = jsonObjAux;
                    jsonObjTabla.put("tabla", jsonObjDoc.getString("tabla"));
                    jsonObjTabla.put("ctosRegs", jsonObjDoc.getInt("ctosRegs"));
                }
                // Mensajes
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
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

    
   
    public void aceptarDescuentoItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: eliminarItem ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        //String[] indicador = {"", "", ""};
        try {
            if (Util.sesionEstaActiva(request)) {
                HttpSession session = request.getSession();
                ///long seqTmp1 = Util.nullLon(session.getAttribute("facRepMosSeqTmp1"));
                //long seqTmp2 = Util.nullLon(session.getAttribute("facRepMosSeqTmp2"));
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                String empresa = (String) session.getAttribute("Empresa");
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
                String msgError = "";

                // Parametros
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                Double descuento = Util.nullDou(request.getParameter("descuentoAux"));
                String moneda = Util.nullCad(request.getParameter("moneda"));
                ParteDAO parteDAO = new ParteDAO();

                // Eliminar parte del tempo
                String resultDelParteTmp = parteDAO.updateTempoDescuento(empresa, numeroParte, descuento,seqTmp2);
                //parteDAO.InsertaParteTempoNOTA(numeroParte, seqTmp2,nota);

                // Reprocesar calculos
                String resultCalculos = parteDAO.procesaCalculos(empresa, seqTmp2);

                if ((resultDelParteTmp == null && "".equals(resultDelParteTmp)) && (resultCalculos == null && "".equals(resultCalculos))) {
                    msgError = "Error: (" + resultDelParteTmp + "," + resultCalculos + ")";
                } else 
                {
                    Parte parteTotales = parteDAO.obtieneTotalesTempo(empresa, seqTmp2);
                    double totBrutoSol = Util.nullDou(parteTotales.getTotBrutoSol());
                    double totDsctoSol = Util.nullDou(parteTotales.getTotDsctoSol());
                    double totVtaSol = Util.nullDou(parteTotales.getTotVtaSol());
                    double totIgvSol = Util.nullDou(parteTotales.getTotIgvSol());
                    double totGralVtaSol = Util.nullDou(parteTotales.getTotGralVtaSol());
                    double totBrutoDol = Util.nullDou(parteTotales.getTotBrutoDol());
                    double totDsctoDol = Util.nullDou(parteTotales.getTotDsctoDol());
                    double totVtaDol = Util.nullDou(parteTotales.getTotVtaDol());
                    double totIgvDol = Util.nullDou(parteTotales.getTotIgvDol());
                    double totGralVtaDol = Util.nullDou(parteTotales.getTotGralVtaDol());

                    // Totales                                                               
                    jsonObjTotales.put("totBrutoSol", totBrutoSol);
                    jsonObjTotales.put("totDsctoSol", totDsctoSol);
                    jsonObjTotales.put("totVtaSol", totVtaSol);
                    jsonObjTotales.put("totIgvSol", totIgvSol);
                    jsonObjTotales.put("totGralVtaSol", totGralVtaSol);
                    jsonObjTotales.put("totBrutoDol", totBrutoDol);
                    jsonObjTotales.put("totDsctoDol", totDsctoDol);
                    jsonObjTotales.put("totVtaDol", totVtaDol);
                    jsonObjTotales.put("totIgvDol", totIgvDol);
                    jsonObjTotales.put("totGralVtaDol", totGralVtaDol);

                    JSONObject jsonObjAux = new JSONObject();
                    jsonObjAux = cadenaTabla(moneda, request);
                    JSONObject jsonObjDoc = jsonObjAux;
                    jsonObjTabla.put("tabla", jsonObjDoc.getString("tabla"));
                    jsonObjTabla.put("ctosRegs", jsonObjDoc.getInt("ctosRegs"));
                }
                // Mensajes
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
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
    /*pipili*/
    public JSONObject cadenaTabla(String moneda, HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        Usuario usuario = (Usuario) session.getAttribute("Usuario");
        Global global = (Global) session.getAttribute("Global");
        ConfiguracionDimension confRep = (ConfiguracionDimension) session.getAttribute("ConfigRepuestos");
        String empresa = (String) session.getAttribute("Empresa");
        StringBuilder sbLista = new StringBuilder();
        StringBuilder sbRegistros = new StringBuilder();
        long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
        JSONObject jsonObject = new JSONObject();
        List<Parte> listaPartesTempo = new ParteDAO().listaPartesTempo(empresa, seqTmp2);
        if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
            sbLista.append("<table id=\"tablaPartesTempo\">");
            sbLista.append("<thead>");
            sbLista.append("<tr>");
            sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
            sbLista.append("<th>N&uacute;mero&nbsp;Parte</th>");
            sbLista.append("<th>Descripci&oacute;n</th>");
            sbLista.append("<th>Cantidad</th>");
            sbLista.append("<th>V.V.P</th>");
            sbLista.append("<th>Dscto.</th>");
            sbLista.append("<th>Tot.Dscto</th>");
            sbLista.append("<th>T.Publico</th>");
            sbLista.append("<th class=\"text-center\">Acciones</th>");
            sbLista.append("<th class=\"text-center\">Acciones</th>");
            sbLista.append("</tr>");
            sbLista.append("</thead>");
            sbLista.append("<tbody>");
            for (Parte parte : listaPartesTempo) {
                sbLista.append("<tr>");
                sbLista.append("<td class=\"details-control\"></td>"); // (para plugin DataTables)
                sbLista.append("<td>").append(Util.nullCad(parte.getCodigo())).append("</td>");
                sbLista.append("<td>").append(Util.nullCad(parte.getDescripcion())).append("</td>");
                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCantidad())).append("</td>");
                switch (moneda) {
                    case "1":
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getVVPSol())).append("</td>");
                        break;
                    case "2":
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getVVPDol())).append("</td>");
                        break;
                }
                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getDescuento())).append("</td>");
                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getDescuentoCantidad())).append("</td>");              
                sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getTotalPublicoSol())).append("</td>");              
                sbLista.append("<td class=\"text-center\"><button onclick=\"eliminarItem('").append(Util.nullCad(parte.getCodigo())).append("');\" class=\"btn btn-sm btn-default\" type=\"button\">Eliminar</button></td>");
                sbLista.append("<td class=\"text-center\"><button onclick=\"editarItem('").append(Util.nullCad(parte.getCodigo())).append("','").append(parte.getDescripcion()).append("','").append(parte.getVVPSol()).append("');\" class=\"btn btn-sm btn-default\" type=\"button\">Editar</button></td>");
                sbLista.append("</tr>");
            }
            sbLista.append("</tbody>");
            sbLista.append("</table>");
            jsonObject.put("ctosRegs", listaPartesTempo.size());
            //jsonObjTabla.put("ctosRegs", listaPartesTempo.size());
        } else {
            jsonObject.put("ctosRegs", 0);
        }
        jsonObject.put("tabla", sbLista.toString());

        return jsonObject;
    }

    public void agregarItem(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: agregarItem ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = null;
        JSONObject jsonObjMsg;
        JSONObject jsonObjTotales = new JSONObject();
        JSONObject jsonObjTabla = new JSONObject();
        try {
            if (Util.sesionEstaActiva(request)) {
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                Global global = (Global) session.getAttribute("Global");
                ConfiguracionDimension confRep = (ConfiguracionDimension) session.getAttribute("ConfigRepuestos");
                String empresa = (String) session.getAttribute("Empresa");
                String codTieLog = Util.nullCad(usuario.getCodTieLog());
                //double dsctoUsrLog = Util.nullDou(usuario.getDsctoTrabajador());
                String gerUsrLog = Util.nullCad(usuario.getGerencia());
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
                double tipCamVta = Util.nullDou(global.getTipoCambioDolar());
                String msgError = "";
                String msgLineaCredito = "";
                String msgInfo = "";

                // Parametros
                String numeroParte = Util.nullCad(request.getParameter("numeroParte"));
                int cantidad = Util.nullNum(request.getParameter("cantidad"));
                double descuento = Util.nullDou(request.getParameter("descuento"));
                String auxFacTipDoc = Util.nullCad(request.getParameter("auxFacTipDoc"));
                String codCliente = Util.nullCad(request.getParameter("codCliente"));
                String moneda = Util.nullCad(request.getParameter("moneda"));
                String formaPago = Util.nullCad(request.getParameter("formaPago"));
                String codigoCanal = Util.nullCad(request.getParameter("canalVenta"));
                String proceso = Util.nullCad(request.getParameter("proceso"));
                String existeRep = Util.nullCad(request.getParameter("existeRep"));
                String itemProducto = Util.nullCad(request.getParameter("itemProducto"));
                int numItemAux = Util.nullNum(request.getParameter("itemRepuesto"));
                Double precio = Util.nullDou(request.getParameter("precio"));
                String auxProceso = Util.nullCad(request.getParameter("auxProceso"));
                String indicadorIGV = "IGV";
                if (!"".equals(codCliente)||"COTIZACION".equals(auxProceso))  {
                    if (!"".equals(numeroParte)) {
                        if ("01".equals(auxFacTipDoc) || "03".equals(auxFacTipDoc)) {
                            if (descuento >= 0 && descuento < 100 && cantidad > 0) {
                                //Cliente cliente = new RepOfertaVentasDAO().getDatosCliente(codCliente);
                                ParteDAO parteDAO = new ParteDAO();
                                // Busca parte en la Lista de Precios
                                Parte objParteLPR = parteDAO.buscaParteLPR(empresa, numeroParte);
                                if (objParteLPR.getExiste() == true) {
                                    String codigo = Util.nullCad(objParteLPR.getCodigo());
                                    String descripcion = Util.nullCad(objParteLPR.getDescripcion());
                                    double vvpSol = precio; //il.nullDou(objParteLPR.getVVDSol());
                                    double vvpDol = Util.redondear(Util.nullDou(objParteLPR.getVVPDol()), 2);
                                    double vvdDol = Util.nullDou(objParteLPR.getVVDDol());
                                    double igv = Util.nullDou(objParteLPR.getIgv());
                                    String anulado = "N";
                                    int stkDis = 0;

                                    msgError = "";

                                    if ("".equals(Util.nullCad(msgError))) {
                                        String ubicacion = "";
                                        String resultInsParteTmp = null;
                                        resultInsParteTmp = new PedidoOfertaRepDAO().insertaParteEnTempo(empresa, codigo, seqTmp2, cantidad, descuento, vvpSol, vvpDol,descripcion,itemProducto);
                                        String resultCalculos = parteDAO.procesaCalculos(empresa, seqTmp2);
                                        Parte parteTotales = parteDAO.obtieneTotalesTempo(empresa, seqTmp2);
                                        double totBrutoSol = Util.nullDou(parteTotales.getTotBrutoSol());
                                        double totDsctoSol = Util.nullDou(parteTotales.getTotDsctoSol());
                                        double totVtaSol = Util.nullDou(parteTotales.getTotVtaSol());
                                        double totIgvSol = Util.nullDou(parteTotales.getTotIgvSol());
                                        double totGralVtaSol = Util.nullDou(parteTotales.getTotGralVtaSol());
                                        double totBrutoDol = Util.nullDou(parteTotales.getTotBrutoDol());
                                        double totDsctoDol = Util.nullDou(parteTotales.getTotDsctoDol());
                                        double totVtaDol = Util.nullDou(parteTotales.getTotVtaDol());
                                        double totIgvDol = Util.nullDou(parteTotales.getTotIgvDol());
                                        double totGralVtaDol = Util.nullDou(parteTotales.getTotGralVtaDol());

                                        // Totales                                                               
                                        jsonObjTotales.put("totBrutoSol", totBrutoSol);
                                        jsonObjTotales.put("totDsctoSol", totDsctoSol);
                                        jsonObjTotales.put("totVtaSol", totVtaSol);
                                        jsonObjTotales.put("totIgvSol", totIgvSol);
                                        jsonObjTotales.put("totGralVtaSol", totGralVtaSol);
                                        jsonObjTotales.put("totBrutoDol", totBrutoDol);
                                        jsonObjTotales.put("totDsctoDol", totDsctoDol);
                                        jsonObjTotales.put("totVtaDol", totVtaDol);
                                        jsonObjTotales.put("totIgvDol", totIgvDol);
                                        jsonObjTotales.put("totGralVtaDol", totGralVtaDol);
                                        JSONObject jsonObjAux = new JSONObject();
                                        jsonObjAux = cadenaTabla(moneda, request);
                                        JSONObject jsonObjDoc = jsonObjAux;
                                        jsonObjTabla.put("tabla", jsonObjDoc.getString("tabla"));
                                        jsonObjTabla.put("ctosRegs", jsonObjDoc.getInt("ctosRegs"));
                                    }
                                } else {
                                    msgError = "N\u00famero de Parte no existe";
                                }
                            }
                        } else {
                            msgError = "Tipo de Documento de Venta inv\u00e1lido";
                        }
                    } else {
                        msgError = "N\u00famero de Parte no existe";
                    }
                } else {
                    msgError = "Codigo de Cliente inv\u00e1lido";
                }

                // Mensajes
                jsonObjMsg = new JSONObject();
                if (!"".equals(Util.nullCad(msgError))) {
                    jsonObjMsg.put("tipoMsg", "error");
                    jsonObjMsg.put("msgError", msgError);
                } else {
                    jsonObjMsg.put("msgInfo", msgInfo);
                    jsonObjMsg.put("msgLineaCredito", msgLineaCredito);
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

    // Confirmar
    public ActionForward conFacRepMostrador(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LOGGER.info("<==== Inicio Metodo: conFacRepMostrador ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
           // request.setAttribute("listaMotivosAnulacion", new RepOfertaVentasDAO().listaMotivosAnulacion());
            HttpSession session = request.getSession();
            Usuario usuario = (Usuario) session.getAttribute("Usuario");
            Tienda tienda = (Tienda) session.getAttribute("Tienda");
            Global global = (Global) session.getAttribute("Global");
            String empresa = (String) session.getAttribute("Empresa");
            String codTieLog = Util.nullCad(usuario.getCodTieLog());
            String codUsrLog = Util.nullCad(usuario.getCodigo());
            String fechaSistema = Util.nullCad(global.getFechaSis());
            String facEleTieLog = Util.nullCad(tienda.getEnvioSunat());
            String codEmpTieLog = Util.nullCad(tienda.getCodGen());
            int vtaRSTieLog = Util.nullNum(Util.nullCad(tienda.getVentaRS()));
            PedidoOfertaRepForm uform = (PedidoOfertaRepForm) form;
            PedidoOfertaRepDAO facRepMostradorDAO = new PedidoOfertaRepDAO();
            //          ClientesDAO clientesDAO = new ClientesDAO();
            ActionErrors errors = new ActionErrors();
            long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
            long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY));
            double tipCamVta = Util.nullDou(global.getTipoCambioDolar());
            uform.setFlagMueOcuForm("oculta");
            int nroRegsTmp = new SecuenciaDAO().contarRegistrosTempo(empresa,seqTmp2);
            String cliCodigo = Util.nullCad(uform.getClienteNumeroDocumento());
            if (!"".equals(cliCodigo) && nroRegsTmp > 0) 
            {
                // Grabar Cliente
                Cliente objCliAux = new Cliente();
                objCliAux.setEmpresa(empresa);
                objCliAux.setCodigo(uform.getClienteNumeroDocumento());
                objCliAux.setRazon_social(uform.getClienteRazonSocial());
                objCliAux.setDireccion(uform.getClienteDistrito());
                objCliAux.setPaterno(uform.getClienteApellidoPaterno());
                objCliAux.setMaterno(uform.getClienteApellidoMaterno());
                objCliAux.setPrimer_nombre(uform.getClientePrimerNombre());
                objCliAux.setSegundo_nombre(uform.getClienteSegundoNombre());
                objCliAux.setMoneda(uform.getMoneda());
                objCliAux.setTelefono1(uform.getClienteTelefono1());
                objCliAux.setTelefono2(uform.getClienteTelefono2());
                objCliAux.setCelular1(uform.getClienteCelular());
                objCliAux.setFormaPago(uform.getFormaPago());
                objCliAux.setTipo_documento(uform.getClienteTipoDocumento());
                objCliAux.setTipo_persona(uform.getClienteTitulo());
                objCliAux.setUbigeo( uform.getClienteDepartamento() + uform.getClienteProvincia()  + uform.getClienteDistrito() );

                String result = new ClienteDAO().actualizaCliente(objCliAux);
                if ("".equals(result)){
                    JSONObject jsonObjResult;
                    Parte parteTotales = new ParteDAO().obtieneTotalesTempo(empresa, seqTmp2);
                    if ("COTIZACION".equals(uform.getAuxproceso()))
                        jsonObjResult = new PedidoOfertaRepDAO().GrabarFactura(uform.getAuxFacTipDoc(), usuario.getTienda() ,usuario.getCodigo(),objCliAux, seqTmp2, parteTotales,"1");
                    else
                        jsonObjResult = new PedidoOfertaRepDAO().GrabarFactura(uform.getAuxFacTipDoc(), usuario.getTienda() ,usuario.getCodigo(),objCliAux, seqTmp2, parteTotales,"2");
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
                        if ("COTIZACION".equals(uform.getAuxproceso())){
                            String tipoDocumento =  "PEDIDO DE VENTA" ;
                            String cadenaOfertVenta =  "  <a href=\"PedidoOfertaRepAction.do?operacion=visualizaPedido&empresa=" + empresa +"&actividad=00002&propuesta=" + msg + "\" target=\"_blank\" title=\"Imprimir\">Imprimir</a> )" ;
                            errors.add("exito2", new ActionMessage("Transacci&oacute;n terminada. Se gener\u00f3 la(s) <strong> " + tipoDocumento + " Nro. " + msg + "    " + Util.quitarUltimoCaracter(Util.nullCad(cadenaOfertVenta)) + "</strong>", false));                                                                                                        
                        } else
                        {                            
                            String tipoDocumento =  "03".equals(uform.getAuxFacTipDoc())?"BOLETA":"FACTURA" ;
                            String cadenaOfertVenta =  "  <a href=\"PedidoOfertaRepAction.do?operacion=visualizaDocumento&empresa=" + empresa +"&actividad=00002&tipoDocumento=" + uform.getAuxFacTipDoc() + "&documento=" + msg + "\" target=\"_blank\" title=\"Imprimir\">Imprimir</a> )" ;
                            errors.add("exito2", new ActionMessage("Transacci&oacute;n terminada. Se gener\u00f3 la(s) <strong> " + tipoDocumento + " Nro. " + msg + "    " + Util.quitarUltimoCaracter(Util.nullCad(cadenaOfertVenta)) + "</strong>", false));                                                                            
                        }
                        
                    } else {
                        errors.add("error", new ActionMessage(msg, false));
                    }                    
                }
                if (!errors.isEmpty()) {
                    saveErrors(request, errors);
                }
            }
            mappingFindForward = "msgConFacRepMostrador";   //msgConFacRepMostrador
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: conFacRepMostrador ====>");

        return mapping.findForward(mappingFindForward);
    }

    
public void visualizaPedido(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = null;
        ServletOutputStream out = null;
        Connection cn = new ConectaSQL().connection();
        //RequerimientoAdmDAO requerimientoAdmDAO = new RequerimientoAdmDAO();
        try {
            String path = request.getServletContext().getRealPath("/");
            //FacRepMostradorForm uform = (FacRepMostradorForm) form;
            String ls_empresa = request.getParameter("empresa"); 
            String ls_actividad = request.getParameter("actividad");
            String ls_propuesta = request.getParameter("propuesta"); 
            Long ll_propuesta = Util.nullLon(ls_propuesta);
            // uform.getTiendaAux();g 
           // String tipoPdf = request.getParameter("tipoJasper");


            // =============================
            // Setear parametros del reporte
            // =============================
            Map params = new HashMap();
            params.put("P_EMPRESA", ls_empresa);
            params.put("P_ACTIVIDAD", ls_actividad);
            params.put("P_PROPUESTA", ll_propuesta);
          /*  params.put("P_DOCUMENTO", ls_documento);
            params.put("P_BASE_DIR", path + Constantes.DIRECTORIO_IMAGENES + "/");*/
            //params.put("SUBREPORT_DIR", path + Constantes.DIRECTORIO_IREPORT + "/");

            // ======
            // Jasper
            // ====== 
            cn = new ConectaSQL().connection();
            String fileJasper = "propVenta.jasper";
            String jasper = path + Constantes.DIRECTORIO_IREPORT + "//" + fileJasper;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, params, cn);
            String filename;

            String time = new SimpleDateFormat("HHmmssSSS").format(new java.util.Date());
            baos = new ByteArrayOutputStream();
            response.setContentType("application/pdf");
            filename =  ls_propuesta + ".pdf";
            response.setHeader("content-disposition", "inline; filename=\"" + filename + "\"");
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            out = response.getOutputStream();
            baos.writeTo(out);

        } catch (Exception e) {

            throw e;
        } finally {
            Util.close(cn);
            if (baos != null) {
                try {
                    baos.close();
                } catch (final Exception e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final Exception e) {
                }
            }
        }
    }    


        
public void visualizaDocumento(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = null;
        ServletOutputStream out = null;
        Connection cn = new ConectaSQL().connection();
        //RequerimientoAdmDAO requerimientoAdmDAO = new RequerimientoAdmDAO();
        try {
            String path = request.getServletContext().getRealPath("/");
            //FacRepMostradorForm uform = (FacRepMostradorForm) form;
            String ls_empresa = request.getParameter("empresa"); 
            String ls_actividad = request.getParameter("actividad");
            String ls_documento = request.getParameter("documento"); 
            String ls_tipodocumento = request.getParameter("tipoDocumento"); 
            // uform.getTiendaAux();g 
           // String tipoPdf = request.getParameter("tipoJasper");


            // =============================
            // Setear parametros del reporte
            // =============================
            Map params = new HashMap();
            params.put("P_EMPRESA", ls_empresa);
            params.put("P_ACTIVIDAD", ls_actividad);
            params.put("P_TIPODOCUMENTO", ls_tipodocumento);
            params.put("P_DOCUMENTO", ls_documento);
            params.put("P_BASE_DIR", path + Constantes.DIRECTORIO_IMAGENES + "/");
            //params.put("SUBREPORT_DIR", path + Constantes.DIRECTORIO_IREPORT + "/");

            // ======
            // Jasper
            // ====== 
            cn = new ConectaSQL().connection();
            String fileJasper = "documento.jasper";
            String jasper = path + Constantes.DIRECTORIO_IREPORT + "//" + fileJasper;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, params, cn);
            String filename;

            String time = new SimpleDateFormat("HHmmssSSS").format(new java.util.Date());
            baos = new ByteArrayOutputStream();
            response.setContentType("application/pdf");
            filename = ls_tipodocumento + "_" + ls_documento + ".pdf";
            response.setHeader("content-disposition", "inline; filename=\"" + filename + "\"");
            JasperExportManager.exportReportToPdfStream(jasperPrint, baos);
            response.setContentLength(baos.size());
            out = response.getOutputStream();
            baos.writeTo(out);

        } catch (Exception e) {

            throw e;
        } finally {
            Util.close(cn);
            if (baos != null) {
                try {
                    baos.close();
                } catch (final Exception e) {
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (final Exception e) {
                }
            }
        }
    }    
     
    
    // Consumir Rest Json
    public void obtenerDatosClienteSAP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: obtenerDatosCliente ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = response.getWriter();
        try {
            if (Util.sesionEstaActiva(request)) {
                JSONArray jArray = new JSONArray();
                HttpSession session = request.getSession();
                Usuario usuario = (Usuario) session.getAttribute("Usuario");
                String codTieLog = Util.nullCad(usuario.getCodTieLog());
                String empresa = (String) session.getAttribute("Empresa");

                String codCli = Util.nullCad(request.getParameter("codigoCliente"));
                
                String cliTipoCliente = Util.nullCad(request.getParameter("clienteTitulo"));
                String cliTipoDocumento = Util.nullCad(request.getParameter("clienteTipoDocumento"));

                JSONObject jsonObject = new JSONObject();
                JSONObject jObjCli = obtenerClienteJSON(empresa, codCli );
                //SONObject jObjDireccion = new JSONObject();

                if (Boolean.parseBoolean(Util.nullCad(jObjCli.get("existe"))) == true) {
                    String facTipDoc;
                    cliTipoCliente  =   (String)jObjCli.get("cliTipPer");
                    cliTipoDocumento =  (String)jObjCli.get("cliTipDoc");     
                    if ("02".equals(cliTipoCliente)) {
                        facTipDoc = "01";
                    } else {
                        if ("06".equals(cliTipoDocumento))
                             facTipDoc = "01";
                        else
                            facTipDoc = "03";
                    }
                    jObjCli.put("facTipDoc", facTipDoc); // Tipo de Documento
                    //jObjCli.put("facPopTipDoc", facPopTipDoc); // PopUp Tipo de Documento
                    if ("02".equals(cliTipoCliente) && "01".equals(cliTipoDocumento)) 
                    {
                        // Empresa
                        jObjCli.put("cliTipoCliente", cliTipoCliente);
                        jObjCli.put("cliTipoDocumento", cliTipoDocumento);
                        jObjCli.put("facTipDoc", "01");
                        jObjCli.put("COCR", "CO");
                    } else if ("01".equals(cliTipoCliente) && "06".equals(cliTipoDocumento)) {
                        // Natural con RUC 
                        jObjCli.put("cliTipoCliente", cliTipoCliente);
                        jObjCli.put("cliTipoDocumento", cliTipoDocumento);
                        jObjCli.put("facTipDoc", "01");
                        jObjCli.put("COCR", "CO");
                    } else {
                        // Natural
                        jObjCli.put("cliTipoCliente", cliTipoCliente);
                        jObjCli.put("cliTipoDocumento", cliTipoDocumento);
                        jObjCli.put("facTipDoc", "03");
                        jObjCli.put("COCR", "CO");
                    }                   

                } else {
                    //jObjCli.put("facPopTipDoc", "N");
                    if ("02".equals(cliTipoCliente) && "01".equals(cliTipoDocumento)) 
                    {
                        // Empresa
                        jObjCli.put("cliTipoCliente", cliTipoCliente);
                        jObjCli.put("cliTipoDocumento", cliTipoDocumento);
                        jObjCli.put("facTipDoc", "01");
                        jObjCli.put("COCR", "CO");
                    } else if ("01".equals(cliTipoCliente) && "06".equals(cliTipoDocumento)) {
                        // Natural con RUC 
                        jObjCli.put("cliTipoCliente", cliTipoCliente);
                        jObjCli.put("cliTipoDocumento", cliTipoDocumento);
                        jObjCli.put("facTipDoc", "01");
                        jObjCli.put("COCR", "CO");
                    } else {
                        // Natural
                        jObjCli.put("cliTipoCliente", cliTipoCliente);
                        jObjCli.put("cliTipoDocumento", cliTipoDocumento);
                        jObjCli.put("facTipDoc", "03");
                        jObjCli.put("COCR", "CO");
                    }  
                }

                jObjCli.put("cliFueBus", "S"); // Comodin para saber si ya se busco un cliente
                //jsonObject.put("direccion", jObjDireccion);
                jsonObject.put("cliente", jObjCli);
                sb.append(jsonObject.toString());
                out.write(sb.toString());
            } else {
                sb.append("relogin");
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

        LOGGER.info("<==== Fin Metodo: obtenerDatosCliente ====>");
    }

    public JSONObject obtenerClienteJSON(String empresa, String codCli) {
        JSONObject jObj = new JSONObject();
        try {
            Cliente cliente = new ClienteDAO().getDatosCliente(empresa, codCli);
            if (cliente!=null){
                jObj.put("codigo", Util.nullCad(cliente.getCodigo()));
                jObj.put("apellidoPaterno", Util.nullCad(cliente.getPaterno()));
                jObj.put("apellidoMaterno", Util.nullCad(cliente.getMaterno()));
                jObj.put("primerNombre", Util.nullCad(cliente.getPrimer_nombre()));
                jObj.put("segundoNombre", Util.nullCad(cliente.getSegundo_nombre()));
                jObj.put("razonSocial", Util.nullCad(cliente.getRazon_social()));
                jObj.put("direccion", Util.nullCad(cliente.getDireccion()));
                jObj.put("telefono1", Util.nullCad(cliente.getTelefono1()));
                jObj.put("telefono2", Util.nullCad(cliente.getTelefono2()));
                jObj.put("celular1", Util.nullCad(cliente.getCelular1()));
                jObj.put("cliTipPer", Util.nullCad(cliente.getTipo_persona())); 
                jObj.put("cliTipDoc", Util.nullCad(cliente.getTipo_documento())); 
                jObj.put("existe", cliente.getExiste());
                jObj.put("formaPago", cliente.getFormaPago());
                jObj.put("ubigeo", cliente.getUbigeo()); 
                jObj.put("codigoDepartamento", cliente.getDepart());
                jObj.put("codigoProvincia", cliente.getProvin());
                jObj.put("codigoDistrito", cliente.getDistri());                       
                jObj.put("existe", cliente.getExiste());
                
            } 
            else
            {
                jObj.put("existe",false);
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        }
        return jObj;
    }
    
      

    public void muestraListaCotizaciones(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        double impuesto = 0;
        try {
            if (Util.sesionEstaActiva(request)) {
                //session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
                //session.setAttribute(SEQ_TMP2_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));   
                String msgError = "";
                String numeroDocumento = Util.nullCad(request.getParameter("clienteNumeroDocumento"));
                String fechaDesde = Util.yyyymmdd(request.getParameter("fechaDesde"));
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                List<Parte> listaPartesTempo = new ParteDAO().muestraListaCotizaciones(empresa, numeroDocumento,fechaDesde);
                if (listaPartesTempo != null && !listaPartesTempo.isEmpty()) {
                    sbLista.append("<table id=\"tablaCotizaciones\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th>Cotizacion</th>");
                    sbLista.append("<th>Fecha</th>");
                    sbLista.append("<th>Venta</th>");
                    sbLista.append("<th>Dscto</th>");
                    sbLista.append("<th>Neto</th>");
                    sbLista.append("<th>IGV</th>");
                    sbLista.append("<th>Total</th>");
                    sbLista.append("<th class=\"text-center\">Acciones</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    for (Parte parte : listaPartesTempo) {
                        sbLista.append("<tr>");
                        sbLista.append("<td class=\"details-control\"></td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getPropuesta() )).append("</td>");
                        sbLista.append("<td>").append(Util.nullCad(parte.getFechaProp() )).append("</td>");                        
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCAL_TotBruto_Sol())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCAL_TotDscto_Sol())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCAL_TotVtaSol())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCAL_TotIGV_Sol())).append("</td>");
                        sbLista.append("<td class=\"text-right\">").append(Util.nullCad(parte.getCAL_TotGralVtaSol())).append("</td>");
                        sbLista.append("<td class=\"text-center\"><button onclick=\"aceptarPropuesta('").append(Util.nullCad(parte.getPropuesta())).append("');\" class=\"btn btn-sm btn-default\" type=\"button\">Seleccionar</button></td>");
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

     
             
    public void actualizaTempoPropuesta(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        double impuesto = 0;
        try {
            if (Util.sesionEstaActiva(request)) {
                            
                //session.setAttribute(SEQ_TMP1_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));
                //session.setAttribute(SEQ_TMP2_SESSION_KEY, new SecuenciaDAO().getSecuenciaTemporal(empresa));                
                long seqTmp1 = Util.nullLon(session.getAttribute(SEQ_TMP1_SESSION_KEY));
                long seqTmp2 = Util.nullLon(session.getAttribute(SEQ_TMP2_SESSION_KEY)); 
                String msgError = "";
                 ParteDAO parteDAO = new ParteDAO();
                Long propuesta = Util.nullLon(request.getParameter("propuesta"));
                String moneda="1";
                String resultInsParteTmp = null;
                resultInsParteTmp = new PedidoOfertaRepDAO().insertaPropuestaTempo(empresa,"00000", propuesta, seqTmp2);
                String resultCalculos = parteDAO.procesaCalculos(empresa, seqTmp2);
                Parte parteTotales = parteDAO.obtieneTotalesTempo(empresa, seqTmp2);
                double totBrutoSol = Util.nullDou(parteTotales.getTotBrutoSol());
                double totDsctoSol = Util.nullDou(parteTotales.getTotDsctoSol());
                double totVtaSol = Util.nullDou(parteTotales.getTotVtaSol());
                double totIgvSol = Util.nullDou(parteTotales.getTotIgvSol());
                double totGralVtaSol = Util.nullDou(parteTotales.getTotGralVtaSol());
                double totBrutoDol = Util.nullDou(parteTotales.getTotBrutoDol());
                double totDsctoDol = Util.nullDou(parteTotales.getTotDsctoDol());
                double totVtaDol = Util.nullDou(parteTotales.getTotVtaDol());
                double totIgvDol = Util.nullDou(parteTotales.getTotIgvDol());
                double totGralVtaDol = Util.nullDou(parteTotales.getTotGralVtaDol());
                
// Totales                                                               
                jsonObjTotales.put("totBrutoSol", totBrutoSol);
                jsonObjTotales.put("totDsctoSol", totDsctoSol);
                jsonObjTotales.put("totVtaSol", totVtaSol);
                jsonObjTotales.put("totIgvSol", totIgvSol);
                jsonObjTotales.put("totGralVtaSol", totGralVtaSol);
                jsonObjTotales.put("totBrutoDol", totBrutoDol);
                jsonObjTotales.put("totDsctoDol", totDsctoDol);
                jsonObjTotales.put("totVtaDol", totVtaDol);
                jsonObjTotales.put("totIgvDol", totIgvDol);
                jsonObjTotales.put("totGralVtaDol", totGralVtaDol);
                JSONObject jsonObjAux = new JSONObject();
                jsonObjAux = cadenaTabla(moneda, request);
                JSONObject jsonObjDoc = jsonObjAux;
                jsonObjTabla.put("tabla", jsonObjDoc.getString("tabla"));
                jsonObjTabla.put("ctosRegs", jsonObjDoc.getInt("ctosRegs"));            
                // Lista los repuestos que estan en el Tempo

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
    
    
    
            
            
    public void ValidarRuc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
         LOGGER.info("<==== Inicio Metodo: obtenerDatosCliente ====>");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("cache-control", "no-cache");
        StringBuilder sb = new StringBuilder();
        PrintWriter out = response.getWriter();
        try {
            if (Util.sesionEstaActiva(request)) {
                JSONArray jArray = new JSONArray();
                HttpSession session = request.getSession();
                String documento =  request.getParameter("documento");
                JSONObject jsonObject = new JSONObject();
                JSONObject jObjExito = SunatUtils.validaRuc(documento);
                jsonObject.put("objExito", jObjExito);
                sb.append(jsonObject.toString());
                out.write(sb.toString());
            } else {
                sb.append("relogin");
            }
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }

        LOGGER.info("<==== Fin Metodo: obtenerDatosCliente ====>");
    }
            
}
