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
                        <li><strong>Gesti&oacute;n de Bancos</strong></li>
                        <li>Programacion y Pagos de documentossssssssssssssss</li>
                    </ol>
                    <html:form action="/ProgramaPagosAction" styleId="frm_generico" method="POST"  enctype="multipart/form-data" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                   
                        <c:choose>
                            <c:when test="${ProgramaPagosForm.flagMueOcuForm=='muestra'}">    
                                <div class="panel panel-default">
                                    <div class="panel-heading">Buscar por</div>
                                    <div class="panel-body">
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Proveedor</label>
                                                <div class="col-md-2">
                                                    <html:text alt="Proveedor" property="rucProveedor" styleId="rucProveedor"    styleClass="form-control input-sm"></html:text>                                           
                                                </div> 
                                                <label class="col-md-1 control-label">Cuenta</label>     
                                                <div class="col-md-3">
                                                    <html:select property="formaPago" styleId="formaPago"  styleClass="form-control input-sm">
                                                        <html:option value="07">BCP S/</html:option>
                                                        <html:option value="08">BCP US$</html:option>
                                                        <html:option value="09">BBVA S/</html:option>
                                                        <html:option value="10">BBVA US$</html:option>
                                                    </html:select>                                                                                       
                                                </div>      
                                                <div class="col-md-4">
                                                    <button onclick="muestraDocumentoPendientes();" id="buscarPendientes" name="buscarPendientes" class="btn btn-default" type="button" title="Consultar">                                                    
                                                              Consultar
                                                    </button>                                         
                                                    <button title="Help" onclick="HelpDocumentosPendientes();" class="btn btn-default"   type="button" ><%=Constantes.ICON_SEARCH %> Pendientes</button>                                                                    
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Fecha Contable</label>
                                                <div class="col-md-2">
                                                        <html:text alt="De" property="fechaContable" styleId="fechaContable"    styleClass="form-control input-sm"></html:text>                                           
                                                </div>
                                                 <label class="col-md-2 control-label">NRO ASIENTO</label>   
                                                 <div class="col-md-2">
                                                     <input  type="text" id="asiento"  name="asiento" maxlength="5" class="form-control input-sm numeric">
                                                 </div>                                                 
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Glosa</label>
                                                <div class="col-md-6"> 
                                                    <input  type="text" id="txtglosa"  name="txtglosa" maxlength="60" class="form-control input-sm upper">                                               
                                                </div>                                         
                                            </div>
                                    </div>
                                </div>
                                <div id="c_tablaListaDocPendientes"></div>                      
                                <div class="accion-line aright">      

                                    <a href="ProgramaPagosAction.do?operacion=inicializa" class="btn btn-danger"><%=Constantes.ICON_CHECK%> Cancelar</a>
                                    <button title="Confirmar" id="btnGrabarPago" name="btnGrabarPago" onclick="grabarPago();" class="btn btn-success"   type="button" ><%=Constantes.ICON_CHECK%> Generar Asiento de Pago</button>                             
                                </div>                                  
                            </c:when>  
                            <c:otherwise>
                                <div class="accion-line aright">
                                    
                                </div>
                            </c:otherwise>                                  
                                
                        </c:choose>                        
                        
                                
                                    
                                    <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                        
                        
                        
                        <div class="modal fade" id="modalHelpDocPendientes" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog  modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Proveedor con documentos pendientes</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="panel panel-default"> 
                                            <div id="c_tablaHelpProveedorDocPendientes"></div>   
                                        </div>   
                                        <div class="accion-line aright">                     
                                             
                                             <button title="Cerrar" id="btnConAdicionar" name="btnCerrar" onclick="cerrarHelpProvDocPendientes();" class="btn btn-sm btn-danger" type="button" ><%=Constantes.ICON_EXIT %>Cerrar</button>                                        
                                        </div>                                                                         
                                    </div>           
                                </div>
                            </div>      
                        </div>   
                                     

                        <input type="hidden" id="pRucProveedor" name="pRucProveedor" />
                        <input type="hidden" id="pIndice" name="pIndice" />
                        <input type="hidden" id="pTipoDocumento" name="pTipoDocumento" />
                        <input type="hidden" id="pSerie" name="pSerie" />
                        <input type="hidden" id="pDocumento" name="pDocumento" />
                        <html:hidden  property="formaPagotxt" styleId="formaPagotxt" ></html:hidden>                                                                                   

                        <html:hidden  property="glosa" styleId="glosa" ></html:hidden> 
                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
       <script type="text/javascript" src="js/funciones/jsProgramaPagos.js?v1.2"></script>
    </body>
</html:html>