package com.imyourbuddy.service;

import com.imyourbuddy.entity.Book;
import com.imyourbuddy.entity.search.BookSearchResult;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

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
         return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + " not found"));
    }

    public Book deleteById(int id) throws ResourceNotFoundException {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + " not found"));
        repository.deleteBookById(id);
        return book;
    }

    public Book save(Book book) {
        return repository.save(book);
    }

    public Book updateBookById(int id, Book updatedBook) throws ResourceNotFoundException {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + " not found"));
        book.setName(updatedBook.getName());
        book.setPrice(updatedBook.getPrice());
        book.setWarehouse(updatedBook.getWarehouse());
        book.setQuantity(updatedBook.getQuantity());
        return repository.save(book);
    }

    public Book partUpdateBookById(int id, Book updatedBook) throws ResourceNotFoundException {
        Book book = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book with id = " + id + " not found"));
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

    public List<BookSearchResult> getAllDistinctBooks() {
        return repository.findAllDistinctBooks();
    }

    public List<Map<String, Object>> getSpecialBooks() {
        List<Book> specialBooks = repository.findSpecialBooks();
        return getMaps(specialBooks);
    }

    private List<Map<String, Object>> getMaps(List<Book> specialBooks) {
        List<Map<String, Object>> resultList = new LinkedList<>();
        for (Book book : specialBooks) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("name", book.getName());
            map.put("price", book.getPrice());
            resultList.add(map);
        }
        return resultList;
    }
}
