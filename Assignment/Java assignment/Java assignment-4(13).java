package courierdb;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shippingcalculation {
    private static final Map<String, Integer> locationDistances = new HashMap<>();
    private static final double DISTANCE_RATE = 2.0;
    private static final double RATE_1_10 = 5.0;
    private static final double RATE_11_30 = 4.5;
    private static final double RATE_31_50 = 4.0;
    private static final double RATE_51_75 = 3.5;
    private static final double RATE_76_100 = 3.0;

    static {
        locationDistances.put("Chennai-Bangalore", 350);
        locationDistances.put("Chennai-Mumbai", 1300);
        locationDistances.put("Chennai-Chennai", 10);
        locationDistances.put("Chennai-Coimbatore", 500);
        locationDistances.put("Chennai-Pune", 1200);
        locationDistances.put("Coimbatore-Mumbai", 1000);
        locationDistances.put("Coimbatore-Chennai", 500);
        locationDistances.put("Coimbatore-Pune", 950);
        locationDistances.put("Mumbai-Pune", 150);
        locationDistances.put("Bangalore-Pune", 850);
        locationDistances.put("Bangalore-Mumbai", 1000);
        locationDistances.put("Bangalore-Chennai", 350);
        locationDistances.put("Pune-Chennai", 1200);
        locationDistances.put("Pune-Coimbatore", 950);
        locationDistances.put("Pune-Mumbai", 150);
        locationDistances.put("Mumbai-Bangalore", 1000);
        locationDistances.put("Mumbai-Coimbatore", 1000);
        locationDistances.put("Mumbai-Chennai", 1300);
    }

    public static double getWeightRate(double weight) {
        if (weight >= 1 && weight <= 10) return RATE_1_10;
        else if (weight >= 11 && weight <= 30) return RATE_11_30;
        else if (weight >= 31 && weight <= 50) return RATE_31_50;
        else if (weight >= 51 && weight <= 75) return RATE_51_75;
        else return RATE_76_100;
    }

    public static double calculateShippingCost(String senderCity, String receiverCity, double weight) {
        String key1 = senderCity + "-" + receiverCity;
        String key2 = receiverCity + "-" + senderCity; 

        int distance;
        if (locationDistances.containsKey(key1)) {
            distance = locationDistances.get(key1);
        } else if (locationDistances.containsKey(key2)) {
            distance = locationDistances.get(key2);
        } else {
            System.out.println("Error: Distance data missing for these locations.");
            return -1;
        }

        double weightRate = getWeightRate(weight);
        return (weight * weightRate) + (distance * DISTANCE_RATE);
    }

    public static double getValidDoubleInput(Scanner scanner) {
        while (!scanner.hasNextDouble()) {
            System.out.println("Invalid input! Please enter a valid weight:");
            scanner.next();
        }
        return scanner.nextDouble();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Select Sender Location:");
        System.out.println("1. 123 Main St, Chennai");
        System.out.println("2. 123 MG Road, Coimbatore");
        System.out.println("3. 56 Gandhi Road, Chennai");
        int senderChoice = scanner.nextInt();
        scanner.nextLine(); 

        System.out.println("Select Receiver Location:");
        System.out.println("1. East Road, Bangalore");
        System.out.println("2. XYZ St, Mumbai");
        System.out.println("3. ABC St, Chennai");
        System.out.println("4. DEF St, Coimbatore");
        System.out.println("5. GHI St, Pune");
        int receiverChoice = scanner.nextInt();
        scanner.nextLine(); 

        String senderCity = switch (senderChoice) {
            case 1 -> "Chennai";
            case 2 -> "Coimbatore";
            case 3 -> "Chennai";
            default -> null;
        };

        String receiverCity = switch (receiverChoice) {
            case 1 -> "Bangalore";
            case 2 -> "Mumbai";
            case 3 -> "Chennai";
            case 4 -> "Coimbatore";
            case 5 -> "Pune";
            default -> null;
        };

        if (senderCity == null || receiverCity == null) {
            System.out.println("Invalid selection.");
            return;
        }

        System.out.print("Enter parcel weight (1-100 kg): ");
        double weight = getValidDoubleInput(scanner);

        if (weight < 1 || weight > 100) {
            System.out.println("Invalid weight! Enter between 1-100 kg.");
            return;
        }

        double cost = calculateShippingCost(senderCity, receiverCity, weight);
        if (cost != -1) {
            System.out.println("Shipping Cost: $" + cost);
        }

        scanner.close();
    }
}
