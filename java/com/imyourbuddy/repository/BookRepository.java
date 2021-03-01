package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Book;
import com.imyourbuddy.entity.search.BookSearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {
    @Query(value = "SELECT DISTINCT b.name as name, b.price as price FROM books b", nativeQuery = true)
    public List<BookSearchResult>findAllDistinctBooks();

    @Query(value = "SELECT * FROM books b WHERE b.name LIKE '%Windows%' OR b.price > 20000" +
            "ORDER BY b.name, b.price DESC"
            , nativeQuery = true)
    public List<Book> findSpecialBooks();

    @Modifying
    @Transactional
    @Query(value = "UPDATE books b SET deleted = true WHERE b.id =:id", nativeQuery = true)
    public void deleteBookById(@Param("id") int id);
}
