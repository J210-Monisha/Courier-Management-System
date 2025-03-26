package UserAuthentication;
import java.util.HashMap;
import java.util.Scanner;
public class user {
    private static HashMap<String, String> employees = new HashMap<>();
    private static HashMap<String, String> customers = new HashMap<>();

    public static void main(String[] args) {
        initializeUsers();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        
        System.out.println("Welcome to the Authentication System");
        System.out.print("Enter user type (employee/customer): ");
        String userType = scanner.nextLine().toLowerCase();
        
        if (!userType.equals("employee") && !userType.equals("customer")) {
            System.out.println("Invalid user type. Exiting...");
            return;
        }
        
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        if (authenticateUser(userType, username, password)) {
            System.out.println("Login successful! Welcome, " + username + "!");
        } else {
            System.out.println("Invalid credentials. Please try again.");
        }
    }

    private static void initializeUsers() {
       
        employees.put("emp1", "password123");
        employees.put("emp2", "securepass");
        
        customers.put("cust1", "mypassword");
        customers.put("cust2", "pass123");
    }

    private static boolean authenticateUser(String userType, String username, String password) {
        if (userType.equals("employee")) {
            return employees.containsKey(username) && employees.get(username).equals(password);
        } else {
            return customers.containsKey(username) && customers.get(username).equals(password);
        }
    }
}