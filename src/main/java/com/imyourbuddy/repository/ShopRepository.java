package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @Query(value = "SELECT s.name FROM shop s WHERE s.geography IN('Sormovsky','Sovetsky')", nativeQuery = true)
    public List<String> findLocalShop();
}
