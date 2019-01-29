$(() => {
    /* Initialization Code */
    const concesionariasService = new ConcesionariasService();
    concesionariasService.GET_CONSULTAR_TODAS(); 

    const concesionarias = new Concesionarias(Config.concesionarias, concesionariasService);
    jUtils.loadModule(concesionarias);
});

$(window).on('load', () => {
    /*  $("#tableConcesionarias").DataTable({}); WTF IS HAPPENING WITH THIS!!! MTFCKR */
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Cleaning UP");
    };
    Cleanup();
});