$(function () {
     $("#btnGrabarPago").prop("disabled", true);
    setCalendariosDependientes({
        obj1: "#fechaIni"
    });
});
    

function cerrarHelpProvDocPendientes(){
  $("#modalHelpDocPendientes").modal("hide");  
}

function muestraDocumentoPendientes(){
    if ($("#periodo").val().length===0){
        toastError("Debe ingresar el Periodo");
        return false;        
    }
    if ($("#rucProveedor").val().length===0){
        toastError("Debe ingresar el Ruc del proveedor");
        return false;
    }
    anhoFin = $("#periodo").val();
    /*var anhoIni = $("#fechaIni").val().substring(6,10);
    var anhoFin = $("#fechaFin").val().substring(6,10);
    if (anhoIni!==anhoFin){
          toastError("Deben pertener al mismo periodo");
        return false;
    }*/
    
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProgramaPagosAction.do?operacion=muestraDocumentoPendientesRango",
        cache: false,
        data: {
            proveedor: $("#rucProveedor").val(),
            formaPago : $("#formaPago").val()   ,
            periodo : anhoFin,
            fechaIni  :$("#fechaIni").val()  ,
            fechaFin  :$("#fechaIni").val()  
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
                            filasXpagina: 10,
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

function aceptarItem(pParm){
    $("#rucProveedor").val(pParm);
    cerrarHelpProvDocPendientes();
    muestraDocumentoPendientes();
}


function HelpDocumentosPendientesRango(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProgramaPagosAction.do?operacion=HelpDocumentosPendientesRango",
        cache: false,
        data: {
               formaPago : $("#formaPago").val()  ,
               fecha : $("#fechaIni").val()  
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


function grabarPago(){
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
               cuentaBanco=node.find("#cuentaBanco" ).val();
               if (cuentaBanco.length ===0)     
                   valor = valor + 1;  
            }                                                
     }); 
    
    if (valor>0){
          toastError("Existen documenos sin cuenta bancaria");
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
                     glosaPago = node.find("#glosa" ).val();
                     cuentaBanco=node.find("#cuentaBanco" ).val();
                     cadena = cadena + tipodocumento.trim() + '|' + documento.trim() + '|' + debe +  '|' + glosaPago + '|' + cuentaBanco +  ',';
                     
            }                                                
     });  
     var resultado = 0;
     cadena = cadena.substring(0,cadena.length - 1 ) ;
     if (cadena.length===0) return false;
     $("#selected").val(cadena);
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


function pagoProveedor(){
        
        $("#formaPagotxt").val($("#formaPago").val());
        showLoader();
        $("#operacion").val("grabaPagoProveedor");
        $("#frm_generico").submit();           
                
}

    

function validaSaldoDocumentos(pParm){
    return $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "fechaProgramacionDocAction.do?operacion=validaSaldoDocumentos",
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
