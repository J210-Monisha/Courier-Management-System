package Order;
import java.util.Scanner;
class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the order status:(Processing,Delivered,Cancelled):");
        String status=scan.next();
        if(status.equals("Processing")){
            System.out.println("The order is still being processed and it will be delivered soon.");
        }
        else if(status.equals("Delivered")){
            System.out.println("The order is delivered sucessfully.");
        }
        else if(status.equals("Cancelled")){
            System.out.println("The order has been cancelled.");
        }
        else{
            System.out.println("Invalid");
        }
        scan.close();
    }
}
