package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query(value = "SELECT DISTINCT EXTRACT(MONTH FROM p.date) FROM purchase p", nativeQuery = true)
    public List<Integer> findAllDistinctMonths();

    @Query(value = "SELECT b.last_name, s.name FROM purchase p INNER JOIN buyer b ON p.buyer = b.id " +
            "INNER JOIN shop s ON p.seller = s.id", nativeQuery = true)
    public List<String> findBuyerAndShop();

    @Query(value = "SELECT p.date, b.last_name, b.discount, bs.name, p.quantity FROM purchase p INNER JOIN buyer b" +
            " ON p.buyer = b.id INNER JOIN books bs ON p.book = bs.id", nativeQuery = true)
    public List<String> findSpecialPurchase();
}
