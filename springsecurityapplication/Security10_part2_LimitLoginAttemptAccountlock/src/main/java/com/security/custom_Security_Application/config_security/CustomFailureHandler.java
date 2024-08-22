package com.security.custom_Security_Application.config_security;

import com.security.custom_Security_Application.dao.EmployeeRepository;
import com.security.custom_Security_Application.entity.Employee;
import com.security.custom_Security_Application.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public  void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse respone, AuthenticationException exception) throws IOException , ServletException{

        String email = request.getParameter("username");
        Employee employee = employeeRepository.findByEmail(email);

        if(employee!=null)
        {
            if(employee.isEnable()){

                if(employee.isAccountNonLocked()){
                    if (employee.getFailedAttempt()<employeeService.ATTEMPT_tIME - 1){
                       employeeService.increaseFailedAttempt(employee);
                    }
                    else {
                        employeeService.lock(employee);
                        exception = new LockedException("Your account is Locked !! failed attempt 3 time");
                    }
                }
                else if(!employee.isAccountNonLocked())
                {
                    if(employeeService.unlockAccountTimeExpired(employee))
                    {
                        exception = new LockedException("Account is ulocked! please try after to login");
                    }
                    else {
                        exception = new LockedException("Account is Locked,please try after some time");
                    }
                }
            }
            else {
                exception = new LockedException("Account is inactive .. need to   verify account");
            }
        }

        super.setDefaultFailureUrl("/signin?error");
        super.onAuthenticationFailure(request,respone,exception);
    }

}
