$(() => {
    /* Initialization Code */

});

$(window).on('load', () => {
    /*  $("#tableConcesionarias").DataTable({}); WTF IS HAPPENING WITH THIS!!! MTFCKR */
    ConcesionariasService.GET_CONSULTAR_TODAS();
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Cleaning UP");
    };
    Cleanup();
});