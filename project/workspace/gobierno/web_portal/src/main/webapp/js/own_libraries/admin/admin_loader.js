$(() => {
    /* Initialization Code */
    const admin = new Admin(AdminConfig);
    jUtils.loadModule(admin);
    console.log("Admin View Loaded Modules: [AdminConfig] = %o, [Admin] = %o",
        AdminConfig, admin);
});

$(window).on('load', () => {
    console.log("Admin View executed on Load");
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Admin View executed Cleaning UP");
    };
    Cleanup();
});