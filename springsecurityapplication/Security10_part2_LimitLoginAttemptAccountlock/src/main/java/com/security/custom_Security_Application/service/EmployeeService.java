package com.security.custom_Security_Application.service;

import com.security.custom_Security_Application.dao.EmployeeRepository;
import com.security.custom_Security_Application.entity.Employee;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private JavaMailSender mailSender;

    public  void addEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Value("$(spring.mail.username)")
    private String fromMail;


    public String findByEmail(String email) {
        return employeeRepository.findByEmail(email).getEmail();
    }

    public boolean sendMail(String tomail,  String url,Employee employee) {

        String content = "Dear [[name]] , <br>" + "Please click the link below to verify youself" + "<h3><a href = \"[[URL]]\" target=\"_self\"> VERIFY </a></h3>" + "Thank You";

        String subject = "Account Verification";

        try{
            SimpleMailMessage message=new SimpleMailMessage();
            message.setFrom(fromMail);
            message.setTo(tomail);
            message.setSubject(subject);

            content = content.replace("[[name]]",employee.getUserName());

            String siteUrl = url + "/verify?code="+employee.getVerificationCode();
            content = content.replace("[[URL]]",siteUrl);
            message.setText(content);


            mailSender.send(message);
            System.out.println(true);
            return true;
        }
        catch(Exception e)
        {
            System.out.println(false);
            e.printStackTrace();
            System.out.println("Error in sending otp");
            return false;
        }


    }

   // private static final long lock_duration_time=24*60*60*1000;
   private static final long lock_duration_time=30 ;

    public static final long ATTEMPT_tIME = 3;

    public  boolean verificationAccount(String verificationCode)
    {
        Employee employee = employeeRepository.findByVerificationCode(verificationCode);
        if(employee == null)
        {
            return false;
        }
        else {
            employee.setEnable(true);
            employee.setVerificationCode(null);
            employeeRepository.save(employee);
            return true;
        }

    }

    public void increaseFailedAttempt(Employee employee)
    {
        int attempt = employee.getFailedAttempt()+1;
        employeeRepository.updateFailedAttempt(attempt,employee.getEmail());

    }

    public void resetAttempt(String email) {
        employeeRepository.updateFailedAttempt(0,email);
    }



    public void lock(Employee employee){
        employee.setAccountNonLocked(false);
        employee.setLockTime(new Date());
        employeeRepository.save(employee);
    }

    public  boolean unlockAccountTimeExpired(Employee employee){
        long lockTimeInMills = employee.getLockTime().getTime();
        long currentTimeMillis = System.currentTimeMillis();

        if(lockTimeInMills + lock_duration_time < currentTimeMillis){
            employee.setAccountNonLocked(true);
            employee.setLockTime(null);
            employee.setFailedAttempt(0);
            employeeRepository.save(employee);
            return true;
        }

        return false;
    }
}
