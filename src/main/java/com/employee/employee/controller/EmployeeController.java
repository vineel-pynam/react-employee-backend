package com.employee.employee.controller;

import com.employee.employee.exception.ResourceNotFoundException;
import com.employee.employee.model.Employee;
import com.employee.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin("*")
public class EmployeeController {

    private EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @GetMapping(path = "/employees")
    public List<Employee> getHello(){
        return employeeRepository.findAll();
    }

    @PostMapping(path = "/add/employee")
    public void postEmployee(@RequestBody Employee employee){
        employeeRepository.save(employee);
    }

    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with " + id + " doesn't exist"));

        return ResponseEntity.ok(employee);
    }

    @DeleteMapping(path = "/delete/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){
        boolean isPresent = employeeRepository.existsById(id);
        if( !isPresent ) {
            throw new ResourceNotFoundException("Student with " + id + " doesn't exist");
        }
        employeeRepository.deleteById(id);
        return ResponseEntity.ok("Deleted!");
    }

    @PutMapping(path = "/update/employees/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student with " + id + " doesn't exist"));

        employee.setFirstName(employeeDetails.getFirstName());
        employee.setLastName(employeeDetails.getLastName());
        employee.setEmail(employeeDetails.getEmail());

        employeeRepository.save(employee);
    }

}
