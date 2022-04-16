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
                        <li><strong>Generales</strong></li>
                        <li>Registro de Ventas</li>
                    </ol>
                    <html:form action="/RegistroVentasAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading">Buscar por</div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Cliente</label>
                                    <div class="col-md-3">
                                        <div class="input-group">
                                            <html:text alt="Codigo" property="codigo" styleId="codigo"   onkeypress="return buscarProductoEnter(event)" styleClass="form-control input-sm upper"></html:text>                                                       
                                        </div>                                                     
                                    </div>       
                                </div>
                                <div class="form-group">
                                    <label class="col-md-2 control-label">De</label>
                                    <div class="col-md-2">
                                        <html:text alt="De" property="fechaIni" styleId="fechaIni"   onkeypress="return buscarProductoEnter(event)" styleClass="form-control input-sm upper"></html:text>                                           
                                    </div>                                                                              
                                    <label class="col-md-1 control-label">Hasta</label>
                                    <div class="col-md-2">
                                        <html:text alt="Hasta" property="fechaFin" styleId="fechaFin"   onkeypress="return buscarProductoEnter(event)" styleClass="form-control input-sm upper"></html:text>                                           
                                    </div>        
                                </div> 
                                <div class="form-group">
                                    <label class="col-md-2 control-label">Tipo Doc.</label>
                                    <div class="col-md-3">
                                        <html:select property="tipoDocumento" styleId="tipoDocumento"    styleClass="form-control input-sm">
                                            <html:option value="%"> -- TODOS -- </html:option>
                                            <html:option value="01">FACTURAS</html:option>
                                            <html:option value="03">BOLETA DE VENTA</html:option>
                                            <html:option value="07">NOTA DE CREDITO</html:option>                                        
                                        </html:select>                                                                                     
                                    </div>
                                    <label class="col-md-1 control-label">Tienda</label>
                                    <div class="col-md-3">
                                        <html:select property="actividad" styleId="actividad"    styleClass="form-control input-sm">
                                            <html:option value="%"> -- TODOS -- </html:option>
                                            <html:options collection="listaTiendas" labelProperty="descripcion" property="indice" />
                                        </html:select>                                                                                     
                                    </div>                                    
                                    <label class="col-md-1 control-label">F.Pago</label>
                                    <div class="col-md-2">
                                        <html:select property="formaPago" styleId="formaPago"    styleClass="form-control input-sm">
                                            <html:option value="%"> -- TODOS -- </html:option>
                                            <html:option value="1">CONTADO</html:option>
                                            <html:option value="2">CREDITO</html:option>                                            
                                        </html:select>                                                                                     
                                    </div>                                    
                                </div>    
                                <div class="form-group">
                                    <label class="col-md-2 control-label">T.Venta</label>
                                    <div class="col-md-3">
                                        <html:select property="tipoVenta" styleId="tipoVenta"    styleClass="form-control input-sm">
                                            <html:option value="%"> -- TODOS -- </html:option>
                                            <html:option value="1">CONTADO</html:option>
                                            <html:option value="2">CREDITO</html:option>                                            
                                        </html:select>                                                                                                                             
                                    </div> 
                                    <div class="col-md-2">    
                                        <button onclick="muestraListaRegistroVentas();" class="btn btn-sm btn-default" type="button" title="Consultar">                                                    
                                               Consultar
                                        </button>
                                         
                                    </div> 
                                    <div class="col-md-2">
                                        <button id="exportButton" disabled="true" class="btn btn-sm btn-danger clearfix"><span class="fa fa-file-excel-o"></span> Export to Excel</button>
                                    </div>
                                </div> 
                                       
                            </div>
                        </div>
                        <div id="c_tablaRegistroVenta"></div>                      
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label">TOTAL NETO</label>
                                    <div class="col-md-2">
                                        <input type="text" id="neto" name="neto" value="100.00"  readonly="true"  class="form-control input-sm upper  text-right" >
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label class="col-md-2 control-label">TOTAL IGV</label>
                                    <div class="col-md-2">
                                        <input type="text" id="igv" name="igv" value="18.00"  readonly="true"  class="form-control input-sm upper  text-right">
                                    </div>
                                </div>  
                                <div class="form-group">
                                    <label class="col-md-2 control-label">TOTAL</label>
                                    <div class="col-md-2">
                                        <input type="text" id="total" name="total" value="118.00" readonly="true" class="form-control input-sm upper  text-right">
                                    </div>
                                </div>  
                            </div>
                        </div>
                        <%-- Hiddens --%>
                        <html:hidden property="operacion" styleId="operacion"></html:hidden>
                    </html:form>
                </div>
            </div>
        </div>

        <%-- Scripts --%>
        <%@include file="jspf/js.jspf" %>   
        <script type="text/javascript" src="js/funciones/jsRegistroVenta.js"></script>
    </body>
</html:html>