package com.durgesh.durgesh7_SmartContactManager.config;

import com.durgesh.durgesh7_SmartContactManager.dao.UserDataRepository;
import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDataRepository userDataRepository;




    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        //fetching user from DataBase

        UserData userData = userDataRepository.getUserDataByUserDataName(username);
        System.out.println(username);
        if(userData == null)
        {
            System.out.println("error");
            throw new UsernameNotFoundException("Could not found user !!");
        }

        CustomUserDetails customUserDetails  = new CustomUserDetails(userData);
        System.out.println(customUserDetails);

        return customUserDetails;
    }



}
