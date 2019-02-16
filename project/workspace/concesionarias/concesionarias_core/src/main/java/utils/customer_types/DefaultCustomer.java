package utils.customer_types;

public class DefaultCustomer extends TypeOfCustomer {
    @Override
    boolean willPay(final Long nroCuota) {
        return nroCuota < DEFAULT_NRO_CUOTAS; // default pays the first 34 cuotas
    }
}
