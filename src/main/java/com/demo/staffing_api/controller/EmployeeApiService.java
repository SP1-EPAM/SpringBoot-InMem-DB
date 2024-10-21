package com.demo.staffing_api.controller;

import com.demo.staffing_api.model.Employee;
import com.demo.staffing_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeApiService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployee(){
        try {
            List<Employee> empList = new ArrayList<>(employeeRepository.findAll());

            if (empList.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(empList, HttpStatus.OK);
        } catch(Exception ex) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{empId}")
    public ResponseEntity<Employee> getEmployee(@PathVariable Long empId) {
        Optional<Employee> empObj = employeeRepository.findById(empId);
        return empObj.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee) {
        try {
            Employee empObj = employeeRepository.save(employee);
            return new ResponseEntity<>(empObj, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/update/{empId}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long empId, @RequestBody Employee employee) {
        try {
            Optional<Employee> empData = employeeRepository.findById(empId);
            if (empData.isPresent()) {
                Employee updatedEmpData = empData.get();
                updatedEmpData.setFirstName(employee.getFirstName());
                updatedEmpData.setLastName(employee.getLastName());

                Employee empObj = employeeRepository.save(updatedEmpData);
                return new ResponseEntity<>(empObj, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{empId}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable Long empId) {
        try {
            employeeRepository.deleteById(empId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<HttpStatus> deleteAllEmployee() {
        try {
            employeeRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
