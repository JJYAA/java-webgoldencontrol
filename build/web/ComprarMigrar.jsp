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
                        <li><strong>Contabilidad</strong></li>
                        <li> Migrar compras</li>
                    </ol>
                    <html:form action="/ComprasMigrarAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Listado de polizas</div>
                            <div class="panel-body">
                                <div class="row">
                                  <div class="col-md-4">
                                        <button title="Confirmar" id="btnConFacRep" onclick="agregarPoliza();"    type="button" ><%=Constantes.ICON_ADD%> </button>                                              
                                        <div id="c_tablaListaPolizas"></div>   
                                  </div>
                                  <div class="col-md-8">   
                                      <div class="panel panel-default">
                                            <div class="panel-heading">Detalle de poliza</div>
                                            <br>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Poliza</label>
                                                <div class="col-md-3"> 
                                                    <input type="text" readonly="true"  alt="Poliza" id="auxpoliza" name="auxpoliza"    Class="form-control input-sm text-right">
                                                </div>                                              
                                                <label class="col-md-2 control-label">Asiento</label>
                                                <div class="col-md-3"> 
                                                    <input type="text" readonly="true"  alt="ASIENTO" id="txtasiento" name="txtasiento"    Class="form-control input-sm text-right">
                                                </div>                                                    
                                            </div> 
                                            <div class="form-group">                                            
                                                <label class="col-md-2 control-label">Monto FOB</label>
                                                <div class="col-md-3"> 
                                                    <input type="text" readonly="true"  alt="FOB" id="txtfob" name="txtfob"    Class="form-control input-sm text-right">
                                                </div>       
                                                <label class="col-md-2 control-label">F.Contable</label>
                                                <div class="col-md-3"> 
                                                    <input type="text"  alt="F. Contable" id="auxtfechacontable" name="auxtfechacontable"    Class="form-control input-sm text-right">
                                                </div>                                                   
                                            </div>                                             
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">S/.</label>
                                                <div class="col-md-3">
                                                    <input type="text" readonly="true"  alt="Soles" id="txtsol" name="txtsol" required="true"  Class="form-control input-sm text-right">
                                                </div>                                                
                                                <label class="col-md-2 control-label">US$</label>
                                                <div class="col-md-3">
                                                    <input type="text" readonly="true"  alt="Dolares" id="txtdol" name="txtdol" required="true"  Class="form-control input-sm text-right">
                                                </div>                                                 
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-2 control-label">Total</label>
                                                <div class="col-md-3">
                                                    <input type="text" readonly="true"  alt="Total" id="txttotal" name="txttotal" required="true"  Class="form-control input-sm text-right">
                                                </div>                                                                                             
                                            </div>                                            
                                            
                                            <!--
                                            <div class="form-check form-check-inline">
                                                <label class="col-md-2 form-check-label" for="inlineCheckbox1">Cerrar poliza</label>
                                                <input class="form-check-input" type="checkbox" disabled="true" id="inlineCheckbox1" >
                                            </div> 
                                            -->
                                      </div>

                                                                         
                                      <div id="c_tablaListaPolizasDetalle"></div>  
                                        <div class="accion-line aright">      
                                            <button title="Confirmar" id="btnAsientoCompras" onclick="GrabarAsiento()" class="btn btn-sm btn-success"  type="button" ><%=Constantes.ICON_CHECK%> Asiento Contable</button>                                                                                                                            
                                            <%--
                                            <button title="Confirmar" id="btnGrabaPolizas" onclick="GrabarPoliza()" class="btn btn-sm btn-success" disabled="true"  type="button" ><%=Constantes.ICON_CHECK%> Grabar  Poliza</button>                                                                                                                            
                                            --%>
                                            <a href="ComprasMigrarAction.do?operacion=inicializa" class="btn btn-sm btn-success"> <%=Constantes.ICON_CLEAR %> Cancelar</a>
                                        </div>  
                                  </div>
                                </div>                                 
                            </div>
                        </div>
                        <%-- Hiddens --%>
                    <div class="modal fade" id="modalPoliza" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">                            
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>                                    
                                    <h4 class="modal-title">Adicionar Poliza</h4>                    
                                </div>                                  
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">Poliza</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="addpoliza" name="addpoliza" class="form-control input-sm upper" maxlength="10" >                                                    
                                        </div>                                       
                                    </div>
                                    <div class="form-group">
                                        <label class="col-md-2 control-label">Monto FOB</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="addfob" name="addfob" class="form-control input-sm decimal-2"  >                                                    
                                        </div>                                       
                                        <label class="col-md-2 control-label">Total CBM</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="addcbm" name="addcbm" class="form-control input-sm decimal-2"  >                                                    
                                        </div>                                        
                                    </div> 
                                    <div class="form-group">
                                            <div class="form-check form-check-inline">
                                                <label class="col-md-2 form-check-label" for="inlineCheckbox1"> Cerrar</label>
                                                <input class="form-check-input" type="checkbox" id="addChkCerrar" value="option1">
                                            </div>                                         
                                    </div>
                                </div> 
                                <div class="modal-footer">                                    
                                    <button title="Confirmar" onclick="actualizaPoliza()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" > Grabar</button>                                                                                                               
                                </div>                                 
                            </div>
                        </div>
                    </div>
                     <div class="modal fade" id="modalAgregar" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">                            
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>                                    
                                    <h4 class="modal-title">Datos de la DOCUMENTO</h4>                    
                                </div>                             
                                <div class="modal-body"> 
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Poliza</label>
                                        <div class="col-sm-4">                                            
                                            <input type="text"  id="txtpoliza" name="txtpoliza" class="form-control input-sm" readonly="true" >                                                    
                                        </div>
                                        <div class="col-sm-2">                                            
                                            <input type="text"  id="txtitem" name="txtitem" class="form-control input-sm" readonly="true" >                                                    
                                        </div>                                        
                                    </div>  
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Descripcion</label>
                                        <div class="col-sm-6">                                            
                                            <input type="text"  id="txtdescripcion" name="txtdescripcion" class="form-control input-sm" readonly="true" >                                                    
                                        </div>
                                    </div>                                     
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">F. Documento</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtfecha" name="txtfecha"    class="form-control input-sm" readonly="true" >                                                    
                                        </div>
                                        <label class="col-md-2 control-label">F. Contable.</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtfechacontable" name="txtfechacontable" class="form-control input-sm" readonly="true" >                                                    
                                        </div>                                        
                                    </div>                                      
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">T.Doc.</label>
                                        <div class="col-sm-5">                                                                               
                                            <select class="form-control" id="txttd" name="txttd">
                                                <option value="01" >FACTURA</option>
                                                <option value="21" >EMBARQUE POR EL SERVICIO DE TRANSPORTE DE CARGA MARITIMA</option>
                                            </select>
                                        </div>
                                    </div>                                      
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Moneda</label>
                                        <div class="col-sm-3"> 
                                            <select id="txtmoneda" class="form-control input-sm">
                                                <option value="1" >Soles</option>
                                                <option value="2" >Dolares</option>
                                            </select>                                            
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Proveedor</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtproveedor" name="txtproveedor" class="form-control input-sm" maxlength="11" >
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Serie</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtserie" name="txtserie" class="form-control input-sm upper"   maxlength="4"  >
                                        </div>
                                        <label class="col-md-2 control-label">#Factura</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtdocumento" name="txtdocumento" class="form-control input-sm"  maxlength="8"  >
                                        </div>                                        
                                    </div>   
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Base imponible</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtbaseimponible" name="txtbaseimponible" class="form-control input-sm decimal-2" maxlength="8" >
                                        </div>
                                    </div> 
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">inafecto</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtinafecto" name="txtinafecto" class="form-control input-sm decimal-2"  maxlength="8"  >
                                        </div>
                                    </div>                                      
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">I.G.V.</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txtigv" name="txtigv" class="form-control input-sm  decimal-2" maxlength="8"   >
                                        </div>
                                    </div> 
                                    <div class="form-group row">
                                        <label class="col-md-3 control-label">Total</label>
                                        <div class="col-sm-3">                                            
                                            <input type="text"  id="txttotal" name="txttotal" class="form-control input-sm decimal-2"  maxlength="8"  >
                                        </div>
                                    </div>                                     
                                </div>
                                <div class="modal-footer">                                    
                                    <button title="Confirmar" onclick="actualizaDatalleitem()" name="btnAceptaItem"  id="btnAceptaItem" class="btn btn-sm btn-success" type="button" > Aceptarrrr</button>                                                                                                               
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
                        
                        
                        <!--
                        <input type="text" id="auxpoliza" name="auxpoliza">
                        -->
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>     
                        <html:hidden property="fechaContable" styleId="fechaContable"></html:hidden> 
                        
			
                        <input type="hidden" id="out_mes"  name="out_mes" >
                        <input type="hidden" id="out_anho"  name="out_anho" >
                        <input type="hidden" id="out_poliza"  name="out_poliza" >
                        <input type="hidden" id="out_tipoComprobante"  name="out_tipoComprobante" >
                        <input type="hidden" id="out_asiento"  name="out_asiento" >
                        
                        <input type="hidden" id="out_importe"  name="out_importe" >
                    </html:form>
                </div>
            </div>
        </div>
        
        
        

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
        <script type="text/javascript" src="js/funciones/jsCompras.js"></script>
    </body>
</html:html>