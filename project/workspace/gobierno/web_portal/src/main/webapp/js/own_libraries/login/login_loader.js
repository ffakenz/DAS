$(() => {
    /* Initialization Code */
    const login = new Login(LoginConfig);
    jUtils.loadModule(login);
    console.log("Login View Loaded Modules: [CONFIG] = %o, [Login] = %o", 
        Config, login);
});

$(window).on('load', () => {
    /*  $("#tableConcesionarias").DataTable({}); WTF IS HAPPENING WITH THIS!!! MTFCKR */
    console.log("Login View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Login View executed Cleaning UP");
    };
    Cleanup();
});