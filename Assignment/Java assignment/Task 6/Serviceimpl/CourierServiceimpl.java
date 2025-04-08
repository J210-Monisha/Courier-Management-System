package Serviceimpl;

import Services.ICourierUserService;
import Services.ICourierAdminService;
import entities.*;

import java.util.*;

public class CourierServiceImpl implements ICourierUserService, ICourierAdminService {
    private List<Courier> courierList = new ArrayList<>();
    private List<Employee> employeeList = new ArrayList<>();


    @Override
    public String getOrderStatus(String trackingNumber) {
        for (Courier c : courierList) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                return c.getStatus();
            }
        }
        return "Order not found";
    }

    @Override
    public boolean cancelOrder(String trackingNumber) {
        for (Courier c : courierList) {
            if (c.getTrackingNumber().equals(trackingNumber)) {
                c.setStatus("Cancelled");
                return true;
            }
        }
        return false;
    }

    @Override
    public List<Courier> getAssignedOrder(int courierStaffId) {
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