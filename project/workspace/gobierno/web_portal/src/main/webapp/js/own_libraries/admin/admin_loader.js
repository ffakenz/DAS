$(window).on('load', () => {
    ConcesionariasService.GET_CONSULTAR_TODAS();
    console.log("Admin View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Admin View executed Cleaning UP");
    };
    Cleanup();
});