package order;
class packageorder {
    String customerName;
    String orderDetails;

    public packageorder(String customerName, String orderDetails) {
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }
}

package order;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderProcessor {
    public static void main(String[] args) {
        List<packageorder> orders =  new ArrayList<>();
        orders.add(new packageorder("Alice", "Laptop"));
        orders.add(new packageorder("Bob", "Smartphone"));
        orders.add(new packageorder("Alice", "Tablet"));
        orders.add(new packageorder("Charlie", "Headphones"));
        orders.add(new packageorder("Alice", "Smartwatch"));

        String customerToFind = "Bob";
        
        System.out.println("Orders for " + customerToFind + ":");
        for (packageorder order : orders) {
            if (order.customerName.equals(customerToFind)) {
                System.out.println(" - " + order.orderDetails);
            }
        }
        
        Random random = new Random();
        int currentLocation = 0;
        int destination = 100;
        int[] trackingHistory = new int[21]; 
        int index = 0;
        
        System.out.println("\nTracking courier location:");
        while (currentLocation < destination) {
            currentLocation += random.nextInt(10) + 1; 
            if (currentLocation > destination) {
                currentLocation = destination;
            }
            
            if (index < trackingHistory.length) {
                trackingHistory[index] = currentLocation; 
                index++;
            }
            
            System.out.println("Courier at location: " + currentLocation);
            try {
                Thread.sleep(1000); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Courier has reached the destination!");
        System.out.println("\nTracking History:");
        for (int i = 0; i < index; i++) {
            System.out.println("Checkpoint " + (i + 1) + ": Location " + trackingHistory[i]);
        }
    }
}
