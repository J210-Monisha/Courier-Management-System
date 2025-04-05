package entities;
public class CourierServices {
    private int serviceID;
    private String serviceName;
    private double cost;

    public CourierServices() {}

    public CourierServices(int serviceID, String serviceName, double cost) {
        this.serviceID = serviceID;
        this.serviceName = serviceName;
        this.cost = cost;
    }

    public int getServiceID() { return serviceID; }
    public void setServiceID(int serviceID) { this.serviceID = serviceID; }

    public String getServiceName() { return serviceName; }
    public void setServiceName(String serviceName) { this.serviceName = serviceName; }

    public double getCost() { return cost; }
    public void setCost(double cost) { this.cost = cost; }

    @Override
    public String toString() {
        return "CourierServices [serviceID=" + serviceID + ", serviceName=" + serviceName + ", cost=" + cost + "]";
    }

    //for testing
    public static void main(String[] args) {
        CourierServices cs = new CourierServices(1, "Express Delivery", 299.99);
        System.out.println(cs);
    }
}
