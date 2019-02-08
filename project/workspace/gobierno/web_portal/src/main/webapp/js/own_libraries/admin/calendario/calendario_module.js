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

    ejecutarSorteo(evt) {
        evt.preventDefault();
        console.log("ejecutarSorteo, [EVENT] = %o", evt);
        const idButton = evt.target.id;
        const idSorteo = idButton.split("-")[1];
        CalendarioService.EJECUTAR_SORTEO(idSorteo);
    }

    crearSorteo(evt) {
        evt.preventDefault();
        console.log("crearSorteo, [EVENT] = %o", evt);
        const _elem = $("#" + evt.target.id);
        const data =  $(_elem).closest("form").serialize();
        console.log("data = %o", data);
        CalendarioService.CREAR_SORTEO(data);
    }

    actualizarSorteo(evt) {
        evt.preventDefault();
        console.log("actualizarFechaSorteo, [EVENT] = %o", evt);
        const idButton = evt.target.id;
        const dataOnButtonId = idButton.split("-"); /* TODO: Check attribute `data` in HTML5 */
        const idSorteo = dataOnButtonId[1];
        const fecha = dataOnButtonId[2];
        const data = { "id_sorteo": idSorteo, "fecha": fecha };
        CalendarioService.ACTUALIZAR_SORTEO(data);
    }
};

$(() => {
    /* Initialization Code */
    const calendario = new Calendario(CalendarioConfig);
    jUtils.loadModule(calendario);
    console.log("Calendario View Loaded Modules: [CalendarioConfig] = %o, [Calendario] = %o",
        CalendarioConfig, calendario);
});
