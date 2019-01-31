let GlobalState  = (function(){
    /* Application State */
    let _LAST_CONFIGS_CONSULTED_ST = undefined;
    class GlobalState {
        constructor() {

        }

        static getLastConfigConsulted(){
            return _LAST_CONFIGS_CONSULTED_ST;
        }
    
        static setLasConfigConsulted(lastConfigConsulted) {
            _LAST_CONFIGS_CONSULTED_ST = lastConfigConsulted;
        }
    }    
    return GlobalState;
})();