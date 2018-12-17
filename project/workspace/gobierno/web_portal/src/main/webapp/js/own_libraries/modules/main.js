$(() => {
    /* run code that MUST be after initialize */
    const login = new Login(Config.login);
    const home = new Home(Config.home);
    [home, login].forEach(module => module.getEventHandlers().forEach(evt => {
        evt.cnfg.forEach(cnfg => {
            console.log("Handle Click For CTX = %o - CNFG = %o", evt.ctx, cnfg);
            $(evt.ctx).delegate(cnfg.delegate, "click", cnfg.handler);
        });
    }));    
});

$(window).on('unload', function() {
    function Cleanup() {
        console.log("Cleaning UP");
    };

    Cleanup();
});