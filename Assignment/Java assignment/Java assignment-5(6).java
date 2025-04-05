package entities;
import java.util.Date;
public class Courier {
    private int courierID;
    private String senderName;
    private String senderAddress;
    private String receiverName;
    private String receiverAddress;
    private double weight;
    private String status;
    private String trackingNumber;
    private Date dispatchDate;
    private Date deliveryDate;

    public Courier() {}

    public Courier(int courierID, String senderName, String senderAddress, String receiverName, 
                   String receiverAddress, double weight, String status, String trackingNumber, 
                   Date dispatchDate, Date deliveryDate) {
        this.courierID = courierID;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.weight = weight;
        this.status = status;
        this.trackingNumber = trackingNumber;
        this.dispatchDate = dispatchDate;
        this.deliveryDate = deliveryDate;
    }

    @Override
    public String toString() {
        return "Courier [courierID=" + courierID + ", senderName=" + senderName + ", senderAddress=" + senderAddress + 
               ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", weight=" + weight + 
               ", status=" + status + ", trackingNumber=" + trackingNumber + ", dispatchDate=" + dispatchDate + 
               ", deliveryDate=" + deliveryDate + "]";
    }
    //for testing
    public static void main(String[] args) {
        Courier courier = new Courier(1,"Akalya","abc street, Bangalore","Priya","xyz Road, Pune",2.5,"In Transit","TRK123456",new Date(), new Date());
        System.out.println(courier);
    }