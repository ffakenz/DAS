const CalendarioConfig = (module) => [
    {
        ctx: Id.CALENDARIO,
        cnfg: [
            { delegate: Id.CALENDAR_NEXT, handler: module.goTo },
            { delegate: Id.CALENDAR_PREV, handler: module.goTo  },
            { delegate: Class.CALENDAR_CELL, handler: module.showCalendarCellModal }
        ]
    },
    {
        ctx: Id.MODAL_CONTENT,
        cnfg: [
            { delegate: Id.CREATE_SORTEO_BTN, handler: module.crearSorteo },
            { delegate: Id.UPDATE_SORTEO_BTN, handler: module.actualizarSorteo }
        ]
    }
];