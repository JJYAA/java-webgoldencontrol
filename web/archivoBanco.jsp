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
                        <li>Archivos de bancos</li>
                    </ol>
                    <html:form action="/ArchivoBancoAction" styleId="frm_generico" method="POST"  enctype="multipart/form-data" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                       
                        <c:choose>
                            <c:when test="${ArchivoBancoForm.flagMueOcuForm=='muestra'}">                                                                          
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
                                                    <button onclick="muestraDocumentoCancelados();" id="btnCancelaciones" name="btnCancelaciones" class="btn btn-default" type="button" title="Consultar">                                                    
                                                              Consultar
                                                    </button>                                                                                            
                                                </div>                                                
                                            </div>
                                    </div>
                                </div>
                                <div id="c_tablaListaDocCancelados"></div>                      
                                <div class="accion-line aright">      
                                    <a href="ArchivoBancoAction.do?operacion=inicializa" class="btn btn-danger"><%=Constantes.ICON_CHECK%> Cancelar</a>
                                  <!--  <button title="Confirmar" id="btnGrabarPago" name="btnGrabarPago" onclick="grabartxtBanco();" class="btn btn-success"   type="button" ><%=Constantes.ICON_CHECK%> Generar txt Banco</button>   -->
                                </div>  

                            </c:when>  
                            <c:otherwise>
                                <div class="accion-line aright">
                                    
                                </div>
                            </c:otherwise>                                  
                                
                        </c:choose>
                        
                       <div class="modal fade" id="EliminarAsiento" data-backdrop="static"  tabindex="-1" role="dialog">                
                        
                           <div class="modal-dialog">
                              <div class="modal-content">
                                 <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">¿Estás seguro?</h4>
                                 </div>
                                 <div class="modal-body">
                                    <p>¿Seguro que quieres ELIINAR el asiento?</p>
                                    <p class="text-warning"><small>Si lo borras, nunca podrás recuperarlo.</small></p>
                                 </div>
                                 <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    <button type="button" class="btn btn-danger"  onclick="EliminarVoucherPlanilla();" >Eliminar</button>
                                 </div>
                              </div>
                           </div>
                        </div>                                    
                                              
                                    <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                        <html:hidden property="selected" styleId="selected"></html:hidden>
                        <html:hidden property="bancoMoneda" styleId="bancoMoneda"></html:hidden>
                        <html:hidden property="rucProveedor" styleId="rucProveedor"></html:hidden>
                        <html:hidden property="planilla" styleId="planilla"></html:hidden>  
                        
                        
                        <input type="hidden" id="txtpla"  name="txtpla">
                        <input type="hidden" id="txtmes"  name="txtmes">
                        <input type="hidden" id="txtanho"  name="txtanho">
                        <input type="hidden" id="txttipocomprobante"  name="txttipocomprobante">
                         <input type="hidden" id="txtasiento"  name="txtasiento">
                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
       <script type="text/javascript" src="js/funciones/jsArchivoBanco.js"></script>
    </body>
</html:html>