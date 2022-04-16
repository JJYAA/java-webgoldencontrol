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
                        <c:when test="${PedidoOfertaRepForm.auxproceso=='COTIZACION'}">
                            <ol class="breadcrumb">
                                <li><strong>Ventas</strong></li>
                                <li>PEDIDO DE VENTA</li>                                   
                            </ol>                            
                        </c:when>
                        <c:when test="${PedidoOfertaRepForm.auxproceso=='FACBOL'}">
                            <ol class="breadcrumb">
                                <li><strong>Repuestos</strong></li>
                                <li>FACTURACI&Oacute;N DE PRODUCTOS</li>                                    
                            </ol>                            
                        </c:when>                          
                        <c:otherwise>
                            <ol class="breadcrumb">
                                <li><strong>Repuestos</strong></li>                        
                                <li>Creaci&oacute;n de Orden de Venta</li>                                    
                            </ol>                            
                        </c:otherwise>
                    </c:choose>

                    <html:form action="/PedidoOfertaRepAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">   
                        <%@include file="jspf/msgs.jspf" %>
                        <c:choose>
                            <c:when test="${PedidoOfertaRepForm.flagMueOcuForm=='muestra'}">      


                                                <div class="panel panel-default">
                                                    <div class="panel-heading">Datos del cliente</div>
                                                    <div class="panel-body">
                                                        <div class="form-group">
                                                            <label class="col-md-2 control-label">T&iacute;tulo</label>
                                                            <div class="col-md-3">
                                                                <html:select property="clienteTitulo" styleId="clienteTitulo"    styleClass="form-control input-sm">
                                                                    <html:option value="01">Natural</html:option>
                                                                    <html:option value="02">Juridica</html:option>
                                                                </html:select>  
                                                            </div>

                                                            <label class="col-md-2 control-label">Tipo documento</label>
                                                            <div class="col-md-2">
                                                                <html:select property="clienteTipoDocumento" styleId="clienteTipoDocumento"    styleClass="form-control input-sm">
                                                                    <html:option value="02">DNI</html:option>
                                                                    <html:option value="06">NATURAL CON RUC</html:option>
                                                                </html:select>  
                                                            </div>                                            
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-md-2 control-label">N&uacute;mero documento</label>
                                                            <div class="col-md-3">
                                                                <div class="input-group">
                                                                    <html:text alt="Numero documento" property="clienteNumeroDocumento" maxlength="8" styleId="clienteNumeroDocumento"   onkeypress="return buscarClienteNroDocEnter(event)" styleClass="form-control input-sm"></html:text>
                                                                        <span class="input-group-btn">
                                                                            <button title="Buscar" type="button" id="btnBuscarCliNroDoc" onclick="buscarClienteNroDocBoton();" class="btn btn-sm btn-default"><%=Constantes.ICON_SEARCH%></button>
                                                                           <button class="btn btn-sm btn-default"	id="btnAbreModalHelpCli" onclick="openModalHelpCli();" type="button" title="Help de Cliente"><%=Constantes.ICON_MENU%></button>                  
                                                                    </span>
                                                                </div>                                                     
                                                            </div>
                                                            <label class="col-md-2 control-label">Propues de Venta</label>        
                                                            <div class="col-md-2">
                                                                <div class="input-group">
                                                                    <html:text alt="Propuesta" property="numeroPropuesta" styleId="numeroPropuesta"   onkeypress="return buscarPropuestaEnter(event)" disabled="true" styleClass="form-control input-sm"></html:text>
                                                                    <span class="input-group-btn">
                                                                        <button title="Buscar" type="button" disabled="true" id="btnbuscarPropuesta" onclick="muestraModelCotizacionesBoton();" class="btn btn-sm btn-default"><%=Constantes.ICON_HAMBURGER%></button>
                                                                    </span>
                                                                </div>                                                     
                                                            </div>                                                    
                                                        </div>

                                                        <div class="form-group">
                                                            <label class="col-md-2 control-label">Moneda</label>
                                                            <div class="col-md-3">
                                                                <html:select property="moneda" styleId="moneda"     styleClass="form-control input-sm">
                                                                    <html:option value="1">SOLES</html:option>
                                                                </html:select>
 
                                                                 
                                                                    
                                                            </div> 
                                                            <label class="col-md-2 control-label">Forma de Pago</label>
                                                            <div class="col-md-2">
                                                                <html:select property="formaPago" styleId="formaPago"    styleClass="form-control input-sm">
                                                                    <html:option value="0">Contado</html:option>
                                                                    <html:option value="1">Credito</html:option>
                                                                </html:select>                                                

                                                            </div>                                            
                                                        </div>                                                     

                                                        <div class="c_cliPerNat">                                       
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">Paterno</label>
                                                                <div class="col-md-3">
                                                                    <html:text alt="Paterno" property="clienteApellidoPaterno" styleId="clienteApellidoPaterno"    styleClass="form-control input-sm"></html:text>
                                                                    </div>
                                                                    <label class="col-md-2 control-label">Materno</label>
                                                                    <div class="col-md-3">
                                                                    <html:text alt="Materno" property="clienteApellidoMaterno" styleId="clienteApellidoMaterno"    styleClass="form-control input-sm"></html:text>
                                                                    </div>                                            
                                                                </div> 
                                                                <div class="form-group">
                                                                    <label class="col-md-2 control-label">Nombre</label>
                                                                    <div class="col-md-3">
                                                                    <html:text alt="Nombre" property="clientePrimerNombre" styleId="clientePrimerNombre"   styleClass="form-control input-sm"></html:text>
                                                                    </div>
                                                                    <label class="col-md-2 control-label">Nombre</label>
                                                                    <div class="col-md-3">
                                                                    <html:text alt="Nombre" property="clienteSegundoNombre" styleId="clienteSegundoNombre"   styleClass="form-control input-sm"></html:text>
                                                                    </div>                                            
                                                                </div>                                                     
                                                            </div>
                                                            <div class="c_cliPerJur no-display">
                                                                <div class="form-group">
                                                                    <label class="col-md-2 control-label">Raz&oacute;n social</label>
                                                                    <div class="col-md-8">
                                                                    <html:text alt="Razon social" property="clienteRazonSocial" styleId="clienteRazonSocial"  styleClass="form-control input-sm upper" maxlength="80"></html:text>
                                                                    </div>
                                                                </div>
                                                            </div>        
                                                            <div class="form-group">        
                                                                <label class="col-md-2 control-label">Direcci&oacute;n</label>
                                                                <div class="col-md-8">
                                                                <html:text alt="Direccion" property="clienteDireccion" styleId="clienteDireccion"  styleClass="form-control input-sm upper" maxlength="80"></html:text>
                                                                </div>                                               
                                                            </div>  
                                                            <div class="form-group">

                                                                <label class="col-md-2 control-label">Dept.</label>
                                                                <div class="col-md-2">
                                                                <html:select property="clienteDepartamento" styleId="clienteDepartamento"    styleClass="form-control input-sm">
                                                                    <html:options collection="listaDepartamento" labelProperty="descripcion" property="indice" />
                                                                </html:select>                                                                                                
                                                            </div>

                                                            <label class="col-md-1 control-label">Prov.</label>
                                                            <div class="col-md-2">                                                

                                                                <html:select property="clienteProvincia" styleId="clienteProvincia"    styleClass="form-control input-sm">
                                                                    <html:options collection="listaProvincia" labelProperty="descripcion" property="indice" />
                                                                </html:select>                                                        
                                                            </div>
                                                            <label class="col-md-1 control-label">Dist.</label>
                                                            <div class="col-md-2">                                                

                                                                <html:select property="clienteDistrito" styleId="clienteDistrito"    styleClass="form-control input-sm">
                                                                    <html:options collection="listaDistrito" labelProperty="descripcion" property="indice" />
                                                                </html:select>                                                          
                                                            </div>

                                                        </div>    
                                                        <div class="c_otros">
                                                            <div class="form-group">
                                                                <label class="col-md-2 control-label">Tele</label>
                                                                <div class="col-md-2">
                                                                    <html:text alt="Telefono" property="clienteTelefono1" styleId="clienteTelefono1"  styleClass="form-control input-sm upper" maxlength="10"></html:text>
                                                                    </div>
                                                                    <label class="col-md-1 control-label">Tele</label>
                                                                    <div class="col-md-2">
                                                                    <html:text alt="Telefono" property="clienteTelefono2" styleId="clienteTelefono2"  styleClass="form-control input-sm upper" maxlength="10"></html:text>
                                                                    </div>                
                                                                    <label class="col-md-1 control-label">Cel.</label>
                                                                    <div class="col-md-2">
                                                                    <html:text alt="Celular" property="clienteCelular" styleId="clienteCelular"  styleClass="form-control input-sm upper" maxlength="10"></html:text>
                                                                    </div>                                                      
                                                                </div>
                                                            </div>  


                                                        </div>        
                                                    </div>            
                                                
 


                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="form-group">

                                                <div class="col-md-2">   
                                                   
                                                    <div class="input-group">                                                        
                                                        <html:text alt="Producto" property="numeroParte" styleId="numeroParte" onkeypress="return buscaParte(event)" readonly="false" styleClass="form-control input-sm upper"></html:text>                                                                                                                
                                                        <span class="input-group-btn">
                                                            <button title="Buscar" type="button" id="btnBuscarCliNroDoc" onclick="buscarProductoEnter();" class="btn btn-sm btn-default"><%=Constantes.ICON_SEARCH%></button>
                                                            <button class="btn btn-sm btn-default"	id="btnAbreModalHelpProd" onclick="openModalHelpProd();" type="button" title="Help de Cliente"><%=Constantes.ICON_MENU%></button>                  
                                                        </span>
                                                    </div>
                                            </div>

                                            <div class="col-md-3">
                                                <html:text alt="Descripci&oacute;n" property="descripcion" styleId="descripcion" readonly="true" styleClass="form-control input-sm"></html:text>
                                            </div>
                                            <div class="col-md-1">
                                                <html:text alt="cajas" property="cajas" styleId="cajas" readonly="true" styleClass="form-control input-sm"></html:text>
                                            </div>
                                            <div class="col-md-1">
                                                
                                                <html:text alt="Total" property="stockTotal" styleId="stockTotal" readonly="true" styleClass="form-control input-sm"></html:text>
                                            </div>

                                            <div class="col-md-1">
                                                
                                                <html:text alt="V.V.P." property="vvp" styleId="vvp" readonly="true" styleClass="form-control input-sm"></html:text>
                                            </div>                                                
                                            <div class="col-md-1">
                                                    
                                                <html:text alt="Cantidad" property="cantidad" styleId="cantidad" maxlength="6" readonly="false" styleClass="form-control input-sm numeros"></html:text>
                                            </div>
                                            <div class="col-md-1">
                                                                                                         
                                                    <html:text alt="Dscto." property="descuento" styleId="descuento" maxlength="6" readonly="false"  onkeypress="return agregarItem(event)" styleClass="form-control input-sm decimal-3"></html:text>                                                    
                                            </div>
                                            <div class="col-md-1">
                                                
                                                <button  type="button" title="Adicionar" id="btnAdicionar" class="btn btn-default"   onclick="AdicionarItem();" type="button" >Adicionar</button>                                                     
                                            </div>
                                        </div>
                                        <div id="c_tablaPartesTempo"></div>        
                                    </div>
                                </div>

                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <div class="col-md-2 col-md-offset-2">
                                                <label class="control-label" for="totalBruto">Total Bruto</label>
                                                <div class="input-group">
                                                    <div class="input-group-addon signo_moneda">S/</div>
                                                    <html:text alt="Total Bruto" property="totalBruto" styleId="totalBruto" styleClass="form-control input-sm text-right" readonly="true"></html:text>                                               
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label" for="totalDescuento">Total Descuento</label>
                                                    <div class="input-group">
                                                        <div class="input-group-addon signo_moneda">S/</div>
                                                    <html:text alt="Total Dscto." property="totalDescuento" styleId="totalDescuento" styleClass="form-control input-sm text-right" readonly="true"></html:text>                                               
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label" for="totalNeto">Total Neto</label>
                                                    <div class="input-group">
                                                        <div class="input-group-addon signo_moneda">S/</div>
                                                    <html:text alt="Total Neto" property="totalNeto" styleId="totalNeto" styleClass="form-control input-sm text-right" readonly="true"></html:text>                                               
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label" for="totalIgv">Total IGV</label>
                                                    <div class="input-group">
                                                        <div class="input-group-addon signo_moneda">S/</div>
                                                    <html:text alt="Total IGV" property="totalIgv" styleId="totalIgv" styleClass="form-control input-sm text-right" readonly="true"></html:text>                                               
                                                    </div>
                                                </div>
                                                <div class="col-md-2">
                                                    <label class="control-label" for="totalGeneral">Total General</label>
                                                    <div class="input-group">
                                                        <div class="input-group-addon signo_moneda">S/</div>
                                                    <html:text alt="Total Gral." property="totalGeneral" styleId="totalGeneral" styleClass="form-control input-sm text-right" readonly="true"></html:text>                                               
                                                    </div>
                                                </div>                                           
                                            </div>
                                        </div>
                                    </div>

                                    <div class="panel panel-default">
                                        <div class="panel-body">
                                            <div class="form-group">                                   
                                                <label class="col-md-2 control-label">Nota 01</label>
                                                <div class="col-md-4">
                                                <html:text alt="Nota 01" property="nota1" styleId="nota1" styleClass="form-control input-sm upper" maxlength="70"></html:text>
                                                </div>
                                            </div>
                                        </div>
                                    </div>            

                                    <div class="alert alert-danger jq-error-container" role="alert" id="jq-error-container">
                                        <p><strong></strong></p>
                                        <ul class="list-unstyled"></ul>
                                    </div>

                                <c:choose>
                                    <c:when test="${PedidoOfertaRepForm.auxproceso=='COTIZACION'}">
                                        <div class="accion-line aright">      
                                            <button title="Confirmar" id="btnConFacRep" class="btn btn-sm btn-success" disabled="true"  type="button" ><%=Constantes.ICON_CHECK%> Genera Cotizacion</button>                                                                                
                                        </div>                                                                                            
                                    </c:when>
                                    <c:otherwise>
                                        <div class="accion-line aright">      
                                            <button title="Confirmar" id="btnConFacRep" class="btn btn-sm btn-success" disabled="true"  type="button" ><%=Constantes.ICON_CHECK%> Genera Factura</button>                                                                                
                                        </div>                                                                                            
                                    </c:otherwise>    
                                </c:choose>                
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
                        <div class="modal fade" id="modalPreciosProductos" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Precios por Producto</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div id="c_tablaPartesProductos"></div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="modal fade" id="modaleditarItem" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Descuento del Producto</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Producto</label>
                                            <div class="col-md-3">
                                                <input type="text"  id="productoAux" name="productoAux" class="form-control input-sm">
                                            </div>
                                        </div>                                         
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Descripción</label>
                                            <div class="col-md-7">
                                                <input type="text"  id="nombreAux" name="nombreAux" class="form-control input-sm">                                                    
                                            </div>
                                        </div>                                         
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Precio</label>
                                            <div class="col-md-2">
                                                <input type="text"  id="precioAux" name="precioAux" class="form-control input-sm">                                                    
                                            </div>
                                        </div>                                             
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Descuento</label>
                                            <div class="col-md-2">
                                                <input type="text"  id="descuentoAux" name="descuentoAux" class="form-control input-sm decimal-3"   >                                                    
                                            </div>
                                            
                                        </div>                                             
                                    </div>
                                    <div class="modal-footer">
                                        <button title="Confirmar" onclick="aceptarDescuento()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_CHECK%> Aceptar</button>                                       
                                    </div>                                        

                                </div>
                            </div>
                        </div>      

                        <div class="modal fade" id="modalCotizaciones" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Cotizaciones de Clientes</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                           
                                                <label class="col-md-1 control-label">Desde</label>
                                                <div class="col-md-3">
                                                    <div class="input-group">
                                                        <html:text alt="Desde" property="fechaDesde" styleId="fechaDesde"   onkeypress="return buscarClienteNroDocEnter(event)" styleClass="form-control date-picker"></html:text>
                                                        <span class="input-group-btn">
                                                                <button title="Buscar" type="button"  onclick="buscarCotizacionesBoton();" class="btn btn-sm btn-default"><%=Constantes.ICON_SEARCH%></button>
                                                        </span>
                                                    </div>                                                     
                                                </div>                                                                                            
                                        </div>            
                                        <div class="modal-body">
                                            <div id="c_tablaCotizaciones"></div>
                                        </div>                                    
                                    </div>
                                </div>
                            </div>      
                        </div>   
                                                       
                <%-- Modal Help de CLIENTE --%>
				<div class="modal fade" id="modalHelpCliente" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">		
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" name="cardNameTitulo" id="cardNameTitulo">Proveedores</h4>					
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="control-label col-md-2">Buscar por</label>
									<div class="col-md-3">
                                                                                        
                                                                                <select id="helpProTipoBusqueda" name="helpProTipoBusqueda" class="form-control input-sm">
                                                                                    <option value="COD">C&Oacute;DIGO</option>
                                                                                    <option value="RAZ">RAZ&Oacute;N SOCIAL</option>      
                                                                                </select>
									</div>
									<div class="he-7 visible-xs visible-sm hidden-md hidden-lg"></div>
									<div class="col-md-7">
										<div class="input-group">
											<input type="text" name="helpProDescripcion" id="helpProDescripcion" 										
												placeholder="Ingrese el texto a buscar" class="form-control input-sm upper" />
				 							<span class="input-group-btn">					
                                                                                            <button class="btn btn-sm btn-default" id="buscarSN" type="button"  onclick="buscarCliHelp();" title="Buscar"><%=Constantes.ICON_SEARCH%></button>						
											</span>
										</div>
									</div>
								</div>
								<div id="c_tableHelpGenerico"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-sm btn-default" title="Cerrar" onclick="closeModalHelpCli();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cerrar</span></button>						
							</div>
						</div>
					</div>
				</div>                                                                         

                <%-- Modal Help de PRODUCTOS --%>
				<div class="modal fade" id="modalHelpProductos" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">		
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<h4 class="modal-title" name="cardNameTitulo" id="cardNameTitulo">Productos</h4>					
							</div>
							<div class="modal-body">
								<div class="form-group">
									<label class="control-label col-md-2">Buscar por</label>
									<div class="col-md-3">
                                                                                        
                                                                                <select id="helpProTipoBusqueda" name="helpProTipoBusqueda" class="form-control input-sm">
                                                                                    <option value="COD">C&Oacute;DIGO</option>
                                                                                    <option value="RAZ">RAZ&Oacute;N SOCIAL</option>      
                                                                                </select>
									</div>
									<div class="he-7 visible-xs visible-sm hidden-md hidden-lg"></div>
									<div class="col-md-7">
										<div class="input-group">
											<input type="text" name="helpProdDescripcion" id="helpProdDescripcion" 										
												placeholder="Ingrese el texto a buscar" class="form-control input-sm upper" />
				 							<span class="input-group-btn">					
                                                                                            <button class="btn btn-sm btn-default" id="buscarSNProd" type="button"  onclick="buscarCliHelp();" title="Buscar"><%=Constantes.ICON_SEARCH%></button>						
											</span>
										</div>
									</div>
								</div>
								<div id="c_tableHelpCliente"></div>
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-sm btn-default" title="Cerrar" onclick="closeModalHelpProd();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cerrar</span></button>						
							</div>
						</div>
					</div>
				</div>                                                                         
                                                        
                                                        
                <%-- HIDDENS --%>
                
                
                <html:hidden property="numItemAux" styleId="numItemAux"></html:hidden>
                        <html:hidden property="auxFacTipDoc" styleId="auxFacTipDoc"></html:hidden>
                        <html:hidden property="auxCliTipDoc" styleId="auxCliTipDoc"></html:hidden>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                        <html:hidden property="auxproceso" styleId="auxproceso"></html:hidden>
                        <html:hidden property="itemCodigo" styleId="itemCodigo"></html:hidden>
                        

                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>  
        <script type="text/javascript" src="js/funciones/jsPedidoOfertaRep.js"></script>           
    </body>
</html:html>