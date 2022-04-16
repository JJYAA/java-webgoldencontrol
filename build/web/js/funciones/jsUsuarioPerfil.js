
$(function () {
muestraUsuarioPerfil();
});

function agregarUsuario(){
    $("#perfil").val("");
    $("#dni").val("");
    $("#nombre").val("");
    $("#dni").prop("readonly", false);
    $("#frm_EditarUsuario").modal("show");    
}
function closeEditUsuario(){
    $("#frm_EditarUsuario").modal("hide");
}
function AgregarUsuario(codigo){
    return $.ajax({
            url: "PerfilAction.do?operacion=muestraUsuarioDatos",
            type: "POST",
            dataType: "JSON",
            cache: false,
            data:{
                dni:codigo
            },
            success: function (data) {
                var jsonObjDatos = data.datos;
                $("#dni").val(jsonObjDatos.dni);
                $("#nombre").val(jsonObjDatos.nombre);
                $("#perfil").val(jsonObjDatos.perfil);
               // $("#chk").checked(true);
                $('#dni').prop('readonly', true);
                if (jsonObjDatos.activo==='1'){
                    $('#chk').prop('checked', true);
                }                    
                else {
                    $('#chk').prop('checked', false);
                }
                    
               // $("#chk").val();
                $("#txtusuario").val(jsonObjDatos.usuario);
                $("#txtpassword").val(jsonObjDatos.contrasenha);
                 $("#frm_EditarUsuario").modal("show");
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
   
  /*  $("#codigo").val(codigo);
    $("#dni").val(codigo);
    $("#perfil").val(perfil);
    $("#nombre").val(nombre);
    $("#dni").prop("readonly", true);
    //$("#chk").checked(true);
    if (activo==='0')
        $("#chk").prop('checked', false);
    else
        $("#chk").prop('checked', true);
    $("#frm_EditarUsuario").modal("show");*/
}

function GrabarEditUsuario(){
    var ls_activo='0';
   if( $('#chk').prop('checked') ) {
        ls_activo='1';
    }
    return $.ajax({
            url: "PerfilAction.do?operacion=GrabarEditUsuarioPerfil",
            type: "POST",
            dataType: "text",
            cache: false,
            data:{
                dni:$("#dni").val(),
                nombre:$("#nombre").val(),
                perfil:$("#perfil").val(),
                activo:ls_activo,
                usuario: $("#txtusuario").val(),
                contrasenha: $("#txtpassword").val()
            },
            success: function (data) {
                $("#frm_EditarUsuario").modal("hide");
                muestraUsuarioPerfil();
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

function muestraUsuarioPerfil() {
        return $.ajax({
            url: "PerfilAction.do?operacion=muestraUsuarioPerfil",
            type: "POST",
            dataType: "JSON",
            cache: false,
            data:{
                dni:"%"
            },
            success: function (data) {
                var jsonObjTabla = data.objTabla;
                $("#c_tablaUsuarioPerfil").html(jsonObjTabla.tabla);
                
                iniDataTable({
                    tabla: "#tablaUsuarioPerfil",
                    filasXpagina: 10,
                    cajaBuscar: false,
                    responsive: true,
                    cboPaginas: false,
                    infoNroReg: true,
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
