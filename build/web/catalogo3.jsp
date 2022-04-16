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
         <%          
          String [] listaImg = new String[100];
          String []muestraImg = new String[15];
          List<Parte> myList = (ArrayList<Parte>) request.getAttribute("data"); 
          Parte parte[] = new Parte[30]; 
          for(int i=0;i<myList.size();i++){
              parte[i] = new Parte();
              listaImg[i] = "images/productos/" + "05-" + myList.get(i).getCodigoProducto() + ".jpg";
              parte[i] = myList.get(i);
          }
          
          for(int i=0;i<14;i++) {
             muestraImg[i] = (listaImg[i]==null)?"images/productos/sinfoto.jpg":listaImg[i];    
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
                        <div class="row" >
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail" >                                   
                                    <img src=<%=muestraImg[0]%> alt="..." style="height:220px">                                  
                                    <div class="caption">
                                        <center><h3><%=parte[0].getCodigoProducto()%></h3></center>                                        
                                        <h4>Precio: <span class="red"><strike>S/ 29.99</strike></span>  S/. <%=parte[0].getPrecioItem()%> </h4>
                                        <p>
                                            <%=parte[0].getDescripcion()%>
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                                <div class="col-md-3 col-sm-4">
                                    <div class="thumbnail" >                                    
                                        <img src=<%=muestraImg[1]%> alt="..." style="height:220px">
                                        <div class="caption">
                                            <center><h3><%=parte[1].getCodigoProducto()%></h3></center> 
                                            <h4>Precio: <span class="red"><strike>S/ 18.60</strike></span> <%=parte[1].getPrecioItem()%></h4>
                                            <p>
                                               <%=parte[1].getDescripcion()%> 
                                            </p>
                                            <p class="text-center">
                                                <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                                <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                            </p>
                                        </div>
                                    </div>
                                </div>
                             
                            
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail" >
                                    <img src=<%=muestraImg[2]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                         <center><h3><%=parte[2].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 15.80</strike></span> <%=parte[2].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[2].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail" >
                                    <img src=<%=muestraImg[3]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[3].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 13.45</strike></span> <%=parte[3].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[3].getDescripcion()%>                                            
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                   <img src=<%=muestraImg[4]%> alt="..." style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[4].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 29.99</strike></span> <%=parte[4].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[4].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[5]%> alt="..." style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[5].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 18.60</strike></span> <%=parte[5].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[7].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[6]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[6].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 15.80</strike></span> <%=parte[6].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[6].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[7]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[7].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 13.45</strike></span> <%=parte[7].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[7].getDescripcion()%>                                          
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>                                        
                        <div class="row">
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[8]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[8].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 29.99</strike></span> <%=parte[8].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[8].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[9]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[9].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 18.60</strike></span> <%=parte[9].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[9].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[10]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[10].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 15.80</strike></span> <%=parte[10].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[10].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[11]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[11].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 13.45</strike></span> <%=parte[11].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[11].getDescripcion()%>                                          
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>                                        
                        <div class="row">
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[12]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[12].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 29.99</strike></span> <%=parte[12].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[12].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[13]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[13].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 18.60</strike></span> <%=parte[13].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[13].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[14]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[14].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 15.80</strike></span> <%=parte[14].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[14].getDescripcion()%> 
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-3 col-sm-4">
                                <div class="thumbnail">
                                    <img src=<%=muestraImg[15]%> alt="..."  style="height:220px">
                                    <div class="caption">
                                        <center><h3><%=parte[15].getCodigoProducto()%></h3></center> 
                                        <h4>Precio: <span class="red"><strike>S/ 13.45</strike></span> <%=parte[15].getPrecioItem()%></h4>
                                        <p>
                                           <%=parte[15].getDescripcion()%>                                          
                                        </p>
                                        <p class="text-center">
                                            <a href="#" class="btn btn-primary" role="button"><%=Constantes.ICON_CART_PLUS%><span class="icono-izq">Agregar al carrito</span></a>
                                            <%--<a href="#" class="btn btn-default" role="button">Button</a>--%>
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>                                        
                                        

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