package com.imyourbuddy.repository;

import com.imyourbuddy.entity.Buyer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyerRepository extends JpaRepository<Buyer, Integer> {
    @Query(value = "SELECT DISTINCT b.geography FROM buyer b", nativeQuery = true)
    public List<String> findAllDistinctDistricts();

    @Query(value = "SELECT b.last_name, b.discount FROM buyer b WHERE b.geography = 'Nizhegorodsky'",nativeQuery = true)
    public List<String> findAllByNizhDist();
}
