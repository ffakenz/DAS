var home = {

    showRegistrarConcesionaria : function () {

        console.log("entro a registrar concesionaria");

        $.get("/web_portal/home/ShowRegistrarConcesionaria.do", function(data, status){
            alert("Data: " + data + "\nStatus: " + status);
        });


        // jUtils.executing( "mensaje" );
        // $.ajax({
        //     url: "/web_portal/home/ShowRegistrarConcesionaria.do",
        //     type: "post",
        //     data: $( "#formConcesionarias" ).serialize(),
        //     dataType: "html",
        //     error: function(hr){
        //         jUtils.hiding("mensaje");
        //         jUtils.showing("error", hr.responseText);
        //     },
        //     success: function(html) {
        //         jUtils.hiding("error");
        //         jUtils.showing("mensaje", html);
        //     }
        // });
    }
};
