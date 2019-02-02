class Admin extends Module {
    constructor(config){
        super(config);
    }

    testConsumo(evt) {
        evt.preventDefault();
        console.log("testConsumo, [EVENT] = %o", evt);
        AdminService.GET_TEST_CONSUMO();
    }
};

$(() => {
    /* Initialization Code */
    const admin = new Admin(AdminConfig);
    jUtils.loadModule(admin);
    console.log("Admin View Loaded Modules: [AdminConfig] = %o, [Admin] = %o",
        AdminConfig, admin);
});