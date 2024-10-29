package com.demo.staffing_api.service;

import com.demo.staffing_api.model.Address;
import com.demo.staffing_api.model.Employee;
import com.demo.staffing_api.repository.EmployeeRepository;
import lombok.NonNull;
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
      this.updateEmployeeDetails(updatedEmpData, employee);
      this.updateEmployeeAddress(updatedEmpData.getAddress(), employee.getAddress());
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

  private void updateEmployeeDetails(@NonNull Employee empData, @NonNull Employee employee) {
    empData.setFirstName(employee.getFirstName());
    empData.setLastName(employee.getLastName());
    empData.setAge(employee.getAge());
    empData.setGender(employee.getGender());
    empData.setSalary(employee.getSalary());
    empData.setManagerId(employee.getManagerId());
  }

  private void updateEmployeeAddress(@NonNull Address addrData, @NonNull Address address) {
    addrData.setStreet(address.getStreet());
    addrData.setCity(address.getCity());
    addrData.setState(address.getState());
    addrData.setCountry(address.getCountry());
    addrData.setZipCode(address.getZipCode());
  }
}
