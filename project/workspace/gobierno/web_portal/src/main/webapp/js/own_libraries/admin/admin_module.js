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