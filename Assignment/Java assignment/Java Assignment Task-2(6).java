package order;
class PurchaseOrder {
    String customerName;
    String orderDetails;

    public PurchaseOrder(String customerName, String orderDetails) {
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
	   	    List<PurchaseOrder> orders = new ArrayList<>();
	        orders.add(new PurchaseOrder("Alice", "Laptop"));
	        orders.add(new PurchaseOrder("Bob", "Smartphone"));
	        orders.add(new PurchaseOrder("Alice", "Tablet"));
	        orders.add(new PurchaseOrder("Charlie", "Headphones"));
	        orders.add(new PurchaseOrder("Alice", "Smartwatch"));

	        String customerToFind = "Alice";
	        System.out.println("Orders for " + customerToFind + ":");
	        for (PurchaseOrder order : orders) {
	            if (order.customerName.equals(customerToFind)) {
	                System.out.println(" - " + order.orderDetails);
	            }
	        }
	        
	        Random random = new Random();
	        int currentLocation = 0;
	        int destination = 100;
	        
	        System.out.println("\nTracking courier location:");
	        while (currentLocation < destination) {
	            currentLocation += random.nextInt(10) + 1; 
	            if (currentLocation > destination) {
	                currentLocation = destination;
	            }
	            System.out.println("Courier at location: " + currentLocation);
	            try {
	                Thread.sleep(1000); 
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	            }
	        }
	        System.out.println("Courier has reached the destination!");
	    }
	}

