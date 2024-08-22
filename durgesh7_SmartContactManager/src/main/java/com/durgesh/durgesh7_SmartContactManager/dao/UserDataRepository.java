package com.durgesh.durgesh7_SmartContactManager.dao;

import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDataRepository extends JpaRepository<UserData,Integer> {

    @Query("select u from UserData u where u.email =:email")
    public  UserData getUserDataByUserDataName(@Param("email") String email);
}
