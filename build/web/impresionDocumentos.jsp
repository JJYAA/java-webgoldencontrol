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
        <div class="container main-container">
            <div class="widget">
                <div class="widget-content" id="main-content">
                    <ol class="breadcrumb">
                        <li><strong>Consulta</strong></li>
                        <li>Impresi&oacute;n de Docuentos</li>
                    </ol>
                    <html:form action="/ImpresionAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Rango de Fechas</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">De</label>
                                    <div class="col-md-2">                                        
                                        <html:text alt="Fec.Inicial" property="fechaInicial" styleId="fechaInicial"    styleClass="form-control date-picker"></html:text>
                                    </div>    
                                    <label class="col-md-1 control-label">Hasta</label>
                                    <div class="col-md-2">    
                                        <html:text alt="Fec.Finla" property="fechaFinal" styleId="fechaFinal" styleClass="form-control date-picker"></html:text>
                                    </div>          
                                    <button title="Consultar" onclick="muestraDocumentos()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_SEARCH%> Consultar</button>                                       
                                </div>
                            </div>
                        </div>
                        <div class="panel panel-default">
                            <div class="panel-body">
                               
                                     <div id="c_tablaListaDocumentos"></div>                                                                     
                                
                            </div>
                        </div>                                            
                        <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
        <script type="text/javascript" src="js/funciones/jsImpresion.js"></script>
    </body>
</html:html>