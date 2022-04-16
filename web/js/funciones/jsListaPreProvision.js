

$(function () {

    $( "#chkgravado" ).prop( "checked", true );
    
    setCalendariosDependientes({
        obj1: "#fechaIni",
        obj2: "#fechaFin" 
    });
    setCalendariosDependientes({
        obj1: "#fechaDocumento"
    });  
    
    setCalendariosDependientes({
        obj1: "#fechaContable"
    });  
    
    setCalendariosDependientes({
        obj1: "#txtfechaContable"
    }); 
    
    $("#txtfechaContable").val($("#fechaIni").val());

    muestraPreProvisiones_TMP();
    
     $("#btnConAdicionar").click(function () {
   
            $("#frm_generico").validate({
                ignore: [], // validar campos ocultos
                rules: {
                    serieDoc: {required: true},
                    numeroDoc: {required: true},
                    fechaDocumento: {required: true},
                    glosa: {required: true},
                    baseImponible:{required: true},
                    theFilePDF:{required: true}
                },
                messages: {
                    serieDoc: {required: "Ingrese la serie"},
                    numeroDoc: {required: "Ingrese el numero de documento"},
                    fechaDocumento: {required: "Ingrese la fecha"},
                    glosa: {required: "Ingrese una glosa"},
                    baseImponible: {required: "Ingrese la base imponible"},
                    theFilePDF: {required: "Ingrese el PDF"}
                },
                errorLabelContainer: $("#jq-error-container ul"),
                errorElement: 'li',
                showErrors: function () {
                    var nroErrores = this.numberOfInvalids();
                    if (nroErrores > 0) {
                        $("#jq-error-container").show();
                        var msgPrev = "Se encontr\u00f3";
                        var msgPost = "error";
                        if (nroErrores === 1) {
                            msgPrev = "Se ha encontrado";
                            msgPost = "error :";
                        } else if (nroErrores > 1) {
                            msgPrev = "Se han encontrado";
                            msgPost = "errores :";
                        }
                        $("#jq-error-container p strong").html(msgPrev + " " + this.numberOfInvalids() + " " + msgPost);
                    } else {
                        $("#jq-error-container").hide();
                    }
                    this.defaultShowErrors();
                }
            }).form();   
            if ($("#frm_generico").valid())
            {
               grabarPreProvision();

            }            
         
     });    
    
    
});




function muestraPreProvisiones(){
    var filtro="0";

 
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=muestraPreProvisiones",
        cache: false,
        data: {
            fechaIni: $("#fechaIni").val(),
            fechaFin: $("#fechaFin").val(),
            filtro : filtro
            
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                       
                        $("#c_tablaListaPreProvision").html(jsonObjTabla.tabla);

                        iniDataTable({
                            tabla: "#tablaListaPreProvision",
                            filasXpagina: -1,
                            cajaBuscar: true,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });

                        break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });    
}

function Adicionar(){
    $("#serieDoc").attr("readonly",false);
    $("#numeroDoc").attr("readonly",false);
    $("#tipoDocumento").attr("readonly",false);    
    $("#serieDoc").val('');
    $("#numeroDoc").val('');
    $("#tipoDocumento").val('01');
    $("#glosa").val('');
    $("#moneda").val('1');
    $("#fechaDocumento").val('');
    $("#baseImponible").val('');
    $("#modalAdicionar").modal("show");         
}

