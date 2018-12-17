$(() => { 
    /* run code that MUST be after initialize */
    const home = new Home();
    const login = new Login();

    $("#header_home").delegate("#login", "click", home.showLogin);
    $("#header_home").delegate("#goToProfile", "click", home.goToProfile);
    $("#header_home").delegate("#logout", "click", login.closeSession);
    $("#registrar_concesionaria_div").delegate("#registrar_concesionaria", "click", home.showRegistrarConcesionaria);
    $("#goToHomeDiv").delegate("#goToHome", "click", home.goToHome);
    
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Cleaning UP");
    };

    Cleanup();
});