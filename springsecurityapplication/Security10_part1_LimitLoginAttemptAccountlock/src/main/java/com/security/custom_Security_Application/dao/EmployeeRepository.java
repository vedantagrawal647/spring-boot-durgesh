package com.security.custom_Security_Application.dao;

import com.security.custom_Security_Application.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public Employee findByEmail(String email);

    public  Employee findByVerificationCode(String code);
}
