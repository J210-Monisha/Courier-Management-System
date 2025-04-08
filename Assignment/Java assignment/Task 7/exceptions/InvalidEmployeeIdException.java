package exceptions;

@SuppressWarnings("serial")
public class InvalidEmployeeIdException extends Exception {
    public InvalidEmployeeIdException(String message) {
        super(message);
    }
}
