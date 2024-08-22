package com.testing.junit5.service;

import com.sun.tools.jconsole.JConsoleContext;
import com.testing.junit5.dao.HomeRepository;
import com.testing.junit5.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HomeService {



    @Autowired
    private HomeRepository homeRepository;


    public HomeService(HomeRepository homeRepository) {
        this.homeRepository = homeRepository;
    }

    public String userInfo()
    {
        return "Hello ji";
    }

    public UserInfo addUser(UserInfo userInfo) {

        return homeRepository.save(userInfo);
    }

    public List<UserInfo> getAllUser() {
        return homeRepository.findAll();
    }

    public UserInfo findUserInfoById(int id)
    {
        return homeRepository.findById(id);

    }

}
