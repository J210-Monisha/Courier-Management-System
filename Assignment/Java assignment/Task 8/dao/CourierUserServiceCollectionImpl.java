package dao;
import entities.*;
import Services.ICourierUserService;
import exceptions.*;

import java.util.List;

public class CourierUserServiceCollectionImpl implements ICourierUserService {

    private CourierCompanyCollection companyObj;

    public CourierUserServiceCollectionImpl() {
        this.companyObj = new CourierCompanyCollection();
    }

    @Override
    public String placeOrder(Courier courierObj) {
        int length = companyObj.getCourierList().length;
        return courierObj.getTrackingNumber();
    }

    @Override
    public String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException {
        for (Courier c : companyObj.getCourierList()) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                return c.getStatus();
            }
        }
        throw new TrackingNumberNotFoundException("Tracking number not found: " + trackingNumber);
    }

    @Override
    public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException {
        for (Courier c : companyObj.getCourierList()) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                c.setStatus("Cancelled");
                return true;
            }
        }
        throw new TrackingNumberNotFoundException("Cannot cancel. Tracking number not found: " + trackingNumber);
    }

    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException {
        boolean valid = false;
        for (Employee e : companyObj.getEmployeeList()) {
            if (e.getEmployeeID() == courierStaffId) {
                valid = true;
                break;
            }
        }
        if (!valid) {
            throw new InvalidEmployeeIdException("Employee ID " + courierStaffId + " does not exist.");
        }

        List<Courier> assigned = new java.util.ArrayList<>();
        for (Courier c : companyObj.getCourierList()) {
            if (c.getCourierID() == courierStaffId) {
                assigned.add(c);
            }
        }
        return assigned;
    }
}