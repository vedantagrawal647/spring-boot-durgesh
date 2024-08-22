package com.dugesh.durgesh11_Mapping.entities.one_to_many_And_many_to_one;

import com.dugesh.durgesh11_Mapping.repo.LaptopRepo;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Employee {

    @Id
    private int emp_id;

    private String emp_name;

    private String designation;

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL)
    private List<Laptop> laptop;


    public Employee() {
    }

    public Employee(int emp_id, String emp_name, String designation) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.designation = designation;
    }

    public int getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(int emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }


    public List<Laptop> getLaptop() {
        return laptop;
    }

    public void setLaptop(List<Laptop> laptop) {
        this.laptop = laptop;
    }
}
