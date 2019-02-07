class Calendario extends Module {
    constructor(config){
        super(config);
    }

    goTo(evt) {
        evt.preventDefault();
        console.log("goTo, [EVENT] = %o", evt);
        const data = $("#" + evt.target.id).closest("form").serialize();
        console.log("data = %o", data);
        CalendarioService.GET_CALENDARIO(data);
    }
    
    showCalendarCellModal(evt) {
        evt.preventDefault();
        console.log("showCalendarCellModal, [EVENT] = %o", evt);
        const data = $("#" + evt.target.id).find("form").serialize();
        console.log("data = %o", data);
        CalendarioService.GET_CALENDAR_CELL_MODAL(data);
    }

    crearSorteo(evt) {
        evt.preventDefault();
        console.log("crearSorteo, [EVENT] = %o", evt);
        const idButton = evt.target.id;
        const fechaEjecucion = idButton.split("-")[1];  /* TODO: add this to `data` or hidden input */
        CalendarioService.CREAR_SORTEO(fechaEjecucion);
    }

    ejecutarSorteo(evt) {
        evt.preventDefault();
        console.log("ejecutarSorteo, [EVENT] = %o", evt);
        const idButton = evt.target.id;
        const idSorteo = idButton.split("-")[1];
        CalendarioService.EJECUTAR_SORTEO(idSorteo);
    }

    actualizarFechaSorteo(evt) {
        evt.preventDefault();
        console.log("actualizarFechaSorteo, [EVENT] = %o", evt);
        const idButton = evt.target.id;
        const dataOnButtonId = idButton.split("-"); /* TODO: Check attribute `data` in HTML5 */
        const idSorteo = dataOnButtonId[1];
        const fecha = dataOnButtonId[2];
        CalendarioService.ACTUALIZAR_FECHA_SORTEO(idSorteo, fecha);
    }
};

$(() => {
    /* Initialization Code */
    const calendario = new Calendario(CalendarioConfig);
    jUtils.loadModule(calendario);
    console.log("Calendario View Loaded Modules: [CalendarioConfig] = %o, [Calendario] = %o",
        CalendarioConfig, calendario);
});
