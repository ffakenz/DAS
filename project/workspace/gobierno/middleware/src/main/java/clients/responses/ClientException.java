package clients.responses;

public class ClientException extends Exception {

    public ClientException(final Exception ex) {
        super(ex.getMessage(), ex.getCause());
    }

    public ClientException(final String message) {
        super(message);
    }
}