$(() => {
    /* Initialization Code */
    const home = new Home(HomeConfig);
    jUtils.loadModule(home);
    console.log("Home View Loaded Modules: [HomeConfig] = %o, [Home] = %o", 
        HomeConfig, home);
});

$(window).on('load', () => {
    /*  $("#tableConcesionarias").DataTable({}); WTF IS HAPPENING WITH THIS!!! MTFCKR */
    console.log("Home View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Home View executed Cleaning UP");
    };
    Cleanup();
});