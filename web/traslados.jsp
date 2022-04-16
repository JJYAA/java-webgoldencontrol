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
                    AQUIIIIIIIIIIII22222222222
                    <ol class="breadcrumb">
                        <li><strong>Procesos</strong></li>
                        <li>TRASLADOS DE ALMACEN</li>                                   
                    </ol>                            


                    <html:form action="/TrasladoAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">   
                        <%@include file="jspf/msgs.jspf" %>
                        <c:choose>
                            <c:when test="${TrasladoForm.flagMueOcuForm=='muestra'}">      
                                <div class="panel panel-default">
                                    <div class="panel-heading">Datos del cliente</div>                                        
                                    <div class="panel-body">
                                        <div class="form-group">

                                            <label class="col-md-2 control-label">Almacen origen</label>
                                            <div class="col-md-3">
                                                <html:select property="almacenOrigen" styleId="almacenOrigen"
                                                             styleClass="form-control input-sm">
                                                    <html:option value="">--SELECCIONE--</html:option>
                                                    <%--
                                                    <html:options collection="listarTipoPersona"  labelProperty="descripcion" property="indice" />
                                                    --%>
                                                </html:select>
                                            </div>
                                            <label class="col-md-2 control-label">Almacen Destino</label>
                                            <div class="col-md-3">
                                                <html:select property="almacenDestino" styleId="almacenDestino"
                                                             styleClass="form-control input-sm">
                                                    <html:option value="">--SELECCIONE--</html:option>
                                                    <%--
                                                    <html:options collection="listarTipoPersona" labelProperty="descripcion" property="indice" />
                                                    --%>
                                                </html:select>
                                            </div>

                                        </div>                                            
                                        <div class="form-group">                                                                                                                                                
                                            <div class="col-md-3">   
                                                <div class="input-group">             
                                                        <%--
                                                        <html:text alt="Numero Parte" property="numeroParte" styleId="numeroParte" onkeypress="return buscaParte(event)" readonly="false" styleClass="form-control input-sm upper"></html:text>                                                                                                                
                                                            <span class="input-group-btn">
                                                                <button title="Buscar" type="button" id="btnBuscarCliNroDoc" onclick="buscarProductoEnter();" class="btn btn-sm btn-default"><%=Constantes.ICON_SEARCH%></button>
                                                        </span>
                                                        --%>
                                                        <label class="control-label">C&oacute;digoArt&iacute;culo</label>
                                                        <div class="input-group">
                                                            <input type="text" name="numeroParte" id="numeroParte"
                                                                   value="" placeholder="C&oacute;digo Art&iacute;culo"
                                                                   class="form-control input-sm upper"
                                                                   onkeypress="return buscaParte(event)" /> 
                                                            <span
                                                                   class="input-group-btn">
                                                                <button class="btn btn-sm btn-default"
                                                                        id="btnAbreModalHelpArt" onclick="openModalHelpArticulo();"
                                                                        type="button" title="Help de Artículo"><%=Constantes.ICON_MENU%></button>
                                                            </span>
                                                        </div>                                                    
                                                    
                                                </div>
                                        </div>                       
                                        <div class="col-md-3">
                                            <label class="control-label" for="descripcion">Descripci&oacute;n</label>
                                            <html:text alt="Descripcion" property="descripcion" styleId="descripcion" readonly="true" styleClass="form-control input-sm"></html:text>
                                        </div>
                                        <div class="col-md-1">
                                            <label class="control-label" for="Cajas">Cajas</label>
                                            <html:text alt="Cajas" property="cajas" styleId="cajas" readonly="true" styleClass="form-control input-sm"></html:text>
                                        </div>                                               
                                        <div class="col-md-1">
                                            <label class="control-label" for="Total">Total</label>
                                            <html:text alt="Total" property="stockTotal" styleId="stockTotal" readonly="true" styleClass="form-control input-sm"></html:text>
                                        </div>
                                        <div class="col-md-1">
                                            <label class="control-label" for="Disp">Disp.</label>
                                            <html:text alt="Disp." property="stockDis" styleId="stockDis" readonly="true" styleClass="form-control input-sm"></html:text>
                                        </div>                                             
                                        <div class="col-md-1">
                                            <label class="control-label" for="Cant">Cant.</label>
                                            <html:text alt="Cant." property="cantidad" styleId="cantidad" readonly="true" styleClass="form-control input-sm"></html:text>
                                        </div>
                                    </div>
                                    <div id="c_tablaPartesTempo"></div>        
                                        
                                    


                            </c:when>
                            <c:otherwise>
                                <div class="accion-line aright">
                                    <div id="c_visualizaPDF"></div>
                                    <%--
                                    <a href="FacRepMostradorAction.do?operacion=iniFacRepMostrador" title="Regresar" class="btn btn-sm btn-warning goloading"><%=Constantes.ICON_REPLY%></i><span class="icono-izq">Regresar</span></a>
                                    --%>
                                </div>
                            </c:otherwise>                              
                        </c:choose>    

                        <html:text property="operacion" styleId="operacion"></html:text>

                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>  
        <script type="text/javascript" src="js/funciones/jsPedidoOfertaRep.js"></script>           
    </body>
</html:html>