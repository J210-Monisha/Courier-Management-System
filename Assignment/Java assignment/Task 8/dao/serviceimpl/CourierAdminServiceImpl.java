package Serviceimpl;

import Services.ICourierAdminService;
import entities.CourierCompanyCollection;
import entities.Employee;

public class CourierAdminServiceImpl extends CourierUserServiceImpl implements ICourierAdminService {

    public CourierAdminServiceImpl(CourierCompanyCollection companyObj) {
        super(companyObj);
    }

    @Override
    public int addCourierStaff(Employee obj) {
        return companyObj.addEmployee(obj);
    }
}