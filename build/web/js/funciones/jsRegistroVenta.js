$(function () {
    setCalendariosDependientes({
        obj1: "#fechaIni",
        obj2: "#fechaFin"
    });
});


    
    
function muestraListaRegistroVentas() {
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "RegistroVentasAction.do?operacion=muestraListaRegistroVentas",
        cache: false,
        data: {
            codigo: $("#producto").val(),
            fechaIni: $("#fechaIni").val(),
            fechaFin: $("#fechaFin").val(),            
            tipoDocumento: $("#tipoDocumento").val(),
            actividad: $("#actividad").val(),
            formaPago: $("#formaPago").val(),
            tipoVenta: $("#tipoVenta").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                var jsonObjTotales = data.objTotales;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        $("#c_tablaRegistroVenta").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaPartesProductos",
                            filasXpagina: 50,
                            cajaBuscar: true,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });
                        $("#neto").val(jsonObjTotales.neto);
                        $("#igv").val(jsonObjTotales.igv);
                        $("#total").val(jsonObjTotales.total);
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




function buscarProductoEnter(e) {
    var key = checkKeyCode(e);
    if (key === 13) {
        muestraListaStock();
    }
}

function buscarProductoBoton() {
    muestraListaStock();
}


function buscarKardexBoton() {
    muestraListaKardex();
}


