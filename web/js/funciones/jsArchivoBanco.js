/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(function () {

    setCalendariosDependientes({
        obj1: "#fechaIni",
        obj2: "#fechaFin"
    });
});

function muestraDocumentoCancelados(){
    var anhoIni = $("#fechaIni").val().substring(6,10);
    var anhoFin = $("#fechaFin").val().substring(6,10);
    if (anhoIni!==anhoFin){
         toastError("El periodo debe ser el mismo");
         $("#c_tablaListaDocCancelados").html("");
        return false;
    }
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ArchivoBancoAction.do?operacion=muestraPlanillasGeneradas", //muestraDocumentoCancelados
        cache: false,
        data: {
               //formaPago : $("#buscarPor").val()  ,
               fechaIni : $("#fechaIni").val() , 
               fechaFin : $("#fechaFin").val()                 
              // tipoBancoMoneda : $("#txttipoBancoMoneda").val()
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
                        
                        //$("#rucProveedor").prop("readonly",true);
                        //$('#formaPago').attr('disabled', 'disabled');
                        //$("#tipoBancoMoneda").prop("disabled", true);    
                        
                        $("#c_tablaListaDocCancelados").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaListaDocCancelados",
                            filasXpagina: 30,
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

function grabartxtBanco(){
        $("#bancoMoneda").val($("#txttipoBancoMoneda").val());
	$("#operacion").val("enviotxtBanco");
	enviaForm({
		id : "frm_generico",
		loading : false
	});    
    
}


function muestratxt(pCodigo){
        $("#rucProveedor").val(pCodigo);
        $("#bancoMoneda").val($("#txttipoBancoMoneda").val());
	$("#operacion").val("enviotxtBanco");
	enviaForm({
		id : "frm_generico",
		loading : false
	});     
}


function muestratxtnew(pplanilla){
        $("#planilla").val(pplanilla);
        //$("#bancoMoneda").val($("#txttipoBancoMoneda").val());
	$("#operacion").val("muestratxtnew");
	enviaForm({
		id : "frm_generico",
		loading : false
	});     
}

function eliminarAsiento(pPlanilla,pMes,pAnho,pTipoComprobante,pAsiento){
    $("#txtpla").val(pPlanilla);
    $("#txtmes").val(pMes);
    $("#txtanho").val(pAnho);
    $("#txttipocomprobante").val(pTipoComprobante);
    $("#txtasiento").val(pAsiento);
    $("#EliminarAsiento").modal("show");  
}
function EliminarVoucherPlanilla(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ArchivoBancoAction.do?operacion=EliminarVoucherPlanilla", //muestraDocumentoCancelados
        cache: false,
        data: {
               planilla : $("#txtpla").val()  ,
               mes : $("#txtmes").val() , 
               anho : $("#txtanho").val() ,                
               tipocomprobante : $("#txttipocomprobante").val(),
               asiento:$("#txtasiento").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                         $("#EliminarAsiento").modal("hide");  
                        muestraDocumentoCancelados();
                       
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


function muestraDocumentoPlanilla(){
    var anhoIni = $("#fechaIni").val().substring(6,10);
    var anhoFin = $("#fechaFin").val().substring(6,10);
    if (anhoIni!==anhoFin){
         toastError("El periodo debe ser el mismo");
        return false;
    }
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ArchivoBancoAction.do?operacion=muestraDocumentoPlanilla", //muestraDocumentoCancelados
        cache: false,
        data: {
               //formaPago : $("#buscarPor").val()  ,
               fechaIni : $("#fechaIni").val() , 
               fechaFin : $("#fechaFin").val()                 
              // tipoBancoMoneda : $("#txttipoBancoMoneda").val()
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
                        
                        //$("#rucProveedor").prop("readonly",true);
                        //$('#formaPago').attr('disabled', 'disabled');
                        //$("#tipoBancoMoneda").prop("disabled", true);    
                        
                        $("#c_tablaListaDocCancelados").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaListaDocCancelados",
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


 function pipili() {
        alert('11111111');
        $("#operacion").val("exportarExcelStock");
        enviaForm({
            id: "frm_generico",
            loading: false
        });
    }
