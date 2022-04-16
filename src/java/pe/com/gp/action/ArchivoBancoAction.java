/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;
import java.text.NumberFormat;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.ClientAnchor;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Picture;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.dao.ArchivoBancoDAO;
import pe.com.gp.dao.ImpresionDAO;
import pe.com.gp.dao.PagoProveedoresDAO;
import pe.com.gp.dao.StockDAO;
import pe.com.gp.entity.BeanPreProvision;
import pe.com.gp.entity.Documento;
import pe.com.gp.entity.Global;
import pe.com.gp.entity.Parte;
import pe.com.gp.entity.Usuario;
import pe.com.gp.form.ArchivoBancoForm;
import pe.com.gp.form.RegistroVentasForm;
import pe.com.gp.util.Constantes;
import pe.com.gp.util.ExportaExcel;
import pe.com.gp.util.Util;
import static pe.com.gp.util.Util.fecha_dia;

/**
 *
 * @author Computer
 */
public class ArchivoBancoAction extends DispatchAction {

    private static final Logger LOGGER = LogManager.getLogger();
    private final int ARBITARY_SIZE = 1048;
    
    
    
    public ActionForward inicializaConsulta(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
        String mappingFindForward;
        if (Util.sesionEstaActiva(request)) {
            HttpSession session = request.getSession();
            String empresa = (String) session.getAttribute("Empresa");
            ArchivoBancoForm uform = (ArchivoBancoForm) form;
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia());
            uform.setFlagMueOcuForm("muestra");
            //muestaLista(request,uform);                
            
            mappingFindForward = "inicializaConsulta";
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
            String empresa = (String) session.getAttribute("Empresa");
            ArchivoBancoForm uform = (ArchivoBancoForm) form;
            uform.setFechaFin(fecha_dia());
            uform.setFechaIni(fecha_dia());
            uform.setFlagMueOcuForm("muestra");
            //muestaLista(request,uform);                
            
            mappingFindForward = "inicializa";
        } else {
            mappingFindForward = "logout";
        }
        LOGGER.info("<==== Fin Metodo: inicializa ====>");

