package com.testing.junit5.dao;

import com.testing.junit5.model.UserInfo;
import org.apache.catalina.User;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class HomeRepositoryTest {

    @Autowired
    private HomeRepository homeRepository;

    @Test
    void findById() {
        UserInfo u =new UserInfo(100,"annand","admin");
        homeRepository.save(u);
        UserInfo actualUser = homeRepository.findById(100);
        boolean actualResult = false;
        if(actualUser!=null)
        {
            actualResult=true;
        }
        assertThat(actualResult).isTrue();
    }

    @AfterEach
    void tearDown(){
        System.out.println("Tear down");
        homeRepository.deleteById(100);
    }

    @BeforeEach
    void settingUp(){
        System.out.println("Setting Up");
    }
}