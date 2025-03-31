package courierdb;
class Courier {
    int id;
    double x, y; 
    boolean available;

    public Courier(int id, double x, double y, boolean available) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.available = available;
    }
}

public class CourierFinder {  
    public static Courier findNearestCourier(Courier[] couriers, double orderX, double orderY) {
        Courier nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Courier courier : couriers) {
            if (courier.available) {
                double distance = Math.hypot(courier.x - orderX, courier.y - orderY); 
                if (distance < minDistance) {
                    minDistance = distance;
                    nearest = courier;
                }
            }
        }
        return nearest;
    }

    public static void main(String[] args) {
        Courier[] couriers = {
            new Courier(1, 1.0, 2.0, true),
            new Courier(2, 2.0, 3.0, false),
            new Courier(3, 5.0, 1.0, true),
            new Courier(4, 0.0, 0.0, true)
        };
        
        double orderX = 2.0, orderY = 2.0;
        Courier nearestCourier = findNearestCourier(couriers, orderX, orderY);
        
        if (nearestCourier != null) {
            System.out.println("Nearest available courier ID: " + nearestCourier.id);
        } else {
            System.out.println("No available courier found.");
        }
    }
}
