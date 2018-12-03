package ar.edu.ubp.das.src.jobs.sorteo;

import beans.NotificationUpdate;

public class SorteoInvariantsHolder implements ISorteoInvariantsHolder {
    public Boolean isPlanCancelado(final NotificationUpdate planBeanResponse) {
        return true;
    }
}