function grabarPreProvision(){
    
     var totalGasto = $.nullFlo($("#pTotalGasto").val());
     var baseImponible = $.nullFlo($("#baseImponible").val());
     var vMail="0";
     if ($("#chkMail").prop("checked")===true)
     {
       vMail="1";           		
     }  
    
     
     if ((vMail==="0")&&(totalGasto!==baseImponible)){
         toastError("Los totales no cuadran, verifique");
         return false;
     }

    var vGravado="0";
    
    var vRetencion="0";
    if($("#chkgravado").prop("checked")===true)
    {
       vGravado="1";         		
    }  
    if($("#chkretencion").prop("checked")===true)
    {
       vRetencion="1";           		
    }   
   
    
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=grabarPreProvision",
        cache: false,
        data: {
            serieDoc : $("#serieDoc").val(),
            numeroDoc : $("#numeroDoc").val(),
            tipoDocumento : $("#tipoDocumento").val(),
            glosa : $("#glosa").val(),
            rucProveedor : $("#pRucProveedor").val(),
            fechaContable:$("#fechaContable").val(),
            hbl:$("#hbl").val(),
            dua:$("#dua").val(),
            gastos:$("#cuentaGasto").val(),
            gravado:vGravado,
            retencion:vRetencion,
            porgravado:$("#porIgv").val(),
            porretencion:$("#porRetencion").val(),
            notaProveedor:$("#nota").val(),
            chkMail:vMail,
            tipoGasto: $("#tipoGasto").val(),
            poliza : $("#poliza").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        console.log("111111111111111111");
                        break;
                    case "exito":
                       // alert('Actualizado');
                        muestraPreProvisiones_TMP();
                        $("#modalAdicionar").modal("hide"); 
                        //$("#modalAdicionar").modal("hide"); 
                        //alert('Actualizado');
                        //$("#btn_accion").trigger('click');
                       //muestraPreProvisiones_TMP();
                        console.log("holaaaaaaaaaaaaa");
                        break;
                }
            } catch (error) {
                toastError(error );
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });       
}

function prueba(){
    $("#btn_accion").trigger('click');
}

function EliminarDocumento(pRucProveedor,pIndice,pTipoDocumento,pSerie,pDocumento){
    $("#pRucProveedor").val(pRucProveedor);
    $("#pIndice").val(pIndice);
    $("#pTipoDocumento").val(pTipoDocumento);
    $("#pSerie").val(pSerie);
    $("#pDocumento").val(pDocumento);

    
     $("#EliminarModal").modal("show"); 
}

function Eliminar(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=EliminarDocumento",
        cache: false,
        data: {
            rucProveedor:$("#pRucProveedor").val(),
            indice:$("#pIndice").val(),
            serieDoc : $("#pSerie").val(),
            numeroDoc : $("#pDocumento").val(),
            tipoDocumento : $("#pTipoDocumento").val()            
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                       $("#EliminarModal").modal("hide"); 
                       muestraPreProvisiones();                            
                       break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });           
}

