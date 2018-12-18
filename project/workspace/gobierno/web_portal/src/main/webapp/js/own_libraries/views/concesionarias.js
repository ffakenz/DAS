$(() => {
    /* Initialization Code */
    const concesionariasService = new ConcesionariasService();
    concesionariasService.GET_CONSULTAR_TODAS(); 
});

$(window).on('load', () => {
    $("#tableConcesionarias").DataTable({}); /* WTF IS HAPPENING WITH THIS!!! MTFCKR */
    const concesionarias = new Concesionarias(Config.concesionarias);
    jUtils.loadModule(concesionarias);
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Cleaning UP");
    };
    Cleanup();
});