package com.dugesh.durgesh11_Mapping.entities.many_to_many;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Product {

    @Id
    private int product_id;

    private String product_name;

    @ManyToMany(mappedBy = "products",fetch = FetchType.EAGER)
    private List<Category> categories = new ArrayList<>() ;

    public Product() {
    }

    public Product(int product_id, String product_name) {
        this.product_id = product_id;
        this.product_name = product_name;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
