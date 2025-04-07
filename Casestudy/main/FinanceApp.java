package main;
import java.time.LocalDate;
import Model.Expense;
import Model.User;
import dao.FinanceRepositoryImpl;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

public class FinanceApp {
    public static void main(String[] args) {

        FinanceRepositoryImpl repo = new FinanceRepositoryImpl();

        try {
            User user = new User(1, "Monisha", "Monisha@123", "Monisha@gmail.com");
            boolean userInserted = repo.createUser(user);
            System.out.println(userInserted ? "User inserted successfully." : "User insertion failed.");
        } catch (Exception e) {
            System.out.println("Failed to insert user: " + e.getMessage());
        }

        try {
            Expense expense1 = new Expense(5, 2, 1200.0, 1, LocalDate.now(), "Books");
            boolean expenseInserted = repo.createExpense(expense1);
            System.out.println(expenseInserted ? "Expense inserted successfully." : "Expense insertion failed.");
        } catch (UserNotFoundException e) {
            System.out.println("Expense creation failed: " + e.getMessage());
        }

        try {
            boolean userDeleted = repo.deleteUser(2);
            System.out.println(userDeleted ? "User deleted successfully." : "Failed to delete user.");
        } catch (UserNotFoundException e) {
            System.out.println("Failed to delete user: " + e.getMessage());
        }

        try {
            boolean expenseDeleted = repo.deleteExpense(2);
            System.out.println(expenseDeleted ? "Expense deleted successfully." : "Failed to delete expense.");
        } catch (ExpenseNotFoundException e) {
            System.out.println("Failed to delete expense: " + e.getMessage());
        }

        try {
            Expense expense11 = new Expense(5, 1, 1200.0, 1, LocalDate.now(), "Books");
            boolean expenseInserted1 = repo.createExpense(expense11);
            System.out.println(expenseInserted1 ? "Expense inserted successfully." : "Expense insertion failed.");
        } catch (UserNotFoundException e) {
            System.out.println("Expense creation failed: " + e.getMessage());
        }

        try {
            Expense updatedExpense = new Expense(5, 1, 1350.0, 1, LocalDate.now(), "Updated Books");
            boolean expenseUpdated = repo.updateExpense(1, updatedExpense);
            System.out.println(expenseUpdated ? "Expense updated successfully." : "Failed to update expense.");
        } catch (UserNotFoundException | ExpenseNotFoundException e) {
            System.out.println("Expense update failed: " + e.getMessage());
        }
    }
}

