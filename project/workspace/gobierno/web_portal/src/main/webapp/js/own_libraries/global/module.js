class Module {
    constructor(eventHandlers) {
        this.eventHandlers = eventHandlers(this);
    }
    getEventHandlers(){
        return this.eventHandlers;
    }
};