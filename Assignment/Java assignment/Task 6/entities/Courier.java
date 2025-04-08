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

    // Static tracking number counter
    private static int trackingCounter = 1348;

    public Courier() {
        this.trackingNumber = "TRK" + trackingCounter++;
    }

    // Parametrized constructor - tracking number auto-generated
    public Courier(int courierID, String senderName, String senderAddress, String receiverName,
                   String receiverAddress, double weight, String status,
                   Date dispatchDate, Date deliveryDate) {
        this.courierID = courierID;
        this.senderName = senderName;
        this.senderAddress = senderAddress;
        this.receiverName = receiverName;
        this.receiverAddress = receiverAddress;
        this.weight = weight;
        this.status = status;
        this.trackingNumber = "TRK" + trackingCounter++;
        this.dispatchDate = dispatchDate;
        this.deliveryDate = deliveryDate;
       
    }
    public int getCourierID() {
        return courierID;
    }

    public void setCourierID(int courierID) {
        this.courierID = courierID;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName;
    }

    public String getSenderAddress() {
        return senderAddress;
    }

    public void setSenderAddress(String senderAddress) {
        this.senderAddress = senderAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public Date getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(Date dispatchDate) {
        this.dispatchDate = dispatchDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    @Override
    public String toString() {
        return "Courier [courierID=" + courierID + ", senderName=" + senderName + ", senderAddress=" + senderAddress +
                ", receiverName=" + receiverName + ", receiverAddress=" + receiverAddress + ", weight=" + weight +
                ", status=" + status + ", trackingNumber=" + trackingNumber + ", dispatchDate=" + dispatchDate +
                ", deliveryDate=" + deliveryDate + "]";
    }

    // Testing
    public static void main(String[] args) {
        Courier courier1 = new Courier(1, "Akalya", "abc street, Bangalore", "Jack", "East Road, Bangalore",
                20.0, "In Transit", new Date(), new Date());

        Courier courier2 = new Courier(3, "Madhu", "123 MG Road", "John", "XYZ St, Coimbatore",
                3.0, "In Transit", new Date(), new Date());

        System.out.println(courier1);
        System.out.println(courier2);
    }
}
