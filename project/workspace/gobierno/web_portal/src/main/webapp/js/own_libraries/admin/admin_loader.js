$(window).on('load', () => {
    setTimeout(function(){
        ConcesionariasService.GET_CONSULTAR_TODAS();
    }, 1000);

    setTimeout(function(){
        EstadoCuentaService.GET_CONSULTAR_TODOS();
    }, 2000);

    setTimeout(function(){
        CalendarioService.GET_SORTEOS();
    }, 3000);

    setTimeout(function(){
        ConsumoService.GET_JOB_RESULTS_REPORT();
    }, 4000);

    console.log("Admin View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Admin View executed Cleaning UP");
    };
    Cleanup();
});