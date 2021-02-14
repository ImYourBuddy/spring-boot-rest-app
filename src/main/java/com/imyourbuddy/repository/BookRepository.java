package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book,Integer> {

    @Query(value = "UPDATE books b SET (name = :name, price = :price, warehouse = :warehouse, quantity = :quantity) WHERE b.id = :id"
    , nativeQuery = true)
    public void updateById(@Param("id") int id, @Param("name") String name, @Param("price") double price
    , @Param("warehouse") String warehouse, @Param("quantity") int quantity);
}
