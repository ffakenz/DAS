package contract;

import beans.PlanBean;

interface Message { }
interface ConsultarPlanes extends Message { }
interface ConsultarPlan extends Message {
    Long planId();
}
interface CancelarPlan extends Message {
    PlanBean planGanador();
}