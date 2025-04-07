package dao;
import java.util.List;
import Model.Expense;
import Model.User;

public interface IFinanceRepository {
    
    boolean createUser(User user);
    
    boolean createExpense(Expense expense);
    
    boolean deleteUser(int userId);
    
    boolean deleteExpense(int expenseId);
    
    List<Expense> getAllExpenses(int userId);
    
    boolean updateExpense(int userId, Expense expense);
}