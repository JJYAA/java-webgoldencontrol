
$(function () {

muestraTabla();
    
    

});

function closePerfil(){
    $("#frm_AgregarPerfil").modal("hide");
}

function GrabarPerfil()
{
    return $.ajax({
        url: "PerfilAction.do?operacion=agregarPerfil",
        type: "POST",
        dataType: "text",
        cache: false,
        data: {
            descripcion: $("#auxnombre").val(),
            perfil: "00000"
        },
        success: function (data) 
        {
               $("#frm_AgregarPerfil").modal("hide");
                muestraTabla();
        },
        error: function (xhr, status, error) {
           console.log(error);

        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        }
    });    
}

function agregarPerfil(){
    $("#frm_AgregarPerfil").modal("show");
}


function closeAgregarOpcion(){
    
    $("#frm_AgregarOpciones").modal("hide"); 
}

function closeEditPerfil(){
    $("#frm_EditarPerfil").modal("hide"); 
}

function EditarPerfil(parm,parm1){  
    $("#frm_EditarPerfil").modal("show");    
    $("#auxnombre1").val(parm1);
    $("#codigo").val(parm);
    
}

function AgregarOpciones(parm,parm1){
    $("#codigo").val(parm);
    $("#frm_AgregarItemPerfil").modal("show");  
    muestraItePerfil();
    //muestraTablasPedientes();   
    //muestraTablasAsignadas();     
}

function EliminarItemPerfil(parm,parmnombre){
     return $.ajax({
            url: "PerfilAction.do?operacion=EliminarItemPerfil",
            type: "POST",
            dataType: "text",
            cache: false,
            data: {
            	perfil: $("#codigo").val(),
                indice: parm,
                indicenombre:parmnombre
            },            
            success: function (data) {
                muestraItePerfil();             
            },
            error: function (xhr, status, error) {
                alert(error);
            },
            beforeSend: function () {
               
                showLoader();
            },
            complete: function () {
                hideLoader();
            }
        });   
}

function AgregarItemPerfil(parm,parm2){
        $("#nombre").val(parm);
        return $.ajax({
            url: "PerfilAction.do?operacion=AgregarItemPerfil",
            type: "POST",
            dataType: "text",
            cache: false,
            data: {
            perfil: $("#codigo").val(),
            nombreOpcion: $("#nombre").val()
            },            
            success: function (data) {
                muestraItePerfil();
               // tablaPendientesAsignar();
                //tablaAsignados();                
            },
            error: function (xhr, status, error) {
 
            },
            beforeSend: function () {
               
                showLoader();
            },
            complete: function () {
                hideLoader();
            }
        });

    
    
}


function muestraItePerfil(){
    return $.ajax({
        url: "PerfilAction.do?operacion=muestraItePerfil",
        type: "POST",
        dataType: "JSON",
        cache: false,
        data: {
        perfil: $("#codigo").val()
        },            
        success: function (data) {
            var jsonObjTabla = data.objTabla;
            $("#c_tablaItemsPendientes").html(jsonObjTabla.tablaPendientes);
            $("#c_tablaItemsAsignados").html(jsonObjTabla.tablaAsignados);
            
            
            iniDataTable({
                tabla: "#tablaItemsPendientes",
                filasXpagina: 15,
                cajaBuscar: false,
                responsive: true,
                cboPaginas: false,
                infoNroReg: false,
                ordenaColumna:false
            });
            
            iniDataTable({
                tabla: "#tablaItemsAsignados",
                filasXpagina: 15,
                cajaBuscar: false,
                responsive: true,
                cboPaginas: false,
                infoNroReg: false,
                ordenaColumna:false
            });
           
           
        },
        error: function (xhr, status, error) {
            alert(error);
        },
        beforeSend: function () {
           
            showLoader();
        },
        complete: function () {
            hideLoader();
        }
    });    
}

function closeItemPerfil(){
    $("#frm_AgregarItemPerfil").modal("hide");    
}

/*
function muestraTablasPedientes(){
    return $.ajax({
        url: "PerfilAction.do?operacion=VisualizarGrupoContactos",
        type: "POST",
        dataType: "JSON",
        cache: false,
        data: {
        perfil: $("#codigo").val()
        },            
        success: function (data) {
            var jsonObjTabla = data.objTabla;
            $("#c_tablaContactosPendientes").html(jsonObjTabla.tablaPendientes);
            $("#c_tablaContactosAsignados").html(jsonObjTabla.tablaAsignados);
            
            
            iniDataTable({
                tabla: "#tablaContactosPendientes",
                filasXpagina: 15,
                cajaBuscar: false,
                responsive: true,
                cboPaginas: false,
                infoNroReg: false,
                ordenaColumna:true
            });
            
            iniDataTable({
                tabla: "#tablaContactosAsignados",
                filasXpagina: 15,
                cajaBuscar: false,
                responsive: true,
                cboPaginas: false,
                infoNroReg: false,
                ordenaColumna:true
            });
           
           
        },
        error: function (xhr, status, error) {
            alert(error);
        },
        beforeSend: function () {
           
            showLoader();
        },
        complete: function () {
            hideLoader();
        }
    });   
}
*/



function GrabarEditPerfil()
{
    return $.ajax({
        url: "PerfilAction.do?operacion=agregarPerfil",
        type: "POST",
        dataType: "text",
        cache: false,
        data: {
            descripcion: $("#auxnombre1").val(),
            perfil: $("#codigo").val()
        },
        success: function (data) 
        {
                $("#frm_EditarPerfil").modal("hide");
                muestraTabla();

        },
        error: function (xhr, status, error) {
           console.log(error);

        },
        beforeSend: function () {
            showLoader();
        },
        complete: function () {
            hideLoader();
        }
    });    
}



function muestraTabla() {
        return $.ajax({
            url: "PerfilAction.do?operacion=muestraTabla",
            type: "POST",
            dataType: "JSON",
            cache: false,
            success: function (data) {
                var jsonObjTabla = data.objTabla;
                $("#c_tablaPerfil").html(jsonObjTabla.tabla);
                
                iniDataTable({
                    tabla: "#tablaPerfil",
                    filasXpagina: 10,
                    cajaBuscar: true,
                    responsive: true,
                    cboPaginas: true,
                    infoNroReg: true,
                    ordenaColumna:false
                    
                });
               
               
            },
            error: function (xhr, status, error) {
                alert(error);
            },
            beforeSend: function () {
               
                showLoader();
            },
            complete: function () {
                hideLoader();
            }
        });
    }


