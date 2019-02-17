$(window).on('load', () => {
    /*
    setTimeout(function(){
        CalendarioService.GET_CALENDARIO({"year": '2019', "month": '3'});
    }, 2000);
    */
    ConcesionariasService.GET_CONSULTAR_TODAS();



    console.log("Admin View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Admin View executed Cleaning UP");
    };
    Cleanup();
});