package test;

import dao.FinanceRepositoryImpl;
import Model.Expense;
import Model.User;
import myexceptions.UserNotFoundException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.time.LocalDate;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class FinanceRepositoryImplTest {

    private FinanceRepositoryImpl repo;

    @Before
    public void setUp() {
        repo = new FinanceRepositoryImpl();
    }

    @Test
    public void testCreateExpense() throws UserNotFoundException {
        User user = new User(10, "Alice", "Alice123", "Alice12@gmail.com");
        repo.createUser(user);
    }

    @Test
    public void testSearchExpense() throws UserNotFoundException {
        User user = new User(2, "John", "John@_123", "John19@gmail.com");
        repo.createUser(user);
    }

    @Test
    public void testSearchExpenseSuccessfully() throws UserNotFoundException {
        User user = new User(40, "Ravi", "ravi123", "ravi@gmail.com");
        repo.createUser(user);

        Expense expense = new Expense(201, 40, 200.00, 1, LocalDate.now(), "Travel");
        repo.createExpense(expense);

        List<Expense> expenses = repo.getAllExpenses(40);

        assertNotNull("Expense list should not be null", expenses);
        assertFalse("Expense list should not be empty", expenses.isEmpty());
    }

    @Test(expected = UserNotFoundException.class)
    public void testExceptionWhenUserNotFound() throws UserNotFoundException {
        repo.getAllExpenses(999);
    }

    @Test(expected = UserNotFoundException.class)
    public void testSearchExpenseThrowsUserNotFoundException() throws UserNotFoundException {
        repo.getAllExpenses(100);
    }
}