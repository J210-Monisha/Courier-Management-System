package dao;
import Model.Expense;
import Model.User;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FinanceRepositoryImpl {

    private Map<Integer, User> userDB = new HashMap<>();
    private Map<Integer, Expense> expenseDB = new HashMap<>();
    public boolean createUser(User user) {
        if (!userDB.containsKey(user.getUserId())) {
            userDB.put(user.getUserId(), user);
            return true;
        }
        return false;
    }
    public boolean createExpense(Expense expense) throws UserNotFoundException {
        if (!userExists(expense.getUserId())) {
            throw new UserNotFoundException("User ID " + expense.getUserId() + " not found.");
        }

        if (!expenseDB.containsKey(expense.getExpenseId())) {
            expenseDB.put(expense.getExpenseId(), expense);
            return true;
        }
        return false;
    }
    public List<Expense> getAllExpenses(int userId) throws UserNotFoundException {
        if (!userExists(userId)) {
            throw new UserNotFoundException("User ID " + userId + " not found.");
        }

        List<Expense> userExpenses = new ArrayList<>();
        for (Expense e : expenseDB.values()) {
            if (e.getUserId() == userId) {
                userExpenses.add(e);
            }
        }
        return userExpenses;
    }
    public boolean updateExpense(int userId, Expense updatedExpense) throws UserNotFoundException, ExpenseNotFoundException {
        if (!userExists(userId)) {
            throw new UserNotFoundException("User ID " + userId + " not found.");
        }

        if (!expenseDB.containsKey(updatedExpense.getExpenseId())) {
            throw new ExpenseNotFoundException("Expense ID " + updatedExpense.getExpenseId() + " not found.");
        }

        expenseDB.put(updatedExpense.getExpenseId(), updatedExpense);
        return true;
    }

    public boolean deleteExpense(int expenseId) throws ExpenseNotFoundException {
        if (!expenseDB.containsKey(expenseId)) {
            throw new ExpenseNotFoundException("Expense ID " + expenseId + " not found.");
        }

        expenseDB.remove(expenseId);
        return true;
    }
    public boolean deleteUser(int userId) throws UserNotFoundException {
        if (!userDB.containsKey(userId)) {
            throw new UserNotFoundException("User ID " + userId + " not found.");
        }

        userDB.remove(userId);
        expenseDB.values().removeIf(expense -> expense.getUserId() == userId);
        return true;
    }

    private boolean userExists(int userId) {
        return userDB.containsKey(userId);
    }
}
