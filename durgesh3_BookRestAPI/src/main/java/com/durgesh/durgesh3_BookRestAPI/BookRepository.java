package com.durgesh.durgesh3_BookRestAPI;

import com.durgesh.durgesh3_BookRestAPI.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book,Integer> {

    public  Book findById(int id);
}
