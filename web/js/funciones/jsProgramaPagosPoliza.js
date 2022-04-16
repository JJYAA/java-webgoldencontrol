

$(function () {

$(function () {
    setCalendariosDependientes({
        obj1: "#fechaContable"
    });
});
    
 
    
    $("#btnGrabarPago").prop("disabled", true);
    
});

function pagoProveedorPoliza(){
/*-----------------------------------------------------*/    
    $("#asiento").val($("#asiento").val());
    $("#formaPagotxt").val($("#formaPago").val());
    $("#glosa").val($("#txtglosa").val());
     showLoader();
    $("#operacion").val("grabaPagoProveedorPoliza");
    $("#frm_generico").submit();           
                
}

function grabarPagoPoliza(){
    var valor = $("#asiento").val().length;
    var valorGlosa = $("#txtglosa").val().length;
    var moneda='S';
    
    if (valor===0){
         toastError("Debe ingresar  Nro de asiento");
        return false;
    }
    if (valorGlosa===0){
         toastError("Debe ingresar  la GLOSA");
        return false;
    }    
    if (($("#formaPago").val()==='08')||($("#formaPago").val()==='10')){
        moneda='D';
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
                     ruc = node.find("#ruc" ).val();
                     tipodocumento = node.find("#tipodocumento" ).val();
                     documento = node.find("#documento" ).val();
                     glosa = node.find("#glosa" ).val();
                     cuentaBanco = node.find("#cuentabanco" ).val();
                     if (glosa==="") {
                         glosa=".";
                     }
                     cadena = cadena + tipodocumento.trim() + '|' + documento.trim() + '|' + debe + '|' +  glosa + '|' + ruc +  '|'  + cuentaBanco +  ','; 
                     
            }                                                
     });  
     var resultado = 0;
     cadena = cadena.substring(0,cadena.length - 1 ) ;
    $.when(validaSaldoDocumentos(cadena)).then(function (data, textStatus, jqXHR) 
    {    
        var resultado = data.objMensaje.resultado; 
        if (resultado===0)
        {                  
            pagoProveedorPoliza();
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
        url: "ProgramaPagosPolizaAction.do?operacion=validaSaldoDocumentos",
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







function muestraDocumentoPendientes(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "ProgramaPagosPolizaAction.do?operacion=muestraDocumentoPendientes",
        cache: false,
        data: {
            proveedor: '%', //$("#rucProveedor").val(),
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
                            filasXpagina: 100,
                            cajaBuscar: true,
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


function muestratxtnew(pplanilla){
        $("#planilla").val(pplanilla);
	$("#operacion").val("muestratxtnew");
	enviaForm({
		id : "frm_generico",
		loading : false
	});     
}