$(window).on('load', () => {
    setTimeout(function(){
        ConcesionariasService.GET_CONSULTAR_TODAS();
    }, 1000);

    setTimeout(function(){
        EstadoCuentaService.GET_CONSULTAR_TODOS();
    }, 2000);

    console.log("Admin View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Admin View executed Cleaning UP");
    };
    Cleanup();
});