function EditarDocumento(pRucProveedor,pIndice,pTipoDocumento,pSerie,pDocumento,pAsiento){
    $("#tipoGasto").val("");
    $("#poliza").val("");
    $("#chkMail").prop("checked", false); 
    $("#pIndice").val(pIndice);
    $("#pTipoDocumento").val(pTipoDocumento);
    $("#pSerie").val(pSerie);
    $("#pDocumento").val(pDocumento);
    $("#pIndice").val(pIndice);
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=EditarDocumento",
        cache: false,
        data: {
            rucProveedor:pRucProveedor,
            indice:pIndice,
            serieDoc : pSerie,
            numeroDoc : pDocumento,
            tipoDocumento : pTipoDocumento            
        },
        success: function (data) {
            try {
                var jsonDatos = data.objDatos;
                var tipoMsg = $.trim(jsonDatos.tipoMsg);
                var msgError = '';
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        $("#pRucProveedor").val(pRucProveedor)
                
                        $("#serieDoc").attr("readonly",true);
                        $("#numeroDoc").attr("readonly",true);
                        $("#tipoDocumento").attr("readonly",true);
                        $("#serieDoc").val(jsonDatos.serieDoc),
                        $("#numeroDoc").val(jsonDatos.numeroDoc),
                        $("#tipoDocumento").val(jsonDatos.tipoDocumento),
                        $("#glosa").val(jsonDatos.glosa),
                        $("#moneda").val(jsonDatos.moneda),
                        $("#fechaDocumento").val(jsonDatos.fechaDocumento),
                        $("#baseImponible").val(jsonDatos.baseImponible) 
                        $("#fechaContable").val(jsonDatos.fechaContable) 
                        $("#hbl").val(jsonDatos.hbl) 
                        $("#dua").val(jsonDatos.dua) 
                        $("#cuentaGasto").val(jsonDatos.cuentaGasto) 
                        $("#nota").val(jsonDatos.notaproveedor);
                        $("#porIgv").val(jsonDatos.porIgv)
                        $("#porRetencion").val(jsonDatos.porRetencion)
                        $("#nota").val(jsonDatos.notaproveedor);
                        $("#poliza").val(jsonDatos.poliza);
                        $("#tipoGasto").val(jsonDatos.tipoGasto);
                        if (jsonDatos.visualizapdf==="1"){
                             $('#visualizaPdf').show(); 
                        } else {
                             $('#visualizaPdf').hide(); 
                        }
                        if (jsonDatos.visualizaxml==="1"){
                             $('#visualizaXml').show(); 
                        } else {
                             $('#visualizaXml').hide(); 
                        }
                        if (jsonDatos.visualizacdr==="1"){
                             $('#visualizaCdr').show(); 
                        } else {
                             $('#visualizaCdr').hide(); 
                        }                 
                        if (pAsiento==="0")
                            $('#btnConAdicionar').attr('disabled',false); 
                        else
                            $('#btnConAdicionar').attr('disabled',true); 
                        
                        $('#tipoDocumento').attr('disabled',true); 
                        $('#moneda').attr('disabled',true); 
                        if (jsonDatos.gravado==="1"){
                            $("#chkgravado").prop("checked", true); 
                        } else {
                            $("#chkgravado").prop("checked", false); 
                        }
                        if (jsonDatos.retencion==="1"){
                            $("#chkretencion").prop("checked", true); 
                        } else {
                            $("#chkretencion").prop("checked", false); 
                        }  
                                               
                        
                        cargaDatosTemporal();
                        $("#modalAdicionar").modal("show"); 
                        break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });             
}


function muestraArchivo(pTipo){
    window.open('PreProvisionAction.do?operacion=muestraArchivo&tipo='+ pTipo + '&rucProveedor=' + $("#pRucProveedor").val() + '&serieDoc=' + $("#pSerie").val() + '&numeroDoc=' + $("#pDocumento").val() +'&tipoDocumento=' + $("#pTipoDocumento").val() + '&indice=' + $("#pIndice").val() , '_blank');	
}


function generaAsiento(){ 
    var ls_anho_ini = $("#fechaIni").val().substring(6,10);
    var ls_anho_fin = $("#fechaFin").val().substring(6,10);
    //if (ls_anho_ini!==ls_anho_fin){
    //    alert('Debe tener el mismo año contable');
    //    return false;
   // }
    var cadena="";
    $('#tablaListaPreProvision').DataTable().rows().iterator('row', function(context, index)
    {
            var node = $(this.row(index).node());         
            var celdas =   node.find("td"); //devolverá las celdas de una fila		
            if (node.find(".chk").prop("checked")===true)  {
                cadena+= node.find(".valor" ).val() + "#";
            }                                      
    });
    if (cadena.length===0){
        alert('No se selecciono ningun Documento');
        return false;
    }
   
    cadena = cadena.substring(0, cadena.length-1);
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=generaAsiento_02",
        cache: false,
        data: {
            selected:cadena,
            //asiento : $("#secuencia").val(),
            fechaContable : $("#txtfechaContable").val()
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);                
                var msgError = jsonObjMsg.msgError;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        toastError(msgError);
                        break;
                    case "exito":
                         muestraPreProvisiones_TMP();
                        break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });                   
}


   

function EliminarAsiento(pRucProveedor,pMes,pAsiento,pTipoDocumento,pTipoComprobante,pSerie,pDocumento,pAnho){
    $("#pRucProveedor").val(pRucProveedor);
    $("#pMes").val(pMes);
    $("#pAsiento").val(pAsiento);
    $("#pTipoDocumento").val(pTipoDocumento);
    $("#pSerie").val(pSerie);
    $("#pDocumento").val(pDocumento);
    $("#pTipoComprobante").val(pTipoComprobante);
    $("#pAnho").val(pAnho);
    $("#EliminarAsiento").modal("show");     
            
}

