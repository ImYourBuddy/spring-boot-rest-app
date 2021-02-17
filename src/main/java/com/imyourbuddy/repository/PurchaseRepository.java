package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Purchase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


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

    @Query(value = "SELECT p.order_number, b.last_name, p.date FROM purchase p INNER JOIN buyer b ON p.buyer = b.id " +
            "WHERE p.total_price > 60000", nativeQuery = true)
    public List<String> findExpensivePurchases();

    @Query(value = "SELECT b.last_name, b.geography, p.date FROM purchase p INNER JOIN buyer b ON p.buyer = b.id " +
            "INNER JOIN shop s ON p.seller = s.id WHERE s.geography = b.geography AND EXTRACT(MONTH from p.date) < 3 " +
            "ORDER BY b.last_name", nativeQuery = true)
    public List<String> findPurchasesInDist();

    @Query(value = "SELECT DISTINCT s.name, s.geography, s.commission FROM purchase p INNER JOIN shop s " +
            "ON p.seller = s.id INNER JOIN buyer b ON p.buyer = b.id WHERE s.geography != 'Avtozavodsky' " +
            "AND b.discount BETWEEN 0.1 AND 0.15", nativeQuery = true)
    public List<String> findPurchasesNotInAvtoz();

    @Query(value = "SELECT DISTINCT bs.name, bs.warehouse, bs.quantity, bs.price FROM purchase p INNER JOIN books bs " +
            "ON p.book = bs.id INNER JOIN shop s ON p.seller = s.id WHERE bs.quantity > 10 AND bs.warehouse = s.geography " +
            "ORDER BY bs.price", nativeQuery = true)
    public List<String> findPurchasesInStorage();
}
