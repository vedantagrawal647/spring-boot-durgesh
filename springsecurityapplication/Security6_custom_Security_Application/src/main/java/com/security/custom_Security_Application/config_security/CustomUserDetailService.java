package com.security.custom_Security_Application.config_security;

import com.security.custom_Security_Application.dao.EmployeeRepository;
import com.security.custom_Security_Application.entity.Employee;
import com.security.custom_Security_Application.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    EmployeeRepository employeeRepository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Employee employee = employeeRepository.findByEmail(email);
        System.out.println(employee+"-------------->>>>>>>>>>>>");
        if(employee==null) {
            throw  new UsernameNotFoundException("User not found");
        }
        else {
            System.out.println(employee.getEmail());
            return  new CustomUser(employee);
        }
    }
}
