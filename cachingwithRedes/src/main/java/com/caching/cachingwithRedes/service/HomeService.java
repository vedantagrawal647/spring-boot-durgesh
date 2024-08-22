package com.caching.cachingwithRedes.service;

import com.caching.cachingwithRedes.entity.Student;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HomeService {


    Student s= new Student("Vedant",2);
    List<Student> l=new ArrayList<>();

    HomeService()
    {
        l.add(s);
    }


    //without cache
    public List<Student> getStudent() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println(" return all student data");
        return l;
    }

    // with cache
    @Cacheable("StudentInfo")
    public List<Student> getStudentData() throws InterruptedException {

        Thread.sleep(3000);
        System.out.println(" return all student data");
        return l;
    }

    @CacheEvict(value = "StudentInfo",allEntries = true)
    public Student addStudent( Student s)
    {
        l.add(s);
        return s;
    }

}
