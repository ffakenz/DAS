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