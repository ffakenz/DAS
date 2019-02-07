class Calendario extends Module {
    constructor(config){
        super(config);
    }

    goToPrevMonth(evt) {
        evt.preventDefault();
        console.log("goToPrevMonth, [EVENT] = %o", evt);
        const data = $("#calendar_next").closest("form").serialize();
        console.log("data = %o", data);
        CalendarioService.GET_CALENDARIO(data);
    }

    goToNextMonth(evt) {
        evt.preventDefault();
        console.log("goToNextMonth, [EVENT] = %o", evt);
        const data = $("#calendar_prev").closest("form").serialize();
        console.log("data = %o", data);
        CalendarioService.GET_CALENDARIO(data);
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
