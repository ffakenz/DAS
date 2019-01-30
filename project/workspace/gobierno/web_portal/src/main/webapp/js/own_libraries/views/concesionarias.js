$(() => {
    /* Initialization Code */
    const concesionarias = new Concesionarias(Config.concesionarias);
    jUtils.loadModule(concesionarias);
    console.log("Concesionarias View Loaded Modules: [CONFIG] = %o, [CONCESIONARIAS] = %o", 
        Config, concesionarias);
});

$(window).on('load', () => {
    ConcesionariasService.GET_CONSULTAR_TODAS();
    console.log("Concesionarias View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Concesionarias View executed Cleaning UP");
    };
    Cleanup();
});