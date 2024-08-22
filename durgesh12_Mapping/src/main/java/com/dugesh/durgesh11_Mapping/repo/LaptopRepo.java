package com.dugesh.durgesh11_Mapping.repo;

import com.dugesh.durgesh11_Mapping.entities.one_to_many_And_many_to_one.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaptopRepo extends JpaRepository<Laptop,Integer> {
}
