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

    public Book updateBookById(int id, Book updatedBook) throws ResourceNotFoundException {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + "not found"));
        book.setName(updatedBook.getName());
        book.setPrice(updatedBook.getPrice());
        book.setWarehouse(updatedBook.getWarehouse());
        book.setQuantity(updatedBook.getQuantity());
        return repository.save(book);
    }

    public Book partUpdateBookById(int id, Book updatedBook) throws ResourceNotFoundException {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + "not found"));
        if (updatedBook.getName() != null) {
            book.setName(updatedBook.getName());
        }
        if (updatedBook.getPrice() != null) {
            book.setPrice(updatedBook.getPrice());
        }
        if (updatedBook.getWarehouse() != null) {
            book.setWarehouse(updatedBook.getWarehouse());
        }
        if (updatedBook.getQuantity() != null) {
            book.setQuantity(updatedBook.getQuantity());
        }
        return repository.save(book);
    }

    public List<String> getAllDistinctBooks() {
        return repository.findAllDistinctBooks();
    }

    public List<String> getSpecialBooks() {
        return repository.findSpecialBooks();
    }
}
