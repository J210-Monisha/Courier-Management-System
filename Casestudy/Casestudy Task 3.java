package dao;

import Model.Expense;
import Model.User;
import util.DBConnUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FinanceRepositoryImpl implements IFinanceRepository {

    private Connection conn;

    public FinanceRepositoryImpl() {
        this.conn = DBConnUtil.getConnection("db.properties");
    }
     
    // Task2(1)
    @Override
    public boolean createUser(User user) {
        String sql = "INSERT INTO Users (user_id, username, password, email_id) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, user.getId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, user.getEmail());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating user: " + e.getMessage());
        }
        return false;
    }
     
    //Task2(2)
    @Override
    public boolean createExpense(Expense expense) {
        String sql = "INSERT INTO Expenses (expense_id, user_id, amount, category_id, expense_date, description) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, expense.getExpenseId());
            ps.setInt(2, expense.getUserId());
            ps.setDouble(3, expense.getAmount());
            ps.setInt(4, expense.getCategoryId());
            ps.setDate(5, Date.valueOf(expense.getDate()));
            ps.setString(6, expense.getDescription());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error creating expense: " + e.getMessage());
        }
        return false;
    }


  //Task2(5)
    @Override
    public List<Expense> getAllExpenses(int userId) {
        List<Expense> expenses = new ArrayList<>();
        String sql = "SELECT * FROM Expenses WHERE user_id = ?";

        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Expense expense = new Expense();
                expense.setExpenseId(rs.getInt("expense_id"));
                expense.setUserId(rs.getInt("user_id"));
                expense.setAmount(rs.getDouble("amount"));
                expense.setCategoryId(rs.getInt("category_id"));
                expense.setDate(rs.getDate("expense_date").toLocalDate());
                expense.setDescription(rs.getString("description"));
                expenses.add(expense);
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving expenses: " + e.getMessage());
        }

        return expenses;
    }

  //Task2(3)
    @Override
    public boolean deleteUser(int userId) {
        String sql = "DELETE FROM Users WHERE user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
        }
        return false;
    }

  //Task2(4)
    @Override
    public boolean deleteExpense(int expenseId) {
        String sql = "DELETE FROM Expenses WHERE expense_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, expenseId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error deleting expense: " + e.getMessage());
        }
        return false;
    }
    
  //Task2(6)
    @Override
    public boolean updateExpense(int userId, Expense expense) {
        String sql = "UPDATE Expenses SET amount = ?, category_id = ?, expense_date = ?, description = ? WHERE expense_id = ? AND user_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, expense.getAmount());
            ps.setInt(2, expense.getCategoryId());
            ps.setDate(3, Date.valueOf(expense.getDate()));
            ps.setString(4, expense.getDescription());
            ps.setInt(5, expense.getExpenseId());
            ps.setInt(6, userId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("Error updating expense: " + e.getMessage());
        }
        return false;
    }
}

