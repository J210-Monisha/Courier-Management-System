package courierdb;
import java.text.SimpleDateFormat;
import java.util.Date;
public class Confirmationemail {
    
    public static String generateEmail(String customerName, String orderNumber, String deliveryAddress, Date deliveryDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
        String formattedDate = dateFormat.format(deliveryDate);
        
        return "Dear " + customerName + ",\n\n" +
               "Thank you for your order! Your order details are as follows:\n\n" +
               "Order Number: " + orderNumber + "\n" +
               "Delivery Address: " + deliveryAddress + "\n" +
               "Expected Delivery Date: " + formattedDate + "\n\n" +
               "We appreciate your business and hope you enjoy your purchase!\n\n" +
               "Best regards,\nCustomer Support Team";
    }
    
    public static void main(String[] args) {
        String customerName = "Monisha M";
        String orderNumber = "ORD123456";
        String deliveryAddress = "ABC Street, Bangalore, India";
        Date deliveryDate = new Date(System.currentTimeMillis() + (5 * 24 * 60 * 60 * 1000)); 
        String emailContent = generateEmail(customerName, orderNumber, deliveryAddress, deliveryDate);
        System.out.println(emailContent);
    }
}
