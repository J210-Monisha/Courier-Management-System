package exceptions;

@SuppressWarnings("serial")
public class TrackingNumberNotFoundException extends Exception {
    public TrackingNumberNotFoundException(String message) {
        super(message);
    }
}