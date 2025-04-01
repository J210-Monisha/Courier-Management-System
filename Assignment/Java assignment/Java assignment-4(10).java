package courierdb;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidation {

    public static void main(String[] args) {
        System.out.println(validateCustomerData("Monisha M", "name"));
        System.out.println(validateCustomerData("ABC St", "address"));
        System.out.println(validateCustomerData("123 456 9865", "phone"));
    }

    public static String validateCustomerData(String data, String detail) {
        switch (detail.toLowerCase()) {
            case "name":
                if (isValidName(data)) {
                    return "Valid name.";
                } else {
                    return "Invalid name. Name should contain only letters and be properly capitalized.";
                }
            case "address":
                if (isValidAddress(data)) {
                    return "Valid address.";
                } else {
                    return "Invalid address. Address should not contain special characters.";
                }
            case "phone":
                if (isValidPhoneNumber(data)) {
                    return "Valid phone number.";
                } else {
                    return "Invalid phone number. Phone number should follow ##### ##### format.";
                }
            default:
                return "Invalid detail type. Please use 'name', 'address', or 'phone'.";
        }
    }

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile("^[A-Z][a-z]*((\\s[A-Z][a-z]*)*)$");
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    public static boolean isValidAddress(String address) {
        Pattern pattern = Pattern.compile("^[a-zA-Z0-9\\s,]*$");
        Matcher matcher = pattern.matcher(address);
        return matcher.matches();
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
    	Pattern pattern = Pattern.compile("^\\d{5}\\s\\d{5}$");
        Matcher matcher = pattern.matcher(phoneNumber);
        return matcher.matches();
    }
}

