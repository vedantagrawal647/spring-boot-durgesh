package com.testing.junit5.controller;

import com.testing.junit5.model.UserInfo;
import com.testing.junit5.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private HomeService homeService;

    @GetMapping("/")
    public String UserInfoReqHandler()
    {
        return homeService.userInfo();
    }

    @PostMapping("/addUser")
    public UserInfo addUser(@RequestBody UserInfo userInfo)
    {
        return homeService.addUser(userInfo);
    }

    @GetMapping("/findAllUser")
    public  List<UserInfo> getAllUser()
    {
        return homeService.getAllUser();
    }

    @GetMapping("/findById")
    public boolean findUserInfoById(@RequestBody UserInfo userInfo)
    {
        UserInfo u = homeService.findUserInfoById(userInfo.getId());
        if(u !=null){
            return true;
        }
        else{
            return  false;
        }

    }


}
