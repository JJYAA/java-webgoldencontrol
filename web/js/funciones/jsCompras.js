$(function () {

    setCalendariosDependientes({
        obj1: "#txtfecha"        
    });
    setCalendariosDependientes({
        obj2:txtfechacontable
    });    
    
    setCalendariosDependientes({
        obj2:auxtfechacontable
    });      
    
    
    $("#btnGrabaPolizas").prop("disabled", true);
    $("#btnAsientoCompras").prop("disabled", true);
    
    $("#txtfecha").val($("#fechaContable").val());
    $("#txtfechacontable").val($("#fechaContable").val());
    $("#auxtfechacontable").val($("#fechaContable").val());
    muestra();
  //  muestraListaTD();
});



function cargaPolizasDetalle(pPoliza,pFob){
    $("#out_importe").val(pFob);

    $("#txtfecha").val($("#fechaContable").val());
    $("#txtfechacontable").val($("#fechaContable").val());    
        $("#auxpoliza").val(pPoliza);
        $("#txtpoliza").val(pPoliza);    
	var container = "#c_tablaListaPolizasDetalle";
	$.ajax({
		url : "ComprasMigrarAction.do?operacion=cargaPolizasDetalle",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: pPoliza
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
                                       
                                        $("#btnGrabaPolizas").prop("disabled", false);
                                        $("#btnAsientoCompras").prop("disabled", false);
                                        $("#txtfob").val(pFob);
					muestraPoliza(pPoliza);
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


function muestraPoliza(pPoliza){
        $("#txtpoliza").val(pPoliza);    
	var container = "#c_tablaListaPolizasDetalle";
	$.ajax({
		url : "ComprasMigrarAction.do?operacion=muestraPolizasDetalle",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: pPoliza
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
                                         var jsonObjtot = data.objTotales;
                                        $("#txtsol").val(jsonObjtot.soles);
                                        $("#txtdol").val(jsonObjtot.dolar);                                    
					$(container).html(data.objTabla.tabla);
                                        iniDataTable({
                                            tabla: "#tablaListaPolizasDetalle",
                                            filasXpagina: 25,
                                            cajaBuscar: false,
                                            responsive: true,
                                            cboPaginas: false,
                                            infoNroReg: false
                                        });  
                                        $("#txttotal").val(jsonObjtot.totalsoles);
                                        $("#txtasiento").val(jsonObjtot.anho + '-' + jsonObjtot.mes + '-' + jsonObjtot.tipo_comprobante + '-' + jsonObjtot.voucher_cab);
                                        $("#auxtfechacontable").val(jsonObjtot.fecha_contable);
                                        if (jsonObjtot.voucher_cab==="0")
                                            $("#btnAsientoCompras").prop("disabled", false);
                                        else
                                            $("#btnAsientoCompras").prop("disabled", true);
                                        
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



function muestra(){
	var container = "#c_tablaListaPolizas";
	$.ajax({
		url : "ComprasMigrarAction.do?operacion=muestraPolizas",
		method : "POST",
		dataType : "JSON",
		success : function(data) {
			try {
				var jsonObjMsg = data.objMensaje;
				var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
				var msg = $.nullCad(jsonObjMsg.msgError);
				switch (tipoMsg) {
				case "relogin":
					window.location = "relogin.jsp";
					break;
				case "error":
					msgError(container, msg);
					break;
				default:
					$(container).html(data.objTabla.tabla);
                                        iniDataTable({
                                            tabla: "#tablaListaPolizas",
                                            filasXpagina: 15,
                                            cajaBuscar: true,
                                            responsive: true,
                                            cboPaginas: false,
                                            infoNroReg: false
                                        });                                        
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


function editItemXX(pItem,pDescripcion){
    $("#txtitem").val(pItem);
    $("#txtdescripcion").val(pDescripcion);
    $("#modalAgregar").modal("show");
}


function editItem(pItem,pDescripcion){
    
    
     $("#txtitem").val(pItem);
    $("#txtdescripcion").val(pDescripcion);  
   
    $("#txttd").val("");
    $("#txtproveedor").val("");
    $("#txtserie").val("");
    $("#txtdocumento").val("");
    $("#txtbaseimponible").val("0");
    $("#txtinafecto").val("0");
    $("#txtigv").val("0");
    $("#txttotal").val("0") ;   
    $("#txtmoneda").val("") ;
    $.ajax({
		url : "ComprasMigrarAction.do?operacion=editItem",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: $("#auxpoliza").val(),
                     item : $("#txtitem").val()                              
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
                                        var jsonObjDatos = data.objDatos;
                                        $("#txtitem").val(jsonObjDatos.c_c_item);

                                        $("#txtfecha").val(data.objDatos.fecha_emision),
                                        $("#txttd").val(data.objDatos.TipoDocumento),
                                        $("#txtproveedor").val(data.objDatos.CodigoProv),
                                        $("#txtserie").val(data.objDatos.seriedocumento),
                                        $("#txtdocumento").val(data.objDatos.documento),
                                        $("#txtbaseimponible").val(data.objDatos.baseImpTotal),
                                        $("#txtinafecto").val(data.objDatos.inafecta),
                                        $("#txtigv").val(data.objDatos.igvTotal),
                                        $("#txttotal").val(data.objDatos.total) 
                                        $("#txtmoneda").val(data.objDatos.moneda);
                                        $("#txtmoneda").val(data.objDatos.moneda);
                                        $("#txtfechacontable").val(data.objDatos.fechacontable);
                                        if ((pItem==="01")||(pItem==="02"))
                                            $("#btnAceptaItem").prop("disabled", false);
                                        else
                                            $("#btnAceptaItem").prop("disabled", true);
                                        $("#modalAgregar").modal("show");                                       
					break;
				}
			} catch (error) {
				//msgError("container", error);
			}
		},
		beforeSend : function() {
			//showAjaxLoader("");
		},
		error : function(xhr, status, error) {
			//msgError("container", error);
		}
	}); 
}



function actualizaDatalleitem(){
 	var container = "#c_tablaListaPolizasDetalle";
	$.ajax({
		url : "ComprasMigrarAction.do?operacion=actualizaDatalleitem",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: $("#auxpoliza").val(),
                     item : $("#txtitem").val(),
                    fecha : $("#txtfecha").val(),
                    td : $("#txttd").val(),
                    proveedor : $("#txtproveedor").val(),
                    serie : $("#txtserie").val(),
                    documento : $("#txtdocumento").val(),
                    baseimponible : $("#txtbaseimponible").val(),
                    inafecto : $("#txtinafecto").val(),
                    igv : $("#txtigv").val(),
                    total : $("#txttotal").val(),                   
                    moneda : $("#txtmoneda").val()  ,
                    fechacontable : $("#txtfechacontable").val()  
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
					$("#modalAgregar").modal("hide");  
                                         muestraPoliza($("#auxpoliza").val());
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

function GrabarPoliza(){
	$.ajax({
		url : "ComprasMigrarAction.do?operacion=GrabarPoliza",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: $("#auxpoliza").val()           
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
                                        $("#btnGrabaPolizas").prop("disabled", true);
                                        $("#btnAsientoCompras").prop("disabled", false);
                                         muestraPoliza($("#auxpoliza").val());                                    
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});    
    
} 


function GrabarAsiento(){
    if ($("#auxtfechacontable").val()===""){
         toastError("No hay ingresado la fecha contable");
        return false;
    }

    $.ajax({
		url : "ComprasMigrarAction.do?operacion=generaAsiento",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza:$("#auxpoliza").val()   ,
                    fechaContable:$("#auxtfechacontable").val()
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
                                         toastError(msg);
					break;
				default:
                                        $("#btnGrabaPolizas").prop("disabled", true);
                                        $("#btnAsientoCompras").prop("disabled", false);
                                        
                                         muestra($("#auxpoliza").val());  
                                         
                                         cargaPolizasDetalle($("#auxpoliza").val(),$("#out_importe").val());
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});    
}


function GrabarAsientoXX(){
    var item="";
    var poliza="";
    var selected="";
  //  alert("1");
     $('#tablaListaPolizasDetalle').DataTable().rows().iterator('row', function(context, index)
      {    
            var node = $(this.row(index).node());              
            var celdas =   node.find("td");        
            if (celdas.find(".chk").prop("checked")===true)
               modificado="1";
            else
                modificado="0";
            if (modificado==="1")  {
                 item =  node.find(".item" ).val(); 
                 poliza  =  node.find(".poliza" ).val(); 
                 selected = selected + poliza + "|" + item + ",";
            }
      }); 
      if (selected.length===0){
          alert("No hay ningun elemento seleccionado");
          return false;
      }
 
        $.ajax({
		url : "ComprasMigrarAction.do?operacion=validaDatosFactura",
		method : "POST",
		dataType : "JSON",
                data:{
                    seleccion: selected ,
                    poliza:$("#auxpoliza").val()     
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
                                         toastError(msg);
					break;
				default:
                                       
                                        generaAsiento(selected);
                                 
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});        
}


function generaAsientoYYY(pSelected){
    $.ajax({
		url : "ComprasMigrarAction.do?operacion=generaAsiento",
		method : "POST",
		dataType : "JSON",
                data:{
                    seleccion: pSelected                    
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
                                         toastError(msg);
					break;
				default:
                                        $("#btnGrabaPolizas").prop("disabled", true);
                                        $("#btnAsientoCompras").prop("disabled", false);
                                         muestraPoliza($("#auxpoliza").val());                                    
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});            
}

function editarPoliza(pPoliza){
    $("#addpoliza").val(pPoliza);
    $('#addpoliza').prop('readonly', true);
    
    $.ajax({
		url : "ComprasMigrarAction.do?operacion=muestraPoliza",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: pPoliza                    
                    },
		success : function(data) {
			try {
				var jsonObjMsg = data.objMensaje;
                                var jsonObjDatos = data.objDatos;
				var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
				var msg = $.nullCad(jsonObjMsg.msg);
				switch (tipoMsg) {
				case "relogin":
					window.location = "relogin.jsp";
					break;
				case "error":
                                         toastError(msg);
					break;
				default:
                                        $("#addfob").val(jsonObjDatos.montoFob);
                                        $("#addcbm").val(jsonObjDatos.montoCbm); 
                                        
                                        if (jsonObjDatos.cerrado==="1")
                                            $( "#addChkCerrar" ).prop( "checked", true );
                                        else
                                            $( "#addChkCerrar" ).prop( "checked", false );
                                        
                                        
                                        $("#modalPoliza").modal("show");                                   
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});                       
}


function agregarPoliza(){
    $("#addpoliza").prop('readonly', false);
    $("#addpoliza").val("");
    $("#addfob").val("");
    $("#addcmb").val("");
    $("#modalPoliza").modal("show");
}


function actualizaPoliza(){ 
    var poli = $("#addpoliza").val().trim();
    if (poli.length===0){
        return false;
    }
    var vCerrado= "0";
    if( $("#addChkCerrar").is(':checked') ) {
        vCerrado="1";
    }    
    $.ajax({
		url : "ComprasMigrarAction.do?operacion=actualizaPoliza",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: $("#addpoliza").val(),
                    montofob:$("#addfob").val(),
                    montocbm:$("#addcbm").val(),
                    cerrado:vCerrado
                    },
		success : function(data) {
			try {
				var jsonObjMsg = data.objMensaje;
                                var jsonObjDatos = data.objDatos;
				var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
				var msg = $.nullCad(jsonObjMsg.msg);
				switch (tipoMsg) {
				case "relogin":
					window.location = "relogin.jsp";
					break;
				case "error":
                                         toastError(msg);
					break;
				default:
                                        $("#modalPoliza").modal("hide");
                                        muestra();                              
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});                       
}


function EliminarAsintoPoliza(pPoliza,pAnho,pMes,pTipoComprobante,pVoucher){
    $("#out_poliza").val(pPoliza);
    $("#out_anho").val(pAnho);
    $("#out_mes").val(pMes);
    $("#out_tipoComprobante").val(pTipoComprobante);
    $("#out_asiento").val(pVoucher);
    $("#EliminarAsiento").modal("show");    
}

function EliminarVoucher(){
    $.ajax({
		url : "ComprasMigrarAction.do?operacion=EliminarAsintoPoliza",
		method : "POST",
		dataType : "JSON",
                data:{
                    poliza: $("#out_poliza").val(),
                    anho: $("#out_anho").val(),
                    mes: $("#out_mes").val(),
                    tipoComprobante : $("#out_tipoComprobante").val(),
                    asiento : $("#out_asiento").val()
                    },
		success : function(data) {
			try {
				var jsonObjMsg = data.objMensaje;
                                var jsonObjDatos = data.objDatos;
				var tipoMsg = $.nullCad(jsonObjMsg.tipoMsg);
				var msg = $.nullCad(jsonObjMsg.msg);
				switch (tipoMsg) {
				case "relogin":
					window.location = "relogin.jsp";
					break;
				case "error":
                                         toastError(msg);
					break;
				default:
                                        $("#EliminarAsiento").modal("hide"); 
                                        muestra(); 
                                        cargaPolizasDetalle($("#auxpoliza").val(),$("#out_importe").val());
					break;
				}
			} catch (error) {
				
			}
		},
		beforeSend : function() {
			
		},
		error : function(xhr, status, error) {
			
		}
	});                           
}