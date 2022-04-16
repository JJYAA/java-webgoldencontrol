$(function () {

    //$("#clienteNumeroDocumento").attr("maxlength", "8");
    // Cambia cantidad
    $("#cantidad").on("keypress", function (e) {
        var key = checkKeyCode(e);
        if (key === 13) {

            if ($.nullNum($("#cantidad").val()) > 0) {
                $("#descuento").focus().select();
            }

        }
    });

    $("#moneda").on("change", function () {
        if ($(this).val() === "1") {
            $(".signo_moneda").html("S/");
        } else {
            $(".signo_moneda").html("US$");
        }
    });
    $("#clienteTitulo").on("change", function () {
        $.when(cboDocPorTitPerJSON("clienteTitulo", "clienteTipoDocumento", false, false)).done(function () {
            if ($("#clienteTitulo").val() === "02") {
                $(".c_cliPerNat").hide();
                $(".c_cliPerJur").show();
            } else {
                $(".c_cliPerNat").show();
                $(".c_cliPerJur").hide();
            }
            propNumDocPorTipo("clienteTipoDocumento", "clienteNumeroDocumento"); 
        });
    });

    $("#clienteTipoDocumento").on("change", function () {
            propNumDocPorTipo("clienteTipoDocumento", "clienteNumeroDocumento");            //valoresDefaultFact();        
    });

    $("#clienteDepartamento").on("change", function () {

        $("#clienteDistrito").empty();
        $.when(cboProJSON("clienteDepartamento", "clienteProvincia", false)).done(function () {
            $("#clienteProvincia").val(objCli.codigoProvincia);
            $.when(cboDistritosJSON("clienteDepartamento", "clienteProvincia", "clienteDistrito", false)).done(function () {
                $("#clienteDistrito").val(objCli.codigoDistrito);
            });
        });
    });

    $("#clienteProvincia").on("change", function () {
        //$("#clienteDepartamento").val(objCli.codigoDepartamento);
        $.when(cboDistritosJSON("clienteDepartamento", "clienteProvincia", false)).done(function () {

        });
    });

});








function cboDocPorTitPerJSON(cboTitPer, cboDocPer, cboDocPerOpcSel, cboDocPerLblSel)
{
    var auxCboDocPer = $("#" + cboDocPer);
    auxCboDocPer.empty();
    if ($("#" + cboTitPer).val() === '01')
    {
        auxCboDocPer.append('<option value="02">DNI</option>');
        auxCboDocPer.append('<option value="06">NATURAL CON RUC</option>');
    }
    else
    {
        auxCboDocPer.append('<option value="01">RUC</option>');
    }
}



function propNumDocPorTipo(tipDoc, numDoc) {
    var tipoDoc = $("#" + tipDoc).val();
    var numeroDoc = $("#" + numDoc);
    switch (tipoDoc) {
        
        case "01","06": // RUC
            //numeroDoc.autotab('filter', {format: 'number', maxlength: 12});
            $("#clienteNumeroDocumento").mask('0#');
            $("#clienteNumeroDocumento").attr("maxlength", "11");
            $("#clienteNumeroDocumento").val(numeroDoc.val().slice(0, 12));
            break;
        case "02": // DNI
            $("#clienteNumeroDocumento").mask('0#');
            $("#clienteNumeroDocumento").attr("maxlength", "8");
            $("#clienteNumeroDocumento").val(numeroDoc.val().slice(0, 8));
            break;
        default:   // OTROS
            //numeroDoc.autotab('filter', {format: 'all', uppercase: true, maxlength: 11});
            numeroDoc.mask('A', {
                translation: {
                    'A': {pattern: /[A-Za-z0-9\s]/, recursive: true}
                },
                onKeyPress: function (value, event) {
                    event.currentTarget.value = value.toUpperCase();
                }
            });
            numeroDoc.attr("maxlength", "11");
            numeroDoc.val(numeroDoc.val().slice(0, 11));
    }
}


function aceptarItem(item, precio) {
    $("#itemCodigo").val(item);
    $("#modalPreciosProductos").modal("hide");
    $("#vvp").val(precio);
    $("#cantidad").focus();
}

