package courierdb;
import java.util.regex.*;

public class AddressFormatter {

    public static void main(String[] args) {
        String formattedAddress = formatAddress("123 main street", "chennai", "India", "600028");
        System.out.println(formattedAddress);
        
        String formattedAddress2 = formatAddress("abc street", "Bangalore", "India", "5600 089");
        System.out.println(formattedAddress2);
    }

    public static String formatAddress(String street, String city, String state, String zip) {
        if (!isValidZip(zip)) {
            return "Invalid ZIP/PIN code format.";
        }

        String formattedStreet = capitalizeWords(street);
        String formattedCity = capitalizeWords(city);
        String formattedState = capitalizeWords(state);
        
        return formattedStreet + ", " + formattedCity + ", " + formattedState + " " + zip;
    }

    public static String capitalizeWords(String str) {
        String[] words = str.toLowerCase().split(" ");
        StringBuilder capitalized = new StringBuilder();
        
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalized.append(Character.toUpperCase(word.charAt(0)))
                           .append(word.substring(1))
                           .append(" ");
            }
        }
        return capitalized.toString().trim();
    }

    public static boolean isValidZip(String zip) {
        String zipPattern = "^(\\d{5}(-\\d{4})?|\\d{6})$";  
        return Pattern.matches(zipPattern, zip);
    }
}
