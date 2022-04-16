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
                    CONTROL DE CAJAS
                    <ol class="breadcrumb">
                        <li><strong>Procesos</strong></li>
                        <li>CONTROL DE CAJAS</li>                                   
                    </ol>                            


                    <html:form action="/ControlCajaAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">   
                        <%@include file="jspf/msgs.jspf" %>
                        <c:choose>
                            <c:when test="${TrasladoForm.flagMueOcuForm=='muestra'}">      
                                <div class="panel panel-default">
                                    <div class="panel-heading">Buscar por</div>                                  
                                    <div class="panel-body">
                                        <div class="form-group">                                    
                                            <label class="col-md-2 control-label"># Boleta </label>
                                            <div class="col-md-3">
                                                <div class="input-group">
                                                    <html:text alt="Numero Boleta" property="boletaIngreso" styleId="boletaIngreso"    styleClass="form-control input-sm upper"  onkeypress="return buscarBoletaEnter(event)"   ></html:text>                                           
                                                </div>                                                                                       
                                            </div>
                                            <div class="col-md-2">
                                                 <input type="checkbox" id="cbox2" value="1" checked> <label for="cbox2">Pistola</label>    
                                            </div>                                                    
                                            <div class="col-md-2">    
                                                <button onclick="buscarBoletaFactura();"  id="btnBuscar"  name="btnBuscar" class="btn btn-sm btn-primary" type="button" title="Consultar"> Consulta</button>
                                            </div>                                                                                                     
                                            <div class="col-md-2">                                              
                                                <a href="ControlCajaAction.do?operacion=inicializa" title="Cancelar" class="btn btn-sm btn-danger goloading"> Cancelar</a> 
                                            </div>                                                
                                        </div>     
                                    </div>                                            
                                </div>
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                                <label class="col-md-2 control-label">Producto</label>
                                                <div class="col-md-3">
                                                <html:text alt="Numero Parte" property="numeroParte" styleId="numeroParte"  onkeypress="return buscaParteEnter(event)"  disabled="true"  styleClass="form-control input-sm upper"></html:text>
                                                </div>
                                                <div class="col-md-3">
                                                    <html:text alt="Descripcion" property="descripcion" styleId="descripcion"  readonly="true" styleClass="form-control input-sm"></html:text>
                                                </div>
                                                <label class="col-md-1 control-label">Cant.</label>
                                                <div class="col-md-1">
                                                    <html:text alt="#Cajas" property="cajas" styleId="cajas"   disabled="true" onkeypress="return cajasEnter(event)"  styleClass="form-control input-sm numeros"></html:text>
                                                </div>                                                
                                                <div class="col-md-2">                                                    
                                                    <button onclick="validaCantidad();" id="BtnAgragar" name="BtnAgregar" class="btn btn-sm btn-default" readonly="true" type="button" title="Consultar">Agregar</button>                                                
                                                </div>                                                                                    
                                        </div>                                        
                                    </div>
                                </div>                                                
                                <div id="c_tablasBoletasFactura"></div>
                                <div class="accion-line aright">                     

                                    
                                    <button title="Confirmar" id="btnConControl" name="btnConControl" onclick="grabarControl();" class="btn btn-sm btn-success" type="button" disabled="disabled"><%=Constantes.ICON_CHECK%> Genera Control</button>                                        
                                    

                                </div>
                                
                                
                                
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

                    <html:hidden property="operacion" styleId="operacion"></html:hidden>

                </html:form>
            </div>
        </div>
    </div>

    <%-- Scripts --%>
    <%@include file="jspf/js.jspf" %>  
    <script type="text/javascript" src="js/funciones/jsControlCajas.js"></script>           
</body>
</html:html>