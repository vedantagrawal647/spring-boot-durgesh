package com.durgesh.durgesh3_BookRestAPI.controller;


import com.durgesh.durgesh3_BookRestAPI.entity.Book;
import com.durgesh.durgesh3_BookRestAPI.services.BookService;
import org.apache.catalina.filters.ExpiresFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
public class BookController {

    @Autowired
    private BookService bookService;

        @GetMapping("/books")
        public ResponseEntity<List<Book>> getBooks(){
            List<Book> list =  this.bookService.getAllBooks();
            if(list.size()<=0)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(list);
        }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBook(@PathVariable("id") int id){
        Book book = this.bookService.getBookById(id);
        if(book==null)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return  ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    //add book handler
    @PostMapping("/books")
    public ResponseEntity<Book> addBook(@RequestBody Book book){
       Book b = null;
       try{
           b=this.bookService.addBook(book);
           System.out.println(book);
           return ResponseEntity.status(HttpStatus.CREATED).body(b);
        }
       catch(Exception e)
       {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
       }
    }

    //delete book handler
    @DeleteMapping("/books/{bookId}")
    public ResponseEntity<Void> deleteBook(@PathVariable("bookId") int bookId){
        try{
            this.bookService.deleteBook(bookId);
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    //update book handler
    @PutMapping("/books/{bookId}")
    public ResponseEntity<Book> updateBook(@RequestBody Book book,@PathVariable("bookId") int bookId){
        try{
            this.bookService.updateBook(book,bookId);
            return  ResponseEntity.ok().body(book);
        }
        catch(Exception e)
        {
            e.printStackTrace();;
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }




}
