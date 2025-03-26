package ordersystem;
class PurchaseOrder {
    String customerName;
    String orderDetails;

    public PurchaseOrder(String customerName, String orderDetails) {
        this.customerName = customerName;
        this.orderDetails = orderDetails;
    }
}

package ordersystem;
import java.util.ArrayList;
import java.util.List;
public class Purchase {
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
    }
}