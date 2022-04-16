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
                    <c:choose>
                        <c:when test="${RegistroVentasForm.flagMueOcuForm=='muestraCajero'}"> 
                            <ol class="breadcrumb">
                                <li><strong>Generales</strong></li>
                                <li>Migrar Cobranzas</li>
                            </ol>
                            
                        </c:when>
                        <c:when test="${RegistroVentasForm.flagMueOcuForm=='muestraVentas'}"> 
                            <ol class="breadcrumb">
                                <li><strong>Generales</strong></li>
                                <li>Migrar Registro de Ventas</li>
                            </ol>                            
                        </c:when>                         
                    </c:choose>
                    <html:form action="/RegistroVentasAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <c:choose>
                            <c:when test="${RegistroVentasForm.flagMueOcuForm=='muestraCajero'}"> 
                                <div class="panel panel-default">
                                    <div class="panel-heading">Buscar por</div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">De</label>
                                            <div class="col-md-2">
                                                <html:text alt="De" property="fechaIni" styleId="fechaIni"    styleClass="form-control input-sm"></html:text>                                           
                                            </div>                                                                              
                                            <label class="col-md-1 control-label">Hasta</label>
                                            <div class="col-md-2">
                                                <html:text alt="Hasta" property="fechaFin" styleId="fechaFin"    styleClass="form-control input-sm"></html:text>                                           
                                            </div> 
                                            <label class="col-md-1 control-label">Secuencia</label>
                                            <div class="col-md-2">
                                            <html:text alt="Secuencia" property="secuencia" styleId="secuencia"  maxlength="5"  styleClass="form-control input-sm numeros"></html:text>                                           
                                            </div>                                             
                                            
                                            <div class="col-md-2">    
                                                <button onclick="muestraListaCajeroMigrar();" class="btn btn-sm btn-default" type="button"  title="Consultar">                                                    
                                                       Consultar
                                                </button>
                                            </div> 
                                            
                                            
                                            
                                        </div>                                          
                                    </div>
                                </div>
                                <div id="c_tablaRegistroVenta"></div>                      
                                <div class="accion-line aright">      
                                    <button title="Confirmar" id="btnConAsientoCajero" class="btn btn-sm btn-success"   type="button" ><%=Constantes.ICON_CHECK%> Genera Asientos Cobranzas</button>                                                                                
                                <button title="Eliminar" id="btnEliminarVentas" onclick="EliminarAsientos(1);" class="btn btn-sm btn-success"   type="button" ><%=Constantes.ICON_CLEAR %> Eliminar Asientos Cobramzas</button>                                                                                
                                </div>                                 
                            </c:when>
                            <c:when test="${RegistroVentasForm.flagMueOcuForm=='muestraVentas'}"> 
                                <div class="panel panel-default">
                                    <div class="panel-heading">Buscar por</div>
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-1 control-label">De</label>
                                            <div class="col-md-2">
                                                <html:text alt="De" property="fechaIni" styleId="fechaIni"   onkeypress="return buscarProductoEnter(event)" styleClass="form-control input-sm upper"></html:text>                                           
                                            </div>                                                                              
                                            <label class="col-md-1 control-label">Hasta</label>
                                            <div class="col-md-2">
                                                <html:text alt="Hasta" property="fechaFin" styleId="fechaFin"   onkeypress="return buscarProductoEnter(event)" styleClass="form-control input-sm upper"></html:text>                                           
                                            </div>    
                                            <div class="col-md-2">    
                                                <button onclick="muestraListaRegistroVentasMigrar();" class="btn btn-sm btn-default" type="button" title="Consultar">                                                    
                                                       Consultar
                                                </button>
                                            </div> 
                                            <label class="col-md-2 control-label">Solo pendientes</label>
                                            <input type="checkbox" id="chkpendientes" name="chkpendientes" value="newsletter">
                                        </div>                                          
                                    </div>
                                </div>
                                <div id="c_tablaRegistroVenta"></div>                      
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">TOTAL NETO</label>
                                            <div class="col-md-2">
                                                <input type="text" id="neto" name="neto" value="0.00"  readonly="true"  class="form-control input-sm upper  text-right" >
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">TOTAL IGV</label>
                                            <div class="col-md-2">
                                                <input type="text" id="igv" name="igv" value="0.00"  readonly="true"  class="form-control input-sm upper  text-right">
                                            </div>
                                        </div>  
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">TOTAL</label>
                                            <div class="col-md-2">
                                                <input type="text" id="total" name="total" value="0.00" readonly="true" class="form-control input-sm upper  text-right">
                                            </div>
                                        </div>  
                                    </div>
                                </div>
                                <div class="accion-line aright">      
                                    <button title="Confirmar" id="btnConAsientoVentas" class="btn btn-sm btn-success"   type="button" ><%=Constantes.ICON_CHECK%> Genera Asientos Ventas</button> 
                                    <button title="Eliminar" id="btnEliminarVentas" onclick="EliminarAsientos(2);" class="btn btn-sm btn-success"   type="button" ><%=Constantes.ICON_CLEAR %> Eliminar Asientos Ventas</button>                                                                                
                                </div>                                                                                                              
                            </c:when>
                            <c:otherwise> 
                                <div class="accion-line aright">
                                </div>                                
                            </c:otherwise>           
                        </c:choose>
                                 
                        <div class="modal fade" id="confirm-delete" aria-hidden="true">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h4 class="modal-title">Eliminar asientos</h4>
                                    </div>
                                    <div class="modal-body">
                                        <label>¿Estás seguro de eliminar los asientos?</label>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-default btn-ok" data-dismiss="modal">Sí</button>
                                        <a class="btn btn-danger" data-dismiss="modal">No
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                                
                                
                                
                        <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                        <html:hidden property="docSelected" styleId="docSelected"  ></html:hidden>                        
                        <html:hidden property="pendientes" styleId="pendientes"  ></html:hidden>    
                        <html:hidden property="proceso" styleId="proceso"  ></html:hidden>    
                        
                         <html:hidden property="docSelectedDel" styleId="docSelectedDel"  ></html:hidden>    
                    </html:form>
                        
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
        <script type="text/javascript" src="js/funciones/jsRegistroVenta.js"></script>
    </body>
</html:html>