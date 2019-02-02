$(() => {
    $('#carousel-1').carousel();
});

$(window).on('load', () => {
    HomeService.GET_CONSULTAR_APROBADAS();
    console.log("Executed get_consultar_aprobadas");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Home View executed Cleaning UP");
    };
    Cleanup();
});