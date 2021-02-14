package com.imyourbuddy.controller;

import com.imyourbuddy.entity.Book;
import com.imyourbuddy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rest")
public class BookController {
    private final BookService service;

    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        return service.findAllBooks();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable(value = "id") int id) {
        Book book = service.findBookById(id);
        return ResponseEntity.ok().body(book);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBookById(@PathVariable(value = "id") int id) {
        service.deleteById(id);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book) {
        return service.save(book);
    }

}
