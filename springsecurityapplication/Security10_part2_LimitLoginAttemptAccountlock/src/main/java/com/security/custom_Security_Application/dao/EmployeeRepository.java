package com.security.custom_Security_Application.dao;

import com.security.custom_Security_Application.entity.Employee;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    public Employee findByEmail(String email);

    public  Employee findByVerificationCode(String code);

    @Query("update Employee e set e.failedAttempt=?1 where e.email=?2")
    @Modifying
    @Transactional
    public void updateFailedAttempt(int attempt,String email);
}