        return mapping.findForward(mappingFindForward);
    }
    
 public void muestraPlanillasGeneradas(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');      
        DecimalFormat df = new DecimalFormat("######0.00", symbols); 
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String fechaIni  = Util.yyyymmdd(Util.nullCad(request.getParameter("fechaIni")));
                String fechaFin  = Util.yyyymmdd(Util.nullCad(request.getParameter("fechaFin")));
                
               // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                int indice  = 1;
                 String strId = "";
                List<BeanPreProvision> ListaPreProvision = new ArchivoBancoDAO().listaPlanillasGeneradas(usuario.getRucEmpresa(),fechaIni,fechaFin);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table  id=\"tablaListaDocCancelados\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">PLANILLA</th>");
                    sbLista.append("<th class=\"text-center\">ASIENTO</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">GLOSA</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">DOCUMENTO</th>");
                    sbLista.append("<th class=\"text-center\">PAGADO</th>");
                    sbLista.append("<th class=\"text-center\">CTA. BANCARIA</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    String codigoProv="";
                    String planilla="";
                    String sumaCuenta="";
                    String cuentaEmpresa="";
                    double totalHaber = 0;
                    int items=0;
                    boolean Cambio=false;
                    boolean flag=false;
                    String ls_asiento="";
                    while ((ListaPreProvision.size()>items)&&(!Cambio)){
                         totalHaber = 0;
                        planilla = ListaPreProvision.get(items).getPlanilla();
                        ls_asiento =ListaPreProvision.get(items).getAnho() + '-' + ListaPreProvision.get(items).getMes() + '-' + ListaPreProvision.get(items).getTipoComprobante() + '-' + ListaPreProvision.get(items).getAsiento();
                        sbLista.append("<tr>");
                        if ("S".equals(ListaPreProvision.get(items).getAnulado()))
                            sbLista.append("<td>").append(planilla).append("</td>"); 
                        else
                            sbLista.append("<td>").append("<a href=\"#\" onclick=\"muestratxtnew('" + planilla + "')\"     >" + planilla +"</a>").append("</td>"); 
                        sbLista.append("<td>").append(ls_asiento).append("</td>"); 
                        if ("S".equals(ListaPreProvision.get(items).getAnulado()))
                            sbLista.append("<td class=\"text-center\">").append("").append("</td>");
                        else
                            sbLista.append("<td class=\"text-center\">").append("<a href=\"#\" onclick=\"eliminarAsiento('" + planilla + "','" + ListaPreProvision.get(items).getMes() + "','"  + ListaPreProvision.get(items).getAnho() +  "','" + ListaPreProvision.get(items).getTipoComprobante() + "','" + ListaPreProvision.get(items).getAsiento() + "')\" >" + Constantes.ICON_CLEAR + "</a>").append("</td>");; 
                        sbLista.append("<td class=\"text-center\">").append("<a href=\"ArchivoBancoAction.do?operacion=visualizaPlanilla&planilla=" + planilla + "&anulado=" + ListaPreProvision.get(items).getAnulado()+ "\" target=\"_blank\" >" + Constantes.ICON_PDF + "</a>").append("</td>");
                        sbLista.append("<td>").append(ListaPreProvision.get(items).getGlosa()).append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>");
                        sbLista.append("<td>").append("").append("</td>");
                        sbLista.append("<td>").append("").append("</td>");
                        sbLista.append("<td>").append("").append("</td>");
                        sbLista.append("</tr>");
                        flag=false;
                        while ((ListaPreProvision.size()>items)&&(!Cambio)&&(planilla.equals(ListaPreProvision.get(items).getPlanilla())))
                        {     
                            totalHaber +=ListaPreProvision.get(items).getDebe();
                            sbLista.append("<tr>");
                            sbLista.append("<td>").append("").append("</td>");
                            sbLista.append("<td>").append("").append("</td>"); 
                            sbLista.append("<td>").append("").append("</td>"); 
                            sbLista.append("<td>").append("").append("</td>");
                            sbLista.append("<td>").append("").append("</td>"); 
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getTipoDocumento()).append("</td>"); 
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getNumeroDoc()).append("</td>"); 
                            sbLista.append("<td class=\"text-right\">").append(df.format(ListaPreProvision.get(items).getDebe())).append("</td>");
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getCuentaBancaria()).append("</td>"); 
                            sbLista.append("<td>").append(".").append("</td>");
                            sbLista.append("</tr>");
                            indice++;                            
                            items++;  
                        }
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>");
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td bgcolor= \"#00FFFF\">").append("TOTAL").append("</td>");                                              
                        sbLista.append("<td class=\"text-right\"  bgcolor= \"#00FFFF\">").append(df.format(totalHaber)).append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                       
                        sbLista.append("</tr>");                        
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
   
    
public void muestraDocumentoCancelados(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        DecimalFormatSymbols simbolo=new DecimalFormatSymbols();
        simbolo.setDecimalSeparator('.');
        simbolo.setGroupingSeparator(',');  
        DecimalFormat nf = new DecimalFormat("###,###.##",simbolo);  
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String formaPago  = Util.nullCad(request.getParameter("formaPago"));
                String proveedor  = Util.nullCad(request.getParameter("proveedor"));
                String tipoBancoMoneda  = Util.nullCad(request.getParameter("tipoBancoMoneda"));
                String fechaIni  = Util.yyyymmdd(Util.nullCad(request.getParameter("fechaIni")));
                String fechaFin  = Util.yyyymmdd(Util.nullCad(request.getParameter("fechaFin")));
                if ("".equals(proveedor)) proveedor="%";
               // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                int indice  = 1;
                 String strId = "";
                List<BeanPreProvision> ListaPreProvision = new ArchivoBancoDAO().muestraDocumentoCancelados(usuario.getRucEmpresa(), proveedor, formaPago,tipoBancoMoneda,fechaIni,fechaFin);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table  id=\"tablaListaDocCancelados\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">RUC</th>");
                    sbLista.append("<th class=\"text-center\">RAZON SOCIAL</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">DOCUMENTO</th>");
                    sbLista.append("<th class=\"text-center\">PAGADO</th>");
                    sbLista.append("<th class=\"text-center\">CTA. BANCARIA</th>");
                    sbLista.append("<th class=\"text-center\">F.PROGRAMADA</th>");
                    sbLista.append("<th class=\"text-center\">Cuenta</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    String codigoProv="";
                    String sumaCuenta="";
                    String cuentaEmpresa="";
                    double totalHaber = 0;
                    int items=0;
                    boolean Cambio=false;
                    boolean flag=false;
                    while ((ListaPreProvision.size()>items)&&(!Cambio)){
                         totalHaber = 0;
                        codigoProv = ListaPreProvision.get(items).getRucProveedor();
                        flag=false;
                        while ((ListaPreProvision.size()>items)&&(!Cambio)&&(codigoProv.equals(ListaPreProvision.get(items).getRucProveedor())))
                        {                              
                            totalHaber +=ListaPreProvision.get(items).getHaber();
                            strId="fechaPago" + String.valueOf(indice);
                            sbLista.append("<tr>");
                            if (!flag)
                                sbLista.append("<td class=\"text-left\">").append("<input type=\"hidden\" id=\"id\" value=\"" + indice +"\" size=\"3\" name=\"id\">").append("<input type=\"hidden\" size=\"2\" id=\"modifica\" name=\"modifica\">").append("<A href=\"#\" onclick=\"muestratxt('" + ListaPreProvision.get(items).getRucProveedor().trim() + "')\" >" + ListaPreProvision.get(items).getRucProveedor() + "</A>").append("</td>"); 
                            else
                                sbLista.append("<td class=\"text-left\">").append("<input type=\"hidden\" id=\"id\" value=\"" + indice +"\" size=\"3\" name=\"id\">").append("<input type=\"hidden\" size=\"2\" id=\"modifica\" name=\"modifica\">").append(ListaPreProvision.get(items).getRucProveedor()).append("</td>");
                            flag=true;
                            sbLista.append("<td class=\"text-left\">").append("<input type=\"hidden\" id=\"codigo\" value=\"" + ListaPreProvision.get(items).getRucProveedor() +"\" size=\"11\" name=\"codigo\">").append(Util.nullCad(ListaPreProvision.get(items).getRs())).append("</td>");                         
                            sbLista.append("<td>").append("<input type=\"hidden\" id=\"tipodocumento\" name=\"tipodocumento\" value='" + ListaPreProvision.get(items).getTipoDocumento() + "'  > ").append(Util.nullCad(ListaPreProvision.get(items).getTipoDocumento())).append("</td>");       
                            sbLista.append("<td>").append("<input type=\"hidden\" id=\"documento\" name=\"documento\" value='" + ListaPreProvision.get(items).getNumeroDoc() + "'  > ").append(Util.nullCad(ListaPreProvision.get(items).getNumeroDoc())).append("</td>");    
                            sbLista.append("<td class=\"text-right\">").append(nf.format(ListaPreProvision.get(items).getHaber())).append("</td>");  
                            sbLista.append("<td class=\"text-center\">").append("<input type=\"text\" id=\"cuentaBanco\" readonly=\"true\" name=\"cuentaBanco\" style=\"text-align: right;\" value=\"" + ListaPreProvision.get(items).getCuentaBancaria() + "\"  >").append("</td>"); 
                            sbLista.append("<td class=\"text-center\">").append(Util.nullCad(ListaPreProvision.get(items).getFechaProPago())).append("</td>"); 
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getCuentaGasto()).append("</td>"); 
                            sbLista.append("</tr>");
                            indice++;                            
                            items++;  
                        }
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td bgcolor= \"#00FFFF\">").append("TOTAL").append("</td>");
                                              
                        sbLista.append("<td class=\"text-right\"  bgcolor= \"#00FFFF\">").append(nf.format(totalHaber)).append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("</tr>");                        
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
 
    public void enviotxtBanco(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response) throws Exception {
           LOGGER.info("<==== Inicio Metodo: exportarExcel ====>");
        ByteArrayOutputStream baos = null;
        
        try {
            if (Util.sesionEstaActiva(request)) {
                ArchivoBancoForm uform = (ArchivoBancoForm) form;

            	HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("Usuario");
				// Parametros
                String msgError = "";   
                String monedaCodigo;
                String buscarPor  =  uform.getBuscarPor();
                String proveedor  = Util.nullCad(request.getParameter("proveedor"));
                String tipoBancoMoneda  = Util.nullCad(uform.getBancoMoneda());
                String fechaIni  = Util.yyyymmdd(Util.nullCad(uform.getFechaIni()));
                String fechaFin  = Util.yyyymmdd(Util.nullCad(uform.getFechaFin()));
                String codigoProv = Util.nullCad(uform.getRucProveedor());
                String CerosMonto="";
                //DecimalFormat df = new DecimalFormat("#.00");
                
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator('.');
                symbols.setGroupingSeparator(',');      
                DecimalFormat df = new DecimalFormat("######0.00", symbols);                
                
                
                if (("07".equals(tipoBancoMoneda))||("09".equals(tipoBancoMoneda)))
                    monedaCodigo ="0001";
                else
                    monedaCodigo ="0002";
                //String codigoProv="";
                int items=0;
                int aux= 0;
                String contenido = "";
                StringBuilder sbLista = new StringBuilder();
                List<BeanPreProvision> ListaPreProvision = new ArchivoBancoDAO().muestraDocumentoCancelados(usuario.getRucEmpresa(), codigoProv, buscarPor,tipoBancoMoneda,fechaIni,fechaFin);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    
                    String path = session.getServletContext().getRealPath("/");
                     
                    
                    boolean Cambio = false;
                    
                    String cuentaEmpresa="";
                    String tipoCuenta="";
                    String referencia="";
                    String exonenadoITF="";
                    String numeroTotal="";
                    String tipoDocEmpresa ="6";
                    double totalHaber = 0;
                    long sumaCuenta= 0;                                        
                    while ((ListaPreProvision.size()>items)&&(!Cambio))
                    {
                        //codigoProv = ListaPreProvision.get(items).getRucProveedor().trim();
                        String nombreFile = codigoProv + ".txt";
                        String ruta = path + nombreFile;
                        File file = new File(ruta);
                        if (!file.exists()) {
                            file.createNewFile();
                        } 
                        FileWriter fw = new FileWriter(file);
                        BufferedWriter bw = new BufferedWriter(fw);                        
                        
                        
                        aux = items;
                        
                        while ((ListaPreProvision.size()>aux)&&(!Cambio) && codigoProv.equals(ListaPreProvision.get(aux).getRucProveedor().trim())     ){                        
                            sumaCuenta = Util.nullLon(ListaPreProvision.get(aux).getCuentaChkSumEmpresa());
                            cuentaEmpresa = ListaPreProvision.get(aux).getCuentaEmpresa();
                            while ((ListaPreProvision.size()>aux)&&(!Cambio)&&(codigoProv.equals(ListaPreProvision.get(aux).getRucProveedor().trim())))
                            {   
                                sumaCuenta += Util.nullLon(ListaPreProvision.get(aux).getCuentaChkSumProveedor()); 
                                totalHaber +=ListaPreProvision.get(aux).getHaber();
                                aux++;    
                            }
                            Cambio = true;
                        } 
                        tipoCuenta ="C";
                        //monedaCodigo ="0001";
                        contenido ="1";
                        contenido+=Util.completarCerosIzq(items, "000000");
                        contenido+=Util.yyyymmdd2(Util.fecha_dia());
                        contenido+=tipoCuenta;
                        contenido+=monedaCodigo;
                        contenido+= cuentaEmpresa + Util.espaciosEnBlanco(20- cuentaEmpresa.length());
                        
                        //contenido+= Util.completarCerosIzq2(totalHaber, 17);
                        
                        numeroTotal  = df.format(totalHaber); 
                        CerosMonto = Util.repetirCadena("0", 17 - numeroTotal.length());
                            
                        contenido += CerosMonto + numeroTotal;                        
                        
                        
                        
                        
                        referencia = "YIW2028 - YIW2037                       ";
                        contenido+=referencia; 
                        exonenadoITF="S";
                        contenido+=exonenadoITF; 
                        contenido+=Util.completarCerosIzq(sumaCuenta, "000000000000000") ; 
                        contenido+= "\r\n";
                        aux = items;                    
                        Cambio=false;
                        String NombreProveedor="";
                        String numeroRuc="";
                        while ((ListaPreProvision.size()>aux)&&(!Cambio)){
                           // codigoProv = ListaPreProvision.get(aux).getRucProveedor();
                            contenido+="2";
                            contenido+= ListaPreProvision.get(aux).getTipoCuentaProveedor();
                            contenido+= ListaPreProvision.get(aux).getCuentaProveedor() + Util.espaciosEnBlanco(20 - ListaPreProvision.get(aux).getCuentaProveedor().length() );
                            contenido+= "1";
                            contenido+= "6";
                            numeroRuc = ListaPreProvision.get(aux).getRuc();
                            if (numeroRuc==null) numeroRuc="";
                            numeroRuc = numeroRuc.trim();
                            contenido+= numeroRuc + Util.espaciosEnBlanco(12 - numeroRuc.length());
                            contenido+=Util.espaciosEnBlanco(3);
                            NombreProveedor = ListaPreProvision.get(aux).getRs();
                            if (NombreProveedor==null) NombreProveedor="";                            
                            NombreProveedor = NombreProveedor.trim();
                            contenido+= NombreProveedor   + Util.espaciosEnBlanco(75 - NombreProveedor.length());
                            contenido+= Util.espaciosEnBlanco(40);
                            contenido+= Util.espaciosEnBlanco(20);   
                            contenido+= monedaCodigo; 
                            
                            
                            numeroTotal  = df.format(totalHaber); 
                            CerosMonto = Util.repetirCadena("0", 17 - numeroTotal.length());
                            
                            contenido += CerosMonto + numeroTotal;
                            
                            //contenido+= Util.completarCerosIzq2(totalHaber, 17);
                            
                            contenido+="S";
                            contenido+= "\r\n";
                            int posicion = 0;
                            totalHaber = 0;
                            String documento="";
                            
                            
                            while ((ListaPreProvision.size()>aux)&&(!Cambio)&&(codigoProv.equals(ListaPreProvision.get(aux).getRucProveedor().trim())))
                            {
                                totalHaber =ListaPreProvision.get(aux).getHaber();
                                contenido+="3";
                                contenido+=ListaPreProvision.get(aux).getTipoDocumentoPagar();
                                posicion = ListaPreProvision.get(aux).getNumeroDoc().indexOf("-");
                                if (posicion==-1)
                                    documento= ListaPreProvision.get(aux).getNumeroDoc();
                                else
                                    documento= ListaPreProvision.get(aux).getNumeroDoc().substring(posicion + 1, ListaPreProvision.get(aux).getNumeroDoc().length()-1);
                                contenido+=Util.completarCerosIzq(Util.nullNum(documento),"000000000000000");
                                
                                numeroTotal = df.format(totalHaber);
                                CerosMonto = Util.repetirCadena("0", 17 - numeroTotal.length());
                                contenido+= CerosMonto + numeroTotal; 
                                aux++;
                                //if (ListaPreProvision.size()>aux)
                                    contenido+= "\r\n";
                               
                            } 
                            Cambio=true;
                        }
                        Cambio=false;
                        bw.write(contenido);
                        bw.close();                    
                        items = items + aux;                        
                        response.setContentType("text/plain");
                        response.setHeader("Content-disposition", "attachment; filename=" + nombreFile + "");
                        FileInputStream in =  new FileInputStream(new File(ruta));
                        ServletOutputStream out = response.getOutputStream();
                        int len = 0;
                        byte[] buffer = new byte[1024];
                        while((len=in.read(buffer))>0) {
                            out.write(buffer, 0, len);
                        }
                        
                        
                        /*
                        int bit = 256;
                        int i = 0;
                        try {
                               while ((bit) >= 0) {
                                bit = in.read();
                                out.write(bit);
                            }
                            //System.out.println("" +bit);
                        }catch (IOException ioe) {
                            ioe.printStackTrace(System.out);
                        }                        
                        */
                        
                        /*
                        byte[] outputByte = new byte[4096];
                        while(in.read(outputByte, 0, 4096) != -1){
                                out.write(outputByte, 0, 4096);
                        }
                                */
                        in.close();
                        out.flush();
                        out.close();                        
                       // if (file.exists()) {
                        //    file.delete();
                        //} 
                        
                    }
                    
                }       
            } 
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(baos);
            //Util.close(out);
        }
        LOGGER.info("<==== Fin Metodo: exportarExcel ====>");
    }
    
    
    

    public void muestratxtnew(ActionMapping mapping, ActionForm form,
               HttpServletRequest request, HttpServletResponse response) throws Exception {
           LOGGER.info("<==== Inicio Metodo: exportarExcel ====>");
        ByteArrayOutputStream baos = null;
        
        try {
            if (Util.sesionEstaActiva(request)) {
                ArchivoBancoForm uform = (ArchivoBancoForm) form;

            	HttpSession session = request.getSession();
		Usuario usuario = (Usuario) session.getAttribute("Usuario");
				// Parametros
                String msgError = "";   
                String monedaCodigo;
                String buscarPor  =  uform.getBuscarPor();
                String proveedor  = Util.nullCad(request.getParameter("proveedor"));
                Long planilla = Util.nullLon(request.getParameter("planilla"));
                String CerosMonto="";
                //DecimalFormat df = new DecimalFormat("#.00");
                
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator('.');
                symbols.setGroupingSeparator(',');      
                DecimalFormat df = new DecimalFormat("######0.00", symbols);                
                
                
                
                 monedaCodigo ="0001";
               
                //String codigoProv="";
                int items=0;
                //int aux= 0;
                String contenido = "";
                String codigoProv= "";
                StringBuilder sbLista = new StringBuilder();
                List<BeanPreProvision> ListaPreProvision = new ArchivoBancoDAO().listaPlanillas(usuario.getRucEmpresa(), planilla);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    
                    String path = session.getServletContext().getRealPath("/");
                     
                    
                    boolean Cambio = false;
                    
                    String cuentaEmpresa="";
                    String tipoCuenta="";
                    String referencia="";
                    String exonenadoITF="";
                    String numeroTotal="";
                    String tipoDocEmpresa ="6";
                    double totalHaber = 0;
                    long sumaCuenta= 0;   
                    int posi = 0;
                    String nombreFile = usuario.getRucEmpresa() + "_PLA_" + String.valueOf(planilla)  +  "_" + Util.yyyymmdd2(Util.fecha_dia()) + ".txt";
                    String ruta = path + nombreFile;
                    File file = new File(ruta);
                    if (!file.exists()) {
                        file.createNewFile();
                    } 
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw); 
                    items = 0;
                    sumaCuenta = Util.nullLon(ListaPreProvision.get(items).getCuentaChkSumEmpresa());
                    while ((ListaPreProvision.size()>items)){
                        codigoProv = ListaPreProvision.get(items).getRucProveedor().trim();
                        sumaCuenta += Util.nullLon(ListaPreProvision.get(items).getCuentaChkSumProveedor()); 
                        while ((ListaPreProvision.size()>items)&&(!Cambio) && codigoProv.equals(ListaPreProvision.get(items).getRucProveedor().trim())     ){                        
                            totalHaber +=ListaPreProvision.get(items).getHaber();
                            items++;
                        }                       
                    }
                    tipoCuenta ="C";
                    monedaCodigo =ListaPreProvision.get(0).getMoneda();
                    cuentaEmpresa = ListaPreProvision.get(0).getCuentaEmpresa();
                    referencia = ListaPreProvision.get(0).getGlosa().trim();
                    referencia = referencia.substring(0,referencia.length()-1);
                    referencia = ListaPreProvision.get(0).getGlosa().trim() + Util.espaciosEnBlanco(40 - ListaPreProvision.get(0).getGlosa().trim().length());
                    contenido ="1";
                    contenido+=Util.completarCerosIzq(items, "000000");
                    contenido+=Util.yyyymmdd2(Util.fecha_dia());
                    contenido+=tipoCuenta;
                    contenido+=monedaCodigo;
                    contenido+= cuentaEmpresa + Util.espaciosEnBlanco(20- cuentaEmpresa.length());
                    numeroTotal  = df.format(totalHaber); 
                    CerosMonto = Util.repetirCadena("0", 17 - numeroTotal.length());
                    contenido += CerosMonto + numeroTotal;                        
                    contenido+=referencia; 
                    exonenadoITF="S";
                    contenido+=exonenadoITF; 
                    contenido+=Util.completarCerosIzq(sumaCuenta, "000000000000000") ; 
                    contenido+= "\r\n";         
                    items = 0;
                    while ((ListaPreProvision.size()>items))
                    {
                        
                        codigoProv = ListaPreProvision.get(items).getRucProveedor().trim();
                                        
                        Cambio=false;
                        String NombreProveedor="";
                        String numeroRuc="";
                        totalHaber = 0;
                        posi = items;
                        while ((ListaPreProvision.size()>posi)&&(codigoProv.equals(ListaPreProvision.get(posi).getRucProveedor().trim()))){
                            totalHaber +=ListaPreProvision.get(posi).getHaber();
                            posi ++;
                        }
                        
                        
                        while ((ListaPreProvision.size()>items)&&(codigoProv.equals(ListaPreProvision.get(items).getRucProveedor().trim()))){
                           // codigoProv = ListaPreProvision.get(aux).getRucProveedor();
                            contenido+="2";
                            contenido+= ListaPreProvision.get(items).getTipoCuentaProveedor();
                            contenido+= ListaPreProvision.get(items).getCuentaProveedor() + Util.espaciosEnBlanco(20 - ListaPreProvision.get(items).getCuentaProveedor().length() );
                            contenido+= "1";
                            contenido+= "6";
                            numeroRuc = ListaPreProvision.get(items).getRuc();
                            if (numeroRuc==null) numeroRuc="";
                            numeroRuc = numeroRuc.trim();
                            contenido+= numeroRuc + Util.espaciosEnBlanco(12 - numeroRuc.length());
                            contenido+=Util.espaciosEnBlanco(3);
                            NombreProveedor = ListaPreProvision.get(items).getRs();
                            if (NombreProveedor==null) NombreProveedor="";                            
                            NombreProveedor = NombreProveedor.trim();
                            contenido+= NombreProveedor   + Util.espaciosEnBlanco(75 - NombreProveedor.length());
                            contenido+= Util.espaciosEnBlanco(40);
                            contenido+= Util.espaciosEnBlanco(20);   
                            contenido+= monedaCodigo; 
                            
                            
                            numeroTotal  = df.format(totalHaber); 
                            CerosMonto = Util.repetirCadena("0", 17 - numeroTotal.length());
                            
                            contenido += CerosMonto + numeroTotal;
                            
                            //contenido+= Util.completarCerosIzq2(totalHaber, 17);
                            
                            contenido+="S";
                            contenido+= "\r\n";
                            int posicion = 0;
                            totalHaber = 0;
                            String documento="";
                            
                            String strAuxiliar="";
                            int sizeCadena=0;
                            while ((ListaPreProvision.size()>items)&&(!Cambio)&&(codigoProv.equals(ListaPreProvision.get(items).getRucProveedor().trim())))
                            {
                                totalHaber =ListaPreProvision.get(items).getHaber();
                                contenido+="3";
                                contenido+=ListaPreProvision.get(items).getTipoDocumentoPagar();
                                posicion = ListaPreProvision.get(items).getNumeroDoc().indexOf("-");
                                strAuxiliar = ListaPreProvision.get(items).getNumeroDoc();
                                if (posicion==-1)
                                    documento= ListaPreProvision.get(items).getNumeroDoc();
                                else
                                    documento= strAuxiliar.substring(0,posicion) + strAuxiliar.substring(posicion + 1,strAuxiliar.length());
                                sizeCadena = documento.length();
                                contenido+=Util.repetirCadena("0",15 - sizeCadena) + documento;
                                
                                numeroTotal = df.format(totalHaber);
                                CerosMonto = Util.repetirCadena("0", 17 - numeroTotal.length());
                                contenido+= CerosMonto + numeroTotal; 
                                items++;
                                //if (ListaPreProvision.size()>aux)
                                    contenido+= "\r\n";
                               
                            } 
                        }                                                                    
                    }
                    bw.write(contenido);
                    bw.close();                    
                                         
                    response.setContentType("text/plain");
                    response.setHeader("Content-disposition", "attachment; filename=" + nombreFile + "");
                    FileInputStream in =  new FileInputStream(new File(ruta));
                    ServletOutputStream out = response.getOutputStream();
                    int len = 0;
                    byte[] buffer = new byte[1024];
                    while((len=in.read(buffer))>0) {
                        out.write(buffer, 0, len);
                    }
                    in.close();
                    out.flush();
                    out.close();                        
                }       
            } 
        } catch (Exception e) {
            LOGGER.error("GP.ERROR: {}", e);
            throw e;
        } finally {
            Util.close(baos);
            //Util.close(out);
        }
        LOGGER.info("<==== Fin Metodo: exportarExcel ====>");
    }
    
    public void visualizaPlanilla(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        ByteArrayOutputStream baos = null;
        ServletOutputStream out = null;
        Connection cn = new ConectaSQL().connection();
        //RequerimientoAdmDAO requerimientoAdmDAO = new RequerimientoAdmDAO();
        try {
            String path = request.getServletContext().getRealPath("/");
            //FacRepMostradorForm uform = (FacRepMostradorForm) form;
            HttpSession session = request.getSession();
            Usuario user = (Usuario) session.getAttribute("Usuario");
            String rucEmpresa = user.getRucEmpresa();
            String ls_planilla = request.getParameter("planilla"); 
            String ls_anulado = request.getParameter("anulado");
            Long ln_planilla = Util.nullLon(ls_planilla);

            // uform.getTiendaAux();g 
           // String tipoPdf = request.getParameter("tipoJasper");


            // =============================
            // Setear parametros del reporte
            // =============================
            Map params = new HashMap();


           
            //params.put("SUBREPORT_DIR", path + Constantes.DIRECTORIO_IREPORT + "/");

            // ======
            // Jasper
            // ====== 
            cn = new ConectaSQL().connection();
            String fileJasper="";
            if (ls_anulado.equals("N")) {
                params.put("pRucEmpresa", user.getRucEmpresa());
                params.put("pPlanilla", ln_planilla );                              
                fileJasper = "planilla.jasper"; 
            }               
            else
            {
                params.put("pRucEmpresa", user.getRucEmpresa());
                params.put("pPlanilla", ln_planilla );
                params.put("pPath", path + Constantes.DIRECTORIO_IMAGENES + "/marcaagua.png");                
                fileJasper = "planilla_anulado.jasper";
            }
            String jasper = path + Constantes.DIRECTORIO_IREPORT + "//" + fileJasper;
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasper, params, cn);
            String filename;

            String time = new SimpleDateFormat("HHmmssSSS").format(new java.util.Date());
            baos = new ByteArrayOutputStream();
            response.setContentType("application/pdf");
            filename = "ssss" + "_" + "a" + ".pdf";
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

    
             
     public void EliminarVoucherPlanilla(
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
        JSONObject jsonObjItem = new JSONObject();
        JSONObject jsonObjDatos = new JSONObject();        
        String rucEmpresa = user.getRucEmpresa();
        long  planilla = Util.nullLon(request.getParameter("planilla"));    
        String  mes = request.getParameter("mes");
        String  anho = request.getParameter("anho");
        String  tipocomprobante = request.getParameter("tipocomprobante");
        long  asiento = Util.nullLon(request.getParameter("asiento"));
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";
                // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                String resultado = new ArchivoBancoDAO().EliminarVoucherPlanilla(rucEmpresa,planilla,mes,anho,tipocomprobante,asiento,user.getCodigo());
                if (resultado.equals("")) {
                    jsonObjMsg = new JSONObject();
                    jsonObjMsg.put("tipoMsg", "exito");
                    jsonObjMsg.put("Msg", "");
                }
                else
                {
                    jsonObjMsg = new JSONObject();
                    jsonObjMsg.put("tipoMsg", "error");                    
                     jsonObjMsg.put("Msg", resultado);
                }
            } else {
                jsonObjMsg = new JSONObject();
                jsonObjMsg.put("tipoMsg", "relogin");
            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("objMensaje", jsonObjMsg);
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

     
public void muestraDocumentoPlanilla(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
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
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator('.');
        symbols.setGroupingSeparator(',');      
        DecimalFormat df = new DecimalFormat("######0.00", symbols); 
        try {
            if (Util.sesionEstaActiva(request)) {
                String msgError = "";               
                String fechaIni  = Util.yyyymmdd(Util.nullCad(request.getParameter("fechaIni")));
                String fechaFin  = Util.yyyymmdd(Util.nullCad(request.getParameter("fechaFin")));
                
               // Lista los repuestos que estan en el Tempo
                StringBuilder sbLista = new StringBuilder();
                int indice  = 1;
                 String strId = "";
                List<BeanPreProvision> ListaPreProvision = new ArchivoBancoDAO().listaPlanillasGeneradas(usuario.getRucEmpresa(),fechaIni,fechaFin);
                if (ListaPreProvision != null && !ListaPreProvision.isEmpty()) {
                    sbLista.append("<table  id=\"tablaListaDocCancelados\">");
                    sbLista.append("<thead>");
                    sbLista.append("<tr>");
                    //sbLista.append("<th class=\"details-control\">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>"); // (para plugin DataTables) 6 espacios
                    sbLista.append("<th class=\"text-center\">PLANILLA</th>");
                    sbLista.append("<th class=\"text-center\">ASIENTO</th>");
                    sbLista.append("<th class=\"text-center\">F.CONTABLE</th>");
                    sbLista.append("<th class=\"text-center\">F.GENERACION</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">&nbsp;</th>");
                    sbLista.append("<th class=\"text-center\">GLOSA</th>");
                    sbLista.append("<th class=\"text-center\">RUC</th>");
                    sbLista.append("<th class=\"text-center\">RAZON SOCIAL</th>");
                    sbLista.append("<th class=\"text-center\">MONEDA</th>");
                    sbLista.append("<th class=\"text-center\">TD</th>");
                    sbLista.append("<th class=\"text-center\">DOCUMENTO</th>");
                    sbLista.append("<th class=\"text-center\">PAGADO</th>");
                    sbLista.append("<th class=\"text-center\">CTA. BANCARIA</th>");
                    sbLista.append("</tr>");
                    sbLista.append("</thead>");
                    sbLista.append("<tbody>");
                    String strValor="";
                    String codigoProv="";
                    String planilla="";
                    String sumaCuenta="";
                    String cuentaEmpresa="";
                    double totalHaber = 0;
                    int items=0;
                    boolean Cambio=false;
                    boolean flag=false;
                    String ls_asiento="";
                    while ((ListaPreProvision.size()>items)&&(!Cambio)){
                         totalHaber = 0;
                        planilla = ListaPreProvision.get(items).getPlanilla();
                        ls_asiento =ListaPreProvision.get(items).getAnho() + '-' + ListaPreProvision.get(items).getMes() + '-' + ListaPreProvision.get(items).getTipoComprobante() + '-' + ListaPreProvision.get(items).getAsiento();
                        sbLista.append("</tr>");
                        flag=false;
                        while ((ListaPreProvision.size()>items)&&(!Cambio)&&(planilla.equals(ListaPreProvision.get(items).getPlanilla())))
                        {     
                            totalHaber +=ListaPreProvision.get(items).getDebe();
                            sbLista.append("<tr>");
                            sbLista.append("<td>").append(planilla).append("</td>");
                            sbLista.append("<td>").append(ls_asiento).append("</td>"); 
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getFechaContable()).append("</td>");
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getFechaRegistro()).append("</td>");
                            if ("S".equals(ListaPreProvision.get(items).getAnulado()))
                                sbLista.append("<td>").append("SI").append("</td>"); 
                            else
                                sbLista.append("<td>").append("NO").append("</td>"); 
                            sbLista.append("<td class=\"text-center\">").append("<a href=\"ArchivoBancoAction.do?operacion=visualizaPlanilla&planilla=" + planilla + "&anulado=" + ListaPreProvision.get(items).getAnulado()+ "\" target=\"_blank\" >" + Constantes.ICON_PDF + "</a>").append("</td>");
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getGlosa()).append("</td>"); 
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getRucProveedor()).append("</td>");
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getRs()).append("</td>");
                            if ("1".equals(ListaPreProvision.get(items).getMoneda()))
                                sbLista.append("<td>").append("S/").append("</td>");
                            else
                                sbLista.append("<td>").append("US$").append("</td>");
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getTipoDocumento()).append("</td>"); 
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getNumeroDoc()).append("</td>"); 
                            sbLista.append("<td class=\"text-right\">").append(df.format(ListaPreProvision.get(items).getDebe())).append("</td>");
                            sbLista.append("<td>").append(ListaPreProvision.get(items).getCuentaBancaria()).append("</td>"); 
                            sbLista.append("</tr>");
                            indice++;                            
                            items++;  
                        }
                        sbLista.append("<tr>");
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>");                         
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>");                        
                        sbLista.append("<td>").append("").append("</td>");
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td bgcolor= \"#00FFFF\">").append("TOTAL").append("</td>");                                              
                        sbLista.append("<td class=\"text-right\"  bgcolor= \"#00FFFF\">").append(df.format(totalHaber)).append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                        sbLista.append("<td>").append("").append("</td>"); 
                       
                        sbLista.append("</tr>");                        
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
