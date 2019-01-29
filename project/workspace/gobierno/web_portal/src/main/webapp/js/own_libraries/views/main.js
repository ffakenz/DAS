$(() => {
    /* run code that MUST be after initialize */
    const login = new Login(Config.login);
    const home = new Home(Config.home);
    const concesionarias = new Concesionarias(Config.concesionarias);

    /* load modules evnt handlers */
    [home, login, concesionarias].forEach(jUtils.loadModule);
    console.log("Main View Loaded Modules: [CONFIG] = %o, [HOME] = %o, [LOGIN] = %o, [CONCESIONARIAS] = %o", 
        Config, home, login, concesionarias);
});

$(window).on('load', () => {
    console.log("Main View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Main View executed Cleaning UP");
    };
    Cleanup();
});