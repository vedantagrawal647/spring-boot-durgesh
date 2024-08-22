package com.dugesh.durgesh11_Mapping.repo;

import com.dugesh.durgesh11_Mapping.entities.many_to_many.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {
}
