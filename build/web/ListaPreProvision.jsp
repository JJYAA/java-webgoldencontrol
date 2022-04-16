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
                        <li><strong>Procesos</strong></li>
                        <li>Listado documentos</li>
                    </ol>
                    <html:form action="/PreProvisionAction" styleId="frm_generico" method="POST"  enctype="multipart/form-data" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Buscar por</div>
                            <div class="panel-body">    
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Por</label> 
                                    <div class="col-md-2">
                                        <html:select property="formaPago" styleId="formaPago"  styleClass="form-control input-sm">
                                            <html:option value="1">Fecha de documento</html:option>                                            
                                            <html:option value="2">Fecha de registro</html:option>
                                            <html:option value="3">Fecha de contable</html:option>
                                        </html:select>                                          
                                    </div>                                
                                </div>
                                
                                <div class="form-group">
             
                                    <label class="col-md-1 control-label">De</label>
                                    <div class="col-md-2">                                        
                                        <html:text alt="De" property="fechaIni" styleId="fechaIni"    styleClass="form-control input-sm"></html:text>                                           
                                    </div>                                                                              
                                    <label class="col-md-1 control-label">Hasta</label>
                                    <div class="col-md-2">
                                        <html:text alt="Hasta" property="fechaFin" styleId="fechaFin"    styleClass="form-control input-sm"></html:text>                                           
                                    </div>   
                                    <div class="col-md-1">    
                                        <button onclick="muestraPreProvisiones_TMP();" id="btn_accion" class="btn btn-sm btn-default" type="button" title="Consultar">                                                    
                                               Consultar
                                        </button>  
                                        
                                      
                                    </div>
                                    <!--
                                    <div class="col-md-1">F Contable</div>
                                    <div class="col-md-2">
                                        <div> <input type="checkbox" id="chkFecha" disabled="true"  styleClass="form-control input-sm"></div>
                                    </div>
                                    -->
                                </div>
                                <div class="form-group">
                                                <label class="col-md-1 control-label">Fecha Contable</label>
                                                <div class="col-md-2">
                                                    <input type="text" id="txtfechaContable" name="txtfechaContable" class="form-control input-sm" >                                                        
                                                </div>
                                </div>
                                <!--
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Secuencia</label>
                                    <div class="col-md-2">
                                            <html:text alt="Secuencia" property="secuencia" styleId="secuencia"  maxlength="5"  styleClass="form-control input-sm numeros"></html:text>                                           
                                    </div>                                                                          
                                </div>    
                                -->       
                            </div>
                        </div>
                        <div id="c_tablaListaPreProvision"></div>                      
                        <div class="accion-line aright">      
                            <button title="Confirmar" onclick="generaAsiento();" class="btn btn-sm btn-success"   type="button" ><%=Constantes.ICON_CHECK%> Generar provision</button>                             
                        </div>   
                        <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                        
                        
                        
                        <div class="modal fade" id="modalAdicionar" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog  modal-lg" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Adiconar</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">

                                        <div class="panel panel-default">
                                             <div class="panel-heading">Datos del Comprobante</div>
                                             <div class="form-group">
                                                <label class="col-md-2 control-label">Tipo Documento</label>
                                                <div class="col-md-2">
                                                    <html:select disabled="disabled"  property="tipoDocumento" styleId="tipoDocumento"  styleClass="form-control input-sm">
                                                            <html:option value="01">FACTURA</html:option>
                                                            <html:option value="02">RECIBOS POR HONORARIO</html:option>
                                                        </html:select>
                                                </div> 
                                                <label class="col-md-2 control-label"><b>FECHA CONTABLE</b></label>
                                                <div class="col-md-2">
                                                        <div class="input-group">
                                                        <html:text alt="Fecha contable"   property="fechaContable" styleId="fechaContable"   styleClass="form-control input-sm"></html:text>                                           
                                                        </div>                                                     
                                                </div>    
                                                        <%--
                                                <label class="col-md-2 control-label"><b>FECHA PAGO</b></label>
                                                <div class="col-md-2">
                                                        <div class="input-group">
                                                        <html:text alt="Fecha Pago" readonly="true"  property="fechaPago" styleId="fechaPago"   styleClass="form-control input-sm upper"></html:text>                                           
                                                        </div>                                                     
                                                </div>    
                                                    --%>
                                             </div>
                                             <div class="form-group">
                                                    <label class="col-md-2 control-label">Serie</label>
                                                    <div class="col-md-2">
                                                        <html:text alt="Serie"  readonly="true" property="serieDoc" styleId="serieDoc"   maxlength="4"  styleClass="form-control input-sm upper"></html:text>
                                                    </div>
                                                    <label class="col-md-2 control-label">Documento</label>
                                                    <div class="col-md-2">
                                                        <html:text alt="Documento"  readonly="true" property="numeroDoc" styleId="numeroDoc"  maxlength="8"   styleClass="form-control input-sm numeros"></html:text>
                                                    </div>    
                                                    <label class="col-md-2 control-label">Gasto</label>
                                                    <div class="col-md-2">                                                                                                                               
                                                    <html:select property="tipoGasto" styleId="tipoGasto" onchange="ChangeItemGasto();"   styleClass="form-control input-sm">
                                                             <html:option value="">-SELECCIONE-</html:option>
                                                             <html:options collection="listaTipoGastos" labelProperty="descripcion" property="indice" />
                                                        </html:select>                                                                 
                                                    </div>

                                             </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Moneda</label>
                                                <div class="col-md-2">
                                                        <html:select  property="moneda" styleId="moneda" disabled="disabled"   styleClass="form-control input-sm">
                                                            <html:option value="1">SOLES</html:option>
                                                            <html:option value="2" >DOLARES</html:option>
                                                        </html:select>
                                                </div>  
                                                <label class="col-md-2 control-label">Fecha Documento</label>
                                                <div class="col-md-2">
                                                        <div class="input-group">
                                                        <html:text alt="Fecha"  disabled="true" property="fechaDocumento" styleId="fechaDocumento"   styleClass="form-control input-sm upper"></html:text>                                           
                                                        </div>                                                     
                                                </div>                                                    
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Glosa</label>
                                                <div class="col-md-8">
                                                      <html:text alt="Glosa" property="glosa" styleId="glosa"  maxlength="60"   styleClass="form-control input-sm upper"></html:text>
                                                </div>                                           
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Cuenta contable</label>
                                                <div class="col-md-5">
                                                    <html:select property="cuentaGasto" styleId="cuentaGasto"    styleClass="form-control input-sm">
                                                        <html:options collection="listaGastos" labelProperty="descripcion" property="indice" />
                                                    </html:select>   
                                                </div>  
                                                <div class="col-md-2">
                                                      <input type="text" id="txtimporte"  maxlength="8"   Class="form-control input-sm decimal-2">
                                                </div> 
                                                <div class="col-md-2">
                                                     <button onclick="adicionarGastos();" class="btn btn-primary" type="button" title="Consultar">+</button>                                                 
                                                </div>                                                 
                                            </div>   
                                            <div id="c_tablaAddGastos">
                                                    
                                            </div> 
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Gravada</label>
                                                <div class="col-md-1">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="chkgravado">                                                        
                                                    </div>                                                    
                                                </div>  
                                                <label class="col-md-1 control-label">%IGV</label>
                                                <div class="col-md-1">                                                    
                                                    <html:text alt="% IGV" property="porIgv" styleId="porIgv"  maxlength="5"   styleClass="form-control input-sm decimal-2"></html:text> 
                                                </div>
                                                <label class="col-md-2 control-label">Retenci&oacute;n %</label>
                                                <div class="col-md-1">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="chkretencion">                                                        
                                                    </div>                                                     
                                                </div>
                                                <div class="col-md-1">
                                                     <html:text alt="%" property="porRetencion" styleId="porRetencion"  maxlength="5"   styleClass="form-control input-sm decimal-2"></html:text> 
                                                </div>                                                
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Base Imponible</label>
                                                <div class="col-md-2">
                                                      <html:text alt="BaseImponible"  readonly="true" property="baseImponible" styleId="baseImponible"  maxlength="8"   styleClass="form-control input-sm decimal-2"></html:text>
                                                </div>                                                                                                                                             
                                            </div> 
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">HBL</label>
                                                <div class="col-md-2">
                                                      <html:text alt="HBL" property="hbl" styleId="hbl"  maxlength="30"   styleClass="form-control input-sm upper"></html:text>
                                                </div> 
                                                <label class="col-md-2 control-label">DUA</label>
                                                <div class="col-md-2">
                                                      <html:text alt="DUA" property="dua" styleId="dua"  maxlength="30"   styleClass="form-control input-sm upper"></html:text>
                                                </div>                                         
                                                <label class="col-md-2 control-label">POLIZA</label>
                                                <div class="col-md-2">
                                                      <html:text alt="POLIZA" property="poliza" styleId="poliza"  maxlength="10"   styleClass="form-control input-sm upper"></html:text>
                                                </div>                                                  
                                            </div>                                                  
                                        </div>
                                        <div class="panel panel-default">                                       
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">PDF</label>
                                                <div class="col-md-7">
                                                    <html:file alt="PDF" property="theFilePDF"  disabled="true" styleId="theFilePDF"   styleClass="form-control input-sm"/>

                                                </div>
                                                <div class="col-md-1">
                                                    <div id="visualizaPdf">
                                                        <a  href="#" onclick="muestraArchivo('PDF')"><span class="glyphicon glyphicon-camera"></span></a> 
                                                    </div>                                                  
                                                </div>
                                             </div>                                        
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">XML</label>
                                                <div class="col-md-7">
                                                    <html:file alt="XML" property="theFileXLS"  disabled="true"  styleId="theFileXLS"   styleClass="form-control input-sm"/>                                            
                                                </div>
                                                <div class="col-md-1">
                                                    <div id="visualizaXml">
                                                        <a  href="#" onclick="muestraArchivo('XML')"><span class="glyphicon glyphicon-camera"></span></a> 
                                                    </div>                                                
                                                </div>                                               
                                             </div>    
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">CDR</label>
                                                <div class="col-md-7">
                                                    <html:file alt="CDR" property="theFileCDR" disabled="true"  styleId="theFileCDR"   styleClass="form-control input-sm"/>
                                                </div> 
                                                <div class="col-md-1">
                                                    <div id="visualizaCdr">
                                                        <a  href="#" onclick="muestraArchivo('CDR')"><span class="glyphicon glyphicon-camera"></span></a> 
                                                    </div>                                                
                                                </div>                                              
                                             </div>                                             
                                        </div>  
                                        <div class="panel panel-default">
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Enviar MAIL</label>
                                                <div class="col-md-1">
                                                    <div class="form-check">
                                                        <input type="checkbox" class="form-check-input" id="chkMail">                                                        
                                                    </div>                                                     
                                                </div> 
                                                <div class="col-md-7">
                                                    <html:text alt="Nota"  property="nota" styleId="nota"  maxlength="80"   styleClass="form-control input-sm"></html:text>
                                                </div>
                                            </div>
                                        </div>       
                                        <div class="accion-line aright">                     
                                                <button title="Confirmar" id="btnConAdicionar" name="btnConAdicionar" onclick="grabarPreProvision();" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_CHECK%> Actualiza PreProvisi&oacute;n</button>                                        
                                        </div>                                                                         
                                    </div>           
                                </div>
                            </div>      
                        </div>   
                                        
                                        
                                        

                        <div class="modal fade" id="EliminarModal" data-backdrop="static"  tabindex="-1" role="dialog">                
                        
                           <div class="modal-dialog">
                              <div class="modal-content">
                                 <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                    <h4 class="modal-title">¿Estás seguro?</h4>
                                 </div>
                                 <div class="modal-body">
                                    <p>¿Seguro que quieres borrar este elemento?</p>
                                    <p class="text-warning"><small>Si lo borras, nunca podrás recuperarlo.</small></p>
                                 </div>
                                 <div class="modal-footer">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Cerrar</button>
                                    <button type="button" class="btn btn-danger"  onclick="Eliminar();" >Eliminar</button>
                                 </div>
                              </div>
                           </div>
                        </div> 
                                        
                                        
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
                                    <button type="button" class="btn btn-danger"  onclick="EliminarVoucher();" >Eliminar</button>
                                 </div>
                              </div>
                           </div>
                        </div>                                            
                                        

                    <input type="hidden" id="pRucProveedor" name="pRucProveedor" />
                    <input type="hidden" id="pIndice" name="pIndice" />
                    <input type="hidden" id="pTipoDocumento" name="pTipoDocumento" />
                    <input type="hidden" id="pSerie" name="pSerie" />
                    <input type="hidden" id="pDocumento" name="pDocumento" />
                    <input type="hidden" id="pMes" name="pMes" />
                    <input type="hidden" id="pAsiento" name="pAsiento" />
                    <input type="hidden" id="pTotalGasto" name="pAsiento" />
                    <input type="hidden" id="pTipoComprobante" name="pTipoComprobante" />
                    <input type="hidden" id="pAnho" name="pAnho" />                    
                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
       <script type="text/javascript" src="js/funciones/jsListaPreProvision.js?v4.0.0.1"></script>
               
             
                                                        
    </body>
</html:html>