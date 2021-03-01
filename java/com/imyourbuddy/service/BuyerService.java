package com.imyourbuddy.service;

import com.imyourbuddy.entity.Buyer;
import com.imyourbuddy.entity.search.BuyerSearchResult;
import com.imyourbuddy.exception.ResourceNotFoundException;
import com.imyourbuddy.repository.BuyerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BuyerService {
    private final BuyerRepository repository;

    @Autowired
    public BuyerService(BuyerRepository repository) {
        this.repository = repository;
    }

    public List<Buyer> getAllBuyers() {
        return repository.findAll();
    }

    public Buyer getBuyerById(int id) throws ResourceNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + " not found"));
    }

    public Buyer deleteBuyerById(int id) throws ResourceNotFoundException {
        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + " not found"));
        repository.deleteBuyerById(id);
        return buyer;
    }

    public Buyer saveBuyer(Buyer buyer) {
        return repository.save(buyer);
    }

    public Buyer updateBuyerById(int id, Buyer updatedBuyer) throws ResourceNotFoundException {
        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + " not found"));
        buyer.setLastName(updatedBuyer.getLastName());
        buyer.setGeography(updatedBuyer.getGeography());
        buyer.setDiscount(updatedBuyer.getDiscount());
        return repository.save(buyer);
    }

    public Buyer partUpdateBuyerById(int id, Buyer updatedBuyer) throws ResourceNotFoundException {
        Buyer buyer = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Buyer with id = " + id + " not found"));
        if (updatedBuyer.getLastName() != null) {
            buyer.setLastName(updatedBuyer.getLastName());
        }
        if (updatedBuyer.getGeography() != null) {
            buyer.setGeography(updatedBuyer.getGeography());
        }
        if (updatedBuyer.getDiscount() != null) {
            buyer.setDiscount(updatedBuyer.getDiscount());
        }
        return repository.save(buyer);
    }

    public List<BuyerSearchResult> getAllDistinctDistricts() {
        return repository.findAllDistinctDistricts();

    }

    public List<Map<String, Object>> getAllByNizhDist() {
        List<Map<String, Object>> resultList = new LinkedList<>();
        List<Buyer> allByNizhDist = repository.findAllByNizhDist();
        for (Buyer buyer : allByNizhDist) {
            Map<String, Object> map = new LinkedHashMap<>();
            map.put("lastname", buyer.getLastName());
            map.put("discount", buyer.getDiscount());
            resultList.add(map);
        }
        return resultList;
    }
}
