package com.codeflex.springboot.controller;

import com.codeflex.springboot.model.Staff;
import com.codeflex.springboot.service.StaffService;
import com.codeflex.springboot.util.CustomErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RestApiController {

    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);

    @Autowired
    StaffService staffService; //Service which will do all data retrieval/manipulation work

    // -------------------Retrieve All Products--------------------------------------------

    @RequestMapping(value = "/staff/", method = RequestMethod.GET)
    public ResponseEntity<List<Staff>> listAllStaffs() {
        List<Staff> staffs = staffService.findAllStaff();
        if (staffs.isEmpty()) {
            return new ResponseEntity<>(staffs, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staffs, HttpStatus.OK);
    }

    // -------------------Retrieve Single Product------------------------------------------

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getStaff(@PathVariable("id") long id) {
        logger.info("Fetching Product with id {}", id);
        Staff staff = staffService.findById(id);
        if (staff == null) {
            logger.error("Staff with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Product with id " + id  + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(staff, HttpStatus.OK);
    }

    // -------------------Create a Product-------------------------------------------

    @RequestMapping(value = "/staff/", method = RequestMethod.POST)
    public ResponseEntity<?> createStaff(@RequestBody Staff staff) {
        logger.info("Creating Product : {}", staff);

        if (staffService.isStaffExist(staff)) {
            logger.error("Unable to create. A Product with name {} already exist", staff.getName());
            return new ResponseEntity<>(new CustomErrorType("Unable to create. A Product with name " +
                    staff.getName() + " already exist."), HttpStatus.CONFLICT);
        }
        staffService.saveStaff(staff);

        return new ResponseEntity<>(staff, HttpStatus.CREATED);
    }

    // ------------------- Update a Product ------------------------------------------------

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateProduct(@PathVariable("id") long id, @RequestBody Staff staff) {
        logger.info("Updating Staff with id {}", id);

        Staff currentStaff = staffService.findById(id);

        if (currentStaff == null) {
            logger.error("Unable to update. Product with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to upate. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }

        currentStaff.setName(staff.getName());

        staffService.updateStaff(currentStaff);
        return new ResponseEntity<>(currentStaff, HttpStatus.OK);
    }

    // ------------------- Delete a Product-----------------------------------------

    @RequestMapping(value = "/staff/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteProduct(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting Product with id {}", id);

        Staff staff = staffService.findById(id);
        if (staff == null) {
            logger.error("Unable to delete. Product with id {} not found.", id);
            return new ResponseEntity<>(new CustomErrorType("Unable to delete. Product with id " + id + " not found."),
                    HttpStatus.NOT_FOUND);
        }
        staffService.deleteStaffById(id);
        return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete All Products-----------------------------

    @RequestMapping(value = "/staff/", method = RequestMethod.DELETE)
    public ResponseEntity<Staff> deleteAllProducts() {
        logger.info("Deleting All Products");

        staffService.deleteAllStaff();
        return new ResponseEntity<Staff>(HttpStatus.NO_CONTENT);
    }

}