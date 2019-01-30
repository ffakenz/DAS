$(() => {
    /* run code that MUST be after initialize */
});

$(window).on('load', () => {
    const login = new Login(Config.login);
    const home = new Home(Config.home);
    const concesionarias = new Concesionarias(Config.concesionarias);

    /* load modules evnt handlers */
    [home, login, concesionarias].forEach(jUtils.loadModule);
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Cleaning UP");
    };
    Cleanup();
});