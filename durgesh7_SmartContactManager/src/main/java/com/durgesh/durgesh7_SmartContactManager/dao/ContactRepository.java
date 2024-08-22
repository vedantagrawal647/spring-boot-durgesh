package com.durgesh.durgesh7_SmartContactManager.dao;

import com.durgesh.durgesh7_SmartContactManager.entities.Contact;
import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ContactRepository extends JpaRepository<Contact,Integer> {
    //pagination

    @Query("from Contact as c where c.userData.id =:userDataId")
    //Pageable ka pass 2 information hoi
    //1. currentPage - page
    //2. contact Per page  - 5
    public Page<Contact> findContactByUserData(@Param("userDataId")int userDataId, Pageable pageable);

    //search
    public List<Contact> findByNameContainingAndUserData(String keywords, UserData userData);

}
