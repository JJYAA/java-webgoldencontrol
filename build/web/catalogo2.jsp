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
        <%             String[] listaImg = new String[100];
            String[] muestraImg = new String[15];
            List<Parte> myList = (ArrayList<Parte>) request.getAttribute("data");
            Parte parte[] = new Parte[30];
            int ln_filas = (myList.size() - 1) / 3;

            for (int i = 0; i < myList.size() - 1; i++) {
                parte[i] = new Parte();
                listaImg[i] = "images/productos/" + "05-" + myList.get(i).getCodigoProducto() + ".jpg";
                parte[i] = myList.get(i);
            }

            for (int i = 0; i < 14; i++) {
                muestraImg[i] = (listaImg[i] == null) ? "images/productos/sinfoto.jpg" : listaImg[i];
            }

        %>


        <div class="container main-container">
            <div class="widget">
                <div class="widget-content" id="main-content">          
                    <html:form action="/CatalogoAction" styleId="frm_generico" method="POST" styleClass="form-horizontal">
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="form-group">
                            <label class="col-md-1 control-label">Familia</label>
                            <div class="col-md-2">
                                <html:select property="familia" styleId="familia"    styleClass="form-control input-sm">
                                    <html:option value=""> -- Seleccione -- </html:option>
                                    <html:options collection="listaFamilia" labelProperty="descripcion" property="indice" />
                                </html:select>                                 
                            </div>
                            <label class="col-md-2 control-label">Clase</label>
                            <div class="col-md-2">
                                <html:select property="clase" styleId="clase"    styleClass="form-control input-sm">
                                    <html:option value=""> -- Seleccione -- </html:option>
                                    <html:options collection="listaClase" labelProperty="descripcion" property="indice" />
                                </html:select>                                  
                            </div>                            
                            <label class="col-md-2 control-label">Grupo</label>
                            <div class="col-md-2">
                                <html:select property="grupo" styleId="grupo"    styleClass="form-control input-sm">
                                    <html:option value=""> -- Seleccione -- </html:option>
                                    <html:options collection="listaGrupo" labelProperty="descripcion" property="indice" />
                                </html:select>                                 
                            </div> 
                            <button title="Confirmar" onclick="aceptarDescuento()"   id="btnConDescuento" class="btn btn-sm btn-success" type="button" ><%=Constantes.ICON_CHECK%> Aceptar</button>                                       
                        </div> 
                        <h1 class="page-header">Cat&aacute;logo</h1>



                        <h3 class="page-header">En el carrito</h3>
                        <div class="panel panel-default">
                            <div class="panel-body">
                                <div class="media">
                                    <div class="media-left"> 
                                        <a href="#"> 
                                            <img alt="64x64" class="media-object" src="data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/PjxzdmcgeG1sbnM9Imh0dHA6Ly93d3cudzMub3JnLzIwMDAvc3ZnIiB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIHZpZXdCb3g9IjAgMCA2NCA2NCIgcHJlc2VydmVBc3BlY3RSYXRpbz0ibm9uZSI+PCEtLQpTb3VyY2UgVVJMOiBob2xkZXIuanMvNjR4NjQKQ3JlYXRlZCB3aXRoIEhvbGRlci5qcyAyLjYuMC4KTGVhcm4gbW9yZSBhdCBodHRwOi8vaG9sZGVyanMuY29tCihjKSAyMDEyLTIwMTUgSXZhbiBNYWxvcGluc2t5IC0gaHR0cDovL2ltc2t5LmNvCi0tPjxkZWZzPjxzdHlsZSB0eXBlPSJ0ZXh0L2NzcyI+PCFbQ0RBVEFbI2hvbGRlcl8xNmM1MjgwNDQyMyB0ZXh0IHsgZmlsbDojQUFBQUFBO2ZvbnQtd2VpZ2h0OmJvbGQ7Zm9udC1mYW1pbHk6QXJpYWwsIEhlbHZldGljYSwgT3BlbiBTYW5zLCBzYW5zLXNlcmlmLCBtb25vc3BhY2U7Zm9udC1zaXplOjEwcHQgfSBdXT48L3N0eWxlPjwvZGVmcz48ZyBpZD0iaG9sZGVyXzE2YzUyODA0NDIzIj48cmVjdCB3aWR0aD0iNjQiIGhlaWdodD0iNjQiIGZpbGw9IiNFRUVFRUUiLz48Zz48dGV4dCB4PSIxMy4xNzE4NzUiIHk9IjM2LjUiPjY0eDY0PC90ZXh0PjwvZz48L2c+PC9zdmc+" data-holder-rendered="true" style="width: 64px; height: 64px;">
                                        </a> 
                                    </div> 
                                    <div class="media-body"> 
                                        <h4 class="media-heading">Lorem ipsum dolor</h4>
                                        Lorem ipsum dolor sit amet, consectetur adipiscing elit. Etiam fermentum magna quis metus laoreet consequat...
                                        <span class="block"><strong>S/ 24.99</strong></span>
                                        <%--<a href="#" class="btn btn-primary btn-xs" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Retirar</span></a>--%>
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
    </body>
</html:html>