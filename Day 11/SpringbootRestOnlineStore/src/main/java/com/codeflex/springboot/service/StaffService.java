package com.codeflex.springboot.service;

import java.util.List;


import com.codeflex.springboot.model.Staff;

public interface StaffService {

    Staff findById(long id);

    Staff findByName(String name);

    void saveStaff(Staff product);

    void updateStaff(Staff product);

    void deleteStaffById(long id);

    List<Staff> findAllStaff();

    void deleteAllStaff();

    boolean isStaffExist(Staff product);

}

