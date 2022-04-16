//grabarFechaPago;

;
var FilaGlobal ;


$(function () {

$(function () {
    setCalendariosDependientes({
        obj1: "#fechaContable"
    });
});
    
 
    
    $("#btnGrabarPago").prop("disabled", true);
    
});

function pagoProveedor(){
/*-----------------------------------------------------*/    
$("#formaPagotxt").val($("#formaPago").val());
             showLoader();
            $("#operacion").val("grabaPagoProveedor");
            $("#frm_generico").submit();           
                
}

function grabarFechaPago(){
    var cadena="";
    var tipodocumento="";
    var documento="";
    var oTable = $('#tablaListaDocPendientes').DataTable();
    var info = oTable.page.info();      
    var count = info.recordsTotal;  
    var totalDebe = 0;
    if (count===0){return false; }
    $('#tablaListaDocPendientes').DataTable().rows().iterator('row', function(context, index)
        {
            var node = $(this.row(index).node());         
            var celdas =   node.find("td"); //devolverá las celdas de una fila		
            modifica = node.find("#modifica" ).val(); 
            if (modifica==='1'){
                    id = node.find("#id" ).val();
                    campo= "#fechaPago" + id
                    fechaPago = node.find(campo ).val();
                    fechaPago_glosa = node.find("#glosa" ).val(); 
                    tipodocumento = node.find("#tipodocumento" ).val();
                    documento = node.find("#documento" ).val();
                    codigo = node.find("#codigo" ).val();
                    cuentabanco = node.find("#cuentaBancaria" ).val();
                    cuenta = node.find("#cuenta" ).val();
                    if (cuentabanco.length===0) cuentabanco="#";
                    cadena = cadena + tipodocumento.trim() + '|' + documento.trim() + '|' + fechaPago + '|' + fechaPago_glosa + '|' +  codigo + '|' + cuentabanco + '|' + cuenta +  '|,';
                     
            }                                                
     });  
     var resultado = 0;
     cadena = cadena.substring(0,cadena.length - 1 ) ;
     $("#selected").val(cadena);
     if (cadena.length>0){
             showLoader();
            $("#operacion").val("grabaProgramaciondePago");
            $("#formaPagotxt").val($("#formaPago").val())
            $("#frm_generico").submit();        
     }
      
        
}




    



function aceptarItem(pParm){
    $("#rucProveedor").val(pParm);
    cerrarHelpProvDocPendientes();
    muestraDocumentoPendientes();
}

function HelpDocumentosPendientes(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProgramaPagosAction.do?operacion=HelpDocumentosPendientes",
        cache: false,
        data: {
                formaPago : $("#formaPago").val()  ,
                periodo : $("#periodo").val()
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
                        $("#modalHelpDocPendientes").modal("show");
                        $("#c_tablaHelpProveedorDocPendientes").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaHelpProveedorDocPendientes",
                            filasXpagina: 10,
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

function cerrarHelpProvDocPendientes(){
  $("#modalHelpDocPendientes").modal("hide");  
}

function muestraDocumentoPendientes(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProgramaPagosAction.do?operacion=muestraDocumentoPendientesFecha",
        cache: false,
        data: {
            proveedor: $("#rucProveedor").val(),
            formaPago : $("#formaPago").val()   ,
            periodo:$("#periodo").val()  
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

                        $("#c_tablaListaDocPendientes").html(jsonObjTabla.tabla);

                        iniDataTable({
                            tabla: "#tablaListaDocPendientes",
                            filasXpagina: 30,
                            cajaBuscar: true,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });
                        setCalendariosDependientes({
                            obj1: ".fechaPago"
                        });                     
                        $("#rucProveedor").prop("readonly",true);
                        $('#formaPago').attr('disabled', 'disabled');
                        $("#buscarPendientes").prop("disabled", false);
                          
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

function myCambio(event){
    var target = $( event.target );
    FilaGlobal = target.closest("tr");  
    FilaGlobal.find("#modifica").val(1);
     $("#btnGrabarPago").prop("disabled", false);
}



function asignarCuentas(event,pProveedor,pCuenta){
    var target = $( event.target );
    FilaGlobal = target.closest("tr");  
    
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "fechaProgramacionDocAction.do?operacion=asignarCuentasProveedor",
        cache: false,
        data: {
                proveedor : pProveedor,
                cuenta : pCuenta
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
                        $("#modalCuentasProveedor").modal("show");
                        $("#c_tablaListaCuentasBancarias").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaListaCuentasBancarias",
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

function cerrarHelpCuentasProveedor(){
    $("#modalCuentasProveedor").modal("hide");
}

function aceptarItemCuenta(item){
    //var target = $( event.target );
    //FilaGlobal = target.closest("tr");    
    $("#modalCuentasProveedor").modal("hide");
    FilaGlobal.find("#cuentaBancaria").val(item);
    FilaGlobal.find("#modifica").val(1);
     $("#btnGrabarPago").prop("disabled", false);
    
}