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
                        <li><strong>Logistica</strong></li>
                        <li>Productos</li>
                    </ol>
                    <html:form action="/ProductosAction" styleId="frm_generico" method="POST" enctype="multipart/form-data" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Buscar por producto</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Producto</label>
                                    <div class="col-md-2">
                                        <div class="input-group">
                                            <html:text alt="Numero documento" property="numeroParte" styleId="numeroParte"   onkeypress="return buscarProductoEnter(event)" styleClass="form-control input-sm upper"></html:text>
                                            <span class="input-group-btn">
                                                    <button title="Buscar" type="button" id="btnBuscarCliNroDoc" onclick="buscarProductoBoton();" class="btn btn-sm btn-default upper"><%=Constantes.ICON_SEARCH%></button>
                                            </span>                                             
                                        </div>                                                     
                                    </div>  
                                    <input type="checkbox" name="chkvalor"  id="chkvalor"  > solo Stock mayor a CERO<br>        
                                </div>
                            </div>
                        </div>                    
                        <div class="col-md-6">
                            <div id="c_tablaPartesProductos"></div>  
                        </div> 
                        <div class="col-md-6">
                            <div class="row">     
                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">ProductoFFF</label>
                                            <div class="col-md-4">     
                                                <div class="input-group">
                                                    <input type="text" name="inputNumeroParte" id="inputNumeroParte"    class="form-control input-sm upper" onkeypress="return buscarProductoItemEnter(event)"  > 
                                                    <span class="input-group-btn">
                                                        <button title="Buscar" type="button" id="btnbuscarProductoItem" onclick="buscarProductoItem();" class="btn btn-sm btn-default upper"><%=Constantes.ICON_SEARCH%></button>
                                                    </span>                                                     
                                                </div>
                                            </div>                                             
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Descripcion</label>
                                            <div class="col-md-8">
                                                <html:text alt="Descripcion del Producto" property="descripcion" styleId="descripcion"   styleClass="form-control input-sm"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">V.V.P. S/.</label>
                                            <div class="col-md-2">
                                                <html:text alt="Precio S/" property="vvpSoles" styleId="vvpSoles"   styleClass="form-control input-sm "/>
                                            </div>
                                            <label class="col-md-2 control-label">V.V.P US$</label>
                                            <div class="col-md-2">
                                                <html:text alt="Precio US$" property="vvpDolar" styleId="vvpDolar"   styleClass="form-control input-sm "/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Activo</label>
                                            <div class="col-md-2"><input type="checkbox" id="selectActivo" name="selectActivo"></div>
                                            <label class="col-md-2 control-label">+Precios</label>
                                            <div class="col-md-2">
                                                <div class="input-group">
                                                    <input type="checkbox" id="selectMasPrecios" name="selectMasPrecios" > 
                                                </div>

                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Cajas</label>
                                            <div class="col-md-3">
                                                <html:text alt="Precio" property="cantidadCaja" styleId="cantidadCaja"   styleClass="form-control input-sm"/>
                                            </div>
                                            <label class="col-md-2 control-label">Duas</label>
                                            <div class="col-md-3">
                                                <html:text alt="Duas" property="duas" styleId="duas"   styleClass="form-control input-sm"/>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Familia</label>
                                            <div class="col-md-5">
                                                <html:select property="familia" styleId="familia"    styleClass="form-control input-sm">
                                                    <html:options collection="listaFamilia" labelProperty="descripcion" property="indice" />
                                                </html:select>                                                
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Clase</label>
                                            <div class="col-md-5">
                                                <html:select property="clase" styleId="clase"    styleClass="form-control input-sm">
                                                    <html:options collection="listaClase" labelProperty="descripcion" property="indice" />
                                                </html:select>                                                
                                            </div>
                                        </div> 
                                        <div class="form-group">
                                            <label class="col-md-3 control-label">Grupo</label>
                                            <div class="col-md-5">
                                                <html:select property="grupo" styleId="grupo"    styleClass="form-control input-sm">
                                                    <html:options collection="listaGrupo" labelProperty="descripcion" property="indice" />
                                                </html:select>                                                
                                            </div>
                                        </div>                                             
                                    </div>                                    
                                </div>

                                <ul class="nav nav-tabs" id="myTab" role="tablist">
                                    <li class="nav-item">
                                        <a class="nav-link" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home"
                                           aria-selected="true">Home</a>
                                    </li>
                                    <li class="nav-item">
                                        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile"
                                           aria-selected="false">Profile</a>
                                    </li>
                                </ul>
                                <div class="tab-content" id="myTabContent">
                                    <div class="tab-pane fade" id="home" role="tabpanel" aria-labelledby="home-tab">
                                        <div class="panel-body">
                                            <div class="form-group">
                                                <label class="col-md-1 control-label">01</label>
                                                <label class="col-md-3 control-label">CAJAS</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="precios01" styleId="precios01"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                                         
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-1 control-label">02</label>
                                                <label class="col-md-3 control-label">DOCENA ESPECIAL</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="precios02" styleId="precios02"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                                         
                                            </div>                                        
                                            <div class="form-group">
                                                <label class="col-md-1 control-label">03</label>
                                                <label class="col-md-3 control-label">CIENTOS</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="precios03" styleId="precios03"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                                         
                                            </div> 
                                            <div class="form-group">
                                                <label class="col-md-1 control-label">04</label>
                                                <label class="col-md-3 control-label">DOCENA 01</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="precios04" styleId="precios04"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                                         
                                            </div>
                                            <div class="form-group">
                                                <label class="col-md-1 control-label">05</label>
                                                <label class="col-md-3 control-label">UNIDADES</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="precios05" styleId="precios05"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                                         
                                            </div>                                               
                                        </div>
                                    </div>                                                     
                                    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                                        <div class="panel-body">
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Costo Promedio</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="costoPromedio" styleId="costoPromedio"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                          
                                            </div>      
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Ultimo Costo</label>
                                                <div class="col-md-2">                                                    
                                                    <html:text alt="precio 01" property="ultimoCosto" styleId="ultimoCosto"   styleClass="form-control input-sm decimal-2"/>
                                                </div>                                          
                                            </div>                                     
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Total</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="stockTotal" styleId="stockTotal"   styleClass="form-control input-sm numeros"/>
                                                </div>                                          
                                            </div>      
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Disponible</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="stockDisponible" styleId="stockDisponible"   styleClass="form-control input-sm numeros"/>
                                                </div>                                          
                                            </div>  
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Seguridad</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="stockSeguridad" styleId="stockSeguridad"   styleClass="form-control input-sm numeros"/>
                                                </div>                                          
                                            </div> 
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Almacenes</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="stockAlmacen" styleId="stockAlmacen"   styleClass="form-control input-sm numeros"/>
                                                </div>                                          
                                            </div>                                     
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Temporal</label>
                                                <div class="col-md-2">
                                                    <html:text alt="precio 01" property="stockTemporal" styleId="stockTemporal"   styleClass="form-control input-sm numeros"/>
                                                </div>                                          
                                            </div>                                      
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Ultimo Ingreso</label>
                                                <div class="col-md-4">
                                                    <input type="text" id="ultimoIngreso" name="ultimoIngreso" readonly="true"  class="form-control input-sm">                                    
                                                </div>                                          
                                            </div>      
                                            <div class="form-group">
                                                <label class="col-md-3 control-label">Ultimo Salida</label>
                                                <div class="col-md-4">
                                                    <input type="text" id="ultimaSalida" name="ultimaSalida" readonly="true"  class="form-control input-sm">                                    
                                                </div>                                          
                                            </div>                                     

                                        </div>
                                    </div>                                           
                                </div>                                        





                                <div class="panel panel-default">
                                    <div class="panel-body">
                                        <div class="form-group">
                                            <div class="col-md-2">
                                                <button title="Imagen" id="btnFile"  onclick="muestraDownloadImg()" class="btn btn-sm btn-success" disabled="true"  type="button" ><%=Constantes.ICON_DOWNLOAD%> Imagen</button>
                                            </div> 
                                        </div>                                        
                                    </div>
                                </div>  
                                <div class="accion-line aright">      
                                    <button title="Confirmar" id="btnNuevoProducto"  onclick="AdicionarProducto()" class="btn btn-sm btn-primary" disabled="true"  type="button" ><%=Constantes.ICON_ADD%> Nuevo</button>
                                    <a href="ProductosAction.do?operacion=inicializa" class="btn btn-sm btn-success" > Cancelar</a>
                                    <button title="Confirmar" id="btnConfirmarProducto"  onclick="GrabarProducto()" class="btn btn-sm btn-success" disabled="true"  type="button" ><%=Constantes.ICON_CHECK%> Confirmar</button>                                                                                
                                </div>  
                            </div>    
                        </div>
                        <div id="myModalDownloadImg" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">Download Imagen</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>                                        
                                    </div>
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label class="col-md-2 control-label">Imagen</label>
                                            <div class="col-md-6">
                                                <html:file alt="Precio" property="theFile" styleId="theFile"   styleClass="form-control input-sm"/>
                                            </div>                                                         
                                        </div>
                                    </div>
                                    <div class="modal-footer">
                                        <button title="Confirmar" onclick="fotoProducto()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_CHECK%> Grabar Imagen</button>                                       
                                    </div>                                       
                                </div>

                            </div>
                        </div>

                        <div id="myModalImagen" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                          <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-body">                                  
                                    <img id="imagen" src="//placehold.it/1000x600" class="img-responsive">
                                </div>
                            </div>
                          </div>
                        </div>                                       

                        <div class="modal fade" id="modalPreciosProductos" data-backdrop="static"  tabindex="-1" role="dialog">
                            <div class="modal-dialog" role="document">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <h5 class="modal-title">+ Precios</h5>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">&times;</span>
                                        </button>
                                    </div>
                                    <div class="modal-body">
                                    </div>
                                    <div class="modal-footer">
                                        <button title="Confirmar" onclick="aceptarDescuento()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_CHECK%> Grabar +Precios</button>                                       
                                    </div>                                         

                                </div>
                            </div>
                        </div>                        


                        <html:hidden property="operacion" styleId="operacion"></html:hidden>

                        <html:hidden property="numeroparteAux" styleId="numeroparteAux"></html:hidden>

                        <html:hidden property="activo" styleId="activo"></html:hidden>
                        <html:hidden property="masPrecios" styleId="masPrecios"></html:hidden>

                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
        <script type="text/javascript" src="js/funciones/jsProductos.js"></script>
    </body>
</html:html>