package com.imyourbuddy.service;

import com.imyourbuddy.entity.Book;
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

    public Book findBookById(int id) {
        Optional<Book> optional = repository.findById(id);
        return optional.orElse(new Book());
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    public Book save(Book book) {
        return repository.save(book);
    }

}
