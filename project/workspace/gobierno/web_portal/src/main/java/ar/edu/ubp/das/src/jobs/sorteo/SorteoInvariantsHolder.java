package ar.edu.ubp.das.src.jobs.sorteo;

import beans.PlanBean;

public class SorteoInvariantsHolder implements ISorteoInvariantsHolder {
    // TODO: Add List<Cuotas> to PlanBean in order to verify this invariant
    public Boolean isPlanCancelado(final PlanBean planBeanResponse) {
        return true;
    }
}
