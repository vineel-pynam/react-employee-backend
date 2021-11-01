package com.employee.employee.controller;

import com.employee.employee.exception.ResourceNotFoundException;
import com.employee.employee.model.Employee;
import com.employee.employee.service.EmployeeService;
import com.employee.employee.service.EmployeeServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1")
@CrossOrigin("*")
public class EmployeeController {

    private EmployeeServiceInterface esi;

    @Autowired
    public EmployeeController(EmployeeService employeeService){
        esi = employeeService;
    }

    @GetMapping(path = "/employees")
    public List<Employee> getEmployees(){
        return esi.getEmployees();
    }

    @PostMapping(path = "/add/employee")
    public void postEmployee(@RequestBody Employee employee){
        esi.postEmployee(employee);
    }

    @GetMapping(path = "/employees/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id){
       return esi.getEmployeeById(id);
    }

    @DeleteMapping(path = "/delete/employees/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id){
        return esi.deleteEmployeeById(id);
    }

    @PutMapping(path = "/update/employees/{id}")
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails){
        esi.updateEmployee(id, employeeDetails);
    }

}
