package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Integer> {

    @Query(value = "SELECT * FROM shop s WHERE s.geography IN('Sormovsky','Sovetsky')", nativeQuery = true)
    public List<Shop> findLocalShop();

    @Modifying
    @Transactional
    @Query(value = "UPDATE shop s SET deleted = true WHERE s.id =:id",nativeQuery = true)
    public void deleteShopById(@Param("id") int id);
}
