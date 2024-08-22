package com.security.custom_Security_Application.service;

import com.security.custom_Security_Application.dao.EmployeeRepository;
import com.security.custom_Security_Application.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public  void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }


    public String findByEmail(String email) {
        return employeeRepository.findByEmail(email).getEmail();
    }
}
