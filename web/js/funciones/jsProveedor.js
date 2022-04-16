/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(function () 
{   
    $("#bcpsolTipo option[value='0']").attr("selected", true);
    $("#bcpdolTipo option[value='0']").attr("selected", true);
    $("#bbvasolTipo option[value='0']").attr("selected", true);
    $("#bbvadolTipo option[value='0']").attr("selected", true);
    
    muestraListaProveedores('%');
    

    

});

function ConsultarProveedor(){
    muestraListaProveedores('%');
}

function muestraListaProveedores(parm1){    
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProveedorAction.do?operacion=muestraListaProveedores",
        cache: false,
        data: {
            codigo:parm1,
            proceso : $("#proceso").val(),
            periodo : $("#periodo").val()
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);                
                var jsonObjTabla = data.objTabla;
                var msg = jsonObjMsg.msgError;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        toastError(msg);
                        $("#c_tablaProveedores").html("");
                        break;
                    case "exito":
                        $("#c_tablaProveedores").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaProveedores",
                            filasXpagina: 20,
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




function myCambio(e){
    var target = $( event.target );
    var Cel = target.closest("td");
    Cel.find("span").eq(0).text(target.val());
    var filas = target.closest("tr");       
    var celdas = $(filas).find("td"); //devolverá las celdas de una fila
    var DocNum =  filas.find(".chk").val(); 
     filas.find("#cambio").val("1");   
}



function grabar(){
    var cadena="";
    $('#tablaProveedores').DataTable().rows().iterator('row', function(context, index)
    {
            var node = $(this.row(index).node());         
            var celdas =   node.find("td"); //devolverá las celdas de una fila		
            cambio =  node.find("#cambio").val();            
            if (cambio==="1")
            {
                if (node.find(".chk").prop("checked")===true)    
                   activo="1";
                else
                   activo="0" ;   
               codigoRuc = node.find(".ruc" ).val(); 
               cadena+= codigoRuc + "," + activo + "#";
            }
    });
    if (cadena.length===0){
        alert('No se selecciono ningun proveedor');
        return false;
    }
    $("#xx").val(cadena);
    cadena = cadena.substring(0, cadena.length-1);
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProveedorAction.do?operacion=actualizaAccesoProv",
        cache: false,
        data: {
            selected:cadena
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);                
               // var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                         muestraListaProveedores('%');
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

function cuentasProveedor(pCodigo){
    $("#codigo").val(pCodigo);
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProveedorAction.do?operacion=datosCuentas",
        cache: false,
        data: {
            rucProvedor : $("#codigo").val()          
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);   
                var jsonObjDatos = data.objDatos; 
               // var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        $("#bcpSoles").val(jsonObjDatos.bcpSoles);
                        $("#bcpDolar").val(jsonObjDatos.bcpDolar);
                        $("#bbvaSoles").val(jsonObjDatos.bbvaSoles);
                        $("#bbvaDolar").val(jsonObjDatos.bbvaDolar);
                        
                        //$("#scotiabankICC").val(jsonObjDatos.scotiabankICC);
                        //$("#interbankICC").val(jsonObjDatos.interbankICC);  
                        if (jsonObjDatos.tipobcpSoles==='0')
                            $("#bcpsolTipo option[value='']").attr("selected", true);
                        else {
                        if (jsonObjDatos.tipobcpSoles==='C')
                             $("#bcpsolTipo option[value='C']").attr("selected", true);
                        else
                             $("#bcpsolTipo option[value='M']").attr("selected", true);
                        }
                        
                        if (jsonObjDatos.tipobcpDolar==='0'){
                            $("#bcpdolTipo option[value='']").attr("selected", true);
                        } else {
                        if (jsonObjDatos.tipobcpDolar==='C')
                             $("#bcpdolTipo option[value='C']").attr("selected", true);
                        else
                             $("#bcpdolTipo option[value='M']").attr("selected", true);                        
                        }
                       
                       
                        if (jsonObjDatos.tipobbvaSoles==='0') {
                            $("#bbvasolTipo option[value='0']").attr("selected", true);
                        } else {
                        if (jsonObjDatos.tipobbvaSoles==='C')
                            $("#bbvasolTipo option[value='C']").attr("selected", true);
                        else
                            $("#bbvasolTipo option[value='M']").attr("selected", true);
                        }
                        
                        
                        if (jsonObjDatos.tipobbvaDolar==='0') {
                             $("#bbvadolTipo option[value='0']").attr("selected", true);
                        } else {
                       if (jsonObjDatos.tipobbvaDolar==='C')
                            $("#bbvadolTipo option[value='C']").attr("selected", true);
                       else
                            $("#bbvadolTipo option[value='M']").attr("selected", true);                                                
                        }
                        
                        
                        $("#modalCuentasProveedor").modal("show"); 
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
function grabarCuentaProveedor(){
    var vbcpSoles = $("#bcpSoles").val();
    var vbcpDolar = $("#bcpDolar").val();
    var vbbvaSoles = $("#bbvaSoles").val();
    var vbbvaDolar = $("#bbvaDolar").val();
    //var vscotiabankICC = $("#scotiabankICC").val();
    //var vinterbankICC = $("#interbankICC").val();    
    
    
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProveedorAction.do?operacion=actualizacuentas",
        cache: false,
        data: {
            rucProvedor : $("#codigo").val(),
            bcpSoles: vbcpSoles,
            bcpDolar: vbcpDolar,
            bbvaSoles: vbbvaSoles,
            bbvaDolar: vbbvaDolar,
            //scotiabankICC: vscotiabankICC,
            //interbankICC: vinterbankICC,
            
            tipobcpSoles : $("#bcpsolTipo").val(),
            tipobcpDolar :  $("#bcpdolTipo").val(),
            tipobbvaSoles :  $("#bbvasolTipo").val(),
            tipobbvaDolar :  $("#bbvadolTipo").val()
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);                
               // var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        $("#modalCuentasProveedor").modal("hide"); 
                         muestraListaProveedores('%');
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
function grabarCuentaProveedorXX(){
    alert($("#bcpSoles").val());
    return false;
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProveedorAction.do?operacion=actualizaCuentasProveedor",
        cache: false,
        data: {
            rucProvedor : $("#codigo").val(),
            bcpSoles: $("#bcpSoles").val(),
            bcpDolar: $("#bcpDolar").val(),
            bbvaSoles: $("#bbvaSoles").val(),
            bbvaDolar: $("#bbvaDolar").val()
            //scotiabankICC: $("#scotiabankICC").val(),
            //interbankICC: $("#interbankICC")              
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);                
               // var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                       $("#modalCuentasProveedor").modal("hide"); 
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

function cerrarCuentaProveedor(){
  $("#modalCuentasProveedor").modal("hide");    
}


function grabarCuentaEmpresa(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProveedorAction.do?operacion=grabarCuentaEmpresa",
        cache: false,
        data: {
           
            bcpSoles: $("#bcpSoles").val(),
            bcpDolar: $("#bcpDolar").val(),
            bcpsolTipo: $("#bcpsolTipo").val(),
            bcpdolTipo: $("#bcpdolTipo").val()     
        }  ,
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);                
               // var jsonObjTabla = data.objTabla;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                       $("#modalCuentasProveedor").modal("hide"); 
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