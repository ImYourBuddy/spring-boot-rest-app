package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT DISTINCT b.name, b.price FROM books b", nativeQuery = true)
    public List<String> findAllDistinctBooks();

    @Query(value = "SELECT b.name, b.price FROM books b WHERE b.name LIKE '%Windows%' OR b.price > 20000"
            , nativeQuery = true)
    public List<String> findSpecialBooks();
}
