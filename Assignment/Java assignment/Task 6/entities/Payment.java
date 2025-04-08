package entities;
import java.util.Date;
public class Payment {
    private int paymentID;
    private int courierID;
    private int locationID;
    private double amount;
    private Date paymentDate;

    public Payment() {}

    public Payment(int paymentID, int courierID, int locationID, double amount, Date paymentDate) {
        this.paymentID = paymentID;
        this.courierID = courierID;
        this.locationID = locationID;
        this.amount = amount;
        this.paymentDate = paymentDate;
    }

    public int getPaymentID() { return paymentID; }
    public void setPaymentID(int paymentID) { this.paymentID = paymentID; }

    public int getCourierID() { return courierID; }
    public void setCourierID(int courierID) { this.courierID = courierID; }

    public int getLocationID() { return locationID; }
    public void setLocationID(int locationID) { this.locationID = locationID; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getPaymentDate() { return paymentDate; }
    public void setPaymentDate(Date paymentDate) { this.paymentDate = paymentDate; }

    @Override
    public String toString() {
        return "Payment [paymentID=" + paymentID + ", courierID=" + courierID + ", locationID=" + locationID + 
               ", amount=" + amount + ", paymentDate=" + paymentDate + "]";
    }
    //for testing
    public static void main(String[] args) {
        Payment payment = new Payment(1, 1,1,250.75,new Date());
        System.out.println(payment);
    }
}