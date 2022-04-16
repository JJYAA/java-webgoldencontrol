<%@page import="java.util.ArrayList"%>
<%@page import="pe.com.gp.entity.Parte"%>
<%@page import="java.util.List"%>
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
                    <html:form action="/CatalogoAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default"><p>                            
                            <div class="form-group">
                                <label class="col-md-1 control-label">Por Familia</label>
                                <div class="col-md-2">
                                    <input type="checkbox" id="chkPor" name="chkPor">                                     
                                </div>                                
                            </div>
                            <div class="c_cliProducto"> 
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Producto</label>
                                    <div class="col-md-2">
                                        <input type="text" id="codigoProducto" name="codigoProducto" Class="form-control input-sm upper" >                                     
                                    </div>                                
                                    <button title="Consultar" onclick="muestraCatalogo()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_SEARCH%> Consultar</button>                                       
                                </div>
                            </div>
                            <div class="c_cliFamilia no-display"> 
                                <div class="form-group">
                                    <label class="col-md-1 control-label">Familia</label>
                                    <div class="col-md-2">
                                        <html:select property="familia" styleId="familia"    styleClass="form-control input-sm">
                                            <html:option value=""> -- Seleccione -- </html:option>
                                            <html:options collection="listaFamilia" labelProperty="descripcion" property="indice" />
                                        </html:select>                                 
                                    </div>
                                    <label class="col-md-1 control-label">Clase</label>
                                    <div class="col-md-2">
                                        <html:select property="clase" styleId="clase"    styleClass="form-control input-sm">
                                            <html:option value=""> -- Seleccione -- </html:option>
                                            <html:options collection="listaClase" labelProperty="descripcion" property="indice" />
                                        </html:select>                                  
                                    </div>                            
                                    <label class="col-md-1 control-label">Grupo</label>
                                    <div class="col-md-2">
                                        <html:select property="grupo" styleId="grupo"    styleClass="form-control input-sm">
                                            <html:option value=""> -- Seleccione -- </html:option>
                                            <html:options collection="listaGrupo" labelProperty="descripcion" property="indice" />
                                        </html:select>                                 
                                    </div> 
                                    <button title="Consultar" onclick="muestraCatalogo()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_SEARCH%> Consultar</button>                                       
                                </div> 

                            </div>

                            <p>
                        </div>

                        <h1 class="page-header">Cat&aacute;logo</h1>
                        <div id="c_tablaListaCatalogo"></div>

                        <h3 class="page-header">En el carrito</h3>
                        <div class="form-group">
                            <label class="col-md-2 control-label">TOTAL S/.</label>
                            <div class="col-md-2">
                                <html:text alt="TOTAL" property="total" styleId="total" readonly="true" styleClass="form-control input-sm"></html:text>
                                </div>                                            
                                <div class="col-md-2">
                                    <button title="Confirmar" id="btnConFacRep" class="btn btn-sm btn-success" disabled="true"  type="button" ><%=Constantes.ICON_CHECK%> Confirmar Carrito</button>                                                                                
                            </div>                                
                        </div>                      

                        <div class="panel panel-default">
                            <div class="panel-body">   
                                <div class="col-md-6">
                                    <div id="c_tablaListaItem"></div>
                                </div>
                            </div>                            
                        </div>
                        <p>
                            <%-- Hiddens --%>
                            <html:hidden property="operacion" styleId="operacion"></html:hidden>
                            <input type="text" id="textbox1" name="textbox1" >
                        </html:form>
                </div>
            </div>
        </div>
        <%@include file="jspf/js.jspf" %>
        <script type="text/javascript" src="js/funciones/jsCatalogo.js" ></script>
        <%-- Scripts --%>


    </body>
</html:html>