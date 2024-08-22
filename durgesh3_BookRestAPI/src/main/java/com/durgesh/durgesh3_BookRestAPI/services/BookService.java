package com.durgesh.durgesh3_BookRestAPI.services;

import com.durgesh.durgesh3_BookRestAPI.BookRepository;
import com.durgesh.durgesh3_BookRestAPI.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

//    private static List<Book> list = new ArrayList<>();

//     static {
//        list.add(new Book(12,"java Complete Reference","Divyanshi"));
//        list.add(new Book(13,"Python Complete Reference","Anshika"));
//        list.add(new Book(14,"C++ Complete Reference","Ayushi"));
//     }

     //get all books
    public  List<Book> getAllBooks() {
         List<Book> list = (List<Book>)this.bookRepository.findAll();
         return list;
    }

    //get single book by id
    public Book getBookById(int id)
    {
        Book book=null;
        try{
            //book = list.stream().filter(e -> e.getId()==id).findFirst().get();

           book =  this.bookRepository.findById(id);

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return book;
    }

    //adding the book
    public Book addBook(Book book)
    {
        // return list .add(book);

        Book result = bookRepository.save(book);
        return result;
    }

    //delete Book by id
    public void deleteBook(int bid){

      //  list = list.stream().filter(book -> book.getId() != bid).collect(Collectors.toList());

        bookRepository.deleteById(bid);
    }

    //update the book
    public void updateBook(Book book,int bid)
    {
      // list = list.stream().map(b -> {
      //       if(b.getId() == bid){
      //          b.setTitle(book.getTitle());
      //          b.setAuthor(book.getAuthor());
      //  }
      //      return b;
      //  }).collect(Collectors.toList());

        book.setId(bid);
        bookRepository.save (book);

    }




}
