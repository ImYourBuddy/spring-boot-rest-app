package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Buyer;
import com.imyourbuddy.entity.search.BuyerSearchResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    @Query(value = "SELECT DISTINCT b.geography as dist FROM buyer b", nativeQuery = true)
    public List<BuyerSearchResult> findAllDistinctDistricts();

    @Query(value = "SELECT * FROM buyer b WHERE b.geography = 'Nizhegorodsky'",nativeQuery = true)
    public List<Buyer> findAllByNizhDist();

    @Modifying
    @Transactional
    @Query(value = "UPDATE buyer b SET deleted = true WHERE b.id =:id", nativeQuery = true)
    public void deleteBuyerById(@Param("id") int id);

}
