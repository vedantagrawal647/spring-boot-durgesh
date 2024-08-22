package com.testing.junit5.dao;

import com.testing.junit5.model.UserInfo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface HomeRepository extends JpaRepository<UserInfo,Integer> {


    public  UserInfo findById(int id);

}
