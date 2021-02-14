package com.imyourbuddy.service;

import com.imyourbuddy.entity.Book;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    private final BookRepository repository;

    @Autowired
    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> findAllBooks() {
        return repository.findAll();
    }

    public Book findBookById(int id) throws ResourceNotFoundException {
        Optional<Book> optional = repository.findById(id);
        return optional
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + "not found"));
    }

    public Book deleteById(int id) throws ResourceNotFoundException {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + "not found"));
        repository.delete(book);
        return book;
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public void updateBookById(int id, String name, double price, String warehouse, int quantity) {
        repository.updateById(id, name, price, warehouse, quantity);
    }
}
