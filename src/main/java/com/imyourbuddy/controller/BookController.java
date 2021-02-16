package com.imyourbuddy.controller;

import com.imyourbuddy.entity.Book;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Book getBookById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
        return service.findBookById(id);
    }

    @DeleteMapping("/books/{id}")
    public Book deleteBookById(@PathVariable(value = "id") int id) throws ResourceNotFoundException {
       return service.deleteById(id);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book) {
        return service.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBookById(@PathVariable(value = "id") int id, @RequestBody Book book) throws ResourceNotFoundException {
        return service.updateBookById(id, book);
    }

    @PatchMapping("/books/{id}")
    public Book partUpdateBookById(@PathVariable(value = "id") int id, @RequestBody Book book) throws ResourceNotFoundException {
        return service.partUpdateBookById(id, book);
    }

    @GetMapping("/books/dist")
    public List<String> getAllDistinctBooks() {
        return service.getAllDistinctBooks();
    }

    @GetMapping("/books/special")
    public List<String> getSpecialBooks() {
        return service.getSpecialBooks();
    }
}