function muestraListaKardex() {

    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "KardexAction.do?operacion=muestraKardex",
        cache: false,
        data: {
            codigoProducto: $("#producto").val(),
            fechaInicio : $("#fechaIni").val(),
            fechaFinal : $("#fechaFin").val(),  
            actividad : "%",
            almacen : $("#almacen").val()
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
                        $("#c_tablaStock").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaPartesProductos",
                            filasXpagina: 100,
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



function consultaListaBoletas() {

    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "BoletaAlmacenAction.do?operacion=consultaListaBoletas",
        cache: false,
        data: {
            tipoBoleta: $("#tipoBoleta").val(),
            fechaInicio : $("#fechaIni").val(),
            fechaFinal : $("#fechaFin").val(),              
            almacen : $("#almacen").val(),
            actividad : "%"
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
                        $("#c_tablaStock").html(jsonObjTabla.tabla);
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

function muestraListaCajeroMigrar(){
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "RegistroVentasAction.do?operacion=muestraListaCajeroMigrar",
        cache: false,
        data: {
            fechaIni: $("#fechaIni").val(),
            fechaFin: $("#fechaFin").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                var jsonObjTotales = data.objTotales;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        $("#c_tablaRegistroVenta").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaPartesProductos",
                            filasXpagina: 50,
                            cajaBuscar: true,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });
                        $("#neto").val(jsonObjTotales.neto);
                        $("#igv").val(jsonObjTotales.igv);
                        $("#total").val(jsonObjTotales.total);
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

function muestraListaRegistroVentasMigrar() {   
    $("#pendientes").val("0");
    if($("#chkpendientes").prop("checked")===true)
    {
       $("#pendientes").val("1");           		
    }     
    $.ajax({
        type: "POST",
        dataType: "JSON",
        url: "RegistroVentasAction.do?operacion=muestraListaRegistroVentasMigrar",
        cache: false,
        data: {
            codigo: "%",
            fechaIni: $("#fechaIni").val(),
            fechaFin: $("#fechaFin").val(),            
            tipoDocumento: "%",
            actividad: "%",
            formaPago: "%",
            tipoVenta: "%",
            pendientes: $("#pendientes").val()
        },
        success: function (data) {
            try {
                var jsonObjMsg = data.objMensaje;
                var tipoMsg = $.trim(jsonObjMsg.tipoMsg);
                var msgError = $.trim(jsonObjMsg.msgError);
                var jsonObjTabla = data.objTabla;
                var jsonObjTotales = data.objTotales;
                switch (tipoMsg) {
                    case "relogin":
                        window.location = "relogin.jsp";
                        break;
                    case "error":
                        break;
                    case "exito":
                        $("#c_tablaRegistroVenta").html(jsonObjTabla.tabla);
                        iniDataTable({
                            tabla: "#tablaPartesProductos",
                            filasXpagina: 50,
                            cajaBuscar: true,
                            responsive: true,
                            cboPaginas: false,
                            infoNroReg: false
                        });
                        $("#neto").val(jsonObjTotales.neto);
                        $("#igv").val(jsonObjTotales.igv);
                        $("#total").val(jsonObjTotales.total);
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

function EliminarAsientos(parm){
    if ($("#docSelectedDel").val()===""){
                 toastError("Debe seleccionar algun Item");
                return false;
            }
    $("#proceso").val(parm);
    $('#confirm-delete').modal('show');   
}

$(function () {
        $('#confirm-delete').on('show.bs.modal',function(){
                    $('.btn-ok').click(function(){
                               showLoader();
            $("#operacion").val("conEliminarAsiento");
            $("#frm_generico").submit();
            });
        });

    $("#btnConAsientoVentas").click(function () {              
            if ($("#docSelected").val()===""){
                 toastError("Debe seleccionar algun Item");
                return false;
            }
            showLoader();
            $("#operacion").val("conAsientoVentas");
            $("#frm_generico").submit();

    });
    
    $("#btnConAsientoCajero").click(function () {  
            if  ($("#secuencia").val()===""){
                toastError("Se debe ingresar el nro de inicio a generar");
                return false;               
            }
            var valor =  $("#docSelected").length;
            if ($("#docSelected").val()===""){
                toastError("Debe seleccionar algun Item");
                return false;
            }
            showLoader();
            $("#operacion").val("conAsientoCajero");
            $("#frm_generico").submit();

    });    
    
    
});

function seleccinadosTodos()
{
    var texto="";
    $('#tablaPartesProductos').DataTable().rows().iterator('row', function(context, index)
    {
        var node = $(this.row(index).node());         	
        var chk = node.find(".chkbx").eq(0);  
        if ($("#chkbxTodos").prop("checked")===true)
            chk.prop("checked",true);
        else            
            chk.prop("checked",false);        
        
        if (chk.prop("checked")===true)
        {
            texto += chk.attr("data-selected") + ",";
        }    
    });   
    texto = texto.substring(0, texto.length - 1);
    $('#docSelected').val(texto);  
}

function seleccinados() 
{
    var texto="";
    $('#tablaPartesProductos').DataTable().rows().iterator('row', function(context, index)
    {
        var node = $(this.row(index).node());         	
        var chk = node.find(".chkbx").eq(0);
        
        console.log(chk.prop("checked"));
        if (chk.prop("checked")===true)
        {
            texto += chk.attr("data-selected") + ",";
        }
    });   
    $('#docSelected').val(texto);  
}




/*-----ELIMINARRRRRRRRRRRRRRRRRR ---*/
function seleccinadosElimina()
{
    var texto="";
    $('#tablaPartesProductos').DataTable().rows().iterator('row', function(context, index)
    {
        var node = $(this.row(index).node());         	
        var chk = node.find(".chkbxDel").eq(0);  
        if ($("#chkbxTodosDel").prop("checked")===true)
            chk.prop("checked",true);
        else            
            chk.prop("checked",false);        
        
        if (chk.prop("checked")===true)
        {
            texto += chk.attr("data-selected") + ",";
        }    
    });   
    texto = texto.substring(0, texto.length - 1);
    $('#docSelectedDel').val(texto);  
}

function seleccinadosDel() 
{
    var texto="";
    $('#tablaPartesProductos').DataTable().rows().iterator('row', function(context, index)
    {
        var node = $(this.row(index).node());         	
        var chk = node.find(".chkbxDel").eq(0);
        
        console.log(chk.prop("checked"));
        if (chk.prop("checked")===true)
        {
            texto += chk.attr("data-selected") + ",";
        }
    });   
    $('#docSelectedDel').val(texto);  
}