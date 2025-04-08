package Serviceimpl;

import Services.ICourierUserService;
import Services.ICourierAdminService;
import entities.*;
import exceptions.InvalidEmployeeIdException;
import exceptions.TrackingNumberNotFoundException;

import java.util.*;

public class CourierServiceImpl implements ICourierUserService, ICourierAdminService {
    private List<Courier> courierList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();


    @Override
    public String getOrderStatus(String trackingNumber) throws TrackingNumberNotFoundException {
        for (Courier c : courierList) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                return c.getStatus();
            }
        }
        throw new TrackingNumberNotFoundException("Tracking number " + trackingNumber + " not found.");
    }
  
    @Override
    public boolean cancelOrder(String trackingNumber) throws TrackingNumberNotFoundException {
        for (Courier c : courierList) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                c.setStatus("Cancelled");
                return true;
            }
        }
        throw new TrackingNumberNotFoundException("Tracking number " + trackingNumber + " not found.");
    }


    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) throws InvalidEmployeeIdException {
        boolean exists = false;
        for (Employee e : employeeList) {
            if (e.getEmployeeID() == courierStaffId) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            throw new InvalidEmployeeIdException("Employee ID " + courierStaffId + " does not exist.");
        }

        List<Courier> assigned = new ArrayList<>();
        for (Courier c : courierList) {
            if (c.getCourierID() == courierStaffId) {
                assigned.add(c);
            }
        }
        return assigned;
    }


    @Override
    public int addCourierStaff(Employee obj) {
        employeeList.add(obj);
        return obj.getEmployeeID();
    }
    @Override
    public String placeOrder(Courier c) {
        courierList.add(c);
        return c.getTrackingNumber();
    }

}
