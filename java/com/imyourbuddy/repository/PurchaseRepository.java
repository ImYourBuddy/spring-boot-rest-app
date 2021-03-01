package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Purchase;
import com.imyourbuddy.entity.search.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    @Query(value = "SELECT DISTINCT EXTRACT(MONTH FROM p.date) as month FROM purchase p", nativeQuery = true)
    public List<PurchaseMonthSearchResult> findAllDistinctMonths();

    @Query(value = "SELECT b.lastname as buyer, s.name as shop FROM purchase p INNER JOIN buyer b " +
            "ON p.buyer = b.id INNER JOIN shop s ON p.seller = s.id", nativeQuery = true)
    public List<PurchaseBuyerShopResult> findBuyerAndShop();

    @Query(value = "SELECT p.date as date, b.lastname as buyer, b.discount as discount, bs.name as book" +
            ", p.quantity as quantity FROM purchase p INNER JOIN buyer b ON p.buyer = b.id INNER JOIN books bs " +
            "ON p.book = bs.id", nativeQuery = true)
    public List<SpecialPurchaseSearchResult> findSpecialPurchase();

    @Query(value = "SELECT p.order_number as order, b.lastname as buyer, p.date as date FROM purchase p " +
            "INNER JOIN buyer b ON p.buyer = b.id WHERE p.total_price >= 60000", nativeQuery = true)
    public List<ExpensivePurchaseSearchResult> findExpensivePurchases();

    @Query(value = "SELECT b.lastname as buyer, b.geography as geography, p.date as date FROM purchase p " +
            "INNER JOIN buyer b ON p.buyer = b.id INNER JOIN shop s ON p.seller = s.id WHERE s.geography = b.geography " +
            "AND EXTRACT(MONTH from p.date) >= 3 ORDER BY b.lastname", nativeQuery = true)
    public List<PurchaseDistrictSearchResult> findPurchasesInDist();

    @Query(value = "SELECT DISTINCT s.name as name, s.geography as geography, s.commission as commission FROM purchase p" +
            " INNER JOIN shop s ON p.seller = s.id INNER JOIN buyer b ON p.buyer = b.id WHERE s.geography != 'Avtozavodsky' " +
            "AND b.discount BETWEEN 0.1 AND 0.15", nativeQuery = true)
    public List<PurchaseNotAvtozResult> findPurchasesNotInAvtoz();

    @Query(value = "SELECT DISTINCT bs.name as name, bs.warehouse as warehouse, bs.quantity as quantity,bs.price as price " +
            "FROM purchase p INNER JOIN books bs ON p.book = bs.id INNER JOIN shop s ON p.seller = s.id " +
            "WHERE bs.quantity > 10 AND bs.warehouse = s.geography " +
            "ORDER BY bs.price", nativeQuery = true)
    public List<PurchaseInStorageResult> findPurchasesInStorage();
}
