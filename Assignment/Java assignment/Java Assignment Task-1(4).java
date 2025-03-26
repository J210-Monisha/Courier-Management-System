package CourierManagement;
public class Courier {
    String name;
    int maxCapacity;
    int currentLoad;
    int location; // Represents proximity (e.g., distance from warehouse)

    public Courier(String name, int maxCapacity, int location) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentLoad = 0;
        this.location = location;
    }

    // Check if this courier can take the shipment
    public boolean canTakeShipment(int shipmentWeight) {
        return (currentLoad + shipmentWeight) <= maxCapacity; // FIX: Now properly ensures capacity
    }

    // Assign shipment and update load
    public void assignShipment(int shipmentWeight) {
        if (canTakeShipment(shipmentWeight)) {
            this.currentLoad += shipmentWeight; // Update the current load correctly
        } else {
            System.out.println(name + " CANNOT take shipment of " + shipmentWeight + "kg. Capacity exceeded!");
        }
    }
}

package CourierManagement;
public class Shipment {
    int weight;
    int destination;

    public Shipment(int weight, int destination) {
        this.weight = weight;
        this.destination = destination;
    }
}

package CourierManagement;
import java.util.ArrayList;
import java.util.List;

public class CourierManagementSystem {
    public static void main(String[] args) {
        List<Courier> couriers = new ArrayList<>();
        List<Shipment> shipments = new ArrayList<>();

        couriers.add(new Courier("Courier1", 100, 10));
        couriers.add(new Courier("Courier2", 150, 5));
        couriers.add(new Courier("Courier3", 200, 8));

        shipments.add(new Shipment(50, 7));
        shipments.add(new Shipment(80, 4));
        shipments.add(new Shipment(120, 6));
        shipments.add(new Shipment(30, 5));

       
        for (Shipment shipment : shipments) {
            Courier bestCourier = null;

            for (Courier courier : couriers) {
                
                if (courier.canTakeShipment(shipment.weight)) {
                    
                    if (bestCourier == null || courier.location < bestCourier.location) {
                        bestCourier = courier;
                    }
                }
            }

            if (bestCourier != null) {
                bestCourier.assignShipment(shipment.weight); 
                System.out.println("Shipment (Weight: " + shipment.weight + ", Destination: " + shipment.destination +
                        ") assigned to " + bestCourier.name + " | Remaining Capacity: " + (bestCourier.maxCapacity - bestCourier.currentLoad));
            } else {
                System.out.println("Shipment (Weight: " + shipment.weight + ", Destination: " + shipment.destination +
                        ") could not be assigned. No available courier.");
            }
        }
    }
}
