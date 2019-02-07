const CalendarioConfig = (module) => [
    {
        ctx: Id.CALENDARIO,
        cnfg: [
            { delegate: Id.CALENDAR_NEXT, handler: module.goTo },
            { delegate: Id.CALENDAR_PREV, handler: module.goTo  },
            { delegate: Class.SORTEO_CELL, handler: module.ejecutarSorteo },
            { delegate: Class.SORTEO_CELL, handler: module.actualizarFechaSorteo },
            { delegate: Class.EMPTY_CELL, handler: module.crearSorteo }
        ]
    }
];