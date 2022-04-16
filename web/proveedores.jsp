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
                        <li>Proveedor</li>
                    </ol>
                    <html:form action="/ProveedorAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Listado de Proveedores</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Periodo</label>
                                    <div class="col-md-1">
                                        <input type="text" alt="Periodo" id="periodo" name="periodo" value="<c:out value="${periodo}"/>"  maxlength="4" Class="form-control numeros">                                           
                                    </div> 
                                     <label class="col-md-1 control-label"></label>
                                     <div class="col-md-1">
                                         <button type="button" onclick="ConsultarProveedor();" class="btn btn-primary">Consultar</button>
                                     </div>
                                </div>
                                <div id="c_tablaProveedores"></div>   
                            </div>
                        </div>
                        <div class="accion-line aright">      
                            <button title="Confirmar" onclick="grabar();" class="btn btn-sm btn-success"   type="button" ><%=Constantes.ICON_CHECK%> Grabar</button>                             
                        </div>                          
                        <%-- Hiddens --%>
                        
                        
                        
                        <div class="modal fade" id="modalCuentasProveedor" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog  modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Proveedor con documentos pendientes</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <!-- <div class="panel panel-default"> -->
                                            <BR>
                                            <div class="panel panel-default">
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">BCP S/.</label>
                                                    <div class="col-md-4">
                                                        <html:text alt="BCP SOLES" property="bcpSoles" styleId="bcpSoles"  maxlength="30" styleClass="form-control numeros"></html:text>                                           
                                                    </div> 
                                                    <div class="col-md-3">
                                                        <html:select property="bcpsolTipo" styleId="bcpsolTipo"  styleClass="form-control input-sm">
                                                            <html:option value="0"> --- SELECCIONE --- </html:option>
                                                            <html:option value="A">Cuenta Ahorro</html:option>
                                                            <html:option value="C">Cuenta Corriente</html:option>
                                                            <html:option value="M">Cuenta Maestra</html:option>
                                                            <html:option value="B">Interbancaria</html:option>
                                                        </html:select>                                                      
                                                    </div>                                                    
                                                </div>                                       
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">BCP US$</label>
                                                    <div class="col-md-4">
                                                        <html:text alt="BCP DOLARES" property="bcpDolar" styleId="bcpDolar"  maxlength="30" styleClass="form-control numeros"></html:text>                                           
                                                    </div>                                                 
                                                    <div class="col-md-3">
                                                        <html:select property="bcpdolTipo" styleId="bcpdolTipo"  styleClass="form-control input-sm">
                                                            <html:option value="0"> --- SELECCIONE --- </html:option>
                                                            <html:option value="A">Cuenta Ahorro</html:option>
                                                            <html:option value="C">Cuenta Corriente</html:option>
                                                            <html:option value="M">Cuenta Maestra</html:option>
                                                            <html:option value="B">Interbancaria</html:option>
                                                        </html:select>                                                      
                                                    </div>                                                      
                                                </div>                                                 
                                            </div>
                                            
                                             <div class="panel panel-default">
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">BBVA S/.</label>
                                                    <div class="col-md-4">
                                                        <html:text alt="BBVA SOLES" property="bbvaSoles" styleId="bbvaSoles"  maxlength="30" styleClass="form-control numeros"></html:text>                                           
                                                    </div>    
                                                    <div class="col-md-3">
                                                        <html:select property="bbvasolTipo" styleId="bbvasolTipo"  styleClass="form-control input-sm">
                                                            <html:option value="0"> --- SELECCIONE --- </html:option>
                                                            <html:option value="A">Cuenta Ahorro</html:option>
                                                            <html:option value="C">Cuenta Corriente</html:option>
                                                            <html:option value="M">Cuenta Maestra</html:option>
                                                            <html:option value="B">Interbancaria</html:option>
                                                        </html:select>                                                      
                                                    </div>                                                     
                                                </div> 
                                                <div class="form-group">
                                                    <label class="col-md-2 control-label">BBVA US$</label>
                                                    <div class="col-md-4">
                                                        <html:text alt="BBVA DOLARES" property="bbvaDolar" styleId="bbvaDolar"  maxlength="30" styleClass="form-control numeros"></html:text>                                           
                                                    </div> 
                                                    <div class="col-md-3">
                                                        <html:select property="bbvadolTipo" styleId="bbvadolTipo"  styleClass="form-control input-sm">
                                                            <html:option value="0"> --- SELECCIONE --- </html:option>
                                                            <html:option value="A">Cuenta Ahorro</html:option>
                                                            <html:option value="C">Cuenta Corriente</html:option>
                                                            <html:option value="M">Cuenta Maestra</html:option>
                                                            <html:option value="B">Interbancaria</html:option>
                                                        </html:select>                                                      
                                                    </div>                                                     
                                                </div>    
                                                    
                                            </div>
                                        <!--
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">INTERBANK ICC</label>
                                                <div class="col-md-4">
                                                    <html:text alt="INTERBANK" property="interbankICC" styleId="interbankICC"  maxlength="30" styleClass="form-control numeros"></html:text>                                           
                                                </div>                                                 
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">SCOTIABANK ICC</label>
                                                <div class="col-md-4">
                                                    <html:text alt="SCOTIABANK" property="scotiabankICC" styleId="scotiabankICC"  maxlength="30" styleClass="form-control numeros"></html:text>                                           
                                                </div>                                                 
                                            </div>  
                                        -->        
                                                
                                        <!--  </div>   -->
                                        <div class="accion-line aright">                     
                                             <button title="Grabar" id="btnGrabar" name="btnGrabar" onclick="grabarCuentaProveedor();" class="btn btn-sm btn-primary" type="button" ><%=Constantes.ICON_ENTER %>Grabar</button>                                        
                                        
                                             <button title="Cerrar" id="btnCerrar" name="btnCerrar" onclick="cerrarCuentaProveedor();" class="btn btn-sm btn-danger" type="button" ><%=Constantes.ICON_EXIT %>Cerrar</button>                                        
                                        </div>                                                                         
                                    </div>           
                                </div>
                            </div>      
                        </div>  
                                        
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                        <html:hidden property="proceso" styleId="proceso"></html:hidden>
                        
                        <input  type="hidden" id="codigo" name="codigo">
                        
                        <input  type="hidden" id="xx" >
                     </html:form>
                    </div>
                </div>
            </div>

            <%-- Scripts --%>
            <%@include file="jspf/js.jspf" %>   
            <script type="text/javascript" src="js/funciones/jsProveedor.js"></script>
    </body>
</html:html>