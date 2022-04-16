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
                        <li><strong>Archivos</strong></li>
                        <li>Asignar Mail</li>
                    </ol>
                    <html:form action="/responsablesMailAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Listado de responsables</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-3 control-label">1</label>
                                    <div class="col-md-4">
                                         <html:text alt="Mail 1" property="mail01" styleId="mail01"   styleClass="form-control input-sm "/>
                                    </div>
                                    <html:checkbox property="chk1"></html:checkbox>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label">2</label>
                                    <div class="col-md-4">
                                         <html:text alt="Mail 2" property="mail02" styleId="mail02"   styleClass="form-control input-sm "/>
                                    </div>
                                    <html:checkbox property="chk2"></html:checkbox>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label">3</label>
                                    <div class="col-md-4">
                                         <html:text alt="Mail 3" property="mail03" styleId="mail03"   styleClass="form-control input-sm "/>
                                    </div>
                                    <html:checkbox property="chk3"></html:checkbox>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label">4</label>
                                    <div class="col-md-4">
                                         <html:text alt="Mail 4" property="mail04" styleId="mail04"   styleClass="form-control input-sm "/>
                                    </div>
                                    <html:checkbox property="chk4"></html:checkbox>
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-3 control-label">5</label>
                                    <div class="col-md-4">
                                         <html:text alt="Mail 5" property="mail05" styleId="mail05"   styleClass="form-control input-sm "/>
                                    </div>
                                    <html:checkbox property="chk5"></html:checkbox>
                                </div>                                     
                            </div>
                        </div>
                        <div class="accion-line aright">                     
                             <button title="Confirmar" id="btnGrabar" name="btnGrabar" onclick="grabar();" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_CHECK%> Grabar</button>                                        
                        </div>                                  
                        <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
        <script type="text/javascript" src="js/funciones/jsMailResponsables.js"></script>
    </body>
</html:html>