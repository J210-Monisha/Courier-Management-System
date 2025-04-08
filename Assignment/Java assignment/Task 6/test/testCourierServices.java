package test;
import entities.Courier;
import Serviceimpl.CourierServiceImpl;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestCourierService {
    public static void main(String[] args) {
        try {
            CourierServiceImpl service = new CourierServiceImpl();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dispatchDate = sdf.parse("2025-03-16");
            Date deliveryDate = sdf.parse("2025-03-20");

            Courier c1 = new Courier(5,"Monisha M", "Delhi", "Peter", "Mumbai", 25.0, "Pending", dispatchDate, deliveryDate);

            String trackingNumber = service.placeOrder(c1);
            System.out.println("Tracking Number: " + trackingNumber);

            System.out.println("Order Status: " + service.getOrderStatus(trackingNumber));
            
            boolean cancelled = service.cancelOrder(trackingNumber);
            System.out.println("Cancelled: " + cancelled);
            System.out.println("Updated Status: " + service.getOrderStatus(trackingNumber));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
