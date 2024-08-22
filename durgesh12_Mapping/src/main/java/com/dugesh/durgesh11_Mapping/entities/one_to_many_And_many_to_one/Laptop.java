package com.dugesh.durgesh11_Mapping.entities.one_to_many_And_many_to_one;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Laptop {

    @Id
    private int laptop_id;

    private String laptopName;

    private String storage;

    @ManyToOne
    private Employee employee;

    public Laptop() {
    }

    public Laptop(int laptop_id, String laptopName, String storage) {
        this.laptop_id = laptop_id;
        this.laptopName = laptopName;
        this.storage = storage;
    }

    public int getLaptop_id() {
        return laptop_id;
    }

    public void setLaptop_id(int laptop_id) {
        this.laptop_id = laptop_id;
    }

    public String getLaptopName() {
        return laptopName;
    }

    public void setLaptopName(String laptopName) {
        this.laptopName = laptopName;
    }

    public String getStorage() {
        return storage;
    }

    public void setStorage(String storage) {
        this.storage = storage;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
