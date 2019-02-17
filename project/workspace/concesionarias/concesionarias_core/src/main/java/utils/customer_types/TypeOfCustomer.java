package utils.customer_types;


public abstract class TypeOfCustomer {
    protected int DEFAULT_NRO_CUOTAS = 35;
    public abstract boolean willPay(final Long nroCuota);
}

