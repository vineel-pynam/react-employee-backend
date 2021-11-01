package com.employee.employee.service;

import com.employee.employee.exception.ResourceNotFoundException;
import com.employee.employee.model.Employee;
import com.employee.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService implements EmployeeServiceInterface {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }

    @Override
    public void postEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    private Employee findEmployeeById(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with " + id + " doesn't exist"));
    }

    @Override
    public ResponseEntity<Employee> getEmployeeById(Long id) {
        Employee employee = findEmployeeById(id);

        return ResponseEntity.ok(employee);
    }

    @Override
    public ResponseEntity<String> deleteEmployeeById(Long id) {
        boolean isPresent = employeeRepository.existsById(id);
        if( !isPresent ) {
            throw new ResourceNotFoundException("Student with " + id + " doesn't exist");
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Deleted!");
    }

    @Override
    public void updateEmployee(Long id, Employee employeeDetails) {
        Employee employee = findEmployeeById(id);

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());

        employeeRepository.save(employee);
    }
}
