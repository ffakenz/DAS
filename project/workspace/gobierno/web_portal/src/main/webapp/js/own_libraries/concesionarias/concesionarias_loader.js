$(window).on('load', () => {
    /* $('#concesionarias_table').DataTable({});*/

    console.log("Concesionarias View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Concesionarias View executed Cleaning UP");
    };
    Cleanup();
});