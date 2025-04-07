package dao;
import java.util.List;
import Model.Expense;
import Model.User;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

public interface IFinanceRepository {
    
    boolean createUser(User user);
    
    boolean createExpense(Expense expense);
    
    boolean deleteUser(int userId) throws UserNotFoundException;
    
    boolean deleteExpense(int expenseId) throws ExpenseNotFoundException;
    
    List<Expense> getAllExpenses(int userId);
    
    boolean updateExpense(int userId, Expense expense) throws ExpenseNotFoundException;
}