/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.com.gp.action;

import java.io.File;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import pe.com.gp.connection.ConectaSQL;
import pe.com.gp.entity.Usuario;
import pe.com.gp.util.Util;

/**
 *
 * @author Administrador
 */
public class reimpresionAction extends DispatchAction {
     private static final Logger LOGGER = LogManager.getLogger();
    
     
    public void boletaAlmacen(ActionMapping mapping, ActionForm form,
                HttpServletRequest request, HttpServletResponse response) throws Exception 
    {
            LOGGER.info("<==== Inicio Metodo: inicializa ====>");
            String mappingFindForward;
            Connection conn = new ConectaSQL().connection();
            String contextPath = request.getSession().getServletContext().getRealPath("/iReport/" + "prueba.jrxml");
            String reportDest = "HelloReportWorld.pdf";
            Map<String, Object> params = new HashMap<String, Object>();
            //params.put("empNo", "00003");            
            if (Util.sesionEstaActiva(request)) {
                
                LOGGER.info("<==== Inicio Metodo: inicializa ====>");
                 try {
                       JasperReport jasperReport = JasperCompileManager.compileReport(contextPath);
                        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,new HashMap(), conn);
                        JasperExportManager.exportReportToPdfFile(jasperPrint, "sample.pdf");
                 }catch (Exception e) {
                     
                 }
                
            }
        
    }        
     

}     

//   public static void AttendanceReportPDF(String url, String un, String pw, String JDBC) {
//    Connection conn = new ConectaSQL().connection();
//    HttpServletResponse response;
//    String reportSource = "./web/CompiledReport/report3.jasper";
//    String reportDest = "../HelloReportWorld.pdf";
//    Map<String, Object> params = new HashMap<String, Object>();
//    params.put("empNo", "00003");
//    try {
//        //dataUtil = new DatabaseUtility(url, un, pw, JDBC);
//        // Fill the report using an empty data source
//        JasperPrint jasperPrint = JasperFillManager.fillReport(reportSource, params, conn);
//        System.out.println("After jasper print");
//        // Create a PDF exporter
//        JRExporter exporter = new JRPdfExporter();
//
//        // Configure the exporter (set output file name and print object)
//        exporter.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, reportDest);
//        exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
//
//        // Export the PDF file
//        exporter.exportReport();
//        JasperViewer.viewReport(jasperPrint, false);
//        System.out.println("This is the end");
//    } catch (JRException ex) {
//       LOGGER.info("<==== Fin Metodo: inicializa ====>" + ex.toString());
//    } catch (Exception e) {
//       LOGGER.info("<==== Fin Metodo: inicializa ====>" + e.toString());
//    }

     
//    public ActionForward boletaAlmacen(ActionMapping mapping, ActionForm form,
//            HttpServletRequest request, HttpServletResponse response) throws Exception {
//        LOGGER.info("<==== Inicio Metodo: inicializa ====>");
//        String mappingFindForward;
//        if (Util.sesionEstaActiva(request)) {
//            HttpSession session = request.getSession();
//            Usuario user = (Usuario) session.getAttribute("Usuario");
//            String empresa = (String) session.getAttribute("Empresa");
//            Map parametros = new HashMap();
//            //sc = this.getServletContext();
//            //Connection conexion = null;
//            //Conexion C = null;
//         String jasperPath = "";
//	 String pdfName = "";
//	 String rpt = "";
//            try { 
//               jasperPath = request.getServletContext().getRealPath("/Reports");
//		pdfName = "Student Report";
//                rpt = "studentReport.jrxml";
//		JRResultSetDataSource resultSetDataSource = new JRResultSetDataSource(rs);
//		HashMap<String, Object> pm = new HashMap<String, Object>();
//		String logo = jasperPath + "/ws.jpg";
//		pm.put("logo", logo);
//		
//            } catch (JRException e) {
//                throw new ServletException(e);
//            } catch (IOException e) {
//                throw new ServletException(e);
//            } finally {
//                conexion.close();
//            }            
//            //ImpresionForm uform = (ImpresionForm) form;
//            //uform.setFechaFinal(Util.fecha_dia());
//            //uform.setFechaInicial(Util.fecha_dia());
//            mappingFindForward = "boletaAlmacen";
//        } else {
//            mappingFindForward = "logout";
//        }
//        LOGGER.info("<==== Fin Metodo: inicializa ====>");
//
//        return mapping.findForward(mappingFindForward);
//    }    
