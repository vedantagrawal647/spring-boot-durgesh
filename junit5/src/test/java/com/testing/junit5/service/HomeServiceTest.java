package com.testing.junit5.service;

import com.testing.junit5.dao.HomeRepository;
import org.apache.catalina.User;
import org.aspectj.lang.annotation.After;
import org.checkerframework.checker.units.qual.A;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class )
class HomeServiceTest {

    @Mock
    private HomeRepository homeRepository;



    private HomeService homeService;

    @BeforeEach
    void setUp(){
        this.homeService = new HomeService(this.homeRepository);
    }



    @Test
    void getAllUser() {
        homeService.getAllUser();
        verify(homeRepository).findAll();
    }
}