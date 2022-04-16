


$(function () 
{
 
        
    $("#btnConFacRep").click( function(){
        
        $("#frm_login").validate({
            rules: {
                empresa: "required",
                usuario: "required",
                contrasena: "required"
            },
            messages: {
                empresa:  "Seleccione la empresa",
                usuario: "Ingrese el usuario",
                contrasena: "Ingrese la contrase&ntilde;a"
            },
            errorLabelContainer: $("#jq-error-container ul"),
            errorElement: 'li',
            ignore: [], // validar tambien los hiddens
            showErrors: function () 
            {
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
                    $("#jq-error-container p strong").html(msgPrev + " " +
                            this.numberOfInvalids() + " " + msgPost);
                } else {
                    $("#jq-error-container").hide();
                }
                this.defaultShowErrors();
            }
        }).form();  
         if ($("#frm_login").valid()) {
            showLoader();                       // Move to a new location or you can do something else
            window.location.href = "LoginAuxiliarAction.do?operacion=validaIngreso&empresa=" + $("#empresa").val()+ "&contrasena=" + $("#clave").val() + "&usuario=" + $("#usuario").val() + "&proceso=01";             
         }     
         

    });

});



