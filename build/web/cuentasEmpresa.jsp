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
                        <li><strong>Gestion bancaria</strong></li>
                        <li>Cuentas bancarias de la empresa</li>
                    </ol>
                    <html:form action="/ProveedorAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Cuentas banco BCP</div>
                            <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">BCP S/.</label>
                                            <div class="col-md-4">
                                                <html:text alt="BCP SOLES" property="bcpSoles" styleId="bcpSoles"  maxlength="20" styleClass="form-control numeros"></html:text>                                           
                                            </div> 
                                            <div class="col-md-3">
                                                <html:select property="bcpsolTipo" styleId="bcpsolTipo"  styleClass="form-control input-sm">
                                                    <html:option value="C">Cuenta Corriente</html:option>
                                                    <html:option value="M">Cuenta Maestra</html:option>
                                                </html:select>                                                      
                                            </div>                                                    
                                        </div>                                       
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">BCP US$</label>
                                            <div class="col-md-4">
                                                <html:text alt="BCP DOLARES" property="bcpDolar" styleId="bcpDolar"  maxlength="20" styleClass="form-control numeros"></html:text>                                           
                                            </div>                                                 
                                            <div class="col-md-3">
                                                <html:select property="bcpdolTipo" styleId="bcpdolTipo"  styleClass="form-control input-sm">
                                                    <html:option value="C">Cuenta Corriente</html:option>
                                                    <html:option value="M">Cuenta Maestra</html:option>
                                                </html:select>                                                      
                                            </div>                                                      
                                        </div>
                                                    
                                        <div class="accion-line aright">                     
                                             <button title="Grabar" id="btnGrabar" name="btnGrabar" onclick="grabarCuentaEmpresa();" class="btn btn-sm btn-primary" type="button" ><%=Constantes.ICON_ENTER %>Grabar</button>                                        
                                             <a href="ProveedorAction.do?operacion=inicializaCuentaEmpresa"  class="btn btn-sm btn-danger"><%=Constantes.ICON_EXIT %>Cancelar</a>
                                        </div>                                                      
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
         <script type="text/javascript" src="js/funciones/jsProveedor.js"></script>
    </body>
</html:html>