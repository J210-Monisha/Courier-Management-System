package exception;

@SuppressWarnings("serial")
public class NegativeSalaryException extends Exception {
    public NegativeSalaryException(String message) {
        super(message);
    }
}
