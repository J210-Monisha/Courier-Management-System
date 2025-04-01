package courierdb;
import java.util.HashMap;
import java.util.Scanner;

public class ParcelTracking {
    public static void main(String[] args) {
        String[][] parcels = {
            {"P12345", "Parcel in transit"},
            {"P67890", "Parcel out for delivery"},
            {"P11121", "Parcel delivered"},
            {"P54321", "Parcel in transit"},
            {"P98765", "Parcel out for delivery"}
        };

        HashMap<String, String> parcelMap = new HashMap<>();
        for (String[] parcel : parcels) {
            parcelMap.put(parcel[0], parcel[1]);
        }

        @SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your parcel tracking number: ");
        String trackingNumber = scanner.nextLine();

        String status = trackParcel(parcelMap, trackingNumber);
        System.out.println(status);
    }

    public static String trackParcel(HashMap<String, String> parcelMap, String trackingNumber) {
        return parcelMap.getOrDefault(trackingNumber, "Invalid tracking number. Please try again.");
    }
}0