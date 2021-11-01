package com.employee.employee.service;

import com.employee.employee.model.Employee;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface EmployeeServiceInterface {

    public List<Employee> getEmployees();
    public void postEmployee(@RequestBody Employee employee);
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id);
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id);
    public void updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails);

}
