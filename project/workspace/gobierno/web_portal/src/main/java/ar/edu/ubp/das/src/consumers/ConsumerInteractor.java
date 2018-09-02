package ar.edu.ubp.das.src.consumers;

import ar.edu.ubp.das.mvc.action.DynaActionForm;
import ar.edu.ubp.das.src.consumers.model.consumer.ConsumerManager;
import ar.edu.ubp.das.src.core.Interactor;
import ar.edu.ubp.das.src.core.InteractorResponse;

public class ConsumerInteractor implements Interactor<Long> {

    private ConsumerManager consumerManager;

    public ConsumerInteractor(final ConsumerManager consumerManager) {
        this.consumerManager = consumerManager;
    }

    @Override
    public InteractorResponse<Long> execute(final DynaActionForm form) {
        return null;
    }

}