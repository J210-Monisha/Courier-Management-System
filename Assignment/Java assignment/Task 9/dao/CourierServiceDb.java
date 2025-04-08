package test;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import Serviceimpl.CourierServiceImpl;
import entities.Courier;
import exceptions.*;

public class TestCourierService {
    public static void main(String[] args) {
        try {
            CourierServiceImpl service = new CourierServiceImpl();

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date dispatchDate = sdf.parse("2025-08-09");
            Date deliveryDate = sdf.parse("2025-08-13");

            Courier c1 = new Courier(6, "Alice", "Delhi", "Bob", "Mumbai", 2.5, "Pending", dispatchDate, deliveryDate);

            String trackingNumber = service.placeOrder(c1);
            try {
                System.out.println("Order Status: " + service.getOrderStatus(trackingNumber));
            } catch (TrackingNumberNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                boolean cancelled = service.cancelOrder(trackingNumber);
                System.out.println("Cancelled: " + cancelled);
                System.out.println("Updated Status: " + service.getOrderStatus(trackingNumber));
            } catch (TrackingNumberNotFoundException e) {
                System.out.println(e.getMessage());
            }

            try {
                List<Courier> list = service.getAssignedOrder(123); 
                System.out.println("Assigned Orders: " + list);
            } catch (InvalidEmployeeIdException e) {
                System.out.println(e.getMessage());
            }
            try {
                System.out.println("\nTrying with a fake tracking number...");
                String fakeTrackingNumber = "TRK9999";
                System.out.println("Order Status: " + service.getOrderStatus(fakeTrackingNumber));
            } catch (TrackingNumberNotFoundException e) {
                System.out.println("Exception caught: " + e.getMessage());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
