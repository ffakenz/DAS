const CalendarioConfig = (module) => [
    {
        ctx: Id.CALENDARIO,
        cnfg: [
            { delegate: Class.EJECUTAR_SORTEO_BTN, handler: module.ejecutarSorteo },
            { delegate: Class.ACTUALIZAR_FECHA_SORTEO_BTN, handler: module.actualizarFechaSorteo },
            { delegate: Class.CALENDARIO_CELL, handler: module.crearSorteo }
        ]
    }
];