package utils.customer_types;

public class CustomerWithDelay extends TypeOfCustomer {
    private final int delay;

    public CustomerWithDelay(final int delay){
        this.delay = delay;
    }
    @Override
    public boolean willPay(final Long nroCuota) {
        return nroCuota < (DEFAULT_NRO_CUOTAS - delay);
    }
}
