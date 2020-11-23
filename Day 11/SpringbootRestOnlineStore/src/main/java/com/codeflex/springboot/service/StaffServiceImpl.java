package com.codeflex.springboot.service;

import com.codeflex.springboot.model.Staff;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service("staffService")
public class StaffServiceImpl implements StaffService {


    //  Using two hashmaps in order to provide performance of O(1) while fetching products
    private static HashMap<Long, Staff> staffs = new HashMap<>();
    private static HashMap<String, Long> idNameHashMap = new HashMap<>();


    public List<Staff> findAllStaff() {
        // Pagination should be added...
        return new ArrayList<>(staffs.values());
    }

    public Staff findById(long id) {
        return staffs.get(id);
    }

    public Staff findByName(String name) {

        if (idNameHashMap.get(name) != null){
            return staffs.get(idNameHashMap.get(name));
        }

        return null;
    }

    public void saveStaff(Staff product) {
        synchronized (this) {    //  Critical section synchronized
            staffs.put(product.getidKaryawan(), product);
            idNameHashMap.put(product.getName(), product.getidKaryawan());
        }
    }

    public void updateStaff(Staff staff) {
        synchronized (this) {    //  Critical section synchronized
            staffs.put(staff.getidKaryawan(), staff);
            idNameHashMap.put(staff.getName(), staff.getidKaryawan());
        }
    }

    public void deleteStaffById(long id) {
        synchronized (this) {    //  Critical section synchronized
            idNameHashMap.remove(staffs.get(id).getName());
            staffs.remove(id);
        }
    }

    public boolean isStaffExist(Staff staff) {
        return findByName(staff.getName()) != null;
    }

    public void deleteAllStaff() {
        staffs.clear();
    }

}
