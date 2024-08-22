package com.dugesh.durgesh11_Mapping.controller;

import com.dugesh.durgesh11_Mapping.entities.many_to_many.Category;
import com.dugesh.durgesh11_Mapping.entities.many_to_many.Product;
import com.dugesh.durgesh11_Mapping.entities.one_to_many_And_many_to_one.Employee;
import com.dugesh.durgesh11_Mapping.entities.one_to_many_And_many_to_one.Laptop;
import com.dugesh.durgesh11_Mapping.entities.one_to_one.Degree;
import com.dugesh.durgesh11_Mapping.entities.one_to_one.Student;
import com.dugesh.durgesh11_Mapping.repo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private DegreeRepo degreeRepo;

    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private StudentRepo studentRepo;

    @Autowired
    private LaptopRepo laptopRepo;


    //one to one
    @GetMapping("/")
    public void m1(){

        Student student = new Student();
        student.setStudent_id(8);
        student.setFirstName("nidhi");
        student.setLastName("agrawal");

        Degree degree = new Degree();
        degree.setDegree_id(108);
        degree.setCourse("bachelor");
        degree.setStudent(student);

        student.setDegree(degree);

        studentRepo.save(student);

    }

    //one to many
    //many to one
    @GetMapping("/about")
    private void m2()
    {
        Employee employee = new Employee();
        employee.setEmp_id(1);
        employee.setEmp_name("vedant");
        employee.setDesignation("intern");

        Laptop laptop1 = new Laptop();
        laptop1.setLaptop_id(101);
        laptop1.setLaptopName("Lenovo");
        laptop1.setStorage("100gb");
        laptop1.setEmployee(employee);

        Laptop laptop2 = new Laptop();
        laptop2.setLaptop_id(102);
        laptop2.setLaptopName("HP");
        laptop2.setStorage("50gb");
        laptop2.setEmployee(employee);

        List<Laptop> laptops = List.of(laptop1,laptop2);

        employee.setLaptop(laptops);

        employeeRepo.save(employee);
    }


    //many to many
    @GetMapping("/map")
    public void mapProductsAndCategories() {
        Category category1 = new Category();
        category1.setCat_id(1);
        category1.setCat_name("Electronic");

        Category category2 = new Category();
        category2.setCat_id(2);
        category2.setCat_name("Telecom");

        Product product1 = new Product();
        product1.setProduct_id(101);
        product1.setProduct_name("Samsung TV");

        Product product2 = new Product();
        product2.setProduct_id(102);
        product2.setProduct_name("LG TV");

        Product product3 = new Product();
        product3.setProduct_id(103);
        product3.setProduct_name("Mi Mobile");

        Product product4 = new Product();
        product4.setProduct_id(104);
        product4.setProduct_name("Telephone");


        List<Product>  category1Products = category1.getProducts();
        category1Products.add(product1);
        category1Products.add(product2);
        category1Products.add(product3);

        List<Product>  category2Products = category1.getProducts();
        category1Products.add(product3);
        category1Products.add(product4);



        // Save entities
        categoryRepo.save(category1);
        categoryRepo.save(category2);
    }



}
