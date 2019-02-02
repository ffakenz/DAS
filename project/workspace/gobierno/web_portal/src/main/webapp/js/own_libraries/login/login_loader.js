$(() => {
    /* Initialization Code */
    const home = new Home(HomeConfig);
    jUtils.loadModule(home);
    const login = new Login(LoginConfig);
    [login, home].forEach(jUtils.loadModule);
    console.log("Login View Loaded Modules: [LoginConfig] = %o, [Login] = %o", 
        LoginConfig, login);
});

$(window).on('load', () => {
    /*  $("#concesionarias_table").DataTable({}); WTF IS HAPPENING WITH THIS!!! MTFCKR */
    console.log("Login View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Login View executed Cleaning UP");
    };
    Cleanup();
});