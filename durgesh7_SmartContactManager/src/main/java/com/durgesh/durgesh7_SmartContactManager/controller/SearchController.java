package com.durgesh.durgesh7_SmartContactManager.controller;



import com.durgesh.durgesh7_SmartContactManager.dao.ContactRepository;
import com.durgesh.durgesh7_SmartContactManager.dao.UserDataRepository;
import com.durgesh.durgesh7_SmartContactManager.entities.Contact;
import com.durgesh.durgesh7_SmartContactManager.entities.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.List;

@RestController
public class SearchController {

    @Autowired
    private UserDataRepository userDataRepository;

    @Autowired
    private ContactRepository contactRepository;


    // search handler
    @GetMapping("/search/{query}")
    public ResponseEntity<?> search(@PathVariable("query") String query,
                                    Principal principal)
    {

        UserData userData = this.userDataRepository.getUserDataByUserDataName(principal.getName());

        List<Contact> contacts = this.contactRepository.findByNameContainingAndUserData(query,userData);

        return ResponseEntity.ok(contacts);

    }


}
