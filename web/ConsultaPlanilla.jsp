<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<%@include file="jspf/browser.jspf" %>
<!DOCTYPE html>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html:html>
    <head>
        <%@include file="jspf/css.jspf" %>
    </head>
    <body id="main-wrapper">
        <%@include file="jspf/header.jspf" %>


                    <ol class="breadcrumb">
                        <li><strong>Gesti&oacute;n de Bancos/Reportes</strong></li>
                        <li>Consulta de planillas</li>
                    </ol>
                    <html:form action="/ArchivoBancoAction" styleId="frm_generico" method="POST"  enctype="multipart/form-data" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                                                                                              
                                <div class="panel panel-default">
                                    <div class="panel-heading">Buscar por</div>
                                    
                                    <div class="panel-body">
                                            <div class="form-group">
                                                 <label class="col-md-2 control-label">De</label> 
                                                <div class="col-md-2">
                                                    <html:text alt="De" property="fechaIni" styleId="fechaIni"  maxlength="4" styleClass="form-control input-sm"></html:text>                                           
                                                </div>                                                  
                                                 <label class="col-md-1 control-label">Hasta</label> 
                                                <div class="col-md-2">
                                                    <html:text alt="De" property="fechaFin" styleId="fechaFin"  maxlength="4" styleClass="form-control input-sm"></html:text>                                           
                                                </div> 
                                                <div class="col-md-2">
                                                    <button onclick="muestraDocumentoPlanilla();" id="btnCancelaciones" name="btnCancelaciones" class="btn btn-default" type="button" title="Consultar">                                                    
                                                              Consultar
                                                    </button> 
                                                                                             
                                                </div>                                                 
                                            </div>
                                    </div>
                                </div>
                                <div id="c_tablaListaDocCancelados"></div>    
                                 <html:hidden property="operacion" styleId="operacion"></html:hidden>
                    </html:form>
       

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
       <script type="text/javascript" src="js/funciones/jsArchivoBanco.js"></script>
    </body>
</html:html>