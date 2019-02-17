package utils.customer_types;

public class IdealCustomer extends TypeOfCustomer {
    @Override
    public boolean willPay(final Long nroCuota) {
        return true;
    }
}
