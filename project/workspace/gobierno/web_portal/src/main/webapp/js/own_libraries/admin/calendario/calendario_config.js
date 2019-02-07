const CalendarioConfig = (module) => [
    {
        ctx: Id.CALENDARIO,
        cnfg: [
            { delegate: Id.CALENDAR_NEXT, handler: module.goTo },
            { delegate: Id.CALENDAR_PREV, handler: module.goTo  },
            { delegate: Class.EJECUTAR_SORTEO_BTN, handler: module.ejecutarSorteo },
            { delegate: Class.ACTUALIZAR_FECHA_SORTEO_BTN, handler: module.actualizarFechaSorteo },
            { delegate: Class.CALENDARIO_CELL, handler: module.crearSorteo }
        ]
    }
];