function EliminarVoucher(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=EliminarAsiento",
        cache: false,
        data: {
            rucProveedor:$("#pRucProveedor").val(),
            mes:$("#pMes").val(),
            anho:$("#pAnho").val(),
            asiento:$("#pAsiento").val(),           
            serieDoc : $("#pSerie").val(),
            numeroDoc : $("#pDocumento").val(),
            tipoDocumento : $("#pTipoDocumento").val() ,
            tipoComprobante : $("#pTipoComprobante").val() 
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        muestraPreProvisiones_TMP();  
                       $("#EliminarAsiento").modal("hide"); 
                                               
                       break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });           
}

function adicionarGastos(){
    var importe = $.nullFlo($("#txtimporte").val());    
    if (importe===0){
        return false;
    }
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=adicionarGastosTemporal",
        cache: false,
        data: {
            importe:$("#txtimporte").val(),
            gasto:$("#cuentaGasto").val(),
            rucProveedor:$("#pRucProveedor").val(),
            descripcion : $('#cuentaGasto').find(":selected").text()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        muestraGastosTemporal();
                         $("#txtimporte").val("");
                         $("#txtimporte").focus();
                       break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });           
}

function muestraGastosTemporal(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=muestraGastosTemporal",
        cache: false,
        data: {
            rucProveedor:$("#pRucProveedor").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                var total = data.objTotal;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":                        
                        $("#pTotalGasto").val(total);
                        $("#c_tablaAddGastos").html(jsonObjTabla.tabla);

                        iniDataTable({
                            tabla: "#tablaAddGastos",
                            filasXpagina: -1,
                            cajaBuscar: false,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });                          
                       break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });           
}

function cargaDatosTemporal(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=cargaDatosTemporal",
        cache: false,
        data: {
            rucProveedor:$("#pRucProveedor").val(),         
            serieDoc : $("#pSerie").val(),
            numeroDoc : $("#pDocumento").val(),
            tipoDocumento : $("#pTipoDocumento").val()  
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                var total = data.objTotal;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":                        
                       muestraGastosTemporal();                      
                       break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });               
}



function EliminarItemTemporal(pitem,pRucProveedor){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=EliminarItemTemporal",
        cache: false,
        data: {
            item:pitem,
            rucProveedor:pRucProveedor
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                var total = data.objTotal;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":                        
                       muestraGastosTemporal();                      
                       break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });               
}



function muestraPreProvisiones_TMP(){ 
    var ls_anho_ini = $("#fechaIni").val().substring(6,10);
    var ls_anho_fin = $("#fechaFin").val().substring(6,10);
   // if (ls_anho_ini!==ls_anho_fin){
   //     alert('Debe tener el mismo año contable');
   //     return false;
   // }
    var cadena="";    
    var filtro="0";
   // if ($("#chkFecha").is(":checked")) 
     //    filtro="1";
 
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PreProvisionAction.do?operacion=muestraPreProvisiones_TMP",
        cache: false,
        data: {
            buscarPor: $("#formaPago").val(),
            fechaIni: $("#fechaIni").val(),
            fechaFin: $("#fechaFin").val(),
            filtro : filtro,
            fechaContable: $("#fechaContable").val()
            
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                            

                        $("#c_tablaListaPreProvision").html(jsonObjTabla.tabla);

                        iniDataTable({
                            tabla: "#tablaListaPreProvision",
                            filasXpagina: -1,
                            cajaBuscar: true,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });

                        break;
                }
            } catch (error) {
                toastError(error);
            }
        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        },
        error: function (xhr, status, error) {
            toastError(error);
        }
    });    
}


function ChangeItemGasto(){
    var valorText = $("#tipoGasto option:selected").text();
    var posicion = valorText.indexOf('-'); // 0
    console.log(posicion);
    var cadena = valorText.substring(0,posicion-1);
    if (cadena==="0")
    {
        console.log("error");
    }
    else
    {
        $("#cuentaGasto").val(cadena);
        $("#txtimporte").focus();
    }
}