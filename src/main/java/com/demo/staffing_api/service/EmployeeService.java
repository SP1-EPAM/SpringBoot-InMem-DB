package com.demo.staffing_api.service;

import com.demo.staffing_api.model.Employee;
import com.demo.staffing_api.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    public Optional<Employee> getEmployee(Long empId) {
        return employeeRepository.findById(empId);
    }

    public Employee addEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    public Employee updateEmployee(Long empId, Employee employee) {
        Optional<Employee> empData = employeeRepository.findById(empId);
        if (empData.isPresent()) {
            Employee updatedEmpData = empData.get();
            updatedEmpData.setFirstName(employee.getFirstName());
            updatedEmpData.setLastName(employee.getLastName());
            updatedEmpData.setAge(employee.getAge());
            updatedEmpData.setGender(employee.getGender());
            updatedEmpData.setSalary(employee.getSalary());
            updatedEmpData.setManagerId(employee.getManagerId());
            return employeeRepository.save(updatedEmpData);
        }
        return null;
    }

    public void deleteEmployee(Long empId) {
        employeeRepository.deleteById(empId);
    }

    public void deleteAllEmployee() {
        employeeRepository.deleteAll();
    }
}
