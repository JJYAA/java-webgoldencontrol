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
                        <li><strong>Archivos</strong></li>
                        <li>Usuario-Perfil</li>
                    </ol>
                    <html:form action="/PerfilAction" styleId="frm_generico" method="POST" styleClass="form-horizontal" >
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading"></div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label"  > Nuevo Usuario</label>
                                    <button title="Confirmar" id="btnConFacRep" onclick="agregarUsuario();"    type="button" ><%=Constantes.ICON_ADD%> </button>                                              
                                </div>  
                            </div>
                        </div>
                        
                            <div class="col-md-12">
                                <div id="c_tablaUsuarioPerfil"></div>   
                            </div>    

                             
                            
                            <div class="modal fade" id="frm_EditarUsuario" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">                            
                                            <h4 class="modal-title">Editar Usuario</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">DNI</label>
                                                    <div class="col-md-4">
                                                        <input type="text" id="dni" name="dni"  class="form-control numeros"  maxlength="15" placeholder="DNI"/>
                                                    </div>                                              
                                            </div>                                            
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Nombre</label>
                                                    <div class="col-md-7">
                                                        <input type="text" id="nombre" name="nombre"  class="form-control input-sm upper" placeholder="Nombre"/>
                                                    </div>                                              
                                            </div>
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Perfil</label>
                                                    <div class="col-md-5">                                                      
                                                        <html:select property="perfil" styleId="perfil"    styleClass="form-control input-sm">
                                                                    <html:options collection="listaPerfiles" labelProperty="descripcion" property="indice" />
                                                        </html:select>                                                  
                                                    </div>                                              
                                            </div>                                            
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Activo</label>
                                                    <div class="col-md-5">                                                      
                                                        <input type="checkbox" id="chk" value="chk" checked="form-control input-sm">
                                                    </div>                                              
                                            </div>                                              
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Usuario</label>
                                                    <div class="col-md-5">                                                      
                                                        <input type="text" id="txtusuario" name="txtusuario"  class="form-control input-sm upper" placeholder="Usuario"/>
                                                    </div>                                              
                                            </div> 
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Contraseña</label>
                                                    <div class="col-md-5">                                                      
                                                        <input type="password" id="txtpassword" name="txtpassword"  class="form-control input-sm upper" placeholder="Contraseña"/>
                                                    </div>                                              
                                            </div>                                             
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-sm btn-default" title="Aceptar" onclick="GrabarEditUsuario();"><%=Constantes.ICON_CHECK%><span class="icono-izq">Aceptar</span></button>
                                            <button type="button" class="btn btn-sm btn-default" title="Cancelar" onclick="closeEditUsuario();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cancelar</span></button>
                                        </div>                                      
                                    </div>                                    
                                </div>                                
                            </div>    
                                        

                            <html:hidden property="operacion" styleId="operacion"></html:hidden>
                            <html:hidden property="nombre" styleId="nombre"></html:hidden>
                            <input type=hidden name="codigo" id="codigo">
                             
                    </html:form>                    
                </div>
            </div>

            <%-- Scripts --%>
            <%@include file="jspf/js.jspf" %>   
            <script type="text/javascript" src="js/funciones/jsUsuarioPerfil.js"></script>
    </body>
</html:html>