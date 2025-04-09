package main;

import java.time.LocalDate;
import java.util.Scanner;

import Model.Expense;
import Model.User;
import dao.FinanceRepositoryImpl;
import myexceptions.ExpenseNotFoundException;
import myexceptions.UserNotFoundException;

public class FinanceApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FinanceRepositoryImpl repo = new FinanceRepositoryImpl();

        while (true) {
            System.out.println("\n--- Finance Management Menu ---");
            System.out.println("1. Add User");
            System.out.println("2. Add Expense");
            System.out.println("3. Delete User");
            System.out.println("4. Delete Expense");
            System.out.println("5. Update Expense");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter User ID: ");
                        int userId = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Password: ");
                        String password = sc.nextLine();
                        System.out.print("Enter Email: ");
                        String email = sc.nextLine();

                        User user = new User(userId, name, password, email);
                        boolean userInserted = repo.createUser(user);
                        System.out.println(userInserted ? "User inserted successfully." : "User insertion failed.");
                        break;

                    case 2:
                        System.out.print("Enter Expense ID: ");
                        int expenseId = sc.nextInt();
                        System.out.print("Enter User ID: ");
                        int expUserId = sc.nextInt();
                        System.out.print("Enter Amount: ");
                        double amount = sc.nextDouble();
                        System.out.print("Enter Category ID: ");
                        int categoryId = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter Description: ");
                        String desc = sc.nextLine();

                        Expense expense = new Expense(expenseId, expUserId, amount, categoryId, LocalDate.now(), desc);
                        boolean expenseInserted = repo.createExpense(expense);
                        System.out.println(expenseInserted ? "Expense inserted successfully." : "Expense insertion failed.");
                        break;

                    case 3:
                        System.out.print("Enter User ID to delete: ");
                        int delUserId = sc.nextInt();
                        boolean userDeleted = repo.deleteUser(delUserId);
                        System.out.println(userDeleted ? "User deleted successfully." : "Failed to delete user.");
                        break;

                    case 4:
                        System.out.print("Enter Expense ID to delete: ");
                        int delExpId = sc.nextInt();
                        boolean expenseDeleted = repo.deleteExpense(delExpId);
                        System.out.println(expenseDeleted ? "Expense deleted successfully." : "Failed to delete expense.");
                        break;

                    case 5:
                        System.out.print("Enter Expense ID to update: ");
                        int updateExpId = sc.nextInt();
                        System.out.print("Enter User ID for the expense: ");
                        int updUserId = sc.nextInt();
                        System.out.print("Enter New Amount: ");
                        double newAmount = sc.nextDouble();
                        System.out.print("Enter New Category ID: ");
                        int newCatId = sc.nextInt();
                        sc.nextLine(); // clear buffer
                        System.out.print("Enter New Description: ");
                        String newDesc = sc.nextLine();

                        Expense updatedExpense = new Expense(updateExpId, updUserId, newAmount, newCatId, LocalDate.now(), newDesc);
                        boolean updated = repo.updateExpense(updateExpId, updatedExpense);
                        System.out.println(updated ? "Expense updated successfully." : "Failed to update expense.");
                        break;

                    case 6:
                        System.out.println("Exiting application. Goodbye!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice. Please select a valid option.");
                }
            } catch (UserNotFoundException | ExpenseNotFoundException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error: " + e.getMessage());
            }
        }
    }
}
