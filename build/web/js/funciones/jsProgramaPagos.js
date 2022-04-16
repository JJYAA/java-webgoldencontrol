

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
    $("#asiento").val($("#asiento").val());
    $("#glosa").val($("#txtglosa").val());    
    showLoader();
    $("#operacion").val("grabaPagoProveedor");
    $("#frm_generico").submit();           
                
}

function grabarPago(){
    var valor = $("#asiento").val().length;    
    var lglosa = $("#txtglosa").val().length;  
    if (valor===0){
         toastError("Debe ingresar  Nro de asiento");
        return false;
    }    
    if (lglosa===0){
        toastError("Se debe ingresar la GLOSA");
        return false;
    }
        
    var cadena="";
    var tipodocumento="";
    var documento="";
    var oTable = $('#tablaListaDocPendientes').DataTable();
    var info = oTable.page.info();      
    var count = info.recordsTotal;  
    var totalDebe = 0;
    if (count===0){return false; }
    var totalDebe = $.nullFlo($("#totaldebe").val());   
    if (totalDebe===0) {return false; }
    var valor = 0;
    $('#tablaListaDocPendientes').DataTable().rows().iterator('row', function(context, index)
    {
            var node = $(this.row(index).node());         
            var celdas =   node.find("td"); //devolverá las celdas de una fila		
            debe = $.nullFlo(node.find("#debe" ).val()); 
            if (debe>0){
                     
                     cuentaBanco = node.find("#cuentabanco" ).val();
                     if (cuentaBanco.length===0)
                         valor = valor + 1;
            }         
    }); 
    if (valor>0){
         toastError("Existen documentos sin cuenta bancaria");
        return false;
    }
    $('#tablaListaDocPendientes').DataTable().rows().iterator('row', function(context, index)
        {
            var node = $(this.row(index).node());         
            var celdas =   node.find("td"); //devolverá las celdas de una fila		
            debe = $.nullFlo(node.find("#debe" ).val()); 
            if (debe>0){
                     totalDebe = totalDebe + debe;
                     tipodocumento = node.find("#tipodocumento" ).val();
                     documento = node.find("#documento" ).val();
                     glosa = node.find("#glosa" ).val();
                     cuentaBanco = node.find("#cuentabanco" ).val();
                     if (glosa==="") {
                         glosa=$("#txtglosa").val();
                     }
                     cadena = cadena + tipodocumento.trim() + '|' + documento.trim() + '|' + debe + '|' +  glosa + '|'  +  $("#rucProveedor").val()  +  '|'  + cuentaBanco + ','; 
                     
            }                                                
     });  
     var resultado = 0;
     cadena = cadena.substring(0,cadena.length - 1 ) ;
    $.when(validaSaldoDocumentos(cadena)).then(function (data, textStatus, jqXHR) 
    {    
        var resultado = data.objMensaje.resultado; 
        if (resultado===0)
        {                  
            pagoProveedor();
        } 
        else 
        {
             toastError("Error en el Importate a Pagar");
        }             
                                                                            
    });      
        
}




    

function validaSaldoDocumentos(pParm){
    return $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProgramaPagosAction.do?operacion=validaSaldoDocumentos",
        cache: false,
        data: {
             formaPago : $("#formaPago").val()  ,
             proveedor: $("#rucProveedor").val(),
             selected:pParm,
             fechaContable:$("#fechaContable").val()
        },
        success: function (data) {
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
                periodo : $("#fechaContable").val().substring(6,10)
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
        url: "ProgramaPagosAction.do?operacion=muestraDocumentoPendientes",
        cache: false,
        data: {
            proveedor: $("#rucProveedor").val(),
            formaPago : $("#formaPago").val() ,
            periodo : $("#fechaContable").val().substring(6,10)
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
                            cajaBuscar: false,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });
                        $(".decimal-2").numeric({
                            allowMinus: false,
                            allowThouSep: false,
                            maxDecimalPlaces: 2
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
function adicionar(){
    
    $("#tablaListaDocPendientes").dataTable().fnDestroy();
    $("#tablaListaDocPendientes>tbody").prepend("<tr><td>00</td><td>documento</td><td>0</td><td><input type=\"text\" id=\"debe\"  onchange=\"myCalculo(event)\" name=\"debe\" class=\"decimal-2\"  readonly=\"false\"></td><td><input type=\"text\" id=\"haber\" name=\"haber\"  class=\"decimal-2\" onchange=\"myCalculo(event)\"></td><td><input type=\"text\" id=\"glosa\"></td></tr>");
     iniDataTable({
                            tabla: "#tablaListaDocPendientes",
                            filasXpagina: 30,
                            cajaBuscar: false,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });
 //$("#modalAdicionar").modal("show");    
}

function myCalculo(event){
    var totalDebe=0;
    var totalHaber=0;
    var debe = 0;
    var haber = 0;
    var linea = 0;
    
    $('#tablaListaDocPendientes').DataTable().rows().iterator('row', function(context, index)
        {
            console.log(index);
            var node = $(this.row(index).node());         
            var celdas =   node.find("td"); //devolverá las celdas de una fila		
            //modificado =  node.find("#modificado").val(); 
            //cierre =  node.find("#cierre").val(); 
            //if (modificado==="1"||cierre==="1")
            //{
                debe = $.nullFlo(node.find("#debe" ).val()); 
                haber =  $.nullFlo(node.find("#haber" ).val()); 
                totalDebe = totalDebe + debe;
                totalHaber = totalHaber + haber;         
               
        });  
        if ((totalDebe + totalHaber)>0)
            $("#btnGrabarPago").prop("disabled", false);
        else
            $("#btnGrabarPago").prop("disabled", true);
        
        totalDebe = totalDebe.toFixed(2);
        totalHaber = totalHaber.toFixed(2);

        $("#totaldebe").val(totalDebe);
        $("#totalhaber").val(totalHaber);
        
        
        
}
