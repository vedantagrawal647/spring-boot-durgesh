package com.security.custom_Security_Application.config_security;

import com.security.custom_Security_Application.entity.Employee;
import com.security.custom_Security_Application.service.EmployeeService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        CustomUser customUser = (CustomUser)authentication.getPrincipal();

        Employee employee = customUser.getEmployee();

        if(employee!=null)
        {
            employeeService.resetAttempt(employee.getEmail());
        }

        if(roles.contains("ROLE_ADMIN"))
        {
            response.sendRedirect("/admin");
        }
        else{
            response.sendRedirect("/user");
        }
    }
}
