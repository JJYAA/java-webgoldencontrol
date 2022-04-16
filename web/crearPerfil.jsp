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
                        <li><strong>Administracion</strong></li>
                        <li>Crear Perfil</li>
                    </ol>
                    <html:form action="/PerfilAction" styleId="frm_generico" method="POST" styleClass="form-horizontal" >
                        <%@include file="jspf/msgs.jspf"%>
                        <div class="panel panel-default">
                            <div class="panel-heading"></div>
                            <div class="panel-body">
                                <div class="form-group">
                                    <label class="col-md-2 control-label"  > Nuevo Perfil</label>
                                    <button title="Confirmar" id="btnConFacRep" onclick="agregarPerfil();"    type="button" ><%=Constantes.ICON_ADD%> </button>                                              
                                </div>  
                            </div>
                        </div>
                        
                            <div class="col-md-12">
                                <div id="c_tablaPerfil"></div>   
                            </div>    

                            <div class="modal fade" id="frm_visualizaPerfil" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">                            
                                            <h4 class="modal-title">Perfil</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div id="c_tablaItemPerfil"></div>    
                                        </div>  
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-sm btn-default" title="Cancelar" onclick="closeVisualiza();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cancelar</span></button>
                                        </div>                                      
                                    </div>                                    
                                </div>                                
                            </div>   
                            <div class="modal fade" id="frm_AgregarPerfil" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">                                
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">                            
                                            <h4 class="modal-title">Adicionar Perfil</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Descripción</label>
                                                    <div class="col-md-5">
                                                        <input type="text" id="auxnombre" name="auxnombre"  class="form-control input-sm" placeholder="Nombre del Perfil"/>
                                                    </div>                                              
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-sm btn-default" title="Aceptar" onclick="GrabarPerfil();"><%=Constantes.ICON_CHECK%><span class="icono-izq">Aceptar</span></button>
                                            <button type="button" class="btn btn-sm btn-default" title="Cancelar" onclick="closePerfil();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cancelar</span></button>
                                        </div>
                                    </div>
                                </div>                                              
                            </div>                                         

                            <div class="modal fade" id="frm_EditarPerfil" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">                            
                                            <h4 class="modal-title">Editar Perfil</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                    <label class="control-label col-md-2">Descripción</label>
                                                    <div class="col-md-5">
                                                        <input type="text" id="auxnombre1" name="auxnombre1"  class="form-control input-sm" placeholder="xxxxxxxxxxxxxxxxl"/>
                                                    </div>                                              
                                            </div>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-sm btn-default" title="Aceptar" onclick="GrabarEditPerfil();"><%=Constantes.ICON_CHECK%><span class="icono-izq">Aceptar</span></button>
                                            <button type="button" class="btn btn-sm btn-default" title="Cancelar" onclick="closeEditPerfil();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cancelar</span></button>
                                        </div>                                      
                                    </div>                                    
                                </div>                                
                            </div>    
                                        
                            <div class="modal fade" id="frm_AgregarOpciones" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog  modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">                            
                                            <h4 class="modal-title">Agregar Opciones</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <div id="c_tablaPendientes"></div>   
                                                </div>                                                    
                                                <div class="col-md-6">
                                                    <div id="c_tablaAsignados"></div>   
                                                </div>                                                                                                 
                                            </div>
                                        </div>
                                        <div class="modal-footer">

                                            <button type="button" class="btn btn-sm btn-default" title="Cancelar" onclick="closeAgregarOpcion();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cancelar</span></button>
                                        </div>                                      
                                    </div>                                    
                                </div>                                
                            </div>  
                            
                            
                            <div class="modal fade" id="frm_AgregarItemPerfil" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-hidden="true">
                                <div class="modal-dialog  modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">                            
                                            <h4 class="modal-title">Asignar Item a perfiles</h4>
                                        </div>
                                        <div class="modal-body">
                                            <div class="form-group">
                                                <div class="col-md-6">
                                                    <div id="c_tablaItemsPendientes"></div>   
                                                </div>                                                    
                                                <div class="col-md-6">
                                                    <div id="c_tablaItemsAsignados"></div>   
                                                </div>                                                                                                 
                                            </div>
                                        </div>
                                        <div class="modal-footer">

                                            <button type="button" class="btn btn-sm btn-default" title="Cancelar" onclick="closeItemPerfil();"><%=Constantes.ICON_CLEAR%><span class="icono-izq">Cancelar</span></button>
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
            <script type="text/javascript" src="js/funciones/jsPerfil.js"></script>
    </body>
</html:html>