function muestraHelpPrecios() {

    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PedidoOfertaRepAction.do?operacion=muestraListaProductos",
        cache: false,
        data: {
            numeroParte: $("#numeroParte").val()
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
                        $("#modalPreciosProductos").modal("show");
                        $("#c_tablaPartesProductos").html(jsonObjTabla.tabla);

                        iniDataTable({
                            tabla: "#tablaPartesProductos",
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


function buscarProductoEnter() {
    buscarProducto();
}

function buscaParte(e)
{
    var key = checkKeyCode(e);
    if (key === 13) {
        buscarProducto();
    }
}
function buscarProducto() {
     
    var numeroParte = $.nullCad($("#numeroParte").val());
    var auxCliFueBus = "S";
    if (numeroParte.length > 0 && auxCliFueBus === "S") 
    {
        $("#numeroParte").blur();
        $.ajax({
            type: "POST",
            dataType: "JSON",
            url: "PedidoOfertaRepAction.do?operacion=buscaParteSAP",
            cache: false,
            data: {
                numeroParte: $("#numeroParte").val(),
                cantidad: $("#cantidad").val(),
                auxFacTipDoc: $("#auxFacTipDoc").val(),
                codCliente: $("#clienteNumeroDocumento").val(),
                moneda: $("#moneda").val(),
                auxProceso: $("#auxproceso").val()
            },
            success: function (data) {
                try {
                    var jsonObjMsg = data.objMensaje;
                    var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                    var msgError = $.trim(jsonObjMsg.msgError);
                    var jsonObjParte = data.objParte;
                    switch (tipoMsg) {
                        case "relogin":
                            window.location = "relogin.jsp";
                            break;
                        case "error":
                            toastError(msgError);
                            $("#descripcion").val("");
                            $("#stockTotal").val("");
                            $("#cajas").val(jsonObjParte.cajas);
                            $("#stockDis").val(jsonObjParte.stockDis);
                            $("#vvp").val(jsonObjParte.vvp);
                            $("#cantidad").val("");
                            $("#descuento").val("");
                            if (jsonObjParte.ctosRegs > 0) {
                                $("#btnConFacRep").prop("disabled", false);
                            }
                            break;
                        case "exito":
                            $("#descripcion").val(jsonObjParte.descripcion);
                            $("#stockTotal").val(jsonObjParte.stockTotal);
                            $("#cajas").val(jsonObjParte.cajas);
                            $("#stockDis").val(jsonObjParte.stockDis);
                            $("#existeRep").val(jsonObjParte.existeRep);
                            $("#numItemAux").val(jsonObjParte.itemRepuesto);
                            //$("#cantidad").focus();
                            if (jsonObjParte.ctosRegs > 0) {
                                $("#btnConFacRep").prop("disabled", false);
                            }
                            muestraHelpPrecios();
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
}


function itemsPedidoOferta() {
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PedidoOfertaRepAction.do?operacion=muestraItemsPedidoOferta",
        cache: false,
        data: {
            docEntry: $("#docEntryAux").val(),
            moneda: $("#moneda").val()
        },
        success: function (data) {
            try {

                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
                var msgError = $.nullCad(jsonObjMsg.msgError);
                var msgInfo = $.nullCad(jsonObjMsg.msgInfo);
                var msgLineaCredito = $.nullCad(jsonObjMsg.msgLineaCredito);

                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    default:
                        if (msgError.length > 0) {
                            toastError(msgError);
                        } else {
                            var jsonObjTot = data.objTotales;
                            var jsonObjTabla = data.objTabla;

                            // Totales
                            if ($("#moneda").val() === "1") {
                                $("#totalBruto").val(jsonObjTot.totBrutoSol);
                                $("#totalDescuento").val(jsonObjTot.totDsctoSol);
                                $("#totalNeto").val(jsonObjTot.totVtaSol);
                                $("#totalIgv").val(jsonObjTot.totIgvSol);
                                $("#totalGeneral").val(jsonObjTot.totGralVtaSol);
                            } else {
                                $("#totalBruto").val(jsonObjTot.totBrutoDol);
                                $("#totalDescuento").val(jsonObjTot.totDsctoDol);
                                $("#totalNeto").val(jsonObjTot.totVtaDol);
                                $("#totalIgv").val(jsonObjTot.totIgvDol);
                                $("#totalGeneral").val(jsonObjTot.totGralVtaDol);
                            }
                            // Mensajes informativos y de Linea de Credito
//                                    var arrayMsgs = [msgInfo, msgLineaCredito];
//                                    var htmlMsgs = listaPalabras(arrayMsgs);
//                                    if (htmlMsgs.length > 0) {
//                                        toastAlerta('1111111111111111->' + htmlMsgs);
//                                    }

                            // Tabla                                    
                            $("#c_tablaPartesTempo").html(jsonObjTabla.tabla);
                            if (jsonObjTabla.ctosRegs > 0) {
                                iniDataTable({
                                    tabla: "#tablaPartesTempo",
                                    filasXpagina: -1,
                                    cajaBuscar: false,
                                    responsive: true,
                                    cboPaginas: false,
                                    infoNroReg: false
                                });
                                var combos = ["moneda", "formaPago"];
                                // bloqueaCombos(combos);
                            } else {
                                var combos = ["moneda"]; // solo libera la moneda
                                // desbloqueaCombos(combos);
                            }

                            // Limpiando cajas de ingreso de data                         
                            $("#pedPendientes").val("");
                            $("#repSeparados").val("");
                            $("#descripcion").val("");
                            $("#stockDis").val("");
                            $("#almacenSec").val("");
                            $("#cantidad").val("");
                            $("#descuento").val("");
                            $("#numeroParte").val("").focus();

                            // Boton confirmar
                            if ($.nullCad(msgLineaCredito).length > 0) {
                                $("#btnConFacRep").prop("disabled", true);
                            } else {
                                if (jsonObjTabla.ctosRegs > 0) {
                                    // bloqueaCombos("canalVenta");
                                    $("#btnConFacRep").prop("disabled", false);
                                } else {
                                    //desbloqueaCombos("canalVenta");
                                }
                            }
                        }
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

function eliminarItem(numeroParte) {
    toastConfirm('Desea eliminar este N\u00famero de Parte?', function () {
        $("#numItemAux").val(numeroParte);
        EliminarProducto();
    });
}

function editarItem(numeroParte, descripcion, precio) {
    $("#productoAux").prop("readonly", true);
    $("#nombreAux").prop("readonly", true);
    $("#precioAux").prop("readonly", true);
    $("#productoAux").val(numeroParte);
    $("#nombreAux").val(descripcion);
    $("#precioAux").val(precio);
    $("#descuentoAux").val("");
    $("#modaleditarItem").modal("show");
}

function aceptarDescuento() {
    var precioAux = $.nullNum($("#precioAux").val());
    var descuento = $.nullFlo($("#descuentoAux").val());
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PedidoOfertaRepAction.do?operacion=aceptarDescuentoItem",
        cache: false,
        data: {
            numeroParte: $("#productoAux").val(),
            descuentoAux: $("#descuentoAux").val(),
            moneda: $("#moneda").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
                var msgError = $.nullCad(jsonObjMsg.msgError);
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    default:
                        if (msgError.length > 0) {
                            toastError(msgError);
                        }
                        else
                        {
                            var jsonObjTot = data.objTotales;
                            var jsonObjTabla = data.objTabla;

                            // Totales
                            if ($("#moneda").val() === "1") {
                                $("#totalBruto").val(jsonObjTot.totBrutoSol);
                                $("#totalDescuento").val(jsonObjTot.totDsctoSol);
                                $("#totalNeto").val(jsonObjTot.totVtaSol);
                                $("#totalIgv").val(jsonObjTot.totIgvSol);
                                $("#totalGeneral").val(jsonObjTot.totGralVtaSol);
                            } else {
                                $("#totalBruto").val(jsonObjTot.totBrutoDol);
                                $("#totalDescuento").val(jsonObjTot.totDsctoDol);
                                $("#totalNeto").val(jsonObjTot.totVtaDol);
                                $("#totalIgv").val(jsonObjTot.totIgvDol);
                                $("#totalGeneral").val(jsonObjTot.totGralVtaDol);
                            }

                            // Tabla
                            $("#c_tablaPartesTempo").html(jsonObjTabla.tabla);
                            if (jsonObjTabla.ctosRegs > 0) {
                                iniDataTable({
                                    tabla: "#tablaPartesTempo",
                                    filasXpagina: -1,
                                    cajaBuscar: false,
                                    responsive: true,
                                    cboPaginas: false,
                                    infoNroReg: false
                                });
                                var combos = ["moneda", "formaPago", "canalVenta"];
                                bloqueaCombos(combos);
                                $("#btnConFacRep").prop("disabled", false);
                            } else {
                                var combos = ["moneda", "canalVenta"]; // libera la moneda y canal de venta
                                desbloqueaCombos(combos);
                                $("#btnConFacRep").prop("disabled", true);
                            }

                            $("#modaleditarItem").modal("hide");
                            // Limpiando cajas de ingreso de data                         
                            $("#pedPendientes").val("");
                            $("#repSeparados").val("");
                            $("#descripcion").val("");
                            $("#stockDis").val("");
                            $("#almacenSec").val("");
                            $("#cantidad").val("");
                            $("#descuento").val("");
                            $("#numeroParte").val("").focus();
                        }

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




function EliminarProducto() {
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PedidoOfertaRepAction.do?operacion=eliminarItem",
        cache: false,
        data: {
            numeroParte: $("#numItemAux").val(),
            moneda: $("#moneda").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
                var msgError = $.nullCad(jsonObjMsg.msgError);
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    default:
                        if (msgError.length > 0) {
                            toastError(msgError);
                        }
                        else
                        {
                            var jsonObjTot = data.objTotales;
                            var jsonObjTabla = data.objTabla;

                            // Totales
                            if ($("#moneda").val() === "1") {
                                $("#totalBruto").val(jsonObjTot.totBrutoSol);
                                $("#totalDescuento").val(jsonObjTot.totDsctoSol);
                                $("#totalNeto").val(jsonObjTot.totVtaSol);
                                $("#totalIgv").val(jsonObjTot.totIgvSol);
                                $("#totalGeneral").val(jsonObjTot.totGralVtaSol);
                            } else {
                                $("#totalBruto").val(jsonObjTot.totBrutoDol);
                                $("#totalDescuento").val(jsonObjTot.totDsctoDol);
                                $("#totalNeto").val(jsonObjTot.totVtaDol);
                                $("#totalIgv").val(jsonObjTot.totIgvDol);
                                $("#totalGeneral").val(jsonObjTot.totGralVtaDol);
                            }

                            // Tabla
                            $("#c_tablaPartesTempo").html(jsonObjTabla.tabla);
                            if (jsonObjTabla.ctosRegs > 0) {
                                iniDataTable({
                                    tabla: "#tablaPartesTempo",
                                    filasXpagina: -1,
                                    cajaBuscar: false,
                                    responsive: true,
                                    cboPaginas: false,
                                    infoNroReg: false
                                });
                                var combos = ["moneda", "formaPago", "canalVenta"];
                                bloqueaCombos(combos);
                                $("#btnConFacRep").prop("disabled", false);
                            } else {
                                var combos = ["moneda", "canalVenta"]; // libera la moneda y canal de venta
                                desbloqueaCombos(combos);
                                $("#btnConFacRep").prop("disabled", true);
                            }


                            // Limpiando cajas de ingreso de data                         
                            $("#pedPendientes").val("");
                            $("#repSeparados").val("");
                            $("#descripcion").val("");
                            $("#stockDis").val("");
                            $("#almacenSec").val("");
                            $("#cantidad").val("");
                            $("#descuento").val("");
                            $("#numeroParte").val("").focus();
                        }

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
function AdicionarItem(){

    Agrega();
}



function Agrega(){

          var numeroParte = $.nullCad($("#numeroParte").val());
        var cantidad = $.nullNum($("#cantidad").val());
        var descuento = $.nullFlo($("#descuento").val());
        var precio = $("#vvp").val();
        if (numeroParte.length > 0 && descuento >= 0 && descuento < 100 && cantidad > 0) {
            $("#descuento").blur();
            $.ajax({
                type: "POST",
                dataType: "JSON",
                url: "PedidoOfertaRepAction.do?operacion=agregarItem",
                cache: false,
                data: {
                    numeroParte: numeroParte,
                    cantidad: cantidad,
                    descuento: descuento,
                    auxFacTipDoc: $("#auxFacTipDoc").val(),
                    codCliente: $("#clienteNumeroDocumento").val(),
                    moneda: $("#moneda").val(),
                    formaPago: $("#formaPago").val(),
                    canalVenta: $("#canalVenta").val(),
                    proceso: $("#auxproceso").val(),
                    indicadorIGV: $("#auxindicadorIGV").val(),
                    existeRep: $("#existeRep").val(),
                    itemRepuesto: $("#numItemAux").val(),
                    precio: $("#vvp").val(),
                    auxProceso: $("#auxproceso").val(),
                    itemProducto:$("#itemCodigo").val()
                },
                success: function (data) {
                    try {

                        var jsonObjMsg = data.objMensaje;
                        var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
                        var msgError = $.nullCad(jsonObjMsg.msgError);
                        var msgInfo = $.nullCad(jsonObjMsg.msgInfo);
                        var msgLineaCredito = $.nullCad(jsonObjMsg.msgLineaCredito);

                        switch (tipoMsg) {
                            case "relogin":
                                window.location = "relogin.jsp";
                                break;
                            default:
                                if (msgError.length > 0) {
                                    toastError(msgError);
                                } else {
                                    var jsonObjTot = data.objTotales;
                                    var jsonObjTabla = data.objTabla;

                                    // Totales

                                    if ($("#moneda").val() === "1") {
                                        $("#totalBruto").val(jsonObjTot.totBrutoSol);
                                        $("#totalDescuento").val(jsonObjTot.totDsctoSol);
                                        $("#totalNeto").val(jsonObjTot.totVtaSol);
                                        $("#totalIgv").val(jsonObjTot.totIgvSol);
                                        $("#totalGeneral").val(jsonObjTot.totGralVtaSol);
                                    } else {
                                        $("#totalBruto").val(jsonObjTot.totBrutoDol);
                                        $("#totalDescuento").val(jsonObjTot.totDsctoDol);
                                        $("#totalNeto").val(jsonObjTot.totVtaDol);
                                        $("#totalIgv").val(jsonObjTot.totIgvDol);
                                        $("#totalGeneral").val(jsonObjTot.totGralVtaDol);
                                    }

                                    // Mensajes informativos y de Linea de Credito
                                    //var arrayMsgs = [msgInfo, msgLineaCredito];
                                    //var htmlMsgs = listaPalabras(arrayMsgs);
                                    //if (htmlMsgs.length > 0) {
                                    //    toastAlerta(htmlMsgs);
                                    //}

                                    // Tabla                                    
                                    $("#c_tablaPartesTempo").html(jsonObjTabla.tabla);
                                    if (jsonObjTabla.ctosRegs > 0) {
                                        iniDataTable({
                                            tabla: "#tablaPartesTempo",
                                            filasXpagina: -1,
                                            cajaBuscar: false,
                                            responsive: true,
                                            cboPaginas: false,
                                            infoNroReg: false
                                        });
                                        var combos = ["moneda", "formaPago"];
                                        // bloqueaCombos(combos);
                                    } else {
                                        var combos = ["moneda"]; // solo libera la moneda
                                        // desbloqueaCombos(combos);
                                    }

                                    // Limpiando cajas de ingreso de data                         

                                    $("#vvp").val("");
                                    $("#descripcion").val("");
                                    $("#stockDis").val("");
                                    $("#almacenSec").val("");
                                    $("#cantidad").val("");
                                    $("#descuento").val("");
                                    $("#stockTotal").val("");
                                    $("#cajas").val("");
                                    $("#numeroParte").val("").focus();

                                    // Boton confirmar
                                    if ($.nullCad(msgLineaCredito).length > 0) {
                                        $("#btnConFacRep").prop("disabled", true);
                                    } else {
                                        if (jsonObjTabla.ctosRegs > 0) {
                                            //  bloqueaCombos("canalVenta");
                                            $("#btnConFacRep").prop("disabled", false);
                                        } else
                                        {
                                            //desbloqueaCombos("canalVenta");
                                        }
                                    }
                                }
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
}
function agregarItem(e) {
    var key = checkKeyCode(e);
    if (key === 13) {
        Agrega();
    }
}

function validaRUC(){	
	$.ajax({
		url : "PedidoOfertaRepAction.do?operacion=ValidarRuc",
		method : "POST",
		dataType : "JSON",
		data : {
			documento : $("#clienteNumeroDocumento").val()			
		},
		success : function(data) {
			try {
				var jsonExito = data.objExito;
  				exito = jsonExito.exito; 
                                alert(exito);
                              
			} catch (error) {
				msgError("container", error);
			}
		},
		beforeSend : function() {
			showAjaxLoader("container");
		},
		error : function(xhr, status, error) {
			msgError("container", error);
		}
	});
    
}

$(function () {
    
    
    $("#btnConFacRep").click(function () {
        
            $.validator.addMethod("add_clienteNumeroDocumento", function () 
            {
                var cliNroDoc = $.nullCad($("#clienteNumeroDocumento").val());
               
                if (cliNroDoc.length === 0) {
                    return false; // Error
                } else {
                    if (cliNroDoc.length > 0) 
                    {
                        
                        if ($("#clienteTitulo").val() === "02") {

                            if (cliNroDoc.length < 11) {
                                return false; // Error     
                            } else 
                            {
                                   return true; // OK    
  
                            }
                        } 
                        else
                        {
                            
                            if ($("#clienteTitulo").val() === "01") 
                            {

                                if ($("#clienteTipoDocumento").val() === "06")
                                {
                                    if (cliNroDoc.length < 11) 
                                    {
                                        return false; // Error     
                                    } 
                                    else
                                    {
                                              return true; // OK          
                                    }                                    
                                } 
                                else
                                {
                                    if (cliNroDoc.length < 8) 
                                    {
                                        return false; // Error     
                                    } 
                                    else
                                    {
                                        return true; // OK      
                                    }                                    
                                }                                                                                                        
                            }                        
                        }
                    }
 
                    
                }
            }, "N\u00famero de Documento inv\u00e1lido");

            $("#frm_generico").validate({
                ignore: [], // validar campos ocultos
                rules: {
                    clienteNumeroDocumento: {add_clienteNumeroDocumento: true},
                    clientePrimerNombre: {
                        required: {
                            depends: function () {
                                return ($.nullCad($("#clienteTipoDocumento").val()) === "02");
                            }
                        }
                    },
                    clienteApellidoPaterno: {
                        required: {
                            depends: function () {
                                return ($.nullCad($("#clienteTipoDocumento").val()) === "02");
                            }
                        }
                    },
                    clienteRazonSocial: {
                        required: {
                            depends: function () {
                                return ($.nullCad($("#clienteTipoDocumento").val()) === "01");
                            }
                        }
                    },
                    clienteDireccion: {required: true},
                    clienteDepartamento: {required: true},
                    clienteProvincia: {required: true},
                    clienteDistrito: {required: true}
                    //auxCliTipDoc: {required: true},
                    //canalVenta: {required: true}
                },
                messages: {
                    clientePrimerNombre: {required: "Ingrese el nombre del cliente"},
                    clienteApellidoPaterno: {required: "Ingrese el Apellido Paterno"},
                    clienteRazonSocial: {required: "Ingrese la Raz\u00f3n Social del cliente"},
                    clienteDireccion: {required: "Ingresela Direcci\u00f3n del cliente"},
                    clienteDepartamento: {required: "Seleccione el Departamento del cliente"},
                    clienteProvincia: {required: "Seleccione la Provincia del cliente"},
                    clienteDistrito: {required: "Seleccione el Distrito del cliente"}
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
        
        // Si no hay errores
        if ($("#frm_generico").valid())
        {
            
            // var quitarIgvDoc = $.nullCad($("#quitarIgvDoc").val());
//
            showLoader();
            $("#operacion").val("conFacRepMostrador");
            $("#frm_generico").submit();

        }
    });
});

function buscarClienteNroDocEnter(e) {
    var key = checkKeyCode(e);
    if (key === 13) {
        buscarClienteNroDoc();
    }
}

function buscarClienteNroDocBoton() {
    buscarClienteNroDoc();
}

function buscarClienteNroDoc() {
    var numDoc = $.nullCad($("#clienteNumeroDocumento").val());
    var titCli = $.nullCad($("#clienteTitulo").val());
    var tipDoc = $.nullCad($("#clienteTipoDocumento").val());
    if (numDoc.length > 0 && titCli.length > 0 && tipDoc.length > 0) {
        $("#c_lineaCredito").hide(); // Limpiando
        $("#clienteLineaCredito").val(""); // Limpiando
        return $.ajax({
            url: "PedidoOfertaRepAction.do?operacion=obtenerDatosClienteSAP",
            type: "POST",
            dataType: "JSON",
            cache: false,
            data: {
                codigoCliente: numDoc,
                clienteTitulo: titCli,
                clienteTipoDocumento: tipDoc
            },
            success: function (data) {
                switch (data) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    default:
                        $("#numeroParte").prop("readonly", false);
                        $("#cantidad").prop("readonly", false);
                        $("#descuento").prop("readonly", false);

                        var objCli = data.cliente;
                        if (objCli.existe === true)
                        {
                            if (objCli.situacion === "2") {
                                toastAlerta("Cliente temporalmente inactivo para la venta");
                                $("#btnConfirmar").prop("disabled", true);
                            }
                            else
                            {
                                var tipoDocAux = objCli.cliTipDoc;
                                $("#clienteTitulo").val(objCli.cliTipoCliente); //);
                                $("#clienteTipoDocumento").val(objCli.cliTipoDocumento);
                                $.when(cboDocPorTitPerJSON("clienteTitulo", "clienteTipoDocumento", false, false)).done(function () {
                                    if ($("#clienteTitulo").val() === "02") {
                                        $(".c_cliPerNat").hide();
                                        $(".c_cliPerJur").show();
                                    } else {
                                        $(".c_cliPerNat").show();
                                        $(".c_cliPerJur").hide();
                                    }
                                    $("#clienteTipoDocumento").val(objCli.cliTipoDocumento);
                                    propNumDocPorTipo("clienteTipoDocumento", "clienteNumeroDocumento");   
                                    $("#clienteTipoDocumento").val(tipoDocAux);
                                    //valoresDefaultFact();
                                }); 
                                
                                
                                $("#btnConfirmar").prop("disabled", false);
                                $("#clienteNumeroDocumento").val(objCli.codigo);
                                $("#clientePrimerNombre").val(objCli.primerNombre);
                                $("#clienteSegundoNombre").val(objCli.segundoNombre);
                                $("#clienteApellidoPaterno").val(objCli.apellidoPaterno);
                                $("#clienteApellidoMaterno").val(objCli.apellidoMaterno);
                                $("#clienteRazonSocial").val(objCli.razonSocial);
                                $("#formaPago").val(objCli.formaPago);
                                $("#clienteDireccion").val(objCli.direccion);
                                $("#formaPago").val(objCli.formaPago);
                                // Ubigeo                            
                                $("#clienteDepartamento").val(objCli.codigoDepartamento);
                                $.when(cboProJSON("clienteDepartamento", "clienteProvincia", false)).done(function () {
                                    $("#clienteProvincia").val(objCli.codigoProvincia);
                                    $.when(cboDistritosJSON("clienteDepartamento", "clienteProvincia", "clienteDistrito", false)).done(function () {
                                        $("#clienteDistrito").val(objCli.codigoDistrito);
                                    });
                                });

                                                               
                                //objCli.cliTipDoc);                                    
                                $("#auxFacTipDoc").val(objCli.facTipDoc);
                                $("#numeroParte").focus();
                            }
                        } else
                        {
                            $("#clienteTitulo").val(objCli.cliTipoCliente); //);
                            $("#clienteTipoDocumento").val(objCli.cliTipoDocumento);                                                      
                            $("#clientePrimerNombre").val("");
                            $("#clienteSegundoNombre").val("");
                            $("#clienteApellidoPaterno").val("");
                            $("#clienteApellidoMaterno").val("");
                            $("#clienteRazonSocial").val("");
                            $("#clienteDireccion").val("");
                            //$("#auxCliTipDoc").val(objCli.cliTipDoc);
                            //$("#auxFacTipDoc").val(objCli.facTipDoc);
                            //$("#auxCliTipPer").val(objCli.cliTipPer);
                            $("#formaPago").val("0");
                            $("#clienteDepartamento").val("15");
                            $.when(cboProJSON("clienteDepartamento", "clienteProvincia", false)).done(function () {
                                $("#clienteProvincia").val("01");
                                $.when(cboDistritosJSON("clienteDepartamento", "clienteProvincia", "clienteDistrito", false)).done(function () {
                                    $("#clienteDistrito").val("01");
                                });
                            });
                            $("#clienteApellidoPaterno").focus();
                            //bloqueaCombos("formaPago");
                        }


                        // Comodin para saber si ya se ralizo la busqueda de un cliente
                        $("#auxCliFueBus").val(objCli.cliFueBus);
                        break;
                }
            },
            error: function () {
                toastError();
            },
            beforeSend: function () {
                showLoader();
            },
            complete: function () {
                hideLoader();
            }
        });
    }
}

function cboProJSON(cboDep, cboPro, proSel) {
   
    var auxCboPro = $("#" + cboPro);
    
    auxCboPro.empty().append('<option value="">Cargando...</option>');
    return $.ajax({
        url: "PedidoOfertaRepAction.do?operacion=ubigeoJSON",
        type: "POST",
        dataType: "JSON",
        cache: false,
        data: {
            auxTipo: "provincias",
            cboDepartamentos: $('#' + cboDep + " option:selected").val()
        },
        success: function (data) {
            switch (data) {

                case "relogin":
                    window.location = "relogin.jsp";
                    break;
                default:

                    auxCboPro.empty();
                    $("#clienteDistrito").empty();
                    $("#clienteDistrito").append('<option value="">-- SELECCIONE --</option>');
                    auxCboPro.append('<option value="">-- SELECCIONE --</option>');
                    $.each(data.provincias, function (key, val) {
                        auxCboPro.append('<option value="' + val.indice + '">' + val.descripcion + '</option>');
                    });

                    if (proSel !== false && proSel !== undefined) {
                        $.map($('#' + cboPro + ' option'), function (option) {
                            if (option.value === proSel) {
                                $(option).attr("selected", "selected");
                            }
                        });
                    }
                    break;
            }
        },
        error: function () {
            //auxCboPro.find('option').remove();
            //auxCboPro.html('');
            auxCboPro.empty();
            auxCboPro.append('<option value="">-- SELECCIONE --</option>');
        }
    });
}

function cboDistritosJSON(cboDep, cboPro, cboDis, disSel) {

    return $.ajax({
        url: "PedidoOfertaRepAction.do?operacion=ubigeoJSON",
        type: "POST",
        dataType: "JSON",
        cache: false,
        data: {
            auxTipo: "distritos",
            cboDepartamentos: $('#' + cboDep + " option:selected").val(),
            cboProvincias: $('#' + cboPro + " option:selected").val()
        },
        success: function (data) {
            switch (data) {
                /*case "error":
                 auxCboDis.find('option').remove();
                 auxCboDis.html('');
                 auxCboDis.append('<option value="">-- SELECCIONE --</option>');
                 break;*/
                case "relogin":
                    window.location = "relogin.jsp";
                    break;
                default:
                    // auxCboDis.find('option').remove();
                    // auxCboDis.html('');
                    $("#clienteDistrito").empty();
                    $("#clienteDistrito").append('<option value="">-- SELECCIONE --</option>');
                    $.each(data.distritos, function (key, val) {
                        $("#clienteDistrito").append('<option value="' + val.indice + '">' + val.descripcion + '</option>');
                    });

                    if (disSel !== false && disSel !== undefined) {
                        $.map($('#' + cboDis + ' option'), function (option) {
                            if (option.value === disSel) {
                                $(option).attr("selected", "selected");
                            }
                        });
                    }
                    break;
            }
        },
        error: function () {
            //auxCboDis.find('option').remove();
            //auxCboDis.html('');
            auxCboDis.empty();
            auxCboDis.append('<option value="">-- SELECCIONE --</option>');
        }
    });
}


function cboDepartamentoJSON(cboDep) {
    var auxCboDis = $("#" + cboDep);
    auxCboDis.empty().append('<option value="">Cargando...</option>');
    return $.ajax({
        url: "FacRepMostradorAction.do?operacion=ubigeoJSON",
        type: "POST",
        dataType: "JSON",
        cache: false,
        data: {
            auxTipo: "departamentos",
            cboDepartamentos: $('#' + cboDep + " option:selected").val(),
            cboProvincias: $('#' + cboPro + " option:selected").val()
        },
        success: function (data) {
            switch (data) {
                /*case "error":
                 auxCboDis.find('option').remove();
                 auxCboDis.html('');
                 auxCboDis.append('<option value="">-- SELECCIONE --</option>');
                 break;*/
                case "relogin":
                    window.location = "relogin.jsp";
                    break;
                default:
                    // auxCboDis.find('option').remove();
                    // auxCboDis.html('');
                    auxCboDis.empty();
                    auxCboDis.append('<option value="">-- SELECCIONE --</option>');
                    $.each(data.distritos, function (key, val) {
                        auxCboDis.append('<option value="' + val.indice + '">' + val.descripcion + '</option>');
                    });

                    if (disSel !== false && disSel !== undefined) {
                        $.map($('#' + cboDis + ' option'), function (option) {
                            if (option.value === disSel) {
                                $(option).attr("selected", "selected");
                            }
                        });
                    }
                    break;
            }
        },
        error: function () {
            //auxCboDis.find('option').remove();
            //auxCboDis.html('');
            auxCboDis.empty();
            auxCboDis.append('<option value="">-- SELECCIONE --</option>');
        }
    });
}


function muestraModelCotizacionesBoton() {
    $("#modalCotizaciones").modal("show");
}

function buscarCotizacionesBoton() {
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "PedidoOfertaRepAction.do?operacion=muestraListaCotizaciones",
        cache: false,
        data: {
            clienteNumeroDocumento: $("#clienteNumeroDocumento").val(),
            fechaDesde: $("#fechaDesde").val()
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

                        $("#c_tablaCotizaciones").html(jsonObjTabla.tabla);

                        iniDataTable({
                            tabla: "#tablaCotizaciones",
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

    function aceptarPropuesta(parm) 
    {
        $("#numeroPropuesta").val(parm);
        $("#numeroPropuesta").prop("disabled", true);
        $("#btnbuscarPropuesta").prop("disabled", true);
        $.ajax({
            type: "POST",
            dataType: "JSON",
            url: "PedidoOfertaRepAction.do?operacion=actualizaTempoPropuesta",
            cache: false,
            data: {
                propuesta: parm
            },
            success: function (data) {
                try 
                {
                    var jsonObjMsg = data.objMensaje;
                    var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
                    var msgError = $.nullCad(jsonObjMsg.msgError);
                    var msgInfo = $.nullCad(jsonObjMsg.msgInfo);
                    var msgLineaCredito = $.nullCad(jsonObjMsg.msgLineaCredito);
                    switch (tipoMsg)
                    {
                        case "relogin":
                            window.location = "relogin.jsp";
                            break;
                        default:
                            if (msgError.length > 0) {
                                toastError(msgError);
                            } 
                            else 
                            {
                                var jsonObjTot = data.objTotales;
                                var jsonObjTabla = data.objTabla;

                                // Totales

                                if ($("#moneda").val() === "1") {
                                    $("#totalBruto").val(jsonObjTot.totBrutoSol);
                                    $("#totalDescuento").val(jsonObjTot.totDsctoSol);
                                    $("#totalNeto").val(jsonObjTot.totVtaSol);
                                    $("#totalIgv").val(jsonObjTot.totIgvSol);
                                    $("#totalGeneral").val(jsonObjTot.totGralVtaSol);
                                } else {
                                    $("#totalBruto").val(jsonObjTot.totBrutoDol);
                                    $("#totalDescuento").val(jsonObjTot.totDsctoDol);
                                    $("#totalNeto").val(jsonObjTot.totVtaDol);
                                    $("#totalIgv").val(jsonObjTot.totIgvDol);
                                    $("#totalGeneral").val(jsonObjTot.totGralVtaDol);
                                }


                                // Tabla                                    
                                $("#c_tablaPartesTempo").html(jsonObjTabla.tabla);
                                if (jsonObjTabla.ctosRegs > 0) {
                                    iniDataTable({
                                        tabla: "#tablaPartesTempo",
                                        filasXpagina: -1,
                                        cajaBuscar: false,
                                        responsive: true,
                                        cboPaginas: false,
                                        infoNroReg: false
                                    });
                                    var combos = ["moneda", "formaPago"];
                                    // bloqueaCombos(combos);
                                } else {
                                    var combos = ["moneda"]; // solo libera la moneda
                                    // desbloqueaCombos(combos);
                                }

                                // Limpiando cajas de ingreso de data                         

                                $("#vvp").val("");
                                $("#descripcion").val("");
                                $("#stockDis").val("");
                                $("#almacenSec").val("");
                                $("#cantidad").val("");
                                $("#descuento").val("");
                                $("#stockTotal").val("");
                                $("#numeroParte").val("").focus();

                                // Boton confirmar
                                if ($.nullCad(msgLineaCredito).length > 0) {
                                    $("#btnConFacRep").prop("disabled", true);
                                } else {
                                    if (jsonObjTabla.ctosRegs > 0) {
                                        //  bloqueaCombos("canalVenta");
                                        $("#btnConFacRep").prop("disabled", false);
                                    } else
                                    {
                                        //desbloqueaCombos("canalVenta");
                                    }
                                }
                                $("#modalCotizaciones").modal("hide");
                            }
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
    
    function buscarPropuestaEnter(e){
        var key = checkKeyCode(e);
        if (key === 13) {
            aceptarPropuesta($("#numeroPropuesta").val());
        }    
    }
    
    
function openModalHelpCli() {
	$("#helpProDescripcion").val("");
	$("#helpProTipoBusqueda").val("COD");
	$("#modalHelpCliente").modal("show");
	
	document.getElementById("buscarSN").setAttribute("onClick",
			"javascript: buscarCliHelp();");
	document.getElementById("helpProDescripcion").setAttribute("onkeypress",
			"javascript: buscarCliHelpEnter(event);");
	buscarCliHelp();
}


   

function buscarCliHelpEnter(e) {
	var key = checkKeyCode(e);
	if (key === 13) {
		buscarCliHelp();
	}
}   
   
function eligeClienteCodigo(codigo){ 
     $("#clienteNumeroDocumento").val(codigo);
     buscarClienteNroDoc();
     closeModalHelpCli();
 }  
function buscarCliHelp() {
	var container = "#c_tableHelpGenerico";
	$.ajax({
		url : "HelpAction.do?operacion=helpClientes",
		method : "POST",
		dataType : "JSON",
		data : {
			tipoBusqueda : $("#helpProTipoBusqueda").val(),
			descripcion : $("#helpProDescripcion").val()
			
		},
		success : function(data) {
			try {
				var jsonObjMsg = data.objMensaje;
				var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
				var msg = $.nullCad(jsonObjMsg.msg);
				switch (tipoMsg) {
				case "relogin":
					window.location = "relogin.jsp";
					break;
				case "error":
					msgError(container, msg);
					break;
				default:
					$(container).html(data.objTabla.tabla);
					break;
				}
			} catch (error) {
				msgError(container, error);
			}
		},
		beforeSend : function() {
			showAjaxLoader(container);
		},
		error : function(xhr, status, error) {
			msgError(container, error);
		}
	});
}
		   
function closeModalHelpCli() {
	
	$("#modalHelpCliente").modal("hide");
}                   


/*-------------------------- help productos */

function closeModalHelpProd() {
	
	$("#modalHelpProductos").modal("hide");
}                   
function openModalHelpProd() {
	$("#helpProdDescripcion").val("");
	$("#helpProTipoBusqueda").val("COD");
	$("#modalHelpProductos").modal("show");
	
	document.getElementById("buscarSNProd").setAttribute("onClick",
			"javascript: buscarProductosHelp();");
	document.getElementById("helpProdDescripcion").setAttribute("onkeypress",
			"javascript: buscarProdHelpEnter(event);");
	buscarProductosHelp();
}



function buscarProdHelpEnter(e) {
	var key = checkKeyCode(e);
	if (key === 13) {
		buscarProductosHelp();
	}
}   
   
function eligeProductoCodigo(codigo){ 
     $("#numeroParte").val(codigo);
     buscarProductoEnter();
     closeModalHelpProd();
 }  
function buscarProductosHelp() {
	var container = "#c_tableHelpCliente";
	$.ajax({
		url : "HelpAction.do?operacion=helpProductos",
		method : "POST",
		dataType : "JSON",
		data : {
			tipoBusqueda : $("#helpProTipoBusqueda").val(),
			descripcion : $("#helpProdDescripcion").val()
			
		},
		success : function(data) {
			try {
				var jsonObjMsg = data.objMensaje;
				var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
				var msg = $.nullCad(jsonObjMsg.msg);
				switch (tipoMsg) {
				case "relogin":
					window.location = "relogin.jsp";
					break;
				case "error":
					msgError(container, msg);
					break;
				default:
					$(container).html(data.objTabla.tabla);
					break;
				}
			} catch (error) {
				msgError(container, error);
			}
		},
		beforeSend : function() {
			showAjaxLoader(container);
		},
		error : function(xhr, status, error) {
			msgError(container, error);
		}
	});
}
