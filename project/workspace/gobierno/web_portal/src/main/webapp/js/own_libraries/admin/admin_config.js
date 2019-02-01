const AdminConfig = (module) => [
    { 
        ctx: Id.TEST_CONSUMO_DIV,
        cnfg: [
            { delegate: Id.TEST_CONSUMO_BTN, handler: module.testConsumo }
        ]
    }
];