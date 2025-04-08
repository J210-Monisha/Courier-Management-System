package Serviceimpl;

import Services.ICourierAdminService;
import entities.CourierCompanyCollection;
import entities.Employee;

public class CourierAdminServiceCollectionImpl extends CourierUserServiceImpl implements ICourierAdminService {

    public CourierAdminServiceCollectionImpl(CourierCompanyCollection companyObj) {
        super(companyObj);
    }

    @Override
    public int addCourierStaff(Employee obj) {
        return companyObj.addCourier(obj);  
    }
}
