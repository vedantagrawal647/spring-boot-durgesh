package com.dugesh.durgesh11_Mapping.entities.many_to_many;

import com.dugesh.durgesh11_Mapping.entities.one_to_many_And_many_to_one.Laptop;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Category {

    @Id
    private int cat_id;

    private String cat_name;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<Product> products =new ArrayList<>();

    public Category() {
    }

    public Category(int cat_id, String cat_name) {
        this.cat_id = cat_id;
        this.cat_name = cat_name;
    }

    public int getCat_id() {
        return cat_id;
    }

    public void setCat_id(int cat_id) {
        this.cat_id = cat_id;
    }

    public String getCat_name() {
        return cat_name;
    }

    public void setCat_name(String cat_name) {
        this.cat_name = cat_name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
