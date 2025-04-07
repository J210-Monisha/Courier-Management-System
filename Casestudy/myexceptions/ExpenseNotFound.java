package myexceptions;

@SuppressWarnings("serial")
public class ExpenseNotFoundException extends Exception {
    public ExpenseNotFoundException(String message) {
        super(message);
    }
}
