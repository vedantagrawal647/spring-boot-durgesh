package com.dugesh.durgesh11_Mapping.repo;

import com.dugesh.durgesh11_Mapping.entities.one_to_one.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepo extends JpaRepository<Degree,Integer> {
}
