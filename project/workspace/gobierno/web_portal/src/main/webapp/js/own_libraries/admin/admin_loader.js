$(window).on('load', () => {
    /*
    setTimeout(function(){
        ConcesionariasService.GET_CONSULTAR_TODAS();
    }, 2000);
    */


    CalendarioService.GET_CALENDARIO({"year": '2019', "month": '3'});

    console.log("Admin View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Admin View executed Cleaning UP");
    };
    Cleanup();
});