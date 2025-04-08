package test;

import dao.CourierServiceDb;
import entities.Courier;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestCourierServiceDb {
    public static void main(String[] args) {
        try {
            CourierServiceDb service = new CourierServiceDb();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            Date dispatchDate = sdf.parse("2025-08-09");
            Date deliveryDate = sdf.parse("2025-08-13");

            Courier courier = new Courier(8, "Alia", "Pune", "Rahul", "Mumbai", 4.5, "Pending", dispatchDate, deliveryDate);

            String trackingNumber = service.placeOrder(courier);

            if (trackingNumber != null) {
                System.out.println("Courier inserted successfully!");
                System.out.println("Tracking Number: " + trackingNumber);

                String status = service.getOrderStatus(trackingNumber);
                System.out.println("Status: " + status);

                boolean updated = service.updateCourierStatus(trackingNumber, "Delivered");
                System.out.println(updated ? "Status updated to Delivered!" : "Update failed!");

                String updatedStatus = service.getOrderStatus(trackingNumber);
                System.out.println("Updated Status: " + updatedStatus);

                System.out.println("Delivery History:");
                List<Courier> history = service.getDeliveryHistory(trackingNumber);
                if (history != null && !history.isEmpty()) {
                    for (Courier c : history) {
                        System.out.println(c);
                    }
                } else {
                    System.out.println("No delivery history found.");
                }
            } else {
                System.out.println("Insertion failed or duplicate tracking number.");
            }

            System.out.println("----- Shipment Report -----");
            List<Courier> allCouriers = service.getAllCouriers();
            for (Courier c : allCouriers) {
                System.out.println(c);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
