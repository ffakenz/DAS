const CalendarioConfig = (module) => [
    {
        ctx: Id.CALENDARIO,
        cnfg: [
            { delegate: Id.CALENDAR_NEXT, handler: module.goTo },
            { delegate: Id.CALENDAR_PREV, handler: module.goTo  },
            { delegate: Class.CALENDAR_CELL, handler: module.showCalendarCellModal }
        ]
    }
];